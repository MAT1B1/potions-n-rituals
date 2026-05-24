package com.matibi.potionsnrituals.effect.custom;

import com.matibi.potionsnrituals.effect.helper.IPregnantPlayer;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.jspecify.annotations.NonNull;

import java.util.Optional;

public class PregnantEffect extends MobEffect {
    public PregnantEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xF7A8C8);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return false;
    }

    @Override
    public void onEffectAdded(@NonNull LivingEntity mob, int amplifier) {
        super.onEffectAdded(mob, amplifier);

        if (!mob.level().isClientSide()) {
            ServerLevel world = (ServerLevel) mob.level();

            world.sendParticles(
                    ParticleTypes.HEART,
                    mob.getX(), mob.getY() + 1.0, mob.getZ(),
                    10,
                    0.5, 0.5, 0.5,
                    0.1
            );
        }
    }

    @Override
    public void onEffectRemoved(@NonNull MobEffectInstance effectInstance, LivingEntity entity) {
        if (entity.level() instanceof ServerLevel level && entity instanceof Player player) {
            Optional<EntityType<?>> mobTypeOpt = ((IPregnantPlayer) player).pnr$getPregnancyMob();

            if (mobTypeOpt.isPresent()) {
                Entity child = mobTypeOpt.get().create(level, EntitySpawnReason.BREEDING);

                if (child != null) {
                    if (child instanceof AgeableMob baby)
                        baby.setAge(-24000);

                    child.absSnapTo(player.getX(), player.getY(), player.getZ(), player.getYRot(), player.getXRot());
                    level.addFreshEntity(child);

                    level.sendParticles(ParticleTypes.HEART,
                            player.getX(), player.getY() + player.getBbHeight(), player.getZ(),
                            10, 0.5, 0.5, 0.5, 0.1);
                }
            }

            ((IPregnantPlayer) player).pnr$clearPregnancy();
        }

        super.onEffectRemoved(effectInstance, entity);
    }
}