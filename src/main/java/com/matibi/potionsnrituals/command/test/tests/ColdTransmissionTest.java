package com.matibi.potionsnrituals.command.test.tests;

import com.matibi.potionsnrituals.command.test.TestAssert;
import com.matibi.potionsnrituals.command.test.TestResult;
import com.matibi.potionsnrituals.command.test.TestSuite;
import com.matibi.potionsnrituals.effect.ModEffects;
import com.matibi.potionsnrituals.util.TickManager;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.LivingEntity;

public class ColdTransmissionTest implements TestSuite {

    @Override
    public String id() {
        return "cold:transmission";
    }

    @Override
    public String description() {
        return "Vérifie que l'effet Cold peut être appliqué sur le joueur et les entités";
    }

    @Override
    public String category() {
        return "effects";
    }

    @Override
    public TestResult run(ServerLevel level, ServerPlayer player) {
        LivingEntity target = TestAssert.spawnEntity(level, player, EntityTypes.ZOMBIE, "ColdTransmissionTest");
        try {
            target.teleportTo(player.getX() - 1, player.getY(), player.getZ());

            TestAssert.clearPlayer(player);
            target.addEffect(new MobEffectInstance(ModEffects.COLD, 72000, 0));
            player.addEffect(new MobEffectInstance(ModEffects.COLD, 72000, 0));
            TickManager.flush(level.getServer());

            if (!player.hasEffect(ModEffects.COLD))
                return TestResult.fail("Impossible d'appliquer Cold au joueur");

            if (!target.hasEffect(ModEffects.COLD))
                return TestResult.fail("Impossible d'appliquer Cold à l'entité");

            return TestResult.pass("Cold peut être appliqué sur le joueur et les entités");
        } finally {
            TestAssert.removeEntity(target);
        }
    }
}
