package com.matibi.potionsnrituals.effect.custom.brainwashing;

import com.matibi.potionsnrituals.effect.ModEffects;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.AABB;

public class DisorientMobHandler {

    public static void register() {
        ServerTickEvents.END_LEVEL_TICK.register(world ->
                world.getEntitiesOfClass(Monster.class, AABB.of(BoundingBox.infinite())).forEach(mob -> {
            if (!mob.hasEffect(ModEffects.BRAINWASHING)) return;

            // Si cible actuelle = joueur ou rien → cherche mob hostile proche
            LivingEntity current = mob.getTarget();
            if (current == null || current instanceof Player) {
                Monster nearest = world.getEntitiesOfClass(Monster.class,
                        mob.getBoundingBox().inflate(16),
                        m -> m != mob
                ).stream().findFirst().orElse(null);

                mob.setTarget(nearest);
            }
        }));
    }
}
