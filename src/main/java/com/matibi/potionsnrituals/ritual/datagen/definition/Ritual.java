package com.matibi.potionsnrituals.ritual.datagen.definition;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public record Ritual(
        int duration,
        Map<String, RitualIngredient> keys,
        List<String> pattern,
        Optional<RitualCatalyst> catalyst,
        RitualResult result
) {
    public static final Codec<Ritual> CODEC = RecordCodecBuilder.create(inst -> inst.group(
            Codec.INT.optionalFieldOf("duration", 200).forGetter(Ritual::duration),
            Codec.unboundedMap(Codec.STRING, RitualIngredient.CODEC).fieldOf("key").forGetter(Ritual::keys),
            Codec.STRING.listOf().fieldOf("pattern").forGetter(Ritual::pattern),
            RitualCatalyst.CODEC.optionalFieldOf("catalyst").forGetter(Ritual::catalyst),
            RitualResult.CODEC.fieldOf("result").forGetter(Ritual::result)
    ).apply(inst, Ritual::new));
}