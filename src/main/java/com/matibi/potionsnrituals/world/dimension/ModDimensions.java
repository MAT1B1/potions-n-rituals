package com.matibi.potionsnrituals.world.dimension;

import com.matibi.potionsnrituals.PotionsNRituals;
import com.matibi.potionsnrituals.util.ModUtils;
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
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.FlatLevelSource;
import net.minecraft.world.level.levelgen.flat.FlatLayerInfo;
import net.minecraft.world.level.levelgen.flat.FlatLevelGeneratorSettings;

import java.util.List;
import java.util.Optional;

public class ModDimensions {

    public static final ResourceKey<Level> POCKET_DIMENSION =
            ResourceKey.create(Registries.DIMENSION, ModUtils.id("pocket_dimension"));

    public static final ResourceKey<DimensionType> POCKET_DIMENSION_TYPE =
            ResourceKey.create(Registries.DIMENSION_TYPE, ModUtils.id("pocket_dimension"));

    public static final ResourceKey<LevelStem> POCKET_LEVEL_STEM =
            ResourceKey.create(Registries.LEVEL_STEM, ModUtils.id("pocket_dimension"));

    public static void bootstrapType(BootstrapContext<DimensionType> context) {

        HolderSet<Block> infiniburnSet =
                context.lookup(Registries.BLOCK).getOrThrow(BlockTags.INFINIBURN_OVERWORLD);

        context.register(POCKET_DIMENSION_TYPE, new DimensionType(
                true,               // hasFixedTime (Bloque le temps pour éviter que le ciel ne bouge)
                false,                          // hasSkyLight
                false,                          // hasCeiling
                false,                          // hasEnderDragonFight
                1.0,                            // coordinateScale
                0,                              // minY
                256,                            // height
                256,                            // logicalHeight
                infiniburnSet,                  // infiniburn (HolderSet)
                0.2F,                           // ambientLight
                new DimensionType.MonsterSettings(ConstantInt.of(0), 0), // monsterSettings (Pas de monstres)
                DimensionType.Skybox.END,       // skybox (Remplace l'ancien système "effects", donne un beau ciel étoilé)
                CardinalLighting.Type.DEFAULT,  // cardinalLightType
                EnvironmentAttributeMap.EMPTY,  // attributes (Vide par défaut)
                HolderSet.empty(),              // timelines (Vide)
                Optional.empty()                // defaultClock (On laisse vide, hasFixedTime fera le travail par défaut)
        ));
    }

    public static void bootstrapStem(BootstrapContext<LevelStem> context) {
        HolderGetter<Biome> biomeRegistry = context.lookup(Registries.BIOME);
        HolderGetter<DimensionType> dimTypes = context.lookup(Registries.DIMENSION_TYPE);

        FlatLevelGeneratorSettings flatSettings = new FlatLevelGeneratorSettings(
                Optional.empty(),
                biomeRegistry.getOrThrow(Biomes.THE_VOID),
                List.of()
        );

        flatSettings.getLayersInfo().add(new FlatLayerInfo(1, Blocks.AIR));
        flatSettings.updateLayers();

        FlatLevelSource chunkGenerator = new FlatLevelSource(flatSettings);

        context.register(POCKET_LEVEL_STEM, new LevelStem(
                dimTypes.getOrThrow(POCKET_DIMENSION_TYPE),
                chunkGenerator
        ));
    }

    public static void register() {
        PotionsNRituals.LOGGER.info("Registering ModDimensions for " + PotionsNRituals.MOD_ID);
    }
}