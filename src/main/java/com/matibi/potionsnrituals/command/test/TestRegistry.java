package com.matibi.potionsnrituals.command.test;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public final class TestRegistry {
    private static final Map<String, TestEntry> TESTS = new LinkedHashMap<>();

    private TestRegistry() {}

    public static void register(String name, TestSupplier supplier) {
        if (TESTS.containsKey(name)) {
            throw new IllegalStateException("Test already registered: " + name);
        }
        TESTS.put(name, new TestEntry(name, supplier, null));
    }

    public static void registerAsync(String name, AsyncTestSupplier supplier) {
        if (TESTS.containsKey(name)) {
            throw new IllegalStateException("Test already registered: " + name);
        }
        TESTS.put(name, new TestEntry(name, null, supplier));
    }

    public static Collection<TestEntry> getAll() {
        return TESTS.values();
    }

    public static Optional<TestEntry> get(String name) {
        return Optional.ofNullable(TESTS.get(name));
    }

    public static void clear() {
        TESTS.clear();
    }
}