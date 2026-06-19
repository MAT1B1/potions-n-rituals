package com.matibi.potionsnrituals.effect.custom;

import com.matibi.potionsnrituals.util.ModUtils;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class NoInteractionEffect extends MobEffect {
    public NoInteractionEffect() {
        super(MobEffectCategory.HARMFUL, 0x9f00ff);

        this.addAttributeModifier(
                Attributes.BLOCK_INTERACTION_RANGE,
                ModUtils.id("no_interaction_block_range"),
                -1.0D,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );
        this.addAttributeModifier(
                Attributes.ENTITY_INTERACTION_RANGE,
                ModUtils.id("no_interaction_entity_range"),
                -1.0D,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );
        this.addAttributeModifier(
                Attributes.ATTACK_DAMAGE,
                ModUtils.id("no_interaction_attack_damage"),
                -1.0D,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );
        this.addAttributeModifier(
                Attributes.ATTACK_KNOCKBACK,
                ModUtils.id("no_interaction_knockback"),
                -1.0D,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );
    }
}