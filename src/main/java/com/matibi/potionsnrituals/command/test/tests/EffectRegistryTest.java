package com.matibi.potionsnrituals.command.test.tests;

import com.matibi.potionsnrituals.command.test.TestResult;
import com.matibi.potionsnrituals.command.test.TestSuite;
import com.matibi.potionsnrituals.effect.ModEffects;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;

import java.lang.reflect.Field;

public class EffectRegistryTest implements TestSuite {

    @Override
    public String id() {
        return "effects:registry";
    }

    @Override
    public String description() {
        return "Vérifie que tous les effets de ModEffects sont enregistrés dans BuiltInRegistries.MOB_EFFECT";
    }

    @Override
    public String category() {
        return "effects";
    }

    @Override
    public TestResult run(ServerLevel level, ServerPlayer player) {
        int total = 0;
        int found = 0;
        int missing = 0;
        StringBuilder missingNames = new StringBuilder();

        for (Field field : ModEffects.class.getDeclaredFields()) {
            if (field.getType() != net.minecraft.core.Holder.class) continue;
            if (!java.lang.reflect.Modifier.isStatic(field.getModifiers())) continue;

            total++;
            try {
                @SuppressWarnings("unchecked")
                net.minecraft.core.Holder<MobEffect> holder =
                        (net.minecraft.core.Holder<MobEffect>) field.get(null);

                String name = holder.getRegisteredName();
                Identifier id = Identifier.parse(name);
                boolean present = BuiltInRegistries.MOB_EFFECT.get(id).isPresent();
                if (present) {
                    found++;
                } else {
                    missing++;
                    if (!missingNames.isEmpty()) missingNames.append(", ");
                    missingNames.append(name);
                }
            } catch (Exception e) {
                missing++;
                missingNames.append(field.getName()).append("(err:").append(e.getClass().getSimpleName()).append(")");
            }
        }

        if (missing > 0) {
            return TestResult.fail(found + "/" + total + " effets enregistrés, manquants : " + missingNames);
        }
        return TestResult.pass(found + "/" + total + " effets enregistrés");
    }
}
