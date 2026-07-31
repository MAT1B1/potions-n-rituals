package com.matibi.potionsnrituals.command.test.tests;

import com.matibi.potionsnrituals.command.test.TestAssert;
import com.matibi.potionsnrituals.command.test.TestResult;
import com.matibi.potionsnrituals.command.test.TestSuite;
import com.matibi.potionsnrituals.effect.ModEffects;
import com.matibi.potionsnrituals.util.TickManager;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class OblivionTest implements TestSuite {

    @Override
    public String id() {
        return "oblivion:forget_items";
    }

    @Override
    public String description() {
        return "Vérifie qu'Oblivion supprime des items de l'inventaire";
    }

    @Override
    public String category() {
        return "effects";
    }

    @Override
    public TestResult run(ServerLevel level, ServerPlayer player) {
        for (int i = 0; i < player.getInventory().getContainerSize(); i++)
            player.getInventory().setItem(i, new ItemStack(Items.DIAMOND));

        player.addEffect(new MobEffectInstance(ModEffects.OBLIVION, 1, 0));
        TestAssert.forceTickEffects(level, player);
        TickManager.flush(level.getServer());

        TickManager.runLater(1, _ -> {
            int emptyCount = 0;
            for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
                if (player.getInventory().getItem(i).isEmpty())
                    emptyCount++;
            }

            if (emptyCount == 0)
                TestAssert.sendAsyncResult(id(),
                        TestResult.fail("Oblivion n'a rien supprimé"));
            else
                TestAssert.sendAsyncResult(id(),
                        TestResult.pass("Oblivion a supprimé " + emptyCount + " slots"));
        });
        return TestResult.pending("Oblivion en attente du tick...");
    }
}
