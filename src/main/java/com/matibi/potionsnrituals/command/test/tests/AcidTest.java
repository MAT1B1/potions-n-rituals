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

public class AcidTest implements TestSuite {

    @Override
    public String id() {
        return "acid:tick_damage";
    }

    @Override
    public String description() {
        return "Vérifie que Acid inflige des dégâts par tick";
    }

    @Override
    public String category() {
        return "effects";
    }

    @Override
    public TestResult run(ServerLevel level, ServerPlayer player) {
        LivingEntity zombie = TestAssert.spawnEntity(level, player, EntityTypes.ZOMBIE, "AcidTest");
        float healthBefore = zombie.getHealth();

        zombie.addEffect(new MobEffectInstance(ModEffects.ACID, 20 * 60, 0));
        TestAssert.forceTickEffects(level, zombie);
        TickManager.flush(level.getServer());

        if (zombie.getHealth() >= healthBefore) {
            TestAssert.removeEntity(zombie);
            return TestResult.fail("Acid n'a pas infligé de dégâts");
        }

        TestAssert.removeEntity(zombie);
        return TestResult.pass("Acid inflige des dégâts : " + (healthBefore - zombie.getHealth()));
    }
}
