package com.matibi.potionsnrituals.command.test;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

public record TestContext(
    ServerPlayer player,
    MinecraftServer server,
    ServerLevel level,
    TestHelper helper
) {
}