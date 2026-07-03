package com.matibi.potionsnrituals.datagen.language;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider.TranslationBuilder;

public class ModConfigLanguageHelper {

    public static void generateConfigFrFrTranslations(TranslationBuilder t) {
        // Écran ModConfig — Titre & Onglets
        t.add("title.potions-n-rituals.config", "Configuration de Potions & Rituals");
        t.add("category.potions-n-rituals.potions_and_items", "Potions & Objets");
        t.add("category.potions-n-rituals.mob_effects", "Effets de statut");
        t.add("category.potions-n-rituals.active_effects", "Effets Actifs");

        // Écran ModConfig — Groupes d'options
        t.add("group.potions-n-rituals.lingering_potion_cooldown", "Cooldown des potions persistantes");
        t.add("group.potions-n-rituals.splash_potion_cooldown", "Cooldown des potions jetables");
        t.add("group.potions-n-rituals.brewing_stand_alternative_fuel", "Carburant alternatif d'alambic");
        t.add("group.potions-n-rituals.potion_stacks", "Piles de potions");
        t.add("group.potions-n-rituals.potion_duration", "Durée des potions");
        t.add("group.potions-n-rituals.imbued_effect_hit", "Nombre de coups imprégnés");
        t.add("group.potions-n-rituals.effect_combination", "Combinaison d'effets");
        t.add("group.potions-n-rituals.permanent_potion", "Potions permanentes");

        // Écran ModConfig — Options (ONGLET 1)
        t.add("option.potions-n-rituals.syringe.damage", "Dégâts");
        t.add("option.potions-n-rituals.syringe.durability_loss", "Perte de durabilité");
        t.add("option.potions-n-rituals.syringe.max_transfer_duration", "Durée max de transfert (ticks)");
        t.add("option.potions-n-rituals.syringe.durability", "Durabilité max");

        t.add("option.potions-n-rituals.lingering_potion_cooldown.cooldown", "Cooldown de base (ticks)");
        t.add("option.potions-n-rituals.lingering_potion_cooldown.short_cooldown", "Cooldown court (ticks)");
        t.add("option.potions-n-rituals.lingering_potion_cooldown.long_cooldown", "Cooldown long (ticks)");

        t.add("option.potions-n-rituals.splash_potion_cooldown.cooldown", "Cooldown de base (ticks)");
        t.add("option.potions-n-rituals.splash_potion_cooldown.short_cooldown", "Cooldown court (ticks)");
        t.add("option.potions-n-rituals.splash_potion_cooldown.long_cooldown", "Cooldown long (ticks)");

        t.add("option.potions-n-rituals.brewing_stand_alternative_fuel.fuel", "Unités de carburant");
        t.add("option.potions-n-rituals.potion_stacks.max_stack_size", "Taille max des piles");

        t.add("option.potions-n-rituals.potion_duration.instant", "Instantanée (ticks)");
        t.add("option.potions-n-rituals.potion_duration.very_short", "Très courte (ticks)");
        t.add("option.potions-n-rituals.potion_duration.short", "Courte (ticks)");
        t.add("option.potions-n-rituals.potion_duration.basic", "Normale (ticks)");
        t.add("option.potions-n-rituals.potion_duration.long", "Longue (ticks)");
        t.add("option.potions-n-rituals.potion_duration.infinite", "Infinie (-1 = infini)");

        t.add("option.potions-n-rituals.imbued_effect_hit.default_hit", "Coups par défaut");
        t.add("option.potions-n-rituals.effect_combination.max_diff", "Différence max de niveau");

        t.add("option.potions-n-rituals.permanent_potion.perm_heal", "Soin permanent");
        t.add("option.potions-n-rituals.permanent_potion.perm_speed", "Vitesse permanente");
        t.add("option.potions-n-rituals.permanent_potion.perm_strength", "Force permanente");

        // Écran ModConfig — Options (ONGLET 2)
        t.add("option.potions-n-rituals.ghost_walk.y_movement", "Mouvement vertical (Y)");
        t.add("option.potions-n-rituals.ghost_walk.movement_absorber", "Amortisseur de mouvement");
        t.add("option.potions-n-rituals.ignition.fireball_speed", "Vitesse des boules de feu");
        t.add("option.potions-n-rituals.ignition.fireball_cooldown", "Cooldown des boules de feu (ticks)");

        t.add("option.potions-n-rituals.vampirism.heal", "Soin de base");
        t.add("option.potions-n-rituals.vampirism.heal_per_level", "Soin par niveau");

        t.add("option.potions-n-rituals.acid.radius", "Rayon");
        t.add("option.potions-n-rituals.acid.depth", "Profondeur");
        t.add("option.potions-n-rituals.acid.radius_per_level", "Rayon par niveau");
        t.add("option.potions-n-rituals.acid.depth_per_level", "Profondeur par niveau");

        t.add("option.potions-n-rituals.frost.radius", "Rayon");
        t.add("option.potions-n-rituals.frost.radius_per_level", "Rayon par niveau");

        t.add("option.potions-n-rituals.giant.max_tries", "Essais max");
        t.add("option.potions-n-rituals.giant.max_tries_per_level", "Essais max par niveau");
        t.add("option.potions-n-rituals.resurrection.max_resurrections", "Nombre max de résurrections");

        t.add("option.potions-n-rituals.adhesion.horizontal_damping", "Amortissement horizontal");
        t.add("option.potions-n-rituals.adhesion.base_climb", "Vitesse d'escalade de base");
        t.add("option.potions-n-rituals.adhesion.climb_per_level", "Vitesse d'escalade par niveau");

        t.add("option.potions-n-rituals.aftermath.health_required_after", "Santé requise après coup (0-1)");

        t.add("option.potions-n-rituals.death.boss_death_damage", "Dégâts à la mort d'un boss");
        t.add("option.potions-n-rituals.death.undead_death_heal", "Soin à la mort d'un mort-vivant");
        t.add("option.potions-n-rituals.death.boss_death_backlash", "Dégats renvoyés par les boss (%)");
        t.add("option.potions-n-rituals.death.undead_death_backlash", "Dégats renvoyés par les mort-vivants (%)");

        t.add("option.potions-n-rituals.oblivion.inventory_forgotten_chance", "Chance de perte d'inventaire");
        t.add("option.potions-n-rituals.oblivion.teleport_max_tries", "Essais max de téléportation");

        t.add("option.potions-n-rituals.ore_sense.scan_range", "Portée du scan");
        t.add("option.potions-n-rituals.ore_sense.scan_range_per_level", "Portée du scan par niveau");
        t.add("option.potions-n-rituals.ore_sense.scan_interval", "Intervalle du scan (ticks)");
        t.add("option.potions-n-rituals.ore_sense.coal_color", "Couleur du charbon");
        t.add("option.potions-n-rituals.ore_sense.iron_color", "Couleur du fer");
        t.add("option.potions-n-rituals.ore_sense.gold_color", "Couleur de l'or");
        t.add("option.potions-n-rituals.ore_sense.diamond_color", "Couleur du diamant");
        t.add("option.potions-n-rituals.ore_sense.emerald_color", "Couleur de l'émeraude");
        t.add("option.potions-n-rituals.ore_sense.lapis_color", "Couleur du lapis-lazuli");
        t.add("option.potions-n-rituals.ore_sense.redstone_color", "Couleur de la redstone");
        t.add("option.potions-n-rituals.ore_sense.copper_color", "Couleur du cuivre");
        t.add("option.potions-n-rituals.ore_sense.ancient_debris_color", "Couleur des débris antiques");
        t.add("option.potions-n-rituals.ore_sense.quartz_color", "Couleur du quartz");
        t.add("option.potions-n-rituals.ore_sense.generic_ore_color", "Couleur des autres minerais");
        t.add("option.potions-n-rituals.ore_sense.alpha_max", "Opacité maximale (Alpha)");
        t.add("option.potions-n-rituals.ore_sense.lerp_speed", "Vitesse de transition (Lerp)");
        t.add("option.potions-n-rituals.ore_sense.thickness", "Épaisseur du contour");

        t.add("option.potions-n-rituals.photosynthesis.food", "Nourriture fournie");
        t.add("option.potions-n-rituals.photosynthesis.base_saturation", "Saturation de base");
        t.add("option.potions-n-rituals.photosynthesis.saturation_per_level", "Saturation par niveau");

        t.add("option.potions-n-rituals.resonance.radius", "Rayon");
        t.add("option.potions-n-rituals.resonance.radius_per_level", "Rayon par niveau");
        t.add("option.potions-n-rituals.resonance.orbit_points", "Points d'orbite");
        t.add("option.potions-n-rituals.resonance.pulse_points", "Points d'impulsion");
        t.add("option.potions-n-rituals.resonance.scale_orbit", "Échelle de l'orbite");
        t.add("option.potions-n-rituals.resonance.scale_pulse", "Échelle de l'impulsion");
        t.add("option.potions-n-rituals.resonance.pulse_period", "Période d'impulsion");
        t.add("option.potions-n-rituals.resonance.orbit_speed_base", "Vitesse d'orbite de base");

        t.add("option.potions-n-rituals.rust.damage", "Dégâts de rouille");
        t.add("option.potions-n-rituals.rust.min_damage", "Dégâts minimums");

        t.add("option.potions-n-rituals.saturation.base", "Saturation de base");
        t.add("option.potions-n-rituals.saturation.per_level", "Saturation par niveau");

        t.add("option.potions-n-rituals.thorns.base", "Dégâts d'épines de base");
        t.add("option.potions-n-rituals.thorns.per_level", "Dégâts d'épines par niveau");

        t.add("option.potions-n-rituals.unstable.effect_seconds", "Durée de l'effet (secondes)");
        t.add("option.potions-n-rituals.unstable.effect_seconds_per_level", "Durée de l'effet par niveau");
        t.add("option.potions-n-rituals.unstable.explosion_power", "Puissance de l'explosion");
        t.add("option.potions-n-rituals.unstable.explosion_power_per_level", "Puissance de l'explosion par niveau");
        t.add("option.potions-n-rituals.unstable.explosion_chance", "Chance d'explosion");
        t.add("option.potions-n-rituals.unstable.explosion_chance_per_level", "Chance d'explosion par niveau");

        t.add("option.potions-n-rituals.reactivation.duration", "Durée de réactivation");
        t.add("option.potions-n-rituals.infinity.max_health_lost", "Vie max perdue par effet");

        t.add("option.potions-n-rituals.pregnancy.duration", "Durée de grossesse (ticks)");
        t.add("option.potions-n-rituals.pregnancy.max_distance", "Distance maximale");

        t.add("option.potions-n-rituals.clumsiness.drop_percentage", "Pourcentage de chance de lâcher");

        t.add("option.potions-n-rituals.cold.min_cough_time", "Temps de toux minimum");
        t.add("option.potions-n-rituals.cold.max_cough_time", "Temps de toux maximum");

        t.add("option.potions-n-rituals.empathy.damage", "Dégâts");
        t.add("option.potions-n-rituals.empathy.damage_per_level", "Dégâts par niveau");

        t.add("option.potions-n-rituals.hydrophobia.damage", "Dégâts");
        t.add("option.potions-n-rituals.hydrophobia.damage_per_level", "Dégâts par niveau");
        t.add("option.potions-n-rituals.hydrophobia.potion_damage", "Dégâts des potions");
        t.add("option.potions-n-rituals.hydrophobia.potion_damage_per_level", "Dégâts des potions par niveau");

        t.add("option.potions-n-rituals.magnetism.range", "Portée");
        t.add("option.potions-n-rituals.magnetism.range_per_level", "Portée par niveau");
        t.add("option.potions-n-rituals.magnetism.pull_strength", "Force d'attraction");

        t.add("option.potions-n-rituals.zombie_contagion.duration", "Durée (ticks)");
        t.add("option.potions-n-rituals.reality_check.max_slowdown", "Ralentissement max");

        // Écran ModConfig — Options des effets actifs (ONGLET 3)
        t.add("option.potions-n-rituals.active_effect.medusa_range", "Portée de Méduse");
        t.add("option.potions-n-rituals.active_effect.medusa_range_per_level", "Portée de Méduse par niveau");
        t.add("option.potions-n-rituals.active_effect.medusa_short_cooldown", "Cooldown court de Méduse (ticks)");
        t.add("option.potions-n-rituals.active_effect.medusa_cooldown", "Cooldown de Méduse (ticks)");
        t.add("option.potions-n-rituals.active_effect.medusa_long_cooldown", "Cooldown long de Méduse (ticks)");
        t.add("option.potions-n-rituals.active_effect.teleport_range", "Portée de téléportation");
        t.add("option.potions-n-rituals.active_effect.teleport_range_per_level", "Portée de téléportation par niveau");
        t.add("option.potions-n-rituals.active_effect.teleport_short_cooldown", "Cooldown court téléportation (ticks)");
        t.add("option.potions-n-rituals.active_effect.teleport_cooldown", "Cooldown téléportation (ticks)");
        t.add("option.potions-n-rituals.active_effect.teleport_long_cooldown", "Cooldown long téléportation (ticks)");
        t.add("option.potions-n-rituals.active_effect.zeus_range", "Portée de Zeus");
        t.add("option.potions-n-rituals.active_effect.zeus_range_per_level", "Portée de Zeus par niveau");
        t.add("option.potions-n-rituals.active_effect.zeus_short_cooldown", "Cooldown court de Zeus (ticks)");
        t.add("option.potions-n-rituals.active_effect.zeus_cooldown", "Cooldown de Zeus (ticks)");
        t.add("option.potions-n-rituals.active_effect.zeus_long_cooldown", "Cooldown long de Zeus (ticks)");
    }

