package com.matibi.potionsnrituals.mixin.lock;

import com.matibi.potionsnrituals.util.ILockable;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockBehaviour.BlockStateBase.class)
public class LockMiningMixin {
    @Inject(method = "getDestroySpeed", at = @At("RETURN"), cancellable = true)
    public void pnr$slowDownPadlockedBlocks(BlockGetter level, BlockPos pos, CallbackInfoReturnable<Float> cir) {
        BlockEntity be = level.getBlockEntity(pos);
        ILockable lockable = (ILockable) be;
        if (lockable == null || lockable.isUnlocked()) return;

        float originalProgress = cir.getReturnValue();
        cir.setReturnValue(originalProgress * 10.0F);
    }
}
