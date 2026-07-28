package com.matibi.potionsnrituals.util;

import com.matibi.potionsnrituals.item.ModItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableSource;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.ArrayList;
import java.util.List;

public class ModMobDrops {

    private record DropAction(EntityType<?> entity, Item item, float initialChance, float decreasePerDrop, float minChanceCap, int maxCount, boolean tiered) {}

    private static final List<DropAction> DROPS_REGISTRY = new ArrayList<>();

    public static void register() {
        addTieredDrops(EntityTypes.CAT, ModItems.CLAW, 1.00f, 0.25f, 0.10f, 2);
        addTieredDrops(EntityTypes.WOLF, ModItems.CLAW, 1.00f, 0.25f, 0.10f, 2);
        addTieredDrops(EntityTypes.OCELOT, ModItems.CLAW, 1.00f, 0.25f, 0.10f, 2);
        addTieredDrops(EntityTypes.WITCH, ModItems.WITCH_S_FINGER, 0.75f, 0.20f, 0.05f, 2);
        addTieredDrops(EntityTypes.BAT, ModItems.BAT_WING, 0.5f, 0.0f, 0.5f, 2);
        addDrop(EntityTypes.ZOMBIE, ModItems.ZOMBIE_BRAIN, 0.25f, 1);
        addDrop(EntityTypes.ZOMBIE, ModItems.ZOMBIE_LUNG, 0.25f, 1);
        addDrop(EntityTypes.ZOMBIE_VILLAGER, ModItems.ZOMBIE_BRAIN, 0.25f, 1);
        addDrop(EntityTypes.ZOMBIE_VILLAGER, ModItems.ZOMBIE_LUNG, 0.25f, 1);
        addDrop(EntityTypes.HUSK, ModItems.ZOMBIE_BRAIN, 0.25f, 1);
        addDrop(EntityTypes.HUSK, ModItems.ZOMBIE_LUNG, 0.25f, 1);

        // Écouteur d'événements de Fabric
        LootTableEvents.MODIFY.register(ModMobDrops::handleLootTable);
    }

    private static void handleLootTable(ResourceKey<LootTable> key, LootTable.Builder tableBuilder,
                                        LootTableSource source, HolderLookup.Provider provider) {
        Identifier path = key.identifier();

        for (DropAction action : DROPS_REGISTRY) {
            if (matchesEntity(path, action.entity())) {
                if (action.tiered())
                    executeTieredBuild(tableBuilder, action.item(), action.initialChance(), action.decreasePerDrop(), action.minChanceCap(), action.maxCount());
                else
                    executeSingleDropBuild(tableBuilder, action.item(), action.initialChance(), action.maxCount());
            }
        }
    }

    public static void addTieredDrops(EntityType<?> entity, Item item, float initialChance, float decreasePerDrop, float minChanceCap, int maxCount) {
        DROPS_REGISTRY.add(new DropAction(entity, item, initialChance, decreasePerDrop, minChanceCap, maxCount, true));
    }

    public static void addDrop(EntityType<?> entity, Item item, float chance, int maxCount) {
        DROPS_REGISTRY.add(new DropAction(entity, item, chance, 0f, chance, maxCount, false));
    }

    private static void executeTieredBuild(LootTable.Builder tableBuilder, Item item, float initialChance, float decreasePerDrop, float minChanceCap, int maxCount) {
        float currentChance = initialChance;
        while (currentChance >= minChanceCap) {
            int count = (currentChance == minChanceCap || currentChance - decreasePerDrop < minChanceCap) ? maxCount : 1;
            executeSingleDropBuild(tableBuilder, item, currentChance, count);

            if (currentChance == minChanceCap) break;

            currentChance -= decreasePerDrop;
            if (currentChance < minChanceCap)
                currentChance = minChanceCap;
        }
    }

    private static boolean matchesEntity(Identifier path, EntityType<?> entity) {
        Identifier entityId = BuiltInRegistries.ENTITY_TYPE.getKey(entity);
        return path.equals(Identifier.withDefaultNamespace("entities/" + entityId.getPath()));
    }

    private static void executeSingleDropBuild(LootTable.Builder tableBuilder, Item item, float chance, int maxCount) {
        LootPool pool = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1.0f))
                .add(LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, maxCount)))
                )
                .when(LootItemRandomChanceCondition.randomChance(chance))
                .build();
        tableBuilder.pool(pool);
    }
}