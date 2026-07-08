package com.matibi.potionsnrituals.block;

import com.matibi.potionsnrituals.PotionsNRituals;
import com.matibi.potionsnrituals.block.custom.BloodTrailBlock;
import com.matibi.potionsnrituals.block.custom.pedestal.PedestalBlock;
import com.matibi.potionsnrituals.block.custom.cauldron.BrewingCauldronBlock;
import com.matibi.potionsnrituals.util.ModUtils;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemLore;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ModBlocks {

    public static final Block
            BLOOD_TRAIL = regBlockOnly("blood", properties ->
                new BloodTrailBlock(properties.strength(0.0F).noOcclusion())),

            PEDESTAL = reg("pedestal",
                properties -> new PedestalBlock(properties.strength(3.0F).noOcclusion()
                    .lightLevel(state -> state.getValue(PedestalBlock.LIT) ? 12 : 0))),

            BREWING_CAULDRON = regBlockOnly("brewing_cauldron",
                properties -> new BrewingCauldronBlock(properties.strength(2.0F).noOcclusion()));

    private static Block reg(String name, Function<BlockBehaviour.Properties, Block> function, Component... tooltips) {
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, ModUtils.id(name))));
        registerBlockItem(name, toRegister, tooltips);
        return Registry.register(BuiltInRegistries.BLOCK, ModUtils.id(name), toRegister);
    }

    private static Block regBlockOnly(String name, Function<BlockBehaviour.Properties, Block> function, Component... tooltips) {
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, ModUtils.id(name))));
        return Registry.register(BuiltInRegistries.BLOCK, ModUtils.id(name), toRegister);
    }

    private static void registerBlockItem(String name, Block block, Component... tooltips) {
        List<Component> lines = new ArrayList<>(List.of(tooltips));
        Registry.register(BuiltInRegistries.ITEM, ModUtils.id(name),
                new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix()
                        .setId(ResourceKey.create(Registries.ITEM, ModUtils.id(name)))
                        .component(DataComponents.LORE, new ItemLore(lines))
        ));
    }

    public static void register() {
        PotionsNRituals.LOGGER.info("Registering mod blocks for " + PotionsNRituals.MOD_ID);
    }
}
