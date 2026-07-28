package com.matibi.potionsnrituals.mixin;

import com.matibi.potionsnrituals.item.custom.talisman.InvisibilityRingItem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.entity.state.ArmedEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemInHandLayer.class)
public class InvisibilityRingMixin {

    @Inject(method = "submit(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/client/renderer/entity/state/ArmedEntityRenderState;FF)V", at = @At("HEAD"), cancellable = true)
    private void pnr$hideItemsForInvisibleRing(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, ArmedEntityRenderState state, float yRot, float xRot, CallbackInfo ci) {

        boolean hasRingInRight = state.rightHandItemStack.getItem() instanceof InvisibilityRingItem;
        boolean hasRingInLeft = state.leftHandItemStack.getItem() instanceof InvisibilityRingItem;

        if (hasRingInRight || hasRingInLeft)
            ci.cancel();
    }
}