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
        generateVanillaEffects(t);
        generateModEffects(t);
        generateItems(t);
        generateBlocks(t);
        generateAdvancements(t);
        generateMisc(t);

        ModBookLanguageHelper.generateBookEnUsTranslations(t);
        ModConfigLanguageHelper.generateConfigEnUsTranslations(t);

        this.additionalTranslations.forEach(t::add);
    }

    private void generateVanillaEffects(TranslationBuilder t) {
        registerVanilla(t, "levitation", "Levitation");
        registerVanilla(t, "glowing", "Glowing");
        registerVanilla(t, "darkness", "Darkness");
        registerVanilla(t, "haste", "Haste");
        registerVanilla(t, "mining_fatigue", "Mining Fatigue");

        t.add("item.minecraft.potion.effect.alcohol", "Alcohol");
        t.add("item.minecraft.splash_potion.effect.alcohol", "Splash Alcohol");
        t.add("item.minecraft.lingering_potion.effect.alcohol", "Lingering Alcohol");
        t.add("item.minecraft.tipped_arrow.effect.alcohol", "Arrow of Alcohol");
    }

    private void generateModEffects(TranslationBuilder t) {
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
        register(t, "reality_check", "Reality Check");
        register(t, "active_tp", "Active teleportation");

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
    }

    private void generateItems(TranslationBuilder t) {
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
        t.add("item.potions-n-rituals.talisman", "Empty Talisman");
        t.add("item.potions-n-rituals.talisman_charged", "Charged Talisman");
        t.add("item.potions-n-rituals.alchemy_guide_basic", "Magnus Opus: The Foundations of Alchemy");
        t.add("item.potions-n-rituals.alchemy_guide_nigredo", "Nigredo: Chronicles of Chaos");
        t.add("item.potions-n-rituals.alchemical_stone", "Alchemical Stone");
        t.add("item.potions-n-rituals.alchemical_stone.effect.empty", "Alchemical Stone");
        t.add("item.potions-n-rituals.alchemical_stone.effect.mixed", "Multi-effect Alchemical Stone");
        registerVanillaReverse(t, "mixed", "Multi-effect");

        t.add("item.potions-n-rituals.syringe", "Syringe");
        t.add("item.potions-n-rituals.syringe.effect.isEmpty", "Syringe");

        BuiltInRegistries.MOB_EFFECT.entrySet().forEach(entry -> {
            Identifier id = BuiltInRegistries.MOB_EFFECT.getKey(entry.getValue());
            if (id == null || !id.getNamespace().equals("minecraft")) return;
            String key = "item.potions-n-rituals.syringe." + entry.getValue().getDescriptionId();
            t.add(key, "Syringe of " + entry.getValue().getDescriptionId());
        });
    }

    private void generateBlocks(TranslationBuilder t) {
        t.add("block.potions-n-rituals.pedestal", "Pedestal");
        t.add("block.potions-n-rituals.brewing_cauldron", "Brewing Cauldron");
    }

    private void generateAdvancements(TranslationBuilder t) {
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

    private void generateMisc(TranslationBuilder t) {
        // Parameter / Keys
        t.add("key.potions-n-rituals.show_effect_icons", "Show Effect Icons");
        t.add("key.potions-n-rituals.effect_button", "Effect Button");
        t.add("key.category.potions-n-rituals.main", "Potions & Rituals");

        // Blood Types
        t.add("potions-n-rituals.blood_type.unknown", "Unknown");
        t.add("potions-n-rituals.blood_type.human", "Human Blood");
        t.add("potions-n-rituals.blood_type.monster", "Monster Blood");

        // Mod Text
        t.add("item.potions-n-rituals.alchemical_stone.block_only", "Runes can only be used on blocks");
        t.add("item.potions-n-rituals.alchemical_stone.block_not_good", "The block is not compatible");
        t.add("itemGroup.potions-n-rituals.alchemy", "Alchemy");
        t.add("splash.potionsnrituals.magic", "Alchemy is power!!!");
        t.add("splash.potionsnrituals.thanks", "Thank you for supporting me LivelyBadGood");

        // Tooltips
        t.add("tooltip.potions-n-rituals.talisman_charge_line", "Gather %s Souls");
        t.add("tooltip.potions-n-rituals.talisman_charged_line", "The talisman overflows with spiritual energy.");
        t.add("tooltip.potions-n-rituals.bookmarks_count_line", "%s Bookmarks");
        t.add("tooltip.potions-n-rituals.imbued_line", "%s (%s hits remaining)");
    }

    // --- TES MÉTHODES DE REVERSE ET REGISTRE SPÉCIFIQUES À L'ANGLAIS ---
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
        t.add("item.minecraft.potion.effect." + id, "Permanent " + name + " Elixir");
        t.add("item.minecraft.splash_potion.effect." + id, "Permanent " + name + " Splash Elixir");
        t.add("item.minecraft.lingering_potion.effect." + id, "Permanent " + name + " Lingering Elixir");
        t.add("item.minecraft.tipped_arrow.effect." + id, "Permanent " + name + " Arrow");
    }
}