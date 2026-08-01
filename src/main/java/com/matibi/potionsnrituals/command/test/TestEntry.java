package com.matibi.potionsnrituals.command.test;

public record TestEntry(
    String name,
    TestSupplier sync,
    AsyncTestSupplier async
) {
    public boolean isAsync() {
        return async != null;
    }
}