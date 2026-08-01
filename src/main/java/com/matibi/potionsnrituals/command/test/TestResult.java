package com.matibi.potionsnrituals.command.test;

public final class TestResult {
    private final boolean passed;
    private final String message;

    private TestResult(boolean passed, String message) {
        this.passed = passed;
        this.message = message;
    }

    public static TestResult pass() {
        return new TestResult(true, "");
    }

    public static TestResult fail(String message) {
        return new TestResult(false, message);
    }

    public boolean passed() {
        return passed;
    }

    public String message() {
        return message;
    }
}