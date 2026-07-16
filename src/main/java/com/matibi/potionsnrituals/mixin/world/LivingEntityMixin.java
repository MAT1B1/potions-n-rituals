package com.matibi.potionsnrituals.mixin.world;

import com.matibi.potionsnrituals.world.dimension.ModDimensions;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class LivingEntityMixin {

    @Shadow public abstract boolean isInWater();

    @Inject(method = "tick", at = @At("HEAD"))
    private void pnr$makeWaterDangerous(CallbackInfo ci) {
        if (!((Entity) (Object)this instanceof LivingEntity entity)) return;
        Level level = entity.level();

        if (!level.isClientSide() && this.isInWater() && level.dimension() == ModDimensions.SPIRIT_DIMENSION)
            entity.addEffect(new MobEffectInstance(MobEffects.WITHER, 40, 1, true, true));
    }
}