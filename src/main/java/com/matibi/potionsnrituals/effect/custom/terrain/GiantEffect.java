package com.matibi.potionsnrituals.effect.custom.terrain;

import com.matibi.potionsnrituals.config.ModConfig;
import com.matibi.potionsnrituals.effect.TerrainEffect;
import com.matibi.potionsnrituals.util.ModUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class GiantEffect extends MobEffect implements TerrainEffect {
    public GiantEffect() {
        super(MobEffectCategory.NEUTRAL, 0x228B22);

        this.addAttributeModifier(
                Attributes.SCALE,
                ModUtils.id("giant_scale"),
                1.0D,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );
        this.addAttributeModifier(
                Attributes.JUMP_STRENGTH,
                ModUtils.id("giant_jump"),
                1.0D,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );
        this.addAttributeModifier(
                Attributes.ATTACK_DAMAGE,
                ModUtils.id("giant_dmg"),
                1.0D,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );
        this.addAttributeModifier(
                Attributes.ATTACK_SPEED,
                ModUtils.id("giant_atk_speed"),
                -0.5D,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );

        this.addAttributeModifier(
                Attributes.BLOCK_INTERACTION_RANGE,
                ModUtils.id("giant_block_range"),
                1.0D,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );
        this.addAttributeModifier(
                Attributes.ENTITY_INTERACTION_RANGE,
                ModUtils.id("giant_entity_range"),
                1.0D,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );

        this.addAttributeModifier(
                Attributes.STEP_HEIGHT,
                ModUtils.id("giant_step_height"),
                1.0D,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );

        this.addAttributeModifier(
                Attributes.MOVEMENT_SPEED,
                ModUtils.id("giant_mvt_speed"),
                1.0D,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );
    }

    @Override
    public void useOnBlock(ServerLevel world, BlockPos pos, int duration, int amplifier) {
        var state = world.getBlockState(pos);
        var block = state.getBlock();
        var rand = world.getRandom();

        var ageOpt = state.getProperties().stream()
                .filter(p -> p instanceof IntegerProperty && p.getName().equals("age"))
                .map(p -> (IntegerProperty) p)
                .findFirst();

        if (ageOpt.isPresent()) {
            var ageProp = ageOpt.get();
            int current = state.getValue(ageProp);
            int max = ageProp.getPossibleValues().stream().mapToInt(i -> i).max().orElse(current);
            if (current < max) {
                world.setBlock(pos, state.setValue(ageProp, max), 2);
                world.levelEvent(2005, pos, 0);
            }
            return;
        }

        if (block instanceof BonemealableBlock fertilizable) {
            int maxTries = ModConfig.get().growth_max_tries + amplifier * ModConfig.get().growth_max_tries_per_level;
            int tries = 0;
            while (tries < maxTries) {
                state = world.getBlockState(pos);
                if (!fertilizable.isValidBonemealTarget(world, pos, state)) break;
                if (!fertilizable.isBonemealSuccess(world, rand, pos, state)) break;
                fertilizable.performBonemeal(world, rand, pos, state);
                tries++;
            }
            if (tries > 0)
                world.levelEvent(2005, pos, 0);
        }
    }
}