package com.matibi.potionsnrituals.effect.custom;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class VampirismEffect extends MobEffect {
    public VampirismEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x8B0000);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int tickCount, int amplification) {
        return true;
    }
}
