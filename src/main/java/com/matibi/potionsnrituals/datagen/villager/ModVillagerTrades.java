package com.matibi.potionsnrituals.datagen.villager;

import com.matibi.potionsnrituals.item.ModItems;
import com.matibi.potionsnrituals.util.ModUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.TradeCost;
import net.minecraft.world.item.trading.VillagerTrade;

import java.util.List;
import java.util.Optional;

public class ModVillagerTrades {

    public static final ResourceKey<VillagerTrade>
            CLERIC_1_WATER_ALCHEMY_BASIC_GUIDE = createKey("cleric/1/water_alchemy_basic_guide");

    public static void bootstrap(BootstrapContext<VillagerTrade> bootstrapContext) {
        bootstrapContext.register(CLERIC_1_WATER_ALCHEMY_BASIC_GUIDE, new VillagerTrade(
                new TradeCost(Items.BONE, 1),
                new ItemStackTemplate(ModItems.BASIC_GUIDE),
                2, 5, 0.05f,
                Optional.empty(),
                List.of()
        ));
    }

    private static ResourceKey<VillagerTrade> createKey(String name) {
        return ResourceKey.create(Registries.VILLAGER_TRADE, ModUtils.id(name));
    }
}
