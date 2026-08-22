package com.matibi.potionsnrituals.effect.custom;

import com.matibi.potionsnrituals.PotionsNRituals;
import com.matibi.potionsnrituals.config.ModConfig;
import com.matibi.potionsnrituals.util.ModUtils;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.core.BlockPos;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.Set;

public class TeleportationEffect extends MobEffect {
    public TeleportationEffect() {
        super(MobEffectCategory.NEUTRAL, 0x8833cc);
    }

    @Override
    public boolean isInstantaneous() {
        return true;
    }

    @Override
    public boolean applyEffectTick(@NonNull ServerLevel world, @NonNull LivingEntity entity, int amplifier) {
        applyEffect(world, entity, amplifier);
        return super.applyEffectTick(world, entity, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) { return true; }

    @Override
    public void applyInstantaneousEffect(@NonNull ServerLevel world, @Nullable Entity effectEntity, @Nullable Entity attacker, @NonNull LivingEntity target, int amplifier, double proximity) {
        applyEffect(world, target, amplifier);
        super.applyInstantaneousEffect(world, effectEntity, attacker, target, amplifier, proximity);
    }

    private static void applyEffect(ServerLevel world, LivingEntity target, int amplifier) {
        if (target instanceof ServerPlayer sp && amplifier > 0) {
            var respawn = sp.getRespawnConfig();

            if (respawn != null) {
                MinecraftServer serv = world.getServer();
                var targetWorld = serv.getLevel(respawn.respawnData().dimension());
                if (targetWorld != null) {
                    BlockPos p = respawn.respawnData().pos();
                    double x = p.getX() + 0.5;
                    double y = p.getY();
                    double z = p.getZ() + 0.5;
                    sp.teleportTo(targetWorld, x, y + 1, z, Set.of(), sp.getYRot(), sp.getXRot(), false);
                    return;
                }
            }
        }

        if (ModUtils.randomTeleport(world, target, 8.0 + world.getRandom().nextDouble() * 8.0, ModConfig.get().tp_max_try))
            PotionsNRituals.LOGGER.info("Teleporting {}", target.getDisplayName().getString());
        else
            PotionsNRituals.LOGGER.warn("{} did not teleport", target.getDisplayName().getString());
    }
}
