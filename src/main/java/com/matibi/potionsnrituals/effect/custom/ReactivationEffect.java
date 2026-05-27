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
import org.jspecify.annotations.Nullable;

public class ReactivationEffect extends MobEffect {
    public ReactivationEffect() {
        super(MobEffectCategory.NEUTRAL, 0x7F00FF);
    }

    @Override
    public boolean isInstantenous() {
        return true;
    }

    @Override
    public void applyInstantenousEffect(@NonNull ServerLevel level, @Nullable Entity source, @Nullable Entity owner, LivingEntity mob, int amplification, double scale) {
        for (MobEffectInstance instance : mob.getActiveEffects()) {
            if (instance.getEffect().value() == this || instance.getEffect().equals(ModEffects.RESURRECTION))
                continue;

            mob.addEffect(new MobEffectInstance(
                    instance.getEffect(),
                    instance.getDuration() * ModConfig.get().reactivation_duration,
                    instance.getAmplifier(),
                    instance.isAmbient(),
                    instance.isVisible(),
                    instance.showIcon()
            ));
        }
        super.applyInstantenousEffect(level, source, owner, mob, amplification, scale);
    }
}