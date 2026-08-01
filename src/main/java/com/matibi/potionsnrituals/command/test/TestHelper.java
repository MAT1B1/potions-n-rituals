package com.matibi.potionsnrituals.command.test;

import com.matibi.potionsnrituals.util.TickManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

import java.util.Objects;
import java.util.Set;
import java.util.function.BooleanSupplier;

public record TestHelper(ServerPlayer player) {

    public ServerLevel level() {
        return player.level();
    }

    public void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new TestAssertionError(message);
        }
    }

    public void assertFalse(boolean condition, String message) {
        if (condition) {
            throw new TestAssertionError(message);
        }
    }

    public void assertEquals(Object expected, Object actual, String message) {
        if (!Objects.equals(expected, actual)) {
            throw new TestAssertionError(message + " (expected: " + expected + ", actual: " + actual + ")");
        }
    }

    public void assertNotNull(Object object, String message) {
        if (object == null) {
            throw new TestAssertionError(message + " (was null)");
        }
    }

    public void assertNull(Object object, String message) {
        if (object != null) {
            throw new TestAssertionError(message + " (was not null: " + object + ")");
        }
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
            if (!condition.getAsBoolean()) {
                onTimeout.run();
            }
        });
        waitUntil(condition, continuation);
    }

    public void clearEffects() {
        player.getActiveEffects().forEach(effect -> player.removeEffect(effect.getEffect()));
    }

    public void clearInventory() {
        player.getInventory().clearContent();
    }

    public static final class TestAssertionError extends RuntimeException {
        public TestAssertionError(String message) {
            super(message);
        }
    }
}