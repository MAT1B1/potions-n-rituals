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

public class InfinityTest implements TestSuite {

    @Override
    public String id() {
        return "infinity:infinite_duration";
    }

    @Override
    public String description() {
        return "Vérifie qu'Infinity rend les effets infinis";
    }

    @Override
    public String category() {
        return "effects";
    }

    @Override
    public TestResult run(ServerLevel level, ServerPlayer player) {
        player.addEffect(new MobEffectInstance(MobEffects.SPEED, 100, 0));
        player.addEffect(new MobEffectInstance(ModEffects.INFINITY, 1, 0));
        TestAssert.forceTickEffects(level, player);
        TickManager.flush(level.getServer());

        if (!player.hasEffect(MobEffects.SPEED))
            return TestResult.fail("Infinity a supprimé Speed");

        int duration = player.getEffect(MobEffects.SPEED).getDuration();
        if (duration != -1)
            return TestResult.fail("Speed n'est pas devenu infini, durée = " + duration);

        return TestResult.pass("Infinity rend les effets permanents");
    }
}
