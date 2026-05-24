package com.matibi.potionsnrituals.potion;

import com.matibi.potionsnrituals.PotionsNRituals;
import com.matibi.potionsnrituals.effect.ModEffects;
import com.matibi.potionsnrituals.item.ModItems;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.fabricmc.fabric.api.registry.FabricPotionBrewingBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
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

import static com.matibi.potionsnrituals.config.ModConfig.*;

public class ModPotions {
    public static final Holder<Potion>
            // ── Vanilla extended ─────────────────────────────────────────────────────
            LEVITATION              = reg(MobEffects.LEVITATION,    dur_very_short,     0, "levitation",    "levitation"),
            LONG_LEVITATION         = reg(MobEffects.LEVITATION,    dur_short,          0, "levitation",    "long_levitation"),

            GLOWING                 = reg(MobEffects.GLOWING,       dur_basic,          0, "glowing",       "glowing"),
            LONG_GLOWING            = reg(MobEffects.GLOWING,       dur_long,           0, "glowing",       "long_glowing"),

            ALCOHOL                 = reg(MobEffects.NAUSEA,        dur_basic,          0, "alcohol",       "alcohol"),
            LONG_ALCOHOL            = reg(MobEffects.NAUSEA,        dur_long,           0, "alcohol",       "long_alcohol"),
            STRONG_ALCOHOL          = reg(MobEffects.NAUSEA,        dur_basic,          1, "alcohol",       "strong_alcohol"),

            DARKNESS                = reg(MobEffects.DARKNESS,      dur_short,          0, "darkness",      "darkness"),
            LONG_DARKNESS           = reg(MobEffects.DARKNESS,      dur_basic,          0, "darkness",      "long_darkness"),

            HASTE                   = reg(MobEffects.HASTE,         dur_basic,          0, "haste",         "haste"),
            LONG_HASTE              = reg(MobEffects.HASTE,         dur_long,           0, "haste",         "long_haste"),
            STRONG_HASTE            = reg(MobEffects.HASTE,         dur_basic,          1, "haste",         "strong_haste"),

            MINING_FATIGUE          = reg(MobEffects.MINING_FATIGUE,dur_basic,          0, "mining_fatigue","mining_fatigue"),
            LONG_MINING_FATIGUE     = reg(MobEffects.MINING_FATIGUE,dur_long,           0, "mining_fatigue","long_mining_fatigue"),
            STRONG_MINING_FATIGUE   = reg(MobEffects.MINING_FATIGUE,dur_basic,          1, "mining_fatigue","strong_mining_fatigue"),

            // ── Permanent ────────────────────────────────────────────────────────────
            PERM_HEALTH             = reg(ModEffects.PERM_HEALTH,   dur_instant,        0, "perm_health",   "perm_health"),
            PERM_SPEED              = reg(ModEffects.PERM_SPEED,    dur_instant,        0, "perm_speed",    "perm_speed"),
            PERM_STRENGTH           = reg(ModEffects.PERM_STRENGTH, dur_instant,        0, "perm_strength", "perm_strength"),

            // ── Terrain ──────────────────────────────────────────────────────────────
            ACID                    = reg(ModEffects.ACID,          dur_short,          0, "acid",          "acid"),
            LONG_ACID               = reg(ModEffects.ACID,          dur_basic,          0, "acid",          "long_acid"),
            STRONG_ACID             = reg(ModEffects.ACID,          dur_short,          1, "acid",          "strong_acid"),

            ALCHEMIST               = reg(ModEffects.ALCHEMIST,     dur_instant,        0, "alchemist",     "alchemist"),

            GIANT                   = reg(ModEffects.GIANT,         dur_basic,          0, "giant",         "giant"),
            LONG_GIANT              = reg(ModEffects.GIANT,         dur_long,           0, "giant",         "long_giant"),
            STRONG_GIANT            = reg(ModEffects.GIANT,         dur_basic,          1, "giant",         "strong_giant"),

            IGNITION                = reg(ModEffects.IGNITION,      dur_short,          0, "ignition",      "ignition"),
            LONG_IGNITION           = reg(ModEffects.IGNITION,      dur_basic,          0, "ignition",      "long_ignition"),

