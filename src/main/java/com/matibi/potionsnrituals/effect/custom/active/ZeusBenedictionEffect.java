package com.matibi.potionsnrituals.effect.custom.active;

import com.matibi.potionsnrituals.config.ModConfig;
import com.matibi.potionsnrituals.effect.ActiveEffect;
import com.matibi.potionsnrituals.util.ActiveEffectUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class ZeusBenedictionEffect extends MobEffect implements ActiveEffect {
    public ZeusBenedictionEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xF5E96B);
    }

    @Override
    public boolean useOnKeybind(ServerLevel world, Player player, int duration, int amplifier) {
        if (!(player instanceof ServerPlayer serverPlayer)) return false;

        double range = ModConfig.get().zeus_range + ModConfig.get().zeus_range_per_level * amplifier;
        BlockHitResult hit = ActiveEffectUtils.getLookedAtBlock(serverPlayer, range);
        if (hit.getType() == HitResult.Type.MISS) return false;

        LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(world, EntitySpawnReason.TRIGGERED);
        if (lightning != null) {
            lightning.setPos(hit.getBlockPos().getX(), hit.getBlockPos().getY(), hit.getBlockPos().getZ());
            world.addFreshEntity(lightning);
        }
        return true;
    }

    @Override
    public int[] getCooldowns(int amplifier) {
        return new int[]{
                ModConfig.get().zeus_short_cooldown,
                ModConfig.get().zeus_cooldown,
                ModConfig.get().zeus_long_cooldown
        };
    }
}
