package com.matibi.potionsnrituals.effect.custom.terrain;

import com.matibi.potionsnrituals.config.ModConfig;
import com.matibi.potionsnrituals.effect.TerrainApplicableEffect;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import org.jspecify.annotations.NonNull;

public class AcidEffect extends MobEffect implements TerrainApplicableEffect {
    private static final ParticleOptions ACID_DUST = new DustParticleOptions(0x7FFF00, 1.0f);

    public AcidEffect() {
        super(MobEffectCategory.HARMFUL, 0x7FFF00);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration % 20 == 0;
    }

    @Override
    public boolean applyEffectTick(@NonNull ServerLevel world, LivingEntity entity, int amplifier) {
        entity.hurtServer(world, entity.damageSources().magic(), 1.0F + amplifier);
        return super.applyEffectTick(world, entity, amplifier);
    }

    @Override
    public void useOnBlock(ServerLevel world, BlockPos center, int duration, int amplifier) {
        int radius = ModConfig.acid_radius + amplifier * ModConfig.acid_radius_per_level;
        int depth = ModConfig.acid_depth + amplifier *  ModConfig.acid_depth_per_level;

        for (int dx = -radius; dx <= radius; dx++) {
            for (int dz = -radius; dz <= radius; dz++) {
                for (int dy = 0; dy > -depth; dy--) {
                    BlockPos target = center.offset(dx, dy, dz);
                    float hardness = world.getBlockState(target).getDestroySpeed(world, target);
                    if (!world.getBlockState(target).isAir()
                            && hardness >= 0.0F
                            && (hardness < 50.0f || amplifier > 0)) {
                        world.destroyBlock(target, false);
                        spawnAcidParticles(world, target, amplifier);
                    }
                }
            }
        }
    }

    private static void spawnAcidParticles(ServerLevel world, BlockPos pos, int amplifier) {
        RandomSource r = world.getRandom();

        double x = pos.getX() + 0.5;
        double y = pos.getY() + 1.01;
        double z = pos.getZ() + 0.5;

        int dustCount = 4 + amplifier * 2;
        world.sendParticles(ACID_DUST, x, y, z, dustCount, 0.35, 0.15, 0.35, 0.008);

        if (r.nextFloat() < 0.35f)
            world.sendParticles(ParticleTypes.SMOKE, x, y + 0.05, z, 1 + amplifier, 0.25, 0.05, 0.25, 0.01);

        if (r.nextFloat() < 0.15f)
            world.playSound(
                    null,
                    x, y, z,
                    SoundEvents.LAVA_EXTINGUISH,
                    SoundSource.BLOCKS,
                    0.35f + 0.1f * amplifier,
                    0.8f + r.nextFloat() * 0.4f
            );
    }
}
