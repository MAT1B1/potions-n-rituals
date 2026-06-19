package com.matibi.potionsnrituals.effect.custom;

import com.matibi.potionsnrituals.effect.ModEffects;
import com.matibi.potionsnrituals.util.ModUtils;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import org.jspecify.annotations.NonNull;

public class BerserkEffect extends MobEffect {
    public BerserkEffect() {
        super(MobEffectCategory.NEUTRAL, 0xba0006);

        this.addAttributeModifier(Attributes.JUMP_STRENGTH,
                ModUtils.id("berserk_jump"),
                0.15D,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);

        this.addAttributeModifier(Attributes.MOVEMENT_SPEED,
                ModUtils.id("berserk_mvt"),
                0.15D,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);

        this.addAttributeModifier(Attributes.ATTACK_SPEED,
                ModUtils.id("berserk_atk_speed"),
                1.0D,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);

        this.addAttributeModifier(Attributes.ATTACK_DAMAGE,
                ModUtils.id("berserk_atk_dmg"),
                1.0D,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);

        this.addAttributeModifier(Attributes.MINING_EFFICIENCY,
                ModUtils.id("berserk_mining_efficiency"),
                1.0D,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);

        this.addAttributeModifier(Attributes.BLOCK_BREAK_SPEED,
                ModUtils.id("berserk_mining_speed"),
                1.0D,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration % 20 == 0;
    }

    @Override
    public void onMobRemoved(@NonNull ServerLevel level, @NonNull LivingEntity mob, int amplifier, Entity.@NonNull RemovalReason reason) {
        mob.removeEffect(MobEffects.NIGHT_VISION);
        if (!mob.isDeadOrDying())
            mob.addEffect(new MobEffectInstance(ModEffects.AFTERMATH, 20 * 10));
        super.onMobRemoved(level, mob, amplifier, reason);
    }

    @Override
    public void onEffectRemoved(@NonNull MobEffectInstance effectInstance, @NonNull LivingEntity entity) {
        entity.removeEffect(MobEffects.NIGHT_VISION);
        if (!entity.isDeadOrDying())
            entity.addEffect(new MobEffectInstance(ModEffects.AFTERMATH, 20 * 10));
        super.onEffectRemoved(effectInstance, entity);
    }

    @Override
    public boolean applyEffectTick(@NonNull ServerLevel world, LivingEntity entity, int amplifier) {
        if (!entity.hasEffect(MobEffects.NIGHT_VISION))
            entity.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, -1, 0, false, false, false));
        return true;
    }

    public static void registerDeathHandler() {
        ServerLivingEntityEvents.ALLOW_DEATH.register((entity, damageSource, _) -> {
            if (!(entity instanceof Player player)) return true;
            if (!player.hasEffect(ModEffects.BERSERK)) return true;
            if (damageSource.is(DamageTypes.FELL_OUT_OF_WORLD))
                return true;
            player.setHealth(1.0F);
            return false;
        });
    }
}
