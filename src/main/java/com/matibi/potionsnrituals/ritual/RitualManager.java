package com.matibi.potionsnrituals.ritual;

import com.matibi.potionsnrituals.PotionsNRituals;
import com.matibi.potionsnrituals.ritual.datagen.definition.Ritual;
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import org.jspecify.annotations.NonNull;

import java.util.HashMap;
import java.util.Map;

public class RitualManager extends SimpleJsonResourceReloadListener<Ritual> {

    private static final Map<Identifier, Ritual> RITUALS = new HashMap<>();

    public RitualManager() {
        super(Ritual.CODEC, FileToIdConverter.json("rituals"));
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