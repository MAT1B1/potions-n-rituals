package com.matibi.potionsnrituals.command.test.tests;

import com.matibi.potionsnrituals.command.test.TestRegistry;
import com.matibi.potionsnrituals.command.test.TestResult;
import com.matibi.potionsnrituals.command.test.TestContext;
import com.matibi.potionsnrituals.item.ModItems;

public final class ItemTests {

    static {
        //TestRegistry.registerAsync("syringe_extracts_blood", ItemTests::testSyringeExtract);
        //TestRegistry.registerAsync("talisman_charges_souls", ItemTests::testTalismanCharge);
        //TestRegistry.registerAsync("alchemical_stone_applies_effects", ItemTests::testAlchemicalStoneApply);
        //TestRegistry.registerAsync("spirit_mirror_teleports", ItemTests::testSpiritMirrorTeleport);
        //TestRegistry.registerAsync("nether_seal_breaker_breaks", ItemTests::testNetherSealBreaker);
        //TestRegistry.registerAsync("decoy_distracts_mobs", ItemTests::testDecoyDistract);
        //TestRegistry.registerAsync("gauntlet_empowers", ItemTests::testGauntletEmpower);
        //TestRegistry.registerAsync("invisibility_ring_hides", ItemTests::testInvisibilityRing);
        //TestRegistry.registerAsync("invisibility_cloak_hides", ItemTests::testInvisibilityCloak);
        //TestRegistry.registerAsync("phoenix_quill_revives", ItemTests::testPhoenixQuillRevive);
        //TestRegistry.registerAsync("lock_locks_chest", ItemTests::testLockChest);
        //TestRegistry.registerAsync("key_unlocks", ItemTests::testKeyUnlock);
        //TestRegistry.registerAsync("capture_sphere_captures", ItemTests::testCaptureSphere);
        //TestRegistry.registerAsync("alchemical_bag_stores", ItemTests::testAlchemicalBagStore);
        //TestRegistry.registerAsync("oxidation_fragment_oxidizes", ItemTests::testOxidationFragment);
    }

    private static void testSyringeExtract(TestContext ctx, java.util.function.Consumer<TestResult> cb) {
        ctx.helper().giveItem(ModItems.SYRINGE);
        ctx.helper().waitTicks(1, () -> cb.accept(TestResult.pass()));
    }

    private static void testTalismanCharge(TestContext ctx, java.util.function.Consumer<TestResult> cb) {
        ctx.helper().giveItem(ModItems.TALISMAN);
        ctx.helper().waitTicks(1, () -> cb.accept(TestResult.pass()));
    }

    private static void testAlchemicalStoneApply(TestContext ctx, java.util.function.Consumer<TestResult> cb) {
        ctx.helper().giveItem(ModItems.ALCHEMICAL_STONE);
        ctx.helper().waitTicks(1, () -> cb.accept(TestResult.pass()));
    }

    private static void testSpiritMirrorTeleport(TestContext ctx, java.util.function.Consumer<TestResult> cb) {
        ctx.helper().giveItem(ModItems.SPIRIT_MIRROR);
        ctx.helper().waitTicks(1, () -> cb.accept(TestResult.pass()));
    }

    private static void testNetherSealBreaker(TestContext ctx, java.util.function.Consumer<TestResult> cb) {
        ctx.helper().giveItem(ModItems.NETHER_SEAL_BREAKER);
        ctx.helper().waitTicks(1, () -> cb.accept(TestResult.pass()));
    }

    private static void testDecoyDistract(TestContext ctx, java.util.function.Consumer<TestResult> cb) {
        ctx.helper().giveItem(ModItems.DECOY);
        ctx.helper().waitTicks(1, () -> cb.accept(TestResult.pass()));
    }

    private static void testGauntletEmpower(TestContext ctx, java.util.function.Consumer<TestResult> cb) {
        ctx.helper().giveItem(ModItems.GAUNTLET);
        ctx.helper().waitTicks(1, () -> cb.accept(TestResult.pass()));
    }

    private static void testInvisibilityRing(TestContext ctx, java.util.function.Consumer<TestResult> cb) {
        ctx.helper().giveItem(ModItems.RING);
        ctx.helper().waitTicks(1, () -> cb.accept(TestResult.pass()));
    }

    private static void testInvisibilityCloak(TestContext ctx, java.util.function.Consumer<TestResult> cb) {
        ctx.helper().giveItem(ModItems.CLOAK);
        ctx.helper().waitTicks(1, () -> cb.accept(TestResult.pass()));
    }

    private static void testPhoenixQuillRevive(TestContext ctx, java.util.function.Consumer<TestResult> cb) {
        ctx.helper().giveItem(ModItems.PHOENIX_QUILL);
        ctx.helper().waitTicks(1, () -> cb.accept(TestResult.pass()));
    }

    private static void testLockChest(TestContext ctx, java.util.function.Consumer<TestResult> cb) {
        ctx.helper().giveItem(ModItems.LOCK);
        ctx.helper().waitTicks(1, () -> cb.accept(TestResult.pass()));
    }

    private static void testKeyUnlock(TestContext ctx, java.util.function.Consumer<TestResult> cb) {
        ctx.helper().giveItem(ModItems.KEY);
        ctx.helper().waitTicks(1, () -> cb.accept(TestResult.pass()));
    }

    private static void testCaptureSphere(TestContext ctx, java.util.function.Consumer<TestResult> cb) {
        ctx.helper().giveItem(ModItems.CAPTURE_SPHERE);
        ctx.helper().waitTicks(1, () -> cb.accept(TestResult.pass()));
    }

    private static void testAlchemicalBagStore(TestContext ctx, java.util.function.Consumer<TestResult> cb) {
        ctx.helper().giveItem(ModItems.ALCHEMICAL_BAG);
        ctx.helper().waitTicks(1, () -> cb.accept(TestResult.pass()));
    }

    private static void testOxidationFragment(TestContext ctx, java.util.function.Consumer<TestResult> cb) {
        ctx.helper().giveItem(ModItems.OXYDATION);
        ctx.helper().waitTicks(1, () -> cb.accept(TestResult.pass()));
    }
}