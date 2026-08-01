package com.matibi.potionsnrituals.command.test;

import com.matibi.potionsnrituals.PotionsNRituals;

public final class ModTests {

    private ModTests() {}

    public static void registerAll() {
        PotionsNRituals.LOGGER.info("Registering tests...");

        Class<?>[] testClasses = {
            com.matibi.potionsnrituals.command.test.tests.CoreTests.class,
            com.matibi.potionsnrituals.command.test.tests.EffectTests.class,
            com.matibi.potionsnrituals.command.test.tests.ItemTests.class,
            com.matibi.potionsnrituals.command.test.tests.PotionTests.class,
            com.matibi.potionsnrituals.command.test.tests.RitualTests.class
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