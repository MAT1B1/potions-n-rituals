package com.matibi.potionsnrituals.command.test.tests;

import com.matibi.potionsnrituals.command.test.TestAssert;
import com.matibi.potionsnrituals.command.test.TestResult;
import com.matibi.potionsnrituals.command.test.TestSuite;
import com.matibi.potionsnrituals.effect.ModEffects;
import com.matibi.potionsnrituals.util.TickManager;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class PurificationTest implements TestSuite {

    @Override
    public String id() {
        return "purification:remove_harmful";
    }

    @Override
    public String description() {
        return "Vérifie que Purification supprime les effets négatifs";
    }

    @Override
    public String category() {
        return "effects";
    }

    @Override
    public TestResult run(ServerLevel level, ServerPlayer player) {
        player.addEffect(new MobEffectInstance(MobEffects.POISON, 20 * 60, 0));
        player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 20 * 60, 0));
        player.addEffect(new MobEffectInstance(ModEffects.DOUBLE_HEALTH, 20 * 60, 0)); // beneficial

        player.addEffect(new MobEffectInstance(ModEffects.PURIFICATION, 20 * 60, 0));
        TestAssert.forceTickEffects(level, player);
        TickManager.flush(level.getServer());

        if (player.hasEffect(MobEffects.POISON))
            return TestResult.fail("Purification n'a pas supprimé le poison");

        if (player.hasEffect(MobEffects.WEAKNESS))
            return TestResult.fail("Purification n'a pas supprimé weakness");

        if (!player.hasEffect(ModEffects.DOUBLE_HEALTH))
            return TestResult.fail("Purification a supprimé un effet bénéfique");

        return TestResult.pass("Purification supprime les effets négatifs uniquement");
    }
}
