package com.matibi.potionsnrituals.effect.custom.terrain;

import com.matibi.potionsnrituals.effect.TerrainEffect;
import com.matibi.potionsnrituals.util.ModUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.block.Blocks;

public class PetrificationEffect extends MobEffect implements TerrainEffect {
    public PetrificationEffect() {
        super(MobEffectCategory.HARMFUL, 0xA8A8A8);

        this.addAttributeModifier(
                Attributes.MOVEMENT_SPEED,
                ModUtils.id("petrification_movement"),
                -1.0D,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );
        this.addAttributeModifier(
                Attributes.KNOCKBACK_RESISTANCE,
                ModUtils.id("petrification_knockback"),
                1.0D,
                AttributeModifier.Operation.ADD_VALUE
        );
        this.addAttributeModifier(
                Attributes.ATTACK_DAMAGE,
                ModUtils.id("petrification_attack_damage"),
                -1.0D,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );
        this.addAttributeModifier(
                Attributes.JUMP_STRENGTH,
                ModUtils.id("petrification_jump"),
                -1.0D,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );
        this.addAttributeModifier(
                Attributes.BLOCK_BREAK_SPEED,
                ModUtils.id("petrification_break_block"),
                -1.0D,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );
        this.addAttributeModifier(
                Attributes.BLOCK_INTERACTION_RANGE,
                ModUtils.id("petrification_interaction"),
                -1.0D,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );
        this.addAttributeModifier(
                Attributes.ENTITY_INTERACTION_RANGE,
                ModUtils.id("petrification_entity_interaction"),
                -1.0D,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );
        this.addAttributeModifier(
                Attributes.GRAVITY,
                ModUtils.id("petrification_gravity"),
                1.0D,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );
        this.addAttributeModifier(
                Attributes.MOVEMENT_EFFICIENCY,
                ModUtils.id("petrification_movement_efficiency"),
                -1.0D,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );
    }

    @Override
    public void useOnBlock(ServerLevel world, BlockPos block, int duration, int amplifier) {
        if (amplifier == 0 && !world.getBlockState(block).is(Blocks.COBBLESTONE)) {
            if (world.getBlockState(block).is(Blocks.SAND) || world.getBlockState(block).is(Blocks.RED_SAND))
                world.setBlock(block, Blocks.SANDSTONE.defaultBlockState(), 3);
            else
                world.setBlock(block, Blocks.COBBLESTONE.defaultBlockState(), 3);
        } else if (amplifier > 0)
            world.setBlock(block, Blocks.BEDROCK.defaultBlockState(), 3);
    }

    @Override
    public boolean isBlockNonApplicable(ServerLevel world, BlockPos block) {
        return world.getBlockState(block).is(Blocks.BEDROCK);
    }
}
