package com.matibi.potionsnrituals.mixin.effect;

import com.matibi.potionsnrituals.config.ModConfig;
import com.matibi.potionsnrituals.effect.ModEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class RealityCheckMixin {

    @Inject(method = "aiStep", at = @At("TAIL"))
    private void realityCheck_slowdown(CallbackInfo ci) {
        Player self = (Player)(Object)this;
        if (!self.hasEffect(ModEffects.REALITY_CHECK)) return;

        var items = self.getInventory().getNonEquipmentItems();

        int totalItems = 0;
        int maxItems = 0;
        for (ItemStack stack : items) {
            totalItems += stack.getCount();
            maxItems += stack.getMaxStackSize();
        }

        if (maxItems == 0) return;

        double fullness = (double) totalItems / (double) maxItems;
        double slowFactor = 1.0 - fullness * ModConfig.get().reality_check_max_slowdown;
        if (slowFactor < 0.0) slowFactor = 0.0;
        if (slowFactor > 1.0) slowFactor = 1.0;

        Vec3 mov = self.getDeltaMovement();
        self.setDeltaMovement(mov.x * slowFactor, mov.y, mov.z * slowFactor);
    }
}
