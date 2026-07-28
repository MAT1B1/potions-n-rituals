package com.matibi.potionsnrituals.mixin;

import com.matibi.potionsnrituals.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(AxeItem.class)
public class OxydationMixin {

    @Inject(method = "evaluateNewBlockState", at = @At("RETURN"))
    private void onOxidationScrape(Level level, BlockPos pos, Player player, BlockState oldState,
                                    CallbackInfoReturnable<Optional<BlockState>> cir) {
        if (level.isClientSide()) return;
        if (player == null) return;

        Optional<BlockState> result = cir.getReturnValue();
        if (result.isEmpty()) return;

        Optional<BlockState> previous = WeatheringCopper.getPrevious(oldState);
        if (previous.isPresent() && previous.get().equals(result.get()))
            Block.popResource(level, pos, new ItemStack(ModItems.OXYDATION));
    }
}
