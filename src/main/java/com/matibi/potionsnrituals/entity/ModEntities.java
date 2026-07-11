package com.matibi.potionsnrituals.entity;

import com.matibi.potionsnrituals.PotionsNRituals;
import com.matibi.potionsnrituals.util.ModUtils;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntities {

    public static final EntityType<RitualControllerEntity> RITUAL_CONTROLLER = reg("ritual_controller",
            EntityType.Builder.of(RitualControllerEntity::new, MobCategory.MISC).sized(0.1f, 0.1f));

    public static final EntityType<PortalBuilderEntity> PORTAL_BUILDER = reg("portal_builder",
            EntityType.Builder.of(PortalBuilderEntity::new, MobCategory.MISC).sized(0.1f, 0.1f));

    private static <T extends Entity> EntityType<T> reg(String name, EntityType.Builder<T> builder) {
        Identifier id = ModUtils.id(name);
        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, id);

        return Registry.register(
                BuiltInRegistries.ENTITY_TYPE,
                id,
                builder.build(key)
        );
    }
    public static void register() {
        PotionsNRituals.LOGGER.info("Registering mod entities for " + PotionsNRituals.MOD_ID);
    }
}