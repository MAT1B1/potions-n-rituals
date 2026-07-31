package com.matibi.potionsnrituals.command.test;

import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
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

    public static void hasNoEffect(ServerPlayer player, Holder<MobEffect> effect, String context) throws AssertionError {
        if (player.hasEffect(effect))
            throw new AssertionError(context + " : le joueur a l'effet " + effect.getRegisteredName() + " (ne devrait pas)");
    }

    public static void entityHasEffect(LivingEntity entity, Holder<MobEffect> effect, String context) throws AssertionError {
        if (!entity.hasEffect(effect))
            throw new AssertionError(context + " : l'entité n'a pas l'effet " + effect.getRegisteredName());
    }

    public static void healthAbove(ServerPlayer player, float min, String context) throws AssertionError {
        if (player.getHealth() < min)
            throw new AssertionError(context + " : vie " + player.getHealth() + " < " + min);
    }

    public static void healthBelow(ServerPlayer player, float max, String context) throws AssertionError {
        if (player.getHealth() > max)
            throw new AssertionError(context + " : vie " + player.getHealth() + " > " + max);
    }

    public static void healthEquals(ServerPlayer player, float expected, float tolerance, String context) throws AssertionError {
        float actual = player.getHealth();
        if (Math.abs(actual - expected) > tolerance)
            throw new AssertionError(context + " : vie attendue " + expected + " ± " + tolerance + ", obtenue " + actual);
    }

    public static void attributeAtLeast(LivingEntity entity, Holder<net.minecraft.world.entity.ai.attributes.Attribute> attribute, double expected, String context) throws AssertionError {
        AttributeInstance inst = entity.getAttribute(attribute);
        if (inst == null)
            throw new AssertionError(context + " : attribut introuvable");
        double actual = inst.getValue();
        if (actual < expected)
            throw new AssertionError(context + " : attribut = " + actual + " < " + expected);
    }

    public static void entityIsAlive(LivingEntity entity, String context) throws AssertionError {
        if (entity.isDeadOrDying())
            throw new AssertionError(context + " : l'entité est morte");
    }

    public static void entityIsDead(LivingEntity entity, String context) throws AssertionError {
        if (!entity.isDeadOrDying())
            throw new AssertionError(context + " : l'entité est vivante (devrait être morte)");
    }

    public static void effectInstanceMatches(ServerPlayer player, Holder<MobEffect> effect, int expectedAmplifier, int expectedDurationMin, String context) throws AssertionError {
        MobEffectInstance inst = player.getEffect(effect);
        if (inst == null)
            throw new AssertionError(context + " : effet " + effect.getRegisteredName() + " non trouvé");
        if (inst.getAmplifier() != expectedAmplifier)
            throw new AssertionError(context + " : amplificateur attendu " + expectedAmplifier + ", obtenu " + inst.getAmplifier());
        if (inst.getDuration() < expectedDurationMin)
            throw new AssertionError(context + " : durée " + inst.getDuration() + " < " + expectedDurationMin);
    }

    public static void armorDurabilityBelow(ServerPlayer player, EquipmentSlot slot, int maxDurability, String context) throws AssertionError {
        ItemStack stack = player.getItemBySlot(slot);
        if (stack.isEmpty())
            throw new AssertionError(context + " : slot " + slot + " vide");
        int currentDurability = stack.getMaxDamage() - stack.getDamageValue();
        if (currentDurability >= maxDurability)
            throw new AssertionError(context + " : durabilité " + currentDurability + " >= " + maxDurability + " (pas assez endommagée)");
    }

    public static void effectAmplifierAtLeast(ServerPlayer player, Holder<MobEffect> effect, int minAmplifier, String context) throws AssertionError {
        MobEffectInstance inst = player.getEffect(effect);
        if (inst == null)
            throw new AssertionError(context + " : effet " + effect.getRegisteredName() + " non trouvé");
        if (inst.getAmplifier() < minAmplifier)
            throw new AssertionError(context + " : amplificateur " + inst.getAmplifier() + " < " + minAmplifier);
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

    public static void setSurvival(ServerPlayer player) {
        if (player.isCreative() || player.isSpectator()) {
            player.setGameMode(net.minecraft.world.level.GameType.SURVIVAL);
        }
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
            MobEffect effect = (MobEffect) inst.getEffect().value();
            if (effect.shouldApplyEffectTickThisTick(inst.getDuration(), inst.getAmplifier())) {
                effect.applyEffectTick(level, entity, inst.getAmplifier());
            }
        }
    }

    public static void forceTickEffects(ServerLevel level, ServerPlayer player) {
        forceTickEffects(level, (LivingEntity) player);
    }

    public static void sendAsyncResult(String testId, TestResult result) {
        ASYNC_RESULTS.put(testId, result);
    }
}
