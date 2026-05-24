package com.matibi.potionsnrituals.mixin.effect;

import com.matibi.potionsnrituals.effect.ModEffects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class ZeusMixin {

    @Inject(method = "hurtServer", at = @At("TAIL"))
    private void strikeOnHurt(ServerLevel level, DamageSource source, float damage, CallbackInfoReturnable<Boolean> cir) {
        if (!cir.getReturnValue()) return;
        if (source.is(DamageTypes.LIGHTNING_BOLT)) return;
        if (!(source.getEntity() instanceof LivingEntity attacker)) return;

        MobEffectInstance effect = attacker.getEffect(ModEffects.ZEUS_BENEDICTION);
        if (effect == null) return;

        LivingEntity target = (LivingEntity) (Object) this;
        LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(level, EntitySpawnReason.TRIGGERED);
        if (lightning == null) return;

        lightning.setPos(target.position());
        if (attacker instanceof ServerPlayer player) {
            lightning.setCause(player);
        }
        level.addFreshEntity(lightning);
    }
}
