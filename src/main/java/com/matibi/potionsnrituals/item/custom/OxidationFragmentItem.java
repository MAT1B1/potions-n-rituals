package com.matibi.potionsnrituals.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NonNull;

public class OxidationFragmentItem extends Item {

    public OxidationFragmentItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NonNull InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        ItemStack stack = context.getItemInHand();

        BlockState state = level.getBlockState(pos);
        Block block = state.getBlock();
        if (!(block instanceof WeatheringCopper weathering)) return InteractionResult.PASS;

        var nextState = weathering.getNext(state);
        if (nextState.isEmpty()) return InteractionResult.PASS;

        if (!level.isClientSide()) {
            level.setBlock(pos, nextState.get(), 11);
            level.playSound(null, pos, SoundEvents.COPPER_STEP, SoundSource.BLOCKS, 1.0f, 1.0f);
            if (player == null || !player.isCreative())
                stack.shrink(1);
        }
        return InteractionResult.SUCCESS;
    }
}
