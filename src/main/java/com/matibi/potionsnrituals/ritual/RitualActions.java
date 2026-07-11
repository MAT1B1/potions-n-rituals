package com.matibi.potionsnrituals.ritual;

import com.matibi.potionsnrituals.config.ModConfig;
import com.matibi.potionsnrituals.effect.ModEffects;
import com.matibi.potionsnrituals.entity.RitualControllerEntity;
import com.matibi.potionsnrituals.ritual.datagen.Ritual;
import com.matibi.potionsnrituals.util.ModUtils;
import com.matibi.potionsnrituals.world.data.ModAttachments;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.clock.ClockTimeMarkers;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RitualActions {

    public interface RitualAction {
        void execute(ServerLevel level, RitualControllerEntity controller, Ritual ritual, int currentTick);
    }

    private static final Map<Identifier, RitualAction> REGISTRY = new HashMap<>();

    public static void register(String id, RitualAction action) {
        REGISTRY.put(ModUtils.id(id), action);
    }

    public static RitualAction get(Identifier id) { return REGISTRY.get(id); }

    static {
        register("random_effect", (level, entity, _, _) -> {
            Player player = level.getNearestPlayer(entity, 10.0);
            if (player != null) {
                var allEffects = BuiltInRegistries.MOB_EFFECT.stream().toList();
                MobEffect chosenHolder = allEffects
                        .get(level.getRandom().nextInt(allEffects.size()));
                player.addEffect(new MobEffectInstance(Holder.direct(chosenHolder), ModConfig.get().dur_short, 0));
                level.playSound(null, player.blockPosition(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS, 0.5f, 1.0f);
            }
        });

        register("summon_thunderstorm", (level, entity, _, _) -> {
            if (level instanceof ServerLevel serverLevel) {
                serverLevel.getServer().setWeatherParameters(0, 6000, true, true);
                Player player = level.getNearestPlayer(entity, 10.0);
                if (player != null)
                    level.playSound(null, player.blockPosition(), SoundEvents.LIGHTNING_BOLT_THUNDER, SoundSource.WEATHER, 1.0f, 1.0f);
            }
        });

        register("summon_dawn", (level, entity, _, _) -> {
            if (level instanceof ServerLevel serverLevel) {
                serverLevel.dimensionType().defaultClock().ifPresent(clock ->
                    serverLevel.clockManager().moveToTimeMarker(clock, ClockTimeMarkers.WAKE_UP_FROM_SLEEP)
                );
                Player player = level.getNearestPlayer(entity, 10.0);
                if (player != null)
                    level.playSound(null, player.blockPosition(), SoundEvents.ILLUSIONER_PREPARE_BLINDNESS, SoundSource.PLAYERS, 1.0f, 0.5f);
            }
        });
        Items.BED.forEach(item ->
            register("summon_night_" + item.getDescriptionId(), (level, entity, _, _) -> {
                if (level instanceof ServerLevel serverLevel) {
                    serverLevel.dimensionType().defaultClock().ifPresent(clock ->
                            serverLevel.clockManager().moveToTimeMarker(clock, ClockTimeMarkers.NIGHT)
                    );
                    Player player = level.getNearestPlayer(entity, 10.0);
                    if (player != null)
                        level.playSound(null, player.blockPosition(), SoundEvents.AMBIENT_CAVE.value(), SoundSource.PLAYERS, 1.0f, 0.5f);
                }
            })
        );
        register("nether_gate_final", (level, entity, _, _) -> {
            if (level instanceof ServerLevel serverLevel) {
                ServerLevel netherLevel = serverLevel.getServer().getLevel(Level.NETHER);
                Player nearestPlayer = level.getNearestPlayer(entity, 10.0);
                if (netherLevel != null && nearestPlayer instanceof ServerPlayer serverPlayer) {
                    double currentX = serverPlayer.getX();
                    double currentY = serverPlayer.getY();
                    double currentZ = serverPlayer.getZ();
                    double destX = currentX / 8.0;
                    double destZ = currentZ / 8.0;
                    double destY = Mth.clamp(currentY, 32.0, 100.0);
                    serverLevel.playSound(null, serverPlayer.blockPosition(), SoundEvents.ILLUSIONER_PREPARE_BLINDNESS, SoundSource.PLAYERS, 1.0f, 0.5f);
                    serverPlayer.addEffect(new MobEffectInstance(ModEffects.GHOST_WALK, ModConfig.get().nether_ghost_duration, 0));
                    serverPlayer.teleportTo(
                            netherLevel,
                            destX, destY, destZ,
                            Set.of(),
                            serverPlayer.getYRot(),
                            serverPlayer.getXRot(),
                            true
                    );
                    netherLevel.playSound(null, BlockPos.containing(destX, destY, destZ), SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1.0f, 1.0f);
                }
            }
        });
        register("nether_gate_action", (level, entity, _, ritualTick) -> {
            if (ritualTick == 1) {
                BlockPos centerPos = entity.blockPosition().below();
                if (!level.getBlockState(centerPos).is(Blocks.BEDROCK)) {
                    level.setBlockAndUpdate(centerPos, Blocks.NETHERRACK.defaultBlockState());

                    if (level instanceof ServerLevel serverLevel)
                        serverLevel.sendParticles(
                                ParticleTypes.LARGE_SMOKE,
                                centerPos.getX() + 0.5,
                                centerPos.getY() + 1.0,
                                centerPos.getZ() + 0.5,
                                15, 0.2, 0.2, 0.2, 0.05
                        );
                }
            }

            Player player = level.getNearestPlayer(entity, 10.0);

            if (player != null && !player.isCreative()) {

                double dx = entity.getX() - player.getX();
                double dy = entity.getY() - player.getY();
                double dz = entity.getZ() - player.getZ();
                double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);

                if (distance > 1.0) {
                    double pullStrength = 0.05;

                    player.setDeltaMovement(player.getDeltaMovement().add(
                            (dx / distance) * pullStrength,
                            (dy / distance) * pullStrength,
                            (dz / distance) * pullStrength
                    ));

                    player.hurtMarked = true;
                }

                if (ritualTick % 11 == 0) {
                    player.hurtServer(level, level.damageSources().magic(), 1.0F);
                    level.playSound(null, player.blockPosition(), SoundEvents.PHANTOM_BITE, SoundSource.PLAYERS, 0.5f, 0.8f);
                }
            }
        });
        register("seal_nether",  (level, _, _, _) -> {
            if (level instanceof ServerLevel serverLevel)
                serverLevel.setAttached(ModAttachments.NETHER_SEALED, true);
        });
    }
}
