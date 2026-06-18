package com.matibi.potionsnrituals.effect.custom;

import com.matibi.potionsnrituals.config.ModConfig;
import com.matibi.potionsnrituals.network.OreSensePayload;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;

public class OreSenseEffect extends MobEffect {

    public OreSenseEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xF0C838);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration % ModConfig.get().ore_scan_interval == 0;
    }

    @Override
    public boolean applyEffectTick(@NonNull ServerLevel world, @NonNull LivingEntity entity, int amplifier) {
        if (!(entity instanceof ServerPlayer player)) return true;

        int range = ModConfig.get().ore_scan_range + amplifier * ModConfig.get().ore_scan_range_per_level;

        ScanResult inView  = scanForOres(world, player, range, true);
        ScanResult offView = scanForOres(world, player, range, false);
        float angle = computeAngle(player, offView.closestPos());

        ServerPlayNetworking.send(player, new OreSensePayload(
                inView.intensity(),  inView.color(),
                offView.intensity(), offView.color(), angle
        ));
        return true;
    }

    /**
     * @param inCone true  → ore dans le cône de regard (dot > 0.3)
     *               false → ore hors du cône
     */
    private static ScanResult scanForOres(ServerLevel world, ServerPlayer player, int range, boolean inCone) {
        Vec3 eyePos  = player.getEyePosition();
        Vec3 lookVec = player.getLookAngle();
        BlockPos playerPos = player.blockPosition();

        double closestDistSq = Double.MAX_VALUE;
        int closestColor = ModConfig.get().ore_color;
        BlockPos closestPos = null;

        for (BlockPos pos : BlockPos.betweenClosed(
                playerPos.offset(-range, -range, -range),
                playerPos.offset(range, range, range)
        )) {
            BlockState state = world.getBlockState(pos);
            int color = getOreColor(state);
            if (color == -1) continue;

            Vec3 toBlock = Vec3.atCenterOf(pos).subtract(eyePos).normalize();
            boolean isInCone = toBlock.dot(lookVec) > 0.3;
            if (isInCone != inCone) continue;

            double distSq = eyePos.distanceToSqr(Vec3.atCenterOf(pos));
            if (distSq < closestDistSq) {
                closestDistSq = distSq;
                closestColor  = color;
                closestPos    = pos.immutable();
            }
        }

        if (closestPos == null)
            return new ScanResult(0.0f, ModConfig.get().ore_color, null);

        double dist = Math.sqrt(closestDistSq);
        float intensity = (float) Math.clamp(1.0 - (dist - 1.0) / (range - 1.0), 0.0, 1.0);
        return new ScanResult(intensity, closestColor, closestPos);
    }

    private static float computeAngle(ServerPlayer player, BlockPos pos) {
        if (pos == null) return 0.0f;
        Vec3 toOre = Vec3.atCenterOf(pos).subtract(player.getEyePosition());
        double oreYaw    = Math.atan2(-toOre.x, toOre.z);
        double playerYaw = Math.toRadians(player.getYRot());
        float angle = (float)(oreYaw - playerYaw);
        while (angle >  Math.PI) angle -= (float) (2 * Math.PI);
        while (angle < -Math.PI) angle += (float) (2 * Math.PI);
        return angle;
    }

    private static int getOreColor(BlockState state) {
        Block block = state.getBlock();
        if (block == Blocks.COAL_ORE || block == Blocks.DEEPSLATE_COAL_ORE)
            return ModConfig.get().coal_color;
        if (state.is(BlockTags.IRON_ORES))      return ModConfig.get().iron_color;
        if (state.is(BlockTags.GOLD_ORES))      return ModConfig.get().gold_color;
        if (block == Blocks.DIAMOND_ORE || block == Blocks.DEEPSLATE_DIAMOND_ORE)
            return ModConfig.get().diamond_color;
        if (block == Blocks.EMERALD_ORE || block == Blocks.DEEPSLATE_EMERALD_ORE)
            return ModConfig.get().emerald_color;
        if (block == Blocks.LAPIS_ORE || block == Blocks.DEEPSLATE_LAPIS_ORE)
            return ModConfig.get().lapis_color;
        if (block == Blocks.REDSTONE_ORE || block == Blocks.DEEPSLATE_REDSTONE_ORE)
            return ModConfig.get().redstone_color;
        if (state.is(BlockTags.COPPER_ORES))    return ModConfig.get().copper_color;
        if (state.is(Blocks.ANCIENT_DEBRIS))    return ModConfig.get().ancient_debris_color;
        if (state.is(Blocks.NETHER_QUARTZ_ORE)) return ModConfig.get().quartz_color;
        return -1;
    }

    @Override
    public void onEffectRemoved(@NonNull MobEffectInstance effectInstance, @NonNull LivingEntity entity) {
        if (!(entity instanceof ServerPlayer player)) return;
        ServerPlayNetworking.send(player, new OreSensePayload(0.0f, ModConfig.get().ore_color, 0.0f, ModConfig.get().ore_color, 0.0f));
    }

    @Override
    public void onMobRemoved(@NonNull ServerLevel level, @NonNull LivingEntity mob, int amplifier, Entity.@NonNull RemovalReason reason) {
        if (!(mob instanceof ServerPlayer player)) return;
        ServerPlayNetworking.send(player, new OreSensePayload(0.0f, ModConfig.get().ore_color, 0.0f, ModConfig.get().ore_color, 0.0f));

        super.onMobRemoved(level, mob, amplifier, reason);
    }

    private record ScanResult(float intensity, int color, BlockPos closestPos) {}
}