package com.matibi.potionsnrituals.potion;

import com.matibi.potionsnrituals.PotionsNRituals;
import com.matibi.potionsnrituals.effect.ModEffects;
import com.matibi.potionsnrituals.item.ModItems;
import com.matibi.potionsnrituals.util.ModUtils;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.fabricmc.fabric.api.registry.FabricPotionBrewingBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;

import com.matibi.potionsnrituals.config.ModConfig;

public class ModPotions {
    public static final Holder<Potion>
            // ── Vanilla extended ─────────────────────────────────────────────────────
            LEVITATION = reg(MobEffects.LEVITATION, ModConfig.get().dur_very_short, 0, "levitation", "levitation"),
            LONG_LEVITATION = reg(MobEffects.LEVITATION, ModConfig.get().dur_short, 0, "levitation", "long_levitation"),

            GLOWING = reg(MobEffects.GLOWING, ModConfig.get().dur_basic, 0, "glowing", "glowing"),
            LONG_GLOWING = reg(MobEffects.GLOWING, ModConfig.get().dur_long, 0, "glowing", "long_glowing"),

            ALCOHOL = reg(MobEffects.NAUSEA, ModConfig.get().dur_basic, 0, "alcohol", "alcohol"),
            LONG_ALCOHOL = reg(MobEffects.NAUSEA, ModConfig.get().dur_long, 0, "alcohol", "long_alcohol"),
            STRONG_ALCOHOL = reg(MobEffects.NAUSEA, ModConfig.get().dur_basic, 1, "alcohol", "strong_alcohol"),

            DARKNESS = reg(MobEffects.DARKNESS, ModConfig.get().dur_short, 0, "darkness", "darkness"),
            LONG_DARKNESS = reg(MobEffects.DARKNESS, ModConfig.get().dur_basic, 0, "darkness", "long_darkness"),

            HASTE = reg(MobEffects.HASTE, ModConfig.get().dur_basic, 0, "haste", "haste"),
            LONG_HASTE = reg(MobEffects.HASTE, ModConfig.get().dur_long, 0, "haste", "long_haste"),
            STRONG_HASTE = reg(MobEffects.HASTE, ModConfig.get().dur_basic, 1, "haste", "strong_haste"),

            MINING_FATIGUE = reg(MobEffects.MINING_FATIGUE, ModConfig.get().dur_basic, 0, "mining_fatigue", "mining_fatigue"),
            LONG_MINING_FATIGUE = reg(MobEffects.MINING_FATIGUE, ModConfig.get().dur_long, 0, "mining_fatigue", "long_mining_fatigue"),
            STRONG_MINING_FATIGUE = reg(MobEffects.MINING_FATIGUE, ModConfig.get().dur_basic, 1, "mining_fatigue", "strong_mining_fatigue"),

            // ── Permanent ────────────────────────────────────────────────────────────
            PERM_HEALTH = reg(ModEffects.PERM_HEALTH, ModConfig.get().dur_instant, 0, "perm_health", "perm_health"),
            PERM_SPEED = reg(ModEffects.PERM_SPEED, ModConfig.get().dur_instant, 0, "perm_speed", "perm_speed"),
            PERM_STRENGTH = reg(ModEffects.PERM_STRENGTH, ModConfig.get().dur_instant, 0, "perm_strength", "perm_strength"),

            // ── Terrain ──────────────────────────────────────────────────────────────
            ACID = reg(ModEffects.ACID, ModConfig.get().dur_short, 0, "acid", "acid"),
            LONG_ACID = reg(ModEffects.ACID, ModConfig.get().dur_basic, 0, "acid", "long_acid"),
            STRONG_ACID = reg(ModEffects.ACID, ModConfig.get().dur_short, 1, "acid", "strong_acid"),

            ALCHEMIST = reg(ModEffects.ALCHEMIST, ModConfig.get().dur_instant, 0, "alchemist", "alchemist"),

            GIANT = reg(ModEffects.GIANT, ModConfig.get().dur_basic, 0, "giant", "giant"),
            LONG_GIANT = reg(ModEffects.GIANT, ModConfig.get().dur_long, 0, "giant", "long_giant"),
            STRONG_GIANT = reg(ModEffects.GIANT, ModConfig.get().dur_basic, 1, "giant", "strong_giant"),

            IGNITION = reg(ModEffects.IGNITION, ModConfig.get().dur_short, 0, "ignition", "ignition"),
            LONG_IGNITION = reg(ModEffects.IGNITION, ModConfig.get().dur_basic, 0, "ignition", "long_ignition"),

