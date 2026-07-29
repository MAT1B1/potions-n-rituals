package com.matibi.potionsnrituals.util;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

public class TickManager {

    private record ConditionalTask(BooleanSupplier condition, Consumer<MinecraftServer> action) {
        public boolean tick(MinecraftServer server) {
            if (condition.getAsBoolean()) {
                action.accept(server);
                return true;
            }
            return false;
        }
    }

    private static final List<ConditionalTask> CONDITIONAL_TASKS = new CopyOnWriteArrayList<>();


    public static void registerUntil(BooleanSupplier condition, Consumer<MinecraftServer> task) {
        CONDITIONAL_TASKS.add(new ConditionalTask(condition, task));
    }

    public static void runLater(int delayTicks, Consumer<MinecraftServer> task) {
        final int[] ticksRemaining = { delayTicks };

        registerUntil(() -> {
            ticksRemaining[0]--;
            return ticksRemaining[0] <= 0;
        }, task);
    }

    public static void initialize() {
        ServerTickEvents.END_SERVER_TICK.register(server ->
                CONDITIONAL_TASKS.removeIf(task -> !task.tick(server)));
    }
}