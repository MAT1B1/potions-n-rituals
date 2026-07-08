package com.matibi.potionsnrituals.ritual.datagen.definition;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record RitualIngredient(String id, String type) {
    public static final Codec<RitualIngredient> CODEC = RecordCodecBuilder.create(inst -> inst.group(
            Codec.STRING.fieldOf("id").forGetter(RitualIngredient::id),
            Codec.STRING.fieldOf("type").forGetter(RitualIngredient::type)
    ).apply(inst, RitualIngredient::new));
}