            PETRIFICATION = reg(ModEffects.PETRIFICATION, ModConfig.get().dur_short, 0, "petrification", "petrification"),
            LONG_PETRIFICATION = reg(ModEffects.PETRIFICATION, ModConfig.get().dur_basic, 0, "petrification", "long_petrification"),
            STRONG_PETRIFICATION = reg(ModEffects.PETRIFICATION, ModConfig.get().dur_short, 1, "petrification", "strong_petrification"),

            RESURRECTION = reg(ModEffects.RESURRECTION, ModConfig.get().dur_inf, 0, "resurrection", "resurrection"),

            // ── Normal ───────────────────────────────────────────────────────────────
            ADHESION = reg(ModEffects.ADHESION, ModConfig.get().dur_basic, 0, "adhesion", "adhesion"),
            LONG_ADHESION = reg(ModEffects.ADHESION, ModConfig.get().dur_long, 0, "adhesion", "long_adhesion"),

            BERSERK = reg(ModEffects.BERSERK, ModConfig.get().dur_short, 0, "berserk", "berserk"),
            LONG_BERSERK = reg(ModEffects.BERSERK, ModConfig.get().dur_basic, 0, "berserk", "long_berserk"),
            STRONG_BERSERK = reg(ModEffects.BERSERK, ModConfig.get().dur_short, 1, "berserk", "strong_berserk"),

            BRAINWASHING = reg(ModEffects.BRAINWASHING, ModConfig.get().dur_basic, 0, "brainwashing", "brainwashing"),
            LONG_BRAINWASHING = reg(ModEffects.BRAINWASHING, ModConfig.get().dur_long, 0, "brainwashing", "long_brainwashing"),

            DEATH = reg(ModEffects.DEATH, ModConfig.get().dur_instant, 0, "death", "death"),

            DOUBLE_HEALTH = reg(ModEffects.DOUBLE_HEALTH, ModConfig.get().dur_instant, 0, "double_health", "double_health"),
            STRONG_DOUBLE_HEALTH = reg(ModEffects.DOUBLE_HEALTH, ModConfig.get().dur_instant, 1, "double_health", "strong_double_health"),

            DWARF = reg(ModEffects.DWARF, ModConfig.get().dur_basic, 0, "dwarf", "dwarf"),
            LONG_DWARF = reg(ModEffects.DWARF, ModConfig.get().dur_long, 0, "dwarf", "long_dwarf"),
            STRONG_DWARF = reg(ModEffects.DWARF, ModConfig.get().dur_basic, 1, "dwarf", "strong_dwarf"),

            FROST = reg(ModEffects.FROST, ModConfig.get().dur_short, 0, "frost", "frost"),
            LONG_FROST = reg(ModEffects.FROST, ModConfig.get().dur_basic, 0, "frost", "long_frost"),

            GHOST_WALK = reg(ModEffects.GHOST_WALK, ModConfig.get().dur_very_short, 0, "ghost_walk", "ghost_walk"),
            LONG_GHOST_WALK = reg(ModEffects.GHOST_WALK, ModConfig.get().dur_short, 0, "ghost_walk", "long_ghost_walk"),

            LIQUID_WALKER = reg(ModEffects.LIQUID_WALKER, ModConfig.get().dur_basic, 0, "liquid_walker", "liquid_walker"),
            LONG_LIQUID_WALKER = reg(ModEffects.LIQUID_WALKER, ModConfig.get().dur_long, 0, "liquid_walker", "long_liquid_walker"),
            STRONG_LIQUID_WALKER = reg(ModEffects.LIQUID_WALKER, ModConfig.get().dur_basic, 1, "liquid_walker", "strong_liquid_walker"),

            LONG_COOLDOWN = reg(ModEffects.LONG_COOLDOWN, ModConfig.get().dur_basic, 0, "long_cooldown", "long_cooldown"),
            LONG_LONG_COOLDOWN = reg(ModEffects.LONG_COOLDOWN, ModConfig.get().dur_long, 0, "long_cooldown", "long_long_cooldown"),

            LONG_LEG = reg(ModEffects.LONG_LEG, ModConfig.get().dur_basic, 0, "long_leg", "long_leg"),
            LONG_LONG_LEG = reg(ModEffects.LONG_LEG, ModConfig.get().dur_long, 0, "long_leg", "long_long_leg"),

            LOVE = reg(ModEffects.LOVE, ModConfig.get().dur_basic, 0, "love", "love"),
            LONG_LOVE = reg(ModEffects.LOVE, ModConfig.get().dur_long, 0, "love", "long_love"),

