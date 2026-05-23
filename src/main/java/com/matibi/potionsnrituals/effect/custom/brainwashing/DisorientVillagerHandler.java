package com.matibi.potionsnrituals.effect.custom.brainwashing;

import com.matibi.potionsnrituals.effect.ModEffects;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.world.entity.npc.villager.Villager;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.AABB;

public class DisorientVillagerHandler {

    public static void register() {
        ServerTickEvents.END_LEVEL_TICK.register(world ->
                world.getEntitiesOfClass(Villager.class, AABB.of(BoundingBox.infinite())).forEach(villager -> {
            if (!villager.hasEffect(ModEffects.BRAINWASHING)) return;

            villager.getOffers().forEach(offer -> {
                if (offer.getSpecialPriceDiff() == 0)
                    offer.addToSpecialPriceDiff(-(offer.getCostA().getCount() / 2));
            });
        }));
    }
}
