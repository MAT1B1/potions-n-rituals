package com.matibi.potionsnrituals.datagen;

import com.matibi.potionsnrituals.PotionsNRituals;
import com.matibi.potionsnrituals.item.ModItems;
import com.matibi.potionsnrituals.recipe.AlchemicalStoneRecipe;
import com.matibi.potionsnrituals.recipe.CombinationRecipe;
import com.matibi.potionsnrituals.recipe.FoodWithEffectRecipe;
import com.matibi.potionsnrituals.recipe.ImbuedEffectRecipe;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SpecialRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {

    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected @NonNull RecipeProvider createRecipeProvider(HolderLookup.@NonNull Provider registries, @NonNull RecipeOutput output) {
        return new RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {
                shapeless(RecipeCategory.BREWING, ModItems.ALCHEMIST_CORE, 8)
                        .requires(Items.POISONOUS_POTATO)
                        .requires(ModItems.POISONOUS_CARROT)
                        .requires(ModItems.POISONOUS_BEETROOT)
                        .requires(Items.BROWN_MUSHROOM)
                        .requires(Items.RED_MUSHROOM)
                        .requires(Items.FERMENTED_SPIDER_EYE)
                        .requires(Items.BONE_MEAL)
                        .requires(Items.ROTTEN_FLESH)
                        .requires(ModItems.WITCH_S_FINGER)
                        .unlockedBy(getHasName(ModItems.WITCH_S_FINGER), has(ModItems.WITCH_S_FINGER))
                        .save(output);

                shaped(RecipeCategory.BREWING, ModItems.ALCHEMICAL_STONE, 8)
                        .pattern("SSS").pattern("SAS").pattern("SSS")
                        .define('A', ModItems.ALCHEMIST_CORE)
                        .define('S', Items.COBBLESTONE)
                        .unlockedBy(getHasName(ModItems.ALCHEMIST_CORE), has(ModItems.ALCHEMIST_CORE))
                        .save(output);

                shaped(RecipeCategory.BREWING, ModItems.ALCHEMICAL_STONE, 8)
                        .pattern("SSS").pattern("SAS").pattern("SSS")
                        .define('A', Items.NETHER_WART)
                        .define('S', Items.COBBLESTONE)
                        .unlockedBy(getHasName(ModItems.ALCHEMIST_CORE), has(ModItems.ALCHEMIST_CORE))
                        .save(output, "alchemical_stone_from_nether_wart");

                shapeless(RecipeCategory.BREWING, ModItems.SYRINGE)
                        .requires(Items.GLASS_BOTTLE)
                        .requires(Items.IRON_NUGGET)
                        .unlockedBy(getHasName(Items.GLASS_BOTTLE), has(Items.GLASS_BOTTLE))
                        .save(output);

                shapeless(RecipeCategory.MISC, ModItems.LEAF, 9)
                        .requires(ItemTags.LEAVES)
                        .unlockedBy("has_leaves", has(ItemTags.LEAVES))
                        .save(output);

                shapeless(RecipeCategory.MISC, Items.LEAF_LITTER)
                        .requires(ModItems.LEAF, 9)
                        .unlockedBy(getHasName(ModItems.LEAF), has(ModItems.LEAF))
                        .save(output);


                SpecialRecipeBuilder.special(FoodWithEffectRecipe::new)
                        .save(output, "potions-n-rituals:food_with_effect_recipe");
                SpecialRecipeBuilder.special(AlchemicalStoneRecipe::new)
                        .save(output, "potions-n-rituals:rune_recipe");
                SpecialRecipeBuilder.special(CombinationRecipe::new)
                        .save(output, "potions-n-rituals:combination_recipe");
                SpecialRecipeBuilder.special(ImbuedEffectRecipe::new)
                        .save(output, "potions-n-rituals:imbued_effect_recipe");
            }
        };
    }

    @Override
    public @NonNull String getName() {
        return PotionsNRituals.MOD_ID + " recipe";
    }
}