            MASKING = reg(ModEffects.MASKING, ModConfig.get().dur_instant, 0, "masking", "masking"),

            NO_INTERACTION = reg(ModEffects.NO_INTERACTION, ModConfig.get().dur_basic, 0, "no_interaction", "no_interaction"),
            LONG_NO_INTERACTION = reg(ModEffects.NO_INTERACTION, ModConfig.get().dur_long, 0, "no_interaction", "long_no_interaction"),

            OBLIVION = reg(ModEffects.OBLIVION, ModConfig.get().dur_instant, 0, "oblivion", "oblivion"),

            ORE_SENSE = reg(ModEffects.ORE_SENSE, ModConfig.get().dur_basic, 0, "ore_sense", "ore_sense"),
            LONG_ORE_SENSE = reg(ModEffects.ORE_SENSE, ModConfig.get().dur_long, 0, "ore_sense", "long_ore_sense"),
            STRONG_ORE_SENSE = reg(ModEffects.ORE_SENSE, ModConfig.get().dur_basic, 1, "ore_sense", "strong_ore_sense"),

            PHOTOSYNTHESIS = reg(ModEffects.PHOTOSYNTHESIS, ModConfig.get().dur_basic, 0, "photosynthesis", "photosynthesis"),
            LONG_PHOTOSYNTHESIS = reg(ModEffects.PHOTOSYNTHESIS, ModConfig.get().dur_long, 0, "photosynthesis", "long_photosynthesis"),
            STRONG_PHOTOSYNTHESIS = reg(ModEffects.PHOTOSYNTHESIS, ModConfig.get().dur_basic, 1, "photosynthesis", "strong_photosynthesis"),

            PURIFICATION = reg(ModEffects.PURIFICATION, ModConfig.get().dur_basic, 0, "purification", "purification"),
            LONG_PURIFICATION = reg(ModEffects.PURIFICATION, ModConfig.get().dur_long, 0, "purification", "long_purification"),

            REACTIVATION = reg(ModEffects.REACTIVATION, ModConfig.get().dur_instant, 0, "reactivation", "reactivation"),
            STRONG_REACTIVATION = reg(ModEffects.REACTIVATION, ModConfig.get().dur_instant, 1, "reactivation", "strong_reactivation"),

            RESONANCE = reg(ModEffects.RESONANCE, ModConfig.get().dur_basic, 0, "resonance", "resonance"),
            LONG_RESONANCE = reg(ModEffects.RESONANCE, ModConfig.get().dur_long, 0, "resonance", "long_resonance"),
            STRONG_RESONANCE = reg(ModEffects.RESONANCE, ModConfig.get().dur_basic, 1, "resonance", "strong_resonance"),

            INFINITY = reg(ModEffects.INFINITY, ModConfig.get().dur_instant, 0, "infinity", "infinity"),

            RUST = reg(ModEffects.RUST, ModConfig.get().dur_short, 0, "rust", "rust"),
            LONG_RUST = reg(ModEffects.RUST, ModConfig.get().dur_basic, 0, "rust", "long_rust"),

            SATURATION = reg(ModEffects.SATURATION, ModConfig.get().dur_short, 0, "saturation", "saturation"),
            LONG_SATURATION = reg(ModEffects.SATURATION, ModConfig.get().dur_basic, 0, "saturation", "long_saturation"),
            STRONG_SATURATION = reg(ModEffects.SATURATION, ModConfig.get().dur_short, 1, "saturation", "strong_saturation"),

            SHORT_COOLDOWN = reg(ModEffects.SHORT_COOLDOWN, ModConfig.get().dur_basic, 0, "short_cooldown", "short_cooldown"),
            LONG_SHORT_COOLDOWN = reg(ModEffects.SHORT_COOLDOWN, ModConfig.get().dur_long, 0, "short_cooldown", "long_short_cooldown"),

            STUN = reg(ModEffects.STUN, 20 * 5, 0, "stun", "stun"),
            LONG_STUN = reg(ModEffects.STUN, 20 * 15, 0, "stun", "long_stun"),

            TELEPORTATION = reg(ModEffects.TELEPORTATION, ModConfig.get().dur_instant, 0, "teleportation", "teleportation"),
            STRONG_TELEPORTATION = reg(ModEffects.TELEPORTATION, ModConfig.get().dur_instant, 1, "teleportation", "strong_teleportation"),

            THORNS = reg(ModEffects.THORNS, ModConfig.get().dur_basic, 0, "thorns", "thorns"),
            LONG_THORNS = reg(ModEffects.THORNS, ModConfig.get().dur_long, 0, "thorns", "long_thorns"),
            STRONG_THORNS = reg(ModEffects.THORNS, ModConfig.get().dur_basic, 1, "thorns", "strong_thorns"),

