package com.matibi.potionsnrituals.util;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class ActiveEffectUtils {

    public static LivingEntity getLookedAtEntity(ServerPlayer player, double range) {
        Vec3 start = player.getEyePosition();
        Vec3 lookVec = player.getViewVector(1.0F);
        Vec3 end = start.add(lookVec.scale(range));
        AABB area = player.getBoundingBox().expandTowards(lookVec.scale(range)).inflate(1.0);

        EntityHitResult hitResult = ProjectileUtil.getEntityHitResult(
                player,
                start,
                end,
                area,
                entity -> entity != player && entity instanceof LivingEntity le && le.isAlive(),
                range * range
        );

        return hitResult != null && hitResult.getEntity() instanceof LivingEntity le ? le : null;
    }

    public static LivingEntity getLookedAtEntity(LivingEntity entity, double range) {
        Vec3 start = entity.getEyePosition();
        Vec3 lookVec = entity.getLookAngle();
        Vec3 end = start.add(lookVec.scale(range));
        AABB area = entity.getBoundingBox().expandTowards(lookVec.scale(range)).inflate(1.0);

        EntityHitResult hitResult = ProjectileUtil.getEntityHitResult(
                entity,
                start,
                end,
                area,
                e -> e != entity && e instanceof LivingEntity le && le.isAlive(),
                range * range
        );

        return hitResult != null && hitResult.getEntity() instanceof LivingEntity le ? le : null;
    }

    public static BlockHitResult getLookedAtBlock(ServerPlayer player, double range) {
        Vec3 start = player.getEyePosition();
        Vec3 end = start.add(player.getViewVector(1.0F).scale(range));

        return player.level().clip(new ClipContext(
                start,
                end,
                ClipContext.Block.OUTLINE,
                ClipContext.Fluid.NONE,
                player
        ));
    }
}
