package com.matibi.potionsnrituals.network;

import com.matibi.potionsnrituals.datacomponent.ModDataComponents;
import com.matibi.potionsnrituals.datacomponent.PersonalBookmark;
import com.matibi.potionsnrituals.effect.helper.ActiveEffectPayload;
import com.matibi.potionsnrituals.effect.helper.CooldownSyncPayload;
import com.matibi.potionsnrituals.item.CustomBook;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public final class ModNetworking {
    private ModNetworking() {
    }

    public static void register() {
        PayloadTypeRegistry.serverboundPlay().register(ActiveEffectPayload.TYPE, ActiveEffectPayload.CODEC);
        PayloadTypeRegistry.clientboundPlay().register(OreSensePayload.TYPE, OreSensePayload.CODEC);
        PayloadTypeRegistry.clientboundPlay().register(CooldownSyncPayload.TYPE, CooldownSyncPayload.CODEC);
        PayloadTypeRegistry.serverboundPlay().register(UpdateBookmarksPayload.TYPE, UpdateBookmarksPayload.CODEC);

        ServerPlayNetworking.registerGlobalReceiver(UpdateBookmarksPayload.TYPE, (payload, context) -> {
            ServerPlayer player = context.player();
            ItemStack stack = player.getItemInHand(payload.hand());
            List<PersonalBookmark> bookmarks = payload.bookmarks();
            if (stack.getItem() instanceof CustomBook && PersonalBookmark.isValidList(bookmarks))
                stack.set(ModDataComponents.PERSONAL_BOOKMARKS, List.copyOf(bookmarks));
        });
    }
}