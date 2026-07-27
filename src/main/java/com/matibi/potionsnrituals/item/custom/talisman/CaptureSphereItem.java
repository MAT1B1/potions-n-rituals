package com.matibi.potionsnrituals.item.custom.talisman;

import com.matibi.potionsnrituals.datacomponent.CapturedMob;
import com.matibi.potionsnrituals.datacomponent.ModDataComponents;
import com.matibi.potionsnrituals.entity.CaptureSphereEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public class CaptureSphereItem extends Item {

    public CaptureSphereItem(Properties properties) {
        super(properties.stacksTo(16));
    }

    @Override
    public @NonNull InteractionResult use(@NonNull Level level, @NonNull Player player, @NonNull InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        level.playSound(
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                SoundEvents.SNOWBALL_THROW,
                SoundSource.NEUTRAL,
                0.5F,
                0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F)
        );

        if (level instanceof ServerLevel serverLevel) {
            Projectile.spawnProjectileFromRotation(
                    (l, e, s) -> {
                        CaptureSphereEntity entity = new CaptureSphereEntity(l, e, s);
                        entity.setItem(s);
                        return entity;
                    },
                    serverLevel,
                    stack,
                    player,
                    0.0F,
                    1.5F,
                    1.0F
            );
        }

        stack.shrink(1);

        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(@NonNull ItemStack stack, @NonNull TooltipContext context, @NonNull TooltipDisplay display, @NonNull Consumer<Component> tooltip, @NonNull TooltipFlag flag) {
        CapturedMob captured = stack.get(ModDataComponents.CAPTURED_MOB);
        if (captured != null && captured.hasMob()) {
            captured.entityType().ifPresent(id ->
                    tooltip.accept(Component.translatable("tooltip.potions-n-rituals.capture_sphere.contains", id.getPath()).withStyle(ChatFormatting.GRAY))
            );
        }
    }

    @Override
    public boolean isFoil(@NonNull ItemStack itemStack) {
        return true;
    }
}