package com.matibi.potionsnrituals.mixin.lock;

import com.matibi.potionsnrituals.util.ILockable;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(ExplosionDamageCalculator.class)
public class LockExplosionMixin {
    @Inject(method = "getBlockExplosionResistance", at = @At("HEAD"), cancellable = true)
    public void pnr$protectPadlockedBlocks(Explosion explosion, BlockGetter level, BlockPos pos, BlockState block, FluidState fluid, CallbackInfoReturnable<Optional<Float>> cir) {
        BlockEntity be = level.getBlockEntity(pos);
        ILockable lockable = (ILockable) be;
        if (lockable == null || lockable.isUnlocked()) return;

        cir.setReturnValue(Optional.of(3600000.0F));
    }
}
