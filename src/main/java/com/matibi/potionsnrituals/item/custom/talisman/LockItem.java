package com.matibi.potionsnrituals.item.custom.talisman;

import com.matibi.potionsnrituals.datacomponent.ModDataComponents;
import com.matibi.potionsnrituals.item.ModItems;
import com.matibi.potionsnrituals.util.ILockable;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jspecify.annotations.NonNull;

import java.util.UUID;

public class LockItem extends Item {

    public LockItem(Properties properties) {
        super(properties.stacksTo(16).rarity(Rarity.RARE));
    }

    @Override
    public @NonNull InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockEntity be = level.getBlockEntity(pos);
        Player player = context.getPlayer();

        if (be != null && player != null) {

            if (!level.isClientSide()) {
                UUID lockId = UUID.randomUUID();

                ((ILockable) be).potions_n_rituals$setPadlockId(lockId);
                level.sendBlockUpdated(pos, level.getBlockState(pos), level.getBlockState(pos), 3);
                ItemStack keyStack = new ItemStack(ModItems.KEY);
                keyStack.set(ModDataComponents.LOCK_ID, lockId);

                if (!player.getInventory().add(keyStack))
                    player.drop(keyStack, false);

                context.getItemInHand().shrink(1);
            }

            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    public static void blockChest() {
        UseBlockCallback.EVENT.register((player, level, hand, hitResult) -> {
            BlockEntity be = level.getBlockEntity(hitResult.getBlockPos());
            ILockable lockable = (ILockable) be;
            if (lockable == null || lockable.isUnlocked() || level.isClientSide()) return InteractionResult.PASS;

            ItemStack itemInHand = player.getItemInHand(hand);
            if (itemInHand.is(ModItems.KEY)) {
                UUID keyId = itemInHand.get(ModDataComponents.LOCK_ID);

                if (keyId != null && keyId.equals(lockable.potions_n_rituals$getPadlockId())) {
                    if (!level.isClientSide()) {
                        lockable.potions_n_rituals$setPadlockId(null);
                        level.destroyBlockProgress(player.getId(), hitResult.getBlockPos(), -1);
                        level.sendBlockUpdated(hitResult.getBlockPos(), level.getBlockState(hitResult.getBlockPos()), level.getBlockState(hitResult.getBlockPos()), 3);
                        itemInHand.shrink(1);
                        player.addItem(new ItemStack(ModItems.LOCK));
                        player.sendOverlayMessage(Component.translatable("message.potions-n-rituals.unlocked"));
                    }
                    return InteractionResult.SUCCESS;
                }
            }
            if (!level.isClientSide())
                player.sendOverlayMessage(Component.translatable("message.potions-n-rituals.locked"));
            return InteractionResult.FAIL;
        });
    }

    @Override
    public boolean isFoil(@NonNull ItemStack stack) { return true; }
}
