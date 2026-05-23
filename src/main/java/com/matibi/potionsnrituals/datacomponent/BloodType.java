package com.matibi.potionsnrituals.datacomponent;

import com.mojang.serialization.Codec;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public record BloodType(String bloodType) implements TooltipProvider {

    public static final BloodType UNKNOWN = new BloodType("unknown");
    public static final BloodType HUMAN   = new BloodType("human");
    public static final BloodType MONSTER = new BloodType("monster");

    public static final Codec<BloodType> CODEC =
            Codec.STRING.xmap(BloodType::new, BloodType::bloodType);

    public static final StreamCodec<RegistryFriendlyByteBuf, BloodType> STREAM_CODEC =
            ByteBufCodecs.STRING_UTF8.<RegistryFriendlyByteBuf>cast()
                    .map(BloodType::new, BloodType::bloodType);

    @Override
    public void addToTooltip(Item.@NonNull TooltipContext context, Consumer<Component> consumer, @NonNull TooltipFlag flag, @NonNull DataComponentGetter components) {
        consumer.accept(
                Component.translatable("potions-n-rituals.blood_type." + bloodType)
                        .withStyle(ChatFormatting.DARK_RED)
        );
    }
}