package com.matibi.potionsnrituals.effect.custom;

import com.matibi.potionsnrituals.config.ModConfig;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NonNull;

import com.matibi.potionsnrituals.effect.TerrainApplicableEffect;

public class PhotosynthesisEffect extends MobEffect implements TerrainApplicableEffect {
    public PhotosynthesisEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x55FF55);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        int interval = (20 * 10) >> amplifier;
        if (interval == 0) interval = 1;
        return duration % interval == 0;
    }

    @Override
    public boolean applyEffectTick(@NonNull ServerLevel world, @NonNull LivingEntity entity, int amplifier) {
        if (!(entity instanceof Player player)
                || !world.dimension().equals(Level.OVERWORLD)
                || !world.canSeeSky(player.blockPosition())
                || world.getSkyDarken() > 4)
            return super.applyEffectTick(world, entity, amplifier);

        var hunger = player.getFoodData();
        hunger.eat(ModConfig.photosynthesis_food,
                ModConfig.photosynthesis_base_saturation + ModConfig.photosynthesis_saturation_per_level * amplifier);

        double x = player.getX();
        double y = player.getY(0.5);
        double z = player.getZ();

        world.sendParticles(
                ParticleTypes.HAPPY_VILLAGER,
                x, y, z,
                6 + amplifier * 2,
                0.4, 0.6, 0.4,
                0.01
        );

        return super.applyEffectTick(world, entity, amplifier);
    }

    @Override
    public void useOnBlock(ServerLevel world, BlockPos block, int duration, int amplifier) {
        if (!world.canSeeSky(block.above())) return;

        BlockState state = world.getBlockState(block);
        if (state.getBlock() instanceof BonemealableBlock growable) {
            if (growable.isValidBonemealTarget(world, block, state)) {
                growable.performBonemeal(world, world.getRandom(), block, state);
            }
        }
    }

    @Override
    public boolean isBlockNonApplicable(ServerLevel world, BlockPos block) {
        if (!world.canSeeSky(block.above())) return true;

        BlockState state = world.getBlockState(block);
        if (!(state.getBlock() instanceof BonemealableBlock growable)) return true;

        return !growable.isValidBonemealTarget(world, block, state);
    }
}