package com.matibi.potionsnrituals.mixin.brewingNpotion;

import com.matibi.potionsnrituals.PotionsNRituals;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PotionBrewing.Builder.class)
public class PotionBrewingBuilderMixin {

    @Inject(method = "addMix", at = @At("HEAD"), cancellable = true)
    private void onRegisterMix(Holder<Potion> from, Item ingredient, Holder<Potion> _to, CallbackInfo ci) {
        if (_to == Potions.REGENERATION && ingredient == Items.GHAST_TEAR)
            ci.cancel();
    }
}