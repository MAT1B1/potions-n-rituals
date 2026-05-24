package com.matibi.potionsnrituals.config;

import com.matibi.potionsnrituals.effect.ModEffects;
import com.matibi.potionsnrituals.item.alchemicalStone.AlchemicalStone;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;

import java.util.List;
import java.util.Set;

public class ModConfig {
    public static List<Holder<MobEffect>> blacklist_effect;

    public static List<Holder<Potion>> blacklist_potions;

    public static List<Holder<AlchemicalStone>> blacklist_stone;

    public static float syringe_damage = 1.0f;
    public static int syringe_durability_loss = 1;
    public static int syringe_max_transfer_duration_ticks = 20 * 10;
    public static int syringe_durability = 20;

    public static int lingering_potion_cooldown = 3 * 20;
    public static int lingering_potion_short_cooldown = 20;
    public static int lingering_potion_long_cooldown = 10 * 20;

    public static int splash_potion_cooldown = 3 * 20;
    public static int splash_potion_short_cooldown = 20;
    public static int splash_potion_long_cooldown = 10 * 20;

    public static int blaze_replacer_fuel = 4;

    public static float ghost_y_movement = 0.15f;
    public static float ghost_movement_absorber = 0.5f;

    public static double fireball_speed = 1.5;
    public static int fireball_cooldown = 5 * 20;

    public static float vampirism_heal = 0.20f;
    public static float vampirism_heal_per_level = 0.10f;

    public static int max_potion_stack = 16;

    public static int dur_instant = 1;
    public static int dur_very_short = 20 * 10;
    public static int dur_short = 20 * 45;
    public static int dur_basic = 20 * 60 * 3;
    public static int dur_long = 20 * 60 * 8;
    public static int dur_inf = -1;

    public static int default_hit = 10;

    public static double perm_heal = 1.0D;
    public static double perm_speed = 0.01D;
    public static double perm_strength = 0.5D;

    public static int acid_radius = 1;
    public static int acid_depth = 1;
    public static int acid_radius_per_level = 1;
    public static int acid_depth_per_level = 1;

    public static int frost_radius = 1;
    public static int frost_radius_per_level = 1;

    public static int growth_max_tries = 4;
    public static int growth_max_tries_per_level = 4;

    public static int max_resurrection = 5;

    public static double horizontal_damping = 0.90D;
    public static double base_climb = 0.20D;
    public static double climb_per_level = 0.05D;

    public static float health_required_after_aftermath = 0.5f;

    public static float boss_death_damage = 0.5f;
    public static float undead_death_heal = 1.0f;
    public static float boss_death_backlash = 0.5f;
    public static float undead_death_backlash = 0.2f;

    public static Set<Holder<MobEffect>> infinity_blacklist = Set.of(
            ModEffects.RESONANCE
    );

    public static float inventory_forgotten = 0.25f;

    public static int tp_max_try = 50;

    public static int ore_scan_range = 5;
    public static int ore_scan_range_per_level = 3;
    public static int ore_scan_interval = 10;
    public static int coal_color = 0x2A2A2A;
    public static int iron_color = 0xD8A98A;
    public static int gold_color = 0xF0D020;
    public static int diamond_color = 0x40E0FF;
    public static int emerald_color = 0x00C060;
    public static int lapis_color = 0x1A3FBF;
    public static int redstone_color = 0xFF2010;
    public static int copper_color = 0xE07040;
    public static int ancient_debris_color = 0x6B3A2A;
    public static int quartz_color = 0xF0EEE8;
    public static int ore_color = 0xF0C838;

    public static int photosynthesis_food = 1;
    public static float photosynthesis_base_saturation = 0.2f;
    public static float photosynthesis_saturation_per_level = 0.1f;

    public static int resonance_radius = 4;
    public static int resonance_radius_per_level = 2;
    public static int orbit_points = 16;
    public static int pulse_points = 40;
    public static float scale_orbit = 0.45f;
    public static float scale_pulse = 0.65f;
    public static int pulse_period = 24;
    public static double orbit_speed_base = 0.08;

    public static float rust_damage = 0.001f;
    public static int rust_min_damage = 1;

    public static float base_saturation = 0.005f;
    public static float saturation_per_level = 0.005f;

    public static float base_thorns = 0.2f;
    public static float thorns_per_level = 0.1f;

    public static List<Holder<MobEffect>> unstable_pool = List.of(
            MobEffects.POISON,
            MobEffects.WEAKNESS,
            MobEffects.BLINDNESS,
            MobEffects.HUNGER,
            MobEffects.WITHER,
            MobEffects.BAD_OMEN,
            ModEffects.RESURRECTION
    );
    public static int unstable_effect_seconds = 8;
    public static int unstable_effect_seconds_per_level = 8;
    public static float unstable_explosion_power = 2.5f;
    public static float unstable_explosion_power_per_level = 1.0f;
    public static float unstable_explosion_chance = 0.30f;
    public static float unstable_explosion_chance_per_level = 0.20f;

    public static int reactivation_duration = 2;

    public static float alpha_max = 0.85f;
    public static float lerp_speed = 0.08f;
    public static float thick = 0.15f;

    public static double health_per_effect = 4.0D;

    public static int pregnancy_duration = 20 * 60 * 20;
    public static double max_distance_pregnancy = 8.0;

    public static int drop_percentage = 30;

    public static int min_cough_time = 1;
    public static int max_cough_time = 10;

    public static boolean isPotionBlacklisted(Holder<Potion> potion) {
        if (blacklist_potions.contains(potion)) return true;
        return potion.value().getEffects().stream()
                .anyMatch(instance -> isEffectBlacklisted(instance.getEffect()));
    }

    public static boolean isStoneBlacklisted(Holder<AlchemicalStone> stone) {
        if (blacklist_stone.contains(stone)) return true;
        return blacklist_effect.contains(stone.value().effect());
    }

    public static boolean isEffectBlacklisted(Holder<MobEffect> effect) {
        return blacklist_effect.contains(effect);
    }

    public static void register() {
        blacklist_effect = List.of(
                ModEffects.BRAINWASHING
        );

        blacklist_potions = List.of();

        blacklist_stone = List.of();
    }
}
