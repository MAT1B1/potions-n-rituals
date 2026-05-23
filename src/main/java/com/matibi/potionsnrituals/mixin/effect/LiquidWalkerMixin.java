package com.matibi.potionsnrituals.mixin.effect;

import com.matibi.potionsnrituals.effect.ModEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LiquidWalkerMixin {

    @Inject(method = "canStandOnFluid", at = @At("HEAD"), cancellable = true)
    private void onCanStandOnFluid(FluidState fluid, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity self = (LivingEntity) (Object) this;
        MobEffectInstance effect = self.getEffect(ModEffects.LIQUID_WALKER);
        if (effect == null) return;
        if (self instanceof Player && self.isCrouching()) return;
        if (self.isUnderWater() || self.getFluidHeight(FluidTags.WATER) > 0.4) return;

        int amplifier = effect.getAmplifier();
        boolean water = fluid.is(FluidTags.WATER);
        boolean lava = fluid.is(FluidTags.LAVA);

        if (water || (lava && amplifier >= 1))
            cir.setReturnValue(true);

    }

    @Inject(method = "travel", at = @At("TAIL"))
    private void modifyLiquidSpeed(Vec3 input, CallbackInfo ci) {
        LivingEntity self = (LivingEntity)(Object)this;
        if (!self.hasEffect(ModEffects.LIQUID_WALKER)) return;
        if (self instanceof Player && self.isCrouching()) return;

        BlockPos below = self.blockPosition().below();
        FluidState fluid = self.level().getBlockState(below).getFluidState();

        boolean onLiquid = (fluid.is(FluidTags.WATER) || fluid.is(FluidTags.LAVA))
                && !self.isUnderWater();

        if (onLiquid) {
            Vec3 velocity = self.getDeltaMovement();

            BlockPos atFeet = self.blockPosition();
            FluidState fluidAtFeet = self.level().getBlockState(atFeet).getFluidState();
            boolean touchingFluid = fluidAtFeet.is(FluidTags.WATER) || fluidAtFeet.is(FluidTags.LAVA);

            double newY = (touchingFluid && velocity.y < 0) ? 0.0 : velocity.y;
            self.setDeltaMovement(velocity.x, newY, velocity.z);
            self.resetFallDistance();
        }
    }
}