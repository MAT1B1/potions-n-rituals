package com.matibi.potionsnrituals.effect.helper;

import com.matibi.potionsnrituals.util.ModUtils;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public record CooldownSyncPayload(Identifier effectId, int cooldownTicks) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<CooldownSyncPayload> TYPE =
            new CustomPacketPayload.Type<>(
                    ModUtils.id("cooldown_sync")
            );

    public static final StreamCodec<RegistryFriendlyByteBuf, CooldownSyncPayload> CODEC =
            StreamCodec.composite(
                    Identifier.STREAM_CODEC, CooldownSyncPayload::effectId,
                    ByteBufCodecs.VAR_INT, CooldownSyncPayload::cooldownTicks,
                    CooldownSyncPayload::new
            );

    @Override
    public @NonNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
