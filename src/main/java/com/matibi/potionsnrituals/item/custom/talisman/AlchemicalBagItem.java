package com.matibi.potionsnrituals.item.custom.talisman;

import com.matibi.potionsnrituals.block.ModBlocks;
import com.matibi.potionsnrituals.config.ModConfig;
import com.matibi.potionsnrituals.datacomponent.ModDataComponents;
import com.matibi.potionsnrituals.datacomponent.ReturnLocation;
import com.matibi.potionsnrituals.world.data.ModAttachments;
import com.matibi.potionsnrituals.world.dimension.ModDimensions;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NonNull;

import java.util.Set;

public class AlchemicalBagItem extends Item {

    public AlchemicalBagItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public @NonNull InteractionResult use(final Level level, final @NonNull Player player, final @NonNull InteractionHand hand) {
        if (level.dimension() == ModDimensions.POCKET_DIMENSION) {
            if (!level.isClientSide())
                player.sendSystemMessage(Component.translatable("message.potions-n-rituals.alchemical_bag.forbidden").withStyle(ChatFormatting.RED));
            return InteractionResult.FAIL;
        }

        ItemStack bag = player.getItemInHand(hand);
        if (level.isClientSide())
            return player.isCrouching() ? InteractionResult.SUCCESS : InteractionResult.PASS;

        if (player.isCrouching()) {
            ServerLevel serverLevel = (ServerLevel) level;

            Integer bagId = bag.get(ModDataComponents.BAG_ID);
            if (bagId == null || bagId == 0) {
                ServerLevel overworld = serverLevel.getServer().getLevel(Level.OVERWORLD);
                if (overworld != null) {
                    bagId = overworld.getAttachedOrCreate(ModAttachments.BAG_ID_COUNTER, () -> 1);
                    bag.set(ModDataComponents.BAG_ID, bagId);
                    overworld.setAttached(ModAttachments.BAG_ID_COUNTER, bagId + 1);
                } else
                    bagId = 1;
            }

            ReturnLocation returnLoc = new ReturnLocation(level.dimension(), player.getX(), player.getY(), player.getZ());
            bag.set(ModDataComponents.RETURN_LOCATION, returnLoc);

            BlockPos dimPos = new BlockPos(bagId * 1000, 100, 0);

            ServerLevel pocketDim = serverLevel.getServer().getLevel(ModDimensions.POCKET_DIMENSION);

            if (pocketDim != null) {
                generateRoom(pocketDim, dimPos);
                player.teleportTo(
                        pocketDim,
                        dimPos.getX() + 2.5,
                        dimPos.getY() + 1.0,
                        dimPos.getZ() + 2.5,
                        Set.of(),
                        player.getYRot(),
                        player.getXRot(),
                        true
                );
            }

            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    private void generateRoom(final ServerLevel level, final BlockPos startPos) {
        if (!level.getBlockState(startPos).isAir())
            return;

        BlockState wallBlock = ModBlocks.BAG_WALL.defaultBlockState();
        BlockState returnBlock = ModBlocks.BAG_EXIT.defaultBlockState();
        BlockState air = Blocks.AIR.defaultBlockState();

        for (int x = 0; x < 2 + ModConfig.get().pocket_dimension_width; x++) {
            for (int y = 0; y < 2 + ModConfig.get().pocket_dimension_height; y++) {
                for (int z = 0; z < 2 + ModConfig.get().pocket_dimension_length; z++) {
                    BlockPos currentPos = startPos.offset(x, y, z);

                    if (x == 0 || x == ModConfig.get().pocket_dimension_width + 1
                            || y == 0 || y == ModConfig.get().pocket_dimension_height + 1
                            || z == 0 || z == ModConfig.get().pocket_dimension_length + 1)
                        level.setBlockAndUpdate(currentPos, wallBlock);
                    else
                        level.setBlockAndUpdate(currentPos, air);
                }
            }
        }
        level.setBlockAndUpdate(startPos.offset(
                ModConfig.get().pocket_dimension_width / 2 + 1,
                ModConfig.get().pocket_dimension_height + 1,
                ModConfig.get().pocket_dimension_length / 2 + 1), returnBlock);
    }
}