package com.matibi.potionsnrituals.datacomponent;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record PersonalBookmark(int pageIndex, int color) {

    public static final Codec<PersonalBookmark> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("page").forGetter(PersonalBookmark::pageIndex),
            Codec.INT.fieldOf("color").forGetter(PersonalBookmark::color)
    ).apply(instance, PersonalBookmark::new));

    public static final StreamCodec<ByteBuf, PersonalBookmark> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, PersonalBookmark::pageIndex,
            ByteBufCodecs.VAR_INT, PersonalBookmark::color,
            PersonalBookmark::new
    );
}