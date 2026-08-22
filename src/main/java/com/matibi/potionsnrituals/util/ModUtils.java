package com.matibi.potionsnrituals.util;

import com.matibi.potionsnrituals.PotionsNRituals;
import net.fabricmc.fabric.api.recipe.v1.ingredient.DefaultCustomIngredients;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.fox.Fox;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;


public class ModUtils {
    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(PotionsNRituals.MOD_ID, path);
    }

    public static Ingredient potionIngredient(Holder<Potion> potionHolder) {
        DataComponentPatch componentsToMatch = DataComponentPatch.builder()
                .set(DataComponents.POTION_CONTENTS, new PotionContents(potionHolder))
                .build();
        Ingredient basePotion = Ingredient.of(Items.POTION);
        return DefaultCustomIngredients.components(basePotion, componentsToMatch);
    }

    // From minecraft code
    public static boolean randomTeleport(ServerLevel level, LivingEntity target, double diameter, int maxAttempt) {
        boolean teleported = false;

        for (int attempt = 0; attempt < maxAttempt; attempt++) {
            double xx = target.getX() + (target.getRandom().nextDouble() - 0.5) * diameter;
            double yy = Mth.clamp(
                    target.getY() + (target.getRandom().nextDouble() - 0.5) * diameter, level.getMinY(), level.getMinY() + ((ServerLevel)level).getLogicalHeight() - 1
            );
            double zz = target.getZ() + (target.getRandom().nextDouble() - 0.5) * diameter;
            if (target.isPassenger()) {
                target.stopRiding();
            }

            Vec3 oldPos = target.position();
            if (target.randomTeleport(xx, yy, zz, true)) {
                level.gameEvent(GameEvent.TELEPORT, oldPos, GameEvent.Context.of(target));
                SoundSource soundSource;
                SoundEvent soundEvent;
                if (target instanceof Fox) {
                    soundEvent = SoundEvents.FOX_TELEPORT;
                    soundSource = SoundSource.NEUTRAL;
                } else {
                    soundEvent = SoundEvents.CHORUS_FRUIT_TELEPORT;
                    soundSource = SoundSource.PLAYERS;
                }

                level.playSound(null, target.getX(), target.getY(), target.getZ(), soundEvent, soundSource);
                target.resetFallDistance();
                teleported = true;
                break;
            }
        }

        if (teleported)
            target.resetCurrentImpulseContext();

        return teleported;
    }
}
