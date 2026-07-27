package com.matibi.potionsnrituals.datacomponent;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;

import java.util.Optional;

public record CapturedMob(Optional<Identifier> entityType) {

    public static final CapturedMob EMPTY = new CapturedMob(Optional.empty());

    public boolean hasMob() {
        return this.entityType.isPresent();
    }

    public static final Codec<CapturedMob> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Identifier.CODEC.optionalFieldOf("entity_type").forGetter(CapturedMob::entityType)
    ).apply(instance, CapturedMob::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, CapturedMob> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.optional(Identifier.STREAM_CODEC), CapturedMob::entityType,
            CapturedMob::new
    );
}
