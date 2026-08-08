package com.matibi.potionsnrituals.command.test;

import com.matibi.potionsnrituals.util.TickManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.ClipContext;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.BooleanSupplier;

public record TestHelper(ServerPlayer player) {

    public ServerLevel level() {
        return player.level();
    }

    public void assertTrue(boolean condition, String message) {
        if (!condition)
            throw new TestAssertionError(message);
    }

    public void assertFalse(boolean condition, String message) {
        if (condition)
            throw new TestAssertionError(message);
    }

    public void assertEquals(Object expected, Object actual, String message) {
        if (!Objects.equals(expected, actual))
            throw new TestAssertionError(message + " (expected: " + expected + ", actual: " + actual + ")");
    }

    @SuppressWarnings("ConstantConditions")
    public void assertNotNull(Object object, String message) {
        if (object == null)
            throw new TestAssertionError(message + " (was null)");
    }

    public void assertNull(Object object, String message) {
        if (object != null)
            throw new TestAssertionError(message + " (was not null: " + object + ")");
    }

    public void giveEffect(Holder<MobEffect> effect, int duration, int amplifier) {
        player.addEffect(new MobEffectInstance(effect, duration, amplifier));
    }

    public void giveEffect(Holder<MobEffect> effect, int duration) {
        giveEffect(effect, duration, 0);
    }

    public void giveItem(Item item, int count) {
        player.addItem(new ItemStack(item, count));
    }

    public void giveItem(Item item) {
        giveItem(item, 1);
    }

    public void setPlayerPos(Vec3 pos) {
        player.teleportTo(player.level(), pos.x, pos.y, pos.z, Set.of(), player.getYRot(), player.getXRot(), true);
    }

    public void setPlayerPos(BlockPos pos) {
        setPlayerPos(Vec3.atCenterOf(pos));
    }

    public void waitTicks(int ticks, Runnable continuation) {
        TickManager.runLater(ticks, _ -> continuation.run());
    }

    public void waitUntil(BooleanSupplier condition, Runnable continuation) {
        TickManager.registerWhile(condition, _ -> continuation.run());
    }

    public void waitUntil(BooleanSupplier condition, int maxTicks, Runnable continuation, Runnable onTimeout) {
        TickManager.runLater(maxTicks, _ -> {
            if (!condition.getAsBoolean())
                onTimeout.run();
        });
        waitUntil(condition, continuation);
    }

    public void clearEffects() {
        for (MobEffectInstance instance : List.copyOf(player.getActiveEffects()))
            player.removeEffect(instance.getEffect());
    }

    public void clearInventory() {
        player.getInventory().clearContent();
    }

    // === Entity Spawning ===
    public <T extends Entity> T spawnEntity(EntityType<T> type, Vec3 offset) {
        Vec3 spawnPos = player.position().add(offset);
        T entity = type.create(player.level(), EntitySpawnReason.TRIGGERED);
        if (entity != null) {
            entity.teleportTo(spawnPos.x, spawnPos.y, spawnPos.z);
            player.level().addFreshEntity(entity);
        }
        return entity;
    }

    public <T extends LivingEntity> T spawnLiving(EntityType<T> type, Vec3 offset) {
        return spawnEntity(type, offset);
    }

    // === Player Look Direction ===
    public void setPlayerLook(float yaw, float pitch) {
        player.setYRot(yaw);
        player.setXRot(pitch);
        player.yRotO = yaw;
        player.xRotO = pitch;
    }

    // === Raycasting Helpers ===
    public BlockHitResult getLookedAtBlock(double range) {
        Vec3 start = player.getEyePosition();
        Vec3 end = start.add(player.getViewVector(1.0F).scale(range));

        return player.level().clip(new ClipContext(
                start,
                end,
                ClipContext.Block.OUTLINE,
                ClipContext.Fluid.NONE,
                player
        ));
    }

    public LivingEntity getLookedAtEntity(double range) {
        Vec3 start = player.getEyePosition();
        Vec3 lookVec = player.getViewVector(1.0F);
        Vec3 end = start.add(lookVec.scale(range));
        AABB area = player.getBoundingBox().expandTowards(lookVec.scale(range)).inflate(1.0);

        EntityHitResult hitResult = net.minecraft.world.entity.projectile.ProjectileUtil.getEntityHitResult(
                player,
                start,
                end,
                area,
                entity -> entity != player && entity instanceof LivingEntity le && le.isAlive(),
                range * range
        );

        return hitResult != null && hitResult.getEntity() instanceof LivingEntity le ? le : null;
    }

    // === Verification Helpers ===
    public boolean hasEntityAt(Class<? extends Entity> entityClass, BlockPos pos, double radius) {
        AABB box = new AABB(pos).inflate(radius);
        List<? extends Entity> entities = player.level().getEntitiesOfClass(entityClass, box);
        return !entities.isEmpty();
    }

    public boolean hasEffect(LivingEntity target, Holder<MobEffect> effect, int minDuration) {
        var instance = target.getEffect(effect);
        return instance != null && instance.getDuration() >= minDuration;
    }

    public boolean playerAt(Vec3 expected, double tolerance) {
        return player.position().distanceTo(expected) <= tolerance;
    }

    public static final class TestAssertionError extends RuntimeException {
        public TestAssertionError(String message) {
            super(message);
        }
    }
}