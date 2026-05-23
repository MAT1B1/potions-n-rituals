package com.matibi.potionsnrituals.mixin.effect;

import com.matibi.potionsnrituals.config.ModConfig;
import com.matibi.potionsnrituals.effect.ModEffects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class VampirismMixin {

    @Inject(method = "hurtServer", at = @At("TAIL"))
    private void onHurtServer(ServerLevel level, DamageSource source, float damage, CallbackInfoReturnable<Boolean> cir) {
        if (!cir.getReturnValue()) return;

        if (!(source.getEntity() instanceof LivingEntity attacker)) return;

        MobEffectInstance effect = attacker.getEffect(ModEffects.VAMPIRISM);
        if (effect == null) return;

        float healPercent = ModConfig.vampirism_heal + effect.getAmplifier() * ModConfig.vampirism_heal_per_level;
        attacker.heal(damage * healPercent);
    }
}
