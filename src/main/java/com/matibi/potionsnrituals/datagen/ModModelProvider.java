package com.matibi.potionsnrituals.datagen;

import com.matibi.potionsnrituals.item.ModItems;
import com.matibi.potionsnrituals.util.ModItemTintSources;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.world.item.Item;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(@NonNull BlockModelGenerators blockModelGenerators) {
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
                ModItems.ALCHEMICAL_TOME
        );

        items.forEach(item -> gen.generateFlatItem(item, ModelTemplates.FLAT_ITEM));
        gen.generateItemWithTintedOverlay(ModItems.ALCHEMICAL_STONE, new ModItemTintSources.TintItemColor(0xFFFFFFFF));
        gen.generateItemWithTintedOverlay(ModItems.SYRINGE, new ModItemTintSources.TintItemColor(0xFFFFFFFF));
    }
}