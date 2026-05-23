package com.matibi.potionsnrituals.item.alchemicalStone;

import com.matibi.potionsnrituals.effect.TerrainApplicableEffect;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

public class AlchemicalStoneItem extends PotionItem {

    public AlchemicalStoneItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NonNull InteractionResult use(Level level, @NonNull Player player, @NonNull InteractionHand hand) {
        if (!level.isClientSide())
            player.sendOverlayMessage(
                    Component.translatable("item.potions-n-rituals.alchemical_stone.block_only"));
        return InteractionResult.FAIL;
    }

    @Override
    public @NonNull InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        ItemStack stack = context.getItemInHand();
        Player player = context.getPlayer();

        if (!(level instanceof ServerLevel serverLevel)) return InteractionResult.SUCCESS;

        PotionContents contents = stack.get(DataComponents.POTION_CONTENTS);
        if (contents == null || !contents.hasEffects()) return InteractionResult.FAIL;

        for (MobEffectInstance effect : contents.getAllEffects()) {
            if (effect.getEffect().value() instanceof TerrainApplicableEffect terrainEffect) {
                if (terrainEffect.isBlockNonApplicable(serverLevel, pos) && player != null)
                    player.sendOverlayMessage(
                            Component.translatable("item.potions-n-rituals.alchemical_stone.block_not_good"));
                else
                    terrainEffect.useOnBlock(serverLevel, player, pos, effect.getDuration(), effect.getAmplifier());
            }
        }

        stack.shrink(1);
        return InteractionResult.SUCCESS;
    }
}