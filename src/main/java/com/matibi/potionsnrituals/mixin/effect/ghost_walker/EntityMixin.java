package com.matibi.potionsnrituals.mixin.effect.ghost_walker;

import com.matibi.potionsnrituals.effect.ModEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @Shadow
    public boolean noPhysics;

    /**
     * Chaque tick, active/désactive noPhysics selon l'effet GhostWalk.
     * noPhysics = true → l'entité ignore toutes les collisions avec les blocs.
     */
    @Inject(method = "tick", at = @At("HEAD"))
    private void ghostWalk_updateNoPhysics(CallbackInfo ci) {
        Entity self = (Entity)(Object)this;

        if (self instanceof LivingEntity living) {
            if (living instanceof Player player && player.isSpectator())
                return;

            boolean hasGhostWalk = living.hasEffect(ModEffects.GHOST_WALK);
            if (hasGhostWalk && !noPhysics)
                noPhysics = true;
            else if (!hasGhostWalk && noPhysics)
                noPhysics = false;
        }
    }
}