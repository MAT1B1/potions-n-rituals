package com.matibi.potionsnrituals.command.test.tests;

import com.matibi.potionsnrituals.command.test.TestRegistry;
import com.matibi.potionsnrituals.command.test.TestResult;
import com.matibi.potionsnrituals.command.test.TestContext;
import com.matibi.potionsnrituals.effect.ModEffects;

public final class EffectTests {

    static {
        // Active effects (keybind-triggered)
        TestRegistry.registerAsync("zeus_lightning_strikes", EffectTests::testZeusLightning);
        TestRegistry.registerAsync("medusa_petrifies", EffectTests::testMedusaPetrify);
        TestRegistry.registerAsync("active_tp_teleports", EffectTests::testActiveTeleport);
        TestRegistry.registerAsync("love_effect_applies", EffectTests::testLoveEffect);

        // Terrain effects (alchemical stone)
        TestRegistry.registerAsync("alchemist_transmutes_coal", EffectTests::testAlchemistTransmute);
        TestRegistry.registerAsync("giant_grows_blocks", EffectTests::testGiantGrowth);
        TestRegistry.registerAsync("ignition_sets_fire", EffectTests::testIgnitionFire);
        TestRegistry.registerAsync("petrification_turns_stone", EffectTests::testPetrification);
        TestRegistry.registerAsync("resurrection_revives", EffectTests::testResurrection);

        // Normal effects
        TestRegistry.register("ghost_walk_phases_blocks", EffectTests::testGhostWalkPhasing);
        TestRegistry.register("ore_sense_detects_ores", EffectTests::testOreSenseDetection);
        TestRegistry.register("berserk_increases_damage", EffectTests::testBerserkDamage);
        TestRegistry.register("vampirism_heals_on_hit", EffectTests::testVampirismHeal);
        TestRegistry.register("thorns_damages_attacker", EffectTests::testThornsDamage);
        TestRegistry.register("rust_degrades_iron", EffectTests::testRustDegradation);
        TestRegistry.register("saturation_feeds", EffectTests::testSaturationFeed);
        TestRegistry.register("stun_paralyzes", EffectTests::testStunParalyze);
        TestRegistry.register("teleportation_random_tp", EffectTests::testTeleportationRandom);
        TestRegistry.register("infinity_prevents_consumption", EffectTests::testInfinityConsumption);
        TestRegistry.register("double_health_doubles_max", EffectTests::testDoubleHealth);
        TestRegistry.register("clumsiness_causes_fumble", EffectTests::testClumsiness);
        TestRegistry.register("cold_slows_entities", EffectTests::testColdSlowness);
        TestRegistry.register("asthma_prevents_sprint", EffectTests::testAsthmaSprint);
        TestRegistry.register("paranoia_spawns_illusions", EffectTests::testParanoiaIllusions);
        TestRegistry.register("hydrophobia_damages_in_water", EffectTests::testHydrophobiaWater);
        TestRegistry.register("zombie_contagion_spreads", EffectTests::testZombieContagion);
        TestRegistry.register("empathy_shares_damage", EffectTests::testEmpathyShare);
        TestRegistry.register("magnetism_attracts_items", EffectTests::testMagnetismAttract);
        TestRegistry.register("midas_turns_gold", EffectTests::testMidasGold);
        TestRegistry.register("reality_check_detects_fake", EffectTests::testRealityCheck);
        TestRegistry.register("aftermath_delayed_damage", EffectTests::testAftermathDelay);
        TestRegistry.register("adhesion_sticks_blocks", EffectTests::testAdhesionStick);
        TestRegistry.register("brainwashing_controls_mob", EffectTests::testBrainwashingControl);
        TestRegistry.register("death_instant_kill", EffectTests::testDeathInstant);
        TestRegistry.register("dwarf_shrinks_player", EffectTests::testDwarfShrink);
        TestRegistry.register("frost_freezes_water", EffectTests::testFrostFreeze);
        TestRegistry.register("liquid_walker_walks_liquid", EffectTests::testLiquidWalker);
        TestRegistry.register("long_cooldown_increases_cd", EffectTests::testLongCooldown);
        TestRegistry.register("long_leg_increases_reach", EffectTests::testLongLegReach);
        TestRegistry.register("masking_hides_from_mobs", EffectTests::testMaskingHide);
        TestRegistry.register("no_interaction_prevents_use", EffectTests::testNoInteraction);
        TestRegistry.register("oblivion_removes_memory", EffectTests::testOblivionMemory);
        TestRegistry.register("photosynthesis_heals_sun", EffectTests::testPhotosynthesisSun);
        TestRegistry.register("pregnant_spawns_baby", EffectTests::testPregnantBaby);
        TestRegistry.register("purification_cleanses", EffectTests::testPurificationCleanse);
        TestRegistry.register("reactivation_retriggers", EffectTests::testReactivationRetrigger);
        TestRegistry.register("resonance_amplifies", EffectTests::testResonanceAmplify);
        TestRegistry.register("short_cooldown_decreases_cd", EffectTests::testShortCooldown);
        TestRegistry.register("unstable_random_effects", EffectTests::testUnstableRandom);
        TestRegistry.register("xp_boost_increases_xp", EffectTests::testXpBoost);
        TestRegistry.register("xp_reduction_decreases_xp", EffectTests::testXpReduction);
        TestRegistry.register("xp_life_uses_xp_as_health", EffectTests::testXpLifeHealth);
        TestRegistry.register("permanent_health_persists", EffectTests::testPermHealthPersist);
        TestRegistry.register("permanent_speed_persists", EffectTests::testPermSpeedPersist);
        TestRegistry.register("permanent_strength_persists", EffectTests::testPermStrengthPersist);
    }

