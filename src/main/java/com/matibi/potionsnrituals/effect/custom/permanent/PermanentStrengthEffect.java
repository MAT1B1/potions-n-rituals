package com.matibi.potionsnrituals.effect.custom.permanent;

import com.matibi.potionsnrituals.config.ModConfig;
import com.matibi.potionsnrituals.util.AttributeUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

public class PermanentStrengthEffect extends MobEffect {
    public PermanentStrengthEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xffc933);
    }

    @Override
    public boolean isInstantaneous() {
        return true;
    }

    @Override
    public boolean applyEffectTick(@NonNull ServerLevel world, @NonNull LivingEntity entity, int amplifier) {
        applyEffect(entity, amplifier);
        return super.applyEffectTick(world, entity, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyInstantaneousEffect(@NonNull ServerLevel world, @Nullable Entity effectEntity, @Nullable Entity attacker, @NonNull LivingEntity target, int amplifier, double proximity) {
        applyEffect(target, amplifier);
        super.applyInstantaneousEffect(world, effectEntity, attacker, target, amplifier, proximity);
    }

    private static void applyEffect(LivingEntity target, int amplifier) {
        if (target instanceof Player player)
            AttributeUtils.changeStrengthBy(player, ModConfig.get().perm_strength);
    }
}