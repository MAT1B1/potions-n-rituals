package com.matibi.potionsnrituals.mixin.effect;

import com.matibi.potionsnrituals.config.ModConfig;
import com.matibi.potionsnrituals.effect.ModEffects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.PotionItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class HydrophobiaMixin {

    @Inject(method = "completeUsingItem", at = @At("HEAD"))
    private void onCompleteUsingItem(CallbackInfo ci) {
        LivingEntity self = (LivingEntity) (Object) this;
        if (self.level().isClientSide()) return;

        if (!(self.getUseItem().getItem() instanceof PotionItem)) return;

        MobEffectInstance effect = self.getEffect(ModEffects.HYDROPHOBIA);
        if (effect == null) return;

        float damage = ModConfig.get().hydrophobia_potion_damage
                + effect.getAmplifier() * ModConfig.get().hydrophobia_potion_damage_per_level;

        self.hurtServer((ServerLevel) self.level(), self.level().damageSources().magic(), damage);
    }
}
