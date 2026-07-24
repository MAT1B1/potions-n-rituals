package com.matibi.potionsnrituals.item.custom.talisman;

import com.matibi.potionsnrituals.config.ModConfig;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class InvisibilityRingItem extends Item {

    public InvisibilityRingItem(Properties properties) {
        super(properties.durability(ModConfig.get().dur_basic).rarity(Rarity.RARE));
    }

    @Override
    public void inventoryTick(@NonNull ItemStack itemStack, ServerLevel level, @NonNull Entity owner, @Nullable EquipmentSlot slot) {

        if (!level.isClientSide() && owner instanceof Player player) {

            boolean inMainHand = player.getMainHandItem() == itemStack;
            boolean inOffHand = player.getOffhandItem() == itemStack;

            if (inMainHand || inOffHand) {
                player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 2, 0, true, false, true));
                itemStack.hurtAndBreak(1, player, player.getEquipmentSlotForItem(itemStack));
            }
        }

        super.inventoryTick(itemStack, level, owner, slot);
    }

    @Override
    public boolean isFoil(@NonNull ItemStack stack) {
        return true;
    }
}