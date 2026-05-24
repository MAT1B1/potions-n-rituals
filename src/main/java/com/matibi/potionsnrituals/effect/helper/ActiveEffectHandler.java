package com.matibi.potionsnrituals.effect.helper;

import com.matibi.potionsnrituals.effect.custom.LoveEffect;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class ActiveEffectHandler {
    public static void register() {
        ServerPlayNetworking.registerGlobalReceiver(ActiveEffectPayload.TYPE, (payload, context) ->
                context.server().execute(() -> {
                    switch (payload.effectType()) {
                        case "love" -> LoveEffect.applyLove(context.player(), payload.entityId());
                    }
                })
        );
    }
}