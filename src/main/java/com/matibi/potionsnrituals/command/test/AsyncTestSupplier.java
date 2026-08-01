package com.matibi.potionsnrituals.command.test;

import java.util.function.Consumer;

@FunctionalInterface
public interface AsyncTestSupplier {
    void run(TestContext context, Consumer<TestResult> callback);
}