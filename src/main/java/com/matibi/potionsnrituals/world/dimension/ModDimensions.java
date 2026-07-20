package com.matibi.potionsnrituals.world.dimension;

import com.matibi.potionsnrituals.PotionsNRituals;
import com.matibi.potionsnrituals.util.ModUtils;
import com.matibi.potionsnrituals.world.biome.ModBiomes;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.attribute.EnvironmentAttributeMap;
import net.minecraft.world.level.CardinalLighting;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.FlatLevelSource;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.flat.FlatLayerInfo;
import net.minecraft.world.level.levelgen.flat.FlatLevelGeneratorSettings;

import java.util.List;
import java.util.Optional;

public class ModDimensions {

    public static final ResourceKey<Level> POCKET_DIMENSION = createLevelKey("pocket_dimension");
    public static final ResourceKey<DimensionType> POCKET_DIMENSION_TYPE = createTypeKey("pocket_dimension");
    public static final ResourceKey<LevelStem> POCKET_LEVEL_STEM = createStemKey("pocket_dimension");

    public static final ResourceKey<Level> SPIRIT_DIMENSION = createLevelKey("spirit_dimension");
    public static final ResourceKey<DimensionType> SPIRIT_DIMENSION_TYPE = createTypeKey("spirit_dimension");
    public static final ResourceKey<LevelStem> SPIRIT_LEVEL_STEM = createStemKey("spirit_dimension");

    public static void bootstrapType(BootstrapContext<DimensionType> context) {
        HolderSet<Block> infiniburnSet = context.lookup(Registries.BLOCK).getOrThrow(BlockTags.INFINIBURN_OVERWORLD);
        DimensionType.MonsterSettings emptyMonsterSettings = new DimensionType.MonsterSettings(ConstantInt.of(0), 0);

        context.register(POCKET_DIMENSION_TYPE, new DimensionType(
                true, false, false, false, 1.0, 0, 256, 256, infiniburnSet, 0.2F,
                emptyMonsterSettings, DimensionType.Skybox.END, CardinalLighting.Type.DEFAULT,
                EnvironmentAttributeMap.EMPTY, HolderSet.empty(), Optional.empty()
        ));

        context.register(SPIRIT_DIMENSION_TYPE, new DimensionType(
                false, true, false, false, 1.0, -64, 384, 384, infiniburnSet, 0.0F,
                emptyMonsterSettings, DimensionType.Skybox.OVERWORLD, CardinalLighting.Type.DEFAULT,
                EnvironmentAttributeMap.EMPTY, HolderSet.empty(), Optional.empty()
        ));
    }

    public static void bootstrapStem(BootstrapContext<LevelStem> context) {
        HolderGetter<DimensionType> dimTypes = context.lookup(Registries.DIMENSION_TYPE);

        context.register(POCKET_LEVEL_STEM, new LevelStem(
                dimTypes.getOrThrow(POCKET_DIMENSION_TYPE),
                createVoidFlatGenerator(context)
        ));

        context.register(SPIRIT_LEVEL_STEM, new LevelStem(
                dimTypes.getOrThrow(SPIRIT_DIMENSION_TYPE),
                createSpiritNoiseGenerator(context)
        ));
    }

    private static NoiseBasedChunkGenerator createSpiritNoiseGenerator(BootstrapContext<LevelStem> context) {
        HolderGetter<Biome> biomeRegistry = context.lookup(Registries.BIOME);
        Holder<Biome> deepDarkBiome = biomeRegistry.getOrThrow(Biomes.DEEP_DARK);
        Holder<Biome> spiritBiome = biomeRegistry.getOrThrow(ModBiomes.SPIRIT_BIOME);
        HolderGetter<NoiseGeneratorSettings> noiseSettings = context.lookup(Registries.NOISE_SETTINGS);

        Climate.ParameterPoint deepDarkPoint = Climate.parameters(
                Climate.Parameter.span(-1.0F, 1.0F),
                Climate.Parameter.span(-1.0F, 1.0F),
                Climate.Parameter.span(-1.0F, 1.0F),
                Climate.Parameter.span(-1.0F, 1.0F),
                Climate.Parameter.span(0.2F, 1.0F),
                Climate.Parameter.span(-1.0F, 1.0F),
                0.0F
        );

        Climate.ParameterPoint spiritPoint = Climate.parameters(
                Climate.Parameter.span(-1.0F, 1.0F),
                Climate.Parameter.span(-1.0F, 1.0F),
                Climate.Parameter.span(-1.0F, 1.0F),
                Climate.Parameter.span(-1.0F, 1.0F),
                Climate.Parameter.span(-1.0F, 0.2F),
                Climate.Parameter.span(-1.0F, 1.0F),
                0.0F
        );

        Climate.ParameterList<Holder<Biome>> biomeList = new Climate.ParameterList<>(List.of(
                Pair.of(deepDarkPoint, deepDarkBiome),
                Pair.of(spiritPoint, spiritBiome)
        ));

        return new NoiseBasedChunkGenerator(
                MultiNoiseBiomeSource.createFromList(biomeList),
                noiseSettings.getOrThrow(NoiseGeneratorSettings.OVERWORLD)
        );
    }

    private static FlatLevelSource createVoidFlatGenerator(BootstrapContext<LevelStem> context) {
        HolderGetter<Biome> biomeRegistry = context.lookup(Registries.BIOME);
        FlatLevelGeneratorSettings flatSettings = new FlatLevelGeneratorSettings(
                Optional.empty(), biomeRegistry.getOrThrow(Biomes.THE_VOID), List.of()
        );
        flatSettings.getLayersInfo().add(new FlatLayerInfo(1, Blocks.AIR));
        flatSettings.updateLayers();

        return new FlatLevelSource(flatSettings);
    }

    private static ResourceKey<Level> createLevelKey(String name) {
        return ResourceKey.create(Registries.DIMENSION, ModUtils.id(name));
    }

    private static ResourceKey<DimensionType> createTypeKey(String name) {
        return ResourceKey.create(Registries.DIMENSION_TYPE, ModUtils.id(name));
    }

    private static ResourceKey<LevelStem> createStemKey(String name) {
        return ResourceKey.create(Registries.LEVEL_STEM, ModUtils.id(name));
    }

    public static void register() {
        PotionsNRituals.LOGGER.info("Registering ModDimensions for " + PotionsNRituals.MOD_ID);
    }
}