package com.matibi.potionsnrituals.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.matibi.potionsnrituals.PotionsNRituals;
import com.matibi.potionsnrituals.book.BookPage;
import com.matibi.potionsnrituals.book.BookStructure;
import com.matibi.potionsnrituals.potion.PotionIconHelper;
import com.mojang.serialization.JsonOps;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.context.ContextMap;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.crafting.display.*;

import java.io.Reader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class BookUtils {
    public static String getIdString(Holder<Potion> potion) {
        return potion.unwrapKey()
                .map(key -> key.identifier().getPath())
                .orElse("unknown");
    }

    public static ItemStack getItemStack(Holder<Potion> potion) {
        ItemStack itemStack = new ItemStack(Items.POTION);
        itemStack.set(DataComponents.POTION_CONTENTS, new PotionContents(potion));
        return itemStack;
    }

    public static String getName(Holder<Potion> potion) {
        String res = getItemStack(potion).getDisplayName().getString();
        return res.substring(1, res.length() - 1);
    }

    public static String getEffectName(Holder<Potion> potion) {
        List<MobEffectInstance> effects = potion.value().getEffects();
        if (effects.isEmpty()) {
            return "Unknown";
        }
        Holder<MobEffect> effect = effects.getFirst().getEffect();
        return Component.translatable(effect.value().getDescriptionId()).getString();
    }

    public static Identifier getEffectTexture(Holder<Potion> potion) {
        Identifier id = PotionIconHelper.getEffectSpriteId(getItemStack(potion));
        return id == null ? ModUtils.id("textures/mob_effect/" + getIdString(potion) + ".png")
                : id.withPrefix("textures/").withSuffix(".png");
    }

    @Environment(EnvType.CLIENT)
    public static BookPage createStandardPage(String id, String title, String body) {
        return new BookPage.TextPage(
                id,
                Component.translatable(title),
                body != null ? Component.translatable(body) : null
        );
    }

    @Environment(EnvType.CLIENT)
    public static BookPage createPotionPage(Holder<Potion> potion, String body) {
        return new BookPage.ImagePage(
               getIdString(potion),
                Component.translatable("§l" + getName(potion)),
                List.of(
                        BookPage.Image.fromItem(getItemStack(potion)),
                        BookPage.Image.fromTexture(getEffectTexture(potion), 64, 64, null, 0xFFE6D8BA)
                ),
                body != null ? Component.translatable(body) : null
        );
    }

    @Environment(EnvType.CLIENT)
    public static void createPotionChapter(BookStructure.Chapter sub, Holder<Potion> potion,
                                           String resume, String explanation, String brew) {
        String id = getIdString(potion);
        sub .page(createPotionPage(potion, resume))
            .page(createStandardPage(id + "_explanation", "How it works", explanation))
            .page(createBrewingPage(
                    id + "_brewing",
                    "How to obtain",
                    potion,
                    brew))
            .page(new BookPage.EmptyPage());
    }

    @Environment(EnvType.CLIENT)
    private static Recipe<?> loadRecipeFromJson(Item item) {
        Minecraft mc = Minecraft.getInstance();
        Optional<ResourceKey<Item>> optid = item.getDefaultInstance().typeHolder().unwrapKey();
        if (optid.isEmpty()) return null;

        Identifier recipeId = optid.get().identifier();
        try {
            Path generatedPath = FabricLoader.getInstance().getGameDir()
                    .getParent()
                    .resolve("src/main/generated/data")
                    .resolve(recipeId.getNamespace())
                    .resolve("recipe")
                    .resolve(recipeId.getPath() + ".json");

            if (Files.exists(generatedPath)) {
                try (Reader reader = Files.newBufferedReader(generatedPath)) {
                    JsonElement json = JsonParser.parseReader(reader);
                    if (mc.level == null) return null;
                    return Recipe.CODEC.parse(mc.level.registryAccess().createSerializationContext(JsonOps.INSTANCE), json)
                            .result()
                            .orElse(null);
                }
            }
        } catch (Exception e) {
            PotionsNRituals.LOGGER.warn("[POTIONS] Erreur lors de la lecture du JSON pour {}", recipeId);
        }
        return null;
    }

    @Environment(EnvType.CLIENT)
    public static BookPage createCraftingPage(String pageId, String title, Item item, String description) {
        Minecraft mc = Minecraft.getInstance();
        ClientLevel level = mc.level;
        if (level == null) return new BookPage.TextPage(pageId, Component.translatable(title), Component.translatable(description));

        Recipe<?> recipe = loadRecipeFromJson(item);
        if (recipe == null || recipe.display().isEmpty()) {
            return new BookPage.TextPage(pageId, Component.translatable(title), Component.translatable(description));
        }

        RecipeDisplay display = recipe.display().getFirst();
        ContextMap contextMap = SlotDisplayContext.fromLevel(level);
        ItemStack output = display.result().resolveForFirstStack(contextMap);

        List<ItemStack> inputs = new ArrayList<>();
        if (display instanceof ShapedCraftingRecipeDisplay shaped) {
            for (SlotDisplay slot : shaped.ingredients()) {
                inputs.add(slot instanceof SlotDisplay.Empty ? ItemStack.EMPTY : slot.resolveForFirstStack(contextMap));
            }
        } else if (display instanceof ShapelessCraftingRecipeDisplay shapeless) {
            for (SlotDisplay slot : shapeless.ingredients()) {
                if (!(slot instanceof SlotDisplay.Empty)) inputs.add(slot.resolveForFirstStack(contextMap));
            }
        } else {
            return new BookPage.TextPage(pageId, Component.translatable(title), Component.translatable(description));
        }

        while (inputs.size() < 9) inputs.add(ItemStack.EMPTY);
        return new BookPage.RecipePage(pageId, Component.translatable(title), BookPage.Recipe.crafting(inputs, output), Component.translatable(description));
    }

    @Environment(EnvType.CLIENT)
    public static BookPage createFurnacePage(String pageId, String title, Item item, String description) {
        Minecraft mc = Minecraft.getInstance();
        ClientLevel level = mc.level;
        if (level == null) return new BookPage.TextPage(pageId, Component.translatable(title), Component.translatable(description));

        Recipe<?> recipe = loadRecipeFromJson(item);
        if (recipe == null || recipe.display().isEmpty()) {
            return new BookPage.TextPage(pageId, Component.translatable(title), Component.translatable(description));
        }

        RecipeDisplay display = recipe.display().getFirst();
        if (!(display instanceof FurnaceRecipeDisplay furnaceDisplay)) {
            return new BookPage.TextPage(pageId, Component.translatable(title), Component.translatable(description));
        }

        ContextMap contextMap = SlotDisplayContext.fromLevel(level);
        ItemStack output = furnaceDisplay.result().resolveForFirstStack(contextMap);

        ItemStack input = ItemStack.EMPTY;
        SlotDisplay slot = furnaceDisplay.ingredient();
        if (!(slot instanceof SlotDisplay.Empty)) {
            input = slot.resolveForFirstStack(contextMap);
        }

        return new BookPage.RecipePage(pageId, Component.translatable(title), BookPage.Recipe.furnace(input, output), Component.translatable(description));
    }

    @Environment(EnvType.CLIENT)
    public static BookPage createBrewingPage(String pageId, String title, Holder<Potion> potion, String description) {
        Minecraft mc = Minecraft.getInstance();
        ClientLevel level = mc.level;
        if (level == null) return new BookPage.TextPage(pageId, Component.translatable(title), Component.translatable(description));

        ItemStack ingredient = ItemStack.EMPTY;
        ItemStack inputPotion = new ItemStack(Items.POTION);
        inputPotion.set(DataComponents.POTION_CONTENTS, new PotionContents(Potions.WATER));

        ItemStack outputPotion = new ItemStack(Items.POTION);
        outputPotion.set(DataComponents.POTION_CONTENTS, new PotionContents(potion));

        PotionBrewing brewingRegistry = level.potionBrewing();

        try {
            Field field = PotionBrewing.class.getDeclaredField("potionMixes");
            field.setAccessible(true);
            List<?> mixes = (List<?>) field.get(brewingRegistry);

            for (Object mixObj : mixes) {
                Method toMethod = mixObj.getClass().getDeclaredMethod("to");
                toMethod.setAccessible(true);
                Holder<?> toHolder = (Holder<?>) toMethod.invoke(mixObj);

                if (toHolder.equals(potion)) {
                    Method fromMethod = mixObj.getClass().getDeclaredMethod("from");
                    fromMethod.setAccessible(true);

                    @SuppressWarnings("unchecked")
                    Holder<Potion> fromHolder = (Holder<Potion>) fromMethod.invoke(mixObj);

                    inputPotion = new ItemStack(Items.POTION);
                    inputPotion.set(DataComponents.POTION_CONTENTS, new PotionContents(fromHolder));

                    Method ingredientMethod = mixObj.getClass().getDeclaredMethod("ingredient");
                    ingredientMethod.setAccessible(true);
                    Ingredient ing = (Ingredient) ingredientMethod.invoke(mixObj);

                    Method itemsMethod = Ingredient.class.getDeclaredMethod("items");
                    itemsMethod.setAccessible(true);

                    @SuppressWarnings("unchecked")
                    Stream<Holder<Item>> itemStream = (Stream<Holder<Item>>) itemsMethod.invoke(ing);

                    Optional<Holder<Item>> firstItem = itemStream.findFirst();
                    if (firstItem.isPresent())
                        ingredient = new ItemStack(firstItem.get().value());
                    break;
                }
            }
        } catch (Exception e) {
            PotionsNRituals.LOGGER.error("[POTIONS] Impossible d'accéder aux recettes d'alambic via Reflection", e);
        }

        return new BookPage.RecipePage(pageId, Component.translatable(title), BookPage.Recipe.brewing(ingredient, inputPotion, outputPotion), Component.translatable(description));
    }
}
