package com.matibi.potionsnrituals.network;

import com.matibi.potionsnrituals.PotionsNRituals;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public record OreSensePayload(
        float inViewIntensity,  int inViewColor,
        float offViewIntensity, int offViewColor, float offViewAngle
) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<OreSensePayload> TYPE =
            new CustomPacketPayload.Type<>(
                    Identifier.fromNamespaceAndPath(PotionsNRituals.MOD_ID, "ore_sense")
            );

    public static final StreamCodec<RegistryFriendlyByteBuf, OreSensePayload> CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.FLOAT, OreSensePayload::inViewIntensity,
                    ByteBufCodecs.INT,   OreSensePayload::inViewColor,
                    ByteBufCodecs.FLOAT, OreSensePayload::offViewIntensity,
                    ByteBufCodecs.INT,   OreSensePayload::offViewColor,
                    ByteBufCodecs.FLOAT, OreSensePayload::offViewAngle,
                    OreSensePayload::new
            );

    @Override
    public @NonNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}