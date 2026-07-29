package com.matibi.potionsnrituals.ritual;

import com.google.gson.JsonElement;
import com.matibi.potionsnrituals.PotionsNRituals;
import com.matibi.potionsnrituals.ritual.datagen.Ritual;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.RegistryOps;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.profiling.ProfilerFiller;
import org.jspecify.annotations.NonNull;

import java.util.HashMap;
import java.util.Map;

public class RitualManager extends SimpleJsonResourceReloadListener<JsonElement> {

    private static final Map<Identifier, Ritual> RITUALS = new HashMap<>();
    private static final Map<Identifier, JsonElement> RAW_RITUALS = new HashMap<>();
    private static final FileToIdConverter CONVERTER = FileToIdConverter.json("rituals");

    public RitualManager() {
        super(ExtraCodecs.JSON, CONVERTER);
    }

    @Override
    protected void apply(@NonNull Map<Identifier, JsonElement> parsed, @NonNull ResourceManager manager, @NonNull ProfilerFiller profiler) {
        RAW_RITUALS.clear();
        RAW_RITUALS.putAll(parsed);
        PotionsNRituals.LOGGER.info("Fichiers JSON des rituels mis en cache ({}). En attente du registre serveur...", RAW_RITUALS.size());
    }

    public static void decodeRituals(HolderLookup.Provider lookup) {
        RITUALS.clear();

        RegistryOps<JsonElement> ops = RegistryOps.create(JsonOps.INSTANCE, lookup);

        for (Map.Entry<Identifier, JsonElement> entry : RAW_RITUALS.entrySet()) {
            DataResult<Ritual> result = Ritual.CODEC.parse(ops, entry.getValue());
            result.error().ifPresentOrElse(
                    err -> PotionsNRituals.LOGGER.error("Rituel invalide {} : {}", entry.getKey(), err.message()),
                    () -> RITUALS.put(entry.getKey(), result.result().orElseThrow())
            );
        }

        if (RITUALS.isEmpty())
            PotionsNRituals.LOGGER.error("Aucun rituel n'a pu être décodé !");
        else
            PotionsNRituals.LOGGER.info("Décodage de {} rituels terminé avec succès !", RITUALS.size());
    }

    public static Map<Identifier, Ritual> getAllRituals() {
        return RITUALS;
    }
}