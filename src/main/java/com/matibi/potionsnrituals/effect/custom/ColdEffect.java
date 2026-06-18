package com.matibi.potionsnrituals.effect.custom;

import com.matibi.potionsnrituals.config.ModConfig;
import com.matibi.potionsnrituals.effect.ModEffects;
import com.matibi.potionsnrituals.util.ActiveEffectUtils;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class ColdEffect extends MobEffect {

    public ColdEffect() {
        super(MobEffectCategory.HARMFUL, 0xAADDFF);
    }

    public static void doCough(LivingEntity entity) {
        if (!(entity.level() instanceof ServerLevel level)) return;

        level.playSound(
                null,
                entity.getX(), entity.getY(), entity.getZ(),
                SoundEvents.PLAYER_HURT,
                SoundSource.PLAYERS,
                0.6f, 1.9f
        );

        level.sendParticles(
                ParticleTypes.POOF,
                entity.getX(),
                entity.getY() + entity.getEyeHeight() - 0.2,
                entity.getZ(),
                10, 0.25, 0.15, 0.25, 0.04
        );

        Vec3 look = entity.getLookAngle();
        entity.addDeltaMovement(new Vec3(
                -look.x * 0.45,
                0.28,
                -look.z * 0.45
        ));
        entity.hurtMarked = true;

        LivingEntity target = ActiveEffectUtils.getLookedAtEntity(entity, 2.5);
        if (target != null)
            target.addEffect(new MobEffectInstance(ModEffects.COLD, ModConfig.get().dur_basic, 0));
    }
}