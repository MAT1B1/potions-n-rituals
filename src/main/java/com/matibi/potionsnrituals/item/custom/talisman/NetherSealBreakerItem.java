package com.matibi.potionsnrituals.item.custom.talisman;

import com.matibi.potionsnrituals.entity.ModEntities;
import com.matibi.potionsnrituals.entity.PortalBuilderEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

public class NetherSealBreakerItem extends Item {

    public NetherSealBreakerItem(Properties properties) {
        super(properties.stacksTo(1).rarity(Rarity.RARE));
    }

    @Override
    public @NonNull InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();

        if (!level.isClientSide()) {
            BlockPos spawnPos = context.getClickedPos().above();

            Direction.Axis axis = context.getHorizontalDirection().getAxis();

            PortalBuilderEntity builder = new PortalBuilderEntity(ModEntities.PORTAL_BUILDER, level);
            builder.setPos(spawnPos.getX() + 0.5, spawnPos.getY(), spawnPos.getZ() + 0.5);
            builder.setPortalAxis(axis);

            level.addFreshEntity(builder);

            context.getItemInHand().shrink(1);
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean isFoil(@NonNull ItemStack stack) { return true; }
}