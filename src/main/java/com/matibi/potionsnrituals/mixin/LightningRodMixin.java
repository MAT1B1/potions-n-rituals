package com.matibi.potionsnrituals.mixin;

import com.matibi.potionsnrituals.item.ModItems;
import com.matibi.potionsnrituals.util.TickManager;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LightningRodBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LightningBolt.class)
public abstract class LightningRodMixin {

    @Shadow
    private BlockPos getStrikePosition() { return null; }

    @Shadow
    private int life;

    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        LightningBolt lightning = (LightningBolt) (Object) this;
        Level level = lightning.level();

        if (!level.isClientSide() && this.life == 2) {
            BlockPos pos = getStrikePosition();
            if (pos != null && level.getBlockState(pos).getBlock() instanceof LightningRodBlock) {
                ItemEntity itemEntity = new ItemEntity(level,
                        pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5,
                        new ItemStack(ModItems.CHARGED_COPPER)
                );
                itemEntity.setInvulnerable(true);
                level.addFreshEntity(itemEntity);
                TickManager.runLater(20, _ -> itemEntity.setInvulnerable(false));
            }
        }
    }
}