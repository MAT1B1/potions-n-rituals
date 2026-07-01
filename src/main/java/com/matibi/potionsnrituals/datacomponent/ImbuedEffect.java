package com.matibi.potionsnrituals.datacomponent;

import com.matibi.potionsnrituals.item.custom.TalismanItem;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.effect.MobEffect;

public record ImbuedEffect(Holder<MobEffect> effect, int hitsRemaining, int amplifier) {

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

    public static void registerTooltip() {
        ItemTooltipCallback.EVENT.register((itemStack, _, _, list) -> {
            if (!itemStack.is(ItemTags.MELEE_WEAPON_ENCHANTABLE)
                    && !itemStack.is(ItemTags.TRIDENT_ENCHANTABLE)) return;
            ImbuedEffect effect = itemStack.getComponents().get(ModDataComponents.IMBUED_EFFECT);
            if (effect == null) return;

            ChatFormatting color = switch (effect.effect().value().getCategory()) {
                case BENEFICIAL, NEUTRAL -> ChatFormatting.BLUE;
                case HARMFUL -> ChatFormatting.RED;
            };

            MutableComponent effectName = Component.translatable(effect.effect().value().getDescriptionId());

            if (effect.amplifier() > 0) {
                effectName.append(Component.literal(" "))
                        .append(Component.translatable("potion.potency." + effect.amplifier()));
            }

            list.add(
                    Component.translatable("tooltip.potions-n-rituals.imbued_line",
                                    effectName, effect.hitsRemaining())
                            .withStyle(color)
            );

        });
    }
}