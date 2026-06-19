package com.matibi.potionsnrituals.item.alchemicalStone;

import com.matibi.potionsnrituals.PotionsNRituals;
import com.matibi.potionsnrituals.effect.ModEffects;
import com.matibi.potionsnrituals.util.ModUtils;
import com.mojang.serialization.Lifecycle;
import net.minecraft.core.Holder;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.RegistrationInfo;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffect;

public class ModAlchemicalStone {

    public static final ResourceKey<Registry<AlchemicalStone>> ALCHEMICAL_STONE_REGISTRY_KEY =
            ResourceKey.createRegistryKey(ModUtils.id("alchemical_stone"));

    public static final MappedRegistry<AlchemicalStone> ALCHEMICAL_STONE_REGISTRY = new MappedRegistry<>(
            ALCHEMICAL_STONE_REGISTRY_KEY,
            Lifecycle.stable()
    );

    public static final Holder<AlchemicalStone>
            ACID = register("acid", ModEffects.ACID, 0),
            STRONG_ACID = register("strong_acid", ModEffects.ACID, 1),
            PETRIFICATION = register("petrification", ModEffects.PETRIFICATION, 0),
            STRONG_PETRIFICATION = register("strong_petrification", ModEffects.PETRIFICATION, 1),
            ALCHEMIST = register("alchemist", ModEffects.ALCHEMIST, 0),
            IGNITION = register("ignition", ModEffects.IGNITION, 0),
            GIANT = register("giant", ModEffects.GIANT, 0),
            RESURRECTION = register("resurrection", ModEffects.RESURRECTION, 0),
            FROST = register("frost", ModEffects.FROST, 0),
            STRONG_FROST = register("strong_frost", ModEffects.FROST, 1);

    public static Holder<AlchemicalStone> register(String name, Holder<MobEffect> effect, int amplifier) {
        Identifier id = ModUtils.id(name + "_alchemical_stone");
        ResourceKey<AlchemicalStone> key = ResourceKey.create(ALCHEMICAL_STONE_REGISTRY_KEY, id);
        AlchemicalStone stone = new AlchemicalStone(id, effect, amplifier);
        return ALCHEMICAL_STONE_REGISTRY.register(key, stone, RegistrationInfo.BUILT_IN);
    }

    public static void register() {
        PotionsNRituals.LOGGER.info("Registering alchemical stones for " + PotionsNRituals.MOD_ID);
        ALCHEMICAL_STONE_REGISTRY.freeze();
    }
}