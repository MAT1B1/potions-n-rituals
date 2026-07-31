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

public class DeathTest implements TestSuite {

    @Override
    public String id() {
        return "death:instant_kill";
    }

    @Override
    public String description() {
        return "Vérifie que Death tue instantanément une entité non-undead";
    }

    @Override
    public String category() {
        return "effects";
    }

    @Override
    public TestResult run(ServerLevel level, ServerPlayer player) {
        LivingEntity sheep = TestAssert.spawnEntity(level, player, EntityTypes.SHEEP, "DeathTest");
        sheep.addEffect(new MobEffectInstance(ModEffects.DEATH, 1, 0));
        TestAssert.forceTickEffects(level, sheep);
        TickManager.flush(level.getServer());
        TestAssert.entityIsDead(sheep, "Death : le mouton aurait dû mourir");
        TestAssert.removeEntity(sheep);
        return TestResult.pass("Death tue instantanément les entités non-undead");
    }
}
