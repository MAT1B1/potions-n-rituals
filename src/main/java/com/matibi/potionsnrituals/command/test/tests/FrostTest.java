package com.matibi.potionsnrituals.command.test.tests;

import com.matibi.potionsnrituals.command.test.TestResult;
import com.matibi.potionsnrituals.command.test.TestSuite;
import com.matibi.potionsnrituals.effect.ModEffects;
import com.matibi.potionsnrituals.util.TickManager;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class FrostTest implements TestSuite {

    @Override
    public String id() {
        return "frost:slowness_freeze";
    }

    @Override
    public String description() {
        return "Vérifie que Frost applique le ralentissement";
    }

    @Override
    public String category() {
        return "effects";
    }

    @Override
    public TestResult run(ServerLevel level, ServerPlayer player) {
        player.addEffect(new MobEffectInstance(ModEffects.FROST, 20 * 60, 0));
        player.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 5 * 20, 3));
        TickManager.flush(level.getServer());

        if (!player.hasEffect(ModEffects.FROST))
            return TestResult.fail("Frost ne s'applique pas");

        if (!player.hasEffect(MobEffects.SLOWNESS))
            return TestResult.fail("Frost n'a pas maintenu le slowness");

        return TestResult.pass("Frost s'applique et maintient le ralentissement");
    }
}
