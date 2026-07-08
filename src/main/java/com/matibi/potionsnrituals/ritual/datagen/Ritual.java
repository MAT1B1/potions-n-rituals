package com.matibi.potionsnrituals.ritual.datagen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
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
        Result result
) {
    public static final Codec<Ritual> CODEC = RecordCodecBuilder.create(inst -> inst.group(
            Codec.INT.optionalFieldOf("duration", 200).forGetter(Ritual::duration),
            Codec.unboundedMap(Codec.STRING, Ingredient.CODEC).fieldOf("key").forGetter(Ritual::keys),
            Codec.STRING.listOf().fieldOf("pattern").forGetter(Ritual::pattern),
            Catalysts.CODEC.optionalFieldOf("catalyst").forGetter(Ritual::catalyst),
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
}