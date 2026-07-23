package com.matibi.potionsnrituals.datagen;

import com.matibi.potionsnrituals.block.ModBlocks;
import com.matibi.potionsnrituals.block.custom.BloodTrailBlock;
import com.matibi.potionsnrituals.block.custom.cauldron.BrewingCauldronBlock;
import com.matibi.potionsnrituals.block.custom.pedestal.PedestalBlock;
import com.matibi.potionsnrituals.item.ModItems;
import com.matibi.potionsnrituals.util.ModItemTintSources;
import com.matibi.potionsnrituals.util.ModUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mojang.math.Quadrant;
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
import net.minecraft.client.renderer.block.dispatch.VariantMutator;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(@NonNull BlockModelGenerators gen) {
        generateBlockNItem(gen, ModBlocks.BAG_WALL);
        generateBlockNItem(gen, ModBlocks.BAG_EXIT);
        generatePedestalModel(gen);
        generateCauldronModel(gen);
        generateBloodTrailModel(gen);
    }

    private void generateBlockNItem(BlockModelGenerators gen, Block block) {
        gen.createTrivialCube(block);
        if (block.asItem() != Items.AIR)
            gen.registerSimpleItemModel(block, ModelLocationUtils.getModelLocation(block));
    }

    private void generatePedestalModel(BlockModelGenerators gen) {
        Identifier pedestalModel = ModelLocationUtils.getModelLocation(ModBlocks.PEDESTAL);
        Identifier pedestalFireModel = createPedestalFireModel(gen);

        gen.blockStateOutput.accept(
                MultiPartGenerator.multiPart(ModBlocks.PEDESTAL)
                        .with(BlockModelGenerators.plainVariant(pedestalModel))
                        .with(new ConditionBuilder().term(PedestalBlock.LIT, true),
                                BlockModelGenerators.plainVariant(pedestalFireModel))
        );
    }

    private void generateCauldronModel(BlockModelGenerators gen) {
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

    private void genVariant(MultiPartGenerator bloodMultipart, Identifier model,
                            boolean north, boolean east, boolean south, boolean west, VariantMutator rotation) {
        var variant = BlockModelGenerators.plainVariant(model);
        if (rotation != null)
            variant = variant.with(rotation);

        bloodMultipart.with(new ConditionBuilder()
                        .term(BloodTrailBlock.NORTH, north)
                        .term(BloodTrailBlock.EAST, east)
                        .term(BloodTrailBlock.SOUTH, south)
                        .term(BloodTrailBlock.WEST, west),
                variant);
    }

    // Méthode utilitaire sans rotation
    private void genVariant(MultiPartGenerator bloodMultipart, Identifier model,
                            boolean north, boolean east, boolean south, boolean west) {
        genVariant(bloodMultipart, model, north, east, south, west, null);
    }

    private void generateBloodTrailModel(BlockModelGenerators gen) {
        Identifier bloodBaseModelId = createBloodBaseModel(gen);

        Identifier puddleModel = createBloodTextureModel(gen, "blood_puddle", bloodBaseModelId);
        Identifier trailModel = createBloodTextureModel(gen, "blood_trail", bloodBaseModelId);
        Identifier curveModel = createBloodTextureModel(gen, "blood_curve", bloodBaseModelId);
        Identifier crossModel = createBloodTextureModel(gen, "blood_cross", bloodBaseModelId);

        MultiPartGenerator bloodMultipart = MultiPartGenerator.multiPart(ModBlocks.BLOOD_TRAIL);

        // --- SANS CONNEXION (FLAQUE) ---
        genVariant(bloodMultipart, puddleModel, false, false, false, false);

        // --- LIGNES DROITES (TRAIL) ---
        genVariant(bloodMultipart, trailModel, true, false, false, false, VariantMutator.Y_ROT.withValue(Quadrant.R90));
        genVariant(bloodMultipart, trailModel, false, false, true, false, VariantMutator.Y_ROT.withValue(Quadrant.R90));
        genVariant(bloodMultipart, trailModel, true, false, true, false, VariantMutator.Y_ROT.withValue(Quadrant.R90));
        genVariant(bloodMultipart, trailModel, false, true, false, false);
        genVariant(bloodMultipart, trailModel, false, false, false, true);
        genVariant(bloodMultipart, trailModel, false, true, false, true);

        // --- VIRAGES (CURVE) ---
        genVariant(bloodMultipart, curveModel, true, true, false, false, VariantMutator.Y_ROT.withValue(Quadrant.R270));
        genVariant(bloodMultipart, curveModel, false, true, true, false);
        genVariant(bloodMultipart, curveModel, false, false, true, true, VariantMutator.Y_ROT.withValue(Quadrant.R90));
        genVariant(bloodMultipart, curveModel, true, false, false, true, VariantMutator.Y_ROT.withValue(Quadrant.R180));

        // --- INTERSECTIONS (CROSS) ---
        genVariant(bloodMultipart, crossModel, false, true, true, true);
        genVariant(bloodMultipart, crossModel, true, false, true, true);
        genVariant(bloodMultipart, crossModel, true, true, false, true);
        genVariant(bloodMultipart, crossModel, true, true, true, false);
        genVariant(bloodMultipart, crossModel, true, true, true, true);

        gen.blockStateOutput.accept(bloodMultipart);
    }

    private Identifier createBloodBaseModel(BlockModelGenerators gen) {
        Identifier modelId = ModUtils.id("block/blood_base");
        gen.modelOutput.accept(modelId, () -> {
            JsonObject model = new JsonObject();
            model.addProperty("ambientocclusion", false);

            JsonArray elements = new JsonArray();
            JsonObject element = new JsonObject();
            element.add("from", createVector(0.0F, 0.1F, 0.0F));
            element.add("to", createVector(16.0F, 0.1F, 16.0F));

            JsonObject faces = new JsonObject();

            JsonObject upFace = new JsonObject();
            upFace.add("uv", createVector(0.0F, 0.0F, 16.0F, 16.0F));
            upFace.addProperty("texture", "#texture");
            faces.add("up", upFace);

            JsonObject downFace = new JsonObject();
            downFace.add("uv", createVector(0.0F, 0.0F, 16.0F, 16.0F));
            downFace.addProperty("texture", "#texture");
            faces.add("down", downFace);

            element.add("faces", faces);
            elements.add(element);

            model.add("elements", elements);
            return model;
        });
        return modelId;
    }

    private Identifier createBloodTextureModel(BlockModelGenerators gen, String name, Identifier baseModelId) {
        Identifier modelId = ModUtils.id("block/" + name);
        gen.modelOutput.accept(modelId, () -> {
            JsonObject model = new JsonObject();
            model.addProperty("parent", baseModelId.toString());

            JsonObject textures = new JsonObject();
            textures.addProperty("particle", ModUtils.id("block/" + name).toString());
            textures.addProperty("texture", ModUtils.id("block/" + name).toString());
            model.add("textures", textures);

            return model;
        });
        return modelId;
    }

    @Override
    public void generateItemModels(ItemModelGenerators gen) {
        List<Item> items = List.of(
                ModItems.CLAW,
                ModItems.BAT_WING,
                ModItems.WITCH_S_FINGER,
                ModItems.ZOMBIE_BRAIN,
                ModItems.ZOMBIE_LUNG,
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
                ModItems.ALBEDO_GUIDE,
                ModItems.CITRINITAS_GUIDE,
                ModItems.RUBEDO_GUIDE,
                ModItems.TALISMAN,
                ModItems.ALCHEMICAL_BAG,
                ModItems.NETHER_SEAL_BREAKER,
                ModItems.TALISMAN_CHARGED,
                ModItems.SPIRIT_MIRROR,
                ModItems.DECOY,
                ModItems.GAUNTLET
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

            // --- STRUCTURE EN 4 COTÉS ---

            elements.add(createFirePlane(
                    createVector(2.0F, 13.0F, 4.0F),
                    createVector(14.0F, 24.0F, 4.0F),
                    "north", "south"
            ));

            elements.add(createFirePlane(
                    createVector(2.0F, 13.0F, 12.0F),
                    createVector(14.0F, 24.0F, 12.0F),
                    "north", "south"
            ));

            elements.add(createFirePlane(
                    createVector(4.0F, 13.0F, 2.0F),
                    createVector(4.0F, 24.0F, 14.0F),
                    "east", "west"
            ));

            elements.add(createFirePlane(
                    createVector(12.0F, 13.0F, 2.0F),
                    createVector(12.0F, 24.0F, 14.0F),
                    "east", "west"
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