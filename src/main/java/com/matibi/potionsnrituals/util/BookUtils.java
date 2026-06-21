package com.matibi.potionsnrituals.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.matibi.potionsnrituals.PotionsNRituals;
import com.matibi.potionsnrituals.book.BookPage;
import com.matibi.potionsnrituals.item.ModItems;
import com.matibi.potionsnrituals.potion.PotionIconHelper;
import com.mojang.serialization.JsonOps;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceProvider;
import net.minecraft.util.context.ContextMap;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.crafting.display.*;
import org.jspecify.annotations.NonNull;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public static Identifier getEffectTexture(Holder<Potion> potion) {
        Identifier id = PotionIconHelper.getEffectSpriteId(getItemStack(potion));
        return id == null ? null : id.withPrefix("textures/").withSuffix(".png");
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
                BookUtils.getIdString(potion),
                Component.translatable("§l" + BookUtils.getName(potion)),
                List.of(
                        BookPage.Image.fromItem(BookUtils.getItemStack(potion), null),
                        BookPage.Image.fromTexture(BookUtils.getEffectTexture(potion), 64, 64, null)
                ),
                body != null ? Component.translatable(body) : null
        );
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
        if (level == null) return new BookPage.TextPage(pageId, Component.literal(title), Component.literal(description));

        Recipe<?> recipe = loadRecipeFromJson(item);
        if (recipe == null || recipe.display().isEmpty()) {
            return new BookPage.TextPage(pageId, Component.literal(title), Component.literal(description));
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
            return new BookPage.TextPage(pageId, Component.literal(title), Component.literal(description));
        }

        while (inputs.size() < 9) inputs.add(ItemStack.EMPTY);
        return new BookPage.RecipePage(pageId, Component.literal(title), BookPage.Recipe.crafting(inputs, output), Component.literal(description));
    }

    @Environment(EnvType.CLIENT)
    public static BookPage createFurnacePage(String pageId, String title, Item item, String description) {
        Minecraft mc = Minecraft.getInstance();
        ClientLevel level = mc.level;
        if (level == null) return new BookPage.TextPage(pageId, Component.literal(title), Component.literal(description));

        Recipe<?> recipe = loadRecipeFromJson(item);
        if (recipe == null || recipe.display().isEmpty()) {
            return new BookPage.TextPage(pageId, Component.literal(title), Component.literal(description));
        }

        RecipeDisplay display = recipe.display().getFirst();
        if (!(display instanceof FurnaceRecipeDisplay furnaceDisplay)) {
            return new BookPage.TextPage(pageId, Component.literal(title), Component.literal(description));
        }

        ContextMap contextMap = SlotDisplayContext.fromLevel(level);
        ItemStack output = furnaceDisplay.result().resolveForFirstStack(contextMap);

        ItemStack input = ItemStack.EMPTY;
        SlotDisplay slot = furnaceDisplay.ingredient();
        if (!(slot instanceof SlotDisplay.Empty)) {
            input = slot.resolveForFirstStack(contextMap);
        }

        return new BookPage.RecipePage(pageId, Component.literal(title), BookPage.Recipe.furnace(input, output), Component.literal(description));
    }

    /*@Environment(EnvType.CLIENT)
    public static BookPage createBrewingPage(String pageId, String title, Item item, String description) {
        Minecraft mc = Minecraft.getInstance();
        ClientLevel level = mc.level;
        if (level == null) return new BookPage.TextPage(pageId, Component.literal(title), Component.literal(description));

        Recipe<?> recipe = loadRecipeFromJson(item);
        if (recipe == null || recipe.display().isEmpty()) {
            return new BookPage.TextPage(pageId, Component.literal(title), Component.literal(description));
        }

        RecipeDisplay display = recipe.display().getFirst();
        if (!(display instanceof BrewingRecipeDisplay brewingDisplay)) {
            return new BookPage.TextPage(pageId, Component.literal(title), Component.literal(description));
        }

        ContextMap contextMap = SlotDisplayContext.fromLevel(level);
        ItemStack output = brewingDisplay.result().resolveForFirstStack(contextMap);

        List<ItemStack> inputs = new ArrayList<>();
        SlotDisplay ingredientSlot = brewingDisplay.ingredient();
        inputs.add(ingredientSlot instanceof SlotDisplay.Empty ? ItemStack.EMPTY : ingredientSlot.resolveForFirstStack(contextMap));

        SlotDisplay inputPotionSlot = brewingDisplay.input();
        inputs.add(inputPotionSlot instanceof SlotDisplay.Empty ? ItemStack.EMPTY : inputPotionSlot.resolveForFirstStack(contextMap));

        return new BookPage.RecipePage(pageId, Component.literal(title), BookPage.Recipe.brewing(inputs, output), Component.literal(description));
    }*/
}
