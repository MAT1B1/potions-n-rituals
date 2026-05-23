package com.matibi.potionsnrituals.mixin.effect;

import com.matibi.potionsnrituals.config.ModConfig;
import com.matibi.potionsnrituals.effect.ModEffects;
import net.minecraft.network.protocol.game.ServerboundSwingPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.entity.projectile.hurtingprojectile.LargeFireball;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerGamePacketListenerImpl.class)
public abstract class PlayerIgnitionMixin {

    @Unique
    private int ignitionFireballCooldown = 0;

    @Inject(method = "handleAnimate", at = @At("HEAD"))
    private void ignition_onSwing(
            ServerboundSwingPacket packet,
            CallbackInfo ci
    ) {
        // récupère le player depuis le handler
        ServerGamePacketListenerImpl self = (ServerGamePacketListenerImpl)(Object)this;
        ServerPlayer player = self.player;

        if (!player.hasEffect(ModEffects.IGNITION)) return;
        if (ignitionFireballCooldown > 0) return;

        Vec3 look = player.getLookAngle();
        Vec3 pos  = player.getEyePosition().add(look.scale(1.5));

        LargeFireball fireball = new LargeFireball(
                player.level(),
                player,
                look.multiply(ModConfig.fireball_speed, ModConfig.fireball_speed, ModConfig.fireball_speed),
                1
        );
        fireball.setPos(pos.x, pos.y, pos.z);
        player.level().addFreshEntity(fireball);

        ignitionFireballCooldown = ModConfig.fireball_cooldown;
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void ignition_tickCooldown(CallbackInfo ci) {
        if (ignitionFireballCooldown > 0)
            ignitionFireballCooldown--;
    }
}