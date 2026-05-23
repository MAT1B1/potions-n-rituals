package com.matibi.potionsnrituals.potion;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.alchemy.PotionContents;

import java.util.ArrayList;
import java.util.List;

// PotionIconHelper.java
public class PotionIconHelper {

    /** null si potion a 0 ou 2+ effets */
    public static Identifier getEffectSpriteId(ItemStack stack) {
        if (!(stack.getItem() instanceof PotionItem)) return null;

        PotionContents contents = stack.get(DataComponents.POTION_CONTENTS);
        if (contents == null) return null;

        List<MobEffectInstance> effects = new ArrayList<>();
        contents.potion().ifPresent(p -> effects.addAll(p.value().getEffects()));
        effects.addAll(contents.customEffects());

        if (effects.size() != 1) return null;

        Identifier effectId = BuiltInRegistries.MOB_EFFECT.getKey(effects.getFirst().getEffect().value());
        if (effectId == null) return null;

        // Sprite ID pour blitSprite — dans l'atlas GUI
        // Vanilla : mob_effect/speed
        // Mod     : potions-n-rituals:mob_effect/acid
        if (effectId.getNamespace().equals("minecraft")) {
            return Identifier.withDefaultNamespace("mob_effect/" + effectId.getPath());
        }
        return Identifier.fromNamespaceAndPath(effectId.getNamespace(), "mob_effect/" + effectId.getPath());
    }
}