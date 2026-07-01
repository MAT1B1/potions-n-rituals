package com.matibi.potionsnrituals.datagen;

import com.matibi.potionsnrituals.block.ModBlocks;
import com.matibi.potionsnrituals.block.custom.cauldron.BrewingCauldronBlock;
import com.matibi.potionsnrituals.item.ModItems;
import com.matibi.potionsnrituals.util.ModItemTintSources,
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(@NonNull BlockModelGenerators gen) {
        gen.createNonTemplateModelBlock(ModBlocks.PEDESTAL);
        gen.blockStateOutput.accept(
                MultiVariantGenerator.dispatch(ModBlocks.BREWING_CAULDRON)
                        .with(PropertyDispatch.initial(BrewingCauldronBlock.LEVEL)
                                .select(0, BlockModelGenerators.plainVariant(
                                        ModelLocationUtils.getModelLocation(Blocks.CAULDRON)))
                                .select(1, BlockModelGenerators.plainVariant(
                                        ModelLocationUtils.getModelLocation(Blocks.WATER_CAULDRON, "_level1")))
                                .select(2, BlockModelGenerators.plainVariant(
                                        ModelLocationUtils.getModelLocation(Blocks.WATER_CAULDRON, "_level2")))
                                .select(3, BlockModelGenerators.plainVariant(
                                        ModelLocationUtils.getModelLocation(Blocks.WATER_CAULDRON, "_full")))
                        )
        );
    }

    @Override
    public void generateItemModels(ItemModelGenerators gen) {
        List<Item> items = List.of(
                ModItems.CLAW,
                ModItems.WITCH_S_FINGER,
                ModItems.ZOMBIE_BRAIN,
                ModItems.POISONOUS_BEETROOT,
                ModItems.POISONOUS_CARROT,
                ModItems.MATERIA_PRIMA,
                ModItems.CHARGED_COPPER,
                ModItems.OXYDATION,
                ModItems.BLOOD_BAG,
                ModItems.SULFUR_BALL,
                ModItems.MERCURY_BALL,
                ModItems.SALT,
                ModItems.BASIC_GUIDE,
                ModItems.NIGREDO_GUIDE,
                ModItems.TALISMAN,
                ModItems.TALISMAN_CHARGED
        );

        items.forEach(item -> gen.generateFlatItem(item, ModelTemplates.FLAT_ITEM));
        gen.generateItemWithTintedOverlay(ModItems.ALCHEMICAL_STONE, new ModItemTintSources.TintItemColor(0xFFFFFFFF));
        gen.generateItemWithTintedOverlay(ModItems.SYRINGE, new ModItemTintSources.TintItemColor(0xFFFFFFFF));
    }
}