            PETRIFICATION           = reg(ModEffects.PETRIFICATION, dur_short,          0, "petrification", "petrification"),
            LONG_PETRIFICATION      = reg(ModEffects.PETRIFICATION, dur_basic,          0, "petrification", "long_petrification"),
            STRONG_PETRIFICATION    = reg(ModEffects.PETRIFICATION, dur_short,          1, "petrification", "strong_petrification"),

            RESURRECTION            = reg(ModEffects.RESURRECTION,  dur_inf,            0, "resurrection",  "resurrection"),

            // ── Normal ───────────────────────────────────────────────────────────────
            ADHESION                = reg(ModEffects.ADHESION,      dur_basic,          0, "adhesion",      "adhesion"),
            LONG_ADHESION           = reg(ModEffects.ADHESION,      dur_long,           0, "adhesion",      "long_adhesion"),

            BERSERK                 = reg(ModEffects.BERSERK,       dur_short,          0, "berserk",       "berserk"),
            LONG_BERSERK            = reg(ModEffects.BERSERK,       dur_basic,          0, "berserk",       "long_berserk"),
            STRONG_BERSERK          = reg(ModEffects.BERSERK,       dur_short,          1, "berserk",       "strong_berserk"),

            BRAINWASHING            = reg(ModEffects.BRAINWASHING,  dur_basic,          0, "brainwashing",  "brainwashing"),
            LONG_BRAINWASHING       = reg(ModEffects.BRAINWASHING,  dur_long,           0, "brainwashing",  "long_brainwashing"),

            DEATH                   = reg(ModEffects.DEATH,         dur_instant,        0, "death",         "death"),

            DOUBLE_HEALTH           = reg(ModEffects.DOUBLE_HEALTH, dur_instant,        0, "double_health", "double_health"),
            STRONG_DOUBLE_HEALTH    = reg(ModEffects.DOUBLE_HEALTH, dur_instant,        1, "double_health", "strong_double_health"),

            DWARF                   = reg(ModEffects.DWARF,         dur_basic,          0, "dwarf",         "dwarf"),
            LONG_DWARF              = reg(ModEffects.DWARF,         dur_long,           0, "dwarf",         "long_dwarf"),
            STRONG_DWARF            = reg(ModEffects.DWARF,         dur_basic,          1, "dwarf",         "strong_dwarf"),

            FROST                   = reg(ModEffects.FROST,         dur_short,          0, "frost",         "frost"),
            LONG_FROST              = reg(ModEffects.FROST,         dur_basic,          0, "frost",         "long_frost"),

            GHOST_WALK              = reg(ModEffects.GHOST_WALK,    dur_very_short,     0, "ghost_walk",    "ghost_walk"),
            LONG_GHOST_WALK         = reg(ModEffects.GHOST_WALK,    dur_short,          0, "ghost_walk",    "long_ghost_walk"),

            LIQUID_WALKER           = reg(ModEffects.LIQUID_WALKER, dur_basic,          0, "liquid_walker", "liquid_walker"),
            LONG_LIQUID_WALKER      = reg(ModEffects.LIQUID_WALKER, dur_long,           0, "liquid_walker", "long_liquid_walker"),
            STRONG_LIQUID_WALKER    = reg(ModEffects.LIQUID_WALKER, dur_basic,          1, "liquid_walker", "strong_liquid_walker"),

            LONG_COOLDOWN           = reg(ModEffects.LONG_COOLDOWN, dur_basic,          0, "long_cooldown", "long_cooldown"),
            LONG_LONG_COOLDOWN      = reg(ModEffects.LONG_COOLDOWN, dur_long,           0, "long_cooldown", "long_long_cooldown"),

            LONG_LEG                = reg(ModEffects.LONG_LEG,      dur_basic,          0, "long_leg",      "long_leg"),
            LONG_LONG_LEG           = reg(ModEffects.LONG_LEG,      dur_long,           0, "long_leg",      "long_long_leg"),

