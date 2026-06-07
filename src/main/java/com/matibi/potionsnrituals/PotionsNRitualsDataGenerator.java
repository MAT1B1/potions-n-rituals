package com.matibi.potionsnrituals;

import com.klikli_dev.modonomicon.api.datagen.BookProvider;
import com.klikli_dev.modonomicon.api.datagen.LanguageProviderCache;
import com.matibi.potionsnrituals.datagen.ModItemTagProvider;
import com.matibi.potionsnrituals.datagen.ModModelProvider;
import com.matibi.potionsnrituals.datagen.ModRecipeProvider;
import com.matibi.potionsnrituals.datagen.book.AlchemistBookProvider;
import com.matibi.potionsnrituals.datagen.language.ModFrenchLanguageProvider;
import com.matibi.potionsnrituals.datagen.language.ModUsLanguageProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.core.HolderLookup;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class PotionsNRitualsDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		var enUsBookCache = new LanguageProviderCache("en_us");
		var frFrBookCache = new LanguageProviderCache("fr_fr");

		pack.addProvider((FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registries) ->
			new BookProvider(output, registries, PotionsNRituals.MOD_ID,
				List.of(new AlchemistBookProvider(PotionsNRituals.MOD_ID, enUsBookCache, frFrBookCache))));

		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModItemTagProvider::new);
		pack.addProvider(ModRecipeProvider::new);

		pack.addProvider((FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registries) ->
				new ModUsLanguageProvider(output, registries, enUsBookCache.data()));
		pack.addProvider((FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registries) ->
				new ModFrenchLanguageProvider(output, registries, frFrBookCache.data()));
	}
}
