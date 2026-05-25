package com.matibi.potionsnrituals.mixin.effect;

import com.matibi.potionsnrituals.effect.ModEffects;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public class AsthmaMixin {

    @Inject(method = "tick", at = @At("TAIL"))
    private void handleAsthmaEffect(CallbackInfo ci) {
        Player player = (Player) (Object) this;
        if (player.level().isClientSide()) return;

        if (player.hasEffect(ModEffects.ASTHMA)) {
            int currentAir = player.getAirSupply();
            player.setAirSupply(currentAir - 3);
            if (player.isSprinting() || !player.onGround()) {
                player.setAirSupply(currentAir - 7);
                if (currentAir <= 5 && player.tickCount % 20 == 0) {
                        ServerLevel level = (ServerLevel) player.level();
                        player.hurtServer(level, level.damageSources().inWall(), 1.0f);
                }
            }
        }
    }
}