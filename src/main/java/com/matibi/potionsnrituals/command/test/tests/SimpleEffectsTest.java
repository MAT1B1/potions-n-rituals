package com.matibi.potionsnrituals.command.test.tests;

import com.matibi.potionsnrituals.command.test.TestResult;
import com.matibi.potionsnrituals.command.test.TestSuite;
import com.matibi.potionsnrituals.effect.ModEffects;
import com.matibi.potionsnrituals.util.TickManager;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;

import java.util.ArrayList;
import java.util.List;

public class SimpleEffectsTest implements TestSuite {

    @Override
    public String id() {
        return "simple_effects:apply_all";
    }

    @Override
    public String description() {
        return "Vérifie que tous les effets restants s'appliquent sans erreur";
    }

    @Override
    public String category() {
        return "effects";
    }

    @Override
    public TestResult run(ServerLevel level, ServerPlayer player) {
        List<Holder<MobEffect>> toTest = List.of(
                ModEffects.ADHESION, ModEffects.ASTHMA, ModEffects.BRAINWASHING,
                ModEffects.CLUMSINESS, ModEffects.DWARF, ModEffects.EMPATHY,
                ModEffects.LIQUID_WALKER, ModEffects.LONG_COOLDOWN, ModEffects.LONG_LEG,
                ModEffects.MASKING, ModEffects.NO_INTERACTION, ModEffects.PARANOIA,
                ModEffects.PERM_HEALTH, ModEffects.PERM_SPEED, ModEffects.PERM_STRENGTH,
                ModEffects.REALITY_CHECK, ModEffects.SHORT_COOLDOWN, ModEffects.STUN,
                ModEffects.XP_BOOST, ModEffects.XP_LIFE, ModEffects.XP_REDUCTION,
                ModEffects.ALCHEMIST, ModEffects.GIANT, ModEffects.PETRIFICATION,
                ModEffects.PHOTOSYNTHESIS, ModEffects.ORE_SENSE, ModEffects.PREGNANT,
                ModEffects.TELEPORTATION, ModEffects.MASKING
        );

        List<String> failed = new ArrayList<>();

        for (Holder<MobEffect> effect : toTest) {
            try {
                player.addEffect(new MobEffectInstance(effect, 20 * 60, 0));
                TickManager.flush(level.getServer());
                if (!player.hasEffect(effect))
                    failed.add(effect.getRegisteredName() + " : effet absent après application");
                player.removeEffect(effect);
            } catch (Exception e) {
                failed.add(effect.getRegisteredName() + " : " + e.getClass().getSimpleName());
            }
        }

        if (!failed.isEmpty())
            return TestResult.fail(String.join(" ; ", failed));

        return TestResult.pass(toTest.size() + " effets appliqués et retirés sans erreur");
    }
}
