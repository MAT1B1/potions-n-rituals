package com.matibi.potionsnrituals.command.test.tests;

import com.matibi.potionsnrituals.command.test.TestRegistry;
import com.matibi.potionsnrituals.command.test.TestResult;
import com.matibi.potionsnrituals.command.test.TestContext;
import com.matibi.potionsnrituals.command.test.TestHelper;
import com.matibi.potionsnrituals.util.TickManager;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.animal.pig.Pig;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

import java.util.function.Consumer;

public final class CoreTests {

    static {
        TestRegistry.register("tick_manager_oneshot", CoreTests::testTickManagerOneShot);
        TestRegistry.register("tick_manager_repeat", CoreTests::testTickManagerRepeat);
        TestRegistry.registerAsync("tick_manager_async_wait", CoreTests::testAsyncWait);

        // === TestHelper assertion tests ===
        TestRegistry.register("helper_assert_false", CoreTests::testAssertFalse);
        TestRegistry.register("helper_assert_equals", CoreTests::testAssertEquals);
        TestRegistry.register("helper_assert_not_null", CoreTests::testAssertNotNull);
        TestRegistry.register("helper_assert_null", CoreTests::testAssertNull);

        // === TestHelper state tests ===
        TestRegistry.register("helper_give_effect", CoreTests::testGiveEffect);
        TestRegistry.register("helper_clear_effects", CoreTests::testClearEffects);
        TestRegistry.register("helper_give_item", CoreTests::testGiveItem);
        TestRegistry.register("helper_give_item_count", CoreTests::testGiveItemCount);
        TestRegistry.register("helper_clear_inventory", CoreTests::testClearInventory);
        TestRegistry.register("helper_set_player_pos_vec3", CoreTests::testSetPlayerPosVec3);
        TestRegistry.register("helper_set_player_pos_block", CoreTests::testSetPlayerPosBlock);
        TestRegistry.register("helper_set_player_look", CoreTests::testSetPlayerLook);
        TestRegistry.register("helper_has_effect", CoreTests::testHasEffect);
        TestRegistry.register("helper_player_at", CoreTests::testPlayerAt);
        TestRegistry.register("helper_has_entity_at", CoreTests::testHasEntityAt);

        // === TestHelper async tests ===
        TestRegistry.registerAsync("helper_wait_until", CoreTests::testWaitUntil);
        TestRegistry.registerAsync("helper_wait_until_timeout", CoreTests::testWaitUntilTimeout);
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

    private static void testAsyncWait(TestContext ctx, Consumer<TestResult> callback) {
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

    // === Assertion tests (sync) ===

    @SuppressWarnings("ConstantConditions")
    private static TestResult testAssertFalse(TestContext ctx) {
        TestHelper helper = ctx.helper();
        helper.assertFalse(false, "false should be false");
        try {
            helper.assertFalse(true, "Expected assertion failure");
            return TestResult.fail("assertFalse should have thrown on true condition");
        } catch (TestHelper.TestAssertionError e) {
            return TestResult.pass();
        }
    }

    private static TestResult testAssertEquals(TestContext ctx) {
        TestHelper helper = ctx.helper();
        helper.assertEquals("hello", "hello", "strings should match");
        helper.assertEquals(42, 42, "integers should match");
        helper.assertEquals(3.14d, 3.14d, "doubles should match");
        try {
            helper.assertEquals("foo", "bar", "expected failure");
            return TestResult.fail("assertEquals should have thrown on mismatched values");
        } catch (TestHelper.TestAssertionError e) {
            return TestResult.pass();
        }
    }

    @SuppressWarnings("ConstantConditions")
    private static TestResult testAssertNotNull(TestContext ctx) {
        TestHelper helper = ctx.helper();
        helper.assertNotNull(ctx.player(), "player should not be null");
        helper.assertNotNull(ctx.level(), "level should not be null");
        try {
            helper.assertNotNull(null, "expected failure");
            return TestResult.fail("assertNotNull should have thrown on null");
        } catch (TestHelper.TestAssertionError e) {
            return TestResult.pass();
        }
    }

    private static TestResult testAssertNull(TestContext ctx) {
        TestHelper helper = ctx.helper();
        helper.assertNull(null, "null should be null");
        try {
            helper.assertNull(ctx.player(), "expected failure");
            return TestResult.fail("assertNull should have thrown on non-null");
        } catch (TestHelper.TestAssertionError e) {
            return TestResult.pass();
        }
    }

    // === State manipulation tests (sync) ===

    private static TestResult testGiveEffect(TestContext ctx) {
        TestHelper helper = ctx.helper();
        helper.clearEffects();
        helper.giveEffect(MobEffects.SPEED, 200, 1);
        ctx.helper().assertTrue(
            helper.player().getEffect(MobEffects.SPEED) != null,
            "Player should have movement speed effect"
        );
        helper.clearEffects();
        return TestResult.pass();
    }

    private static TestResult testClearEffects(TestContext ctx) {
        TestHelper helper = ctx.helper();
        helper.giveEffect(MobEffects.SPEED, 200, 0);
        helper.giveEffect(MobEffects.HASTE, 200, 0);
        helper.assertTrue(helper.player().getActiveEffects().size() >= 2, "Should have at least 2 effects before clearing");
        helper.clearEffects();
        helper.assertTrue(helper.player().getActiveEffects().isEmpty(), "Should have no effects after clearing");
        return TestResult.pass();
    }

    private static TestResult testGiveItem(TestContext ctx) {
        TestHelper helper = ctx.helper();
        helper.clearInventory();
        helper.giveItem(Items.STICK);
        helper.assertTrue(helper.player().getInventory().contains(new ItemStack(Items.STICK)), "Should have a stick after giveItem");
        helper.clearInventory();
        return TestResult.pass();
    }

    private static TestResult testGiveItemCount(TestContext ctx) {
        TestHelper helper = ctx.helper();
        helper.clearInventory();
        helper.giveItem(Items.STICK, 16);
        int count = helper.player().getInventory().countItem(Items.STICK);
        helper.assertEquals(16, count, "Should have 16 sticks after giveItem with count");
        helper.clearInventory();
        return TestResult.pass();
    }

    private static TestResult testClearInventory(TestContext ctx) {
        TestHelper helper = ctx.helper();
        helper.giveItem(Items.STICK, 5);
        helper.giveItem(Items.STONE, 3);
        helper.assertFalse(helper.player().getInventory().isEmpty(), "Inventory should not be empty before clearing");
        helper.clearInventory();
        helper.assertTrue(helper.player().getInventory().isEmpty(), "Inventory should be empty after clearing");
        return TestResult.pass();
    }

    private static TestResult testSetPlayerPosVec3(TestContext ctx) {
        TestHelper helper = ctx.helper();
        Vec3 target = new Vec3(10, 64, -5);
        helper.setPlayerPos(target);
        helper.assertTrue(helper.playerAt(target, 0.1), "Player should be at the set position");
        return TestResult.pass();
    }

    private static TestResult testSetPlayerPosBlock(TestContext ctx) {
        TestHelper helper = ctx.helper();
        BlockPos target = new BlockPos(20, 70, 15);
        helper.setPlayerPos(target);
        helper.assertTrue(helper.playerAt(Vec3.atCenterOf(target), 0.1), "Player should be at the center of the set block position");
        return TestResult.pass();
    }

    private static TestResult testSetPlayerLook(TestContext ctx) {
        TestHelper helper = ctx.helper();
        helper.setPlayerLook(90.0f, 0.0f);
        helper.assertEquals(90.0f, helper.player().getYRot(), "Player yaw should be 90");
        helper.assertEquals(0.0f, helper.player().getXRot(), "Player pitch should be 0");
        return TestResult.pass();
    }

    private static TestResult testHasEffect(TestContext ctx) {
        TestHelper helper = ctx.helper();
        helper.clearEffects();
        helper.giveEffect(MobEffects.HASTE, 500, 0);
        helper.assertTrue(helper.hasEffect(helper.player(), MobEffects.HASTE, 500), "Should have dig speed with at least 500 ticks");
        helper.assertFalse(helper.hasEffect(helper.player(), MobEffects.SPEED, 1), "Should not have movement speed");
        helper.clearEffects();
        return TestResult.pass();
    }

    private static TestResult testPlayerAt(TestContext ctx) {
        TestHelper helper = ctx.helper();
        Vec3 pos = helper.player().position();
        helper.assertTrue(helper.playerAt(pos, 0.0), "Player should be at current position");
        helper.assertTrue(helper.playerAt(pos, 1.0), "Player should be at current position within tolerance");
        helper.assertFalse(helper.playerAt(pos.add(10, 0, 0), 0.0), "Player should not be 10 blocks away");
        return TestResult.pass();
    }

    private static TestResult testHasEntityAt(TestContext ctx) {
        TestHelper helper = ctx.helper();
        Vec3 playerPos = helper.player().position();
        BlockPos blockPos = BlockPos.containing(playerPos);
        Pig pig = helper.spawnLiving(EntityTypes.PIG, new Vec3(0, 3, 0));
        helper.assertNotNull(pig, "Spawned pig should not be null");
        TickManager.flush(ctx.server());
        helper.assertTrue(helper.hasEntityAt(Pig.class, blockPos, 10.0), "Should detect pig near player position");
        pig.discard();
        return TestResult.pass();
    }

    // === Async tests ===

    private static void testWaitUntil(TestContext ctx, Consumer<TestResult> callback) {
        TestHelper helper = ctx.helper();
        boolean[] ready = {false};
        helper.waitUntil(() -> ready[0], () -> ready[0] = true);
        TickManager.flush(ctx.server());
        helper.assertTrue(ready[0], "waitUntil condition should become true after flush");
        callback.accept(TestResult.pass());
    }

    private static void testWaitUntilTimeout(TestContext ctx, Consumer<TestResult> callback) {
        TestHelper helper = ctx.helper();
        boolean[] timedOut = {false};
        helper.waitUntil(
            () -> false,
            2,
            () -> {},
            () -> timedOut[0] = true
        );
        TickManager.flush(ctx.server());
        TickManager.flush(ctx.server());
        helper.assertTrue(timedOut[0], "onTimeout should be called when condition never becomes true within maxTicks");
        callback.accept(TestResult.pass());
    }
}