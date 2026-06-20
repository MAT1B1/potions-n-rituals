package com.matibi.potionsnrituals.book;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.Nullable;

import java.util.List;

public record BookPage(@Nullable String id, @Nullable Component title, List<PageImage> images, @Nullable Component bodyText) {

    public static BookPage Empty(String id) {
        return new BookPage(id, null, List.of(), null);
    }

    public BookPage {
        if (images == null) images = List.of();
        if (images.size() > 2) {
            throw new IllegalArgumentException("Max 2 images par page, reçu: " + images.size());
        }
        images = List.copyOf(images);
    }

    public record PageImage(@Nullable Identifier texture, @Nullable ItemStack itemStack, int width, int height, @Nullable String caption) {

        public PageImage {
            if ((texture == null) == (itemStack == null)) {
                throw new IllegalArgumentException("PageImage doit avoir soit texture, soit itemStack — pas les deux, pas aucun");
            }
        }

        public static PageImage fromTexture(Identifier texture, int width, int height, @Nullable String caption) {
            return new PageImage(texture, null, width, height, caption);
        }

        public static PageImage fromItem(ItemStack stack, @Nullable String caption) {
            return new PageImage(null, stack, 64, 64, caption);
        }
    }
}