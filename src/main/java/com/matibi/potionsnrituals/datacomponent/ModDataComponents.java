package com.matibi.potionsnrituals.datacomponent;

import com.matibi.potionsnrituals.PotionsNRituals;
import com.matibi.potionsnrituals.util.ModUtils;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import java.util.function.UnaryOperator;

public final class ModDataComponents {
    private ModDataComponents() {
    }

    public static final DataComponentType<ImbuedEffect> IMBUED_EFFECT = register("imbued_effect",
            builder -> builder
                    .persistent(ImbuedEffect.CODEC)
                    .networkSynchronized(ImbuedEffect.STREAM_CODEC));

    public static final DataComponentType<BloodType> BLOOD_TYPE = register("blood_type",
            builder -> builder
                    .persistent(BloodType.CODEC)
                    .networkSynchronized(BloodType.STREAM_CODEC));

    private static <T> DataComponentType<T> register(String id, UnaryOperator<DataComponentType.Builder<T>> op) {
        return Registry.register(
                BuiltInRegistries.DATA_COMPONENT_TYPE,
                ModUtils.id(id),
                op.apply(DataComponentType.builder()).build()
        );
    }

    public static void register() {
        PotionsNRituals.LOGGER.info("Registering data components for " + PotionsNRituals.MOD_ID);
    }
}