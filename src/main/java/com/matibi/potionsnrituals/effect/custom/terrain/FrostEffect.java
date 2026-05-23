package com.matibi.potionsnrituals.effect.custom.terrain;

import com.matibi.potionsnrituals.config.ModConfig;
import com.matibi.potionsnrituals.effect.ModEffects;
import com.matibi.potionsnrituals.effect.TerrainApplicableEffect;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NonNull;

import java.util.Objects;

public class FrostEffect extends MobEffect implements TerrainApplicableEffect {
    public FrostEffect() {
        super(MobEffectCategory.HARMFUL, 0x80D8FF);
    }

    @Override
    public boolean applyEffectTick(@NonNull ServerLevel world, LivingEntity entity, int amplifier) {
        if (entity.hasEffect(ModEffects.IGNITION)) {
            entity.setTicksFrozen(0);
            return super.applyEffectTick(world, entity, amplifier);
        }

        MobEffectInstance slowness = entity.getEffect(MobEffects.SLOWNESS);
        if (slowness == null || slowness.getDuration() < 20)
            entity.addEffect(new MobEffectInstance(
                MobEffects.SLOWNESS, 5 * 20, 3 + amplifier, false, false));

        entity.setTicksFrozen(entity.getTicksRequiredToFreeze() + 1);

        if (Objects.requireNonNull(entity.getEffect(ModEffects.FROST)).getDuration() % 20 == 0)
            entity.hurtServer(world, entity.damageSources().magic(), 1.0F);

        return super.applyEffectTick(world, entity, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public void useOnBlock(ServerLevel world, BlockPos block, int duration, int amplifier) {
        int radius = ModConfig.frost_radius + amplifier * ModConfig.frost_radius_per_level;
        BlockPos.betweenClosed(block.offset(-radius, -1, -radius), block.offset(radius, 1, radius))
                .forEach(pos -> {
                    BlockState state = world.getBlockState(pos);
                    if (state.is(Blocks.WATER) || state.getFluidState().is(FluidTags.WATER)) {
                        world.setBlock(pos, Blocks.ICE.defaultBlockState(), 3);
                    }
                });
    }
}