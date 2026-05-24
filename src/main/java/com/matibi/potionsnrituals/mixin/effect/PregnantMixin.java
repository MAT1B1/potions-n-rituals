package com.matibi.potionsnrituals.mixin.effect;

import com.matibi.potionsnrituals.effect.helper.IPregnantPlayer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(Player.class)
public class PregnantMixin implements IPregnantPlayer {

    @Unique
    private String pnr$mobTypeRegistryName = null;

    @Override
    public Optional<EntityType<?>> pnr$getPregnancyMob() {
        if (pnr$mobTypeRegistryName == null) return Optional.empty();
        return BuiltInRegistries.ENTITY_TYPE.getOptional(Identifier.tryParse(pnr$mobTypeRegistryName));
    }

    @Override
    public void pnr$setPregnancyMob(EntityType<?> type) {
        Identifier key = BuiltInRegistries.ENTITY_TYPE.getKey(type);
        this.pnr$mobTypeRegistryName = key.toString();
    }

    @Override
    public void pnr$clearPregnancy() {
        this.pnr$mobTypeRegistryName = null;
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void pnr$writeNbt(ValueOutput output, CallbackInfo ci) {
        if (pnr$mobTypeRegistryName != null)
            output.putString("PnRPregnancyMob", pnr$mobTypeRegistryName);
    }

    // Chargement depuis le NBT du joueur
    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void pnr$readNbt(ValueInput input, CallbackInfo ci) {
        if (input.contains("PnRPregnancyMob"))
            this.pnr$mobTypeRegistryName = input.getString("PnRPregnancyMob").orElse(null);
        else
            this.pnr$mobTypeRegistryName = null;

    }
}