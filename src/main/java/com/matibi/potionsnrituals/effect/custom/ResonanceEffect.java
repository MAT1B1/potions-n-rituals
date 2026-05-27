package com.matibi.potionsnrituals.effect.custom;

import com.matibi.potionsnrituals.config.ModConfig;
import com.matibi.potionsnrituals.effect.ModEffects;
import net.minecraft.core.particles.DustColorTransitionOptions;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.entity.LivingEntity;
import org.jspecify.annotations.NonNull;

import java.util.Collection;
import java.util.List;
import java.util.OptionalInt;

public class ResonanceEffect extends MobEffect {
    public ResonanceEffect() {
        super(MobEffectCategory.NEUTRAL, 0xC29DC7);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyEffectTick(@NonNull ServerLevel world, LivingEntity entity, int amplifier) {
        int maxRadius = ModConfig.get().resonance_radius + amplifier * ModConfig.get().resonance_radius_per_level;

        Collection<MobEffectInstance> effects = entity.getActiveEffects().stream()
                .filter(e -> !e.getEffect().equals(ModEffects.RESONANCE))
                .toList();

        OptionalInt rgb = PotionContents.getColorOptional(effects);
        int baseColor = rgb.isEmpty() ? 0xC29DC7 : rgb.getAsInt();
        int centerColor = lighten(baseColor);

        spawnPulseWave(world, entity, amplifier, maxRadius, centerColor, baseColor);
        spawnOrbitRing(world, entity, amplifier, maxRadius, baseColor);

        MobEffectInstance self = entity.getEffect(ModEffects.RESONANCE);
        if (self != null && self.getDuration() % 40 == 0 && !effects.isEmpty()) {
            List<LivingEntity> nearby = world.getEntitiesOfClass(
                    LivingEntity.class,
                    entity.getBoundingBox().inflate(maxRadius),
                    e -> e != entity && e.isAlive()
            );
            for (LivingEntity target : nearby) {
                for (MobEffectInstance effect : effects) {
                    MobEffectInstance copy = new MobEffectInstance(
                            effect.getEffect(),
                            Math.max(1, effect.getDuration() / 2),
                            effect.getAmplifier()
                    );
                    target.addEffect(copy);
                }
            }
        }
        return true;
    }

    private static void spawnPulseWave(ServerLevel world, LivingEntity entity, int amplifier,
                                       double maxRadius, int centerColor, int edgeColor) {
        int period = Math.max(8, ModConfig.get().pulse_period - amplifier * 2);
        double phase = ((world.getGameTime() + (entity.getId() & 7)) % period) / (double) period;

        double eased = easeOutSine(phase);
        double r = 0.5 + eased * (maxRadius - 0.5);

        double y = entity.getY() + entity.getBbHeight() * 0.55;
        double step = (Math.PI * 2.0) / ModConfig.get().pulse_period;

        var pulse = new DustColorTransitionOptions(centerColor, edgeColor, ModConfig.get().scale_pulse);
        for (int i = 0; i < ModConfig.get().pulse_points; i++) {
            double angle = i * step;
            double x = entity.getX() + Math.cos(angle) * r;
            double z = entity.getZ() + Math.sin(angle) * r;
            world.sendParticles(pulse, x, y, z, 1, 0.0, 0.0, 0.0, 0.0);
        }
    }

    private static void spawnOrbitRing(ServerLevel world, LivingEntity entity, int amplifier,
                                       double radius, int baseColor) {
        double speed = ModConfig.get().orbit_speed_base + amplifier * 0.02;
        double t = world.getGameTime() * speed;

        double y = entity.getY() + entity.getBbHeight() * 0.6;
        double phaseOffset = (entity.getId() & 15) * 0.25;
        double step = (Math.PI * 2.0) / ModConfig.get().orbit_points;
        var orbit = new DustParticleOptions(baseColor, ModConfig.get().scale_orbit);

        for (int i = 0; i < ModConfig.get().orbit_points; i++) {
            double angle = t + phaseOffset + i * step;
            double x = entity.getX() + Math.cos(angle) * radius;
            double z = entity.getZ() + Math.sin(angle) * radius;
            world.sendParticles(orbit, x, y, z, 1, 0.0, 0.0, 0.0, 0.0);
        }
    }

    private static int lighten(int color) {
        float amount = 0.35f;
        int r = (color >> 16) & 0xFF, g = (color >> 8) & 0xFF, b = color & 0xFF;
        r = clamp255((int) (r + (255 - r) * amount));
        g = clamp255((int) (g + (255 - g) * amount));
        b = clamp255((int) (b + (255 - b) * amount));
        return (r << 16) | (g << 8) | b;
    }

    private static int clamp255(int v) { return Math.clamp(v, 0, 255); }

    private static double easeOutSine(double x) {
        return Math.sin((x * Math.PI) / 2.0);
    }
}