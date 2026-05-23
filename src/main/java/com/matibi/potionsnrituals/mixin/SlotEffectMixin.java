package com.matibi.potionsnrituals.mixin;

import com.matibi.potionsnrituals.keybind.KeyInputHandler;
import com.matibi.potionsnrituals.potion.PotionIconHelper;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiGraphicsExtractor.class)
public class SlotEffectMixin {

    @Inject(
            method = "item(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;III)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void onRenderItem(
            LivingEntity owner, Level level, ItemStack itemStack, int x, int y, int seed, CallbackInfo ci
    ) {
        if (!KeyInputHandler.isHeld()) return;

        Identifier spriteId = PotionIconHelper.getEffectSpriteId(itemStack);
        if (spriteId == null) return;

        ci.cancel();

        GuiGraphicsExtractor self = (GuiGraphicsExtractor)(Object)this;
        self.blitSprite(RenderPipelines.GUI_TEXTURED, spriteId, x, y, 16, 16);
    }
}