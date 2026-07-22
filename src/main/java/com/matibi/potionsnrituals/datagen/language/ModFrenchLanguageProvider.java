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
    private TranslationBuilder t;

    public ModFrenchLanguageProvider(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        this(dataOutput, registryLookup, Map.of());
    }

    public ModFrenchLanguageProvider(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup, Map<String, String> additionalTranslations) {
        super(dataOutput, "fr_fr", registryLookup);
        this.additionalTranslations = additionalTranslations;
    }

    @Override
    public void generateTranslations(HolderLookup.@NonNull Provider wrapperLookup, @NonNull TranslationBuilder t_p) {
        t = t_p;
        generateVanillaEffects();
        generateModEffects();
        generateItems();
        generateBlocks();
        generateAdvancements();
        generateMisc();

        ModBookLanguageHelper.generateBookFrFrTranslations(t);
        ModConfigLanguageHelper.generateConfigFrFrTranslations(t);

        this.additionalTranslations.forEach(t::add);
    }

    private void generateVanillaEffects() {
        registerVanilla("levitation", "de Lévitation");
        registerVanilla("glowing", "de Surbrillance");
        registerVanilla("darkness", "de Obscurité");
        registerVanilla("haste", "de Célérité");
        registerVanilla("mining_fatigue", "de Fatigue de minage");

        t.add("item.minecraft.potion.effect.alcohol", "Alcool");
        t.add("item.minecraft.splash_potion.effect.alcohol", "Alcool jetable");
        t.add("item.minecraft.lingering_potion.effect.alcohol", "Alcool persistant");
        t.add("item.minecraft.tipped_arrow.effect.alcohol", "Flèche d'Alcool");
    }

    private void generateModEffects() {
        register("saturation", "Saturation");
        register("long_leg", "Grandes jambes");
        register("liquid_walker", "Marche sur liquide");
        register("ore_sense", "Détection de minerais");
        register("resonance", "Résonance");
        register("reactivation", "Réactivation");
        register("purification", "Purification");
        register("teleportation", "Téléportation");
        register("thorns", "Épines", "d'Épines");
        register("brainwashing", "Lavage de cerveau");
        register("death", "Mort");
        register("double_health", "Double vie");
        register("infinity", "Infinité", "d'Infinité");
        register("long_cooldown", "Cooldown allongé");
        register("short_cooldown", "Cooldown réduit");
        register("love", "Amour", "d'Amour");
        register("masking", "Voile d'oubli");
        register("unstable", "Instable", "Instable");
        register("vampirism", "Vampirisme");
        register("stun", "Étourdissement", "d'Étourdissement");
        register("no_interaction", "Mains liées");
        register("aftermath", "Contrecoup");
        register("berserk", "Berserk");
        register("ghost_walk", "Marche spectrale");
        register("dwarf", "Nanisme");
        register("photosynthesis", "Photosynthèse");
        register("pregnant", "Enceinte");
        register("oblivion", "Oubli", "d'Oubli");
        register("adhesion", "Adhésion", "d'Adhésion");
        register("rust", "Rouille");
        register("xp_boost", "Boost d'XP");
        register("xp_life", "Vie d'XP");
        register("zeus", "Bénédiction de Zeus");
        register("medusa", "Bénédiction de Méduse");
        register("midas", "Bénédiction de Midas");
        register("xp_reduction", "Réduction d'XP");
        register("clumsiness", "Maladresse");
        register("cold", "Rhume");
        register("asthma", "Asthme", "d'Asthme");
        register("paranoia", "Paranoïa");
        register("hydrophobia", "Hydrophobie");
        register("zombie_contagion", "Contagion de Zombie");
        register("empathy", "Empathie", "d'Empathie");
        register("magnetism", "Magnétisme");
        register("reality_check", "Retour à la Réalité");
        register("active_tp", "Téléportation Active");

        registerWithAlchemicalStone("resurrection", "Résurrection");
        registerWithAlchemicalStone("giant", "Géant");
        registerWithAlchemicalStone("petrification", "Pétrification");
        registerWithAlchemicalStone("acid", "Acidité", "d'Acidité");
        registerWithAlchemicalStone("ignition", "Ignition", "d'Ignition");
        registerWithAlchemicalStone("alchemist", "Alchimiste", "de l'Alchimiste");
        registerWithAlchemicalStone("frost", "Givre");

        registerElixir("perm_health", "Vie");
        registerElixir("perm_speed", "Vitesse");
        registerElixir("perm_strength", "Force");
    }

    private void generateItems() {
        addItem("poisonous_carrot", "Carotte empoisonnée");
        addItem("poisonous_beetroot", "Betterave empoisonnée");
        addItem("materia_prima", "Materia Prima");
        addItem("claw", "Griffe");
        addItem("zombie_brain", "Cerveau de zombie");
        addItem("witch_finger", "Doigt de sorcière");
        addItem("blood_bag", "Poche de sang");
        addItem("oxydation", "Fragment d'oxydation");
        addItem("charged_copper", "Cuivre chargé");
        addItem("sulfur_ball", "Boule de Sulfure");
        addItem("mercury_ball", "Boule de Mercure");
        addItem("salt", "Sel");
        addItem("talisman", "Talisman vide");
        addItem("talisman_charged", "Talisman chargé");
        addItem("alchemical_bag", "Dimension de Poche");
        addItem("nether_seal_breaker", "Descelleur de Portail");
        addItem("spirit_mirror", "Miroir spirituel");
        addItem("decoy", "Leurre");
        addItem("alchemy_guide_basic", "Magnus Opus: Les Fondements de l'Alchimie");
        addItem("alchemy_guide_nigredo", "Nigredo: Chroniques du Chaos");
        addItem("alchemy_guide_albedo", "Albedo: Vaisseau de l'Ordre");
        addItem("alchemy_guide_citrinitas", "Citrinitas: Ancre de l'Éveil");
        addItem("alchemy_guide_rubedo", "Rubedo: Apogée de la Maîtrise");
        addItem("alchemical_stone", "Pierre alchimique");
        addItem("alchemical_stone.effect.empty", "Pierre alchimique");
        addItem("alchemical_stone.effect.mixed", "Pierre alchimique multi-effets");
        registerVanilla("mixed", "multi-effets");

        addItem("syringe", "Seringue");
        addItem("syringe.effect.isEmpty", "Seringue");

        BuiltInRegistries.MOB_EFFECT.entrySet().forEach(entry -> {
            Identifier id = BuiltInRegistries.MOB_EFFECT.getKey(entry.getValue());
            if (id == null || !id.getNamespace().equals("minecraft")) return;
            String key = "item.potions-n-rituals.syringe." + entry.getValue().getDescriptionId();
            t.add(key, "Seringue de " + entry.getValue().getDescriptionId());
        });
    }

    private void addItem(String id, String name) {
        t.add("item.potions-n-rituals." + id, name);
    }

    private void generateBlocks() {
        t.add("block.potions-n-rituals.pedestal", "Piédestal");
    }

    private void generateAdvancements() {
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

    private void generateMisc() {
        // parameter
        t.add("key.potions-n-rituals.show_effect_icons", "Montrer les effets");
        t.add("key.potions-n-rituals.effect_button", "Bouton d'effet");
        t.add("key.category.potions-n-rituals.main", "Potions & Rituals");

        // Blood Types
        t.add("potions-n-rituals.blood_type.unknown", "Inconnu");
        t.add("potions-n-rituals.blood_type.human", "Sang humain");
        t.add("potions-n-rituals.blood_type.monster", "Sang de monstre");

        // Mod Text
        addItem("alchemical_stone.block_only", "Les pierres alchimiques ne peuvent être utilisées que sur des blocs");
        addItem("alchemical_stone.block_not_good", "Le bloc n'est pas compatible");
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

    private void registerWithAlchemicalStone(String id, String name) {
        registerWithAlchemicalStone(id, name, "de " + name);
    }

    private void registerWithAlchemicalStone(String id, String effectName, String name) {
        register(id, effectName, name);
        addItem("alchemical_stone.effect." + id, "Pierre " + name);
    }

    private void registerVanilla(String id, String name) {
        t.add("item.minecraft.potion.effect." + id, "Potion " + name);
        t.add("item.minecraft.splash_potion.effect." + id, "Potion jetable " + name);
        t.add("item.minecraft.lingering_potion.effect." + id, "Potion persistante " + name);
        t.add("item.minecraft.tipped_arrow.effect." + id, "Flèche " + name);
    }

    private void register(String id, String name) {
        register(id, name, "de " + name);
    }

    private void register(String id, String effectName, String name) {
        registerVanilla(id, name);
        t.add("effect.potions-n-rituals." + id, effectName);
        addItem("syringe.effect.potions-n-rituals." + id, "Seringue " + name);
    }

    private void registerElixir(String id, String name) {
        t.add("effect.potions-n-rituals." + id, name + " Permanente");
        t.add("item.minecraft.potion.effect." + id, "Elixir de " + name + " Permanente");
        t.add("item.minecraft.splash_potion.effect." + id, "Elixir jetable de " + name + " Permanente");
        t.add("item.minecraft.lingering_potion.effect." + id, "Elixir persistante de " + name + " Permanente");
        t.add("item.minecraft.tipped_arrow.effect." + id, "Flèche de " + name + " Permanente");
    }
}