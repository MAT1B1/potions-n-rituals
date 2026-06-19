package com.matibi.potionsnrituals.effect.custom;

import com.matibi.potionsnrituals.effect.ModEffects;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.item.ItemStack;

import org.jspecify.annotations.NonNull;

public class ZombieContagionEffect extends MobEffect {
    public ZombieContagionEffect() {
        super(MobEffectCategory.HARMFUL, 0x3A6E3A);
    }

    @Override
    public void onEffectRemoved(@NonNull MobEffectInstance effectInstance, LivingEntity entity) {
        if (entity.level().isClientSide()) return;
        if (!(entity instanceof ServerPlayer player)) return;
        if (!player.isAlive()) return;
        if (player.hasEffect(ModEffects.ZOMBIE_CONTAGION)) return;

        ServerLevel world = player.level();

        Zombie zombie = EntityTypes.ZOMBIE.create(world, EntitySpawnReason.EVENT);
        if (zombie == null) return;

        for (EquipmentSlot slot : EquipmentSlot.values()) {
            ItemStack playerItem = player.getItemBySlot(slot).copy();
            if (!playerItem.isEmpty()) {
                zombie.setItemSlot(slot, playerItem);
            }
        }

        zombie.setPersistenceRequired();
        zombie.absSnapTo(player.getX(), player.getY(), player.getZ(), player.getYRot(), player.getXRot());
        world.addFreshEntity(zombie);

        world.sendParticles(ParticleTypes.SMOKE,
                player.getX(), player.getY() + 1.0, player.getZ(),
                20, 0.5, 0.5, 0.5, 0.05);

        world.playSound(null, player.blockPosition(),
                SoundEvents.ZOMBIE_AMBIENT,
                SoundSource.HOSTILE, 1.0f, 1.0f);

        player.hurtServer(world, world.damageSources().magic(), Float.MAX_VALUE);
    }
}
