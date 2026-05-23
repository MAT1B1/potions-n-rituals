package com.matibi.potionsnrituals.effect.custom;

import com.matibi.potionsnrituals.config.ModConfig;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level.ExplosionInteraction;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class UnstableEffect extends MobEffect {
    public UnstableEffect() {
        super(MobEffectCategory.HARMFUL, 0x000000);
    }

    @Override
    public boolean isInstantenous() {
        return true;
    }

    @Override
    public boolean applyEffectTick(@NonNull ServerLevel world, @NonNull LivingEntity entity, int amplifier) {
        applyEffect(world, null, null, entity, amplifier, 1.0D);
        return super.applyEffectTick(world, entity, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) { return true; }

    @Override
    public void applyInstantenousEffect(@NonNull ServerLevel world, @Nullable Entity effectEntity, @Nullable Entity attacker, @NonNull LivingEntity target, int amplifier, double proximity) {
        applyEffect(world, effectEntity, attacker, target, amplifier, proximity);
        super.applyInstantenousEffect(world, effectEntity, attacker, target, amplifier, proximity);
    }

    private static void applyEffect(ServerLevel world, @Nullable Entity effectEntity, @Nullable Entity attacker, LivingEntity target, int amplifier, double proximity) {
        RandomSource r = world.getRandom();

        float explodeChance = ModConfig.unstable_explosion_chance + amplifier * ModConfig.unstable_explosion_chance_per_level;

        if (r.nextFloat() < explodeChance) {
            float power = ModConfig.unstable_explosion_power + amplifier * ModConfig.unstable_explosion_power_per_level;
            world.explode(
                    target,
                    target.getX(), target.getY(), target.getZ(),
                    power,
                    false,
                    ExplosionInteraction.MOB
            );

            world.playSound(null, target.blockPosition(), SoundEvents.CREEPER_PRIMED,
                    SoundSource.HOSTILE, 0.9f, 0.7f + r.nextFloat() * 0.4f);
            world.sendParticles(ParticleTypes.EXPLOSION, target.getX(), target.getY(0.5), target.getZ(),
                    1, 0.0, 0.0, 0.0, 0.0);
        } else {
            Holder<MobEffect> pick = ModConfig.unstable_pool.get(r.nextInt(ModConfig.unstable_pool.size()));

            int seconds = ModConfig.unstable_effect_seconds + amplifier * ModConfig.unstable_effect_seconds_per_level;
            int level = pick == MobEffects.WITHER ? Math.max(0, amplifier - 1) : amplifier;

            target.addEffect(new MobEffectInstance(pick, seconds * 20, level, false, true, true));

            world.sendParticles(ParticleTypes.SMOKE, target.getX(), target.getY(0.6), target.getZ(),
                    8 + amplifier * 4, 0.4, 0.2, 0.4, 0.01);
            world.playSound(null, target.blockPosition(), SoundEvents.BREWING_STAND_BREW,
                    SoundSource.PLAYERS, 0.7f, 0.9f + r.nextFloat() * 0.2f);
        }
    }
}