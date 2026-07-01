package com.matibi.potionsnrituals.mixin.brewingNpotion;

import com.matibi.potionsnrituals.block.ModBlocks;
import com.matibi.potionsnrituals.block.custom.cauldron.BrewingCauldronBlock;
import com.matibi.potionsnrituals.block.custom.cauldron.BrewingCauldronBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractCauldronBlock.class)
public class CauldronBlockMixin {

    @Inject(method = "useItemOn", at = @At("HEAD"), cancellable = true)
    private void onUsePotionOnVanillaCauldron(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult, CallbackInfoReturnable<InteractionResult> cir) {
        if (itemStack.is(Items.POTION) || itemStack.is(Items.SPLASH_POTION) || itemStack.is(Items.LINGERING_POTION) || itemStack.is(com.matibi.potionsnrituals.item.ModItems.ALCHEMICAL_STONE)) {
            PotionContents itemContents = itemStack.get(DataComponents.POTION_CONTENTS);

            if (itemContents != null && itemContents.hasEffects()) {
                if (!level.isClientSide()) {
                    BlockState newState = ModBlocks.BREWING_CAULDRON.defaultBlockState().setValue(BrewingCauldronBlock.LEVEL, 1);
                    level.setBlock(pos, newState, 3);

                    BlockEntity be = level.getBlockEntity(pos);
                    if (be instanceof BrewingCauldronBlockEntity cauldronBe)
                        cauldronBe.setPotionContents(itemContents);

                    level.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    if (!player.isCreative())
                        itemStack.shrink(1);
                }

                cir.setReturnValue(InteractionResult.SUCCESS);
            }
        }
    }
}