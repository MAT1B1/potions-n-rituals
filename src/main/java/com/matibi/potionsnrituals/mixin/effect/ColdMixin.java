package com.matibi.potionsnrituals.mixin.effect;

import com.matibi.potionsnrituals.config.ModConfig;
import com.matibi.potionsnrituals.effect.ModEffects;
import com.matibi.potionsnrituals.effect.custom.ColdEffect;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public class ColdMixin {

    @Unique private int pnr$coughTimer = 0;
    @Unique private int pnr$coughInterval = -1; // -1 = pas encore init

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void pnr$write(ValueOutput output, CallbackInfo ci) {
        output.putInt("cold_timer", pnr$coughTimer);
        output.putInt("cold_interval", pnr$coughInterval);
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void pnr$read(ValueInput input, CallbackInfo ci) {
        pnr$coughTimer = input.getInt("cold_timer").orElse(0);
        pnr$coughInterval = input.getInt("cold_interval").orElse(-1);
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void pnr$tick(CallbackInfo ci) {
        Player self = (Player)(Object)this;
        if (!self.hasEffect(ModEffects.COLD)) return;
        if (self.level().isClientSide()) return;

        if (pnr$coughInterval < 0) {
            int seconds = ModConfig.min_cough_time + self.level().getRandom().nextInt(ModConfig.max_cough_time -  ModConfig.min_cough_time + 1);
            pnr$coughInterval = 20 * seconds;
        }

        pnr$coughTimer++;

        if (pnr$coughTimer >= pnr$coughInterval) {
            ColdEffect.doCough(self);
            pnr$coughTimer = 0;
            int seconds = ModConfig.min_cough_time + self.level().getRandom().nextInt(ModConfig.max_cough_time -  ModConfig.min_cough_time + 1);
            pnr$coughInterval = 20 * seconds;
        }
    }
}