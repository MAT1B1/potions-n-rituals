package com.matibi.potionsnrituals.effect.custom.terrain;

import com.matibi.potionsnrituals.effect.ModEffects;
import com.matibi.potionsnrituals.effect.TerrainEffect;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NonNull;

import java.util.HashMap;
import java.util.Map;

public class IgnitionEffect extends MobEffect implements TerrainEffect {
    private static final Map<Block, Block> SMELTABLE = new HashMap<>();

    static {
        SMELTABLE.put(Blocks.SAND, Blocks.GLASS);
        SMELTABLE.put(Blocks.RED_SAND, Blocks.GLASS);
        SMELTABLE.put(Blocks.COBBLESTONE, Blocks.STONE);
        SMELTABLE.put(Blocks.STONE, Blocks.SMOOTH_STONE);
        SMELTABLE.put(Blocks.NETHERRACK, Blocks.NETHER_BRICKS);
        SMELTABLE.put(Blocks.CLAY, Blocks.BRICKS);
        SMELTABLE.put(Blocks.WET_SPONGE, Blocks.SPONGE);
        SMELTABLE.put(Blocks.RAW_IRON_BLOCK, Blocks.IRON_BLOCK);
        SMELTABLE.put(Blocks.RAW_COPPER_BLOCK, Blocks.COPPER_BLOCK);
        SMELTABLE.put(Blocks.RAW_GOLD_BLOCK, Blocks.GOLD_BLOCK);
    }

    public IgnitionEffect() {
        super(MobEffectCategory.HARMFUL, 0xFF4500);
    }

    @Override
    public boolean applyEffectTick(@NonNull ServerLevel world, LivingEntity entity, int amplifier) {
        if (!entity.isOnFire() && !entity.isInLiquid() && !entity.hasEffect(ModEffects.FROST))
            entity.setRemainingFireTicks(4 * 20);
        if (entity.hasEffect(ModEffects.FROST) && entity.hasEffect(MobEffects.SLOWNESS))
            entity.removeEffect(MobEffects.SLOWNESS);
        return super.applyEffectTick(world, entity, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public void useOnBlock(ServerLevel world, BlockPos blockPos, int duration, int amplifier) {
        BlockState state = world.getBlockState(blockPos);
        Block block = state.getBlock();

        world.setBlock(blockPos, SMELTABLE.getOrDefault(block, Blocks.COAL_BLOCK).defaultBlockState(), 3);

        world.sendParticles(
                ParticleTypes.FLAME,
                blockPos.getX() + 0.5,
                blockPos.getY() + 0.5,
                blockPos.getZ() + 0.5,
                5, 0.4, 0.4, 0.4, 0.01
        );
    }

    @Override
    public boolean isBlockNonApplicable(ServerLevel world, BlockPos blockPos) {
        BlockState state = world.getBlockState(blockPos);
        Block block = state.getBlock();
        return !SMELTABLE.containsKey(block) && !state.is(BlockTags.LOGS);
    }
}