package com.matibi.potionsnrituals.item.alchemicalStone;

import com.matibi.potionsnrituals.PotionsNRituals;
import com.matibi.potionsnrituals.effect.TerrainEffect;
import com.matibi.potionsnrituals.item.ModItems;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.Holder;
import net.minecraft.ChatFormatting;

import java.util.*;

public record AlchemicalStone(Identifier id, Holder<MobEffect> effect, int amplifier) {

    public static ItemStack getItemStack(Holder<AlchemicalStone> entry) {
        AlchemicalStone stone = entry.value();
        return getItemStack(stone.effect(), stone.amplifier());
    }

    public static ItemStack getItemStack(PotionContents contents, int number) {
        if (contents == null || !contents.hasEffects())
            return ItemStack.EMPTY;

        for (MobEffectInstance effect : contents.getAllEffects()) {
            return getItemStack(effect.getEffect(), effect.getAmplifier(), number);
        }

        return ItemStack.EMPTY;
    }

    public static ItemStack getItemStack(PotionContents contents) {
        return getItemStack(contents, 1);
    }

    public static ItemStack getItemStack(Holder<MobEffect> effect, int amplifier) {
        return getItemStack(effect, amplifier, 1);
    }

    public static ItemStack getItemStack(Holder<MobEffect> effect, int amplifier, int number) {
        Identifier effectId = BuiltInRegistries.MOB_EFFECT.getKey(effect.value());

        if (effect.value() instanceof TerrainEffect &&
                effectId != null &&
                effectId.getNamespace().equals(PotionsNRituals.MOD_ID)) {

            ItemStack stack = new ItemStack(ModItems.ALCHEMICAL_STONE, number);

            stack.set(DataComponents.POTION_CONTENTS, new PotionContents(
                    Optional.empty(),
                    Optional.empty(),
                    List.of(new MobEffectInstance(effect, 1, amplifier)),
                    Optional.empty()
            ));

            String translationKey = "item.potions-n-rituals.alchemical_stone.effect." + effectId.getPath();
            stack.set(DataComponents.CUSTOM_NAME,
                    Component.translatable(translationKey)
                            .withStyle(s -> s.withItalic(false)));

            createTooltip(stack, effect, amplifier);

            return stack;
        }

        return ItemStack.EMPTY;
    }

    private static void createTooltip(ItemStack stack, Holder<MobEffect> effect, int amplifier) {
        SequencedSet<DataComponentType<?>> hidden = new LinkedHashSet<>();
        hidden.add(DataComponents.POTION_CONTENTS);
        stack.set(DataComponents.TOOLTIP_DISPLAY, new TooltipDisplay(false, hidden));

        ChatFormatting color = switch (effect.value().getCategory()) {
            case BENEFICIAL, NEUTRAL -> ChatFormatting.BLUE;
            case HARMFUL -> ChatFormatting.RED;
        };

        MutableComponent effectName = Component.translatable(effect.value().getDescriptionId());
        if (amplifier > 0) {
            effectName.append(Component.literal(" "))
                    .append(Component.translatable("potion.potency." + amplifier));
        }

        stack.set(DataComponents.LORE,
                new net.minecraft.world.item.component.ItemLore(
                        List.of(effectName.withStyle(s -> s.withColor(color).withItalic(false))),
                        List.of()
                ));
    }
}