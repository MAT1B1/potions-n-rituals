package com.matibi.potionsnrituals.item.custom;

import com.matibi.potionsnrituals.world.dimension.ModDimensions;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

import java.util.Set;

public class SpiritMirrorItem extends Item {

    public SpiritMirrorItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NonNull InteractionResult use(Level level, Player player, @NonNull InteractionHand usedHand) {
        ItemStack itemStack = player.getItemInHand(usedHand);

        if (!level.isClientSide() && player instanceof ServerPlayer serverPlayer) {
            MinecraftServer server = level.getServer();
            if (server != null) {
                ServerLevel currentLevel = (ServerLevel) level;
                ServerLevel targetLevel;

                if (currentLevel.dimension() == ModDimensions.SPIRIT_DIMENSION) {
                    targetLevel = server.getLevel(Level.OVERWORLD);
                    serverPlayer.sendSystemMessage(Component.literal("Retour dans le monde matériel..."));
                } else {
                    targetLevel = server.getLevel(ModDimensions.SPIRIT_DIMENSION);
                    serverPlayer.sendSystemMessage(Component.literal("Entrée dans le monde des esprits..."));
                }

                if (targetLevel != null) {
                    serverPlayer.teleportTo(
                            targetLevel,
                            serverPlayer.getX(),
                            serverPlayer.getY(),
                            serverPlayer.getZ(),
                            Set.of(),
                            serverPlayer.getYRot(),
                            serverPlayer.getXRot(),
                            true
                    );

                    targetLevel.playSound(null, serverPlayer.blockPosition(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.0F);

                    serverPlayer.getCooldowns().addCooldown(itemStack, 100);
                }
            }
        }
        return InteractionResult.SUCCESS;
    }
}