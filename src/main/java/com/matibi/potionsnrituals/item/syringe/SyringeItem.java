package com.matibi.potionsnrituals.item.syringe;

import com.matibi.potionsnrituals.config.ModConfig;
import com.matibi.potionsnrituals.item.ModItems;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SyringeItem extends Item {

    public SyringeItem(Properties properties) {
        super(properties.durability(ModConfig.get().syringe_durability));
    }

    @Override
    public @NonNull InteractionResult use(@NonNull Level level, @NonNull Player player, @NonNull InteractionHand hand) {
        if (!(level instanceof ServerLevel sw)) return InteractionResult.FAIL;
        ItemStack stack = player.getItemInHand(hand);
        PotionContents contents = stack.get(DataComponents.POTION_CONTENTS);

        if (contents == null) return InteractionResult.FAIL;

        player.hurtServer(sw, sw.damageSources().mobAttack(player), ModConfig.get().syringe_damage);
        stack.hurtAndBreak(ModConfig.get().syringe_durability_loss, player, EquipmentSlot.MAINHAND);

        if (contents.hasEffects()) {
            contents.getAllEffects().forEach(instance -> {
                MobEffectInstance existing = player.getEffect(instance.getEffect());
                int dur = existing == null ? 0 : existing.getDuration();
                player.addEffect(new MobEffectInstance(
                        instance.getEffect(),
                        instance.getDuration() + dur,
                        instance.getAmplifier(),
                        instance.isAmbient(),
                        instance.isVisible(),
                        instance.showIcon()
                ));
            });
            resetSyringe(stack);
        } else if (!player.getActiveEffects().isEmpty()) {
            fillSyringe(stack, player);
        } else {
            player.getInventory().add(new ItemStack(ModItems.BLOOD_BAG));
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void hurtEnemy(ItemStack stack, @NonNull LivingEntity target, @NonNull LivingEntity attacker) {
        PotionContents contents = stack.get(DataComponents.POTION_CONTENTS);
        if (contents == null) return;

        stack.hurtAndBreak(ModConfig.get().syringe_durability_loss, attacker, EquipmentSlot.MAINHAND);

        if (contents.hasEffects()) {
            contents.getAllEffects().forEach(target::addEffect);
            resetSyringe(stack);
        } else if (!target.getActiveEffects().isEmpty())
            fillSyringe(stack, target);
        else if (attacker instanceof Player player)
            player.getInventory().add(new ItemStack(ModItems.BLOOD_BAG));

    }

    private void resetSyringe(ItemStack stack) {
        stack.set(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
    }

    private void fillSyringe(ItemStack stack, LivingEntity entity) {
        List<MobEffectInstance> effects = new ArrayList<>();

        new ArrayList<>(entity.getActiveEffects()).forEach(instance -> {
            int oldDur = instance.getDuration();
            int transfer = Math.min(oldDur, ModConfig.get().syringe_max_transfer_duration_ticks);
            int remaining = Math.max(0, oldDur - transfer);

            entity.removeEffect(instance.getEffect());

            if (remaining > 0) {
                entity.addEffect(new MobEffectInstance(
                        instance.getEffect(), remaining,
                        instance.getAmplifier(), instance.isAmbient(),
                        instance.isVisible(), instance.showIcon()
                ));
            }
            if (transfer > 0) {
                effects.add(new MobEffectInstance(
                        instance.getEffect(), transfer,
                        instance.getAmplifier(), instance.isAmbient(),
                        instance.isVisible(), instance.showIcon()
                ));
            }
        });

        stack.set(DataComponents.POTION_CONTENTS,
                new PotionContents(Optional.empty(), Optional.empty(), effects, Optional.empty()));

        if (!effects.isEmpty()) {
            String key = "item.potions-n-rituals.syringe." +
                    effects.getFirst().getEffect().value().getDescriptionId();
            stack.set(DataComponents.CUSTOM_NAME,
                    Component.translatable(key).withStyle(s -> s.withItalic(false)));
        }
    }
}