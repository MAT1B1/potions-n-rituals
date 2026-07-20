package com.matibi.potionsnrituals.item.custom.talisman;

import com.matibi.potionsnrituals.datacomponent.ModDataComponents;
import com.matibi.potionsnrituals.datacomponent.TalismanCharge;
import com.matibi.potionsnrituals.item.ModItems;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class TalismanItem extends Item {

    public static final int CHARGE_NEEDED = 10;

    public TalismanItem(Properties properties) {
        super(properties.stacksTo(1));
    }

    public void chargeTalisman(ItemStack stack, Player player) {
        TalismanCharge current = stack.getOrDefault(ModDataComponents.TALISMAN_CHARGE, TalismanCharge.EMPTY);

        if (current.isCharged()) return;

        int nextCharge = current.charge() + 1;

        if (nextCharge >= CHARGE_NEEDED) {
            ItemStack chargedStack = new ItemStack(ModItems.TALISMAN_CHARGED);
            player.setItemSlot(EquipmentSlot.OFFHAND, chargedStack);
        } else
            stack.set(ModDataComponents.TALISMAN_CHARGE, new TalismanCharge(nextCharge, false));
    }

    public static void registerEvents() {
        ServerLivingEntityEvents.AFTER_DEATH.register((livingEntity, damageSource) -> {
            if (damageSource.getEntity() instanceof Player player) {
                ItemStack offhandStack = player.getItemBySlot(EquipmentSlot.OFFHAND);

                if (offhandStack.is(ModItems.TALISMAN) && offhandStack.getItem() instanceof TalismanItem talisman) {
                    MinecraftServer server = livingEntity.level().getServer();
                    if (server == null) return;
                    ServerLevel serverLevel = server.getLevel(livingEntity.level().dimension());
                    if (serverLevel == null) return;
                    serverLevel.sendParticles(
                            ParticleTypes.SOUL,
                            livingEntity.getX(),
                            livingEntity.getY() + 1.0,
                            livingEntity.getZ(),
                            20,
                            0.3, 0.5, 0.3,
                            0.05
                    );

                    serverLevel.playSound(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(),
                            SoundEvents.SOUL_ESCAPE, SoundSource.PLAYERS, 1.0F, 1.0F);

                    talisman.chargeTalisman(offhandStack, player);
                }
            }
        });
    }
}