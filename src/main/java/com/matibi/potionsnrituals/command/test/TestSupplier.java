package com.matibi.potionsnrituals.command.test;

@FunctionalInterface
public interface TestSupplier {
    TestResult run(TestContext context);
}