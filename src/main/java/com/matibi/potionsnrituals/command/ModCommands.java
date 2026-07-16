package com.matibi.potionsnrituals.command;

import com.matibi.potionsnrituals.world.dimension.ModDimensions;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

public class ModCommands {

    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, _, _) ->
            registerSpiritCommand(dispatcher)
        );
    }

    private static void registerSpiritCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("spirit")
                .requires(CommandSourceStack::isPlayer)
                .executes(ModCommands::executeSpiritTeleport));
    }

    private static int executeSpiritTeleport(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ServerPlayer player = context.getSource().getPlayerOrException();

        ServerLevel currentLevel = player.level();
        MinecraftServer server = context.getSource().getServer();

        ServerLevel targetLevel;
        if (currentLevel.dimension() == ModDimensions.SPIRIT_DIMENSION) {
            targetLevel = server.getLevel(Level.OVERWORLD);
            player.sendSystemMessage(Component.literal("Retour dans le monde matériel..."));
        } else {
            targetLevel = server.getLevel(ModDimensions.SPIRIT_DIMENSION);
            player.sendSystemMessage(Component.literal("Entrée dans le monde des esprits..."));
        }

        if (targetLevel != null) {
            player.teleportTo(
                    targetLevel,
                    player.getX(),
                    player.getY(),
                    player.getZ(),
                    java.util.Set.of(),
                    player.getYRot(),
                    player.getXRot(),
                    true
            );
        }

        return 1;
    }
}