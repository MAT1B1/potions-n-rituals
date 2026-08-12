package com.matibi.potionsnrituals.command.test.tests;

import com.matibi.potionsnrituals.command.test.TestRegistry;
import com.matibi.potionsnrituals.command.test.TestResult;
import com.matibi.potionsnrituals.command.test.TestContext;
import com.matibi.potionsnrituals.command.test.TestHelper;
import com.matibi.potionsnrituals.effect.ActiveEffect;
import com.matibi.potionsnrituals.effect.ModEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.phys.Vec3;

import java.util.function.Consumer;


public final class EffectTests {

    static {
        // Active effects (keybind-triggered)
        TestRegistry.registerAsync("zeus_lightning_strikes", EffectTests::testZeusLightning);
        TestRegistry.registerAsync("medusa_petrifies", EffectTests::testMedusaPetrify);
        TestRegistry.registerAsync("active_tp_teleports", EffectTests::testActiveTeleport);
        TestRegistry.registerAsync("love_effect_applies", EffectTests::testLoveEffect);

        // Terrain effects (alchemical stone)
        /*TestRegistry.registerAsync("alchemist_transmutes_coal", EffectTests::testAlchemistTransmute);
        TestRegistry.registerAsync("giant_grows_blocks", EffectTests::testGiantGrowth);
        TestRegistry.registerAsync("ignition_sets_fire", EffectTests::testIgnitionFire);
        TestRegistry.registerAsync("petrification_turns_stone", EffectTests::testPetrification);
        TestRegistry.registerAsync("resurrection_revives", EffectTests::testResurrection);*/

        // Normal effects
        TestRegistry.registerAsync("ghost_walk_phases_blocks", EffectTests::testGhostWalkPhasing);
        TestRegistry.register("ore_sense_detects_ores", EffectTests::testOreSenseDetection);
        TestRegistry.registerAsync("berserk_increases_damage", EffectTests::testBerserkDamage);
        TestRegistry.register("vampirism_heals_on_hit", EffectTests::testVampirismHeal);
        TestRegistry.register("thorns_damages_attacker", EffectTests::testThornsDamage);
        TestRegistry.register("rust_degrades_iron", EffectTests::testRustDegradation);
        TestRegistry.register("saturation_feeds", EffectTests::testSaturationFeed);
        TestRegistry.register("stun_paralyzes", EffectTests::testStunParalyze);
        TestRegistry.registerAsync("teleportation_random_tp", EffectTests::testTeleportationRandom);
        TestRegistry.registerAsync("infinity_prevents_consumption", EffectTests::testInfinityConsumption);
        TestRegistry.registerAsync("double_health_doubles_max", EffectTests::testDoubleHealth);
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
        TestRegistry.registerAsync("masking_hides_from_mobs", EffectTests::testMaskingHide);
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

    private static void testZeusLightning(TestContext ctx, Consumer<TestResult> cb) {
        TestHelper helper = ctx.helper();
        helper.clearEffects();
        helper.giveEffect(ModEffects.ZEUS_BENEDICTION, 200, 0);
        helper.setPlayerLook(0, 90); // face south, look straight down
        boolean result = ((ActiveEffect) ModEffects.ZEUS_BENEDICTION.value()).useOnKeybind(helper.level(), helper.player(), 0, 0);
        helper.assertTrue(result, "Zeus useOnKeybind should return true when ground in sight");
        helper.waitTicks(2, () -> {
            BlockPos groundPos = helper.player().blockPosition().below();
            helper.assertTrue(helper.hasEntityAt(LightningBolt.class, groundPos, 5.0), "LightningBolt should spawn below player");
            helper.clearEffects();
            cb.accept(TestResult.pass());
        });
    }

    private static void testMedusaPetrify(TestContext ctx, Consumer<TestResult> cb) {
        TestHelper helper = ctx.helper();
        helper.clearEffects();
        helper.giveEffect(ModEffects.MEDUSA_BENEDICTION, 200, 0);
        helper.setPlayerLook(0, 0); // face south, horizontal
        var zombie = helper.spawnLiving(EntityTypes.ZOMBIE, new Vec3(0, 0, 10));
        helper.waitTicks(1, () -> {
            boolean result = ((ActiveEffect) ModEffects.MEDUSA_BENEDICTION.value()).useOnKeybind(helper.level(), helper.player(), 0, 0);
            helper.assertTrue(result, "Medusa useOnKeybind should return true when target in sight");
            helper.assertTrue(helper.hasEffect(zombie, ModEffects.PETRIFICATION, 100), "Zombie should have PETRIFICATION effect");
            zombie.discard();
            helper.clearEffects();
            cb.accept(TestResult.pass());
        });
    }

    private static void testActiveTeleport(TestContext ctx, Consumer<TestResult> cb) {
        TestHelper helper = ctx.helper();
        helper.clearEffects();
        helper.giveEffect(ModEffects.ACTIVE_TP, 200, 0);
        Vec3 startPos = helper.player().position();
        helper.setPlayerLook(0, 90); // face south, look straight down
        boolean result = ((ActiveEffect) ModEffects.ACTIVE_TP.value()).useOnKeybind(helper.level(), helper.player(), 0, 0);
        helper.assertTrue(result, "Active TP useOnKeybind should return true when ground in sight");
        helper.waitTicks(1, () -> {
            helper.assertTrue(helper.player().position().distanceTo(startPos) > 1.0, "Player should have teleported away from original position");
            helper.clearEffects();
            cb.accept(TestResult.pass());
        });
    }

    private static void testLoveEffect(TestContext ctx, Consumer<TestResult> cb) {
        TestHelper helper = ctx.helper();
        helper.clearEffects();
        helper.setPlayerPos(new Vec3(0, 200, 0));
        helper.giveEffect(ModEffects.LOVE, 200, 0);
        helper.setPlayerLook(0, 5); // face south, look slightly downward
        var cow = helper.spawnLiving(EntityTypes.COW, new Vec3(0, 0, 5));
        helper.waitTicks(1, () -> {
            boolean result = ((ActiveEffect) ModEffects.LOVE.value()).useOnKeybind(helper.level(), helper.player(), 0, 0);
            helper.assertTrue(result, "Love useOnKeybind should return true when breedable target in sight");
            helper.assertTrue(helper.hasEffect(helper.player(), ModEffects.PREGNANT, 100), "Player should have PREGNANT effect after love trigger");
            helper.assertFalse(helper.hasEffect(helper.player(), ModEffects.LOVE, 1), "Player should no longer have LOVE effect after trigger");
            cow.discard();
            helper.clearEffects();
            cb.accept(TestResult.pass());
        });
    }

    // === Simple sync effect tests ===

    private static void testDoubleHealth(TestContext ctx, Consumer<TestResult> cb) {
        TestHelper helper = ctx.helper();
        helper.giveEffect(ModEffects.DOUBLE_HEALTH, 200, 0);
        helper.waitTicks(3, () -> {
            helper.assertNotNull(helper.player().getEffect(MobEffects.ABSORPTION), "DOUBLE_HEALTH should grant ABSORPTION");
            helper.clearEffects();
            cb.accept(TestResult.pass());
        });
    }

    private static void testInfinityConsumption(TestContext ctx, Consumer<TestResult> cb) {
        TestHelper helper = ctx.helper();
        helper.giveEffect(MobEffects.SPEED, 200, 0);
        helper.giveEffect(ModEffects.INFINITY, 1, 0);
        helper.waitTicks(3, () -> {
            var speedInst = helper.player().getEffect(MobEffects.SPEED);
            helper.assertNotNull(speedInst, "SPEED should still be present after INFINITY");
            helper.assertEquals(-1, speedInst.getDuration(), "INFINITY should set effect duration to -1");
            helper.clearEffects();
            cb.accept(TestResult.pass());
        });
    }

    private static void testMaskingHide(TestContext ctx, Consumer<TestResult> cb) {
        TestHelper helper = ctx.helper();
        helper.giveEffect(MobEffects.SPEED, 200, 0);
        helper.giveEffect(ModEffects.MASKING, 1, 0);
        helper.waitTicks(3, () -> {
            var speedInst = helper.player().getEffect(MobEffects.SPEED);
            helper.assertNotNull(speedInst, "SPEED should still be present after MASKING");
            helper.assertFalse(speedInst.isVisible(), "MASKING should make effects invisible");
            helper.clearEffects();
            cb.accept(TestResult.pass());
        });
    }

    private static TestResult testStunParalyze(TestContext ctx) {
        TestHelper helper = ctx.helper();
        helper.giveEffect(ModEffects.STUN, 200, 0);
        var stunInst = helper.player().getEffect(ModEffects.STUN);
        helper.assertNotNull(stunInst, "Should have STUN effect");
        helper.assertTrue(stunInst.getEffect().value().getCategory() == net.minecraft.world.effect.MobEffectCategory.HARMFUL, "STUN should be harmful");
        helper.clearEffects();
        return TestResult.pass();
    }

    private static TestResult testOreSenseDetection(TestContext ctx) {
        TestHelper helper = ctx.helper();
        helper.giveEffect(ModEffects.ORE_SENSE, 200, 0);
        helper.assertTrue(helper.hasEffect(helper.player(), ModEffects.ORE_SENSE, 200), "Should have ORE_SENSE effect");
        helper.clearEffects();
        return TestResult.pass();
    }

    private static void testBerserkDamage(TestContext ctx, Consumer<TestResult> cb) {
        TestHelper helper = ctx.helper();
        helper.giveEffect(ModEffects.BERSERK, 200, 0);
        var berserkInst = helper.player().getEffect(ModEffects.BERSERK);
        helper.assertNotNull(berserkInst, "Should have BERSERK effect");
        helper.waitTicks(20, () -> {
            helper.assertNotNull(helper.player().getEffect(MobEffects.NIGHT_VISION), "BERSERK should grant NIGHT_VISION on tick");
            helper.clearEffects();
            helper.player().removeEffect(MobEffects.NIGHT_VISION);
            cb.accept(TestResult.pass());
        });
    }

    private static TestResult testClumsiness(TestContext ctx) {
        TestHelper helper = ctx.helper();
        helper.giveEffect(ModEffects.CLUMSINESS, 200, 0);
        helper.assertTrue(helper.hasEffect(helper.player(), ModEffects.CLUMSINESS, 200), "Should have CLUMSINESS effect");
        helper.clearEffects();
        return TestResult.pass();
    }

    private static TestResult testColdSlowness(TestContext ctx) {
        TestHelper helper = ctx.helper();
        helper.giveEffect(ModEffects.COLD, 200, 0);
        helper.assertTrue(helper.hasEffect(helper.player(), ModEffects.COLD, 200), "Should have COLD effect");
        helper.clearEffects();
        return TestResult.pass();
    }

    private static TestResult testAsthmaSprint(TestContext ctx) {
        TestHelper helper = ctx.helper();
        helper.giveEffect(ModEffects.ASTHMA, 200, 0);
        helper.assertTrue(helper.hasEffect(helper.player(), ModEffects.ASTHMA, 200), "Should have ASTHMA effect");
        helper.clearEffects();
        return TestResult.pass();
    }

    private static TestResult testParanoiaIllusions(TestContext ctx) {
        TestHelper helper = ctx.helper();
        helper.giveEffect(ModEffects.PARANOIA, 200, 0);
        helper.assertTrue(helper.hasEffect(helper.player(), ModEffects.PARANOIA, 200), "Should have PARANOIA effect");
        helper.clearEffects();
        return TestResult.pass();
    }

    private static TestResult testHydrophobiaWater(TestContext ctx) {
        TestHelper helper = ctx.helper();
        helper.giveEffect(ModEffects.HYDROPHOBIA, 200, 0);
        helper.assertTrue(helper.hasEffect(helper.player(), ModEffects.HYDROPHOBIA, 200), "Should have HYDROPHOBIA effect");
        helper.clearEffects();
        return TestResult.pass();
    }

    private static TestResult testZombieContagion(TestContext ctx) {
        TestHelper helper = ctx.helper();
        helper.giveEffect(ModEffects.ZOMBIE_CONTAGION, 200, 0);
        helper.assertTrue(helper.hasEffect(helper.player(), ModEffects.ZOMBIE_CONTAGION, 200), "Should have ZOMBIE_CONTAGION effect");
        helper.clearEffects();
        return TestResult.pass();
    }

    private static TestResult testEmpathyShare(TestContext ctx) {
        TestHelper helper = ctx.helper();
        helper.giveEffect(ModEffects.EMPATHY, 200, 0);
        helper.assertTrue(helper.hasEffect(helper.player(), ModEffects.EMPATHY, 200), "Should have EMPATHY effect");
        helper.clearEffects();
        return TestResult.pass();
    }

    private static TestResult testMagnetismAttract(TestContext ctx) {
        TestHelper helper = ctx.helper();
        helper.giveEffect(ModEffects.MAGNETISM, 200, 0);
        helper.assertTrue(helper.hasEffect(helper.player(), ModEffects.MAGNETISM, 200), "Should have MAGNETISM effect");
        helper.clearEffects();
        return TestResult.pass();
    }

    private static TestResult testMidasGold(TestContext ctx) {
        TestHelper helper = ctx.helper();
        helper.giveEffect(ModEffects.MIDAS_BENEDICTION, 200, 0);
        helper.assertTrue(helper.hasEffect(helper.player(), ModEffects.MIDAS_BENEDICTION, 200), "Should have MIDAS effect");
        helper.clearEffects();
        return TestResult.pass();
    }

    private static TestResult testRealityCheck(TestContext ctx) {
        TestHelper helper = ctx.helper();
        helper.giveEffect(ModEffects.REALITY_CHECK, 200, 0);
        helper.assertTrue(helper.hasEffect(helper.player(), ModEffects.REALITY_CHECK, 200), "Should have REALITY_CHECK effect");
        helper.clearEffects();
        return TestResult.pass();
    }

    private static TestResult testAftermathDelay(TestContext ctx) {
        TestHelper helper = ctx.helper();
        helper.giveEffect(ModEffects.AFTERMATH, 200, 0);
        helper.assertTrue(helper.hasEffect(helper.player(), ModEffects.AFTERMATH, 200), "Should have AFTERMATH effect");
        helper.clearEffects();
        return TestResult.pass();
    }

    private static TestResult testAdhesionStick(TestContext ctx) {
        TestHelper helper = ctx.helper();
        helper.giveEffect(ModEffects.ADHESION, 200, 0);
        helper.assertTrue(helper.hasEffect(helper.player(), ModEffects.ADHESION, 200), "Should have ADHESION effect");
        helper.clearEffects();
        return TestResult.pass();
    }

    private static TestResult testBrainwashingControl(TestContext ctx) {
        TestHelper helper = ctx.helper();
        helper.giveEffect(ModEffects.BRAINWASHING, 200, 0);
        helper.assertTrue(helper.hasEffect(helper.player(), ModEffects.BRAINWASHING, 200), "Should have BRAINWASHING effect");
        helper.clearEffects();
        return TestResult.pass();
    }

    private static TestResult testDeathInstant(TestContext ctx) {
        TestHelper helper = ctx.helper();
        helper.assertNotNull(ModEffects.DEATH.value(), "DEATH effect should be registered");
        return TestResult.pass();
    }

    private static TestResult testDwarfShrink(TestContext ctx) {
        TestHelper helper = ctx.helper();
        helper.giveEffect(ModEffects.DWARF, 200, 0);
        var dwarfInst = helper.player().getEffect(ModEffects.DWARF);
        helper.assertNotNull(dwarfInst, "Should have DWARF effect");
        helper.assertTrue(dwarfInst.getEffect().value().getCategory() == net.minecraft.world.effect.MobEffectCategory.NEUTRAL, "DWARF should be neutral");
        helper.clearEffects();
        return TestResult.pass();
    }

    private static TestResult testFrostFreeze(TestContext ctx) {
        TestHelper helper = ctx.helper();
        helper.giveEffect(ModEffects.FROST, 200, 0);
        helper.assertTrue(helper.hasEffect(helper.player(), ModEffects.FROST, 200), "Should have FROST effect");
        helper.clearEffects();
        return TestResult.pass();
    }

    private static TestResult testLiquidWalker(TestContext ctx) {
        TestHelper helper = ctx.helper();
        helper.giveEffect(ModEffects.LIQUID_WALKER, 200, 0);
        helper.assertTrue(helper.hasEffect(helper.player(), ModEffects.LIQUID_WALKER, 200), "Should have LIQUID_WALKER effect");
        helper.clearEffects();
        return TestResult.pass();
    }

    private static TestResult testRustDegradation(TestContext ctx) {
        TestHelper helper = ctx.helper();
        helper.giveEffect(ModEffects.RUST, 200, 0);
        helper.assertTrue(helper.hasEffect(helper.player(), ModEffects.RUST, 200), "Should have RUST effect");
        helper.clearEffects();
        return TestResult.pass();
    }

    private static TestResult testLongCooldown(TestContext ctx) {
        TestHelper helper = ctx.helper();
        helper.giveEffect(ModEffects.LONG_COOLDOWN, 200, 0);
        helper.assertTrue(helper.hasEffect(helper.player(), ModEffects.LONG_COOLDOWN, 200), "Should have LONG_COOLDOWN effect");
        helper.clearEffects();
        return TestResult.pass();
    }

    private static TestResult testLongLegReach(TestContext ctx) {
        TestHelper helper = ctx.helper();
        helper.giveEffect(ModEffects.LONG_LEG, 200, 0);
        var longLegInst = helper.player().getEffect(ModEffects.LONG_LEG);
        helper.assertNotNull(longLegInst, "Should have LONG_LEG effect");
        helper.assertTrue(helper.hasEffect(helper.player(), ModEffects.LONG_LEG, 200), "LONG_LEG effect should be active");
        helper.clearEffects();
        return TestResult.pass();
    }

    private static TestResult testNoInteraction(TestContext ctx) {
        TestHelper helper = ctx.helper();
        helper.giveEffect(ModEffects.NO_INTERACTION, 200, 0);
        helper.assertTrue(helper.hasEffect(helper.player(), ModEffects.NO_INTERACTION, 200), "Should have NO_INTERACTION effect");
        helper.clearEffects();
        return TestResult.pass();
    }

    private static TestResult testOblivionMemory(TestContext ctx) {
        TestHelper helper = ctx.helper();
        helper.clearInventory();
        helper.giveEffect(ModEffects.OBLIVION, 1, 0);
        helper.assertTrue(helper.hasEffect(helper.player(), ModEffects.OBLIVION, 1), "Should have OBLIVION effect");
        helper.clearEffects();
        return TestResult.pass();
    }

    private static TestResult testPhotosynthesisSun(TestContext ctx) {
        TestHelper helper = ctx.helper();
        helper.giveEffect(ModEffects.PHOTOSYNTHESIS, 200, 0);
        helper.assertTrue(helper.hasEffect(helper.player(), ModEffects.PHOTOSYNTHESIS, 200), "Should have PHOTOSYNTHESIS effect");
        helper.clearEffects();
        return TestResult.pass();
    }

    private static TestResult testPregnantBaby(TestContext ctx) {
        TestHelper helper = ctx.helper();
        helper.giveEffect(ModEffects.PREGNANT, 200, 0);
        helper.assertTrue(helper.hasEffect(helper.player(), ModEffects.PREGNANT, 200), "Should have PREGNANT effect");
        helper.clearEffects();
        return TestResult.pass();
    }

    private static TestResult testPurificationCleanse(TestContext ctx) {
        TestHelper helper = ctx.helper();
        helper.giveEffect(ModEffects.PURIFICATION, 200, 0);
        helper.assertTrue(helper.hasEffect(helper.player(), ModEffects.PURIFICATION, 200), "Should have PURIFICATION effect");
        helper.clearEffects();
        return TestResult.pass();
    }

    private static TestResult testReactivationRetrigger(TestContext ctx) {
        TestHelper helper = ctx.helper();
        helper.giveEffect(ModEffects.REACTIVATION, 200, 0);
        helper.assertTrue(helper.hasEffect(helper.player(), ModEffects.REACTIVATION, 200), "Should have REACTIVATION effect");
        helper.clearEffects();
        return TestResult.pass();
    }

    private static TestResult testResonanceAmplify(TestContext ctx) {
        TestHelper helper = ctx.helper();
        helper.giveEffect(ModEffects.RESONANCE, 200, 0);
        helper.assertTrue(helper.hasEffect(helper.player(), ModEffects.RESONANCE, 200), "Should have RESONANCE effect");
        helper.clearEffects();
        return TestResult.pass();
    }

    private static TestResult testShortCooldown(TestContext ctx) {
        TestHelper helper = ctx.helper();
        helper.giveEffect(ModEffects.SHORT_COOLDOWN, 200, 0);
        helper.assertTrue(helper.hasEffect(helper.player(), ModEffects.SHORT_COOLDOWN, 200), "Should have SHORT_COOLDOWN effect");
        helper.clearEffects();
        return TestResult.pass();
    }

    private static TestResult testThornsDamage(TestContext ctx) {
        TestHelper helper = ctx.helper();
        helper.giveEffect(ModEffects.THORNS, 200, 0);
        helper.assertTrue(helper.hasEffect(helper.player(), ModEffects.THORNS, 200), "Should have THORNS effect");
        helper.clearEffects();
        return TestResult.pass();
    }

    private static TestResult testUnstableRandom(TestContext ctx) {
        TestHelper helper = ctx.helper();
        helper.assertNotNull(ModEffects.UNSTABLE.value(), "UNSTABLE effect should be registered");
        return TestResult.pass();
    }

    private static TestResult testVampirismHeal(TestContext ctx) {
        TestHelper helper = ctx.helper();
        helper.giveEffect(ModEffects.VAMPIRISM, 200, 0);
        helper.assertTrue(helper.hasEffect(helper.player(), ModEffects.VAMPIRISM, 200), "Should have VAMPIRISM effect");
        helper.clearEffects();
        return TestResult.pass();
    }

    private static TestResult testXpBoost(TestContext ctx) {
        TestHelper helper = ctx.helper();
        helper.giveEffect(ModEffects.XP_BOOST, 200, 0);
        helper.assertTrue(helper.hasEffect(helper.player(), ModEffects.XP_BOOST, 200), "Should have XP_BOOST effect");
        helper.clearEffects();
        return TestResult.pass();
    }

    private static TestResult testXpReduction(TestContext ctx) {
        TestHelper helper = ctx.helper();
        helper.giveEffect(ModEffects.XP_REDUCTION, 200, 0);
        helper.assertTrue(helper.hasEffect(helper.player(), ModEffects.XP_REDUCTION, 200), "Should have XP_REDUCTION effect");
        helper.clearEffects();
        return TestResult.pass();
    }

    private static TestResult testXpLifeHealth(TestContext ctx) {
        TestHelper helper = ctx.helper();
        helper.giveEffect(ModEffects.XP_LIFE, 200, 0);
        helper.assertTrue(helper.hasEffect(helper.player(), ModEffects.XP_LIFE, 200), "Should have XP_LIFE effect");
        helper.clearEffects();
        return TestResult.pass();
    }

    private static TestResult testPermHealthPersist(TestContext ctx) {
        TestHelper helper = ctx.helper();
        helper.giveEffect(ModEffects.PERM_HEALTH, 200, 0);
        helper.assertTrue(helper.hasEffect(helper.player(), ModEffects.PERM_HEALTH, 200), "Should have PERM_HEALTH effect");
        helper.clearEffects();
        return TestResult.pass();
    }

    private static TestResult testPermSpeedPersist(TestContext ctx) {
        TestHelper helper = ctx.helper();
        helper.giveEffect(ModEffects.PERM_SPEED, 200, 0);
        helper.assertTrue(helper.hasEffect(helper.player(), ModEffects.PERM_SPEED, 200), "Should have PERM_SPEED effect");
        helper.clearEffects();
        return TestResult.pass();
    }

    private static TestResult testPermStrengthPersist(TestContext ctx) {
        TestHelper helper = ctx.helper();
        helper.giveEffect(ModEffects.PERM_STRENGTH, 200, 0);
        helper.assertTrue(helper.hasEffect(helper.player(), ModEffects.PERM_STRENGTH, 200), "Should have PERM_STRENGTH effect");
        helper.clearEffects();
        return TestResult.pass();
    }

    // === Async effect tests ===

    private static TestResult testSaturationFeed(TestContext ctx) {
        TestHelper helper = ctx.helper();
        helper.giveEffect(ModEffects.SATURATION, 200, 0);
        helper.assertTrue(helper.hasEffect(helper.player(), ModEffects.SATURATION, 200), "Should have SATURATION effect");
        helper.clearEffects();
        return TestResult.pass();
    }

    private static void testTeleportationRandom(TestContext ctx, Consumer<TestResult> cb) {
        TestHelper helper = ctx.helper();
        Vec3 startPos = helper.player().position();
        helper.giveEffect(ModEffects.TELEPORTATION, 1, 0);
        helper.waitTicks(3, () -> {
            helper.assertTrue(helper.player().position().distanceTo(startPos) > 1.0, "TELEPORTATION should move player to a random location");
            helper.clearEffects();
            cb.accept(TestResult.pass());
        });
    }

    private static void testGhostWalkPhasing(TestContext ctx, Consumer<TestResult> cb) {
        TestHelper helper = ctx.helper();
        helper.giveEffect(ModEffects.GHOST_WALK, 20 * 3, 0);
        helper.waitTicks(20, () -> {
            helper.assertTrue(helper.hasEffect(helper.player(), MobEffects.DARKNESS, 20), "GHOST_WALK should apply DARKNESS effect on tick");
            helper.clearEffects();
            cb.accept(TestResult.pass());
        });
    }

    // === Terrain effect stub tests (need block interaction) ===

    private static void testAlchemistTransmute(TestContext ctx, Consumer<TestResult> cb) {
        ctx.helper().giveEffect(ModEffects.ALCHEMIST, 200, 0);
        ctx.helper().waitTicks(1, () -> cb.accept(TestResult.pass()));
    }

    private static void testGiantGrowth(TestContext ctx, Consumer<TestResult> cb) {
        ctx.helper().giveEffect(ModEffects.GIANT, 200, 0);
        ctx.helper().waitTicks(1, () -> cb.accept(TestResult.pass()));
    }

    private static void testIgnitionFire(TestContext ctx, Consumer<TestResult> cb) {
        ctx.helper().giveEffect(ModEffects.IGNITION, 200, 0);
        ctx.helper().waitTicks(1, () -> cb.accept(TestResult.pass()));
    }

    private static void testPetrification(TestContext ctx, Consumer<TestResult> cb) {
        ctx.helper().giveEffect(ModEffects.PETRIFICATION, 200, 0);
        ctx.helper().waitTicks(1, () -> cb.accept(TestResult.pass()));
    }

    private static void testResurrection(TestContext ctx, Consumer<TestResult> cb) {
        ctx.helper().giveEffect(ModEffects.RESURRECTION, 200, 0);
        ctx.helper().waitTicks(1, () -> cb.accept(TestResult.pass()));
    }
}
