package com.matibi.potionsnrituals.potion;

import com.matibi.potionsnrituals.util.ModUtils;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.alchemy.PotionContents;

import java.util.ArrayList;
import java.util.List;

public class PotionIconHelper {

    public static Identifier getEffectSpriteId(ItemStack stack) {
        if (!(stack.getItem() instanceof PotionItem)) return null;

        PotionContents contents = stack.get(DataComponents.POTION_CONTENTS);
        if (contents == null) return null;

        if (contents.potion().isPresent()) {
            Identifier potionId = BuiltInRegistries.POTION.getKey(contents.potion().get().value());
            if (potionId != null && potionId.getPath().contains("turtle_master"))
                return ModUtils.id("mob_effect/turtle_master");
        }

        List<MobEffectInstance> effects = new ArrayList<>();
        contents.potion().ifPresent(p -> effects.addAll(p.value().getEffects()));
        effects.addAll(contents.customEffects());

        if (effects.size() != 1) return null;

        Identifier effectId = BuiltInRegistries.MOB_EFFECT.getKey(effects.getFirst().getEffect().value());
        if (effectId == null) return null;

        if (effectId.getNamespace().equals("minecraft"))
            return Identifier.withDefaultNamespace("mob_effect/" + effectId.getPath());

        return Identifier.fromNamespaceAndPath(effectId.getNamespace(), "mob_effect/" + effectId.getPath());
    }
}