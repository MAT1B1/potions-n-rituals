package com.matibi.potionsnrituals.network;

import com.matibi.potionsnrituals.datacomponent.PersonalBookmark;
import com.matibi.potionsnrituals.util.ModUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.InteractionHand;
import org.jspecify.annotations.NonNull;

import java.util.List;

public record UpdateBookmarksPayload(InteractionHand hand, List<PersonalBookmark> bookmarks) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<UpdateBookmarksPayload> TYPE =
            new CustomPacketPayload.Type<>(ModUtils.id("update_bookmarks"));

    private static final StreamCodec<ByteBuf, InteractionHand> HAND_CODEC = ByteBufCodecs.BOOL.map(
            isOffHand -> isOffHand ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND,
            hand -> hand == InteractionHand.OFF_HAND
    );

    public static final StreamCodec<ByteBuf, UpdateBookmarksPayload> CODEC = StreamCodec.composite(
            HAND_CODEC, UpdateBookmarksPayload::hand,
            PersonalBookmark.STREAM_CODEC.apply(ByteBufCodecs.list()), UpdateBookmarksPayload::bookmarks,
            UpdateBookmarksPayload::new
    );

    @Override
    public @NonNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}