            MASKING                 = reg(ModEffects.MASKING,       dur_instant,        0, "masking",       "masking"),

            NO_INTERACTION          = reg(ModEffects.NO_INTERACTION,dur_basic,          0, "no_interaction","no_interaction"),
            LONG_NO_INTERACTION     = reg(ModEffects.NO_INTERACTION,dur_long,           0, "no_interaction","long_no_interaction"),

            OBLIVION                = reg(ModEffects.OBLIVION,      dur_instant,        0, "oblivion",      "oblivion"),

            ORE_SENSE               = reg(ModEffects.ORE_SENSE,     dur_basic,          0, "ore_sense",     "ore_sense"),
            LONG_ORE_SENSE          = reg(ModEffects.ORE_SENSE,     dur_long,           0, "ore_sense",     "long_ore_sense"),
            STRONG_ORE_SENSE        = reg(ModEffects.ORE_SENSE,     dur_basic,          1, "ore_sense",     "strong_ore_sense"),

            PHOTOSYNTHESIS          = reg(ModEffects.PHOTOSYNTHESIS,dur_basic,          0, "photosynthesis","photosynthesis"),
            LONG_PHOTOSYNTHESIS     = reg(ModEffects.PHOTOSYNTHESIS,dur_long,           0, "photosynthesis","long_photosynthesis"),
            STRONG_PHOTOSYNTHESIS   = reg(ModEffects.PHOTOSYNTHESIS,dur_basic,          1, "photosynthesis","strong_photosynthesis"),

            PURIFICATION            = reg(ModEffects.PURIFICATION,  dur_basic,          0, "purification",  "purification"),
            LONG_PURIFICATION       = reg(ModEffects.PURIFICATION,  dur_long,           0, "purification",  "long_purification"),

            REACTIVATION            = reg(ModEffects.REACTIVATION,  dur_instant,          0, "reactivation",  "reactivation"),
            STRONG_REACTIVATION     = reg(ModEffects.REACTIVATION,  dur_instant,          1, "reactivation",  "strong_reactivation"),

            RESONANCE               = reg(ModEffects.RESONANCE,     dur_basic,          0, "resonance",     "resonance"),
            LONG_RESONANCE          = reg(ModEffects.RESONANCE,     dur_long,           0, "resonance",     "long_resonance"),
            STRONG_RESONANCE        = reg(ModEffects.RESONANCE,     dur_basic,          1, "resonance",     "strong_resonance"),

            INFINITY                = reg(ModEffects.INFINITY,      dur_instant,        0, "infinity",          "infinity"),

            RUST                    = reg(ModEffects.RUST,          dur_short,          0, "rust",          "rust"),
            LONG_RUST               = reg(ModEffects.RUST,          dur_basic,          0, "rust",          "long_rust"),

            SATURATION              = reg(ModEffects.SATURATION,    dur_short,          0, "saturation",    "saturation"),
            LONG_SATURATION         = reg(ModEffects.SATURATION,    dur_basic,          0, "saturation",    "long_saturation"),
            STRONG_SATURATION       = reg(ModEffects.SATURATION,    dur_short,          1, "saturation",    "strong_saturation"),

            SHORT_COOLDOWN          = reg(ModEffects.SHORT_COOLDOWN,dur_basic,          0, "short_cooldown","short_cooldown"),
            LONG_SHORT_COOLDOWN     = reg(ModEffects.SHORT_COOLDOWN,dur_long,           0, "short_cooldown","long_short_cooldown"),

            STUN                    = reg(ModEffects.STUN,          20 * 5,     0, "stun",          "stun"),
            LONG_STUN               = reg(ModEffects.STUN,          20 * 15,    0, "stun",          "long_stun"),

            TELEPORTATION           = reg(ModEffects.TELEPORTATION, dur_instant,        0, "teleportation", "teleportation"),
            STRONG_TELEPORTATION    = reg(ModEffects.TELEPORTATION, dur_instant,        1, "teleportation", "strong_teleportation"),

