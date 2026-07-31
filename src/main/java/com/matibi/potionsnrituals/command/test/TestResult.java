package com.matibi.potionsnrituals.command.test;

import net.minecraft.network.chat.Component;

public record TestResult(TestStatus status, Component message) {

    private static final Component PREFIX = Component.literal("§7[§6PNR§7]§r ");
    private static final Component PASS_PREFIX = PREFIX.copy().append(Component.literal("§a✓ "));
    private static final Component FAIL_PREFIX = PREFIX.copy().append(Component.literal("§c✗ "));
    private static final Component ERROR_PREFIX = PREFIX.copy().append(Component.literal("§e⚠ "));
    private static final Component SKIP_PREFIX = PREFIX.copy().append(Component.literal("§8- "));
    private static final Component PEND_PREFIX = PREFIX.copy().append(Component.literal("§8⏳ "));

    public Component formatted() {
        Component prefix = switch (status) {
            case PASS -> PASS_PREFIX;
            case FAIL -> FAIL_PREFIX;
            case ERROR -> ERROR_PREFIX;
            case SKIPPED -> SKIP_PREFIX;
            case PENDING -> PEND_PREFIX;
        };
        return prefix.copy().append(message);
    }

    public static TestResult pass(Component message) {
        return new TestResult(TestStatus.PASS, message);
    }

    public static TestResult pass(String message) {
        return pass(Component.literal(message));
    }

    public static TestResult fail(Component message) {
        return new TestResult(TestStatus.FAIL, message);
    }

    public static TestResult fail(String message) {
        return fail(Component.literal(message));
    }

    public static TestResult error(Component message) {
        return new TestResult(TestStatus.ERROR, message);
    }

    public static TestResult error(String message) {
        return error(Component.literal(message));
    }

    public static TestResult skipped(Component message) {
        return new TestResult(TestStatus.SKIPPED, message);
    }

    public static TestResult pending(Component message) {
        return new TestResult(TestStatus.PENDING, message);
    }

    public static TestResult pending(String message) {
        return pending(Component.literal(message));
    }
}
