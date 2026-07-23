package com.matibi.potionsnrituals.network;

import com.matibi.potionsnrituals.util.ModUtils;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import org.jspecify.annotations.NonNull;

public record GauntletCyclePayload() implements CustomPacketPayload {
    public static final Type<GauntletCyclePayload> TYPE = new Type<>(ModUtils.id("gauntlet_cycle"));
    public static final StreamCodec<RegistryFriendlyByteBuf, GauntletCyclePayload> STREAM_CODEC = StreamCodec.unit(new GauntletCyclePayload());

    @Override
    public @NonNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}