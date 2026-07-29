package com.matibi.potionsnrituals.ritual.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.matibi.potionsnrituals.util.ModUtils;
import com.mojang.serialization.JsonOps;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import org.jspecify.annotations.NonNull;

import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.CompletableFuture;

public abstract class RitualRecipeProvider implements DataProvider {
    private final FabricPackOutput output;
    private final CompletableFuture<HolderLookup.Provider> registriesFuture;
    private final Map<Identifier, JsonObject> recipes = new HashMap<>();

    private HolderLookup.Provider lookup;

    public RitualRecipeProvider(FabricPackOutput output,  CompletableFuture<HolderLookup.Provider> registriesFuture) {
        this.output = output;
        this.registriesFuture = registriesFuture;
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

    protected RitualBuilder addRitual(Item item, int count, String customSuffix) {
        Identifier res = BuiltInRegistries.ITEM.getKey(item);
        Identifier recipeId = ModUtils.id(res.getPath() + "_" + customSuffix + "_ritual");

        RitualBuilder builder = new RitualBuilder(this, recipeId);
        builder.setItemResult(item, count);
        return builder;
    }

    protected RitualBuilder addRitual(Block block, List<DatagenItem> items, String customSuffix) {
        Identifier res = BuiltInRegistries.BLOCK.getKey(block);
        Identifier recipeId = ModUtils.id(res.getPath() + "_" + customSuffix + "_ritual");

        RitualBuilder builder = new RitualBuilder(this, recipeId);
        builder.setContainerResult(block, items);
        return builder;
    }

    protected void addRecipe(Identifier id, JsonObject recipe) {
        if (this.recipes.containsKey(id))
            throw new IllegalStateException("Un rituel avec l'ID " + id + " existe déjà !");
        this.recipes.put(id, recipe);
    }

    @Override
    public @NonNull CompletableFuture<?> run(@NonNull CachedOutput writer) {
        return this.registriesFuture.thenCompose(provider -> {
            this.lookup = provider;
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
        });
    }

    @Override
    public @NonNull String getName() {
        return "Potions N' Rituals - Recipes";
    }

    public static class RitualBuilder {
        private final RitualRecipeProvider provider;
        private final Identifier recipeId;

        private int duration = 200;
        private final Map<String, Ingredient> keys = new LinkedHashMap<>();
        private final List<String> pattern = new ArrayList<>();
        private final List<Ritual.Conditions> conditions = new ArrayList<>();
        private Ritual.Catalysts catalyst = null;
        private String duringAction = null;

        private String resultItem = null;
        private String resultBlock = null;
        private String resultEntity = null;
        private String resultCustom = null;
        private Integer resultCount = null;

        // Variables pour forcer l'écriture JSON sans ItemStack
        private String resultItemStackId = null;
        private int resultItemStackCount = 1;
        private DataComponentPatch.Builder componentsBuilder = null;
        private List<DatagenItem> containerItems = null;

        public RitualBuilder(RitualRecipeProvider provider, Identifier recipeId) {
            this.provider = provider;
            this.recipeId = recipeId;
        }

        protected void setResult(String type, String id, int count) {
            switch (type) {
                case "item" -> this.resultItem = id;
                case "block" -> this.resultBlock = id;
                case "entity" -> this.resultEntity = id;
            }
            this.resultCount = count;
        }

        public RitualBuilder setItemResult(Item item, int count) {
            this.resultItemStackId = BuiltInRegistries.ITEM.getKey(item).toString();
            this.resultItemStackCount = count;
            return this;
        }

        public <T> RitualBuilder component(DataComponentType<T> type, T value) {
            if (this.componentsBuilder == null) this.componentsBuilder = DataComponentPatch.builder();
            this.componentsBuilder.set(type, value);
            return this;
        }

        public void setContainerResult(Block containerBlock, List<DatagenItem> items) {
            this.resultBlock = BuiltInRegistries.BLOCK.getKey(containerBlock).toString();
            this.containerItems = items;
        }

        protected void setCustomResult(String customId) {
            this.resultCustom = customId;
        }

        public RitualBuilder pattern(String line) { this.pattern.add(line); return this; }
        public RitualBuilder duration(int duration) { this.duration = duration; return this; }
        public RitualBuilder define(char symbol, Ingredient ingredient) { this.keys.put(String.valueOf(symbol), ingredient); return this; }
        public RitualBuilder define(char symbol, Item item) { return this.define(symbol, Ingredient.of(item)); }
        public RitualBuilder define(char symbol, Block block) { return this.define(symbol, Ingredient.of(block)); }
        public RitualBuilder define(char symbol, HolderSet<Item> tag) { return this.define(symbol, Ingredient.of(tag)); }
        public RitualBuilder catalyst(Ritual.Catalysts catalyst) { this.catalyst = catalyst; return this; }
        public RitualBuilder during(String action) { this.duringAction = action; return this; }


        public enum WEATHER { RAIN, THUNDER, CLEAR }
        public enum DIMENSION {
            OVERWORLD("minecraft:overworld"), NETHER("minecraft:the_nether"), END("minecraft:the_end");
            private final String id;
            DIMENSION(String id) { this.id = id; }
            public String location() { return id; }
        }

        public RitualBuilder weather(WEATHER weather) {
            JsonObject params = new JsonObject();
            params.addProperty("type", weather.name().toLowerCase());
            return addCondition(Ritual.ConditionTypes.WEATHER, params);
        }

        public RitualBuilder effect(Holder<MobEffect> effect) {
            return effect(effect, 0);
        }

        public RitualBuilder effect(Holder<MobEffect> effect, int amplifier) {
            JsonObject params = new JsonObject();
            String id = effect.unwrapKey()
                    .map(key -> key.identifier().toString())
                    .orElseThrow(() -> new IllegalArgumentException("Le holder fourni n'a pas d'ID de registre valide"));
            params.addProperty("id", id);
            params.addProperty("amp", amplifier);
            return addCondition(Ritual.ConditionTypes.EFFECT, params);
        }

        public RitualBuilder biome(ResourceKey<Biome> biome) {
            JsonObject params = new JsonObject();
            params.addProperty("id", biome.identifier().toString());
            return addCondition(Ritual.ConditionTypes.BIOME, params);
        }

        public RitualBuilder xp(float min, float max) {
            return minMax(Ritual.ConditionTypes.XP, min, max);
        }

        public RitualBuilder health(float min, float max) {
            return minMax(Ritual.ConditionTypes.HEALTH, min, max);
        }

        public RitualBuilder height(float min, float max) {
            return minMax(Ritual.ConditionTypes.HEIGHT, min, max);
        }

        public RitualBuilder brightness(float min, float max) {
            return minMax(Ritual.ConditionTypes.BRIGHTNESS, min, max);
        }

        public RitualBuilder time(float min, float max) {
            return minMax(Ritual.ConditionTypes.TIME, min, max);
        }

        public RitualBuilder dimension(DIMENSION dimension) {
            JsonObject params = new JsonObject();
            params.addProperty("id", dimension.location());
            return addCondition(Ritual.ConditionTypes.DIMENSION, params);
        }

        public RitualBuilder offhand(Item item) {
            JsonObject params = new JsonObject();
            params.addProperty("id", BuiltInRegistries.ITEM.getKey(item).toString());
            return addCondition(Ritual.ConditionTypes.OFFHAND, params);
        }

        private RitualBuilder minMax(Ritual.ConditionTypes conditions, float min, float max) {
            JsonObject params = new JsonObject();
            params.addProperty("min", min);
            params.addProperty("max", max);
            return addCondition(conditions, params);
        }

        private RitualBuilder addCondition(Ritual.ConditionTypes type, JsonObject params) {
            this.conditions.add(new Ritual.Conditions(type, params));
            return this;
        }

        public void save() {
            Ritual ritual = getRitual();
            RegistryOps<JsonElement> ops = RegistryOps.create(JsonOps.INSTANCE, this.provider.lookup);
            JsonObject finalJson = Ritual.CODEC.encodeStart(ops, ritual).getOrThrow().getAsJsonObject();

            JsonObject keysObj = finalJson.getAsJsonObject("key");
            if (keysObj != null)
                for (Map.Entry<String, JsonElement> entry : keysObj.entrySet())
                    keysObj.add(entry.getKey(), fixIngredientFormat(entry.getValue()));

            finalJson.addProperty("type", "potions-n-rituals:ritual");

            JsonObject resultObj = finalJson.getAsJsonObject("result");
            if (resultObj == null) {
                resultObj = new JsonObject();
                finalJson.add("result", resultObj);
            }

            if (this.resultItemStackId != null) {
                JsonObject itemObj = new JsonObject();
                itemObj.addProperty("id", this.resultItemStackId);
                if (this.resultItemStackCount != 1) itemObj.addProperty("count", this.resultItemStackCount);
                if (this.componentsBuilder != null) {
                    DataComponentPatch patch = this.componentsBuilder.build();
                    if (!patch.isEmpty()) itemObj.add("components", DataComponentPatch.CODEC.encodeStart(ops, patch).getOrThrow());
                }
                resultObj.add("item_stack", itemObj);
            }

            if (this.containerItems != null && !this.containerItems.isEmpty()) {
                JsonArray arr = new JsonArray();
                for (DatagenItem dItem : this.containerItems) {
                    JsonObject itemObj = new JsonObject();
                    itemObj.addProperty("id", BuiltInRegistries.ITEM.getKey(dItem.item()).toString());
                    if (dItem.count() != 1) itemObj.addProperty("count", dItem.count());
                    if (dItem.components() != null && !dItem.components().isEmpty())
                        itemObj.add("components", DataComponentPatch.CODEC.encodeStart(ops, dItem.components()).getOrThrow());
                    arr.add(itemObj);
                }
                resultObj.add("container_items", arr);
            }

            this.provider.addRecipe(this.recipeId, finalJson);
        }

        private @NonNull Ritual getRitual() {
            Ritual.Result ritualResult = new Ritual.Result(
                    Optional.ofNullable(this.resultItem),
                    Optional.empty(),
                    Optional.ofNullable(this.resultBlock),
                    Optional.empty(),
                    Optional.ofNullable(this.resultEntity),
                    Optional.ofNullable(this.resultCustom),
                    Optional.ofNullable(this.resultCount)
            );

            return new Ritual(this.duration, this.keys, this.pattern, Optional.ofNullable(this.catalyst), this.conditions, Optional.ofNullable(this.duringAction), ritualResult);
        }

        private JsonElement fixIngredientFormat(JsonElement element) {
            if (element.isJsonArray()) {
                JsonArray arr = new JsonArray();
                for (JsonElement e : element.getAsJsonArray()) arr.add(normalizeEntry(e));
                return arr;
            }
            if (element.isJsonObject() && element.getAsJsonObject().has("fabric:type")) {
                JsonObject obj = element.getAsJsonObject();
                if (obj.has("base")) obj.add("base", fixIngredientFormat(obj.get("base")));
                return obj;
            }
            JsonArray arr = new JsonArray();
            arr.add(element);
            return arr;
        }

        private JsonElement normalizeEntry(JsonElement element) {
            if (element.isJsonObject()) {
                JsonObject obj = element.getAsJsonObject();
                if (obj.has("fabric:type") && obj.has("base")) obj.add("base", normalizeEntry(obj.get("base")));
                return obj;
            }
            return element;
        }
    }
}