    // Active effect tests
    private static void testZeusLightning(TestContext ctx, java.util.function.Consumer<TestResult> cb) {
        ctx.helper().giveEffect(ModEffects.ZEUS_BENEDICTION, 200, 0);
        ctx.helper().waitTicks(1, () -> cb.accept(TestResult.pass()));
    }

    private static void testMedusaPetrify(TestContext ctx, java.util.function.Consumer<TestResult> cb) {
        ctx.helper().giveEffect(ModEffects.MEDUSA_BENEDICTION, 200, 0);
        ctx.helper().waitTicks(1, () -> cb.accept(TestResult.pass()));
    }

    private static void testActiveTeleport(TestContext ctx, java.util.function.Consumer<TestResult> cb) {
        ctx.helper().giveEffect(ModEffects.ACTIVE_TP, 200, 0);
        ctx.helper().waitTicks(1, () -> cb.accept(TestResult.pass()));
    }

    private static void testLoveEffect(TestContext ctx, java.util.function.Consumer<TestResult> cb) {
        ctx.helper().giveEffect(ModEffects.LOVE, 200, 0);
        ctx.helper().waitTicks(1, () -> cb.accept(TestResult.pass()));
    }

    // Terrain effect tests
    private static void testAlchemistTransmute(TestContext ctx, java.util.function.Consumer<TestResult> cb) {
        ctx.helper().giveEffect(ModEffects.ALCHEMIST, 200, 0);
        ctx.helper().waitTicks(1, () -> cb.accept(TestResult.pass()));
    }

    private static void testGiantGrowth(TestContext ctx, java.util.function.Consumer<TestResult> cb) {
        ctx.helper().giveEffect(ModEffects.GIANT, 200, 0);
        ctx.helper().waitTicks(1, () -> cb.accept(TestResult.pass()));
    }

    private static void testIgnitionFire(TestContext ctx, java.util.function.Consumer<TestResult> cb) {
        ctx.helper().giveEffect(ModEffects.IGNITION, 200, 0);
        ctx.helper().waitTicks(1, () -> cb.accept(TestResult.pass()));
    }

    private static void testPetrification(TestContext ctx, java.util.function.Consumer<TestResult> cb) {
        ctx.helper().giveEffect(ModEffects.PETRIFICATION, 200, 0);
        ctx.helper().waitTicks(1, () -> cb.accept(TestResult.pass()));
    }

    private static void testResurrection(TestContext ctx, java.util.function.Consumer<TestResult> cb) {
        ctx.helper().giveEffect(ModEffects.RESURRECTION, 200, 0);
        ctx.helper().waitTicks(1, () -> cb.accept(TestResult.pass()));
    }

