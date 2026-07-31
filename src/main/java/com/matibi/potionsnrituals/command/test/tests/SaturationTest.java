package com.matibi.potionsnrituals.command.test.tests;

import com.matibi.potionsnrituals.command.test.TestAssert;
import com.matibi.potionsnrituals.command.test.TestResult;
import com.matibi.potionsnrituals.command.test.TestSuite;
import com.matibi.potionsnrituals.effect.ModEffects;
import com.matibi.potionsnrituals.util.TickManager;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;

public class SaturationTest implements TestSuite {

    @Override
    public String id() {
        return "saturation:restore";
    }

    @Override
    public String description() {
        return "Vérifie que Saturation restaure la saturation";
    }

    @Override
    public String category() {
        return "effects";
    }

    @Override
    public TestResult run(ServerLevel level, ServerPlayer player) {
        var food = player.getFoodData();
        food.eat(6, 0);

        float satBefore = food.getSaturationLevel();
        player.addEffect(new MobEffectInstance(ModEffects.SATURATION, 20 * 60, 0));
        player.tickCount = (player.tickCount / 10 + 1) * 10;
        TestAssert.forceTickEffects(level, player);
        TickManager.flush(level.getServer());

        if (food.getSaturationLevel() <= satBefore)
            return TestResult.fail("Saturation n'a pas augmenté la saturation");

        return TestResult.pass("Saturation augmente la saturation");
    }
}
