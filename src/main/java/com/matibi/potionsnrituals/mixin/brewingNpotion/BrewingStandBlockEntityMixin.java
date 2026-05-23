package com.matibi.potionsnrituals.mixin.brewingNpotion;

import com.matibi.potionsnrituals.config.ModConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BrewingStandBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BrewingStandBlockEntity.class)
public abstract class BrewingStandBlockEntityMixin {

    @Inject(method = "serverTick", at = @At("HEAD"))
    private static void injectCustomFuel(Level level, BlockPos pos, BlockState selfState, BrewingStandBlockEntity entity, CallbackInfo ci) {
        BrewingStandAccessor accessor = (BrewingStandAccessor) entity;
        ItemStack fuelStack = accessor.getInventory().get(4);

        if (accessor.getFuel() < 20 && fuelStack.is(Items.LAVA_BUCKET)) {
            accessor.setFuel(Math.min(accessor.getFuel() + ModConfig.blaze_replacer_fuel, 20));
            accessor.getInventory().set(4, new ItemStack(Items.BUCKET));
            level.sendBlockUpdated(pos, selfState, selfState, 3);
        }
    }
}
