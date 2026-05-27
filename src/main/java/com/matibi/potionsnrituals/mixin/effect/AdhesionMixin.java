package com.matibi.potionsnrituals.mixin.effect;

import com.matibi.potionsnrituals.config.ModConfig;
import com.matibi.potionsnrituals.effect.ModEffects; // Ton fichier de registry
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class AdhesionMixin {

    @Inject(method = "aiStep", at = @At("HEAD"))
    private void applyAdhesionPhysics(CallbackInfo ci) {
        LivingEntity mob = (LivingEntity) (Object) this;

        if (mob.hasEffect(ModEffects.ADHESION)) {
            MobEffectInstance effect = mob.getEffect(ModEffects.ADHESION);
            int amplifier = effect != null ? effect.getAmplifier() : 0;
            Vec3 vel = mob.getDeltaMovement();

            if (mob.horizontalCollision && !mob.onGround()) {
                if (mob.isShiftKeyDown()) {
                    mob.setDeltaMovement(vel.x * ModConfig.get().horizontal_damping, 0.0D, vel.z * ModConfig.get().horizontal_damping);
                    mob.fallDistance = 0.0F;
                } else {
                    double climbSpeed = ModConfig.get().base_climb + amplifier * ModConfig.get().climb_per_level;
                    mob.setDeltaMovement(
                            vel.x * ModConfig.get().horizontal_damping,
                            climbSpeed * ModConfig.get().horizontal_damping,
                            vel.z * ModConfig.get().horizontal_damping
                    );
                    mob.fallDistance = 0.0F;
                }
            }
        }
    }
}