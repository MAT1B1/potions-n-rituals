package com.matibi.potionsnrituals.effect.custom;

import com.matibi.potionsnrituals.config.ModConfig;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

public class DeathEffect extends MobEffect {
    public DeathEffect() {
        super(MobEffectCategory.HARMFUL, 0x000000);
    }

    @Override
    public boolean isInstantenous() {
        return true;
    }

    @Override
    public boolean applyEffectTick(@NonNull ServerLevel world, @NonNull LivingEntity entity, int amplifier) {
        applyEffect(world, null, null, entity, amplifier);
        return super.applyEffectTick(world, entity, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyInstantenousEffect(@NonNull ServerLevel world, @Nullable Entity effectEntity, @Nullable Entity attacker, @NonNull LivingEntity target, int amplifier, double proximity) {
        applyEffect(world, effectEntity, attacker, target, amplifier);
        super.applyInstantenousEffect(world, effectEntity, attacker, target, amplifier, proximity);
    }

    private static void applyEffect(ServerLevel world, @Nullable Entity effectEntity, @Nullable Entity attacker, LivingEntity target, int amplifier) {
        if (!target.isAlive()) return;

        if (isBoss(target))
            handleBoss(world, attacker, target);
        else if (isUndead(target))
            handleUndead(world, attacker, target);
        else
            killTarget(world, attacker, target);

    }

    private static void handleBoss(ServerLevel world, @Nullable Entity attacker, LivingEntity target) {
        float maxHealth = target.getMaxHealth();
        float currentHealth = target.getHealth();

        if (currentHealth > maxHealth * ModConfig.boss_death_damage)
            target.setHealth(maxHealth * ModConfig.boss_death_damage);

        if (attacker instanceof LivingEntity livingAttacker)
            livingAttacker.hurtServer(world, world.damageSources().magic(), livingAttacker.getMaxHealth() * ModConfig.boss_death_backlash);
    }

    private static void handleUndead(ServerLevel world, @Nullable Entity attacker, LivingEntity target) {
        target.setHealth(target.getMaxHealth() * ModConfig.undead_death_heal);

        if (attacker instanceof LivingEntity livingAttacker)
            livingAttacker.hurtServer(world, world.damageSources().magic(), livingAttacker.getMaxHealth() * ModConfig.undead_death_backlash);
    }

    private static void killTarget(ServerLevel world, @Nullable Entity attacker, LivingEntity target) {
        if (attacker instanceof LivingEntity livingAttacker)
            target.hurtServer(world, world.damageSources().mobAttack(livingAttacker), target.getMaxHealth());
        else
            target.hurtServer(world, world.damageSources().magic(), target.getMaxHealth());

    }

    private static boolean isBoss(LivingEntity e) {
        var type = e.getType();
        return type == EntityType.WITHER
                || type == EntityType.ENDER_DRAGON
                || type == EntityType.WARDEN
                || type == EntityType.ELDER_GUARDIAN;
    }

    private static boolean isUndead(LivingEntity e) {
        return e.tags().toList().contains(EntityTypeTags.UNDEAD);
    }
}