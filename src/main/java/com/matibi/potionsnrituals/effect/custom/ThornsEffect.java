package com.matibi.potionsnrituals.effect.custom;

import com.matibi.potionsnrituals.config.ModConfig;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import org.jspecify.annotations.NonNull;

public class ThornsEffect extends MobEffect {
    public ThornsEffect() {
        super(MobEffectCategory.HARMFUL, 0x55FF55);
    }

    @Override
    public void onMobHurt(@NonNull ServerLevel world, @NonNull LivingEntity entity, int amplifier, DamageSource source, float amount) {
        if (source.getEntity() instanceof LivingEntity attacker) {
            float reflected = amount * (ModConfig.get().base_thorns + amplifier * ModConfig.get().thorns_per_level);
            attacker.hurtServer(world, source, reflected);
        }
    }
}