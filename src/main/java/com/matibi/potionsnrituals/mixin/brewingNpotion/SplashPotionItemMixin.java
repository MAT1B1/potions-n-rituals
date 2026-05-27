package com.matibi.potionsnrituals.mixin.brewingNpotion;

import com.matibi.potionsnrituals.config.ModConfig;
import com.matibi.potionsnrituals.effect.ModEffects;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SplashPotionItem;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SplashPotionItem.class)
public abstract class SplashPotionItemMixin {
    @Inject(method = "use", at = @At("HEAD"))
    private void applyCooldownOnUse(Level level, Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        int time = ModConfig.get().splash_potion_cooldown;
        if (player.hasEffect(ModEffects.LONG_COOLDOWN)) time = ModConfig.get().splash_potion_long_cooldown;
        if (player.hasEffect(ModEffects.SHORT_COOLDOWN)) time = ModConfig.get().splash_potion_short_cooldown;
        ItemStack stack = player.getItemInHand(hand);
        player.getCooldowns().addCooldown(stack, time);
    }
}
