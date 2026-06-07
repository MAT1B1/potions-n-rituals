package com.matibi.potionsnrituals.effect.custom.active;

import com.matibi.potionsnrituals.config.ModConfig;
import com.matibi.potionsnrituals.effect.ActiveEffect;
import com.matibi.potionsnrituals.util.ActiveEffectUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.Set;

public class ActiveTeleportationEffect extends MobEffect implements ActiveEffect {
    public ActiveTeleportationEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x8833cc);
    }

    @Override
    public void useOnKeybind(ServerLevel world, Player player, int duration, int amplifier) {
        if (!(player instanceof ServerPlayer serverPlayer)) return;

        double range = ModConfig.get().active_teleport_range + ModConfig.get().active_teleport_range_per_level * amplifier;
        BlockHitResult hit = ActiveEffectUtils.getLookedAtBlock(serverPlayer, range);
        if (hit.getType() == HitResult.Type.MISS) return;

        BlockPos pos = hit.getBlockPos().relative(hit.getDirection());

        serverPlayer.teleportTo(
                world,
                pos.getX() + 0.5,
                pos.getY(),
                pos.getZ() + 0.5,
                Set.of(),
                serverPlayer.getYRot(),
                serverPlayer.getXRot(),
                false
        );
        world.broadcastEntityEvent(serverPlayer, (byte) 46);
    }

    @Override
    public int[] getCooldowns(int amplifier) {
        return new int[]{
                ModConfig.get().active_teleport_short_cooldown,
                ModConfig.get().active_teleport_cooldown,
                ModConfig.get().active_teleport_long_cooldown
        };
    }
}
