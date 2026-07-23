package com.matibi.potionsnrituals.effect.helper;

import com.matibi.potionsnrituals.effect.ActiveEffect;
import com.matibi.potionsnrituals.network.ActiveEffectPayload;
import com.matibi.potionsnrituals.network.CooldownSyncPayload;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;

import java.util.*;

public class ActiveEffectHandler {
    private static final Map<UUID, Map<Identifier, Long>> COOLDOWNS = new HashMap<>();

    public static void register() {
        ServerPlayNetworking.registerGlobalReceiver(ActiveEffectPayload.TYPE, (payload, context) ->
                context.server().execute(() -> {
                    ServerPlayer player = context.player();
                    Identifier effectId = payload.effectType();
                    MobEffect effect = BuiltInRegistries.MOB_EFFECT.getValue(effectId);
                    if (!(effect instanceof ActiveEffect active)) return;
                    if (isOnCooldown(player, effectId)) return;

                    int amplifier = payload.amplifier();
                    int[] cooldowns = active.getCooldowns(amplifier);
                    int cooldownTicks = cooldowns[Math.min(amplifier, cooldowns.length - 1)];

                    boolean success = active.useOnKeybind(player.level(), player, 0, amplifier);

                    if (success && cooldownTicks > 0) {
                        setCooldown(player, effectId, cooldownTicks);
                    }
                })
        );

        ServerPlayConnectionEvents.DISCONNECT.register((handler, _) ->
                COOLDOWNS.remove(handler.getPlayer().getUUID())
        );
    }

    private static boolean isOnCooldown(ServerPlayer player, Identifier effectId) {
        Map<Identifier, Long> playerCooldowns = COOLDOWNS.get(player.getUUID());
        if (playerCooldowns == null) return false;
        Long cooldownEnd = playerCooldowns.get(effectId);
        return cooldownEnd != null && cooldownEnd > player.level().getGameTime();
    }

    private static void setCooldown(ServerPlayer player, Identifier effectId, int ticks) {
        COOLDOWNS.computeIfAbsent(player.getUUID(), _ -> new HashMap<>())
                .put(effectId, player.level().getGameTime() + ticks);

        ServerPlayNetworking.send(player, new CooldownSyncPayload(effectId, ticks));
    }
}
