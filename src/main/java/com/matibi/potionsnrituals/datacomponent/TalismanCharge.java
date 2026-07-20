package com.matibi.potionsnrituals.datacomponent;

import com.matibi.potionsnrituals.item.custom.talisman.TalismanItem;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.ChatFormatting;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record TalismanCharge(int charge, boolean isCharged) {

    public static final TalismanCharge EMPTY = new TalismanCharge(0, false);

    public static final Codec<TalismanCharge> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("charge").forGetter(TalismanCharge::charge),
            Codec.BOOL.fieldOf("is_chargeed").forGetter(TalismanCharge::isCharged)
    ).apply(instance, TalismanCharge::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, TalismanCharge> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, TalismanCharge::charge,
            ByteBufCodecs.BOOL, TalismanCharge::isCharged,
            TalismanCharge::new
    );

    public static void registerTooltip() {
        ItemTooltipCallback.EVENT.register((itemStack, _, _, list) -> {
            if (!(itemStack.getItem() instanceof TalismanItem)) return;
            TalismanCharge charge = itemStack.getComponents().getOrDefault(ModDataComponents.TALISMAN_CHARGE, TalismanCharge.EMPTY);
            if (charge.isCharged())
                list.add(
                        Component.translatable("tooltip.potions-n-rituals.talisman_charged_line")
                                .withStyle(ChatFormatting.GOLD)
                );
            else {
                MutableComponent progression = Component.literal(String.valueOf(TalismanItem.CHARGE_NEEDED - charge.charge()))
                        .withStyle(ChatFormatting.GOLD);

                list.add(
                        Component.translatable("tooltip.potions-n-rituals.talisman_charge_line", progression)
                                .withStyle(ChatFormatting.GRAY)
                );
            }
        });
    }

}