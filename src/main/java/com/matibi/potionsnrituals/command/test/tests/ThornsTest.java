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

public class ThornsTest implements TestSuite {

    @Override
    public String id() {
        return "thorns:reflect_damage";
    }

    @Override
    public String description() {
        return "Vérifie que Thorns réfléchit les dégâts";
    }

    @Override
    public String category() {
        return "effects";
    }

    @Override
    public TestResult run(ServerLevel level, ServerPlayer player) {
        LivingEntity zombie = TestAssert.spawnEntity(level, player, EntityTypes.ZOMBIE, "ThornsTest");
        zombie.addEffect(new MobEffectInstance(ModEffects.THORNS, 20 * 60, 0));
        TickManager.flush(level.getServer());

        float healthBefore = zombie.getHealth();
        zombie.hurtServer(level, level.damageSources().playerAttack(player), 10.0f);

        if (zombie.getHealth() >= healthBefore) {
            TestAssert.removeEntity(zombie);
            return TestResult.fail("Thorns n'a pas réfléchi les dégâts");
        }

        TestAssert.removeEntity(zombie);
        return TestResult.pass("Thorns réfléchit les dégâts : " + (healthBefore - zombie.getHealth()) + " dégâts subis");
    }
}
