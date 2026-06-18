package com.matibi.potionsnrituals.effect.custom;

import com.matibi.potionsnrituals.config.ModConfig;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class OblivionEffect extends MobEffect {
    public OblivionEffect() {
        super(MobEffectCategory.HARMFUL, 0x383838);
    }

    @Override
    public boolean isInstantaneous() {
        return true;
    }

    @Override
    public boolean applyEffectTick(@NonNull ServerLevel world, @NonNull LivingEntity entity, int amplifier) {
        applyEffect(world, null, null, entity, amplifier);
        return super.applyEffectTick(world, entity, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) { return true; }

    @Override
    public void applyInstantaneousEffect(@NonNull ServerLevel world, @Nullable Entity effectEntity, @Nullable Entity attacker, @NonNull LivingEntity target, int amplifier, double proximity) {
        applyEffect(world, effectEntity, attacker, target, amplifier);
        super.applyInstantaneousEffect(world, effectEntity, attacker, target, amplifier, proximity);
    }

    private static void applyEffect(ServerLevel world, @Nullable Entity effectEntity, @Nullable Entity attacker, LivingEntity target, int amplifier) {
        if (!(target instanceof Player player)) return;

        var inventory = player.getInventory();
        var random = world.getRandom();
        int count = (int) (inventory.getContainerSize() * ModConfig.get().inventory_forgotten);

        for (int i = 0; i < count; i++) {
            int slot = random.nextInt(inventory.getContainerSize());
            if (!inventory.getItem(slot).isEmpty()) {
                player.drop(inventory.getItem(slot).split(1), true);
            }
        }
    }
}