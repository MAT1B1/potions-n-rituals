package com.matibi.potionsnrituals.block.custom.cauldron;

import com.matibi.potionsnrituals.block.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;

public class BrewingCauldronBlockEntity extends BlockEntity {

    private PotionContents potionContents = PotionContents.EMPTY;

    public BrewingCauldronBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BREWING_CAULDRON, pos, state);
    }

    public PotionContents getPotionContents() {
        return potionContents;
    }

    public void setPotionContents(PotionContents contents) {
        this.potionContents = contents;
        this.setChanged();
        if (this.level != null && !this.level.isClientSide()) {
            this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), 3);
            if (this.level instanceof ServerLevel serverLevel)
                serverLevel.getChunkSource().blockChanged(this.worldPosition);
        }
    }

    @Override
    public Object getRenderData() {
        if (this.potionContents != null && !this.potionContents.equals(PotionContents.EMPTY))
            return this.potionContents.getColor();
        return 0;
    }

    public int getLiquidLevel() {
        return getBlockState().getValue(BrewingCauldronBlock.LEVEL);
    }

    public boolean isFull() {
        return getLiquidLevel() >= BrewingCauldronBlock.MAX_LEVEL;
    }

    public boolean isEmpty() {
        return getLiquidLevel() <= 0;
    }

    public void clear() {
        this.potionContents = PotionContents.EMPTY;
        setChanged();
        if (level != null)
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
    }

    @Override
    protected void saveAdditional(@NonNull ValueOutput output) {
        super.saveAdditional(output);
        if (!potionContents.equals(PotionContents.EMPTY))
            output.store("potion_contents", PotionContents.CODEC, this.potionContents);
    }

    @Override
    protected void loadAdditional(@NonNull ValueInput input) {
        super.loadAdditional(input);
        this.potionContents = input.read("potion_contents", PotionContents.CODEC)
                .orElse(PotionContents.EMPTY);
        if (this.level != null && this.level.isClientSide())
            this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), 3);
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public @NonNull CompoundTag getUpdateTag(final HolderLookup.@NonNull Provider registries) {
        return this.saveCustomOnly(registries);
    }
}