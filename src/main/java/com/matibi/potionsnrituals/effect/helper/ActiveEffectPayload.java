package com.matibi.potionsnrituals.effect.helper;

import com.matibi.potionsnrituals.PotionsNRituals;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public record ActiveEffectPayload(int entityId, String effectType) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<ActiveEffectPayload> TYPE =
            new CustomPacketPayload.Type<>(
                    Identifier.fromNamespaceAndPath(PotionsNRituals.MOD_ID, "active_effect")
            );

    public static final StreamCodec<RegistryFriendlyByteBuf, ActiveEffectPayload> CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.VAR_INT, ActiveEffectPayload::entityId,
                    ByteBufCodecs.STRING_UTF8, ActiveEffectPayload::effectType,
                    ActiveEffectPayload::new
            );

    @Override
    public @NonNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}