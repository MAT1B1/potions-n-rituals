package com.matibi.potionsnrituals.block.custom;

import com.matibi.potionsnrituals.datacomponent.ModDataComponents;
import com.matibi.potionsnrituals.datacomponent.ReturnLocation;
import com.matibi.potionsnrituals.item.custom.talisman.AlchemicalBagItem;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jspecify.annotations.NonNull;

import java.util.Set;

public class BagExitBlock extends Block {

    public BagExitBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @NonNull InteractionResult useWithoutItem(@NonNull BlockState state, Level level, @NonNull BlockPos pos, @NonNull Player player, @NonNull BlockHitResult hitResult) {
        if (!level.isClientSide()) {
            ServerLevel serverLevel = (ServerLevel) level;
            ItemStack bag = findBag(player);

            if (!bag.isEmpty()) {
                ReturnLocation returnLoc = bag.get(ModDataComponents.RETURN_LOCATION);

                if (returnLoc != null) {
                    ServerLevel targetLevel = serverLevel.getServer().getLevel(returnLoc.dimension());
                    if (targetLevel != null)
                        player.teleportTo(
                                targetLevel,
                                returnLoc.x(),
                                returnLoc.y(),
                                returnLoc.z(),
                                Set.of(),
                                player.getYRot(),
                                player.getXRot(),
                                true
                        );
                }
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.CONSUME;
    }

    private ItemStack findBag(Player player) {
        for (ItemStack item : player.getInventory()) {
            if (item.getItem() instanceof AlchemicalBagItem)
                return item;
        }
        return ItemStack.EMPTY;
    }
}