            UNSTABLE = reg(ModEffects.UNSTABLE, ModConfig.get().dur_instant, 0, "unstable", "unstable"),
            STRONG_UNSTABLE = reg(ModEffects.UNSTABLE, ModConfig.get().dur_instant, 1, "unstable", "strong_unstable"),

            VAMPIRISM = reg(ModEffects.VAMPIRISM, ModConfig.get().dur_basic, 0, "vampirism", "vampirism"),
            LONG_VAMPIRISM = reg(ModEffects.VAMPIRISM, ModConfig.get().dur_long, 0, "vampirism", "long_vampirism"),
            STRONG_VAMPIRISM = reg(ModEffects.VAMPIRISM, ModConfig.get().dur_basic, 1, "vampirism", "strong_vampirism"),

            XP_BOOST = reg(ModEffects.XP_BOOST, ModConfig.get().dur_basic, 0, "xp_boost", "xp_boost"),
            LONG_XP_BOOST = reg(ModEffects.XP_BOOST, ModConfig.get().dur_long, 0, "xp_boost", "long_xp_boost"),
            STRONG_XP_BOOST = reg(ModEffects.XP_BOOST, ModConfig.get().dur_basic, 1, "xp_boost", "strong_xp_boost"),

            XP_REDUCTION = reg(ModEffects.XP_REDUCTION, ModConfig.get().dur_basic, 0, "xp_reduction", "xp_reduction"),
            LONG_XP_REDUCTION = reg(ModEffects.XP_REDUCTION, ModConfig.get().dur_long, 0, "xp_reduction", "long_xp_reduction"),
            STRONG_XP_REDUCTION = reg(ModEffects.XP_REDUCTION, ModConfig.get().dur_basic, 1, "xp_reduction", "strong_xp_reduction"),

            XP_LIFE = reg(ModEffects.XP_LIFE, ModConfig.get().dur_basic, 0, "xp_life", "xp_life"),
            LONG_XP_LIFE = reg(ModEffects.XP_LIFE, ModConfig.get().dur_long, 0, "xp_life", "long_xp_life"),

            ZEUS = reg(ModEffects.ZEUS_BENEDICTION, ModConfig.get().dur_basic, 0, "zeus", "zeus"),
            LONG_ZEUS = reg(ModEffects.ZEUS_BENEDICTION, ModConfig.get().dur_long, 0, "zeus", "long_zeus"),

            COLD = reg(ModEffects.COLD, ModConfig.get().dur_basic, 0, "cold", "cold"),
            LONG_COLD = reg(ModEffects.COLD, ModConfig.get().dur_long, 0, "cold", "long_cold"),

            CLUMSINESS = reg(ModEffects.CLUMSINESS, ModConfig.get().dur_basic, 0, "clumsiness", "clumsiness"),
            LONG_CLUMSINESS = reg(ModEffects.CLUMSINESS, ModConfig.get().dur_long, 0, "clumsiness", "long_clumsiness"),

            ASTHMA = reg(ModEffects.ASTHMA, ModConfig.get().dur_basic, 0, "asthma", "asthma"),
            LONG_ASTHMA = reg(ModEffects.ASTHMA, ModConfig.get().dur_long, 0, "asthma", "long_asthma"),

            PARANOIA = reg(ModEffects.PARANOIA, ModConfig.get().dur_basic, 0, "paranoia", "paranoia"),
            LONG_PARANOIA = reg(ModEffects.PARANOIA, ModConfig.get().dur_long, 0, "paranoia", "long_paranoia"),

            MEDUSA = reg(ModEffects.MEDUSA_BENEDICTION, ModConfig.get().dur_basic, 0, "medusa", "medusa"),
            LONG_MEDUSA = reg(ModEffects.MEDUSA_BENEDICTION, ModConfig.get().dur_long, 0, "medusa", "long_medusa"),

            MIDAS = reg(ModEffects.MIDAS_BENEDICTION, ModConfig.get().dur_basic, 0, "midas", "midas"),
            LONG_MIDAS = reg(ModEffects.MIDAS_BENEDICTION, ModConfig.get().dur_long, 0, "midas", "long_midas"),

            ACTIVE_TELEPORT = reg(ModEffects.ACTIVE_TP, ModConfig.get().dur_basic, 0, "active_tp", "active_tp"),
            LONG_ACTIVE_TELEPORT = reg(ModEffects.ACTIVE_TP, ModConfig.get().dur_long, 0, "active_tp", "long_active_tp"),

