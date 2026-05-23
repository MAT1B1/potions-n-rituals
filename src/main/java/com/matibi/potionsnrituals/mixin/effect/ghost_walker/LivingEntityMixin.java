package com.matibi.potionsnrituals.mixin.effect.ghost_walker;

import com.matibi.potionsnrituals.effect.ModEffects;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixin CLIENT uniquement.
 * Rend le joueur semi-transparent (comme une entité invisible) quand il a GhostWalk.
 * <p>
 * isInvisible() = true → le renderer utilise un alpha réduit (~26/255 ≈ 10%)
 * C'est exactement le comportement vanilla pour les joueurs en équipe qui voient
 * leurs coéquipiers invisibles.
 * <p>
 * À déclarer dans le fichier mixin client (ex. potionsnrituals.client.mixins.json)
 */
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    public LivingEntityMixin(EntityType<?> type, Level level) {
        super(type, level);
    }

    @Shadow
    public abstract boolean hasEffect(Holder<MobEffect> effect);

    @Inject(method = "updateInvisibilityStatus", at = @At("TAIL"))
    private void ghostWalk_forceInvisible(CallbackInfo ci) {
        if (this.hasEffect(ModEffects.GHOST_WALK))
            this.setInvisible(true);
    }

    @Inject(method = "getEffectiveGravity", at = @At("RETURN"), cancellable = true)
    private void ghostWalk_noGravity(CallbackInfoReturnable<Double> cir) {
        if (this.hasEffect(ModEffects.GHOST_WALK))
            cir.setReturnValue(0.0);
    }
}