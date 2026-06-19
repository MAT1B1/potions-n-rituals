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
public class EmpathyMixin {

    @Inject(method = "hurtServer", at = @At("TAIL"))
    private void onHurtServer(ServerLevel level, DamageSource source, float damage, CallbackInfoReturnable<Boolean> cir) {
        if (!cir.getReturnValue()) return;

        if (!(source.getEntity() instanceof LivingEntity attacker)) return;

        MobEffectInstance effect = attacker.getEffect(ModEffects.EMPATHY);
        if (effect == null) return;

        float dmgPercent = ModConfig.get().empathy_dmg + effect.getAmplifier() * ModConfig.get().empathy_dmg_per_level;
        attacker.hurtServer(level, level.damageSources().magic(), damage * dmgPercent);
    }
}
