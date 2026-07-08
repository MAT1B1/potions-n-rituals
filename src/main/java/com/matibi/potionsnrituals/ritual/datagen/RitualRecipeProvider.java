package com.matibi.potionsnrituals.ritual.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.matibi.potionsnrituals.ritual.datagen.definition.RitualCatalyst;
import com.matibi.potionsnrituals.util.ModUtils;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.jspecify.annotations.NonNull;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public abstract class RitualRecipeProvider implements DataProvider {
    private final FabricPackOutput output;
    private final Map<Identifier, JsonObject> recipes = new HashMap<>();

    public RitualRecipeProvider(FabricPackOutput output) {
        this.output = output;
    }

    protected abstract void configure();

    protected RitualBuilder addRitual(Item item, int count) {
        Identifier res = BuiltInRegistries.ITEM.getKey(item);
        Identifier recipeId = ModUtils.id(res.getPath() + "_ritual");
        RitualBuilder builder = new RitualBuilder(this, recipeId);
        builder.setResult("item", res.toString(), count);
        return builder;
    }

    protected RitualBuilder addRitual(Block block, int count) {
        Identifier res = BuiltInRegistries.BLOCK.getKey(block);
        Identifier recipeId = ModUtils.id(res.getPath() + "_ritual");
        RitualBuilder builder = new RitualBuilder(this, recipeId);
        builder.setResult("block", res.toString(), count);
        return builder;
    }

    protected RitualBuilder addRitual(EntityType<?> entity, int count) {
        Identifier res = BuiltInRegistries.ENTITY_TYPE.getKey(entity);
        Identifier recipeId = ModUtils.id(res.getPath() + "_summon_ritual");
        RitualBuilder builder = new RitualBuilder(this, recipeId);
        builder.setResult("entity", res.toString(), count);
        return builder;
    }

    protected RitualBuilder addRitual(String customId) {
        Identifier recipeId = ModUtils.id(customId);
        RitualBuilder builder = new RitualBuilder(this, recipeId);
        builder.setCustomResult(customId);
        return builder;
    }

    protected void addRecipe(Identifier id, JsonObject recipe) {
        if (this.recipes.containsKey(id)) {
            throw new IllegalStateException("Un rituel avec l'ID " + id + " existe déjà !");
        }
        this.recipes.put(id, recipe);
    }

    @Override
    public @NonNull CompletableFuture<?> run(@NonNull CachedOutput writer) {
        this.recipes.clear();
        this.configure();

        CompletableFuture<?>[] futures = new CompletableFuture<?>[this.recipes.size()];
        int i = 0;

        PackOutput.PathProvider pathProvider = this.output.createPathProvider(PackOutput.Target.DATA_PACK, "rituals");

        for (Map.Entry<Identifier, JsonObject> entry : this.recipes.entrySet()) {
            Path path = pathProvider.json(entry.getKey());
            futures[i++] = DataProvider.saveStable(writer, entry.getValue(), path);
        }

        return CompletableFuture.allOf(futures);
    }

    @Override
    public @NonNull String getName() {
        return "Potions N' Rituals - Recipes";
    }

    // --- LA CLASS BUILDER INTERNE ---
    public static class RitualBuilder {
        private final RitualRecipeProvider provider;
        private final Identifier recipeId;
        private int duration = 200;
        private final Map<Character, JsonObject> keys = new LinkedHashMap<>();
        private final JsonArray pattern = new JsonArray();
        private String catalyst;
        private final JsonObject result = new JsonObject();

        public RitualBuilder(RitualRecipeProvider provider, Identifier recipeId) {
            this.provider = provider;
            this.recipeId = recipeId;
        }

        protected void setResult(String type, String id, int count) {
            this.result.addProperty(type, id);
            this.result.addProperty("count", count);
        }

        protected void setCustomResult(String customId) {
            this.result.addProperty("custom", customId);
        }

        public RitualBuilder pattern(String line) {
            this.pattern.add(line);
            return this;
        }

        public RitualBuilder duration(int duration) {
            this.duration = duration;
            return this;
        }

        public RitualBuilder define(char symbol, Item item) {
            String id = BuiltInRegistries.ITEM.getKey(item).toString();
            return this.rawKey(symbol, id, "item");
        }

        public RitualBuilder define(char symbol, Block block) {
            String id = BuiltInRegistries.BLOCK.getKey(block).toString();
            return this.rawKey(symbol, id, "block");
        }

        private RitualBuilder rawKey(char symbol, String id, String type) {
            JsonObject keyObj = new JsonObject();
            keyObj.addProperty("id", id);
            keyObj.addProperty("type", type);
            this.keys.put(symbol, keyObj);
            return this;
        }

        public RitualBuilder catalyst(RitualCatalyst catalyst) {
            this.catalyst = catalyst.name().toLowerCase();
            return this;
        }

        // --- LA NOUVELLE MÉTHODE DE FIN ---
        public void save() {
            JsonObject json = new JsonObject();
            json.addProperty("type", "potions-n-rituals:ritual");
            json.addProperty("duration", this.duration);

            JsonObject keyObj = new JsonObject();
            for (Map.Entry<Character, JsonObject> entry : this.keys.entrySet()) {
                keyObj.add(String.valueOf(entry.getKey()), entry.getValue());
            }
            json.add("key", keyObj);

            json.add("pattern", this.pattern);
            if (this.catalyst != null)
                json.addProperty("catalyst", this.catalyst);

            json.add("result", this.result);

            this.provider.addRecipe(this.recipeId, json);
        }
    }
}