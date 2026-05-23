package com.matibi.potionsnrituals.recipe;

import com.matibi.potionsnrituals.config.ModConfig;
import com.matibi.potionsnrituals.datacomponent.ImbuedEffect;
import com.matibi.potionsnrituals.datacomponent.ModDataComponents;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ImbuedEffectRecipe extends CustomRecipe {

    public static final MapCodec<ImbuedEffectRecipe> MAP_CODEC = MapCodec.unit(new ImbuedEffectRecipe());
    public static final StreamCodec<RegistryFriendlyByteBuf, ImbuedEffectRecipe> STREAM_CODEC = StreamCodec.unit(new ImbuedEffectRecipe());
    public static final RecipeSerializer<ImbuedEffectRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);

    @Override
    public boolean matches(CraftingInput input, @NonNull Level level) {
        ItemStack weapon = ItemStack.EMPTY;
        ItemStack potion = ItemStack.EMPTY;

        for (int slot = 0; slot < input.size(); slot++) {
            ItemStack stack = input.getItem(slot);
            if (stack.isEmpty()) continue;

            if (isPotionItem(stack)) {
                if (!potion.isEmpty()) return false;
                potion = stack;
            } else if (isWeapon(stack)) {
                if (!weapon.isEmpty()) return false;
                weapon = stack;
            } else return false;
        }

        if (weapon.isEmpty() || potion.isEmpty()) return false;
        if (weapon.has(ModDataComponents.IMBUED_EFFECT) ||
                !Objects.requireNonNull(weapon.get(DataComponents.ENCHANTMENTS)).isEmpty())
            return false;

        return !getEffectList(potion).isEmpty();
    }

    private boolean isPotionItem(ItemStack stack) {
        return stack.is(Items.POTION)
                || stack.is(Items.SPLASH_POTION)
                || stack.is(Items.LINGERING_POTION);
    }

    private boolean isWeapon(ItemStack stack) {
        return stack.is(ItemTags.MELEE_WEAPON_ENCHANTABLE)
                || stack.is(ItemTags.TRIDENT_ENCHANTABLE);
    }

    @Override
    public @NonNull ItemStack assemble(CraftingInput input) {
        ItemStack weapon = ItemStack.EMPTY;
        ItemStack potion = ItemStack.EMPTY;

        for (int slot = 0; slot < input.size(); slot++) {
            ItemStack stack = input.getItem(slot);
            if (stack.isEmpty()) continue;
            if (isPotionItem(stack)) potion = stack;
            else if (isWeapon(stack)) weapon = stack;
        }

        List<MobEffectInstance> effects = getEffectList(potion);
        if (effects.isEmpty()) return ItemStack.EMPTY;

        MobEffectInstance chosen = effects.getFirst();
        ItemStack result = weapon.copyWithCount(1);

        int hits = ModConfig.default_hit + chosen.getDuration() / (20 * 60) * 2;

        result.set(ModDataComponents.IMBUED_EFFECT,
                new ImbuedEffect(chosen.getEffect(), hits, chosen.getAmplifier()));
        result.set(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true);

        return result;
    }

    @Override
    public @NonNull RecipeSerializer<ImbuedEffectRecipe> getSerializer() {
        return SERIALIZER;
    }

    public static List<MobEffectInstance> getEffectList(ItemStack potion) {
        List<MobEffectInstance> effects = new ArrayList<>();
        PotionContents contents = potion.get(DataComponents.POTION_CONTENTS);
        if (contents != null)
            contents.getAllEffects().forEach(effects::add);
        return effects;
    }
}