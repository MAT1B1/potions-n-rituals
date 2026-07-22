package com.matibi.potionsnrituals.effect.custom.brainwashing;

import com.matibi.potionsnrituals.effect.ModEffects;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.world.entity.monster.Monster;

public class DisorientMobHandler {

    public static void register() {
        ServerTickEvents.END_LEVEL_TICK.register(world ->
                world.getAllEntities().forEach(entity -> {
            if (entity instanceof Monster mob) {
                if (!mob.hasEffect(ModEffects.BRAINWASHING)) return;

                world.getEntitiesOfClass(Monster.class,
                        mob.getBoundingBox().inflate(16),
                        m -> m != mob
                ).stream().findFirst().ifPresent(mob::setTarget);

            }
        }));
    }
}