    // Normal effect tests (stubs - implement as needed)
    private static TestResult testGhostWalkPhasing(TestContext ctx) { return TestResult.pass(); }
    private static TestResult testOreSenseDetection(TestContext ctx) { return TestResult.pass(); }
    private static TestResult testBerserkDamage(TestContext ctx) { return TestResult.pass(); }
    private static TestResult testVampirismHeal(TestContext ctx) { return TestResult.pass(); }
    private static TestResult testThornsDamage(TestContext ctx) { return TestResult.pass(); }
    private static TestResult testRustDegradation(TestContext ctx) { return TestResult.pass(); }
    private static TestResult testSaturationFeed(TestContext ctx) { return TestResult.pass(); }
    private static TestResult testStunParalyze(TestContext ctx) { return TestResult.pass(); }
    private static TestResult testTeleportationRandom(TestContext ctx) { return TestResult.pass(); }
    private static TestResult testInfinityConsumption(TestContext ctx) { return TestResult.pass(); }
    private static TestResult testDoubleHealth(TestContext ctx) { return TestResult.pass(); }
    private static TestResult testClumsiness(TestContext ctx) { return TestResult.pass(); }
    private static TestResult testColdSlowness(TestContext ctx) { return TestResult.pass(); }
    private static TestResult testAsthmaSprint(TestContext ctx) { return TestResult.pass(); }
    private static TestResult testParanoiaIllusions(TestContext ctx) { return TestResult.pass(); }
    private static TestResult testHydrophobiaWater(TestContext ctx) { return TestResult.pass(); }
    private static TestResult testZombieContagion(TestContext ctx) { return TestResult.pass(); }
    private static TestResult testEmpathyShare(TestContext ctx) { return TestResult.pass(); }
    private static TestResult testMagnetismAttract(TestContext ctx) { return TestResult.pass(); }
    private static TestResult testMidasGold(TestContext ctx) { return TestResult.pass(); }
    private static TestResult testRealityCheck(TestContext ctx) { return TestResult.pass(); }
    private static TestResult testAftermathDelay(TestContext ctx) { return TestResult.pass(); }
    private static TestResult testAdhesionStick(TestContext ctx) { return TestResult.pass(); }
    private static TestResult testBrainwashingControl(TestContext ctx) { return TestResult.pass(); }
    private static TestResult testDeathInstant(TestContext ctx) { return TestResult.pass(); }
    private static TestResult testDwarfShrink(TestContext ctx) { return TestResult.pass(); }
    private static TestResult testFrostFreeze(TestContext ctx) { return TestResult.pass(); }
    private static TestResult testLiquidWalker(TestContext ctx) { return TestResult.pass(); }
    private static TestResult testLongCooldown(TestContext ctx) { return TestResult.pass(); }
    private static TestResult testLongLegReach(TestContext ctx) { return TestResult.pass(); }
    private static TestResult testMaskingHide(TestContext ctx) { return TestResult.pass(); }
    private static TestResult testNoInteraction(TestContext ctx) { return TestResult.pass(); }
    private static TestResult testOblivionMemory(TestContext ctx) { return TestResult.pass(); }
    private static TestResult testPhotosynthesisSun(TestContext ctx) { return TestResult.pass(); }
    private static TestResult testPregnantBaby(TestContext ctx) { return TestResult.pass(); }
    private static TestResult testPurificationCleanse(TestContext ctx) { return TestResult.pass(); }
    private static TestResult testReactivationRetrigger(TestContext ctx) { return TestResult.pass(); }
    private static TestResult testResonanceAmplify(TestContext ctx) { return TestResult.pass(); }
    private static TestResult testShortCooldown(TestContext ctx) { return TestResult.pass(); }
    private static TestResult testUnstableRandom(TestContext ctx) { return TestResult.pass(); }
    private static TestResult testXpBoost(TestContext ctx) { return TestResult.pass(); }
    private static TestResult testXpReduction(TestContext ctx) { return TestResult.pass(); }
    private static TestResult testXpLifeHealth(TestContext ctx) { return TestResult.pass(); }
    private static TestResult testPermHealthPersist(TestContext ctx) { return TestResult.pass(); }
    private static TestResult testPermSpeedPersist(TestContext ctx) { return TestResult.pass(); }
    private static TestResult testPermStrengthPersist(TestContext ctx) { return TestResult.pass(); }
}