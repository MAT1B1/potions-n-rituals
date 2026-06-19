package com.matibi.potionsnrituals.recipe;

import com.matibi.potionsnrituals.util.ModUtils;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class ModRecipeSerializer {

    public static final RecipeSerializer<AlchemicalStoneRecipe> RUNE_FROM_POTION =
            AlchemicalStoneRecipe.SERIALIZER;

    public static final RecipeSerializer<CombinationRecipe> COMBINATION =
            CombinationRecipe.SERIALIZER;

    public static final RecipeSerializer<FoodWithEffectRecipe> FOOD_WITH_EFFECT =
            FoodWithEffectRecipe.SERIALIZER;

    public static final RecipeSerializer<ImbuedEffectRecipe> IMBUED_EFFECT =
            ImbuedEffectRecipe.SERIALIZER;

    private static void reg(String id, RecipeSerializer<?> serializer) {
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER,
                ModUtils.id(id),
                serializer);
    }

    public static void register() {
        reg("rune_from_potion",         RUNE_FROM_POTION);
        reg("combination",              COMBINATION);
        reg("food_with_effect_recipe",  FOOD_WITH_EFFECT);
        reg("imbued_effect",            IMBUED_EFFECT);
    }
}