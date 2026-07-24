package com.matibi.potionsnrituals.item.custom.talisman;

import com.matibi.potionsnrituals.config.ModConfig;
import com.matibi.potionsnrituals.util.ModUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class InvisibilityCloakItem extends Item {

    public InvisibilityCloakItem(Properties properties) {
        super(properties.durability(ModConfig.get().dur_long)
                .rarity(Rarity.RARE)
                .equippable(EquipmentSlot.CHEST)
                .attributes(createAttributes()));
    }

    private static ItemAttributeModifiers createAttributes() {
        return ItemAttributeModifiers.builder()
                .add(
                        Attributes.ARMOR,
                        new AttributeModifier(
                                ModUtils.id("invisibility_cloak_armor"),
                                2.0,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.CHEST
                )
                .build();
    }

    @Override
    public void inventoryTick(@NonNull ItemStack itemStack, ServerLevel level, @NonNull Entity owner, @Nullable EquipmentSlot slot) {

        if (!level.isClientSide() && owner instanceof Player player) {

            if (slot == EquipmentSlot.CHEST) {
                player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 2, 0, true, false, true));
                itemStack.hurtAndBreak(1, player, EquipmentSlot.CHEST);
            }
        }

        super.inventoryTick(itemStack, level, owner, slot);
    }

    @Override
    public boolean isFoil(@NonNull ItemStack stack) {
        return true;
    }
}