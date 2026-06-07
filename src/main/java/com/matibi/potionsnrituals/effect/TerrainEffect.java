package com.matibi.potionsnrituals.effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;

public interface TerrainEffect {

    default void useOnBlock(ServerLevel world, BlockPos block, int duration, int amplifier) {}

    default void useOnBlock(ServerLevel world, Player player, BlockPos block, int duration, int amplifier) {
        useOnBlock(world, block, duration, amplifier);
    }

    default boolean isBlockNonApplicable(ServerLevel world, BlockPos block) {
        return false;
    }
}
