package com.matibi.potionsnrituals.command.test;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

public interface TestSuite {

    String id();

    String description();

    String category();

    TestResult run(ServerLevel level, ServerPlayer player);
}
