package com.matibi.potionsnrituals.recipe;

import com.matibi.potionsnrituals.item.ModItems;
import com.matibi.potionsnrituals.util.CombinationUtils;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.effect.MobEffectInstance;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CombinationRecipe extends CustomRecipe {

    public static final MapCodec<CombinationRecipe> MAP_CODEC = MapCodec.unit(new CombinationRecipe());
    public static final StreamCodec<RegistryFriendlyByteBuf, CombinationRecipe> STREAM_CODEC = StreamCodec.unit(new CombinationRecipe());
    public static final RecipeSerializer<CombinationRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);

    @Override
    public boolean matches(CraftingInput input, @NonNull Level level) {
        int validPotionCount = 0;
        Item basePotionItem = null;

        for (int slot = 0; slot < input.size(); slot++) {
            ItemStack stack = input.getItem(slot);
            if (!stack.isEmpty()) {
                if (!isPotionItem(stack)) return false;

                PotionContents component = stack.get(DataComponents.POTION_CONTENTS);
                if (component == null || !component.hasEffects() || stack.getItem() == ModItems.ALCHEMICAL_STONE)
                    return false;

                if (basePotionItem == null) basePotionItem = stack.getItem();
                else if (stack.getItem() != basePotionItem) return false;

                validPotionCount++;
            }
        }
        return validPotionCount > 1;
    }

    private boolean isPotionItem(ItemStack stack) {
        return stack.is(Items.POTION)
                || stack.is(Items.SPLASH_POTION)
                || stack.is(Items.LINGERING_POTION)
                || stack.is(ModItems.ALCHEMICAL_STONE);
    }

    @Override
    public @NonNull ItemStack assemble(CraftingInput input) {
        Item firstPotion = null;
        List<MobEffectInstance> effects = new ArrayList<>();

        for (int slot = 0; slot < input.size(); slot++) {
            ItemStack stack = input.getItem(slot);
            if (!stack.isEmpty() && isPotionItem(stack)) {
                if (firstPotion == null) firstPotion = stack.getItem();
                PotionContents contents = stack.get(DataComponents.POTION_CONTENTS);
                if (contents != null)
                    contents.getAllEffects().forEach(effects::add);
            }
        }
        if (firstPotion == null) return ItemStack.EMPTY;

        ItemStack result = new ItemStack(firstPotion);
        result.set(DataComponents.POTION_CONTENTS, new PotionContents(
                Optional.empty(), Optional.empty(),
                CombinationUtils.combineEffect(effects),
                Optional.of("mixed")
        ));
        return result.copyWithCount(input.ingredientCount());
    }

    @Override
    public @NonNull RecipeSerializer<CombinationRecipe> getSerializer() {
        return SERIALIZER;
    }
}