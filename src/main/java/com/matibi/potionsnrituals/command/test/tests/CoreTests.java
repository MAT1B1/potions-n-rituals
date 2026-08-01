package com.matibi.potionsnrituals.command.test.tests;

import com.matibi.potionsnrituals.command.test.TestRegistry;
import com.matibi.potionsnrituals.command.test.TestResult;
import com.matibi.potionsnrituals.command.test.TestContext;
import com.matibi.potionsnrituals.util.TickManager;

public final class CoreTests {

    static {
        TestRegistry.register("tick_manager_oneshot", CoreTests::testTickManagerOneShot);
        TestRegistry.register("tick_manager_repeat", CoreTests::testTickManagerRepeat);
        TestRegistry.registerAsync("tick_manager_async_wait", CoreTests::testAsyncWait);
    }

    private static TestResult testTickManagerOneShot(TestContext ctx) {
        boolean[] executed = {false};
        TickManager.runLater(1, _ -> executed[0] = true);
        TickManager.flush(ctx.server());
        ctx.helper().assertTrue(executed[0], "OneShot task should execute after flush");
        return TestResult.pass();
    }

private static TestResult testTickManagerRepeat(TestContext ctx) {
        int[] count = {0};
        TickManager.registerWhile(() -> count[0] < 3, _ -> count[0]++);
        TickManager.flush(ctx.server());
        TickManager.flush(ctx.server());
        TickManager.flush(ctx.server());
        ctx.helper().assertEquals(3, count[0], "Repeat task should execute 3 times");
        return TestResult.pass();
    }

    private static void testAsyncWait(TestContext ctx, java.util.function.Consumer<TestResult> callback) {
        int[] count = {0};
        ctx.helper().waitTicks(2, () -> {
            count[0]++;
            ctx.helper().waitTicks(1, () -> {
                count[0]++;
                ctx.helper().assertEquals(2, count[0], "Should have waited 2 ticks then 1 tick");
                callback.accept(TestResult.pass());
            });
        });
    }
}