            THORNS                  = reg(ModEffects.THORNS,        dur_basic,          0, "thorns",        "thorns"),
            LONG_THORNS             = reg(ModEffects.THORNS,        dur_long,           0, "thorns",        "long_thorns"),
            STRONG_THORNS           = reg(ModEffects.THORNS,        dur_basic,          1, "thorns",        "strong_thorns"),

            UNSTABLE                = reg(ModEffects.UNSTABLE,      dur_instant,        0, "unstable",      "unstable"),
            STRONG_UNSTABLE         = reg(ModEffects.UNSTABLE,      dur_instant,        1, "unstable",      "strong_unstable"),

            VAMPIRISM               = reg(ModEffects.VAMPIRISM,     dur_basic,          0, "vampirism",     "vampirism"),
            LONG_VAMPIRISM          = reg(ModEffects.VAMPIRISM,     dur_long,           0, "vampirism",     "long_vampirism"),
            STRONG_VAMPIRISM        = reg(ModEffects.VAMPIRISM,     dur_basic,          1, "vampirism",     "strong_vampirism"),

            XP_BOOST                = reg(ModEffects.XP_BOOST,      dur_basic,          0, "xp_boost",      "xp_boost"),
            LONG_XP_BOOST           = reg(ModEffects.XP_BOOST,      dur_long,           0, "xp_boost",      "long_xp_boost"),
            STRONG_XP_BOOST         = reg(ModEffects.XP_BOOST,      dur_basic,          1, "xp_boost",      "strong_xp_boost"),

            XP_LIFE                 = reg(ModEffects.XP_LIFE,       dur_basic,          0, "xp_life",       "xp_life"),
            LONG_XP_LIFE            = reg(ModEffects.XP_LIFE,       dur_long,           0, "xp_life",       "long_xp_life");

    public static void register() {
        PotionsNRituals.LOGGER.info("Registering potions for " + PotionsNRituals.MOD_ID);

        DefaultItemComponentEvents.MODIFY.register(context -> context.modify(item ->
                        item == Items.POTION || item == Items.SPLASH_POTION || item == Items.LINGERING_POTION,
                (builder, _) -> builder.set(DataComponents.MAX_STACK_SIZE, max_potion_stack)
        ));

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FOOD_AND_DRINKS).register(output ->
                output.getDisplayStacks().removeIf(stack ->
                stack.getItem() instanceof PotionItem
        ));

