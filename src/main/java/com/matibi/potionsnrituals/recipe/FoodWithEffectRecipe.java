package com.matibi.potionsnrituals.recipe;

import com.matibi.potionsnrituals.effect.ModEffects;
import com.matibi.potionsnrituals.util.CombinationUtils;
import com.mojang.serialization.MapCodec;
import it.unimi.dsi.fastutil.objects.ReferenceLinkedOpenHashSet;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class FoodWithEffectRecipe extends CustomRecipe {

    public static final MapCodec<FoodWithEffectRecipe> MAP_CODEC = MapCodec.unit(new FoodWithEffectRecipe());
    public static final StreamCodec<RegistryFriendlyByteBuf, FoodWithEffectRecipe> STREAM_CODEC = StreamCodec.unit(new FoodWithEffectRecipe());
    public static final RecipeSerializer<FoodWithEffectRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);


    public FoodWithEffectRecipe() {
        super();
    }

    @Override
    public boolean matches(CraftingInput input, @NonNull Level level) {
        int potionCount = 0;
        int foodCount = 0;

        for (int slot = 0; slot < input.size(); slot++) {
            ItemStack stack = input.getItem(slot);
            if (stack.isEmpty()) continue;

            if (isPotionItem(stack)) {
                if (stack.get(DataComponents.POTION_CONTENTS) == null) return false;
                potionCount++;
            } else if (isFood(stack)) {
                foodCount++;
            } else return false;

            if (foodCount > 1) return false;
        }
        return potionCount >= 1 && foodCount == 1;
    }

    private boolean isPotionItem(ItemStack stack) {
        return stack.is(Items.POTION) || stack.is(Items.SPLASH_POTION) || stack.is(Items.LINGERING_POTION);
    }

    @Override
    public @NonNull ItemStack assemble(CraftingInput input) {
        ItemStack food = ItemStack.EMPTY;
        AtomicBoolean hasMasking = new AtomicBoolean(false);

        for (int slot = 0; slot < input.size(); slot++) {
            ItemStack s = input.getItem(slot);
            if (!s.isEmpty() && isFood(s)) { food = s; break; }
        }
        if (food.isEmpty()) return ItemStack.EMPTY;

        List<MobEffectInstance> effects = new ArrayList<>();
        PotionContents existing = food.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
        existing.getAllEffects().forEach(e -> {
            if (e.getEffect().equals(ModEffects.MASKING)) hasMasking.set(true);
            effects.add(e);
        });

        for (int slot = 0; slot < input.size(); slot++) {
            ItemStack s = input.getItem(slot);
            if (s.isEmpty() || !isPotionItem(s)) continue;
            PotionContents incoming = s.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
            incoming.getAllEffects().forEach(e -> {
                if (e.getEffect().equals(ModEffects.MASKING)) hasMasking.set(true);
                effects.add(e);
            });
        }

        ItemStack result = food.copyWithCount(1);
        result.set(DataComponents.POTION_CONTENTS, new PotionContents(
                Optional.empty(), Optional.empty(),
                CombinationUtils.combineEffect(effects), Optional.empty()
        ));

        if (hasMasking.get()) {
            TooltipDisplay tdc = result.getOrDefault(DataComponents.TOOLTIP_DISPLAY, TooltipDisplay.DEFAULT);
            var hidden = new ReferenceLinkedOpenHashSet<>(tdc.hiddenComponents());
            hidden.add(DataComponents.POTION_CONTENTS);
            result.set(DataComponents.TOOLTIP_DISPLAY, new TooltipDisplay(false, hidden));
        }

        FoodProperties baseFood = food.get(DataComponents.FOOD);
        if (baseFood != null)
            result.set(DataComponents.FOOD,
                    new FoodProperties(
                            baseFood.nutrition(),
                            baseFood.saturation(),
                            true
                    ));

        Consumable consumable = food.get(DataComponents.CONSUMABLE);
        if (consumable != null) result.set(DataComponents.CONSUMABLE, consumable);

        return result;
    }

    private boolean isFood(ItemStack stack) {
        return stack.getItem().components().has(DataComponents.FOOD);
    }

    @Override
    public @NonNull RecipeSerializer<FoodWithEffectRecipe> getSerializer() {
        return SERIALIZER;
    }
}