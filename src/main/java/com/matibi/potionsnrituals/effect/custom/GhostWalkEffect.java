package com.matibi.potionsnrituals.effect.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.jspecify.annotations.NonNull;

public class GhostWalkEffect extends MobEffect {

    public GhostWalkEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xA9E5FF);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration % 20 == 0;
    }

    @Override
    public boolean applyEffectTick(@NonNull ServerLevel world, @NonNull LivingEntity entity, int amplifier) {
        entity.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 20 * 60, 0, false, false, false));
        return true;
    }

    @Override
    public void onEffectAdded(@NonNull LivingEntity entity, int amplifier) {
        // Désactive la gravité et les collisions via le flag noPhysics
        // (géré par MixinEntity qui lit cet effet)
    }

    @Override
    public void onMobRemoved(@NonNull ServerLevel level, LivingEntity mob, int amplifier, Entity.@NonNull RemovalReason reason) {
        mob.removeEffect(MobEffects.DARKNESS);
        super.onMobRemoved(level, mob, amplifier, reason);
    }

    @Override
    public void onEffectRemoved(@NonNull MobEffectInstance effectInstance, @NonNull LivingEntity entity) {
        // MixinEntity relit l'effet à chaque tick, rien à faire ici
        // La gravité et les collisions reprennent automatiquement
        entity.removeEffect(MobEffects.DARKNESS);
    }
}