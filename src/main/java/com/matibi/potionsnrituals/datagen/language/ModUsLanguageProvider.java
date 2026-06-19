package com.matibi.potionsnrituals.datagen.language;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ModUsLanguageProvider extends FabricLanguageProvider {

    private final Map<String, String> additionalTranslations;

    public ModUsLanguageProvider(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        this(dataOutput, registryLookup, Map.of());
    }

    public ModUsLanguageProvider(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup, Map<String, String> additionalTranslations) {
        super(dataOutput, "en_us", registryLookup);
        this.additionalTranslations = additionalTranslations;
    }

    @Override
    public void generateTranslations(HolderLookup.@NonNull Provider wrapperLookup, @NonNull TranslationBuilder t) {

        // Potions & Effect
        registerVanilla(t, "levitation", "Levitation");
        registerVanilla(t, "glowing", "Glowing");
        registerVanilla(t, "darkness", "Darkness");
        registerVanilla(t, "haste", "Haste");
        registerVanilla(t, "mining_fatigue", "Mining Fatigue");

        t.add("item.minecraft.potion.effect.alcohol", "Alcohol");
        t.add("item.minecraft.splash_potion.effect.alcohol", "Splash Alcohol");
        t.add("item.minecraft.lingering_potion.effect.alcohol", "Lingering Alcohol");
        t.add("item.minecraft.tipped_arrow.effect.alcohol", "Arrow of Alcohol");

        register(t, "saturation", "Saturation");
        register(t, "long_leg", "Long Legs");
        register(t, "liquid_walker", "Liquid Walker");
        register(t, "ore_sense", "Ore Sense");
        register(t, "resonance", "Resonance");
        register(t, "reactivation", "Reactivation");
        register(t, "purification", "Purification");
        register(t, "teleportation", "Teleportation");
        register(t, "thorns", "Thorns");
        register(t, "brainwashing", "Brain Washing");
        register(t, "death", "Death");
        register(t, "double_health", "Double Health");
        register(t, "infinity", "Infinity");
        register(t, "long_cooldown", "Long Cooldown");
        register(t, "short_cooldown", "Short Cooldown");
        register(t, "love", "Love");
        register(t, "masking", "Masking");
        registerReverse(t, "unstable", "Unstable");
        register(t, "vampirism", "Vampirism");
        register(t, "stun", "Stun");
        register(t, "no_interaction", "Hands Bound");
        register(t, "aftermath", "Aftermath");
        register(t, "berserk", "Beserk");
        register(t, "ghost_walk", "Ghost Walk");
        register(t, "dwarf", "Dwarfism");
        register(t, "photosynthesis", "Photosynthesis");
        register(t, "pregnant", "Pregnant");
        register(t, "oblivion", "Oblivion");
        register(t, "adhesion", "Adhesion");
        register(t, "rust", "Rust");
        register(t, "xp_boost", "XP Boost");
        register(t, "xp_life", "XP Life");
        register(t, "zeus", "Zeus Benediction");
        register(t, "medusa", "Medusa Benediction");
        register(t, "midas", "Midas Benediction");
        register(t, "xp_reduction", "XP Reduction");
        register(t, "clumsiness", "Clumsiness");
        register(t, "cold", "Cold");
        register(t, "asthma", "Asthma");
        register(t, "paranoia", "Paranoia");
        register(t, "hydrophobia", "Hydrophobia");
        register(t, "zombie_contagion", "Zombie Contagion");
        register(t, "empathy", "Empathy");
        register(t, "magnetism", "Magnetism");

        registerWithAlchemicalStone(t, "giant", "Giant");
        registerWithAlchemicalStone(t, "resurrection", "Resurrection");
        registerWithAlchemicalStone(t, "petrification", "Petrification");
        registerWithAlchemicalStone(t, "acid", "Acidity");
        registerWithAlchemicalStone(t, "ignition", "Ignition");
        registerWithAlchemicalStone(t, "alchemist", "Alchemist");
        registerWithAlchemicalStone(t, "frost", "Frost");

        registerElixir(t, "perm_health", "Health");
        registerElixir(t, "perm_speed", "Speed");
        registerElixir(t, "perm_strength", "Strength");

        t.add("item.potions-n-rituals.poisonous_carrot", "Poisonous Carrot");
        t.add("item.potions-n-rituals.poisonous_beetroot", "Poisonous Beetroot");
        t.add("item.potions-n-rituals.materia_prima", "Materia Prima");
        t.add("item.potions-n-rituals.claw", "Claw");
        t.add("item.potions-n-rituals.zombie_brain", "Zombie Brain");
        t.add("item.potions-n-rituals.leaf", "Leaf");
        t.add("item.potions-n-rituals.witch_finger", "Witch's Finger");
        t.add("item.potions-n-rituals.blood_bag", "Blood Bag");
        t.add("item.potions-n-rituals.oxydation", "Oxidation Fragment");
        t.add("item.potions-n-rituals.charged_copper", "Charged Copper");
        t.add("item.potions-n-rituals.sulfur_ball", "Sulfur Ball");
        t.add("item.potions-n-rituals.mercury_ball", "Mercury Ball");
        t.add("item.potions-n-rituals.salt", "Salt");
        t.add("item.potions-n-rituals.alchemical_stone", "Alchemical Stone");
        t.add("item.potions-n-rituals.alchemical_stone.effect.empty", "Alchemical Stone");
        t.add("item.potions-n-rituals.alchemical_stone.effect.mixed", "Multi-effect Alchemical Stone");
        registerVanillaReverse(t, "mixed", "Multi-effect");

		t.add("item.potions-n-rituals.syringe", "Syringe");
		t.add("item.potions-n-rituals.syringe.effect.isEmpty", "Syringe");

		t.add("block.potions-n-rituals.brewing_cauldron", "Brewing Cauldron");

        BuiltInRegistries.MOB_EFFECT.entrySet().forEach(entry -> {
            Identifier id = BuiltInRegistries.MOB_EFFECT.getKey(entry.getValue());
            if (id == null || !id.getNamespace().equals("minecraft")) return;
            String key = "item.potions-n-rituals.syringe." + entry.getValue().getDescriptionId();
            t.add(key, "Syringe of " + entry.getValue().getDescriptionId());
        });

        t.add("potions-n-rituals.blood_type.unknown", "Unknown");
        t.add("potions-n-rituals.blood_type.human", "Human Blood");
        t.add("potions-n-rituals.blood_type.monster", "Monster Blood");

        t.add("item.potions-n-rituals.alchemical_stone.block_only", "Runes can only be used on blocks");
        t.add("item.potions-n-rituals.alchemical_stone.block_not_good", "The block is not compatible");
        t.add("itemGroup.potions-n-rituals.alchemy", "Alchemy");

        t.add("advancements.potions-n-rituals.root.title", "Potions & Rituals");
        t.add("advancements.potions-n-rituals.root.description", "The art of alchemy");
        t.add("advancements.potions-n-rituals.nigredo.title", "Nigredo");
        t.add("advancements.potions-n-rituals.nigredo.description", "Delve into chaos and create the Primordial Matter");
        t.add("advancements.potions-n-rituals.albedo.title", "Albedo");
        t.add("advancements.potions-n-rituals.albedo.description", "Wash away impurities and brew your first potions");
        t.add("advancements.potions-n-rituals.citrinitas.title", "Citrinitas");
        t.add("advancements.potions-n-rituals.citrinitas.description", "Channel Nether energies to forge your first talismans and artifacts");
        t.add("advancements.potions-n-rituals.rubedo.title", "Rubedo");
        t.add("advancements.potions-n-rituals.rubedo.description", "Achieve absolute perfection by performing great worldly rituals");

        t.add("splash.potionsnrituals.magic", "Alchemy is power!!!");
        t.add("splash.potionsnrituals.thanks", "Thank you for supporting me LivelyBadGood");
        t.add("tooltip.potions-n-rituals.imbued_line", "%s (%s hits remaining)");
        t.add("key.potions-n-rituals.show_effect_icons", "Show Effect Icons");
        t.add("key.potions-n-rituals.effect_button", "Effect Button");
        t.add("key.category.potions-n-rituals.main", "Potions & Rituals");

        //ModConfig Screen — Title & Tabs
        t.add("title.potions-n-rituals.config", "Potions & Rituals");
        t.add("category.potions-n-rituals.potions_and_items", "Potions & Items");
        t.add("category.potions-n-rituals.mob_effects", "Mob Effects");

        //ModConfig Screen — Option Groups
        t.add("group.potions-n-rituals.lingering_potion_cooldown", "Lingering Potion Cooldown");
        t.add("group.potions-n-rituals.splash_potion_cooldown", "Splash Potion Cooldown");
        t.add("group.potions-n-rituals.brewing_stand_alternative_fuel", "Brewing Stand Alternative Fuel");
        t.add("group.potions-n-rituals.potion_stacks", "Potion Stacks");
        t.add("group.potions-n-rituals.potion_duration", "Potion duration");
        t.add("group.potions-n-rituals.imbued_effect_hit", "Imbued Effect Hit");
        t.add("group.potions-n-rituals.effect_combination", "Effect Combination");
        t.add("group.potions-n-rituals.permanent_potion", "Permanent Potion");

        //ModConfig Screen — Options (TAB 1)
        t.add("option.potions-n-rituals.syringe.damage", "Damage");
        t.add("option.potions-n-rituals.syringe.durability_loss", "Durability Loss");
        t.add("option.potions-n-rituals.syringe.max_transfer_duration", "Max Transfer Duration (ticks)");
        t.add("option.potions-n-rituals.syringe.durability", "Durability");

        t.add("option.potions-n-rituals.lingering_potion_cooldown.cooldown", "Cooldown (ticks)");
        t.add("option.potions-n-rituals.lingering_potion_cooldown.short_cooldown", "Short Cooldown (ticks)");
        t.add("option.potions-n-rituals.lingering_potion_cooldown.long_cooldown", "Long Cooldown (ticks)");

        t.add("option.potions-n-rituals.splash_potion_cooldown.cooldown", "Cooldown (ticks)");
        t.add("option.potions-n-rituals.splash_potion_cooldown.short_cooldown", "Short Cooldown (ticks)");
        t.add("option.potions-n-rituals.splash_potion_cooldown.long_cooldown", "Long Cooldown (ticks)");

        t.add("option.potions-n-rituals.brewing_stand_alternative_fuel.fuel", "Fuel");

        t.add("option.potions-n-rituals.potion_stacks.max_stack_size", "Max Stack Size");

        t.add("option.potions-n-rituals.potion_duration.instant", "Instant (ticks)");
        t.add("option.potions-n-rituals.potion_duration.very_short", "Very Short (ticks)");
        t.add("option.potions-n-rituals.potion_duration.short", "Short (ticks)");
        t.add("option.potions-n-rituals.potion_duration.basic", "Basic (ticks)");
        t.add("option.potions-n-rituals.potion_duration.long", "Long (ticks)");
        t.add("option.potions-n-rituals.potion_duration.infinite", "Infinite (-1 = inf)");

        t.add("option.potions-n-rituals.imbued_effect_hit.default_hit", "Default Hit");

        t.add("option.potions-n-rituals.effect_combination.max_diff", "Max Diff");

        t.add("option.potions-n-rituals.permanent_potion.perm_heal", "Perm Heal");
        t.add("option.potions-n-rituals.permanent_potion.perm_speed", "Perm Speed");
        t.add("option.potions-n-rituals.permanent_potion.perm_strength", "Perm Strength");

        //ModConfig Screen — Options (TAB 2)
        t.add("option.potions-n-rituals.ghost_walk.y_movement", "Y Movement");
        t.add("option.potions-n-rituals.ghost_walk.movement_absorber", "Movement Absorber");

        t.add("option.potions-n-rituals.ignition.fireball_speed", "Fireball Speed");
        t.add("option.potions-n-rituals.ignition.fireball_cooldown", "Fireball Cooldown (ticks)");

        t.add("option.potions-n-rituals.vampirism.heal", "Heal");
        t.add("option.potions-n-rituals.vampirism.heal_per_level", "Heal Per Level");

        t.add("option.potions-n-rituals.acid.radius", "Radius");
        t.add("option.potions-n-rituals.acid.depth", "Depth");
        t.add("option.potions-n-rituals.acid.radius_per_level", "Radius Per Level");
        t.add("option.potions-n-rituals.acid.depth_per_level", "Depth Per Level");

        t.add("option.potions-n-rituals.frost.radius", "Radius");
        t.add("option.potions-n-rituals.frost.radius_per_level", "Radius Per Level");

        t.add("option.potions-n-rituals.giant.max_tries", "Max Tries");
        t.add("option.potions-n-rituals.giant.max_tries_per_level", "Max Tries Per Level");

        t.add("option.potions-n-rituals.resurrection.max_resurrections", "Max Resurrections");

        t.add("option.potions-n-rituals.adhesion.horizontal_damping", "Horizontal Damping");
        t.add("option.potions-n-rituals.adhesion.base_climb", "Base Climb");
        t.add("option.potions-n-rituals.adhesion.climb_per_level", "Climb Per Level");

        t.add("option.potions-n-rituals.aftermath.health_required_after", "Health Required After (0-1)");

        t.add("option.potions-n-rituals.death.boss_death_damage", "Boss Death Damage");
        t.add("option.potions-n-rituals.death.undead_death_heal", "Undead Death Heal");
        t.add("option.potions-n-rituals.death.boss_death_backlash", "Boss Death Backlash");
        t.add("option.potions-n-rituals.death.undead_death_backlash", "Undead Death Backlash");

        t.add("option.potions-n-rituals.oblivion.inventory_forgotten_chance", "Inventory Forgotten Chance");
        t.add("option.potions-n-rituals.oblivion.teleport_max_tries", "Teleport Max Tries");

        t.add("option.potions-n-rituals.ore_sense.scan_range", "Scan Range");
        t.add("option.potions-n-rituals.ore_sense.scan_range_per_level", "Scan Range Per Level");
        t.add("option.potions-n-rituals.ore_sense.scan_interval", "Scan Interval (ticks)");
        t.add("option.potions-n-rituals.ore_sense.coal_color", "Coal Color");
        t.add("option.potions-n-rituals.ore_sense.iron_color", "Iron Color");
        t.add("option.potions-n-rituals.ore_sense.gold_color", "Gold Color");
        t.add("option.potions-n-rituals.ore_sense.diamond_color", "Diamond Color");
        t.add("option.potions-n-rituals.ore_sense.emerald_color", "Emerald Color");
        t.add("option.potions-n-rituals.ore_sense.lapis_color", "Lapis Color");
        t.add("option.potions-n-rituals.ore_sense.redstone_color", "Redstone Color");
        t.add("option.potions-n-rituals.ore_sense.copper_color", "Copper Color");
        t.add("option.potions-n-rituals.ore_sense.ancient_debris_color", "Ancient Debris Color");
        t.add("option.potions-n-rituals.ore_sense.quartz_color", "Quartz Color");
        t.add("option.potions-n-rituals.ore_sense.generic_ore_color", "Generic Ore Color");
        t.add("option.potions-n-rituals.ore_sense.alpha_max", "Alpha Max");
        t.add("option.potions-n-rituals.ore_sense.lerp_speed", "Lerp Speed");
        t.add("option.potions-n-rituals.ore_sense.thickness", "Thickness");

        t.add("option.potions-n-rituals.photosynthesis.food", "Food");
        t.add("option.potions-n-rituals.photosynthesis.base_saturation", "Base Saturation");
        t.add("option.potions-n-rituals.photosynthesis.saturation_per_level", "Saturation Per Level");

        t.add("option.potions-n-rituals.resonance.radius", "Radius");
        t.add("option.potions-n-rituals.resonance.radius_per_level", "Radius Per Level");
        t.add("option.potions-n-rituals.resonance.orbit_points", "Orbit Points");
        t.add("option.potions-n-rituals.resonance.pulse_points", "Pulse Points");
        t.add("option.potions-n-rituals.resonance.scale_orbit", "Scale Orbit");
        t.add("option.potions-n-rituals.resonance.scale_pulse", "Scale Pulse");
        t.add("option.potions-n-rituals.resonance.pulse_period", "Pulse Period");
        t.add("option.potions-n-rituals.resonance.orbit_speed_base", "Orbit Speed Base");

        t.add("option.potions-n-rituals.rust.damage", "Damage");
        t.add("option.potions-n-rituals.rust.min_damage", "Min Damage");

        t.add("option.potions-n-rituals.saturation.base", "Base");
        t.add("option.potions-n-rituals.saturation.per_level", "Per Level");

        t.add("option.potions-n-rituals.thorns.base", "Base");
        t.add("option.potions-n-rituals.thorns.per_level", "Per Level");

        t.add("option.potions-n-rituals.unstable.effect_seconds", "Effect Seconds");
        t.add("option.potions-n-rituals.unstable.effect_seconds_per_level", "Effect Seconds Per Level");
        t.add("option.potions-n-rituals.unstable.explosion_power", "Explosion Power");
        t.add("option.potions-n-rituals.unstable.explosion_power_per_level", "Explosion Power Per Level");
        t.add("option.potions-n-rituals.unstable.explosion_chance", "Explosion Chance");
        t.add("option.potions-n-rituals.unstable.explosion_chance_per_level", "Explosion Chance Per Level");

        t.add("option.potions-n-rituals.reactivation.duration", "Duration");

        t.add("option.potions-n-rituals.infinity.max_health_lost", "Max Health Lost Per Effect");

        t.add("option.potions-n-rituals.pregnancy.duration", "Duration (ticks)");
        t.add("option.potions-n-rituals.pregnancy.max_distance", "Max Distance");

        t.add("option.potions-n-rituals.clumsiness.drop_percentage", "Drop Percentage");

        t.add("option.potions-n-rituals.cold.min_cough_time", "Min Cough Time");
        t.add("option.potions-n-rituals.cold.max_cough_time", "Max Cough Time");

        t.add("option.potions-n-rituals.empathy.damage", "Damage");
        t.add("option.potions-n-rituals.empathy.damage_per_level", "Damage Per Level");

        t.add("option.potions-n-rituals.hydrophobia.damage", "Damage");
        t.add("option.potions-n-rituals.hydrophobia.damage_per_level", "Damage Per Level");
        t.add("option.potions-n-rituals.hydrophobia.potion_damage", "Potion Damage");
        t.add("option.potions-n-rituals.hydrophobia.potion_damage_per_level", "Potion Damage Per Level");

        t.add("option.potions-n-rituals.magnetism.range", "Range");
        t.add("option.potions-n-rituals.magnetism.range_per_level", "Range Per Level");
        t.add("option.potions-n-rituals.magnetism.pull_strength", "Pull Strength");

        t.add("option.potions-n-rituals.zombie_contagion.duration", "Duration (ticks)");

        //ModConfig Screen — Active Effect Options
        t.add("group.potions-n-rituals.active_effect", "Active Effect");
        t.add("option.potions-n-rituals.active_effect.medusa_range", "Medusa Range");
        t.add("option.potions-n-rituals.active_effect.teleport_range", "Teleport Range");
        t.add("option.potions-n-rituals.active_effect.zeus_range", "Zeus Range");
        t.add("option.potions-n-rituals.active_effect.zeus_range_per_level", "Zeus Range Per Level");
        t.add("option.potions-n-rituals.active_effect.zeus_short_cooldown", "Zeus Short Cooldown (ticks)");
        t.add("option.potions-n-rituals.active_effect.zeus_cooldown", "Zeus Cooldown (ticks)");
        t.add("option.potions-n-rituals.active_effect.zeus_long_cooldown", "Zeus Long Cooldown (ticks)");
        t.add("option.potions-n-rituals.active_effect.teleport_range_per_level", "Teleport Range Per Level");
        t.add("option.potions-n-rituals.active_effect.teleport_short_cooldown", "Teleport Short Cooldown (ticks)");
        t.add("option.potions-n-rituals.active_effect.teleport_cooldown", "Teleport Cooldown (ticks)");
        t.add("option.potions-n-rituals.active_effect.teleport_long_cooldown", "Teleport Long Cooldown (ticks)");

        t.add("effect.potions-n-rituals.active_teleportation", "Active Teleportation");
        t.add("item.minecraft.potion.effect.active_teleportation", "Potion of Active Teleportation");
        t.add("item.minecraft.splash_potion.effect.active_teleportation", "Splash Potion of Active Teleportation");
        t.add("item.minecraft.lingering_potion.effect.active_teleportation", "Lingering Potion of Active Teleportation");
        t.add("item.minecraft.tipped_arrow.effect.active_teleportation", "Arrow of Active Teleportation");
        t.add("item.potions-n-rituals.syringe.effect.potions-n-rituals.active_teleportation", "Syringe of Active Teleportation");
        t.add("option.potions-n-rituals.active_effect.medusa_range_per_level", "Medusa Range Per Level");
        t.add("option.potions-n-rituals.active_effect.medusa_short_cooldown", "Medusa Short Cooldown (ticks)");
        t.add("option.potions-n-rituals.active_effect.medusa_cooldown", "Medusa Cooldown (ticks)");
        t.add("option.potions-n-rituals.active_effect.medusa_long_cooldown", "Medusa Long Cooldown (ticks)");

        this.additionalTranslations.forEach(t::add);
    }

    private void registerWithAlchemicalStone(TranslationBuilder t, String id, String name) {
        register(t, id, name);
        t.add("item.potions-n-rituals.alchemical_stone.effect." + id, name + " Stone");
    }

    private void registerVanilla(TranslationBuilder t, String id, String name) {
        t.add("item.minecraft.potion.effect." + id, "Potion of " + name);
        t.add("item.minecraft.splash_potion.effect." + id, "Splash Potion of " + name);
        t.add("item.minecraft.lingering_potion.effect." + id, "Lingering Potion of " + name);
        t.add("item.minecraft.tipped_arrow.effect." + id, "Arrow of " + name);
    }

    private void registerVanillaReverse(TranslationBuilder t, String id, String name) {
        t.add("item.minecraft.potion.effect." + id, name + " Potion");
        t.add("item.minecraft.splash_potion.effect." + id, name + " Splash Potion");
        t.add("item.minecraft.lingering_potion.effect." + id, name + " Lingering Potion");
        t.add("item.minecraft.tipped_arrow.effect." + id, name + " Arrow");
        t.add("item.potions-n-rituals.syringe.effect." + id, name + " Syringe");
    }

    private void registerReverse(TranslationBuilder t, String id, String name) {
        registerVanillaReverse(t, id, name);
        t.add("effect.potions-n-rituals." + id, name);
    }

    private void register(TranslationBuilder t, String id, String name) {
        registerVanilla(t, id, name);
        t.add("effect.potions-n-rituals." + id, name);
        t.add("item.potions-n-rituals.syringe.effect.potions-n-rituals." + id, "Syringe of " + name);
    }

    private void registerElixir(TranslationBuilder t, String id, String name) {
        t.add("effect.potions-n-rituals." + id, "Permanent " + name);
        t.add("item.minecraft.potion.effect." + id, "Permanent "+ name + " Elixir");
        t.add("item.minecraft.splash_potion.effect." + id, "Permanent "+ name + " Splash Elixir");
        t.add("item.minecraft.lingering_potion.effect." + id, "Permanent "+ name + " Lingering Elixir");
        t.add("item.minecraft.tipped_arrow.effect." + id, "Permanent "+ name + " Arrow");
    }
}