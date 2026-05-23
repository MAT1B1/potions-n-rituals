package com.matibi.potionsnrituals.effect.custom;

import com.matibi.potionsnrituals.config.ModConfig;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.jspecify.annotations.NonNull;

public class SaturationEffect extends MobEffect {
    public SaturationEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x5e370a);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyEffectTick(@NonNull ServerLevel world, @NonNull LivingEntity entity, int amplifier) {
        if (!(entity instanceof Player player)) return false;

        if (player.tickCount % 10 == 0) {
            var food = player.getFoodData();
            float gain = ModConfig.base_saturation + ModConfig.saturation_per_level * amplifier;
            food.setSaturation(Math.min(food.getFoodLevel(), food.getSaturationLevel() + gain));
        }
        return super.applyEffectTick(world, entity, amplifier);
    }
}