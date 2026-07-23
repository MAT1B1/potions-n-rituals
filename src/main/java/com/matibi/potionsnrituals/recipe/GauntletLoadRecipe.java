package com.matibi.potionsnrituals.recipe;

import com.matibi.potionsnrituals.item.ModItems;
import com.matibi.potionsnrituals.item.custom.talisman.GauntletItem;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;

public class GauntletLoadRecipe extends CustomRecipe {

    public static final MapCodec<GauntletLoadRecipe> MAP_CODEC = MapCodec.unit(new GauntletLoadRecipe());
    public static final StreamCodec<RegistryFriendlyByteBuf, GauntletLoadRecipe> STREAM_CODEC = StreamCodec.unit(new GauntletLoadRecipe());
    public static final RecipeSerializer<GauntletLoadRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);

    public GauntletLoadRecipe() {
        super();
    }

    @Override
    public boolean matches(CraftingInput input, @NonNull Level level) {
        int gauntletCount = 0;
        int stoneCount = 0;
        ItemStack gauntlet = ItemStack.EMPTY;

        for (int slot = 0; slot < input.size(); slot++) {
            ItemStack stack = input.getItem(slot);
            if (stack.isEmpty()) continue;

            if (stack.getItem() instanceof GauntletItem) {
                gauntletCount++;
                gauntlet = stack;
            } else if (stack.is(ModItems.ALCHEMICAL_STONE))
                stoneCount++;
            else
                return false;
        }

        if (gauntletCount != 1 || stoneCount == 0) return false;

        PotionContents contents = gauntlet.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
        int currentEffectsSize = 0;
        for (MobEffectInstance ignored : contents.customEffects())
            currentEffectsSize++;

        return (currentEffectsSize + stoneCount) <= GauntletItem.MAX_STONES;
    }

    @Override
    public @NonNull ItemStack assemble(CraftingInput input) {
        ItemStack gauntlet = ItemStack.EMPTY;
        List<MobEffectInstance> additionalEffects = new ArrayList<>();

        for (int slot = 0; slot < input.size(); slot++) {
            ItemStack stack = input.getItem(slot);
            if (stack.isEmpty()) continue;

            if (stack.getItem() instanceof GauntletItem)
                gauntlet = stack;
            else if (stack.is(ModItems.ALCHEMICAL_STONE)) {
                PotionContents stoneContents = stack.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
                stoneContents.getAllEffects().forEach(additionalEffects::add);
            }
        }

        if (gauntlet.isEmpty()) return ItemStack.EMPTY;

        ItemStack result = gauntlet.copyWithCount(1);
        PotionContents existing = gauntlet.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);

        List<MobEffectInstance> combinedEffects = new ArrayList<>();
        combinedEffects.addAll(existing.customEffects());
        combinedEffects.addAll(additionalEffects);

        result.set(DataComponents.POTION_CONTENTS, new PotionContents(
                existing.potion(),
                existing.customColor(),
                combinedEffects,
                existing.customName()
        ));

        return result;
    }

    @Override
    public @NonNull RecipeSerializer<GauntletLoadRecipe> getSerializer() {
        return SERIALIZER;
    }
}