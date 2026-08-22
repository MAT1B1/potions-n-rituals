package com.matibi.potionsnrituals.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ModRegistryDataProvider extends FabricDynamicRegistryProvider {
    public ModRegistryDataProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.@NonNull Provider registries, @NonNull Entries entries) {
        entries.addAll(registries.lookupOrThrow(Registries.VILLAGER_TRADE));
        entries.addAll(registries.lookupOrThrow(Registries.DIMENSION_TYPE));
        entries.addAll(registries.lookupOrThrow(Registries.LEVEL_STEM));
        entries.addAll(registries.lookupOrThrow(Registries.BIOME));
    }

    @Override
    public @NonNull String getName() {
        return "Potions & Rituals Data provider";
    }
}
