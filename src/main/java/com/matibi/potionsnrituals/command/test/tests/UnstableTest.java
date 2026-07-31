package com.matibi.potionsnrituals.command.test.tests;

import com.matibi.potionsnrituals.command.test.TestResult;
import com.matibi.potionsnrituals.command.test.TestSuite;
import com.matibi.potionsnrituals.effect.ModEffects;
import com.matibi.potionsnrituals.util.TickManager;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;

public class UnstableTest implements TestSuite {

    @Override
    public String id() {
        return "unstable:apply";
    }

    @Override
    public String description() {
        return "Vérifie qu'Unstable s'applique et a un effet";
    }

    @Override
    public String category() {
        return "effects";
    }

    @Override
    public TestResult run(ServerLevel level, ServerPlayer player) {
        player.addEffect(new MobEffectInstance(ModEffects.UNSTABLE, 1, 0));
        TickManager.flush(level.getServer());
        return TestResult.pass("Unstable appliqué");
    }
}
