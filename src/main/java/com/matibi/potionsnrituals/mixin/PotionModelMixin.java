package com.matibi.potionsnrituals.mixin;

import com.matibi.potionsnrituals.potion.ModPotions;
import com.matibi.potionsnrituals.util.ModUtils;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ClientItem;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ItemOwner;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.Level;
import net.minecraft.core.component.DataComponents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemModelResolver.class)
public abstract class PotionModelMixin {

    @Shadow
    private ClientItem.Properties getItemProperties(Identifier modelId) {
        return null;
    }

    @Shadow
    private ItemModel getItemModel(Identifier modelId) {
        return null;
    }

    @Inject(method = "appendItemLayers", at = @At("HEAD"), cancellable = true)
    private void injectCustomPotionModel(ItemStackRenderState output, ItemStack item, ItemDisplayContext displayContext, Level level, ItemOwner owner, int seed, CallbackInfo ci) {

        if (!item.is(Items.POTION)) return;
        PotionContents contents = item.get(DataComponents.POTION_CONTENTS);

        if (contents == null || contents.potion().isEmpty()) return;
        Holder<Potion> potion = contents.potion().get();

        if (potion != ModPotions.PERM_HEALTH
                && potion != ModPotions.PERM_SPEED
                && potion != ModPotions.PERM_STRENGTH) return;

        Identifier customModelId = ModUtils.id("potion");

        var prop = this.getItemProperties(customModelId);
        if (prop == null) return;
        output.setOversizedInGui(prop.oversizedInGui());

        ClientLevel clientLevel = level instanceof ClientLevel ? (ClientLevel)level : null;

        var model = this.getItemModel(customModelId);
        if (model == null) return;
        model.update(
                output, item, (ItemModelResolver)(Object)this,
                displayContext, clientLevel, owner, seed
        );
        ci.cancel();
    }
}