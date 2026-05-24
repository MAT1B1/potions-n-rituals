package com.matibi.potionsnrituals.effect.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.levelgen.Heightmap;
import org.jspecify.annotations.NonNull;

public class VampirismEffect extends MobEffect {
    public VampirismEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x8B0000);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int tickCount, int amplification) {
        return tickCount % 20 == 0;
    }

    @Override
    public boolean applyEffectTick(@NonNull ServerLevel serverLevel, @NonNull LivingEntity mob, int amplification) {
        if (serverLevel.isRaining() || serverLevel.isDarkOutside()) return super.applyEffectTick(serverLevel, mob, amplification);
        ItemStack helmet = mob.getItemBySlot(EquipmentSlot.HEAD);
        if (!helmet.isEmpty()) return super.applyEffectTick(serverLevel, mob, amplification);

        BlockPos pos = mob.getOnPos();
        int topY = serverLevel.getHeight(Heightmap.Types.WORLD_SURFACE, pos.getX(), pos.getZ());
        if (pos.getY() >= topY)
            mob.setRemainingFireTicks(40);
        return super.applyEffectTick(serverLevel, mob, amplification);
    }
}
