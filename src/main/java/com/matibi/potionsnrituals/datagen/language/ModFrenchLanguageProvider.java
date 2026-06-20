package com.matibi.potionsnrituals.datagen.language;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ModFrenchLanguageProvider extends FabricLanguageProvider {

    private final Map<String, String> additionalTranslations;

    public ModFrenchLanguageProvider(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        this(dataOutput, registryLookup, Map.of());
    }

    public ModFrenchLanguageProvider(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup, Map<String, String> additionalTranslations) {
        super(dataOutput, "fr_fr", registryLookup);
        this.additionalTranslations = additionalTranslations;
    }

    @Override
    public void generateTranslations(HolderLookup.@NonNull Provider wrapperLookup, @NonNull TranslationBuilder t) {
        registerVanilla(t, "levitation", "de Lévitation");
        registerVanilla(t, "glowing", "de Surbrillance");
        registerVanilla(t, "darkness", "de Obscurité");
        registerVanilla(t, "haste", "de Célérité");
        registerVanilla(t, "mining_fatigue", "de Fatigue de minage");

        t.add("item.minecraft.potion.effect.alcohol", "Alcool");
        t.add("item.minecraft.splash_potion.effect.alcohol", "Alcool jetable");
        t.add("item.minecraft.lingering_potion.effect.alcohol", "Alcool persistant");
        t.add("item.minecraft.tipped_arrow.effect.alcohol", "Flèche d'Alcool");

        register(t, "saturation", "Saturation");
        register(t, "long_leg", "Grandes jambes");
        register(t, "liquid_walker", "Marche sur liquide");
        register(t, "ore_sense", "Détection de minerais");
        register(t, "resonance", "Résonance");
        register(t, "reactivation", "Réactivation");
        register(t, "purification", "Purification");
        register(t, "teleportation", "Téléportation");
        register(t, "thorns", "Épines", "d'Épines");
        register(t, "brainwashing", "Lavage de cerveau");
        register(t, "death", "Mort");
        register(t, "double_health", "Double vie");
        register(t, "infinity", "Infinité", "d'Infinité");
        register(t, "long_cooldown", "Cooldown allongé");
        register(t, "short_cooldown", "Cooldown réduit");
        register(t, "love", "Amour", "d'Amour");
        register(t, "masking", "Voile d'oubli");
        register(t, "unstable", "Instable", "Instable");
        register(t, "vampirism", "Vampirisme");
        register(t, "stun", "Étourdissement", "d'Étourdissement");
        register(t, "no_interaction", "Mains liées");
        register(t, "aftermath", "Contrecoup");
        register(t, "berserk", "Berserk");
        register(t, "ghost_walk", "Marche spectrale");
        register(t, "dwarf", "Nanisme");
        register(t, "photosynthesis", "Photosynthèse");
        register(t, "pregnant", "Enceinte");
        register(t, "oblivion", "Oubli", "d'Oubli");
        register(t, "adhesion", "Adhésion", "d'Adhésion");
        register(t, "rust", "Rouille");
        register(t, "xp_boost", "Boost d'XP");
        register(t, "xp_life", "Vie d'XP");
        register(t, "zeus", "Bénédiction de Zeus");
        register(t, "medusa", "Bénédiction de Méduse");
        register(t, "midas", "Bénédiction de Midas");
        register(t, "xp_reduction", "Réduction d'XP");
        register(t, "clumsiness", "Maladresse");
        register(t, "cold", "Rhume");
        register(t, "asthma", "Asthme", "d'Asthme");
        register(t, "paranoia", "Paranoïa");
        register(t, "hydrophobia", "Hydrophobie");
        register(t, "zombie_contagion", "Contagion de Zombie");
        register(t, "empathy", "Empathie", "d'Empathie");
        register(t, "magnetism", "Magnétisme");
        register(t, "reality_check", "Retour à la Réalité");
        register(t, "active_tp", "Téléportation Active");

        registerWithAlchemicalStone(t, "resurrection", "Résurrection");
        registerWithAlchemicalStone(t, "giant", "Géant");
        registerWithAlchemicalStone(t, "petrification", "Pétrification");
        registerWithAlchemicalStone(t, "acid", "Acidité", "d'Acidité");
        registerWithAlchemicalStone(t, "ignition", "Ignition", "d'Ignition");
        registerWithAlchemicalStone(t, "alchemist", "Alchimiste", "de l'Alchimiste");
        registerWithAlchemicalStone(t, "frost", "Givre");

        registerElixir(t, "perm_health", "Vie");
        registerElixir(t, "perm_speed", "Vitesse");
        registerElixir(t, "perm_strength", "Force");

        t.add("item.potions-n-rituals.poisonous_carrot", "Carotte empoisonnée");
        t.add("item.potions-n-rituals.poisonous_beetroot", "Betterave empoisonnée");
        t.add("item.potions-n-rituals.materia_prima", "Materia Prima");
        t.add("item.potions-n-rituals.claw", "Griffe");
        t.add("item.potions-n-rituals.zombie_brain", "Cerveau de zombie");
        t.add("item.potions-n-rituals.leaf", "Feuille");
        t.add("item.potions-n-rituals.witch_finger", "Doigt de sorcière");
        t.add("item.potions-n-rituals.blood_bag", "Poche de sang");
        t.add("item.potions-n-rituals.oxydation", "Fragment d'oxydation");
        t.add("item.potions-n-rituals.charged_copper", "Cuivre chargé");
        t.add("item.potions-n-rituals.sulfur_ball", "Boule de Sulfure");
        t.add("item.potions-n-rituals.mercury_ball", "Boule de Mercure");
        t.add("item.potions-n-rituals.salt", "Sel");
        t.add("item.potions-n-rituals.alchemical_tome", "Tome Alchimique");
        t.add("item.potions-n-rituals.alchemical_stone", "Pierre alchimique");
        t.add("item.potions-n-rituals.alchemical_stone.effect.empty", "Pierre alchimique");
        t.add("item.potions-n-rituals.alchemical_stone.effect.mixed", "Pierre alchimique multi-effets");
        registerVanilla(t, "mixed", "multi-effets");

        t.add("item.potions-n-rituals.syringe", "Seringue");
        t.add("item.potions-n-rituals.syringe.effect.isEmpty", "Seringue");

        BuiltInRegistries.MOB_EFFECT.entrySet().forEach(entry -> {
            Identifier id = BuiltInRegistries.MOB_EFFECT.getKey(entry.getValue());
            if (id == null || !id.getNamespace().equals("minecraft")) return;
            String key = "item.potions-n-rituals.syringe." + entry.getValue().getDescriptionId();
            t.add(key, "Seringue de " + entry.getValue().getDescriptionId());
        });

        t.add("potions-n-rituals.blood_type.unknown", "Inconnu");
        t.add("potions-n-rituals.blood_type.human", "Sang humain");
        t.add("potions-n-rituals.blood_type.monster", "Sang de monstre");

        t.add("item.potions-n-rituals.alchemical_stone.block_only", "Les pierres alchimiques ne peuvent être utilisées que sur des blocs");
        t.add("item.potions-n-rituals.alchemical_stone.block_not_good", "Le bloc n'est pas compatible");
        t.add("itemGroup.potions-n-rituals.alchemy", "Alchimie");

        t.add("advancements.potions-n-rituals.root.title", "Potions & Rituels");
        t.add("advancements.potions-n-rituals.root.description", "L'art de l'alchimie");
        t.add("advancements.potions-n-rituals.nigredo.title", "Nigredo");
        t.add("advancements.potions-n-rituals.nigredo.description", "Plongez dans le chaos et créez la Matière Première");
        t.add("advancements.potions-n-rituals.albedo.title", "Albedo");
        t.add("advancements.potions-n-rituals.albedo.description", "Lavez l'impureté et distillez vos premières potions");
        t.add("advancements.potions-n-rituals.citrinitas.title", "Citrinitas");
        t.add("advancements.potions-n-rituals.citrinitas.description", "Canalisez l'énergie du Nether pour forger talismans et artefacts");
        t.add("advancements.potions-n-rituals.rubedo.title", "Rubedo");
        t.add("advancements.potions-n-rituals.rubedo.description", "Atteignez la perfection absolue en accomplissant les grands rituels");

        t.add("splash.potionsnrituals.magic", "L'Alchimie c'est le pouvoir !!!");
        t.add("splash.potionsnrituals.thanks", "Merci de me supporter LivelyBadGood");
        t.add("tooltip.potions-n-rituals.imbued_line", "%s (%s coups restants)");
        t.add("key.potions-n-rituals.show_effect_icons", "Montrer les effets");
        t.add("key.potions-n-rituals.effect_button", "Bouton d'effet");
        t.add("key.category.potions-n-rituals.main", "Potions & Rituals");

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

        // Écran ModConfig — Options des effets actifs
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

        this.additionalTranslations.forEach(t::add);
    }

    private void registerWithAlchemicalStone(TranslationBuilder t, String id, String name) {
        registerWithAlchemicalStone(t, id, name, "de " + name);
    }

    private void registerWithAlchemicalStone(TranslationBuilder t, String id, String effectName, String name) {
        register(t, id, effectName, name);
        t.add("item.potions-n-rituals.alchemical_stone.effect." + id, "Pierre " + name);
    }

    private void registerVanilla(TranslationBuilder t, String id, String name) {
        t.add("item.minecraft.potion.effect." + id, "Potion " + name);
        t.add("item.minecraft.splash_potion.effect." + id, "Potion jetable " + name);
        t.add("item.minecraft.lingering_potion.effect." + id, "Potion persistante " + name);
        t.add("item.minecraft.tipped_arrow.effect." + id, "Flèche " + name);
    }

    private void register(TranslationBuilder t, String id, String name) {
        register(t, id, name, "de " + name);
    }

    private void register(TranslationBuilder t, String id, String effectName, String name) {
        registerVanilla(t, id, name);
        t.add("effect.potions-n-rituals." + id, effectName);
        t.add("item.potions-n-rituals.syringe.effect.potions-n-rituals." + id, "Seringue " + name);
    }

    private void registerElixir(TranslationBuilder t, String id, String name) {
        t.add("effect.potions-n-rituals." + id, name + " Permanente");
        t.add("item.minecraft.potion.effect." + id, "Elixir de " + name + " Permanente");
        t.add("item.minecraft.splash_potion.effect." + id, "Elixir jetable de " + name + " Permanente");
        t.add("item.minecraft.lingering_potion.effect." + id, "Elixir persistante de " + name + " Permanente");
        t.add("item.minecraft.tipped_arrow.effect." + id, "Flèche de " + name + " Permanente");
    }
}