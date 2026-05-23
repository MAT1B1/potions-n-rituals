package com.matibi.potionsnrituals.effect.custom.terrain;

import com.matibi.potionsnrituals.PotionsNRituals;
import com.matibi.potionsnrituals.config.ModConfig;
import com.matibi.potionsnrituals.effect.ModEffects;
import com.matibi.potionsnrituals.effect.TerrainApplicableEffect;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.game.ClientboundEntityEventPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class ResurrectionEffect extends MobEffect implements TerrainApplicableEffect {

    private static boolean applying = false;
    private static boolean first = false;
    private static final Map<UUID, Vec3> ANCHOR_POSITIONS = new HashMap<>();

    public ResurrectionEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xffcc66);
    }

    public static void registerDeathHandler() {
        ServerLivingEntityEvents.ALLOW_DEATH.register((entity, _, _) -> {
            if (!(entity instanceof Player player)) return true;
            if (player.hasEffect(ModEffects.BERSERK)) return true;
            if (!player.hasEffect(ModEffects.RESURRECTION)) return true;

            Vec3 anchor = ANCHOR_POSITIONS.remove(player.getUUID());
            ServerLevel world = (ServerLevel) player.level();

            MobEffectInstance currentEffect = player.getEffect(ModEffects.RESURRECTION);
            if (currentEffect == null) return true;
            int currentAmplifier = currentEffect.getAmplifier();

            totemLogic(world, player);

            if (currentAmplifier <= 0)
                ANCHOR_POSITIONS.remove(player.getUUID());
            else
                player.addEffect(new MobEffectInstance(
                        ModEffects.RESURRECTION,
                        -1,
                        currentAmplifier -1));

            if (anchor != null) {
                PotionsNRituals.LOGGER.info("Scheduling TP to {}, {}, {}", anchor.x, anchor.y, anchor.z);

                MinecraftServer server = world.getServer();

                server.execute(() -> server.execute(() -> {
                    if (player instanceof ServerPlayer serverPlayer && !serverPlayer.isDeadOrDying()) {
                        serverPlayer.teleportTo(world, anchor.x, anchor.y + 1, anchor.z,
                                Set.of(), player.getYRot(), player.getXRot(), false);

                        world.playSound(null, anchor.x, anchor.y, anchor.z,
                                SoundEvents.CHORUS_FRUIT_TELEPORT, player.getSoundSource(), 1.0f, 1.0f);

                        world.sendParticles(
                                ParticleTypes.PORTAL,
                                anchor.x, anchor.y + 1, anchor.z,
                                60, 1.0, 1.0, 1.0, 0.2
                        );
                        totemLogic(world, player);
                    }
                }));
            }

            return false;
        });
    }

    @Override
    public void onEffectAdded(@NonNull LivingEntity mob, int amplifier) {
        first = true;
        super.onEffectAdded(mob, amplifier);
    }

    @Override
    public void onEffectStarted(@NonNull LivingEntity mob, int amplifier) {
        if (applying || first) {
            first = false;
            return;
        }
        if (!(mob instanceof ServerPlayer player)) return;

        MobEffectInstance current = player.getEffect(ModEffects.RESURRECTION);
        if (current == null) return;

        int targetAmp = Math.min(current.getAmplifier() + 1, ModConfig.max_resurrection - 1);
        if (current.getAmplifier() == targetAmp) return;

        applying = true;
        try {
            player.addEffect(new MobEffectInstance(ModEffects.RESURRECTION, -1, targetAmp));
        } finally {
            applying = false;
        }
    }

    private static void totemLogic(ServerLevel world, Player player) {
        player.setHealth(1.0f);

        player.removeAllEffects();
        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 20 * 45, 1));
        player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 20 * 5, 1));
        player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 20 * 40, 0));

        world.levelEvent(1033, player.blockPosition(), 0);
        player.playSound(SoundEvents.TOTEM_USE, 1.0F, 1.0F);

        if (player instanceof ServerPlayer serverPlayer)
            serverPlayer.connection.send(new ClientboundEntityEventPacket(player, (byte) 35));
    }

    @Override
    public void useOnBlock(ServerLevel world, Player player, BlockPos block, int duration, int amplifier) {
        player.addEffect(new MobEffectInstance(ModEffects.RESURRECTION, -1, amplifier));
        ANCHOR_POSITIONS.put(player.getUUID(), Vec3.atCenterOf(block));

        world.playSound(null, block, SoundEvents.TRIDENT_RETURN,
                SoundSource.PLAYERS, 1.0f, 1.2f);

        Vec3 center = Vec3.atCenterOf(block);

        for (int i = 0; i < 80; i++) {
            double x = block.getX() + world.getRandom().nextDouble();
            double z = block.getZ() + world.getRandom().nextDouble();
            double y = center.y + 1 + world.getRandom().nextDouble();
            double dy = -0.05 - world.getRandom().nextDouble() * 0.05;

            world.sendParticles(
                    ParticleTypes.TOTEM_OF_UNDYING,
                    x, y, z,
                    1,
                    0, dy, 0,
                    0.02
            );
        }
    }
}