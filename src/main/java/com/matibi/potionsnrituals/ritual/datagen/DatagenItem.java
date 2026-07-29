package com.matibi.potionsnrituals.ritual.datagen;

import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.world.item.Item;

record DatagenItem(Item item, int count, DataComponentPatch components) {
    public static DatagenItem of(Item item, int count) {
        return new DatagenItem(item, count, DataComponentPatch.EMPTY);
    }
}