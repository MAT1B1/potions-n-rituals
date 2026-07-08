package com.matibi.potionsnrituals.ritual.datagen.definition;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.Optional;

public record RitualResult(
        Optional<String> item,
        Optional<String> block,
        Optional<String> entity,
        Optional<String> custom,
        Optional<Integer> count
) {
    public static final Codec<RitualResult> CODEC = RecordCodecBuilder.create(inst -> inst.group(
            Codec.STRING.optionalFieldOf("item").forGetter(RitualResult::item),
            Codec.STRING.optionalFieldOf("block").forGetter(RitualResult::block),
            Codec.STRING.optionalFieldOf("entity").forGetter(RitualResult::entity),
            Codec.STRING.optionalFieldOf("custom").forGetter(RitualResult::custom),
            Codec.INT.optionalFieldOf("count").forGetter(RitualResult::count)
    ).apply(inst, RitualResult::new));
}