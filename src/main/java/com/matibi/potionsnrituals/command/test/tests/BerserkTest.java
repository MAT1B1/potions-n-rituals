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
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.LivingEntity;

public class BerserkTest implements TestSuite {

    @Override
    public String id() {
        return "berserk:night_vision_and_aftermath";
    }

    @Override
    public String description() {
        return "Vérifie que Berserk ajoute Night Vision et Aftermath à la fin sur un mob";
    }

    @Override
    public String category() {
        return "effects";
    }

    @Override
    public TestResult run(ServerLevel level, ServerPlayer player) {
        LivingEntity zombie = TestAssert.spawnEntity(level, player, EntityTypes.ZOMBIE, "BerserkTest");
        zombie.addEffect(new MobEffectInstance(ModEffects.BERSERK, 20 * 60, 0));

        TestAssert.forceTickEffects(level, zombie);
        TestAssert.entityHasEffect(zombie, MobEffects.NIGHT_VISION, "Berserk n'a pas ajouté Night Vision");

        zombie.removeEffect(ModEffects.BERSERK);
        TickManager.flush(level.getServer());

        if (!zombie.hasEffect(ModEffects.AFTERMATH))
            return TestResult.fail("Aftermath n'a pas été appliqué après Berserk");

        TestAssert.removeEntity(zombie);
        return TestResult.pass("Berserk ajoute Night Vision et Aftermath à la fin");
    }
}
