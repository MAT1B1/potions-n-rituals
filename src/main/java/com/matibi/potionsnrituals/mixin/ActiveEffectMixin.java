package com.matibi.potionsnrituals.mixin;

import com.matibi.potionsnrituals.effect.ActiveEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class ActiveEffectMixin {
    @Inject(method = "addEffect(Lnet/minecraft/world/effect/MobEffectInstance;)Z", at = @At("HEAD"), cancellable = true)
    private void preventMultipleActiveEffects(MobEffectInstance instance, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity self = (LivingEntity) (Object) this;

        if (!(instance.getEffect().value() instanceof ActiveEffect)) return;

        for (MobEffectInstance existing : self.getActiveEffects()) {
            if (existing.getEffect().value() instanceof ActiveEffect
                    && existing.getEffect().value().getClass() != instance.getEffect().value().getClass()) {
                cir.setReturnValue(false);
                return;
            }
        }
    }
}
