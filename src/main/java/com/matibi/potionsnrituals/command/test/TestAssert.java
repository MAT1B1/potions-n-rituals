package com.matibi.potionsnrituals.command.test;

import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestAssert {

    private static final Map<String, TestResult> ASYNC_RESULTS = new HashMap<>();

    public static boolean hasAsyncResult(String testId) {
        return ASYNC_RESULTS.containsKey(testId);
    }

    public static TestResult consumeAsyncResult(String testId) {
        return ASYNC_RESULTS.remove(testId);
    }

    public static void clearAsyncResults() {
        ASYNC_RESULTS.clear();
    }

    public static void hasEffect(ServerPlayer player, Holder<MobEffect> effect, String context) throws AssertionError {
        if (!player.hasEffect(effect))
            throw new AssertionError(context + " : le joueur n'a pas l'effet " + effect.getRegisteredName());
    }

    public static void entityHasEffect(LivingEntity entity, Holder<MobEffect> effect, String context) throws AssertionError {
        if (!entity.hasEffect(effect))
            throw new AssertionError(context + " : l'entité n'a pas l'effet " + effect.getRegisteredName());
    }

    public static void entityIsDead(LivingEntity entity, String context) throws AssertionError {
        if (!entity.isDeadOrDying())
            throw new AssertionError(context + " : l'entité est vivante (devrait être morte)");
    }

    public static void clearPlayer(ServerPlayer player) {
        player.removeAllEffects();
        player.setHealth(player.getMaxHealth());
        player.getInventory().clearContent();
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            player.setItemSlot(slot, ItemStack.EMPTY);
        }
        player.fallDistance = 0;
        player.clearFire();
    }

    public static <T extends LivingEntity> T spawnEntity(ServerLevel level, ServerPlayer player, EntityType<T> type, String context) {
        T entity = type.create(level, EntitySpawnReason.COMMAND);
        if (entity == null)
            throw new RuntimeException(context + " : impossible de créer l'entité");
        entity.setPos(player.getX(), player.getY(), player.getZ());
        level.addFreshEntity(entity);
        return entity;
    }

    public static void removeEntity(LivingEntity entity) {
        entity.remove(Entity.RemovalReason.DISCARDED);
    }

    public static void forceTickEffects(ServerLevel level, LivingEntity entity) {
        for (MobEffectInstance inst : List.copyOf(entity.getActiveEffects())) {
            MobEffect effect = inst.getEffect().value();
            if (effect.shouldApplyEffectTickThisTick(inst.getDuration(), inst.getAmplifier()))
                effect.applyEffectTick(level, entity, inst.getAmplifier());
        }
    }

    public static void forceTickEffects(ServerLevel level, ServerPlayer player) {
        forceTickEffects(level, (LivingEntity) player);
    }

    public static void sendAsyncResult(String testId, TestResult result) {
        ASYNC_RESULTS.put(testId, result);
    }
}
