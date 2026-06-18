package com.matibi.potionsnrituals.effect.custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.server.level.ServerLevel;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

public class DoubleHealthEffect extends MobEffect {
    public DoubleHealthEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xf4c542);
    }

    @Override
    public boolean isInstantaneous() {
        return true;
    }

    @Override
    public void applyInstantaneousEffect(@NonNull ServerLevel level, @Nullable Entity effectEntity,
                                        @Nullable Entity attacker, @NonNull LivingEntity target,
                                        int amplifier, double proximity) {
        applyDoubleHealth(target, amplifier);
        super.applyInstantaneousEffect(level, effectEntity, attacker, target, amplifier, proximity);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration == 1;
    }

    @Override
    public boolean applyEffectTick(@NonNull ServerLevel level, @NonNull LivingEntity entity, int amplifier) {
        applyDoubleHealth(entity, amplifier);
        return super.applyEffectTick(level, entity, amplifier);
    }

    private static void applyDoubleHealth(LivingEntity target, int amplifier) {
        target.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, -1, 5 * (amplifier + 1), false, false, false));
    }
}