package com.matibi.potionsnrituals.util;

import com.matibi.potionsnrituals.PotionsNRituals;
import com.matibi.potionsnrituals.book.BookPage;
import com.matibi.potionsnrituals.book.BookStructure;
import com.matibi.potionsnrituals.item.custom.alchemicalStone.AlchemicalStone;
import com.matibi.potionsnrituals.potion.PotionIconHelper;
import com.matibi.potionsnrituals.ritual.RitualManager;
import com.matibi.potionsnrituals.ritual.datagen.Ritual;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.ClientRecipeBook;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.recipebook.RecipeCollection;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class BookUtils {
    public static String getIdString(Holder<?> holder) {
        return holder.unwrapKey()
                .map(key -> key.identifier().getPath())
                .orElse("unknown");
    }

    public static String getIdString(Item item) {
        return BuiltInRegistries.ITEM.getResourceKey(item)
                .map(key -> key.identifier().toString())
                .orElse("unknown");
    }

    public static ItemStack getItemStack(Holder<Potion> potion) {
        ItemStack itemStack = new ItemStack(Items.POTION);
        itemStack.set(DataComponents.POTION_CONTENTS, new PotionContents(potion));
        return itemStack;
    }

    public static String getName(Holder<Potion> potion) {
        return getItemStack(potion).getHoverName().getString();
    }

    public static String getName(Item item) {
        return new ItemStack(item).getHoverName().getString();
    }

    public static String getName(ItemStack itemStack) {
        return itemStack.getHoverName().getString();
    }

    public static String getEffectName(Holder<Potion> potion) {
        if (potion == Potions.TURTLE_MASTER)
            return Component.translatable("effect.potions-n-rituals.turtle_master").getString();
        List<MobEffectInstance> effects = potion.value().getEffects();
        if (effects.isEmpty())
            return "Unknown";
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
    public static BookPage createStonePage(Holder<AlchemicalStone> stone, String body) {
        Holder<Potion> potion = AlchemicalStone.getPotion(stone);
        return new BookPage.ImagePage(
                getIdString(stone),
                Component.translatable("§l" + getName(AlchemicalStone.getItemStack(stone))),
                List.of(
                        BookPage.Image.fromItem(AlchemicalStone.getItemStack(stone)),
                        BookPage.Image.fromTexture(getEffectTexture(potion), 64, 64, null, 0xFFE6D8BA)
                ),
                body != null ? Component.translatable(body) : null
        );
    }

    @Environment(EnvType.CLIENT)
    public static BookPage createIllustrationPage(String idString) {
        Identifier id = ModUtils.id("textures/gui/pages/" + idString + ".png");
        return new BookPage.ImagePage(id.getPath(), Component.empty(),
                List.of(
                        BookPage.Image.fromTexture(id,
                                110, 145, null, 0x00000000)
                ), Component.empty());
    }

    @Environment(EnvType.CLIENT)
    public static BookPage createRitualPage(String title, String ritualId, String description) {
        Ritual ritual = RitualManager.getAllRituals().get(ModUtils.id(ritualId));

        if (ritual == null) {
            PotionsNRituals.LOGGER.error("Impossible de créer la page du livre : Le rituel '{}' n'est pas chargé !", ritualId);
            return new BookPage.TextPage(ritualId, Component.translatable(title), Component.literal("§cErreur: Rituel introuvable."));
        }

        String pageId = ritualId.toLowerCase();
        List<BookPage.Image> gridImages = new ArrayList<>();

        Identifier pedestalTexture = ModUtils.id("textures/gui/pedestal.png");

        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null)
            return new BookPage.ImagePage(pageId, Component.translatable(title), gridImages, Component.translatable(description));

        ContextMap contextMap = SlotDisplayContext.fromLevel(mc.level);

        for (String row : ritual.pattern()) {
            for (char c : row.toCharArray()) {
                if (c == ' ') {
                    gridImages.add(new BookPage.Image(null, null, null, 16, 16, "", 0));
                    continue;
                }

                String key = String.valueOf(c);
                Ingredient ing = ritual.keys().get(key);

                if (ing != null && !ing.isEmpty()) {
                    ItemStack stack = ing.display().resolveForFirstStack(contextMap);

                    if (!stack.isEmpty()) {
                        Item item = stack.getItem();
                        Block block = Block.byItem(item);
                        if (block != Blocks.AIR)
                            gridImages.add(BookPage.Image.fromBlockState(block.defaultBlockState()));
                        else
                            gridImages.add(BookPage.Image.compound(pedestalTexture, stack));
                    } else
                        gridImages.add(new BookPage.Image(null, null, null, 16, 16, "", 0));
                } else
                    gridImages.add(new BookPage.Image(null, null, null, 16, 16, "", 0));
            }
        }

        return new BookPage.ImagePage(
                pageId,
                Component.translatable(title),
                gridImages,
                Component.translatable(description)
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
    public static void createRitualChapter(BookStructure.Chapter sub, String title, String ritualId, String description) {
        sub .page(createRitualPage(title, ritualId, ""))
            .page(new BookPage.TextPage(null, Component.translatable(title), Component.translatable(description)));
    }

    @Environment(EnvType.CLIENT)
    public static void createTalismanChapter(BookStructure.Chapter sub, Item item, String explanation, String recipe) {
        String id = getIdString(item);
        sub .page(new BookPage.ImagePage(id, Component.literal("§l" + getName(item)),
                        List.of(BookPage.Image.fromItem(new ItemStack(item))),
                        Component.translatable(explanation)))
                .page(createCraftingPage(id + "_craft", "How to obtain", item, recipe));
    }

    @Environment(EnvType.CLIENT)
    public static void createTalismanThroughRitualChapter(BookStructure.Chapter sub, Item item, String explanation) {
        String id = getIdString(item);
        String idRitual = id.split(":")[1] + "_ritual";
        sub .page(new BookPage.ImagePage(id, Component.literal("§l" + getName(item)),
                        List.of(BookPage.Image.fromItem(new ItemStack(item))),
                        Component.translatable(explanation)))
                .page(createRitualPage("How to obtain", idRitual, ""));
    }

    @Environment(EnvType.CLIENT)
    private static RecipeDisplay getRecipeDisplay(Item item, RecipeType<?> targetType) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null || mc.player == null) return null;

        ContextMap contextMap = SlotDisplayContext.fromLevel(mc.level);
        ClientRecipeBook recipeBook = mc.player.getRecipeBook();

        for (RecipeCollection collection : recipeBook.getCollections()) {
            for (RecipeDisplayEntry entry : collection.getRecipes()) {
                RecipeDisplay display = entry.display();

                boolean matchesCategory =
                        (targetType == RecipeType.CRAFTING &&
                                (display instanceof ShapedCraftingRecipeDisplay || display instanceof ShapelessCraftingRecipeDisplay))
                                || (targetType == RecipeType.SMELTING && display instanceof FurnaceRecipeDisplay);

                if (!matchesCategory) continue;

                ItemStack outputStack = display.result().resolveForFirstStack(contextMap);
                if (!outputStack.isEmpty() && outputStack.getItem() == item)
                    return display;
            }
        }
        return null;
    }

    @Environment(EnvType.CLIENT)
    public static BookPage createCraftingPage(String pageId, String title, Item item, String description) {
        Minecraft mc = Minecraft.getInstance();
        ClientLevel level = mc.level;
        if (level == null) return new BookPage.TextPage(pageId, Component.translatable(title), Component.translatable(description));

        RecipeDisplay display = getRecipeDisplay(item, RecipeType.CRAFTING);
        if (display == null)
            return new BookPage.TextPage(pageId, Component.translatable(title), Component.translatable(description));

        ContextMap contextMap = SlotDisplayContext.fromLevel(level);
        ItemStack output = display.result().resolveForFirstStack(contextMap);

        List<ItemStack> inputs = new ArrayList<>();
        if (display instanceof ShapedCraftingRecipeDisplay shaped)
            for (SlotDisplay slot : shaped.ingredients())
                inputs.add(slot.resolveForFirstStack(contextMap));
        else if (display instanceof ShapelessCraftingRecipeDisplay shapeless)
            for (SlotDisplay slot : shapeless.ingredients())
                inputs.add(slot.resolveForFirstStack(contextMap));
        else
            return new BookPage.TextPage(pageId, Component.translatable(title), Component.translatable(description));

        while (inputs.size() < 9) inputs.add(ItemStack.EMPTY);
        return new BookPage.RecipePage(pageId, Component.translatable(title), BookPage.Recipe.crafting(inputs, output), Component.translatable(description));
    }

    @Environment(EnvType.CLIENT)
    public static BookPage createFurnacePage(String pageId, String title, Item item, String description) {
        Minecraft mc = Minecraft.getInstance();
        ClientLevel level = mc.level;
        if (level == null) return new BookPage.TextPage(pageId, Component.translatable(title), Component.translatable(description));

        RecipeDisplay display = getRecipeDisplay(item, RecipeType.SMELTING);
        if (!(display instanceof FurnaceRecipeDisplay furnaceDisplay))
            return new BookPage.TextPage(pageId, Component.translatable(title), Component.translatable(description));

        ContextMap contextMap = SlotDisplayContext.fromLevel(level);
        ItemStack output = furnaceDisplay.result().resolveForFirstStack(contextMap);
        ItemStack input = furnaceDisplay.ingredient().resolveForFirstStack(contextMap);

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
