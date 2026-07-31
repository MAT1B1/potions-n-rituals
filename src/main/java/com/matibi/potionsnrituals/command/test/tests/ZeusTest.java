package com.matibi.potionsnrituals.command.test.tests;

import com.matibi.potionsnrituals.command.test.TestResult;
import com.matibi.potionsnrituals.command.test.TestSuite;
import com.matibi.potionsnrituals.effect.ModEffects;
import com.matibi.potionsnrituals.effect.custom.active.ZeusBenedictionEffect;
import com.matibi.potionsnrituals.util.TickManager;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;

public class ZeusTest implements TestSuite {

    @Override
    public String id() {
        return "zeus:active_effect";
    }

    @Override
    public String description() {
        return "Vérifie que l'effet actif Zeus implémente ActiveEffect et useOnKeybind";
    }

    @Override
    public String category() {
        return "effects";
    }

    @Override
    public TestResult run(ServerLevel level, ServerPlayer player) {
        var effect = ModEffects.ZEUS_BENEDICTION.value();
        if (!(effect instanceof ZeusBenedictionEffect zeus))
            return TestResult.fail("ZEUS_BENEDICTION n'est pas une instance de ZeusBenedictionEffect");

        player.addEffect(new MobEffectInstance(ModEffects.ZEUS_BENEDICTION, 72000, 0));
        TickManager.flush(level.getServer());

        int[] cooldowns = zeus.getCooldowns(0);
        if (cooldowns.length < 3)
            return TestResult.fail("getCooldowns ne retourne pas assez de valeurs");

        if (cooldowns[0] <= 0)
            return TestResult.fail("zeus_short_cooldown devrait être > 0, obtenu " + cooldowns[0]);

        return TestResult.pass("Zeus est actif, cooldowns = [" + cooldowns[0] + ", " + cooldowns[1] + ", " + cooldowns[2] + "] ticks");
    }
}
