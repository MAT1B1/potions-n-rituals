package com.matibi.potionsnrituals.mixin.effect.ghost_walker;

import com.matibi.potionsnrituals.config.ModConfig;
import com.matibi.potionsnrituals.effect.ModEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerGhostWalkMixin {

    @Inject(method = "aiStep", at = @At("TAIL"))
    private void ghostWalk_verticalControl(CallbackInfo ci) {
        Player self = (Player)(Object)this;
        if (!self.hasEffect(ModEffects.GHOST_WALK)) return;

        Vec3 mov = self.getDeltaMovement();
        double vy;

        if (self.isJumping())
            vy = ModConfig.get().ghost_y_movement;
        else if (self.isShiftKeyDown())
            vy = -ModConfig.get().ghost_y_movement;
        else
            vy = mov.y * ModConfig.get().ghost_movement_absorber;

        self.setDeltaMovement(mov.x, vy, mov.z);
        self.fallDistance = 0.0f;
    }
}