package com.matibi.potionsnrituals.ritual;

import com.matibi.potionsnrituals.entity.RitualControllerEntity;
import com.matibi.potionsnrituals.ritual.datagen.definition.Ritual;
import net.minecraft.server.level.ServerLevel;

public interface RitualAction {
    void execute(ServerLevel level, RitualControllerEntity controller, Ritual ritual);
}