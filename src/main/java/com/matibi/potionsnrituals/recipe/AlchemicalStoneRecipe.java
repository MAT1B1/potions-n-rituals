package com.matibi.potionsnrituals.recipe;

import com.matibi.potionsnrituals.item.ModItems;
import com.matibi.potionsnrituals.item.custom.alchemicalStone.AlchemicalStone;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

import com.matibi.potionsnrituals.config.ModConfig;

public class AlchemicalStoneRecipe extends CustomRecipe {

    public static final MapCodec<AlchemicalStoneRecipe> MAP_CODEC =
            MapCodec.unit(new AlchemicalStoneRecipe());

    public static final StreamCodec<RegistryFriendlyByteBuf, AlchemicalStoneRecipe> STREAM_CODEC =
            StreamCodec.unit(new AlchemicalStoneRecipe());

    public static final RecipeSerializer<AlchemicalStoneRecipe> SERIALIZER =
            new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);

    @Override
    public boolean matches(CraftingInput input, @NonNull Level level) {
        if (input.width() == 3 && input.height() == 3 && input.ingredientCount() == 9) {
            for (int i = 0; i < input.height(); i++) {
                for (int j = 0; j < input.width(); j++) {
                    ItemStack stack = input.getItem(j + i * input.width());
                    if (stack.isEmpty()) return false;
                    if (j == 1 && i == 1) {
                        if (!stack.is(Items.POTION)) return false;
                        PotionContents contents = stack.get(DataComponents.POTION_CONTENTS);
                        if (contents != null) {
                            if (contents.potion().isPresent()
                                    && ModConfig.get().isPotionBlacklisted(contents.potion().get())) return false;
                            if (contents.customEffects().stream()
                                    .anyMatch(e -> ModConfig.get().isEffectBlacklisted(e.getEffect()))) return false;
                        }
                    } else if (!stack.is(ModItems.ALCHEMICAL_STONE)) return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public @NonNull ItemStack assemble(CraftingInput input) {
        ItemStack center = input.getItem(1 + input.width());
        if (!center.is(Items.POTION)) return ItemStack.EMPTY;
        PotionContents contents = center.get(DataComponents.POTION_CONTENTS);
        return AlchemicalStone.getItemStack(contents, 8);
    }

    @Override
    public @NonNull RecipeSerializer<AlchemicalStoneRecipe> getSerializer() {
        return SERIALIZER;
    }
}