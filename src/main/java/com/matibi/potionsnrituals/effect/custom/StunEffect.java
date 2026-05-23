package com.matibi.potionsnrituals.effect.custom;

import com.matibi.potionsnrituals.PotionsNRituals;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class StunEffect extends MobEffect {
    public StunEffect() {
        super(MobEffectCategory.HARMFUL, 0xede13b);

        this.addAttributeModifier(
                Attributes.MOVEMENT_SPEED,
                Identifier.fromNamespaceAndPath(PotionsNRituals.MOD_ID, "stun_movement"),
                -0.95D,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );
        this.addAttributeModifier(
                Attributes.ATTACK_DAMAGE,
                Identifier.fromNamespaceAndPath(PotionsNRituals.MOD_ID, "stun_knockback"),
                -0.95D,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );
        this.addAttributeModifier(
                Attributes.JUMP_STRENGTH,
                Identifier.fromNamespaceAndPath(PotionsNRituals.MOD_ID, "stun_jump"),
                -0.95D,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );
        this.addAttributeModifier(
                Attributes.BLOCK_BREAK_SPEED,
                Identifier.fromNamespaceAndPath(PotionsNRituals.MOD_ID, "stun_break_block"),
                -0.95D,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );
        this.addAttributeModifier(
                Attributes.BLOCK_INTERACTION_RANGE,
                Identifier.fromNamespaceAndPath(PotionsNRituals.MOD_ID, "stun_interaction"),
                -0.95D,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );
        this.addAttributeModifier(
                Attributes.ENTITY_INTERACTION_RANGE,
                Identifier.fromNamespaceAndPath(PotionsNRituals.MOD_ID, "stun_entity_interaction"),
                -0.95D,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );
        this.addAttributeModifier(
                Attributes.MOVEMENT_EFFICIENCY,
                Identifier.fromNamespaceAndPath(PotionsNRituals.MOD_ID, "stun_movement_efficiency"),
                -0.95D,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );
    }
}