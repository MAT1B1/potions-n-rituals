package com.matibi.potionsnrituals.block;

import com.matibi.potionsnrituals.PotionsNRituals;
import com.matibi.potionsnrituals.block.custom.cauldron.BrewingCauldronBlockEntity;
import com.matibi.potionsnrituals.util.ModUtils;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import java.util.Set;

public class ModBlockEntities {

    public static final BlockEntityType<BrewingCauldronBlockEntity> BREWING_CAULDRON_BLOCK_ENTITY =
            Registry.register(
                    BuiltInRegistries.BLOCK_ENTITY_TYPE,
                    ModUtils.id("brewing_cauldron"),
                    new BlockEntityType<>(
                            BrewingCauldronBlockEntity::new,
                            Set.of(ModBlocks.BREWING_CAULDRON)
                    )
            );

    public static void register() {
        PotionsNRituals.LOGGER.info("Registering mod block entities for " + PotionsNRituals.MOD_ID);
    }
}