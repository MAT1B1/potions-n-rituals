package com.matibi.potionsnrituals.effect.custom;

import com.matibi.potionsnrituals.config.ModConfig;
import com.matibi.potionsnrituals.effect.ModEffects;
import com.matibi.potionsnrituals.effect.helper.IPregnantPlayer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public class LoveEffect extends MobEffect {
    public LoveEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xFF69B4);
    }

    public static void applyLove(ServerPlayer player, int entityId) {
        if (!player.hasEffect(ModEffects.LOVE)) return;

        Entity entity = player.level().getEntity(entityId);
        if (!(entity instanceof AgeableMob target)) return;
        if (!target.isAlive() || target.isBaby()) return;
        if (player.hasEffect(ModEffects.PREGNANT)) return;
        if (player.distanceToSqr(target) > ModConfig.get().max_distance_pregnancy * ModConfig.get().max_distance_pregnancy) return;
        if (!player.hasLineOfSight(target)) return;
        if (!isLookingAt(player, target)) return;

        ((IPregnantPlayer) player).pnr$setPregnancyMob(target.getType());

        player.addEffect(new MobEffectInstance(ModEffects.PREGNANT, ModConfig.get().pregnancy_duration, 0));
        player.removeEffect(ModEffects.LOVE);
    }

    private static boolean isLookingAt(ServerPlayer player, Entity target) {
        Vec3 look = player.getViewVector(1.0F).normalize();
        Vec3 toTarget = target.getBoundingBox().getCenter().subtract(player.getEyePosition()).normalize();
        return look.dot(toTarget) > 0.96;
    }
}
