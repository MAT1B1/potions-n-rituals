package com.matibi.potionsnrituals.datacomponent;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import java.util.Collections;
import java.util.List;

public record PersonalBookmark(int pageIndex, int color) {
    public static final int[] ALLOWED_COLORS = { 0xFF5555FF, 0xFFAA00AA, 0xFFFFAA00, 0xFF55FFFF };
    public static final int MAX_BOOKMARKS = ALLOWED_COLORS.length;

    private static boolean isAllowedColor(int color) {
        for (int allowed : ALLOWED_COLORS) {
            if (allowed == color) return true;
        }
        return false;
    }

    public static boolean isValidList(java.util.List<PersonalBookmark> bookmarks) {
        if (bookmarks.size() > MAX_BOOKMARKS) return false;
        java.util.Set<Integer> seenColors = new java.util.HashSet<>();
        for (PersonalBookmark bm : bookmarks) {
            if (bm.pageIndex() < 0) return false;
            if (!isAllowedColor(bm.color())) return false;
            if (!seenColors.add(bm.color())) return false;
        }
        return true;
    }

    public static final Codec<PersonalBookmark> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("page").forGetter(PersonalBookmark::pageIndex),
            Codec.INT.fieldOf("color").forGetter(PersonalBookmark::color)
    ).apply(instance, PersonalBookmark::new));

    public static final StreamCodec<ByteBuf, PersonalBookmark> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, PersonalBookmark::pageIndex,
            ByteBufCodecs.VAR_INT, PersonalBookmark::color,
            PersonalBookmark::new
    );

    public static void registerTooltip() {
        ItemTooltipCallback.EVENT.register((itemStack, _, _, list) -> {
            List<PersonalBookmark> bookmarks = itemStack.getComponents().getOrDefault(ModDataComponents.PERSONAL_BOOKMARKS, Collections.emptyList());

            int count = bookmarks.size();
            if (count == 0) return;

            MutableComponent currentCount = Component.literal(String.valueOf(count))
                    .withStyle(ChatFormatting.BLUE);

            list.add(
                    Component.translatable("tooltip.potions-n-rituals.bookmarks_count_line", currentCount)
            );
        });
    }
}