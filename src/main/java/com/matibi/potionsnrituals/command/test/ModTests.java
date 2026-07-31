package com.matibi.potionsnrituals.command.test;

import com.matibi.potionsnrituals.PotionsNRituals;
import com.matibi.potionsnrituals.command.test.tests.*;

import java.util.*;

public class ModTests {

    private static final Map<String, TestSuite> TESTS = new LinkedHashMap<>();

    public static TestSuite register(TestSuite test) {
        TESTS.put(test.id(), test);
        return test;
    }

    public static Collection<TestSuite> getAll() {
        return TESTS.values();
    }

    public static TestSuite get(String id) {
        return TESTS.get(id);
    }

    public static List<TestSuite> getByCategory(String category) {
        return TESTS.values().stream()
                .filter(t -> t.category().equalsIgnoreCase(category))
                .toList();
    }

    public static List<String> getCategories() {
        return TESTS.values().stream()
                .map(TestSuite::category)
                .distinct()
                .sorted()
                .toList();
    }

    public static int count() {
        return TESTS.size();
    }

    public static void registerAll() {
        register(new EffectRegistryTest());
        register(new VampirismTest());
        register(new RustTest());
        register(new BerserkTest());
        register(new AftermathTest());
        register(new ColdTransmissionTest());
        register(new ZeusTest());
        register(new ConfigTest());
        register(new PotionRegistryTest());
        register(new DeathTest());
        register(new DoubleHealthTest());
        register(new HydrophobiaTest());
        register(new IgnitionTest());
        register(new MagnetismTest());
        register(new MidasTest());
        register(new OblivionTest());
        register(new PurificationTest());
        register(new SaturationTest());
        register(new ThornsTest());
        register(new AcidTest());
        register(new FrostTest());
        register(new ActiveEffectsTest());
        register(new ReactivationTest());
        register(new InfinityTest());
        register(new ResonanceTest());
        register(new GhostWalkTest());
        register(new UnstableTest());
        register(new ResurrectionTest());
        register(new SimpleEffectsTest());

        PotionsNRituals.LOGGER.info("Registered {} test suites", TESTS.size());
    }
}
