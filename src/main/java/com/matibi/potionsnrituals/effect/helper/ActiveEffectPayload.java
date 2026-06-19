package com.matibi.potionsnrituals.effect.helper;

import com.matibi.potionsnrituals.util.ModUtils;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public record ActiveEffectPayload(int entityId, Identifier effectType, int amplifier) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<ActiveEffectPayload> TYPE =
            new CustomPacketPayload.Type<>(
                    ModUtils.id("active_effect")
            );

    public static final StreamCodec<RegistryFriendlyByteBuf, ActiveEffectPayload> CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.VAR_INT, ActiveEffectPayload::entityId,
                    Identifier.STREAM_CODEC, ActiveEffectPayload::effectType,
                    ByteBufCodecs.VAR_INT, ActiveEffectPayload::amplifier,
                    ActiveEffectPayload::new
            );

    @Override
    public @NonNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
