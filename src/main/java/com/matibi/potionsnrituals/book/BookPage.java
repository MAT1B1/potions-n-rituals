package com.matibi.potionsnrituals.book;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.Nullable;

import java.util.List;

public sealed interface BookPage permits
        BookPage.EmptyPage,
        BookPage.ImagePage,
        BookPage.RecipePage,
        BookPage.TextPage
{
    @Nullable String id();
    @Nullable Component title();

    record TextPage(
            @Nullable String id,
            @Nullable Component title,
            @Nullable Component bodyText
    ) implements BookPage {}

    record ImagePage(
            @Nullable String id,
            @Nullable Component title,
            List<Image> images,
            @Nullable Component bodyText
    ) implements BookPage {
        public ImagePage {
            if (images == null) images = List.of();
            images = List.copyOf(images);
        }
    }

    record EmptyPage(String id) implements BookPage {

        public EmptyPage() {
            this("blank_" + System.nanoTime());
        }

        @Override
        public @Nullable Component title() {
            return null;
        }
    }

    record RecipePage(
            @Nullable String id,
            @Nullable Component title,
            Recipe recipe,
            @Nullable Component bodyText
    ) implements BookPage {}

    // --- Sous-Records utilitaires (Images et Recettes) ---
    record Image(@Nullable Identifier texture, @Nullable ItemStack itemStack, @Nullable BlockState blockState, int width, int height, @Nullable String caption, int bgColor) {
        public Image {
        }

        public static Image fromItem(ItemStack stack) {
            return new Image(null, stack, null, 64, 64, null, 0x00000000);
        }

        public static Image compound(Identifier bgTexture, ItemStack stack) {
            return new Image(bgTexture, stack, null, 64, 64, null, 0x00000000);
        }

        public static Image fromTexture(Identifier texture) {
            return new Image(texture, null, null, 64, 64, null, 0x00000000);
        }

        public static Image fromBlockState(BlockState state) {
            return new Image(null, null, state, 64, 64, null, 0x00000000);
        }

        public static Image fromItem(ItemStack stack, int width, int height, @Nullable String caption, int bgColor) {
            return new Image(null, stack, null, width, height, caption, bgColor);
        }

        public static Image compound(Identifier bgTexture, ItemStack stack, int width, int height, @Nullable String caption, int bgColor) {
            return new Image(bgTexture, stack, null, width, height, caption, bgColor);
        }

        public static Image fromTexture(Identifier texture, int width, int height, @Nullable String caption, int bgColor) {
            return new Image(texture, null, null, width, height, caption, bgColor);
        }

        public static Image fromBlockState(BlockState state, int width, int height, @Nullable String caption, int bgColor) {
            return new Image(null, null, state, width, height, caption, bgColor);
        }
    }

    record Recipe(Recipe.Type type, List<ItemStack> inputs, ItemStack output) {
        public enum Type {
            CRAFTING,
            FURNACE,
            BREWING
        }

        public static Recipe crafting(List<ItemStack> inputs, ItemStack output) {
            return new Recipe(Recipe.Type.CRAFTING, inputs, output);
        }

        public static Recipe furnace(ItemStack input, ItemStack output) {
            return new Recipe(Recipe.Type.FURNACE, List.of(input), output);
        }

        public static Recipe brewing(ItemStack ingredient, ItemStack potionInput, ItemStack potionOutput) {
            return new Recipe(Recipe.Type.BREWING, List.of(ingredient, potionInput), potionOutput);
        }
    }
}