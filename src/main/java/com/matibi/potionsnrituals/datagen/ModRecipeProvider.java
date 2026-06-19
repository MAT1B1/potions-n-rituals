package com.matibi.potionsnrituals.datagen;

import com.matibi.potionsnrituals.PotionsNRituals;
import com.matibi.potionsnrituals.item.ModItems;
import com.matibi.potionsnrituals.recipe.AlchemicalStoneRecipe;
import com.matibi.potionsnrituals.recipe.CombinationRecipe;
import com.matibi.potionsnrituals.recipe.FoodWithEffectRecipe;
import com.matibi.potionsnrituals.recipe.ImbuedEffectRecipe;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancements.predicates.FluidPredicate;
import net.minecraft.advancements.predicates.LocationPredicate;
import net.minecraft.advancements.triggers.PlayerTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.material.Fluids;
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
                SimpleCookingRecipeBuilder.smelting(
                        Ingredient.of(Items.CINNABAR),
                        RecipeCategory.MISC,
                        CookingBookCategory.MISC,
                        ModItems.MERCURY_BALL,
                        0.7F,
                        200)
                    .unlockedBy("has_cinnabar", has(Items.CINNABAR))
                    .save(output);
                SimpleCookingRecipeBuilder.smelting(
                        Ingredient.of(Items.SULFUR),
                        RecipeCategory.MISC,
                        CookingBookCategory.MISC,
                        ModItems.SULFUR_BALL,
                        0.7F,
                        200)
                    .unlockedBy("has_sulfur", has(Items.SULFUR))
                    .save(output);
                SimpleCookingRecipeBuilder.smelting(
                        Ingredient.of(Items.WATER_BUCKET),
                        RecipeCategory.MISC,
                        CookingBookCategory.MISC,
                        ModItems.SALT,
                        0.35F,
                        200)
                    .unlockedBy("in_water", PlayerTrigger.TriggerInstance.located(
                        LocationPredicate.Builder.location()
                            .setFluid(FluidPredicate.Builder.fluid()
                                .of(Fluids.WATER))
                    ))
                    .save(output);

                shapeless(RecipeCategory.MISC, ModItems.MATERIA_PRIMA, 8)
                        .requires(ModItems.SALT)
                        .requires(ModItems.SULFUR_BALL)
                        .requires(ModItems.MERCURY_BALL)
                        .requires(Items.WATER_BUCKET)
                        .requires(Items.LAVA_BUCKET)
                        .requires(Items.DIRT)
                        .requires(Items.GLASS_BOTTLE)
                        .requires(Items.GOLDEN_DANDELION)
                        .requires(Items.ROTTEN_FLESH)
                        .unlockedBy(getHasName(ModItems.SULFUR_BALL), has(ModItems.SULFUR_BALL))
                        .save(output);

                shaped(RecipeCategory.BREWING, ModItems.ALCHEMICAL_STONE, 8)
                        .pattern("SSS").pattern("SAS").pattern("SSS")
                        .define('A', ModItems.MATERIA_PRIMA)
                        .define('S', Items.COBBLESTONE)
                        .unlockedBy(getHasName(ModItems.MATERIA_PRIMA), has(ModItems.MATERIA_PRIMA))
                        .save(output);

                shaped(RecipeCategory.BREWING, ModItems.ALCHEMICAL_STONE, 8)
                        .pattern("SSS").pattern("SAS").pattern("SSS")
                        .define('A', Items.NETHER_WART)
                        .define('S', Items.COBBLESTONE)
                        .unlockedBy(getHasName(ModItems.MATERIA_PRIMA), has(ModItems.MATERIA_PRIMA))
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