package com.matibi.potionsnrituals.mixin.effect;

import com.matibi.potionsnrituals.config.ModConfig;
import com.matibi.potionsnrituals.effect.ModEffects;
import net.minecraft.network.protocol.game.ServerboundSwingPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.InteractionHand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerGamePacketListenerImpl.class)
public class ClumsinessMixin {
    @Inject(method = "handleAnimate", at = @At("HEAD"))
    private void clumsiness_swing(
            ServerboundSwingPacket packet,
            CallbackInfo ci
    ) {
        ServerGamePacketListenerImpl self = (ServerGamePacketListenerImpl)(Object)this;
        ServerPlayer player = self.player;

        if (!player.hasEffect(ModEffects.CLUMSINESS)) return;

        if (player.getRandom().nextInt(100) < ModConfig.get().drop_percentage) {
            player.drop(player.getItemInHand(InteractionHand.MAIN_HAND), true);
        }
    }
}
