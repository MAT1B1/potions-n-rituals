package com.matibi.potionsnrituals.effect.custom;

import com.matibi.potionsnrituals.PotionsNRituals;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class LongLegEffect extends MobEffect {
    public LongLegEffect() {
        super(MobEffectCategory.NEUTRAL, 0x89D964);

        this.addAttributeModifier(
                Attributes.STEP_HEIGHT,
                Identifier.fromNamespaceAndPath(PotionsNRituals.MOD_ID, "long_legs_step"),
                0.4D,
                AttributeModifier.Operation.ADD_VALUE
        );

        this.addAttributeModifier(
                Attributes.MOVEMENT_SPEED,
                Identifier.fromNamespaceAndPath(PotionsNRituals.MOD_ID, "long_legs_speed"),
                0.1D,
                AttributeModifier.Operation.ADD_MULTIPLIED_BASE
        );
    }
}