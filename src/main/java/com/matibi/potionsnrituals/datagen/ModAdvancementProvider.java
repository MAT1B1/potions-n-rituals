package com.matibi.potionsnrituals.datagen;

import com.matibi.potionsnrituals.PotionsNRituals;
import com.matibi.potionsnrituals.item.ModItems;
import com.matibi.potionsnrituals.util.ModUtils;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.triggers.InventoryChangeTrigger;
import net.minecraft.advancements.triggers.PlayerTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementProvider extends FabricAdvancementProvider {

    public ModAdvancementProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(HolderLookup.@NonNull Provider registries, @NonNull Consumer<AdvancementHolder> writer) {
        // RACINE : L'ouverture du grimoire
        AdvancementHolder root = Advancement.Builder.advancement()
                .display(
                        Items.BOOK,
                        Component.translatable("advancements.potions-n-rituals.root.title"),
                        Component.translatable("advancements.potions-n-rituals.root.description"),
                        // Texture sombre et mystique par défaut (Obsidienne pleureuse ou Nether)
                        Identifier.withDefaultNamespace("gui/advancements/backgrounds/stone"),
                        AdvancementType.TASK,
                        false,
                        false,
                        false
                )
                .addCriterion("tick", PlayerTrigger.TriggerInstance.tick())
                .save(writer, ModUtils.id("root"));

        // 1. NIGREDO : Création de la matière première
        AdvancementHolder nigredo = Advancement.Builder.advancement()
                .parent(root)
                .display(
                        ModItems.MATERIA_PRIMA, // Utilise ton pixil-frame-0.png !
                        Component.translatable("advancements.potions-n-rituals.nigredo.title"),
                        Component.translatable("advancements.potions-n-rituals.nigredo.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("has_materia_prima", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.MATERIA_PRIMA))
                .rewards(getReward("advancement/reward_book_nigredo"))
                .save(writer, ModUtils.id("nigredo"));

        // 2. ALBEDO : Création des premières potions
        AdvancementHolder albedo = Advancement.Builder.advancement()
                .parent(nigredo)
                .display(
                        Items.POTION,
                        Component.translatable("advancements.potions-n-rituals.albedo.title"),
                        Component.translatable("advancements.potions-n-rituals.albedo.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                // Se déclenche quand le joueur a brassé ou obtenu une fiole d'eau distillée / potion étrange
                .addCriterion("has_potion", InventoryChangeTrigger.TriggerInstance.hasItems(Items.POTION))
                .rewards(getReward("advancement/reward_book_albedo"))
                .save(writer, ModUtils.id("albedo"));

        // 3. CITRINITAS : Les Talismans (Bloqué derrière le Nether via le Quartz)
        AdvancementHolder citrinitas = Advancement.Builder.advancement()
                .parent(albedo)
                .display(
                        Items.QUARTZ, // À remplacer plus tard par ton premier talisman !
                        Component.translatable("advancements.potions-n-rituals.citrinitas.title"),
                        Component.translatable("advancements.potions-n-rituals.citrinitas.description"),
                        null,
                        AdvancementType.CHALLENGE,
                        true,
                        true,
                        false
                )
                .addCriterion("has_quartz", InventoryChangeTrigger.TriggerInstance.hasItems(Items.QUARTZ))
                .rewards(getReward("advancement/reward_book_citrinitas"))
                .save(writer, ModUtils.id("citrinitas"));

        // 4. RUBEDO : Les Rituels (Bloqué derrière l'End via l'End Stone)
        Advancement.Builder.advancement()
                .parent(citrinitas)
                .display(
                        Items.END_STONE, // À remplacer plus tard par ton item de rituel ultime !
                        Component.translatable("advancements.potions-n-rituals.rubedo.title"),
                        Component.translatable("advancements.potions-n-rituals.rubedo.description"),
                        null,
                        AdvancementType.GOAL,
                        true,
                        true,
                        false
                )
                .addCriterion("has_end_stone", InventoryChangeTrigger.TriggerInstance.hasItems(Items.END_STONE))
                .rewards(getReward("advancement/reward_book_rubedo"))
                .save(writer, ModUtils.id("rubedo"));
    }

    @Override
    public @NonNull String getName() {
        return PotionsNRituals.MOD_ID + " advancement";
    }

    private AdvancementRewards.Builder getReward(String path) {
        return AdvancementRewards.Builder.loot(ResourceKey.create(Registries.LOOT_TABLE, ModUtils.id(path)));
    }
}