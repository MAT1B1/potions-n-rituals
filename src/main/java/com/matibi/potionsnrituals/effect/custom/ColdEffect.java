package com.matibi.potionsnrituals.effect.custom;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class ColdEffect extends MobEffect {

    public ColdEffect() {
        super(MobEffectCategory.HARMFUL, 0xAADDFF);
    }

    public static void doCough(Player player) {
        if (!(player.level() instanceof ServerLevel level)) return;

        level.playSound(
                null,
                player.getX(), player.getY(), player.getZ(),
                SoundEvents.PLAYER_HURT,
                SoundSource.PLAYERS,
                0.6f, 1.9f
        );

        level.sendParticles(
                ParticleTypes.POOF,
                player.getX(),
                player.getY() + player.getEyeHeight() - 0.2,
                player.getZ(),
                10, 0.25, 0.15, 0.25, 0.04
        );

        Vec3 look = player.getLookAngle();
        player.addDeltaMovement(new Vec3(
                -look.x * 0.45,
                0.28,
                -look.z * 0.45
        ));
        player.hurtMarked = true;
    }
}