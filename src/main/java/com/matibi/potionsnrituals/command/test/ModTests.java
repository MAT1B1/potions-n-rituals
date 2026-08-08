package com.matibi.potionsnrituals.command.test;

import com.matibi.potionsnrituals.PotionsNRituals;
import com.matibi.potionsnrituals.command.test.tests.CoreTests;
import com.matibi.potionsnrituals.command.test.tests.EffectTests;
import com.matibi.potionsnrituals.command.test.tests.ItemTests;
import com.matibi.potionsnrituals.command.test.tests.RitualTests;

public final class ModTests {

    private ModTests() {}

    public static void registerAll() {
        PotionsNRituals.LOGGER.info("Registering tests...");

        Class<?>[] testClasses = {
            CoreTests.class,
            EffectTests.class,
            ItemTests.class,
            RitualTests.class
        };

        for (Class<?> clazz : testClasses) {
            try {
                Class.forName(clazz.getName());
                PotionsNRituals.LOGGER.debug("Loaded test class: {}", clazz.getSimpleName());
            } catch (ClassNotFoundException e) {
                PotionsNRituals.LOGGER.warn("Test class not found: {}", clazz.getName());
            }
        }

        PotionsNRituals.LOGGER.info("Registered {} test(s)", TestRegistry.getAll().size());
    }
}