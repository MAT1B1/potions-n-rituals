package com.matibi.potionsnrituals.command.test.tests;

import com.matibi.potionsnrituals.command.test.TestResult;
import com.matibi.potionsnrituals.command.test.TestSuite;
import com.matibi.potionsnrituals.effect.ModEffects;
import com.matibi.potionsnrituals.util.TickManager;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;

public class ResurrectionTest implements TestSuite {

    @Override
    public String id() {
        return "resurrection:apply";
    }

    @Override
    public String description() {
        return "Vérifie que Resurrection s'applique correctement";
    }

    @Override
    public String category() {
        return "effects";
    }

    @Override
    public TestResult run(ServerLevel level, ServerPlayer player) {
        player.addEffect(new MobEffectInstance(ModEffects.RESURRECTION, -1, 0));
        TickManager.flush(level.getServer());

        if (!player.hasEffect(ModEffects.RESURRECTION))
            return TestResult.fail("Resurrection ne s'applique pas");

        int amp = player.getEffect(ModEffects.RESURRECTION).getAmplifier();
        if (amp != 0)
            return TestResult.fail("Resurrection amplifier devrait être 0, obtenu " + amp);

        return TestResult.pass("Resurrection appliqué avec amplifier " + amp);
    }
}
