package com.matibi.potionsnrituals.effect.custom;

import com.matibi.potionsnrituals.config.ModConfig;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import org.jspecify.annotations.NonNull;

public class HydrophobiaEffect extends MobEffect {
    public HydrophobiaEffect() {
        super(MobEffectCategory.HARMFUL, 0x2A7FFF);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration % 20 == 0;
    }

    @Override
    public boolean applyEffectTick(@NonNull ServerLevel world, @NonNull LivingEntity entity, int amplifier) {
        if (entity.isInWater() || entity.isInWaterOrRain()) {
            float damage = ModConfig.get().hydrophobia_damage + amplifier * ModConfig.get().hydrophobia_damage_per_level;
            entity.hurtServer(world, world.damageSources().drown(), damage);
        }
        return super.applyEffectTick(world, entity, amplifier);
    }
}
