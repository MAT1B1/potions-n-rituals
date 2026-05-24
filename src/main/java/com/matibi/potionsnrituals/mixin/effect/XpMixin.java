package com.matibi.potionsnrituals.mixin.effect;

import com.matibi.potionsnrituals.effect.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Player.class)
public abstract class XpMixin {
    @ModifyVariable(method = "giveExperiencePoints", at = @At("HEAD"), argsOnly = true, name = "i")
    private int pnr$boostXpGain(int i) {
        if (i <= 0) return i;

        Player player = (Player) (Object) this;
        MobEffectInstance xpBoost = player.getEffect(ModEffects.XP_BOOST);
        MobEffectInstance xpReduction = player.getEffect(ModEffects.XP_REDUCTION);
        if (xpBoost == null && xpReduction == null) return i;

        if (xpReduction == null) {
            int multiplier = 2 + xpBoost.getAmplifier();
            long boosted = (long) i * multiplier;
            return boosted > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) boosted;
        } else {
            int multiplier = 2 + xpReduction.getAmplifier();
            long reduced = (long) i / multiplier;
            return reduced < 0 ? 0 : (int) reduced;
        }
    }
}
