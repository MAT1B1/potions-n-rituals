package com.matibi.potionsnrituals.command.test;

import com.matibi.potionsnrituals.PotionsNRituals;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public class TestCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("pnr-test")
                .requires(CommandSourceStack::isPlayer)
                .then(Commands.literal("run")
                        .executes(TestCommand::runAllTests))
                .then(Commands.literal("list")
                        .executes(TestCommand::listTests))
                .then(Commands.literal("reload")
                        .executes(TestCommand::reloadTests))
                .then(Commands.argument("name", StringArgumentType.string())
                        .executes(TestCommand::runSingleTest))
        );
    }

    private static boolean isSurvival(ServerPlayer player) {
        return !player.isCreative();
    }

    private static int reloadTests(CommandContext<CommandSourceStack> context) {
        ServerPlayer player;
        try {
            player = context.getSource().getPlayerOrException();
        } catch (CommandSyntaxException e) {
            context.getSource().sendFailure(Component.literal("§cMust be run by a player"));
            return 0;
        }

        if (isSurvival(player)) {
            player.sendSystemMessage(Component.literal("§cYou must be an operator to run tests"));
            return 0;
        }

        TestRegistry.clear();
        ModTests.registerAll();
        player.sendSystemMessage(Component.literal("§aTest registry reloaded: " + TestRegistry.getAll().size() + " tests registered"));
        return 1;
    }

    private static int runAllTests(CommandContext<CommandSourceStack> context) {
        ServerPlayer player;
        try {
            player = context.getSource().getPlayerOrException();
        } catch (CommandSyntaxException e) {
            context.getSource().sendFailure(Component.literal("§cMust be run by a player"));
            return 0;
        }

        if (isSurvival(player)) {
            player.sendSystemMessage(Component.literal("§cYou must be an operator to run tests"));
            return 0;
        }

        List<TestEntry> tests = new ArrayList<>(TestRegistry.getAll());
        if (tests.isEmpty()) {
            player.sendSystemMessage(Component.literal("§eNo tests registered"));
            return 1;
        }

        Vec3 originalPos = player.position();
        float originalYaw = player.getYRot();
        float originalPitch = player.getXRot();

        player.sendSystemMessage(Component.literal("§6Running " + tests.size() + " test(s)..."));
        runSequential(player, tests, 0, new ArrayList<>(), results -> {
            int passed = (int) results.stream().filter(TestResult::passed).count();
            int failed = results.size() - passed;
            player.sendSystemMessage(Component.literal(
                    String.format("§6Tests complete: §a%d passed §c%d failed", passed, failed)));
            player.teleportTo(player.level(), originalPos.x, originalPos.y, originalPos.z, Set.of(), originalYaw, originalPitch, true);
        });
        return 1;
    }

    private static int runSingleTest(CommandContext<CommandSourceStack> context) {
        ServerPlayer player;
        try {
            player = context.getSource().getPlayerOrException();
        } catch (CommandSyntaxException e) {
            context.getSource().sendFailure(Component.literal("§cMust be run by a player"));
            return 0;
        }

        if (isSurvival(player)) {
            player.sendSystemMessage(Component.literal("§cYou must be an operator to run tests"));
            return 0;
        }

        String name = StringArgumentType.getString(context, "name");
        TestRegistry.get(name).ifPresentOrElse(
                test -> runTest(player, test, result -> {
                    String msg = result.passed()
                            ? "§a" + test.name()
                            : "§c" + test.name() + " fail: " + result.message();
                    player.sendSystemMessage(Component.literal(msg));
                }),
                () -> player.sendSystemMessage(Component.literal("§cTest not found: " + name))
        );
        return 1;
    }

    private static int listTests(CommandContext<CommandSourceStack> context) {
        ServerPlayer player;
        try {
            player = context.getSource().getPlayerOrException();
        } catch (CommandSyntaxException e) {
            context.getSource().sendFailure(Component.literal("§cMust be run by a player"));
            return 0;
        }

        if (isSurvival(player)) {
            player.sendSystemMessage(Component.literal("§cYou must be an operator to run tests"));
            return 0;
        }

        List<TestEntry> tests = new ArrayList<>(TestRegistry.getAll());
        if (tests.isEmpty()) {
            player.sendSystemMessage(Component.literal("§eNo tests registered"));
            return 1;
        }

        player.sendSystemMessage(Component.literal("§6Registered tests (" + tests.size() + "):"));
        for (TestEntry test : tests) {
            String type = test.isAsync() ? "§e[async]" : "§7[sync]";
            player.sendSystemMessage(Component.literal("  " + type + " §f" + test.name()));
        }
        return 1;
    }

    private static void runSequential(ServerPlayer player, List<TestEntry> tests, int index,
                                      List<TestResult> results, Consumer<List<TestResult>> onComplete) {
        if (index >= tests.size()) {
            onComplete.accept(results);
            return;
        }

        TestEntry test = tests.get(index);
        TestContext ctx = new TestContext(
                player,
                player.level().getServer(),
                player.level(),
                new TestHelper(player)
        );

        Consumer<TestResult> onTestComplete = result -> {
            String msg = result.passed()
                    ? "§a" + test.name()
                    : "§c" + test.name() + " fail: " + result.message();
            player.sendSystemMessage(Component.literal(msg));

            results.add(result);
            runSequential(player, tests, index + 1, results, onComplete);
        };

        try {
            if (test.isAsync())
                test.async().run(ctx, onTestComplete);
            else
                onTestComplete.accept(test.sync().run(ctx));
        } catch (TestHelper.TestAssertionError e) {
            onTestComplete.accept(TestResult.fail(e.getMessage()));
        } catch (Exception e) {
            PotionsNRituals.LOGGER.error("Test {} threw exception", test.name(), e);
            onTestComplete.accept(TestResult.fail("Exception: " + e.getClass().getSimpleName() + " - " + e.getMessage()));
        }
    }

    private static void runTest(ServerPlayer player, TestEntry test, Consumer<TestResult> callback) {
        TestContext ctx = new TestContext(
                player,
                player.level().getServer(),
                player.level(),
                new TestHelper(player)
        );

        try {
            if (test.isAsync())
                test.async().run(ctx, callback);
            else
                callback.accept(test.sync().run(ctx));
        } catch (TestHelper.TestAssertionError e) {
            callback.accept(TestResult.fail(e.getMessage()));
        } catch (Exception e) {
            PotionsNRituals.LOGGER.error("Test {} threw exception", test.name(), e);
            callback.accept(TestResult.fail("Exception: " + e.getClass().getSimpleName() + " - " + e.getMessage()));
        }
    }
}