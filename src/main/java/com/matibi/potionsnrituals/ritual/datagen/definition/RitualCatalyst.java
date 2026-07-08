package com.matibi.potionsnrituals.ritual.datagen.definition;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;
import org.jspecify.annotations.NonNull;

public enum RitualCatalyst implements StringRepresentable {
    IGNITE("ignite"),
    KILL("kill"),
    SUICIDE("suicide"),
    THUNDERSTRIKE("thunderstrike"),
    MOONLIGHT("moonlight");

    public static final Codec<RitualCatalyst> CODEC = StringRepresentable.fromEnum(RitualCatalyst::values);
    private final String name;

    RitualCatalyst(String name) {
        this.name = name;
    }

    @Override
    public @NonNull String getSerializedName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}