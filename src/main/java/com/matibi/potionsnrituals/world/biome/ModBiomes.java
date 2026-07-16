package com.matibi.potionsnrituals.world.biome;

import com.matibi.potionsnrituals.util.ModUtils;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.attribute.AmbientParticle;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;

import java.util.List;

public class ModBiomes {

    public static final ResourceKey<Biome> SPIRIT_BIOME =
            ResourceKey.create(Registries.BIOME, ModUtils.id("spirit_biome"));

    public static void bootstrap(BootstrapContext<Biome> context) {

        BiomeSpecialEffects effects = new BiomeSpecialEffects.Builder()
                .waterColor(0x00FFFF)
                .grassColorOverride(0x5555FF)
                .foliageColorOverride(0x5555FF)
                .dryFoliageColorOverride(0x3333AA)
                .build();

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE),
                        context.lookup(Registries.CONFIGURED_CARVER));

        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        spawnBuilder.addSpawn(
                MobCategory.CREATURE,
                50,
                new MobSpawnSettings.SpawnerData(EntityTypes.ALLAY, 1, 3)
        );

        context.register(SPIRIT_BIOME, new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .temperature(0.5F)
                .downfall(0.0F)
                .specialEffects(effects)
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(biomeBuilder.build())

                .setAttribute(EnvironmentAttributes.AMBIENT_PARTICLES,
                        List.of(new AmbientParticle(ParticleTypes.WHITE_ASH, 0.005F)))
                .setAttribute(EnvironmentAttributes.FOG_START_DISTANCE, 0.0F)
                .setAttribute(EnvironmentAttributes.FOG_END_DISTANCE, 160.0F)
                .setAttribute(EnvironmentAttributes.SUN_ANGLE, 180.0F)
                .setAttribute(EnvironmentAttributes.FOG_COLOR, 0x2A2A72)

                .build());
    }
}