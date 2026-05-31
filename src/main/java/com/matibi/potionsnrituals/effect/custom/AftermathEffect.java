package com.matibi.potionsnrituals.effect.custom;

import com.matibi.potionsnrituals.config.ModConfig;
import com.matibi.potionsnrituals.effect.ModEffects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.jspecify.annotations.NonNull;

import java.util.Objects;

public class AftermathEffect extends MobEffect {
    public AftermathEffect() {
        super(MobEffectCategory.NEUTRAL, 0x4A1A1A);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyEffectTick(@NonNull ServerLevel world, LivingEntity entity, int amplifier) {
        float half = entity.getMaxHealth() * ModConfig.get().health_required_after_aftermath;

        if (entity.getHealth() > half)
            entity.removeEffect(ModEffects.AFTERMATH);

        return super.applyEffectTick(world, entity, amplifier);
    }

    @Override
    public void onEffectRemoved(@NonNull MobEffectInstance effectInstance, @NonNull LivingEntity entity) {
        super.onEffectRemoved(effectInstance, entity);
        ServerLevel world = Objects.requireNonNull(entity.level().getServer())
                .getLevel(ServerLevel.OVERWORLD);
        if (world != null)
            entity.hurtServer(world, world.damageSources().magic(), entity.getMaxHealth());
    }

    @Override
    public void onMobRemoved(@NonNull ServerLevel level, @NonNull LivingEntity mob, int amplifier, Entity.@NonNull RemovalReason reason) {
        super.onMobRemoved(level, mob, amplifier, reason);
        mob.hurtServer(level, level.damageSources().magic(), mob.getMaxHealth());
    }
}