            HYDROPHOBIA = reg(ModEffects.HYDROPHOBIA, ModConfig.get().dur_basic, 0, "hydrophobia", "hydrophobia"),
            LONG_HYDROPHOBIA = reg(ModEffects.HYDROPHOBIA, ModConfig.get().dur_long, 0, "hydrophobia", "long_hydrophobia"),
            STRONG_HYDROPHOBIA = reg(ModEffects.HYDROPHOBIA, ModConfig.get().dur_basic, 1, "hydrophobia", "strong_hydrophobia"),

            ZOMBIE_CONTAGION = reg(ModEffects.ZOMBIE_CONTAGION, ModConfig.get().zombie_contagion_duration, 0, "zombie_contagion", "zombie_contagion"),

            MAGNETISM = reg(ModEffects.MAGNETISM, ModConfig.get().dur_basic, 0, "magnetism", "magnetism"),
            LONG_MAGNETISM = reg(ModEffects.MAGNETISM, ModConfig.get().dur_long, 0, "magnetism", "long_magnetism"),
            STRONG_MAGNETISM = reg(ModEffects.MAGNETISM, ModConfig.get().dur_basic, 1, "magnetism", "strong_magnetism"),

            REALITY_CHECK = reg(ModEffects.REALITY_CHECK, ModConfig.get().dur_basic, 0, "reality_check", "reality_check"),
            LONG_REALITY_CHECK = reg(ModEffects.REALITY_CHECK, ModConfig.get().dur_long, 0, "reality_check", "long_reality_check");


    public static void register() {
        PotionsNRituals.LOGGER.info("Registering potions for " + PotionsNRituals.MOD_ID);

        DefaultItemComponentEvents.MODIFY.register(context -> context.modify(item ->
                        item == Items.POTION || item == Items.SPLASH_POTION || item == Items.LINGERING_POTION,
                (builder, _) -> builder.set(DataComponents.MAX_STACK_SIZE, ModConfig.get().max_potion_stack)
        ));

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FOOD_AND_DRINKS).register(output ->
                output.getDisplayStacks().removeIf(stack ->
                stack.getItem() instanceof PotionItem
        ));

