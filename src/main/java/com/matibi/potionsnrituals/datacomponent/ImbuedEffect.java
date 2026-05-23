package com.matibi.potionsnrituals.datacomponent;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public record ImbuedEffect(Holder<MobEffect> effect, int hitsRemaining, int amplifier)
        implements TooltipProvider {

    public static final Codec<ImbuedEffect> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BuiltInRegistries.MOB_EFFECT.holderByNameCodec().fieldOf("effect").forGetter(ImbuedEffect::effect),
            Codec.INT.fieldOf("hits").forGetter(ImbuedEffect::hitsRemaining),
            Codec.INT.optionalFieldOf("amp", 0).forGetter(ImbuedEffect::amplifier)
    ).apply(instance, ImbuedEffect::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, ImbuedEffect> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.holderRegistry(net.minecraft.core.registries.Registries.MOB_EFFECT),
                    ImbuedEffect::effect,
                    ByteBufCodecs.VAR_INT,
                    ImbuedEffect::hitsRemaining,
                    ByteBufCodecs.VAR_INT,
                    ImbuedEffect::amplifier,
                    ImbuedEffect::new
            );

    @Override
    public void addToTooltip(Item.@NonNull TooltipContext context, @NonNull Consumer<Component> consumer, @NonNull TooltipFlag flag, @NonNull DataComponentGetter components) {

        ChatFormatting color = switch (this.effect().value().getCategory()) {
            case BENEFICIAL, NEUTRAL -> ChatFormatting.BLUE;
            case HARMFUL -> ChatFormatting.RED;
        };

        MutableComponent effectName = Component.translatable(this.effect().value().getDescriptionId());

        if (this.amplifier() > 0) {
            effectName.append(Component.literal(" "))
                    .append(Component.translatable("potion.potency." + this.amplifier()));
        }

        consumer.accept(
                Component.translatable("tooltip.potions-n-rituals.imbued_line",
                                effectName, this.hitsRemaining())
                        .withStyle(color)
        );
    }
}