package com.matibi.potionsnrituals.block.custom.pedestal;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;

@Environment(EnvType.CLIENT)
public class PedestalRenderState extends BlockEntityRenderState {
    public final ItemStackRenderState itemRenderState = new ItemStackRenderState();
    public boolean hasItem = false;
    public float ageInTicks = 0;
}