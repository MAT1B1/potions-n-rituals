package com.matibi.potionsnrituals.config;

import com.matibi.potionsnrituals.effect.ModEffects;
import com.matibi.potionsnrituals.item.custom.alchemicalStone.AlchemicalStone;
import com.matibi.potionsnrituals.util.ModUtils;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;

import java.util.List;
import java.util.Set;

public class ModConfig {

    public static final ConfigClassHandler<ModConfig> HANDLER = ConfigClassHandler.createBuilder(ModConfig.class)
            .id(ModUtils.id("config"))
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(FabricLoader.getInstance().getConfigDir().resolve("potionsnrituals.json"))
                    .build())
            .build();

    // ── Non-serialized (runtime only) ──────────────────────────────────────────────────
    public List<Holder<MobEffect>> blacklist_effect;
    public List<Holder<Potion>> blacklist_potions;
    public List<Holder<AlchemicalStone>> blacklist_stone;

    public Set<Holder<MobEffect>> infinity_blacklist = Set.of(ModEffects.RESONANCE);

    public List<Holder<MobEffect>> unstable_pool = List.of(
            MobEffects.POISON, MobEffects.WEAKNESS, MobEffects.BLINDNESS,
            MobEffects.HUNGER, MobEffects.WITHER, MobEffects.BAD_OMEN,
            ModEffects.RESURRECTION
    );

    // ── Potions & Items ─────────────────────────────────────────────────────────────────

    // ── Syringe ───────────────────────────────────────────────────────────────
    @SerialEntry public float syringe_damage = 1.0f;
    @SerialEntry public int syringe_durability_loss = 1;
    @SerialEntry public int syringe_max_transfer_duration_ticks = 20 * 10;
    @SerialEntry public int syringe_durability = 20;

    // ── Lingering Potion Cooldown ─────────────────────────────────────────────
    @SerialEntry public int lingering_potion_cooldown = 3 * 20;
    @SerialEntry public int lingering_potion_short_cooldown = 20;
    @SerialEntry public int lingering_potion_long_cooldown = 10 * 20;

    // ── Splash Potion Cooldown ────────────────────────────────────────────────
    @SerialEntry public int splash_potion_cooldown = 3 * 20;
    @SerialEntry public int splash_potion_short_cooldown = 20;
    @SerialEntry public int splash_potion_long_cooldown = 10 * 20;

    // ── Brewing Stand Alternative Fuel ────────────────────────────────────────
    @SerialEntry public int blaze_replacer_fuel = 4;

    // ── Potion Stacks ─────────────────────────────────────────────────────────
    @SerialEntry public int max_potion_stack = 16;

    // ── Potion duration ───────────────────────────────────────────────────────
    @SerialEntry public int dur_instant = 1;
    @SerialEntry public int dur_very_short = 20 * 10;
    @SerialEntry public int dur_short = 20 * 45;
    @SerialEntry public int dur_basic = 20 * 60 * 3;
    @SerialEntry public int dur_long = 20 * 60 * 8;
    @SerialEntry public int dur_inf = -1;

    // ── Imbued Effect Hit ─────────────────────────────────────────────────────
    @SerialEntry public int default_hit = 10;

    // ── Effect Combination ────────────────────────────────────────────────────
    @SerialEntry public int max_diff = 1;

    // ── Permanent Potion ──────────────────────────────────────────────────────
    @SerialEntry public double perm_heal = 1.0D;
    @SerialEntry public double perm_speed = 0.01D;
    @SerialEntry public double perm_strength = 0.5D;

    // ── Misc ────────────────────────────────────────────────────────────────────────────

    @SerialEntry public int nether_ghost_duration = 20 * 10;

    @SerialEntry public int pocket_dimension_width = 3;
    @SerialEntry public int pocket_dimension_length = 3;
    @SerialEntry public int pocket_dimension_height = 3;

    // ── Mob Effect ──────────────────────────────────────────────────────────────────────

    // ── Ghost Walk ────────────────────────────────────────────────────────────
    @SerialEntry public float ghost_y_movement = 0.15f;
    @SerialEntry public float ghost_movement_absorber = 0.5f;

    // ── Ignition ──────────────────────────────────────────────────────────────
    @SerialEntry public double fireball_speed = 1.5;
    @SerialEntry public int fireball_cooldown = 5 * 20;

    // ── Vampirism ─────────────────────────────────────────────────────────────
    @SerialEntry public float vampirism_heal = 0.20f;
    @SerialEntry public float vampirism_heal_per_level = 0.10f;

    // ── Empathy ───────────────────────────────────────────────────────────────
    @SerialEntry public float empathy_dmg = 0.30f;
    @SerialEntry public float empathy_dmg_per_level = 0.20f;

    // ── Acid ──────────────────────────────────────────────────────────────────
    @SerialEntry public int acid_radius = 1;
    @SerialEntry public int acid_depth = 1;
    @SerialEntry public int acid_radius_per_level = 1;
    @SerialEntry public int acid_depth_per_level = 1;

    // ── Frost ─────────────────────────────────────────────────────────────────
    @SerialEntry public int frost_radius = 1;
    @SerialEntry public int frost_radius_per_level = 1;

    // ── Giant ─────────────────────────────────────────────────────────────────
    @SerialEntry public int growth_max_tries = 4;
    @SerialEntry public int growth_max_tries_per_level = 4;

    // ── Resurrection ──────────────────────────────────────────────────────────
    @SerialEntry public int max_resurrection = 5;

    // ── Adhesion ──────────────────────────────────────────────────────────────
    @SerialEntry public double horizontal_damping = 0.90D;
    @SerialEntry public double base_climb = 0.20D;
    @SerialEntry public double climb_per_level = 0.05D;

    // ── Aftermath ─────────────────────────────────────────────────────────────
    @SerialEntry public float health_required_after_aftermath = 0.5f;

    // ── Death ─────────────────────────────────────────────────────────────────
    @SerialEntry public float boss_death_damage = 0.5f;
    @SerialEntry public float undead_death_heal = 1.0f;
    @SerialEntry public float boss_death_backlash = 0.5f;
    @SerialEntry public float undead_death_backlash = 0.2f;

    // ── Oblivion ──────────────────────────────────────────────────────────────
    @SerialEntry public float inventory_forgotten = 0.25f;
    @SerialEntry public int tp_max_try = 50;

    // ── Ore Sense ─────────────────────────────────────────────────────────────
    @SerialEntry public int ore_scan_range = 5;
    @SerialEntry public int ore_scan_range_per_level = 3;
    @SerialEntry public int ore_scan_interval = 10;
    @SerialEntry public int coal_color = 0x2A2A2A;
    @SerialEntry public int iron_color = 0xD8A98A;
    @SerialEntry public int gold_color = 0xF0D020;
    @SerialEntry public int diamond_color = 0x40E0FF;
    @SerialEntry public int emerald_color = 0x00C060;
    @SerialEntry public int lapis_color = 0x1A3FBF;
    @SerialEntry public int redstone_color = 0xFF2010;
    @SerialEntry public int copper_color = 0xE07040;
    @SerialEntry public int ancient_debris_color = 0x6B3A2A;
    @SerialEntry public int quartz_color = 0xF0EEE8;
    @SerialEntry public int ore_color = 0xF0C838;
    @SerialEntry public float alpha_max = 0.85f;
    @SerialEntry public float lerp_speed = 0.08f;
    @SerialEntry public float thick = 0.15f;

    // ── Photosynthesis ────────────────────────────────────────────────────────
    @SerialEntry public int photosynthesis_food = 1;
    @SerialEntry public float photosynthesis_base_saturation = 0.2f;
    @SerialEntry public float photosynthesis_saturation_per_level = 0.1f;

    // ── Resonance ─────────────────────────────────────────────────────────────
    @SerialEntry public int resonance_radius = 4;
    @SerialEntry public int resonance_radius_per_level = 2;
    @SerialEntry public int orbit_points = 16;
    @SerialEntry public int pulse_points = 40;
    @SerialEntry public float scale_orbit = 0.45f;
    @SerialEntry public float scale_pulse = 0.65f;
    @SerialEntry public int pulse_period = 24;
    @SerialEntry public double orbit_speed_base = 0.08;

    // ── Rust ──────────────────────────────────────────────────────────────────
    @SerialEntry public float rust_damage = 0.001f;
    @SerialEntry public int rust_min_damage = 1;

    // ── Saturation ────────────────────────────────────────────────────────────
    @SerialEntry public float base_saturation = 0.005f;
    @SerialEntry public float saturation_per_level = 0.005f;

    // ── Thorns ────────────────────────────────────────────────────────────────
    @SerialEntry public float base_thorns = 0.2f;
    @SerialEntry public float thorns_per_level = 0.1f;

    // ── Unstable ──────────────────────────────────────────────────────────────
    @SerialEntry public int unstable_effect_seconds = 8;
    @SerialEntry public int unstable_effect_seconds_per_level = 8;
    @SerialEntry public float unstable_explosion_power = 2.5f;
    @SerialEntry public float unstable_explosion_power_per_level = 1.0f;
    @SerialEntry public float unstable_explosion_chance = 0.30f;
    @SerialEntry public float unstable_explosion_chance_per_level = 0.20f;

    // ── Reactivation ──────────────────────────────────────────────────────────
    @SerialEntry public int reactivation_duration = 2;

    // ── Infinity ──────────────────────────────────────────────────────────────
    @SerialEntry public double max_health_lost_per_effect = 4.0D;

    // ── Pregnancy ─────────────────────────────────────────────────────────────
    @SerialEntry public int pregnancy_duration = 20 * 60 * 20;
    @SerialEntry public double max_distance_pregnancy = 8.0;

    // ── Clumsiness ────────────────────────────────────────────────────────────
    @SerialEntry public int drop_percentage = 30;

    // ── Hydrophobia ───────────────────────────────────────────────────────────
    @SerialEntry public float hydrophobia_damage = 1.0f;
    @SerialEntry public float hydrophobia_damage_per_level = 0.5f;
    @SerialEntry public float hydrophobia_potion_damage = 4.0f;
    @SerialEntry public float hydrophobia_potion_damage_per_level = 2.0f;

    // ── Magnetism ─────────────────────────────────────────────────────────────
    @SerialEntry public float magnetism_range = 5.0f;
    @SerialEntry public float magnetism_range_per_level = 5.0f;
    @SerialEntry public double magnetism_pull_strength = 0.1D;

    // ── Zombie Contagion ──────────────────────────────────────────────────────
    @SerialEntry public int zombie_contagion_duration = 20 * 60 * 10;

    // ── Cough ─────────────────────────────────────────────────────────────────
    @SerialEntry public int min_cough_time = 1;
    @SerialEntry public int max_cough_time = 10;

    // ── Reality Check ──────────────────────────────────────────────────────────
    @SerialEntry public double reality_check_max_slowdown = 0.7D;

    // ── Active Effect ───────────────────────────────────────────────────────────────
    @SerialEntry public double medusa_range = 20.0;
    @SerialEntry public double medusa_range_per_level = 5.0;
    @SerialEntry public int medusa_short_cooldown = 20 * 5;
    @SerialEntry public int medusa_cooldown = 20 * 10;
    @SerialEntry public int medusa_long_cooldown = 20 * 20;

    @SerialEntry public double active_teleport_range = 64.0;
    @SerialEntry public double active_teleport_range_per_level = 16.0;
    @SerialEntry public int active_teleport_short_cooldown = 20;
    @SerialEntry public int active_teleport_cooldown = 20 * 5;
    @SerialEntry public int active_teleport_long_cooldown = 20 * 10;

    @SerialEntry public double zeus_range = 64.0;
    @SerialEntry public double zeus_range_per_level = 16.0;
    @SerialEntry public int zeus_short_cooldown = 20;
    @SerialEntry public int zeus_cooldown = 20 * 5;
    @SerialEntry public int zeus_long_cooldown = 20 * 10;

    // ── Static accessor (replaces old static fields) ────────────────────────────────────
    public static ModConfig get() {
        return HANDLER.instance();
    }

    // ── Helper methods ──────────────────────────────────────────────────────────────────
    public boolean isPotionBlacklisted(Holder<Potion> potion) {
        if (blacklist_potions.contains(potion)) return true;
        return potion.value().getEffects().stream()
                .anyMatch(instance -> isEffectBlacklisted(instance.getEffect()));
    }

    public boolean isStoneBlacklisted(Holder<AlchemicalStone> stone) {
        if (blacklist_stone.contains(stone)) return true;
        return blacklist_effect.contains(stone.value().effect());
    }

    public boolean isEffectBlacklisted(Holder<MobEffect> effect) {
        return blacklist_effect.contains(effect);
    }

    public static void register() {
        HANDLER.load();

        ModConfig cfg = get();
        cfg.blacklist_effect = List.of(ModEffects.BRAINWASHING);
        cfg.blacklist_potions = List.of();
        cfg.blacklist_stone = List.of();
    }
}