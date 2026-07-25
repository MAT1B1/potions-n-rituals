package com.matibi.potionsnrituals.network;

import com.matibi.potionsnrituals.util.ModUtils;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import org.jspecify.annotations.NonNull;

public record ExecuteCommandPayload(String command) implements CustomPacketPayload {
    public static final Type<ExecuteCommandPayload> TYPE = new Type<>(ModUtils.id("execute_command"));

    public static final StreamCodec<RegistryFriendlyByteBuf, ExecuteCommandPayload> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.STRING_UTF8, ExecuteCommandPayload::command,
                    ExecuteCommandPayload::new
            );

    @Override
    public @NonNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}