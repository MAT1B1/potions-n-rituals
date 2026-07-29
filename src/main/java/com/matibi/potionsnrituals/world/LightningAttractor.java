package com.matibi.potionsnrituals.world;

import com.matibi.potionsnrituals.config.ModConfig;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LightningAttractor {

    private static final Map<UUID, Integer> CHARGING_PLAYERS = new HashMap<>();
    private static final Map<UUID, Integer> TARGET_THRESHOLDS = new HashMap<>();

    public static void checkPlayerArmor(Player player) {
        if (player.level().isClientSide() || !(player.level() instanceof ServerLevel serverLevel)) return;

        UUID uuid = player.getUUID();

        if (serverLevel.isThundering() && serverLevel.canSeeSky(player.blockPosition()) && hasMetalArmor(player)) {

            var config = ModConfig.get();
            int targetTime = TARGET_THRESHOLDS.computeIfAbsent(uuid, _ -> serverLevel.getRandom().nextIntBetweenInclusive(
                    config.lightning_attractor_min_threshold, config.lightning_attractor_max_threshold));

            int currentCharge = CHARGING_PLAYERS.getOrDefault(uuid, 0) + 1;
            CHARGING_PLAYERS.put(uuid, currentCharge);

            serverLevel.sendParticles(
                    ParticleTypes.ELECTRIC_SPARK,
                    player.getX(), player.getY() + 1.0, player.getZ(),
                    1, 0.4, 0.6, 0.4, 0.1
            );

            if (currentCharge >= targetTime) {
                CHARGING_PLAYERS.remove(uuid);
                TARGET_THRESHOLDS.remove(uuid);

                LightningBolt lightning = EntityTypes.LIGHTNING_BOLT.create(serverLevel, EntitySpawnReason.SPAWNER);
                if (lightning != null) {
                    lightning.setPos(player.getX(), player.getY(), player.getZ());
                    serverLevel.addFreshEntity(lightning);
                }
            }
        } else if (CHARGING_PLAYERS.containsKey(uuid)) {
            int currentCharge = CHARGING_PLAYERS.get(uuid) - 2;
            if (currentCharge <= 0) {
                CHARGING_PLAYERS.remove(uuid);
                TARGET_THRESHOLDS.remove(uuid);
            } else
                CHARGING_PLAYERS.put(uuid, currentCharge);
        }
    }

    private static boolean hasMetalArmor(Player player) {
        return isMetalPiece(player.getItemBySlot(EquipmentSlot.HEAD)) ||
                isMetalPiece(player.getItemBySlot(EquipmentSlot.CHEST)) ||
                isMetalPiece(player.getItemBySlot(EquipmentSlot.LEGS)) ||
                isMetalPiece(player.getItemBySlot(EquipmentSlot.FEET));
    }

    private static boolean isMetalPiece(ItemStack stack) {
        if (stack.is(ItemTags.HEAD_ARMOR)
                || stack.is(ItemTags.CHEST_ARMOR)
                || stack.is(ItemTags.LEG_ARMOR)
                || stack.is(ItemTags.FOOT_ARMOR)) {
            String id = net.minecraft.core.registries.BuiltInRegistries.ITEM.getKey(stack.getItem()).toString();
            return id.contains("iron") || id.contains("gold") || id.contains("copper");
        }
        return false;
    }

    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(server ->
                server.getPlayerList().getPlayers().forEach(LightningAttractor::checkPlayerArmor));
    }
}