        FabricPotionBrewingBuilder.BUILD.register(builder -> {
            // changement de base, mais moins efficace que de farmer des nether wart
            addMix(builder, Potions.WATER, ModItems.MATERIA_PRIMA, Potions.AWKWARD);

            // potions accessible early
            addMix(builder, Potions.AWKWARD, Items.MAGMA_BLOCK, Potions.FIRE_RESISTANCE);
            addMix(builder, Potions.AWKWARD, Items.GOLDEN_APPLE, Potions.REGENERATION);
            addMix(builder, Potions.AWKWARD, Items.FIREWORK_STAR, Potions.STRENGTH);
            addMix(builder, Potions.AWKWARD, ModItems.POISONOUS_CARROT, Potions.POISON);
            addMix(builder, Potions.AWKWARD, ModItems.POISONOUS_BEETROOT, Potions.POISON);
            addMix(builder, Potions.AWKWARD, Items.POISONOUS_POTATO, Potions.POISON);

            // potion custom
            addMix(builder, Potions.SLOW_FALLING, Items.FEATHER, ModPotions.LEVITATION);
            addMix(builder, Potions.NIGHT_VISION, Items.GLOW_BERRIES, ModPotions.GLOWING);
            addMix(builder, Potions.AWKWARD, Items.SWEET_BERRIES, ModPotions.ALCOHOL);
            addMix(builder, Potions.AWKWARD, Items.INK_SAC, ModPotions.DARKNESS);
            addMix(builder, Potions.AWKWARD, Items.BAMBOO, ModPotions.LONG_LEG);
            addMix(builder, Potions.AWKWARD, Items.POPPY, ModPotions.LOVE);
            addMix(builder, Potions.AWKWARD, Items.LILY_PAD, ModPotions.LIQUID_WALKER);
            addMix(builder, Potions.NIGHT_VISION, Items.IRON_ORE, ModPotions.ORE_SENSE);
            addMix(builder, Potions.AWKWARD, Items.ECHO_SHARD, ModPotions.RESONANCE);
            addMix(builder, ModPotions.SHORT_COOLDOWN, Items.SUNFLOWER, ModPotions.REACTIVATION);
            addMix(builder, Potions.AWKWARD, Items.MILK_BUCKET, ModPotions.PURIFICATION);
            addMix(builder, Potions.TURTLE_MASTER, Items.OBSIDIAN, ModPotions.PETRIFICATION);
            addMix(builder, Potions.POISON, Items.ROTTEN_FLESH, ModPotions.ACID);
            addMix(builder, Potions.FIRE_RESISTANCE, Items.FERMENTED_SPIDER_EYE, ModPotions.IGNITION);
            addMix(builder, Potions.AWKWARD, Items.ENDER_PEARL, ModPotions.TELEPORTATION);
            addMix(builder, Potions.AWKWARD, Items.CACTUS, ModPotions.THORNS);
            addMix(builder, Potions.AWKWARD, ModItems.ZOMBIE_BRAIN, ModPotions.BRAINWASHING);
            addMix(builder, Potions.AWKWARD, Items.SNOWBALL, ModPotions.COLD);
            addMix(builder, ModPotions.COLD, Items.SNOWBALL, ModPotions.FROST);
            addMix(builder, Potions.AWKWARD, Items.COPPER_INGOT, ModPotions.ALCHEMIST);
            addMix(builder, ModPotions.RESURRECTION, Items.WITHER_ROSE, ModPotions.DEATH);
            addMix(builder, Potions.AWKWARD, Items.BEETROOT, ModPotions.SATURATION);
            addMix(builder, Potions.STRONG_HEALING, Items.GOLDEN_APPLE, ModPotions.DOUBLE_HEALTH);
            addMix(builder, Potions.AWKWARD, Items.TOTEM_OF_UNDYING, ModPotions.RESURRECTION);
            addMix(builder, Potions.AWKWARD, ModItems.CLAW, ModPotions.HASTE);
            addMix(builder, ModPotions.HASTE, Items.FERMENTED_SPIDER_EYE, ModPotions.MINING_FATIGUE);
            addMix(builder, Potions.AWKWARD, Items.SUNFLOWER, ModPotions.SHORT_COOLDOWN);
            addMix(builder, ModPotions.SHORT_COOLDOWN, Items.FERMENTED_SPIDER_EYE, ModPotions.LONG_COOLDOWN);
            addMix(builder, Potions.INVISIBILITY, Items.FERMENTED_SPIDER_EYE, ModPotions.MASKING);
            addMix(builder, Potions.AWKWARD, ModItems.BLOOD_BAG, ModPotions.VAMPIRISM);
            addMix(builder, Potions.AWKWARD, Items.TORCHFLOWER, ModPotions.BERSERK);
            addMix(builder, Potions.AWKWARD, Items.BONE_MEAL, ModPotions.GIANT);
            addMix(builder, ModPotions.GIANT, Items.FERMENTED_SPIDER_EYE, ModPotions.DWARF);
            addMix(builder, Potions.AWKWARD, Items.LEAF_LITTER, ModPotions.PHOTOSYNTHESIS);
            addMix(builder, Potions.AWKWARD, Items.PITCHER_PLANT, ModPotions.OBLIVION);
            addMix(builder, Potions.AWKWARD, Items.GHAST_TEAR, ModPotions.GHOST_WALK);
            addMix(builder, Potions.AWKWARD, ModItems.CHARGED_COPPER, ModPotions.STUN);
            addMix(builder, Potions.AWKWARD, ModItems.OXYDATION, ModPotions.RUST);
            addMix(builder, Potions.AWKWARD, Items.RESIN_CLUMP, ModPotions.ADHESION);
            addMix(builder, ModPotions.BRAINWASHING, Items.RABBIT_FOOT, ModPotions.NO_INTERACTION);
            addMix(builder, Potions.AWKWARD, Items.LAPIS_LAZULI, ModPotions.XP_BOOST);
            addMix(builder, ModPotions.XP_BOOST, Items.FERMENTED_SPIDER_EYE, ModPotions.XP_REDUCTION);
            addMix(builder, ModPotions.XP_BOOST, Items.GLISTERING_MELON_SLICE, ModPotions.XP_LIFE);
            addMix(builder, ModPotions.TELEPORTATION, ModItems.SULFUR_BALL, ModPotions.ACTIVE_TELEPORT);
            addMix(builder, Potions.WATER_BREATHING, Items.FERMENTED_SPIDER_EYE, ModPotions.HYDROPHOBIA);
            addMix(builder, Potions.AWKWARD, Items.ROTTEN_FLESH, ModPotions.ZOMBIE_CONTAGION);
            addMix(builder, Potions.AWKWARD, Items.IRON_INGOT, ModPotions.MAGNETISM);
            addMix(builder, ModPotions.STRONG_PETRIFICATION, ModItems.MATERIA_PRIMA, ModPotions.MEDUSA);
            addMix(builder, ModPotions.ALCHEMIST, ModItems.MATERIA_PRIMA, ModPotions.MIDAS);
            addMix(builder, ModPotions.STUN, ModItems.MATERIA_PRIMA, ModPotions.ZEUS);

            // version longue
            addLong(builder, ModPotions.LEVITATION, ModPotions.LONG_LEVITATION);
            addLong(builder, ModPotions.GLOWING, ModPotions.LONG_GLOWING);
            addLong(builder, ModPotions.ALCOHOL, ModPotions.LONG_ALCOHOL);
            addLong(builder, ModPotions.DARKNESS, ModPotions.LONG_DARKNESS);
            addLong(builder, ModPotions.LONG_LEG, ModPotions.LONG_LONG_LEG);
            addLong(builder, ModPotions.LOVE, ModPotions.LONG_LOVE);
            addLong(builder, ModPotions.LIQUID_WALKER, ModPotions.LONG_LIQUID_WALKER);
            addLong(builder, ModPotions.ORE_SENSE, ModPotions.LONG_ORE_SENSE);
            addLong(builder, ModPotions.RESONANCE, ModPotions.LONG_RESONANCE);
            addLong(builder, ModPotions.PURIFICATION, ModPotions.LONG_PURIFICATION);
            addLong(builder, ModPotions.PETRIFICATION, ModPotions.LONG_PETRIFICATION);
            addLong(builder, ModPotions.ACID, ModPotions.LONG_ACID);
            addLong(builder, ModPotions.IGNITION, ModPotions.LONG_IGNITION);
            addLong(builder, ModPotions.THORNS, ModPotions.LONG_THORNS);
            addLong(builder, ModPotions.BRAINWASHING, ModPotions.LONG_BRAINWASHING);
            addLong(builder, ModPotions.FROST, ModPotions.LONG_FROST);
            addLong(builder, ModPotions.COLD, ModPotions.LONG_COLD);
            addMix(builder, ModPotions.LONG_COLD, Items.SNOWBALL, ModPotions.LONG_COLD);
            addLong(builder, ModPotions.SATURATION, ModPotions.LONG_SATURATION);
            addLong(builder, ModPotions.HASTE, ModPotions.LONG_HASTE);
            addLong(builder, ModPotions.MINING_FATIGUE, ModPotions.LONG_MINING_FATIGUE);
            addMix(builder, ModPotions.LONG_HASTE, Items.FERMENTED_SPIDER_EYE, ModPotions.LONG_MINING_FATIGUE);
            addLong(builder, ModPotions.SHORT_COOLDOWN, ModPotions.LONG_SHORT_COOLDOWN);
            addLong(builder, ModPotions.LONG_COOLDOWN, ModPotions.LONG_LONG_COOLDOWN);
            addMix(builder, ModPotions.LONG_SHORT_COOLDOWN, Items.FERMENTED_SPIDER_EYE, ModPotions.LONG_LONG_COOLDOWN);
            addLong(builder, ModPotions.VAMPIRISM, ModPotions.LONG_VAMPIRISM);
            addLong(builder, ModPotions.STUN, ModPotions.LONG_STUN);
            addLong(builder, ModPotions.NO_INTERACTION, ModPotions.LONG_NO_INTERACTION);
            addLong(builder, ModPotions.BERSERK, ModPotions.LONG_BERSERK);
            addLong(builder, ModPotions.GHOST_WALK, ModPotions.LONG_GHOST_WALK);
            addLong(builder, ModPotions.GIANT, ModPotions.LONG_GIANT);
            addLong(builder, ModPotions.DWARF, ModPotions.LONG_DWARF);
            addLong(builder, ModPotions.PHOTOSYNTHESIS, ModPotions.LONG_PHOTOSYNTHESIS);
            addLong(builder, ModPotions.ADHESION, ModPotions.LONG_ADHESION);
            addLong(builder, ModPotions.RUST, ModPotions.LONG_RUST);
            addLong(builder, ModPotions.XP_BOOST, ModPotions.LONG_XP_BOOST);
            addLong(builder, ModPotions.XP_REDUCTION, ModPotions.LONG_XP_REDUCTION);
            addMix(builder, ModPotions.LONG_XP_BOOST, Items.FERMENTED_SPIDER_EYE, ModPotions.LONG_XP_REDUCTION);
            addLong(builder, ModPotions.XP_LIFE, ModPotions.LONG_XP_LIFE);
            addLong(builder, ModPotions.ZEUS, ModPotions.LONG_ZEUS);
            addLong(builder, ModPotions.CLUMSINESS, ModPotions.LONG_ZEUS);
            addLong(builder, ModPotions.ASTHMA, ModPotions.LONG_ASTHMA);
            addLong(builder, ModPotions.PARANOIA, ModPotions.LONG_PARANOIA);
            addLong(builder, ModPotions.CLUMSINESS, ModPotions.LONG_CLUMSINESS);
            addLong(builder, ModPotions.MEDUSA, ModPotions.LONG_MEDUSA);
            addLong(builder, ModPotions.ACTIVE_TELEPORT, ModPotions.LONG_ACTIVE_TELEPORT);
            addLong(builder, ModPotions.HYDROPHOBIA, ModPotions.LONG_HYDROPHOBIA);
            addLong(builder, ModPotions.MAGNETISM, ModPotions.LONG_MAGNETISM);
            addLong(builder, ModPotions.MIDAS, ModPotions.LONG_MIDAS);
            addLong(builder, ModPotions.REALITY_CHECK, ModPotions.LONG_REALITY_CHECK);

            // version strong
            addStrong(builder, ModPotions.ALCOHOL, ModPotions.STRONG_ALCOHOL);
            addStrong(builder, ModPotions.ORE_SENSE, ModPotions.STRONG_ORE_SENSE);
            addStrong(builder, ModPotions.RESONANCE, ModPotions.STRONG_RESONANCE);
            addStrong(builder, ModPotions.REACTIVATION, ModPotions.STRONG_REACTIVATION);
            addStrong(builder, ModPotions.ACID, ModPotions.STRONG_ACID);
            addStrong(builder, ModPotions.THORNS, ModPotions.STRONG_THORNS);
            addStrong(builder, ModPotions.SATURATION, ModPotions.STRONG_SATURATION);
            addStrong(builder, ModPotions.DOUBLE_HEALTH, ModPotions.STRONG_DOUBLE_HEALTH);
            addStrong(builder, ModPotions.MINING_FATIGUE, ModPotions.STRONG_MINING_FATIGUE);
            addStrong(builder, ModPotions.UNSTABLE, ModPotions.STRONG_UNSTABLE);
            addStrong(builder, ModPotions.VAMPIRISM, ModPotions.STRONG_VAMPIRISM);
            addStrong(builder, ModPotions.BERSERK, ModPotions.STRONG_BERSERK);
            addStrong(builder, ModPotions.TELEPORTATION, ModPotions.STRONG_TELEPORTATION);
            addStrong(builder, ModPotions.GIANT, ModPotions.STRONG_GIANT);
            addStrong(builder, ModPotions.DWARF, ModPotions.STRONG_DWARF);
            addStrong(builder, ModPotions.PHOTOSYNTHESIS, ModPotions.STRONG_PHOTOSYNTHESIS);
            addStrong(builder, ModPotions.LIQUID_WALKER, ModPotions.STRONG_LIQUID_WALKER);
            addStrong(builder, ModPotions.PETRIFICATION, ModPotions.STRONG_PETRIFICATION);
            addStrong(builder, ModPotions.HASTE, ModPotions.STRONG_HASTE);
            addStrong(builder, ModPotions.XP_BOOST, ModPotions.STRONG_XP_BOOST);
            addStrong(builder, ModPotions.XP_REDUCTION, ModPotions.STRONG_XP_REDUCTION);
            addStrong(builder, ModPotions.HYDROPHOBIA, ModPotions.STRONG_HYDROPHOBIA);
            addStrong(builder, ModPotions.MAGNETISM, ModPotions.STRONG_MAGNETISM);
        });
    }

    private static void addMix(PotionBrewing.Builder builder,
                               Holder<Potion> input,
                               Item ingredient,
                               Holder<Potion> output) {
        if (ModConfig.get().isPotionBlacklisted(input) || ModConfig.get().isPotionBlacklisted(output)) return;
        builder.addMix(input, ingredient, output);
    }

    private static void addLong(PotionBrewing.Builder builder,
                                  Holder<Potion> input,
                                  Holder<Potion> output) {
        addMix(builder, input, Items.REDSTONE, output);
    }

    private static void addStrong(PotionBrewing.Builder builder,
              Holder<Potion> input,
              Holder<Potion> output) {
        addMix(builder, input, Items.GLOWSTONE_DUST, output);
    }

    private static Holder<Potion> reg(Holder<MobEffect> effect,
                                      int duration,
                                      int amplifier,
                                      String name,
                                      String id) {
        return Registry.registerForHolder(
                BuiltInRegistries.POTION,
                ModUtils.id(id),
                new Potion(name, new MobEffectInstance(effect, duration, amplifier))
        );
    }
}
