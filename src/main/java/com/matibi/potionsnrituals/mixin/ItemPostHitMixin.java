package com.matibi.potionsnrituals.mixin;

import com.matibi.potionsnrituals.datacomponent.ImbuedEffect;
import com.matibi.potionsnrituals.datacomponent.ModDataComponents;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Item.class)
public abstract class ItemPostHitMixin {

    @Inject(method = "hurtEnemy", at = @At("TAIL"))
    private void applyImbueOnHit(ItemStack itemStack, LivingEntity mob,
                                 LivingEntity attacker, CallbackInfo ci) {
        if (attacker.level().isClientSide()) return;

        ImbuedEffect imbued = itemStack.get(ModDataComponents.IMBUED_EFFECT);
        if (imbued == null) return;

        int duration = imbued.effect().value().isInstantaneous() ? 1 : 20 * 10;

        mob.addEffect(new MobEffectInstance(
                imbued.effect(), duration, imbued.amplifier()));

        int remaining = imbued.hitsRemaining() - 1;
        if (remaining > 0)
            itemStack.set(ModDataComponents.IMBUED_EFFECT,
                    new ImbuedEffect(imbued.effect(), remaining, imbued.amplifier()));
        else {
            itemStack.remove(ModDataComponents.IMBUED_EFFECT);
            itemStack.remove(DataComponents.ENCHANTMENT_GLINT_OVERRIDE);
        }
    }
}