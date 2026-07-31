package com.matibi.potionsnrituals.command.test.tests;

import com.matibi.potionsnrituals.command.test.TestResult;
import com.matibi.potionsnrituals.command.test.TestSuite;
import com.matibi.potionsnrituals.effect.ModEffects;
import com.matibi.potionsnrituals.util.TickManager;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;

public class GhostWalkTest implements TestSuite {

    @Override
    public String id() {
        return "ghost_walk:apply";
    }

    @Override
    public String description() {
        return "Vérifie que Ghost Walk s'applique correctement";
    }

    @Override
    public String category() {
        return "effects";
    }

    @Override
    public TestResult run(ServerLevel level, ServerPlayer player) {
        player.addEffect(new MobEffectInstance(ModEffects.GHOST_WALK, 20 * 60, 0));
        TickManager.flush(level.getServer());
        if (!player.hasEffect(ModEffects.GHOST_WALK))
            return TestResult.fail("Ghost Walk ne s'applique pas");

        player.removeEffect(ModEffects.GHOST_WALK);
        if (player.hasEffect(ModEffects.GHOST_WALK))
            return TestResult.fail("Ghost Walk n'a pas été retiré");

        return TestResult.pass("Ghost Walk s'applique et se retire correctement");
    }
}
