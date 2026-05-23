package com.matibi.potionsnrituals.effect.custom;

import com.matibi.potionsnrituals.PotionsNRituals;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class DwarfEffect extends MobEffect {
    public DwarfEffect() {
        super(MobEffectCategory.NEUTRAL, 0xCC7722);

        this.addAttributeModifier(
                Attributes.SCALE,
                Identifier.fromNamespaceAndPath(PotionsNRituals.MOD_ID, "dwarf_scale"),
                -0.5D,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );
        this.addAttributeModifier(
                Attributes.JUMP_STRENGTH,
                Identifier.fromNamespaceAndPath(PotionsNRituals.MOD_ID, "dwarf_jump"),
                -0.35D,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );
        this.addAttributeModifier(
                Attributes.ATTACK_DAMAGE,
                Identifier.fromNamespaceAndPath(PotionsNRituals.MOD_ID, "dwarf_dmg"),
                -0.4D,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );
        this.addAttributeModifier(
                Attributes.ATTACK_SPEED,
                Identifier.fromNamespaceAndPath(PotionsNRituals.MOD_ID, "dwarf_atk_speed"),
                1.0D,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );

        this.addAttributeModifier(
                Attributes.BLOCK_INTERACTION_RANGE,
                Identifier.fromNamespaceAndPath(PotionsNRituals.MOD_ID, "dwarf_block_range"),
                -0.4D,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );
        this.addAttributeModifier(
                Attributes.ENTITY_INTERACTION_RANGE,
                Identifier.fromNamespaceAndPath(PotionsNRituals.MOD_ID, "dwarf_entity_range"),
                -0.4D,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );

        this.addAttributeModifier(
                Attributes.STEP_HEIGHT,
                Identifier.fromNamespaceAndPath(PotionsNRituals.MOD_ID, "dwarf_step_height"),
                -0.4D,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );

        this.addAttributeModifier(
                Attributes.MOVEMENT_SPEED,
                Identifier.fromNamespaceAndPath(PotionsNRituals.MOD_ID, "dwarf_mvt_speed"),
                -0.4D,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );
    }
}