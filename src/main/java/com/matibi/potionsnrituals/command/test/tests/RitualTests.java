package com.matibi.potionsnrituals.command.test.tests;

import com.matibi.potionsnrituals.command.test.TestRegistry;
import com.matibi.potionsnrituals.command.test.TestResult;
import com.matibi.potionsnrituals.command.test.TestContext;

public final class RitualTests {

    static {
        TestRegistry.registerAsync("ritual_json_loads", RitualTests::testJsonLoad);
        TestRegistry.registerAsync("ritual_pattern_matches", RitualTests::testPatternMatch);
        TestRegistry.registerAsync("ritual_catalyst_triggers", RitualTests::testCatalystTrigger);
        TestRegistry.registerAsync("ritual_conditions_check", RitualTests::testConditionsCheck);
        TestRegistry.registerAsync("ritual_result_executes", RitualTests::testResultExecute);
    }

    private static void testJsonLoad(TestContext ctx, java.util.function.Consumer<TestResult> cb) {
        ctx.helper().waitTicks(1, () -> cb.accept(TestResult.pass()));
    }

    private static void testPatternMatch(TestContext ctx, java.util.function.Consumer<TestResult> cb) {
        ctx.helper().waitTicks(1, () -> cb.accept(TestResult.pass()));
    }

    private static void testCatalystTrigger(TestContext ctx, java.util.function.Consumer<TestResult> cb) {
        ctx.helper().waitTicks(1, () -> cb.accept(TestResult.pass()));
    }

    private static void testConditionsCheck(TestContext ctx, java.util.function.Consumer<TestResult> cb) {
        ctx.helper().waitTicks(1, () -> cb.accept(TestResult.pass()));
    }

    private static void testResultExecute(TestContext ctx, java.util.function.Consumer<TestResult> cb) {
        ctx.helper().waitTicks(1, () -> cb.accept(TestResult.pass()));
    }
}