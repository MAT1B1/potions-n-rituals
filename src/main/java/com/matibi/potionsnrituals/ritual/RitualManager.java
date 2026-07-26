package com.matibi.potionsnrituals.ritual;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.matibi.potionsnrituals.PotionsNRituals;
import com.matibi.potionsnrituals.ritual.datagen.Ritual;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.RegistryOps;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import org.jspecify.annotations.NonNull;

import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class RitualManager extends SimpleJsonResourceReloadListener<Ritual> {

    private static final Map<Identifier, Ritual> RITUALS = new HashMap<>();
    private static final FileToIdConverter CONVERTER = FileToIdConverter.json("rituals");
    private static final HolderLookup.Provider LOOKUP = VanillaRegistries.createLookup();

    public RitualManager() {
        super(Ritual.CODEC, CONVERTER);
    }

    @Override
    protected @NonNull Map<Identifier, Ritual> prepare(@NonNull ResourceManager manager, @NonNull ProfilerFiller profiler) {
        Map<Identifier, Ritual> map = new HashMap<>();
        RegistryOps<JsonElement> ops = RegistryOps.create(JsonOps.INSTANCE, LOOKUP);

        for (Map.Entry<Identifier, Resource> entry : CONVERTER.listMatchingResources(manager).entrySet()) {
            Identifier fileId = entry.getKey();
            Identifier id = CONVERTER.fileToId(fileId);
            try (Reader reader = entry.getValue().openAsReader()) {
                JsonElement json = JsonParser.parseReader(reader);
                DataResult<Ritual> result = Ritual.CODEC.parse(ops, json);
                result.error().ifPresentOrElse(
                        err -> PotionsNRituals.LOGGER.error("Rituel invalide {} : {}", id, err.message()),
                        () -> map.put(id, result.result().orElseThrow())
                );
            } catch (Exception e) {
                PotionsNRituals.LOGGER.error("Erreur lecture rituel {}", id, e);
            }
        }
        return map;
    }

    @Override
    protected void apply(@NonNull Map<Identifier, Ritual> parsedRituals, @NonNull ResourceManager manager, @NonNull ProfilerFiller profiler) {
        RITUALS.clear();
        RITUALS.putAll(parsedRituals);

        if (RITUALS.isEmpty())
            PotionsNRituals.LOGGER.error("Aucun rituels chargé");
        else
            PotionsNRituals.LOGGER.info("Chargement de {} rituels terminé !", RITUALS.size());
    }

    public static Map<Identifier, Ritual> getAllRituals() {
        return RITUALS;
    }
}