        FabricPotionBrewingBuilder.BUILD.register(builder -> {
            // changement de base, mais moins efficace que de farmer des nether wart
            addMix(builder, Potions.WATER,                   ModItems.ALCHEMIST_CORE,    Potions.AWKWARD);

            // potions accessible early
            addMix(builder, Potions.AWKWARD,                 Items.MAGMA_BLOCK,          Potions.FIRE_RESISTANCE);
            addMix(builder, Potions.AWKWARD,                 Items.GOLDEN_APPLE,         Potions.REGENERATION);
            addMix(builder, Potions.AWKWARD,                 Items.FIREWORK_STAR,        Potions.STRENGTH);

            // potion custom
            addMix(builder, Potions.SLOW_FALLING,            Items.FEATHER,              ModPotions.LEVITATION);
            addMix(builder, Potions.NIGHT_VISION,            Items.GLOW_BERRIES,         ModPotions.GLOWING);
            addMix(builder, Potions.AWKWARD,                 Items.SWEET_BERRIES,        ModPotions.ALCOHOL);
            addMix(builder, Potions.AWKWARD,                 Items.INK_SAC,              ModPotions.DARKNESS);
            addMix(builder, Potions.AWKWARD,                 Items.BAMBOO,               ModPotions.LONG_LEG);
            addMix(builder, Potions.AWKWARD,                 Items.LILY_PAD,             ModPotions.LIQUID_WALKER);
            addMix(builder, Potions.NIGHT_VISION,            Items.IRON_ORE,             ModPotions.ORE_SENSE);
            addMix(builder, Potions.AWKWARD,                 Items.ECHO_SHARD,           ModPotions.RESONANCE);
            addMix(builder, ModPotions.SHORT_COOLDOWN,       Items.SUNFLOWER,            ModPotions.REACTIVATION);
            addMix(builder, Potions.AWKWARD,                 Items.MILK_BUCKET,          ModPotions.PURIFICATION);
            addMix(builder, Potions.TURTLE_MASTER,           Items.OBSIDIAN,             ModPotions.PETRIFICATION);
            addMix(builder, Potions.AWKWARD,                 Items.ROTTEN_FLESH,         ModPotions.ACID);
            addMix(builder, Potions.FIRE_RESISTANCE,         Items.FERMENTED_SPIDER_EYE, ModPotions.IGNITION);
            addMix(builder, Potions.AWKWARD,                 Items.ENDER_PEARL,          ModPotions.TELEPORTATION);
            addMix(builder, Potions.AWKWARD,                 Items.CACTUS,               ModPotions.THORNS);
            addMix(builder, Potions.AWKWARD,                 ModItems.ZOMBIE_BRAIN,      ModPotions.BRAINWASHING);
            addMix(builder, Potions.AWKWARD,                 Items.SNOWBALL,             ModPotions.FROST);
            addMix(builder, Potions.AWKWARD,                 Items.COPPER_INGOT,         ModPotions.ALCHEMIST);
            addMix(builder, ModPotions.RESURRECTION,         Items.WITHER_ROSE,          ModPotions.DEATH);
            addMix(builder, Potions.AWKWARD,                 Items.BEETROOT,             ModPotions.SATURATION);
            addMix(builder, Potions.STRONG_HEALING,          Items.GOLDEN_APPLE,         ModPotions.DOUBLE_HEALTH);
            addMix(builder, Potions.AWKWARD,                 Items.TOTEM_OF_UNDYING,     ModPotions.RESURRECTION);
            addMix(builder, Potions.AWKWARD,                 ModItems.CLAW,              ModPotions.HASTE);
            addMix(builder, ModPotions.HASTE,                Items.FERMENTED_SPIDER_EYE, ModPotions.MINING_FATIGUE);
            addMix(builder, Potions.AWKWARD,                 Items.SUNFLOWER,            ModPotions.SHORT_COOLDOWN);
            addMix(builder, ModPotions.SHORT_COOLDOWN,       Items.FERMENTED_SPIDER_EYE, ModPotions.LONG_COOLDOWN);
            addMix(builder, Potions.INVISIBILITY,            Items.FERMENTED_SPIDER_EYE, ModPotions.MASKING);
            addMix(builder, Potions.AWKWARD,                 ModItems.BLOOD_BAG,         ModPotions.VAMPIRISM);
            addMix(builder, Potions.AWKWARD,                 Items.TORCHFLOWER,          ModPotions.BERSERK);
            addMix(builder, Potions.AWKWARD,                 Items.BONE_MEAL,            ModPotions.GIANT);
            addMix(builder, ModPotions.GIANT,                Items.FERMENTED_SPIDER_EYE, ModPotions.DWARF);
            addMix(builder, Potions.AWKWARD,                 Items.LEAF_LITTER,          ModPotions.PHOTOSYNTHESIS);
            addMix(builder, Potions.AWKWARD,                 Items.PITCHER_PLANT,        ModPotions.OBLIVION);
            addMix(builder, Potions.AWKWARD,                 Items.GHAST_TEAR,           ModPotions.GHOST_WALK);
            addMix(builder, Potions.AWKWARD,                 ModItems.CHARGED_COPPER,    ModPotions.STUN);
            addMix(builder, Potions.AWKWARD,                 ModItems.OXYDATION,         ModPotions.RUST);
            addMix(builder, Potions.AWKWARD,                 Items.RESIN_CLUMP,          ModPotions.ADHESION);
            addMix(builder, ModPotions.BRAINWASHING,         Items.RABBIT_FOOT,          ModPotions.NO_INTERACTION);
            addMix(builder, Potions.AWKWARD,                 Items.LAPIS_LAZULI,         ModPotions.XP_BOOST);
            addMix(builder, ModPotions.XP_BOOST,             Items.FERMENTED_SPIDER_EYE, ModPotions.XP_LIFE);

            // version longue
            addMix(builder, ModPotions.LEVITATION,           Items.REDSTONE,             ModPotions.LONG_LEVITATION);
            addMix(builder, ModPotions.GLOWING,              Items.REDSTONE,             ModPotions.LONG_GLOWING);
            addMix(builder, ModPotions.ALCOHOL,              Items.REDSTONE,             ModPotions.LONG_ALCOHOL);
            addMix(builder, ModPotions.DARKNESS,             Items.REDSTONE,             ModPotions.LONG_DARKNESS);
            addMix(builder, ModPotions.LONG_LEG,             Items.REDSTONE,             ModPotions.LONG_LONG_LEG);
            addMix(builder, ModPotions.LIQUID_WALKER,        Items.REDSTONE,             ModPotions.LONG_LIQUID_WALKER);
            addMix(builder, ModPotions.ORE_SENSE,            Items.REDSTONE,             ModPotions.LONG_ORE_SENSE);
            addMix(builder, ModPotions.RESONANCE,            Items.REDSTONE,             ModPotions.LONG_RESONANCE);
            addMix(builder, ModPotions.PURIFICATION,         Items.REDSTONE,             ModPotions.LONG_PURIFICATION);
            addMix(builder, ModPotions.PETRIFICATION,        Items.REDSTONE,             ModPotions.LONG_PETRIFICATION);
            addMix(builder, ModPotions.ACID,                 Items.REDSTONE,             ModPotions.LONG_ACID);
            addMix(builder, ModPotions.IGNITION,             Items.REDSTONE,             ModPotions.LONG_IGNITION);
            addMix(builder, ModPotions.THORNS,               Items.REDSTONE,             ModPotions.LONG_THORNS);
            addMix(builder, ModPotions.BRAINWASHING,         Items.REDSTONE,             ModPotions.LONG_BRAINWASHING);
            addMix(builder, ModPotions.FROST,                Items.REDSTONE,             ModPotions.LONG_FROST);
            addMix(builder, ModPotions.SATURATION,           Items.REDSTONE,             ModPotions.LONG_SATURATION);
            addMix(builder, ModPotions.HASTE,                Items.REDSTONE,             ModPotions.LONG_HASTE);
            addMix(builder, ModPotions.MINING_FATIGUE,       Items.REDSTONE,             ModPotions.LONG_MINING_FATIGUE);
            addMix(builder, ModPotions.LONG_HASTE,           Items.FERMENTED_SPIDER_EYE, ModPotions.LONG_MINING_FATIGUE);
            addMix(builder, ModPotions.SHORT_COOLDOWN,       Items.REDSTONE,             ModPotions.LONG_SHORT_COOLDOWN);
            addMix(builder, ModPotions.LONG_COOLDOWN,        Items.REDSTONE,             ModPotions.LONG_LONG_COOLDOWN);
            addMix(builder, ModPotions.LONG_SHORT_COOLDOWN,  Items.FERMENTED_SPIDER_EYE, ModPotions.LONG_LONG_COOLDOWN);
            addMix(builder, ModPotions.VAMPIRISM,            Items.REDSTONE,             ModPotions.LONG_VAMPIRISM);
            addMix(builder, ModPotions.STUN,                 Items.REDSTONE,             ModPotions.LONG_STUN);
            addMix(builder, ModPotions.NO_INTERACTION,       Items.REDSTONE,             ModPotions.LONG_NO_INTERACTION);
            addMix(builder, ModPotions.BERSERK,              Items.REDSTONE,             ModPotions.LONG_BERSERK);
            addMix(builder, ModPotions.GHOST_WALK,           Items.REDSTONE,             ModPotions.LONG_GHOST_WALK);
            addMix(builder, ModPotions.GIANT,                Items.REDSTONE,             ModPotions.LONG_GIANT);
            addMix(builder, ModPotions.DWARF,                Items.REDSTONE,             ModPotions.LONG_DWARF);
            addMix(builder, ModPotions.PHOTOSYNTHESIS,       Items.REDSTONE,             ModPotions.LONG_PHOTOSYNTHESIS);
            addMix(builder, ModPotions.ADHESION,             Items.REDSTONE,             ModPotions.LONG_ADHESION);
            addMix(builder, ModPotions.RUST,                 Items.REDSTONE,             ModPotions.LONG_RUST);
            addMix(builder, ModPotions.XP_BOOST,             Items.REDSTONE,             ModPotions.LONG_XP_BOOST);
            addMix(builder, ModPotions.XP_LIFE,              Items.REDSTONE,             ModPotions.LONG_XP_LIFE);

            // version strong
            addMix(builder, ModPotions.ALCOHOL,              Items.GLOWSTONE,            ModPotions.STRONG_ALCOHOL);
            addMix(builder, ModPotions.ORE_SENSE,            Items.GLOWSTONE,            ModPotions.STRONG_ORE_SENSE);
            addMix(builder, ModPotions.RESONANCE,            Items.GLOWSTONE,            ModPotions.STRONG_RESONANCE);
            addMix(builder, ModPotions.REACTIVATION,         Items.GLOWSTONE,            ModPotions.STRONG_REACTIVATION);
            addMix(builder, ModPotions.ACID,                 Items.GLOWSTONE,            ModPotions.STRONG_ACID);
            addMix(builder, ModPotions.THORNS,               Items.GLOWSTONE,            ModPotions.STRONG_THORNS);
            addMix(builder, ModPotions.SATURATION,           Items.GLOWSTONE,            ModPotions.STRONG_SATURATION);
            addMix(builder, ModPotions.DOUBLE_HEALTH,        Items.GLOWSTONE,            ModPotions.STRONG_DOUBLE_HEALTH);
            addMix(builder, ModPotions.MINING_FATIGUE,       Items.GLOWSTONE,            ModPotions.STRONG_MINING_FATIGUE);
            addMix(builder, ModPotions.UNSTABLE,             Items.GLOWSTONE,            ModPotions.STRONG_UNSTABLE);
            addMix(builder, ModPotions.VAMPIRISM,            Items.GLOWSTONE,            ModPotions.STRONG_VAMPIRISM);
            addMix(builder, ModPotions.BERSERK,              Items.GLOWSTONE,            ModPotions.STRONG_BERSERK);
            addMix(builder, ModPotions.TELEPORTATION,        Items.GLOWSTONE,            ModPotions.STRONG_TELEPORTATION);
            addMix(builder, ModPotions.GIANT,                Items.GLOWSTONE,            ModPotions.STRONG_GIANT);
            addMix(builder, ModPotions.DWARF,                Items.GLOWSTONE,            ModPotions.STRONG_DWARF);
            addMix(builder, ModPotions.PHOTOSYNTHESIS,       Items.GLOWSTONE,            ModPotions.STRONG_PHOTOSYNTHESIS);
            addMix(builder, ModPotions.LIQUID_WALKER,        Items.GLOWSTONE,            ModPotions.STRONG_LIQUID_WALKER);
            addMix(builder, ModPotions.PETRIFICATION,        Items.GLOWSTONE,            ModPotions.STRONG_PETRIFICATION);
            addMix(builder, ModPotions.HASTE,                Items.GLOWSTONE,            ModPotions.STRONG_HASTE);
            addMix(builder, ModPotions.XP_BOOST,             Items.GLOWSTONE,            ModPotions.STRONG_XP_BOOST);
        });
    }

    private static void addMix(PotionBrewing.Builder builder,
                               Holder<Potion> input,
                               Item ingredient,
                               Holder<Potion> output) {
        if (isPotionBlacklisted(input) || isPotionBlacklisted(output)) return;
        builder.addMix(input, ingredient, output);
    }

    private static Holder<Potion> reg(Holder<MobEffect> effect,
                                      int duration,
                                      int amplifier,
                                      String name,
                                      String id) {
        return Registry.registerForHolder(
                BuiltInRegistries.POTION,
                Identifier.fromNamespaceAndPath(PotionsNRituals.MOD_ID, id),
                new Potion(name, new MobEffectInstance(effect, duration, amplifier))
        );
    }
}
