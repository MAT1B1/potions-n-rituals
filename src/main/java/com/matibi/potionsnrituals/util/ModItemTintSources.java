package com.matibi.potionsnrituals.util;

import com.matibi.potionsnrituals.PotionsNRituals;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.color.item.ItemTintSources;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionContents;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class ModItemTintSources {

    public static final MapCodec<TintItemColor> TINT_POTION_CODEC = MapCodec.unit(new TintItemColor(0xFFFFFFFF));

    public static void register() {
        ItemTintSources.ID_MAPPER.put(
                Identifier.fromNamespaceAndPath(PotionsNRituals.MOD_ID, "tint_potion"),
                TINT_POTION_CODEC
        );
    }

    public record TintItemColor(int defaultColor) implements ItemTintSource {
        @Override
        public int calculate(@NonNull ItemStack itemStack, @Nullable ClientLevel level, @Nullable LivingEntity owner) {
            PotionContents contents = itemStack.get(DataComponents.POTION_CONTENTS);
            if (contents == null || contents.equals(PotionContents.EMPTY)) return defaultColor;
            return contents.getColor();
        }

        @Override
        public @NonNull MapCodec<? extends ItemTintSource> type() {
            return TINT_POTION_CODEC;
        }
    }
}