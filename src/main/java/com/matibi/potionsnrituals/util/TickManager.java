package com.matibi.potionsnrituals.util;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

public class TickManager {

    private interface TickTask {
        boolean tick(MinecraftServer server);
    }

    private record RepeatTask(BooleanSupplier condition, Consumer<MinecraftServer> action) implements TickTask {
        @Override
        public boolean tick(MinecraftServer server) {
            if (condition.getAsBoolean()) {
                action.accept(server);
                return true;
            }
            return false;
        }
    }

    private static class OneShotTask implements TickTask {
        private int ticksLeft;
        private final Consumer<MinecraftServer> action;

        OneShotTask(int delayTicks, Consumer<MinecraftServer> action) {
            this.ticksLeft = delayTicks;
            this.action = action;
        }

        @Override
        public boolean tick(MinecraftServer server) {
            ticksLeft--;
            if (ticksLeft <= 0) {
                action.accept(server);
                return true;
            }
            return false;
        }
    }

    private static final List<TickTask> TASKS = new ArrayList<>();

    public static void registerUntil(BooleanSupplier condition, Consumer<MinecraftServer> task) {
        TASKS.add(new RepeatTask(condition, task));
    }

    public static void runLater(int delayTicks, Consumer<MinecraftServer> task) {
        TASKS.add(new OneShotTask(delayTicks, task));
    }

    public static void initialize() {
        ServerTickEvents.END_SERVER_TICK.register(TickManager::tick);
    }

    public static void flush(MinecraftServer server) {
        tick(server);
    }

    private static void tick(MinecraftServer server) {
        List<TickTask> snapshot = List.copyOf(TASKS);
        TASKS.clear();
        for (TickTask task : snapshot) {
            if (!task.tick(server)) {
                TASKS.add(task);
            }
        }
    }
}
