package com.matibi.potionsnrituals.effect.custom.active;

import com.matibi.potionsnrituals.config.ModConfig;
import com.matibi.potionsnrituals.effect.ActiveEffect;
import com.matibi.potionsnrituals.effect.ModEffects;
import com.matibi.potionsnrituals.util.ActiveEffectUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class MedusaBenedictionEffect extends MobEffect implements ActiveEffect {
    public MedusaBenedictionEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x556B2F);
    }

    @Override
    public void useOnKeybind(ServerLevel world, Player player, int duration, int amplifier) {
        if (!(player instanceof ServerPlayer serverPlayer)) return;

        double range = ModConfig.get().medusa_range + ModConfig.get().medusa_range_per_level * amplifier;
        LivingEntity target = ActiveEffectUtils.getLookedAtEntity(serverPlayer, range);
        if (target == null) return;

        target.addEffect(new MobEffectInstance(ModEffects.PETRIFICATION, 20 * 45, amplifier, false, false, true));
    }

    @Override
    public int[] getCooldowns(int amplifier) {
        return new int[]{
                ModConfig.get().medusa_short_cooldown,
                ModConfig.get().medusa_cooldown,
                ModConfig.get().medusa_long_cooldown
        };
    }
}
