package com.matibi.potionsnrituals.util;

import com.matibi.potionsnrituals.PotionsNRituals;
import net.fabricmc.fabric.api.recipe.v1.ingredient.DefaultCustomIngredients;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.crafting.Ingredient;


public class ModUtils {
    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(PotionsNRituals.MOD_ID, path);
    }

    public static Ingredient potionIngredient(Holder<Potion> potionHolder) {
        DataComponentPatch componentsToMatch = DataComponentPatch.builder()
                .set(DataComponents.POTION_CONTENTS, new PotionContents(potionHolder))
                .build();
        Ingredient basePotion = Ingredient.of(Items.POTION);
        return DefaultCustomIngredients.components(basePotion, componentsToMatch);
    }
}
