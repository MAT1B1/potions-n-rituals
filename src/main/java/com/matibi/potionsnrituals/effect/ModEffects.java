package com.matibi.potionsnrituals.effect;

import com.matibi.potionsnrituals.PotionsNRituals;
import com.matibi.potionsnrituals.effect.custom.AdhesionEffect;
import com.matibi.potionsnrituals.effect.custom.AftermathEffect;
import com.matibi.potionsnrituals.effect.custom.BerserkEffect;
import com.matibi.potionsnrituals.effect.custom.brainwashing.BrainwashingEffect;
import com.matibi.potionsnrituals.effect.custom.DeathEffect;
import com.matibi.potionsnrituals.effect.custom.DoubleHealthEffect;
import com.matibi.potionsnrituals.effect.custom.DwarfEffect;
import com.matibi.potionsnrituals.effect.custom.terrain.FrostEffect;
import com.matibi.potionsnrituals.effect.custom.GhostWalkEffect;
import com.matibi.potionsnrituals.effect.custom.InfinityEffect;
import com.matibi.potionsnrituals.effect.custom.LiquidWalkerEffect;
import com.matibi.potionsnrituals.effect.custom.LongCooldownEffect;
import com.matibi.potionsnrituals.effect.custom.LongLegEffect;
import com.matibi.potionsnrituals.effect.custom.MaskingEffect;
import com.matibi.potionsnrituals.effect.custom.NoInteractionEffect;
import com.matibi.potionsnrituals.effect.custom.OblivionEffect;
import com.matibi.potionsnrituals.effect.custom.OreSenseEffect;
import com.matibi.potionsnrituals.effect.custom.PhotosynthesisEffect;
import com.matibi.potionsnrituals.effect.custom.PurificationEffect;
import com.matibi.potionsnrituals.effect.custom.ReactivationEffect;
import com.matibi.potionsnrituals.effect.custom.ResonanceEffect;
import com.matibi.potionsnrituals.effect.custom.RustEffect;
import com.matibi.potionsnrituals.effect.custom.SaturationEffect;
import com.matibi.potionsnrituals.effect.custom.ShortCooldownEffect;
import com.matibi.potionsnrituals.effect.custom.StunEffect;
import com.matibi.potionsnrituals.effect.custom.TeleportationEffect;
import com.matibi.potionsnrituals.effect.custom.ThornsEffect;
import com.matibi.potionsnrituals.effect.custom.UnstableEffect;
import com.matibi.potionsnrituals.effect.custom.VampirismEffect;
import com.matibi.potionsnrituals.effect.custom.XpBoostEffect;
import com.matibi.potionsnrituals.effect.custom.permanent.PermanentHealthEffect;
import com.matibi.potionsnrituals.effect.custom.permanent.PermanentSpeedEffect;
import com.matibi.potionsnrituals.effect.custom.permanent.PermanentStrengthEffect;
import com.matibi.potionsnrituals.effect.custom.terrain.*;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;

public class ModEffects {

    public static final Holder<MobEffect>
            // ── PERMANENT ─────────────────────────────────────────────────────────────
            PERM_HEALTH     = reg("permanent_health",   new PermanentHealthEffect()),
            PERM_SPEED      = reg("permanent_speed",    new PermanentSpeedEffect()),
            PERM_STRENGTH   = reg("permanent_strength", new PermanentStrengthEffect()),

            // ── TERRAIN ───────────────────────────────────────────────────────────────
            ACID            = reg("acid",               new AcidEffect()),
            ALCHEMIST       = reg("alchemist",         new AlchemistEffect()),
            GIANT           = reg("giant",              new GiantEffect()),
            IGNITION        = reg("ignition",           new IgnitionEffect()),
            PETRIFICATION   = reg("petrification",      new PetrificationEffect()),
            RESURRECTION    = reg("resurrection",       new ResurrectionEffect()),


            // ── NORMAL ────────────────────────────────────────────────────────────────
            ADHESION        = reg("adhesion",           new AdhesionEffect()),
            AFTERMATH       = reg("aftermath",          new AftermathEffect()),
            BERSERK         = reg("berserk",            new BerserkEffect()),
            BRAINWASHING    = reg("brainwashing",       new BrainwashingEffect()),
            DEATH           = reg("death",              new DeathEffect()),
            DOUBLE_HEALTH   = reg("double_health",      new DoubleHealthEffect()),
            DWARF           = reg("dwarf",              new DwarfEffect()),
            FROST           = reg("frost",              new FrostEffect()),
            GHOST_WALK      = reg("ghost_walk",         new GhostWalkEffect()),
            LIQUID_WALKER   = reg("liquid_walker",      new LiquidWalkerEffect()),
            LONG_COOLDOWN   = reg("long_cooldown",      new LongCooldownEffect()),
            LONG_LEG        = reg("long_leg",           new LongLegEffect()),
            MASKING         = reg("masking",            new MaskingEffect()),
            NO_INTERACTION  = reg("no_interaction",     new NoInteractionEffect()),
            OBLIVION        = reg("oblivion",           new OblivionEffect()),
            ORE_SENSE       = reg("ore_sense",          new OreSenseEffect()),
            PHOTOSYNTHESIS  = reg("photosynthesis",     new PhotosynthesisEffect()),
            PURIFICATION    = reg("purification",       new PurificationEffect()),
            REACTIVATION    = reg("reactivation",       new ReactivationEffect()),
            RESONANCE       = reg("resonance",          new ResonanceEffect()),
            INFINITY        = reg("infinity",           new InfinityEffect()),
            RUST            = reg("rust",               new RustEffect()),
            SATURATION      = reg("saturation",         new SaturationEffect()),
            SHORT_COOLDOWN  = reg("short_cooldown",     new ShortCooldownEffect()),
            STUN            = reg("stun",               new StunEffect()),
            TELEPORTATION   = reg("teleportation",      new TeleportationEffect()),
            THORNS          = reg("thorns",             new ThornsEffect()),
            UNSTABLE        = reg("unstable",           new UnstableEffect()),
            VAMPIRISM       = reg("vampirism",          new VampirismEffect()),
            XP_BOOST        = reg("xp_boost",           new XpBoostEffect());

    private static Holder<MobEffect> reg(String name, MobEffect effect) {
        return Registry.registerForHolder(
                BuiltInRegistries.MOB_EFFECT,
                Identifier.fromNamespaceAndPath(PotionsNRituals.MOD_ID, name),
                effect
        );
    }

    public static void register() {
        PotionsNRituals.LOGGER.info("registering mod effects for " + PotionsNRituals.MOD_ID);
    }
}
