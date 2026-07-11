package com.matibi.potionsnrituals.datacomponent;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

public record ReturnLocation(ResourceKey<Level> dimension, double x, double y, double z) {

    public static final Codec<ReturnLocation> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ResourceKey.codec(Registries.DIMENSION).fieldOf("dimension").forGetter(ReturnLocation::dimension),
            Codec.DOUBLE.fieldOf("x").forGetter(ReturnLocation::x),
            Codec.DOUBLE.fieldOf("y").forGetter(ReturnLocation::y),
            Codec.DOUBLE.fieldOf("z").forGetter(ReturnLocation::z)
    ).apply(instance, ReturnLocation::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, ReturnLocation> STREAM_CODEC = StreamCodec.composite(
            ResourceKey.streamCodec(Registries.DIMENSION), ReturnLocation::dimension,
            ByteBufCodecs.DOUBLE, ReturnLocation::x,
            ByteBufCodecs.DOUBLE, ReturnLocation::y,
            ByteBufCodecs.DOUBLE, ReturnLocation::z,
            ReturnLocation::new
    );
}