package com.matibi.potionsnrituals.effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;

public interface ActiveEffect {

    boolean useOnKeybind(ServerLevel world, Player player, int duration, int amplifier);

    default int[] getCooldowns(int amplifier) {
        return new int[]{0, 0, 0};
    }
}
