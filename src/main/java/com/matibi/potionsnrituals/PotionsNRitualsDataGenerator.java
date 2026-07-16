package com.matibi.potionsnrituals;

import com.matibi.potionsnrituals.datagen.*;
import com.matibi.potionsnrituals.datagen.language.ModFrenchLanguageProvider;
import com.matibi.potionsnrituals.datagen.language.ModUsLanguageProvider;
import com.matibi.potionsnrituals.world.biome.ModBiomes;
import com.matibi.potionsnrituals.world.dimension.ModDimensions;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class PotionsNRitualsDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModItemTagProvider::new);
		pack.addProvider(ModRecipeProvider::new);
		pack.addProvider(ModAdvancementProvider::new);
		pack.addProvider(ModBlockLootTableProvider::new);
		pack.addProvider(ModLootTableProvider::new);
		pack.addProvider(ModRitualProvider::new);
		pack.addProvider(ModWorldgenProvider::new);

		pack.addProvider(ModUsLanguageProvider::new);
		pack.addProvider(ModFrenchLanguageProvider::new);
	}

	@Override
	public void buildRegistry(RegistrySetBuilder registryBuilder) {
		registryBuilder.add(Registries.DIMENSION_TYPE, ModDimensions::bootstrapType);
		registryBuilder.add(Registries.LEVEL_STEM, ModDimensions::bootstrapStem);
		registryBuilder.add(Registries.BIOME, ModBiomes::bootstrap);
	}
}
