package com.matibi.potionsnrituals.effect.custom.terrain;

import com.matibi.potionsnrituals.effect.TerrainEffect;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

public class AlchemistEffect extends MobEffect implements TerrainEffect {
    public AlchemistEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xd1c70d);
    }

    @Override
    public boolean isInstantaneous() {
        return true;
    }

    @Override
    public boolean applyEffectTick(@NonNull ServerLevel world, @NonNull LivingEntity entity, int amplifier) {
        applyEffect(world, null, null, entity, amplifier, 1.0D);
        return super.applyEffectTick(world, entity, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyInstantaneousEffect(@NonNull ServerLevel world, @Nullable Entity effectEntity, @Nullable Entity attacker, @NonNull LivingEntity target, int amplifier, double proximity) {
        applyEffect(world, effectEntity, attacker, target, amplifier, proximity);
        super.applyInstantaneousEffect(world, effectEntity, attacker, target, amplifier, proximity);
    }

    private static void applyEffect(ServerLevel world, @Nullable Entity effectEntity, @Nullable Entity attacker, LivingEntity target, int amplifier, double proximity) {
        if (!(target instanceof Player player)) return;

        ItemStack offhand = player.getOffhandItem();
        int count = offhand.getCount();

        if (offhand.is(Items.COAL) || offhand.is(Items.CHARCOAL))
            player.setItemInHand(InteractionHand.OFF_HAND, new ItemStack(Items.GOLD_INGOT, count));
        else if (offhand.is(Items.COAL_BLOCK))
            player.setItemInHand(InteractionHand.OFF_HAND, new ItemStack(Items.GOLD_BLOCK, count));
        else if (offhand.is(Items.COAL_ORE))
            player.setItemInHand(InteractionHand.OFF_HAND, new ItemStack(Items.GOLD_ORE, count));
        else if (offhand.is(Items.DEEPSLATE_COAL_ORE))
            player.setItemInHand(InteractionHand.OFF_HAND, new ItemStack(Items.DEEPSLATE_GOLD_ORE, count));
    }

    @Override
    public void useOnBlock(ServerLevel world, BlockPos block, int duration, int amplifier) {
        var blockType = world.getBlockState(block).getBlock();

        if (blockType == Blocks.COAL_BLOCK)
            world.setBlock(block, Blocks.GOLD_BLOCK.defaultBlockState(), 3);
        else if (blockType == Blocks.COAL_ORE)
            world.setBlock(block, Blocks.GOLD_ORE.defaultBlockState(), 3);
        else if (blockType == Blocks.DEEPSLATE_COAL_ORE)
            world.setBlock(block, Blocks.DEEPSLATE_GOLD_ORE.defaultBlockState(), 3);
    }

    @Override
    public boolean isBlockNonApplicable(ServerLevel world, BlockPos block) {
        BlockState state = world.getBlockState(block);
        return state.getBlock() != Blocks.COAL_BLOCK
                && state.getBlock() != Blocks.COAL_ORE
                && state.getBlock() != Blocks.DEEPSLATE_COAL_ORE;
    }
}