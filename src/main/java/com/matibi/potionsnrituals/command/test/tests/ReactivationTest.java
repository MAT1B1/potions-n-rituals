package com.matibi.potionsnrituals.command.test.tests;

import com.matibi.potionsnrituals.command.test.TestAssert;
import com.matibi.potionsnrituals.command.test.TestResult;
import com.matibi.potionsnrituals.command.test.TestSuite;
import com.matibi.potionsnrituals.config.ModConfig;
import com.matibi.potionsnrituals.effect.ModEffects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class ReactivationTest implements TestSuite {

    @Override
    public String id() {
        return "reactivation:double_duration";
    }

    @Override
    public String description() {
        return "Vérifie que Reactivation double la durée des effets";
    }

    @Override
    public String category() {
        return "effects";
    }

    @Override
    public TestResult run(ServerLevel level, ServerPlayer player) {
        player.addEffect(new MobEffectInstance(MobEffects.SPEED, 100, 0));
        TestAssert.forceTickEffects(level, player);

        int durationBefore = player.getEffect(MobEffects.SPEED).getDuration();

        MobEffect effect = ModEffects.REACTIVATION.value();
        effect.applyInstantaneousEffect(level, null, player, player, 0, 1.0);

        int durationAfter = player.getEffect(MobEffects.SPEED).getDuration();

        int expected = durationBefore * ModConfig.get().reactivation_duration;
        if (durationAfter < expected)
            return TestResult.fail("Reactivation n'a pas doublé la durée : " + durationBefore + " → " + durationAfter + " (attendu ≥ " + expected + ")");

        return TestResult.pass("Reactivation double la durée : " + durationBefore + " → " + durationAfter);
    }
}
