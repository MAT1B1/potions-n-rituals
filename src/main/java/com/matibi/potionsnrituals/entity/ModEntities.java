package com.matibi.potionsnrituals.entity;

import com.matibi.potionsnrituals.PotionsNRituals;
import com.matibi.potionsnrituals.util.ModUtils;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntities {

    public static final Identifier RITUAL_CONTROLLER_ID = ModUtils.id("ritual_controller");
    public static final ResourceKey<EntityType<?>> RITUAL_CONTROLLER_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, RITUAL_CONTROLLER_ID);

    public static final EntityType<RitualControllerEntity> RITUAL_CONTROLLER = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            RITUAL_CONTROLLER_ID,
            EntityType.Builder.of(RitualControllerEntity::new, MobCategory.MISC)
                    .sized(0.1f, 0.1f)
                    .build(RITUAL_CONTROLLER_KEY)
    );

    public static void register() {
        PotionsNRituals.LOGGER.info("Registering mod entities for " + PotionsNRituals.MOD_ID);
    }
}