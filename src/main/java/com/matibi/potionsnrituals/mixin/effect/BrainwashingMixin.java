package com.matibi.potionsnrituals.mixin.effect;

import com.matibi.potionsnrituals.effect.ModEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.ClientInput;
import net.minecraft.client.player.KeyboardInput;
import net.minecraft.world.entity.player.Input;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec2;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyboardInput.class)
public abstract class BrainwashingMixin extends ClientInput {
    @Inject(method = "tick", at = @At("TAIL"))
    private void invertMovementOnBrainwashing(CallbackInfo ci) {
        Player player = Minecraft.getInstance().player;

        if (player != null && player.hasEffect(ModEffects.BRAINWASHING)) {

            this.keyPresses = new Input(
                    this.keyPresses.backward(), // Avancer devient Reculer
                    this.keyPresses.forward(),  // Reculer devient Avancer
                    this.keyPresses.right(),    // Gauche devient Droite
                    this.keyPresses.left(),     // Droite devient Gauche
                    this.keyPresses.sprint(),   // Jump devient Sprint
                    this.keyPresses.jump(),     // Shift devient Jump
                    this.keyPresses.shift()     // Sprint devient Shift
            );

            this.moveVector = new Vec2(-this.moveVector.x, -this.moveVector.y);
        }
    }
}