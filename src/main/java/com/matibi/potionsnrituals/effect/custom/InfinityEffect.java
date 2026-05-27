package com.matibi.potionsnrituals.effect.custom;

import com.matibi.potionsnrituals.config.ModConfig;
import com.matibi.potionsnrituals.effect.ModEffects;
import com.matibi.potionsnrituals.util.AttributeUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class InfinityEffect extends MobEffect {
    public InfinityEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x9f00ff);
    }

    @Override
    public boolean isInstantenous() {
        return true;
    }

    @Override
    public boolean applyEffectTick(@NonNull ServerLevel world, @NonNull LivingEntity entity, int amplifier) {
        applyEffect(entity);
        return super.applyEffectTick(world, entity, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyInstantenousEffect(@NonNull ServerLevel world, @Nullable Entity effectEntity, @Nullable Entity attacker, @NonNull LivingEntity target, int amplifier, double proximity) {
        applyEffect(target);
        super.applyInstantenousEffect(world, effectEntity, attacker, target, amplifier, proximity);
    }

    private static void applyEffect(LivingEntity target) {
        List<MobEffectInstance> snapshot = List.copyOf(target.getActiveEffects());

        double cost = 0;

        for (MobEffectInstance effect : snapshot) {
            if (ModConfig.get().infinity_blacklist.contains(effect.getEffect())) continue;
            if (effect.getEffect().equals(ModEffects.INFINITY)) continue;

            if (!(target instanceof Player) || effect.getDuration() <= 0 ) continue;
            cost++;

            target.addEffect(new MobEffectInstance(
                    effect.getEffect(),
                    -1,
                    effect.getAmplifier(),
                    effect.isAmbient(),
                    effect.isVisible(),
                    effect.showIcon()
            ));
        }
        if (target instanceof Player player)
            AttributeUtils.changeHealthBy(player, - cost * ModConfig.get().max_health_lost_per_effect);
    }
}