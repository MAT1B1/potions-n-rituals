package com.matibi.potionsnrituals.command.test.tests;

import com.matibi.potionsnrituals.command.test.TestAssert;
import com.matibi.potionsnrituals.command.test.TestResult;
import com.matibi.potionsnrituals.command.test.TestSuite;
import com.matibi.potionsnrituals.effect.ModEffects;
import com.matibi.potionsnrituals.util.TickManager;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.level.block.Blocks;

import java.util.Set;

public class HydrophobiaTest implements TestSuite {

    @Override
    public String id() {
        return "hydrophobia:drown_damage";
    }

    @Override
    public String description() {
        return "Vérifie que Hydrophobia inflige des dégâts de noyade dans l'eau";
    }

    @Override
    public String category() {
        return "effects";
    }

    @Override
    public TestResult run(ServerLevel level, ServerPlayer player) {
        BlockPos pos = player.blockPosition();

        level.setBlock(pos, Blocks.WATER.defaultBlockState(), 3);

        player.teleportTo(level, pos.getX() + 0.5, pos.getY() + 0.1, pos.getZ() + 0.5, Set.of(), player.getYRot(), player.getXRot(), true);

        TickManager.runLater(20, _ -> {
            float healthBefore = player.getHealth();
            player.addEffect(new MobEffectInstance(ModEffects.HYDROPHOBIA, 20 * 60, 0));

            TestAssert.forceTickEffects(level, player);
            TickManager.flush(level.getServer());
            TickManager.runLater(20, _ -> {
                if (!player.isInWater()) {
                    TestAssert.sendAsyncResult(id(),
                            TestResult.fail("Joueur pas dans eau (Y=" + player.getY() + ")"));
                    return;
                }
                player.removeEffect(ModEffects.HYDROPHOBIA);
                level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);

                if (player.getHealth() >= healthBefore)
                    TestAssert.sendAsyncResult(id(),
                            TestResult.fail("Hydrophobia n'a pas infligé de dégâts dans l'eau"));
                else
                    TestAssert.sendAsyncResult(id(),
                            TestResult.pass("Hydrophobia inflige des dégâts de noyade"));
            });
        });
        return TestResult.pending("Hydrophobia");
    }
}
