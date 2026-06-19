package com.matibi.potionsnrituals.effect;

import com.matibi.potionsnrituals.PotionsNRituals;
import com.matibi.potionsnrituals.effect.custom.*;
import com.matibi.potionsnrituals.effect.custom.active.ActiveTeleportationEffect;
import com.matibi.potionsnrituals.effect.custom.active.LoveEffect;
import com.matibi.potionsnrituals.effect.custom.active.MedusaBenedictionEffect;
import com.matibi.potionsnrituals.effect.custom.active.ZeusBenedictionEffect;
import com.matibi.potionsnrituals.effect.custom.brainwashing.BrainwashingEffect;
import com.matibi.potionsnrituals.effect.custom.permanent.PermanentHealthEffect;
import com.matibi.potionsnrituals.effect.custom.permanent.PermanentSpeedEffect;
import com.matibi.potionsnrituals.effect.custom.permanent.PermanentStrengthEffect;
import com.matibi.potionsnrituals.effect.custom.terrain.*;
import com.matibi.potionsnrituals.util.ModUtils;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;

public class ModEffects {

    public static final Holder<MobEffect>
            // ── PERMANENT ─────────────────────────────────────────────────────────────
            PERM_HEALTH         = reg("perm_health",   new PermanentHealthEffect()),
            PERM_SPEED          = reg("perm_speed",    new PermanentSpeedEffect()),
            PERM_STRENGTH       = reg("perm_strength", new PermanentStrengthEffect()),

            // ── TERRAIN ───────────────────────────────────────────────────────────────
            ACID                = reg("acid",               new AcidEffect()),
            ALCHEMIST           = reg("alchemist",          new AlchemistEffect()),
            GIANT               = reg("giant",              new GiantEffect()),
            IGNITION            = reg("ignition",           new IgnitionEffect()),
            PETRIFICATION       = reg("petrification",      new PetrificationEffect()),
            RESURRECTION        = reg("resurrection",       new ResurrectionEffect()),


            // ── NORMAL ────────────────────────────────────────────────────────────────
            ADHESION            = reg("adhesion",           new AdhesionEffect()),
            AFTERMATH           = reg("aftermath",          new AftermathEffect()),
            BERSERK             = reg("berserk",            new BerserkEffect()),
            BRAINWASHING        = reg("brainwashing",       new BrainwashingEffect()),
            DEATH               = reg("death",              new DeathEffect()),
            DOUBLE_HEALTH       = reg("double_health",      new DoubleHealthEffect()),
            DWARF               = reg("dwarf",              new DwarfEffect()),
            FROST               = reg("frost",              new FrostEffect()),
            GHOST_WALK          = reg("ghost_walk",         new GhostWalkEffect()),
            LIQUID_WALKER       = reg("liquid_walker",      new LiquidWalkerEffect()),
            LONG_COOLDOWN       = reg("long_cooldown",      new LongCooldownEffect()),
            LONG_LEG            = reg("long_leg",           new LongLegEffect()),
            LOVE                = reg("love",               new LoveEffect()),
            MASKING             = reg("masking",            new MaskingEffect()),
            NO_INTERACTION      = reg("no_interaction",     new NoInteractionEffect()),
            OBLIVION            = reg("oblivion",           new OblivionEffect()),
            ORE_SENSE           = reg("ore_sense",          new OreSenseEffect()),
            PHOTOSYNTHESIS      = reg("photosynthesis",     new PhotosynthesisEffect()),
            PREGNANT            = reg("pregnant",           new PregnantEffect()),
            PURIFICATION        = reg("purification",       new PurificationEffect()),
            REACTIVATION        = reg("reactivation",       new ReactivationEffect()),
            RESONANCE           = reg("resonance",          new ResonanceEffect()),
            INFINITY            = reg("infinity",           new InfinityEffect()),
            RUST                = reg("rust",               new RustEffect()),
            SATURATION          = reg("saturation",         new SaturationEffect()),
            SHORT_COOLDOWN      = reg("short_cooldown",     new ShortCooldownEffect()),
            STUN                = reg("stun",               new StunEffect()),
            TELEPORTATION       = reg("teleportation",      new TeleportationEffect()),
            THORNS              = reg("thorns",             new ThornsEffect()),
            UNSTABLE            = reg("unstable",           new UnstableEffect()),
            VAMPIRISM           = reg("vampirism",          new VampirismEffect()),
            XP_BOOST            = reg("xp_boost",           new XpBoostEffect()),
            XP_REDUCTION        = reg("xp_reduction",       new XpReductionEffect()),
            XP_LIFE             = reg("xp_life",            new XpLifeEffect()),
            ZEUS_BENEDICTION    = reg("zeus",               new ZeusBenedictionEffect()),
            MEDUSA_BENEDICTION  = reg("medusa",             new MedusaBenedictionEffect()),
            ACTIVE_TELEPORTATION = reg("active_teleportation", new ActiveTeleportationEffect()),
            CLUMSINESS          = reg("clumsiness",         new ClumsinessEffect()),
            COLD                = reg("cold",               new ColdEffect()),
            ASTHMA              = reg("asthma",             new AsthmaEffect()),
            PARANOIA            = reg("paranoia",           new ParanoiaEffect()),
            HYDROPHOBIA         = reg("hydrophobia",        new HydrophobiaEffect()),
            ZOMBIE_CONTAGION    = reg("zombie_contagion",   new ZombieContagionEffect()),
            EMPATHY             = reg("empathy",            new EmpathyEffect()),
            MAGNETISM           = reg("magnetism",          new MagnetismEffect()),
            MIDAS_BENEDICTION   = reg("midas",              new MidasBenedictionEffect());

    private static Holder<MobEffect> reg(String name, MobEffect effect) {
        return Registry.registerForHolder(
                BuiltInRegistries.MOB_EFFECT,
                ModUtils.id(name),
                effect
        );
    }

    public static void register() {
        PotionsNRituals.LOGGER.info("registering mod effects for " + PotionsNRituals.MOD_ID);
    }
}
