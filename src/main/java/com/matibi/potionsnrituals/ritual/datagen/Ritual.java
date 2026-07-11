package com.matibi.potionsnrituals.ritual.datagen;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.StringRepresentable;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public record Ritual(
        int duration,
        Map<String, Ingredient> keys,
        List<String> pattern,
        Optional<Catalysts> catalyst,
        List<Conditions> conditions,
        Optional<String> during,
        Result result
) {
    public static final Codec<Ritual> CODEC = RecordCodecBuilder.create(inst -> inst.group(
            Codec.INT.optionalFieldOf("duration", 200).forGetter(Ritual::duration),
            Codec.unboundedMap(Codec.STRING, Ingredient.CODEC).fieldOf("key").forGetter(Ritual::keys),
            Codec.STRING.listOf().fieldOf("pattern").forGetter(Ritual::pattern),
            Catalysts.CODEC.optionalFieldOf("catalyst").forGetter(Ritual::catalyst),
            Conditions.CODEC.listOf().optionalFieldOf("conditions", List.of()).forGetter(Ritual::conditions),
            Codec.STRING.optionalFieldOf("during").forGetter(Ritual::during),
            Result.CODEC.fieldOf("result").forGetter(Ritual::result)
    ).apply(inst, Ritual::new));

    public record Ingredient(String id, String type) {
        public static final Codec<Ingredient> CODEC = RecordCodecBuilder.create(inst -> inst.group(
                Codec.STRING.fieldOf("id").forGetter(Ingredient::id),
                Codec.STRING.fieldOf("type").forGetter(Ingredient::type)
        ).apply(inst, Ingredient::new));
    }

    public record Result(
            Optional<String> item,
            Optional<String> block,
            Optional<String> entity,
            Optional<String> custom,
            Optional<Integer> count
    ) {
        public static final Codec<Result> CODEC = RecordCodecBuilder.create(inst -> inst.group(
                Codec.STRING.optionalFieldOf("item").forGetter(Result::item),
                Codec.STRING.optionalFieldOf("block").forGetter(Result::block),
                Codec.STRING.optionalFieldOf("entity").forGetter(Result::entity),
                Codec.STRING.optionalFieldOf("custom").forGetter(Result::custom),
                Codec.INT.optionalFieldOf("count").forGetter(Result::count)
        ).apply(inst, Result::new));
    }

    public enum Catalysts implements StringRepresentable {
        IGNITE("ignite"),
        KILL("kill"),
        SUICIDE("suicide"),
        THUNDERSTRIKE("thunderstrike"),
        MOONLIGHT("moonlight");

        public static final Codec<Catalysts> CODEC = StringRepresentable.fromEnum(Catalysts::values);
        private final String name;

        Catalysts(String name) {
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

    public enum ConditionTypes implements StringRepresentable {
        WEATHER("weather"),
        MOONPHASE("moonphase"),
        HEALTH("health"),
        DIMENSION("dimension"),
        BRIGHTNESS("brightness"),
        TIME("time"),
        HEIGHT("height"),
        EFFECT("effect"),
        OFFHAND("offhand"),
        BIOME("biome"),
        XP("xp");

        public static final Codec<ConditionTypes> CODEC = StringRepresentable.fromEnum(ConditionTypes::values);
        private final String name;

        ConditionTypes(String name) {
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

    public record Conditions(ConditionTypes type, JsonObject params) {
        public static final Codec<Conditions> CODEC = RecordCodecBuilder.create(inst -> inst.group(
                ConditionTypes.CODEC.fieldOf("type").forGetter(Conditions::type),
                ExtraCodecs.JSON.xmap(
                        JsonElement::getAsJsonObject,
                        jsonObject -> jsonObject
                ).fieldOf("params").forGetter(Conditions::params)
        ).apply(inst, Conditions::new));

        @Override
        public ConditionTypes type() {
            return type;
        }

        @Override
        public JsonObject params() {
            return params;
        }
    }
}