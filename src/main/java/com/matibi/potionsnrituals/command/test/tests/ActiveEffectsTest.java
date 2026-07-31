package com.matibi.potionsnrituals.command.test.tests;

import com.matibi.potionsnrituals.command.test.TestResult;
import com.matibi.potionsnrituals.command.test.TestSuite;
import com.matibi.potionsnrituals.effect.ModEffects;
import com.matibi.potionsnrituals.effect.custom.active.ActiveTeleportationEffect;
import com.matibi.potionsnrituals.effect.custom.active.LoveEffect;
import com.matibi.potionsnrituals.effect.custom.active.MedusaBenedictionEffect;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

import java.util.ArrayList;
import java.util.List;

public class ActiveEffectsTest implements TestSuite {

    @Override
    public String id() {
        return "active_effects:interface";
    }

    @Override
    public String description() {
        return "Vérifie que Medusa, ActiveTP et Love implémentent ActiveEffect";
    }

    @Override
    public String category() {
        return "effects";
    }

    @Override
    public TestResult run(ServerLevel level, ServerPlayer player) {
        List<String> errors = new ArrayList<>();

        var medusa = ModEffects.MEDUSA_BENEDICTION.value();
        if (!(medusa instanceof MedusaBenedictionEffect ae))
            errors.add("MEDUSA_BENEDICTION n'est pas MedusaBenedictionEffect");
        else if (ae.getCooldowns(0).length != 3)
            errors.add("Medusa cooldowns invalides");

        var tp = ModEffects.ACTIVE_TP.value();
        if (!(tp instanceof ActiveTeleportationEffect ae2))
            errors.add("ACTIVE_TP n'est pas ActiveTeleportationEffect");
        else if (ae2.getCooldowns(0).length != 3)
            errors.add("ActiveTP cooldowns invalides");

        var love = ModEffects.LOVE.value();
        if (!(love instanceof LoveEffect))
            errors.add("LOVE n'est pas LoveEffect");

        if (!errors.isEmpty())
            return TestResult.fail(String.join(" ; ", errors));

        return TestResult.pass("Medusa, ActiveTP et Love implémentent ActiveEffect avec cooldowns");
    }
}
