package com.matibi.potionsnrituals.datagen;

import com.matibi.potionsnrituals.item.ModItems;
import com.matibi.potionsnrituals.util.ModUtils;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class ModLootTableProvider extends SimpleFabricLootTableSubProvider {

    public ModLootTableProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture, LootContextParamSets.ADVANCEMENT_REWARD);
    }

    @Override
    public void generate(@NonNull BiConsumer<ResourceKey<LootTable>, LootTable.Builder> exporter) {
        registerRewardBook(exporter, "reward_book_basic", ModItems.BASIC_GUIDE);
        registerRewardBook(exporter, "reward_book_nigredo", ModItems.NIGREDO_GUIDE);
        registerRewardBook(exporter, "reward_book_albedo", ModItems.ALBEDO_GUIDE);
        registerRewardBook(exporter, "reward_book_citrinitas", ModItems.CITRINITAS_GUIDE);
        registerRewardBook(exporter, "reward_book_rubedo", ModItems.RUBEDO_GUIDE);
    }

    private void registerRewardBook(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> exporter, String id, Item item) {
        ResourceKey<LootTable> key = ResourceKey.create(
                Registries.LOOT_TABLE,
                ModUtils.id("advancement/" + id)
        );

        LootTable.Builder builder = LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(item))
                );

        exporter.accept(key, builder);
    }
}