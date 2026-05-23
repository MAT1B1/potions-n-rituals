package com.matibi.potionsnrituals.mixin.brewingNpotion;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BrewingStandBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(BrewingStandBlockEntity.class)
public interface BrewingStandAccessor {
    @Accessor("fuel")
    int getFuel();

    @Accessor("fuel")
    void setFuel(int value);

    @Accessor("items")
    NonNullList<ItemStack> getInventory();
}
