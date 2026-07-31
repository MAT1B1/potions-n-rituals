package com.matibi.potionsnrituals.command.test.tests;

import com.matibi.potionsnrituals.command.test.TestResult;
import com.matibi.potionsnrituals.command.test.TestSuite;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.alchemy.Potion;

import java.lang.reflect.Field;

public class PotionRegistryTest implements TestSuite {

    @Override
    public String id() {
        return "potions:registry";
    }

    @Override
    public String description() {
        return "Vérifie que toutes les potions de ModPotions sont enregistrées";
    }

    @Override
    public String category() {
        return "potions";
    }

    @Override
    public TestResult run(ServerLevel level, ServerPlayer player) {
        Class<?> modPotionsClass;
        try {
            modPotionsClass = Class.forName("com.matibi.potionsnrituals.potion.ModPotions");
        } catch (ClassNotFoundException e) {
            return TestResult.error("Classe ModPotions introuvable");
        }

        int total = 0;
        int found = 0;
        int missing = 0;
        StringBuilder missingNames = new StringBuilder();

        for (Field field : modPotionsClass.getDeclaredFields()) {
            if (field.getType() != net.minecraft.core.Holder.class) continue;
            if (!java.lang.reflect.Modifier.isStatic(field.getModifiers())) continue;

            total++;
            try {
                @SuppressWarnings("unchecked")
                net.minecraft.core.Holder<Potion> holder =
                        (net.minecraft.core.Holder<Potion>) field.get(null);

                String name = holder.getRegisteredName();
                Identifier id = Identifier.parse(name);
                boolean present = BuiltInRegistries.POTION.get(id).isPresent();
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
            return TestResult.fail(found + "/" + total + " potions enregistrées, manquantes : " + missingNames);
        }
        return TestResult.pass(found + "/" + total + " potions enregistrées");
    }
}
