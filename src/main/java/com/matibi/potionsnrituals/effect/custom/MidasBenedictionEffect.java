package com.matibi.potionsnrituals.effect.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jspecify.annotations.NonNull;

public class MidasBenedictionEffect extends MobEffect {

    public MidasBenedictionEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xFFD700);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration % 10 == 0;
    }

    @Override
    public boolean applyEffectTick(@NonNull ServerLevel world, @NonNull LivingEntity entity, int amplifier) {
        if (!(entity instanceof ServerPlayer player) || player.isCreative())
            return true;

        for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
            ItemStack stack = player.getInventory().getItem(i);
            if (stack.isEmpty()) continue;

            Item replacement = getGoldVariant(stack);
            if (replacement == null || replacement == stack.getItem()) continue;

            ItemStack newStack = stack.transmuteCopy(replacement, stack.getCount());

            if (stack.isDamageableItem()) {
                float ratio = (float)(stack.getMaxDamage() - stack.getDamageValue()) / stack.getMaxDamage();
                newStack.setDamageValue((int)(newStack.getMaxDamage() * (1f - ratio)));
            }

            player.getInventory().setItem(i, newStack);
        }

        return true;
    }

    private static Item getGoldVariant(ItemStack stack) {
        Item item = stack.getItem();

        // Sécurité : Si l'item est déjà en or, on ne fait rien
        if (isAlreadyGold(item)) return null;

        if (item == Items.APPLE) return Items.GOLDEN_APPLE;
        if (item == Items.CARROT) return Items.GOLDEN_CARROT;

        if (stack.is(ItemTags.SWORDS)) return Items.GOLDEN_SWORD;
        if (stack.is(ItemTags.AXES)) return Items.GOLDEN_AXE;
        if (stack.is(ItemTags.PICKAXES)) return Items.GOLDEN_PICKAXE;
        if (stack.is(ItemTags.SHOVELS)) return Items.GOLDEN_SHOVEL;
        if (stack.is(ItemTags.HOES)) return Items.GOLDEN_HOE;
        if (stack.is(ItemTags.HEAD_ARMOR)) return Items.GOLDEN_HELMET;
        if (stack.is(ItemTags.CHEST_ARMOR)) return Items.GOLDEN_CHESTPLATE;
        if (stack.is(ItemTags.LEG_ARMOR)) return Items.GOLDEN_LEGGINGS;
        if (stack.is(ItemTags.FOOT_ARMOR)) return Items.GOLDEN_BOOTS;

        return null;
    }

    private static boolean isAlreadyGold(Item item) {
        return item == Items.GOLDEN_APPLE || item == Items.GOLDEN_CARROT ||
                item == Items.GOLDEN_SWORD || item == Items.GOLDEN_AXE ||
                item == Items.GOLDEN_PICKAXE || item == Items.GOLDEN_SHOVEL ||
                item == Items.GOLDEN_HOE || item == Items.GOLDEN_HELMET ||
                item == Items.GOLDEN_CHESTPLATE || item == Items.GOLDEN_LEGGINGS ||
                item == Items.GOLDEN_BOOTS;
    }
}