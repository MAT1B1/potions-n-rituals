package com.matibi.potionsnrituals.command.test.tests;

import com.matibi.potionsnrituals.command.test.TestAssert;
import com.matibi.potionsnrituals.command.test.TestResult;
import com.matibi.potionsnrituals.command.test.TestSuite;
import com.matibi.potionsnrituals.config.ModConfig;
import com.matibi.potionsnrituals.effect.ModEffects;
import com.matibi.potionsnrituals.util.TickManager;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.LivingEntity;

public class AftermathTest implements TestSuite {

    @Override
    public String id() {
        return "aftermath:kill_below_half";
    }

    @Override
    public String description() {
        return "Vérifie que la suppression d'Aftermath tue un mob si sa vie est < 50%";
    }

    @Override
    public String category() {
        return "effects";
    }

    @Override
    public TestResult run(ServerLevel level, ServerPlayer player) {
        LivingEntity zombie = TestAssert.spawnEntity(level, player, EntityTypes.ZOMBIE, "AftermathTest");
        float maxHealth = zombie.getMaxHealth();
        float halfFraction = ModConfig.get().health_required_after_aftermath;
        float belowHalf = maxHealth * halfFraction * 0.8f;

        zombie.setHealth(belowHalf);
        zombie.addEffect(new MobEffectInstance(ModEffects.AFTERMATH, 20 * 60, 0));

        float healthBefore = zombie.getHealth();
        zombie.removeEffect(ModEffects.AFTERMATH);
        TickManager.flush(level.getServer());

        if (zombie.getHealth() >= healthBefore) {
            TestAssert.removeEntity(zombie);
            return TestResult.fail("Aftermath n'a pas infligé de dégâts en dessous de " + (halfFraction * 100) + "%");
        }

        zombie.setHealth(maxHealth);

        float aboveHalf = maxHealth * (halfFraction + 0.1f);
        zombie.setHealth(aboveHalf);
        zombie.addEffect(new MobEffectInstance(ModEffects.AFTERMATH, 20 * 60, 0));

        healthBefore = zombie.getHealth();
        zombie.removeEffect(ModEffects.AFTERMATH);
        TickManager.flush(level.getServer());

        if (zombie.getHealth() < healthBefore - 0.01f) {
            TestAssert.removeEntity(zombie);
            return TestResult.fail("Aftermath a infligé des dégâts alors que la vie > " + (halfFraction * 100) + "%");
        }

        TestAssert.removeEntity(zombie);
        return TestResult.pass("Aftermath tue en dessous de 50%, épargne au-dessus");
    }
}
