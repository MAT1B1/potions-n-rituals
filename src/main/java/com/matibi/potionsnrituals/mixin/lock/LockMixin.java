package com.matibi.potionsnrituals.mixin.lock;

import com.matibi.potionsnrituals.util.ILockable;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.UUIDUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

@Mixin(BlockEntity.class)
public abstract class LockMixin implements ILockable {
    @Unique
    private UUID pnr$padlockId = null;

    @Override
    public UUID potions_n_rituals$getPadlockId() {
        return this.pnr$padlockId;
    }

    @Override
    public void potions_n_rituals$setPadlockId(UUID id) {
        this.pnr$padlockId = id;
        ((BlockEntity)(Object)this).setChanged();
    }

    @Inject(method = "saveAdditional", at = @At("TAIL"))
    protected void pnr$savePadlockId(ValueOutput output, CallbackInfo ci) {
        if (this.pnr$padlockId != null)
            output.store("potions-n-rituals:padlock_id", UUIDUtil.CODEC, this.pnr$padlockId);
    }

    @Inject(method = "loadAdditional", at = @At("TAIL"))
    protected void pnr$loadPadlockId(ValueInput input, CallbackInfo ci) {
        this.pnr$padlockId = input.read("potions-n-rituals:padlock_id", UUIDUtil.CODEC).orElse(null);
    }

    @Inject(method = "getUpdateTag", at = @At("RETURN"))
    protected void pnr$syncPadlockTag(HolderLookup.Provider registries, CallbackInfoReturnable<CompoundTag> cir) {
        if (this.pnr$padlockId != null) {
            CompoundTag tag = cir.getReturnValue();
            tag.store("potions-n-rituals:padlock_id", UUIDUtil.CODEC, this.pnr$padlockId);
        }
    }

    @Inject(method = "getUpdatePacket", at = @At("RETURN"), cancellable = true)
    protected void pnr$sendPadlockPacket(CallbackInfoReturnable<Packet<ClientGamePacketListener>> cir) {
        if (cir.getReturnValue() == null)
            cir.setReturnValue(ClientboundBlockEntityDataPacket.create((BlockEntity)(Object)this));
    }
}