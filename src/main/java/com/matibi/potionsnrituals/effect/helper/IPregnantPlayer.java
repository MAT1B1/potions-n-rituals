package com.matibi.potionsnrituals.effect.helper;

import net.minecraft.world.entity.EntityType;
import java.util.Optional;

public interface IPregnantPlayer {
    Optional<EntityType<?>> pnr$getPregnancyMob();
    void pnr$setPregnancyMob(EntityType<?> type);
    void pnr$clearPregnancy();
}