package com.matibi.potionsnrituals.command.test.tests;

import com.matibi.potionsnrituals.command.test.TestAssert;
import com.matibi.potionsnrituals.command.test.TestResult;
import com.matibi.potionsnrituals.command.test.TestSuite;
import com.matibi.potionsnrituals.config.ModConfig;
import com.matibi.potionsnrituals.effect.ModEffects;
import com.matibi.potionsnrituals.util.TickManager;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.LivingEntity;

public class VampirismTest implements TestSuite {

    @Override
    public String id() {
        return "vampirism:lifesteal";
    }

    @Override
    public String description() {
        return "Vérifie que l'effet Vampirism soigne le joueur quand il tape un mob";
    }

    @Override
    public String category() {
        return "effects";
    }

    @Override
    public TestResult run(ServerLevel level, ServerPlayer player) {
        float initialHealth = player.getMaxHealth();
        player.setHealth(initialHealth * 0.5f);
        float beforeHit = player.getHealth();

        player.addEffect(new MobEffectInstance(ModEffects.VAMPIRISM, ModConfig.get().dur_basic, 0));

        LivingEntity target = TestAssert.spawnEntity(level, player, EntityTypes.ZOMBIE, "VampirismTest");
        try {
            target.setHealth(20);
            target.hurtServer(level, level.damageSources().playerAttack(player), 1.0f);
            TickManager.flush(level.getServer());

            float afterHit = player.getHealth();
            float expectedHeal = (ModConfig.get().vampirism_heal + ModConfig.get().vampirism_heal_per_level * 0);
            float expectedHealth = beforeHit + expectedHeal;

            if (afterHit <= beforeHit)
                return TestResult.fail("Vampirism n'a pas soigné : vie " + beforeHit + " → " + afterHit);

            if (Math.abs(afterHit - expectedHealth) > 0.5f)
                return TestResult.fail("Soin attendu ~" + String.format("%.2f", expectedHealth)
                        + ", obtenu " + String.format("%.2f", afterHit));

            return TestResult.pass("Soin confirmé : " + String.format("%.1f", beforeHit)
                    + " → " + String.format("%.1f", afterHit) + " HP");
        } finally {
            TestAssert.removeEntity(target);
        }
    }
}
