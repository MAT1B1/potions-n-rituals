package com.matibi.potionsnrituals.util;

import com.matibi.potionsnrituals.book.BookPage;
import com.matibi.potionsnrituals.potion.PotionIconHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;

import java.util.List;

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
        if (body != null)
            return new BookPage(id, Component.literal(title), List.of(), Component.literal(body));
        return new BookPage(id, Component.literal(title), List.of(), null);
    }

    @Environment(EnvType.CLIENT)
    public static BookPage createPotionPage(Holder<Potion> potion, String body) {
        return new BookPage(
                BookUtils.getIdString(potion),
                Component.literal("§l" + BookUtils.getName(potion)),
                List.of(
                        BookPage.PageImage.fromItem(BookUtils.getItemStack(potion), null),
                        BookPage.PageImage.fromTexture(BookUtils.getEffectTexture(potion), 64, 64, null)
                ),
                Component.literal(body)
        );
    }
}