    public static void generateConfigEnUsTranslations(TranslationBuilder t) {
        // ModConfig Screen — Title & Tabs
        t.add("title.potions-n-rituals.config", "Potions & Rituals");
        t.add("category.potions-n-rituals.potions_and_items", "Potions & Items");
        t.add("category.potions-n-rituals.mob_effects", "Mob Effects");
        t.add("category.potions-n-rituals.active_effects", "Active Effects");

        // ModConfig Screen — Option Groups
        t.add("group.potions-n-rituals.lingering_potion_cooldown", "Lingering Potion Cooldown");
        t.add("group.potions-n-rituals.splash_potion_cooldown", "Splash Potion Cooldown");
        t.add("group.potions-n-rituals.brewing_stand_alternative_fuel", "Brewing Stand Alternative Fuel");
        t.add("group.potions-n-rituals.potion_stacks", "Potion Stacks");
        t.add("group.potions-n-rituals.potion_duration", "Potion duration");
        t.add("group.potions-n-rituals.imbued_effect_hit", "Imbued Effect Hit");
        t.add("group.potions-n-rituals.effect_combination", "Effect Combination");
        t.add("group.potions-n-rituals.permanent_potion", "Permanent Potion");

        // ModConfig Screen — Options (TAB 1)
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

        // ModConfig Screen — Options (TAB 2)
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
        t.add("option.potions-n-rituals.reality_check.max_slowdown", "Max Slowdown");

        // ModConfig Screen — Active Effect Options (TAB 3)
        t.add("option.potions-n-rituals.active_effect.medusa_range", "Medusa Range");
        t.add("option.potions-n-rituals.active_effect.medusa_range_per_level", "Medusa Range Per Level");
        t.add("option.potions-n-rituals.active_effect.medusa_short_cooldown", "Medusa Short Cooldown (ticks)");
        t.add("option.potions-n-rituals.active_effect.medusa_cooldown", "Medusa Cooldown (ticks)");
        t.add("option.potions-n-rituals.active_effect.medusa_long_cooldown", "Medusa Long Cooldown (ticks)");
        t.add("option.potions-n-rituals.active_effect.teleport_range", "Teleport Range");
        t.add("option.potions-n-rituals.active_effect.teleport_range_per_level", "Teleport Range Per Level");
        t.add("option.potions-n-rituals.active_effect.teleport_short_cooldown", "Teleport Short Cooldown (ticks)");
        t.add("option.potions-n-rituals.active_effect.teleport_cooldown", "Teleport Cooldown (ticks)");
        t.add("option.potions-n-rituals.active_effect.teleport_long_cooldown", "Teleport Long Cooldown (ticks)");
        t.add("option.potions-n-rituals.active_effect.zeus_range", "Zeus Range");
        t.add("option.potions-n-rituals.active_effect.zeus_range_per_level", "Zeus Range Per Level");
        t.add("option.potions-n-rituals.active_effect.zeus_short_cooldown", "Zeus Short Cooldown (ticks)");
        t.add("option.potions-n-rituals.active_effect.zeus_cooldown", "Zeus Cooldown (ticks)");
        t.add("option.potions-n-rituals.active_effect.zeus_long_cooldown", "Zeus Long Cooldown (ticks)");
    }
}