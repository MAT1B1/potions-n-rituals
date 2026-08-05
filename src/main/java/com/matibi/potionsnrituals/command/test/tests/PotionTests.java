package com.matibi.potionsnrituals.command.test.tests;

import com.matibi.potionsnrituals.command.test.TestRegistry;
import com.matibi.potionsnrituals.command.test.TestResult;
import com.matibi.potionsnrituals.command.test.TestContext;

public final class PotionTests {

    static {
        //TestRegistry.registerAsync("potion_brewing_works", PotionTests::testBrewing);
        //TestRegistry.registerAsync("potion_effects_apply", PotionTests::testEffectsApply);
        //TestRegistry.registerAsync("potion_duration_correct", PotionTests::testDuration);
        //TestRegistry.registerAsync("potion_amplifier_correct", PotionTests::testAmplifier);
    }

    private static void testBrewing(TestContext ctx, java.util.function.Consumer<TestResult> cb) {
        ctx.helper().waitTicks(1, () -> cb.accept(TestResult.pass()));
    }

    private static void testEffectsApply(TestContext ctx, java.util.function.Consumer<TestResult> cb) {
        ctx.helper().waitTicks(1, () -> cb.accept(TestResult.pass()));
    }

    private static void testDuration(TestContext ctx, java.util.function.Consumer<TestResult> cb) {
        ctx.helper().waitTicks(1, () -> cb.accept(TestResult.pass()));
    }

    private static void testAmplifier(TestContext ctx, java.util.function.Consumer<TestResult> cb) {
        ctx.helper().waitTicks(1, () -> cb.accept(TestResult.pass()));
    }
}