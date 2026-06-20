package com.matibi.potionsnrituals.mixin.brewingNpotion;

import com.matibi.potionsnrituals.item.ModItems;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractFurnaceBlockEntity.class)
public abstract class FurnaceLeaveBucketMixin {

    @Inject(method = "burn", at = @At("TAIL"))
    private static void pnr$leaveBucket(NonNullList<ItemStack> items, ItemStack inputItemStack,
                                        ItemStack result, CallbackInfo ci) {
        if (result.is(ModItems.SALT))
            items.set(0, new ItemStack(Items.BUCKET));
    }
}