package com.matibi.potionsnrituals.command.test.tests;

import com.matibi.potionsnrituals.command.test.TestAssert;
import com.matibi.potionsnrituals.command.test.TestResult;
import com.matibi.potionsnrituals.command.test.TestSuite;
import com.matibi.potionsnrituals.effect.ModEffects;
import com.matibi.potionsnrituals.util.TickManager;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class DoubleHealthTest implements TestSuite {

    @Override
    public String id() {
        return "double_health:absorption";
    }

    @Override
    public String description() {
        return "Vérifie que Double Health donne de l'absorption";
    }

    @Override
    public String category() {
        return "effects";
    }

    @Override
    public TestResult run(ServerLevel level, ServerPlayer player) {
        player.addEffect(new MobEffectInstance(ModEffects.DOUBLE_HEALTH, 1, 0));
        TestAssert.forceTickEffects(level, player);
        TickManager.flush(level.getServer());
        if (!player.hasEffect(MobEffects.ABSORPTION))
            return TestResult.fail("Double Health n'a pas appliqué l'absorption");

        int absLevel = player.getEffect(MobEffects.ABSORPTION).getAmplifier();
        return TestResult.pass("Double Health donne absorption " + absLevel);
    }
}
