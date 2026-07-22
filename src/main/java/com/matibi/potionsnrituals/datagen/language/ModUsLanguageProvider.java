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
    private TranslationBuilder t;

    public ModUsLanguageProvider(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        this(dataOutput, registryLookup, Map.of());
    }

    public ModUsLanguageProvider(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup, Map<String, String> additionalTranslations) {
        super(dataOutput, "en_us", registryLookup);
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

        ModBookLanguageHelper.generateBookEnUsTranslations(t);
        ModConfigLanguageHelper.generateConfigEnUsTranslations(t);

        this.additionalTranslations.forEach(t::add);
    }

    private void generateVanillaEffects() {
        registerVanilla("levitation", "Levitation");
        registerVanilla("glowing", "Glowing");
        registerVanilla("darkness", "Darkness");
        registerVanilla("haste", "Haste");
        registerVanilla("mining_fatigue", "Mining Fatigue");

        t.add("item.minecraft.potion.effect.alcohol", "Alcohol");
        t.add("item.minecraft.splash_potion.effect.alcohol", "Splash Alcohol");
        t.add("item.minecraft.lingering_potion.effect.alcohol", "Lingering Alcohol");
        t.add("item.minecraft.tipped_arrow.effect.alcohol", "Arrow of Alcohol");
    }

    private void generateModEffects() {
        register("saturation", "Saturation");
        register("long_leg", "Long Legs");
        register("liquid_walker", "Liquid Walker");
        register("ore_sense", "Ore Sense");
        register("resonance", "Resonance");
        register("reactivation", "Reactivation");
        register("purification", "Purification");
        register("teleportation", "Teleportation");
        register("thorns", "Thorns");
        register("brainwashing", "Brain Washing");
        register("death", "Death");
        register("double_health", "Double Health");
        register("infinity", "Infinity");
        register("long_cooldown", "Long Cooldown");
        register("short_cooldown", "Short Cooldown");
        register("love", "Love");
        register("masking", "Masking");
        register("vampirism", "Vampirism");
        register("stun", "Stun");
        register("no_interaction", "Hands Bound");
        register("aftermath", "Aftermath");
        register("berserk", "Beserk");
        register("ghost_walk", "Ghost Walk");
        register("dwarf", "Dwarfism");
        register("photosynthesis", "Photosynthesis");
        register("pregnant", "Pregnant");
        register("oblivion", "Oblivion");
        register("adhesion", "Adhesion");
        register("rust", "Rust");
        register("xp_boost", "XP Boost");
        register("xp_life", "XP Life");
        register("zeus", "Zeus Benediction");
        register("medusa", "Medusa Benediction");
        register("midas", "Midas Benediction");
        register("xp_reduction", "XP Reduction");
        register("clumsiness", "Clumsiness");
        register("cold", "Cold");
        register("asthma", "Asthma");
        register("paranoia", "Paranoia");
        register("hydrophobia", "Hydrophobia");
        register("zombie_contagion", "Zombie Contagion");
        register("empathy", "Empathy");
        register("magnetism", "Magnetism");
        register("reality_check", "Reality Check");
        register("active_tp", "Active teleportation");

        registerVanillaReverse("unstable", "Unstable");
        t.add("effect.potions-n-rituals." + "unstable", "Unstable");

        registerWithAlchemicalStone("giant", "Giant");
        registerWithAlchemicalStone("resurrection", "Resurrection");
        registerWithAlchemicalStone("petrification", "Petrification");
        registerWithAlchemicalStone("acid", "Acidity");
        registerWithAlchemicalStone("ignition", "Ignition");
        registerWithAlchemicalStone("alchemist", "Alchemist");
        registerWithAlchemicalStone("frost", "Frost");

        registerElixir("perm_health", "Health");
        registerElixir("perm_speed", "Speed");
        registerElixir("perm_strength", "Strength");
    }

    private void generateItems() {
        addItem("poisonous_carrot", "Poisonous Carrot");
        addItem("poisonous_beetroot", "Poisonous Beetroot");
        addItem("materia_prima", "Materia Prima");
        addItem("claw", "Claw");
        addItem("zombie_brain", "Zombie Brain");
        addItem("witch_finger", "Witch's Finger");
        addItem("blood_bag", "Blood Bag");
        addItem("oxydation", "Oxidation Fragment");
        addItem("charged_copper", "Charged Copper");
        addItem("sulfur_ball", "Sulfur Ball");
        addItem("mercury_ball", "Mercury Ball");
        addItem("salt", "Salt");
        addItem("talisman", "Empty Talisman");
        addItem("talisman_charged", "Charged Talisman");
        addItem("alchemical_bag", "Pocket Dimension");
        addItem("nether_seal_breaker", "Portal Seal Breaker");
        addItem("spirit_mirror", "Spirit Mirror");
        addItem("decoy", "Decoy");
        addItem("alchemy_guide_basic", "Magnus Opus: The Foundations of Alchemy");
        addItem("alchemy_guide_nigredo", "Nigredo: Chronicles of Chaos");
        addItem("alchemy_guide_albedo", "Albedo: Vessel of Order");
        addItem("alchemy_guide_citrinitas", "Citrinitas: Anchor of Enlightenment");
        addItem("alchemy_guide_rubedo", "Rubedo: Apex of Mastery");
        addItem("alchemical_stone", "Alchemical Stone");
        addItem("alchemical_stone.effect.empty", "Alchemical Stone");
        addItem("alchemical_stone.effect.mixed", "Multi-effect Alchemical Stone");
        registerVanillaReverse("mixed", "Multi-effect");

        addItem("syringe", "Syringe");
        addItem("syringe.effect.isEmpty", "Syringe");

        BuiltInRegistries.MOB_EFFECT.entrySet().forEach(entry -> {
            Identifier id = BuiltInRegistries.MOB_EFFECT.getKey(entry.getValue());
            if (id == null || !id.getNamespace().equals("minecraft")) return;
            String key = "item.potions-n-rituals.syringe." + entry.getValue().getDescriptionId();
            t.add(key, "Syringe of " + entry.getValue().getDescriptionId());
        });
    }

    private void addItem(String id, String name) {
        t.add("item.potions-n-rituals." + id, name);
    }

    private void generateBlocks() {
        t.add("block.potions-n-rituals.pedestal", "Pedestal");
        t.add("block.potions-n-rituals.brewing_cauldron", "Brewing Cauldron");
    }

    private void generateAdvancements() {
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
    }

    private void generateMisc() {
        // Parameter / Keys
        t.add("key.potions-n-rituals.show_effect_icons", "Show Effect Icons");
        t.add("key.potions-n-rituals.effect_button", "Effect Button");
        t.add("key.category.potions-n-rituals.main", "Potions & Rituals");

        // Blood Types
        t.add("potions-n-rituals.blood_type.unknown", "Unknown");
        t.add("potions-n-rituals.blood_type.human", "Human Blood");
        t.add("potions-n-rituals.blood_type.monster", "Monster Blood");

        // Mod Text
        addItem("alchemical_stone.block_only", "Runes can only be used on blocks");
        addItem("alchemical_stone.block_not_good", "The block is not compatible");
        t.add("itemGroup.potions-n-rituals.alchemy", "Alchemy");
        t.add("splash.potionsnrituals.magic", "Alchemy is power!!!");
        t.add("splash.potionsnrituals.thanks", "Thank you for supporting me LivelyBadGood");
        t.add("message.potions-n-rituals.alchemical_bag.forbidden", "The laws of alchemy forbid opening a pocket dimension within another...");

        // Tooltips
        t.add("tooltip.potions-n-rituals.talisman_charge_line", "Gather %s Souls");
        t.add("tooltip.potions-n-rituals.talisman_charged_line", "The talisman overflows with spiritual energy.");
        t.add("tooltip.potions-n-rituals.bookmarks_count_line", "%s Bookmarks");
        t.add("tooltip.potions-n-rituals.imbued_line", "%s (%s hits remaining)");
    }

    // --- TES MÉTHODES DE REVERSE ET REGISTRE SPÉCIFIQUES À L'ANGLAIS ---
    private void registerWithAlchemicalStone(String id, String name) {
        register(id, name);
        addItem("alchemical_stone.effect." + id, name + " Stone");
    }

    private void registerVanilla(String id, String name) {
        t.add("item.minecraft.potion.effect." + id, "Potion of " + name);
        t.add("item.minecraft.splash_potion.effect." + id, "Splash Potion of " + name);
        t.add("item.minecraft.lingering_potion.effect." + id, "Lingering Potion of " + name);
        t.add("item.minecraft.tipped_arrow.effect." + id, "Arrow of " + name);
    }

    private void registerVanillaReverse(String id, String name) {
        t.add("item.minecraft.potion.effect." + id, name + " Potion");
        t.add("item.minecraft.splash_potion.effect." + id, name + " Splash Potion");
        t.add("item.minecraft.lingering_potion.effect." + id, name + " Lingering Potion");
        t.add("item.minecraft.tipped_arrow.effect." + id, name + " Arrow");
        addItem("syringe.effect." + id, name + " Syringe");
    }

    private void register(String id, String name) {
        registerVanilla(id, name);
        t.add("effect.potions-n-rituals." + id, name);
        addItem("syringe.effect.potions-n-rituals." + id, "Syringe of " + name);
    }

    private void registerElixir(String id, String name) {
        t.add("effect.potions-n-rituals." + id, "Permanent " + name);
        t.add("item.minecraft.potion.effect." + id, "Permanent " + name + " Elixir");
        t.add("item.minecraft.splash_potion.effect." + id, "Permanent " + name + " Splash Elixir");
        t.add("item.minecraft.lingering_potion.effect." + id, "Permanent " + name + " Lingering Elixir");
        t.add("item.minecraft.tipped_arrow.effect." + id, "Permanent " + name + " Arrow");
    }
}