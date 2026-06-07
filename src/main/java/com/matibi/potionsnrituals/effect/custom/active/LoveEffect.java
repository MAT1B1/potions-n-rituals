package com.matibi.potionsnrituals.effect.custom.active;

import com.matibi.potionsnrituals.config.ModConfig;
import com.matibi.potionsnrituals.effect.ActiveEffect;
import com.matibi.potionsnrituals.effect.ModEffects;
import com.matibi.potionsnrituals.effect.helper.IPregnantPlayer;
import com.matibi.potionsnrituals.util.ActiveEffectUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class LoveEffect extends MobEffect implements ActiveEffect {
    public LoveEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xFF69B4);
    }

    @Override
    public void useOnKeybind(ServerLevel world, Player player, int duration, int amplifier) {
        if (!(player instanceof ServerPlayer user)) return;
        if (!user.hasEffect(ModEffects.LOVE)) return;
        if (user.hasEffect(ModEffects.PREGNANT)) return;

        LivingEntity target = ActiveEffectUtils.getLookedAtEntity(user, ModConfig.get().max_distance_pregnancy);
        if (!(target instanceof AgeableMob ageable)) return;
        if (!ageable.isAlive() || ageable.isBaby()) return;
        if (!user.hasLineOfSight(target)) return;

        ((IPregnantPlayer) user).pnr$setPregnancyMob(ageable.getType());

        user.addEffect(new MobEffectInstance(ModEffects.PREGNANT, ModConfig.get().pregnancy_duration, 0));
        user.removeEffect(ModEffects.LOVE);
    }
}
