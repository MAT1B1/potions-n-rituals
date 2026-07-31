package com.matibi.potionsnrituals.command.test.tests;

import com.matibi.potionsnrituals.command.test.TestResult;
import com.matibi.potionsnrituals.command.test.TestSuite;
import com.matibi.potionsnrituals.effect.ModEffects;
import com.matibi.potionsnrituals.util.TickManager;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;

public class ResonanceTest implements TestSuite {

    @Override
    public String id() {
        return "resonance:apply";
    }

    @Override
    public String description() {
        return "Vérifie que Resonance s'applique sur le joueur";
    }

    @Override
    public String category() {
        return "effects";
    }

    @Override
    public TestResult run(ServerLevel level, ServerPlayer player) {
        player.addEffect(new MobEffectInstance(ModEffects.RESONANCE, 20 * 60, 0));
        TickManager.flush(level.getServer());

        if (!player.hasEffect(ModEffects.RESONANCE))
            return TestResult.fail("Resonance ne s'applique pas");

        return TestResult.pass("Resonance appliqué avec succès");
    }
}
