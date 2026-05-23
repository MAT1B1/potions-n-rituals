package com.matibi.potionsnrituals.effect.custom;

import com.matibi.potionsnrituals.config.ModConfig;
import net.minecraft.core.Direction;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
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
    public boolean isInstantenous() {
        return true;
    }

    @Override
    public boolean applyEffectTick(@NonNull ServerLevel world, @NonNull LivingEntity entity, int amplifier) {
        applyEffect(world, null, null, entity, amplifier, 1.0D);
        return super.applyEffectTick(world, entity, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) { return true; }

    @Override
    public void applyInstantenousEffect(@NonNull ServerLevel world, @Nullable Entity effectEntity, @Nullable Entity attacker, @NonNull LivingEntity target, int amplifier, double proximity) {
        applyEffect(world, effectEntity, attacker, target, amplifier, proximity);
        super.applyInstantenousEffect(world, effectEntity, attacker, target, amplifier, proximity);
    }

    private static void applyEffect(ServerLevel world, @Nullable Entity effectEntity, @Nullable Entity attacker, LivingEntity target, int amplifier, double proximity) {
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
                    world.broadcastEntityEvent(sp, (byte) 46);
                    return;
                }
            }
        }

        for (int i = 0; i < ModConfig.tp_max_try; i++) {
            double radius = 8.0 + world.getRandom().nextDouble() * 8.0;
            double angle = world.getRandom().nextDouble() * 2 * Math.PI;
            double dx = Math.cos(angle) * radius;
            double dz = Math.sin(angle) * radius;

            double newX = target.getX() + dx;
            double newZ = target.getZ() + dz;

            int startY = Mth.floor(target.getY());

            BlockPos.MutableBlockPos targetPos = new BlockPos.MutableBlockPos(newX, startY, newZ);

            while (targetPos.getY() > world.getMinSectionY() && targetPos.getY() < startY + 8) {
                if (world.getBlockState(targetPos).isAir())
                    targetPos.move(Direction.DOWN);
                else
                    break;
            }

            if (!world.getBlockState(targetPos).isAir() &&
                    world.getBlockState(targetPos.above()).isAir() &&
                    world.getBlockState(targetPos.above(2)).isAir()) {

                target.teleportTo(newX, targetPos.getY() + 1, newZ);
                world.broadcastEntityEvent(target, (byte) 46);
                return;
            }
        }
    }
}