package com.matibi.potionsnrituals.item.custom.talisman;

import com.matibi.potionsnrituals.screen.CommandWriteScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class PhoenixQuillItem extends Item {
    public PhoenixQuillItem(Properties properties) {
        super(properties.stacksTo(16).rarity(Rarity.EPIC));
    }

    @Override
    public @NonNull InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (level.isClientSide())
            openCommandScreen();
        return InteractionResult.SUCCESS;
    }

    private void openCommandScreen() {
        Minecraft.getInstance().setScreenAndShow(new CommandWriteScreen());
    }

    @Override
    public void inventoryTick(@NonNull ItemStack itemStack, @NonNull ServerLevel level, @NonNull Entity owner, @Nullable EquipmentSlot slot) {
        if (slot == EquipmentSlot.MAINHAND ||slot == EquipmentSlot.OFFHAND) {
            level.sendParticles(ParticleTypes.FLAME,
                    owner.getX(),
                    owner.getY() + 1.0,
                    owner.getZ(),
                    1,
                    0.2, 0.2, 0.2,
                    0.01
            );
            if (level.getGameTime() % 40 == 0)
                owner.setRemainingFireTicks(20 * 5);
        }
    }

    @Override
    public boolean isFoil(@NonNull ItemStack stack) { return true; }
}
