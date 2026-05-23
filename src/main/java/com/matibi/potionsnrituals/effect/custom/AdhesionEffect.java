package com.matibi.potionsnrituals.effect.custom;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class AdhesionEffect extends MobEffect {

    public AdhesionEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x3B8B3B);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return false;
    }
}