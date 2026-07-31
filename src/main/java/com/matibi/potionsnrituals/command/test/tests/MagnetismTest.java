package com.matibi.potionsnrituals.command.test.tests;

import com.matibi.potionsnrituals.command.test.TestAssert;
import com.matibi.potionsnrituals.command.test.TestResult;
import com.matibi.potionsnrituals.command.test.TestSuite;
import com.matibi.potionsnrituals.effect.ModEffects;
import com.matibi.potionsnrituals.util.TickManager;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class MagnetismTest implements TestSuite {

    @Override
    public String id() {
        return "magnetism:pull_items";
    }

    @Override
    public String description() {
        return "Vérifie que Magnetisme attire les items";
    }

    @Override
    public String category() {
        return "effects";
    }

    @Override
    public TestResult run(ServerLevel level, ServerPlayer player) {
        ItemEntity item = new ItemEntity(level, player.getX() + 5, player.getY(), player.getZ(),
                new ItemStack(Items.DIAMOND));
        level.addFreshEntity(item);

        TickManager.runLater(20 * 3, _ -> {
            player.addEffect(new MobEffectInstance(ModEffects.MAGNETISM, 20 * 60, 0));
            TestAssert.forceTickEffects(level, player);
            player.removeEffect(ModEffects.MAGNETISM);

            boolean hasVelocity = item.getDeltaMovement().lengthSqr() > 0.001;
            item.remove(Entity.RemovalReason.DISCARDED);

            if (hasVelocity)
                TestAssert.sendAsyncResult(id(),
                        TestResult.pass("Magnetism applique une vélocité (delta=" + String.format("%.3f", item.getDeltaMovement().length()) + ")"));
            else
                TestAssert.sendAsyncResult(id(),
                        TestResult.fail("Pas de vélocité sur l'item"));
        });
        return TestResult.pending("Magnetism");
    }
}
