package com.matibi.potionsnrituals.command.test;

import com.matibi.potionsnrituals.util.TickManager;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class TestCommand {

    private static final Component HEADER = Component.literal("§6§m        §r §6§lPNR Test Suite §6§m        §r");
    private static final Component FOOTER = Component.literal("§6§m                                §r");

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("pnr")
                .then(Commands.literal("test")
                        .requires(CommandSourceStack::isPlayer)
                        .then(Commands.literal("list")
                                .executes(TestCommand::list))
                        .then(Commands.literal("run")
                                .then(Commands.argument("id", StringArgumentType.greedyString())
                                        .suggests((_, builder) -> {
                                            for (TestSuite t : ModTests.getAll())
                                                builder.suggest(t.id());
                                            return builder.buildFuture();
                                        })
                                        .executes(TestCommand::runSingle)))
                        .then(Commands.literal("run-all")
                                .executes(TestCommand::runAll))
                        .then(Commands.literal("category")
                                .then(Commands.argument("name", StringArgumentType.word())
                                        .suggests((_, builder) -> {
                                            for (String cat : ModTests.getCategories())
                                                builder.suggest(cat);
                                            return builder.buildFuture();
                                        })
                                        .executes(TestCommand::runCategory)))));
    }

    private static int list(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        ServerPlayer player = ctx.getSource().getPlayerOrException();
        Collection<TestSuite> tests = ModTests.getAll();

        player.sendSystemMessage(HEADER);
        player.sendSystemMessage(Component.literal("§7" + tests.size() + " tests disponibles :"));

        String currentCat = "";
        for (TestSuite t : tests) {
            if (!t.category().equals(currentCat)) {
                currentCat = t.category();
                player.sendSystemMessage(Component.literal(" §8[" + currentCat + "]§r"));
            }
            player.sendSystemMessage(Component.literal("  §e" + t.id() + "§7 - " + t.description()));
        }
        player.sendSystemMessage(FOOTER);
        return ModTests.count();
    }

    private static int runSingle(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        ServerPlayer player = ctx.getSource().getPlayerOrException();
        ServerLevel level = player.level();

        String id = StringArgumentType.getString(ctx, "id");
        TestSuite test = ModTests.get(id);

        if (test == null) {
            player.sendSystemMessage(Component.literal("§cTest introuvable : " + id));
            return 0;
        }

        player.sendSystemMessage(Component.literal("§e▶ Running " + test.id() + "..."));
        TestResult result = executeTest(test, level, player);
        player.sendSystemMessage(result.formatted());
        return result.status() == TestStatus.PASS ? 1 : 0;
    }

    private static int runAll(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        ServerPlayer player = ctx.getSource().getPlayerOrException();
        ServerLevel level = player.level();

        player.sendSystemMessage(HEADER);
        return runTests(player, level, ModTests.getAll());
    }

    private static int runCategory(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        ServerPlayer player = ctx.getSource().getPlayerOrException();
        ServerLevel level = player.level();

        String category = StringArgumentType.getString(ctx, "name");
        List<TestSuite> tests = ModTests.getByCategory(category);

        if (tests.isEmpty()) {
            player.sendSystemMessage(Component.literal("§cAucun test trouvé pour la catégorie : " + category));
            return 0;
        }

        player.sendSystemMessage(Component.literal("§e▶ Running category: " + category));
        return runTests(player, level, tests);
    }

    private static int runTests(ServerPlayer player, ServerLevel level, Collection<TestSuite> tests) {
        TestAssert.clearAsyncResults();
        List<String> pendingIds = new ArrayList<>();
        List<TestSuite> testList = List.copyOf(tests);
        List<TestResult> results = new ArrayList<>(testList.size());

        for (TestSuite test : testList) {
            TestResult result = executeTest(test, level, player);
            results.add(result);
            if (result.status() == TestStatus.PENDING) {
                pendingIds.add(test.id());
            }
        }

        Runnable displayAll = () -> {
            int passed = 0, failed = 0, errors = 0;
            for (int i = 0; i < results.size(); i++) {
                if (results.get(i).status() == TestStatus.PENDING) {
                    TestResult real = TestAssert.consumeAsyncResult(testList.get(i).id());
                    results.set(i, real);
                }
                player.sendSystemMessage(results.get(i).formatted());
                switch (results.get(i).status()) {
                    case PASS -> passed++;
                    case FAIL -> failed++;
                    case ERROR -> errors++;
                }
            }
            sendSummary(player, passed, failed, errors);
        };

        if (pendingIds.isEmpty()) {
            displayAll.run();
        } else {
            TickManager.registerUntil(
                    () -> pendingIds.stream().allMatch(TestAssert::hasAsyncResult),
                    _ -> displayAll.run());
        }

        return 0;
    }

    private static void sendSummary(ServerPlayer player, int passed, int failed, int errors) {
        Component summary = Component.literal("§7Résultats : §a" + passed + " passé§f"
                + (failed > 0 ? ", §c" + failed + " échoué" : "")
                + (errors > 0 ? ", §e" + errors + " erreur" : ""));
        player.sendSystemMessage(summary);
        player.sendSystemMessage(FOOTER);
    }

    private static TestResult executeTest(TestSuite test, ServerLevel level, ServerPlayer player) {
        boolean wasCreative = player.isCreative();
        boolean wasSpectator = player.isSpectator();
        player.setGameMode(GameType.SURVIVAL);

        try {
            TestAssert.clearPlayer(player);
            return test.run(level, player);
        } catch (AssertionError e) {
            return TestResult.fail(Component.literal(test.id() + " : " + e.getMessage()));
        } catch (Exception e) {
            return TestResult.error(Component.literal(test.id() + " : §7" + e.getClass().getSimpleName() + " - " + e.getMessage()));
        } finally {
            if (player.isDeadOrDying()) {
                player.setHealth(player.getMaxHealth());
                player.teleportTo(level, player.getX(), player.getY(), player.getZ(), Set.of(), player.getYRot(), player.getXRot(), true);
            }
            if (wasCreative)
                player.setGameMode(GameType.CREATIVE);
            else if (wasSpectator)
                player.setGameMode(GameType.SPECTATOR);
        }
    }
}
