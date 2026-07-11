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
        generateVanillaEffects(t);
        generateModEffects(t);
        generateItems(t);
        generateBlocks(t);
        generateAdvancements(t);
        generateMisc(t);

        ModBookLanguageHelper.generateBookFrFrTranslations(t);
        ModConfigLanguageHelper.generateConfigFrFrTranslations(t);

        this.additionalTranslations.forEach(t::add);
    }

    private void generateVanillaEffects(TranslationBuilder t) {
        registerVanilla(t, "levitation", "de Lévitation");
        registerVanilla(t, "glowing", "de Surbrillance");
        registerVanilla(t, "darkness", "de Obscurité");
        registerVanilla(t, "haste", "de Célérité");
        registerVanilla(t, "mining_fatigue", "de Fatigue de minage");

        t.add("item.minecraft.potion.effect.alcohol", "Alcool");
        t.add("item.minecraft.splash_potion.effect.alcohol", "Alcool jetable");
        t.add("item.minecraft.lingering_potion.effect.alcohol", "Alcool persistant");
        t.add("item.minecraft.tipped_arrow.effect.alcohol", "Flèche d'Alcool");
    }

    private void generateModEffects(TranslationBuilder t) {
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
    }

    private void generateItems(TranslationBuilder t) {
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
        t.add("item.potions-n-rituals.talisman", "Talisman vide");
        t.add("item.potions-n-rituals.talisman_charged", "Talisman chargé");
        t.add("item.potions-n-rituals.alchemical_bag", "Dimension de Poche");
        t.add("item.potions-n-rituals.nether_seal_breaker", "Descelleur de Portail");
        t.add("item.potions-n-rituals.alchemy_guide_basic", "Magnus Opus: Les Fondements de l'Alchimie");
        t.add("item.potions-n-rituals.alchemy_guide_nigredo", "Nigredo: Chroniques du Chaos");
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
    }

    private void generateBlocks(TranslationBuilder t) {
        t.add("block.potions-n-rituals.pedestal", "Piédestal");
    }

    private void generateAdvancements(TranslationBuilder t) {
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
    }

    private void generateMisc(TranslationBuilder t) {
        // parameter
        t.add("key.potions-n-rituals.show_effect_icons", "Montrer les effets");
        t.add("key.potions-n-rituals.effect_button", "Bouton d'effet");
        t.add("key.category.potions-n-rituals.main", "Potions & Rituals");

        // Blood Types
        t.add("potions-n-rituals.blood_type.unknown", "Inconnu");
        t.add("potions-n-rituals.blood_type.human", "Sang humain");
        t.add("potions-n-rituals.blood_type.monster", "Sang de monstre");

        // Mod Text
        t.add("item.potions-n-rituals.alchemical_stone.block_only", "Les pierres alchimiques ne peuvent être utilisées que sur des blocs");
        t.add("item.potions-n-rituals.alchemical_stone.block_not_good", "Le bloc n'est pas compatible");
        t.add("itemGroup.potions-n-rituals.alchemy", "Alchimie");
        t.add("splash.potionsnrituals.magic", "L'Alchimie c'est le pouvoir !!!");
        t.add("splash.potionsnrituals.thanks", "Merci de me supporter LivelyBadGood");
        t.add("message.potions-n-rituals.alchemical_bag.forbidden", "Les lois de l'alchimie interdisent d'ouvrir une dimension de poche à l'intérieur d'une autre...");

        // tooltip
        t.add("tooltip.potions-n-rituals.talisman_charge_line", "Capture %s Âmes");
        t.add("tooltip.potions-n-rituals.talisman_charged_line", "Le talisman déborde d'énergie spirituelle.");
        t.add("tooltip.potions-n-rituals.bookmarks_count_line", "%s Marque-pages");
        t.add("tooltip.potions-n-rituals.imbued_line", "%s (%s coups restants)");
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