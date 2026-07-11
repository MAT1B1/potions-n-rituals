package com.matibi.potionsnrituals.mixin;

import com.matibi.potionsnrituals.world.data.ModAttachments;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.portal.PortalShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(PortalShape.class)
public class PortalShapeMixin {

    @Inject(method = "findEmptyPortalShape", at = @At("HEAD"), cancellable = true)
    private static void onFindEmptyPortalShape(LevelAccessor level, BlockPos pos, Direction.Axis preferredAxis, CallbackInfoReturnable<Optional<PortalShape>> cir) {

        if (level instanceof ServerLevel serverLevel) {
            ServerLevel overworld = serverLevel.getServer().getLevel(Level.OVERWORLD);

            if (overworld != null) {
                Boolean isSealed = overworld.getAttached(ModAttachments.NETHER_SEALED);

                if (isSealed == null || isSealed)
                    cir.setReturnValue(Optional.empty());
            }
        }
    }
}