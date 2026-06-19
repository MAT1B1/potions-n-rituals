package com.matibi.potionsnrituals.effect.custom;

import com.matibi.potionsnrituals.config.ModConfig;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class MagnetismEffect extends MobEffect {
    public MagnetismEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xCC4444);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyEffectTick(@NonNull ServerLevel world, @NonNull LivingEntity entity, int amplifier) {
        if (!(entity instanceof Player player)) return true;

        float range = ModConfig.get().magnetism_range + amplifier * ModConfig.get().magnetism_range_per_level;

        List<ItemEntity> items = world.getEntitiesOfClass(
                ItemEntity.class,
                player.getBoundingBox().inflate(range)
        );

        Vec3 playerPos = player.position().add(0, player.getEyeHeight() * 0.5, 0);

        for (ItemEntity item : items) {
            double dist = item.distanceToSqr(player);
            if (dist < 0.8) continue;
            Vec3 dir = playerPos.subtract(item.position()).normalize();

            item.addDeltaMovement(dir.scale(ModConfig.get().magnetism_pull_strength));
            item.hurtMarked = true;
        }

        return true;
    }
}
