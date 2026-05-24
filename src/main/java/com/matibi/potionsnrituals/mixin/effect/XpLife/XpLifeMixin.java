package com.matibi.potionsnrituals.mixin.effect.XpLife;

import com.matibi.potionsnrituals.effect.ModEffects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class XpLifeMixin {
    @Inject(method = "hurtServer", at = @At("TAIL"), cancellable = true)
    private void pnr$xpAsLife(ServerLevel level, DamageSource source, float damage, CallbackInfoReturnable<Boolean> cir) {
        if (damage <= 0.0F) return;

        Player player = (Player) (Object) this;
        MobEffectInstance effect = player.getEffect(ModEffects.XP_LIFE);
        if (effect == null) return;

        int levelCost = Math.max(0, (int) Math.floor(damage / 2.0F));

        if (damage % 2 != 0)
            player.giveExperiencePoints(- (int) (player.getXpNeededForNextLevel() / 2.0f));

        for (int i = 0; i < levelCost; i++) {
            if (player.experienceLevel <= 0) {
                player.removeEffect(ModEffects.XP_LIFE);
                player.hurtServer(level, source, player.getMaxHealth());
                break;
            }
            int xpForCurrentLevel = player.getXpNeededForNextLevel();
            player.giveExperiencePoints(-xpForCurrentLevel);
        }
        player.heal(damage);

        cir.setReturnValue(false);
    }
}