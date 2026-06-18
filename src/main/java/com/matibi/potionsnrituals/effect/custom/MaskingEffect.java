package com.matibi.potionsnrituals.effect.custom;

import com.matibi.potionsnrituals.effect.ModEffects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.List;

public class MaskingEffect extends MobEffect {
    public MaskingEffect() {super(MobEffectCategory.NEUTRAL, 0x444444);}

    @Override
    public boolean isInstantaneous() {
        return true;
    }

    @Override
    public void applyInstantaneousEffect(@NonNull ServerLevel level, @Nullable Entity source, @Nullable Entity owner, @NonNull LivingEntity mob, int amplification, double scale) {
        super.applyInstantaneousEffect(level, source, owner, mob, amplification, scale);

        List<MobEffectInstance> effects = mob.getActiveEffects().stream()
                .filter(e -> !e.getEffect().equals(ModEffects.MASKING)
                        && !e.getEffect().equals(ModEffects.RESURRECTION))
                .map(e -> new MobEffectInstance(
                        e.getEffect(),
                        e.getDuration(),
                        e.getAmplifier(),
                        e.isAmbient(),
                        false,
                        false
                ))
                .toList();

        effects.forEach(e -> mob.removeEffect(e.getEffect()));

        effects.forEach(mob::addEffect);
    }
}
