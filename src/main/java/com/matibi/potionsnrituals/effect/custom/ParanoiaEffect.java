package com.matibi.potionsnrituals.effect.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class ParanoiaEffect extends MobEffect {
    public ParanoiaEffect() {
        super(MobEffectCategory.HARMFUL, 0x2D0A4E);
    }

    private static final List<SoundEvent> PARANOIA_SOUNDS = List.of(
            SoundEvents.ZOMBIE_AMBIENT,
            SoundEvents.CREEPER_PRIMED,
            SoundEvents.SKELETON_AMBIENT,
            SoundEvents.SPIDER_AMBIENT
    );

    @Override
    public boolean applyEffectTick(@NonNull ServerLevel serverLevel, @NonNull LivingEntity mob, int amplification) {
        if (!(mob instanceof Player player)) return super.applyEffectTick(serverLevel, mob, amplification);

        RandomSource random = player.getRandom();
        if (random.nextInt(100) < 20) {
            SoundEvent sound = PARANOIA_SOUNDS.get(
                    random.nextInt(PARANOIA_SOUNDS.size())
            );

            double offsetX = (random.nextDouble() - 0.5) * 10;
            double offsetZ = (random.nextDouble() - 0.5) * 10;

            serverLevel.playSound(
                    null,
                    player.getX() + offsetX, player.getY(), player.getZ() + offsetZ,
                    sound,
                    SoundSource.HOSTILE,
                    1.0f,
                    1.0f
            );
        }
        return super.applyEffectTick(serverLevel, mob, amplification);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration % 20 == 0;
    }
}