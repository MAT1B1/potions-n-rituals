package com.matibi.potionsnrituals.datagen;

import com.matibi.potionsnrituals.block.ModBlocks;
import com.matibi.potionsnrituals.block.custom.cauldron.BrewingCauldronBlock;
import com.matibi.potionsnrituals.block.custom.pedestal.PedestalBlock;
import com.matibi.potionsnrituals.item.ModItems;
import com.matibi.potionsnrituals.util.ModItemTintSources;
import com.matibi.potionsnrituals.util.ModUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.blockstates.ConditionBuilder;
import net.minecraft.client.data.models.blockstates.MultiPartGenerator;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.resources.Identifier;
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
        Identifier pedestalModel = ModelLocationUtils.getModelLocation(ModBlocks.PEDESTAL);
        Identifier pedestalFireModel = createPedestalFireModel(gen);

        gen.blockStateOutput.accept(
                MultiPartGenerator.multiPart(ModBlocks.PEDESTAL)
                        .with(BlockModelGenerators.plainVariant(pedestalModel))
                        .with(new ConditionBuilder().term(PedestalBlock.LIT, true),
                                BlockModelGenerators.plainVariant(pedestalFireModel))
        );

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

    private static Identifier createPedestalFireModel(BlockModelGenerators gen) {
        Identifier modelId = ModUtils.id("block/pedestal_fire");
        gen.modelOutput.accept(modelId, () -> {
            JsonObject model = new JsonObject();
            model.addProperty("ambientocclusion", false);
            model.addProperty("render_type", "cutout");

            JsonObject textures = new JsonObject();
            textures.addProperty("particle", "minecraft:block/fire_0");
            textures.addProperty("fire", "minecraft:block/fire_0");
            model.add("textures", textures);

            JsonArray elements = new JsonArray();
            elements.add(createFirePlane(
                    createVector(5.0F, 13.0F, 8.0F),
                    createVector(11.0F, 18.0F, 8.0F),
                    "north",
                    "south"
            ));
            elements.add(createFirePlane(
                    createVector(8.0F, 13.0F, 5.0F),
                    createVector(8.0F, 18.0F, 11.0F),
                    "east",
                    "west"
            ));
            model.add("elements", elements);

            return model;
        });
        return modelId;
    }

    private static JsonObject createFirePlane(JsonArray from, JsonArray to, String firstFace, String secondFace) {
        JsonObject element = new JsonObject();
        element.add("from", from);
        element.add("to", to);
        element.addProperty("shade", false);

        JsonObject faces = new JsonObject();
        faces.add(firstFace, createFireFace());
        faces.add(secondFace, createFireFace());
        element.add("faces", faces);

        return element;
    }

    private static JsonObject createFireFace() {
        JsonObject face = new JsonObject();
        face.add("uv", createVector(0.0F, 0.0F, 16.0F, 16.0F));
        face.addProperty("texture", "#fire");
        return face;
    }

    private static JsonArray createVector(float... values) {
        JsonArray vector = new JsonArray();
        for (float value : values)
            vector.add(value);
        return vector;
    }
}
