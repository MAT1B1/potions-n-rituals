package com.matibi.potionsnrituals.util;

import java.util.Map;
import net.minecraft.world.entity.player.Player;

public class CommandPricing {

    private static final Map<String, Float> COMMAND_COSTS = Map.<String, Float>ofEntries(
            // --- COTE MAXIMUM (1.0F) ---
            Map.entry("gamemode", 1.0F),
            Map.entry("op", 1.0F),
            Map.entry("deop", 1.0F),
            Map.entry("ban", 1.0F),
            Map.entry("kick", 1.0F),
            Map.entry("stop", 1.0F),
            Map.entry("data", 1.0F),
            Map.entry("debug", 1.0F),
            Map.entry("reload", 1.0F),
            Map.entry("locate", 1.0F),
            Map.entry("loot", 1.0F),

            Map.entry("kill", 0.9F),
            Map.entry("damage", 0.7F),

            Map.entry("tp", 0.5F),
            Map.entry("teleport", 0.5F),
            Map.entry("enchant", 0.5F),
            Map.entry("experience", 0.5F),
            Map.entry("xp", 0.5F),
            Map.entry("attribute", 0.5F),
            Map.entry("give", 0.5F),
            Map.entry("fill", 0.5F),
            Map.entry("clone", 0.5F),
            Map.entry("setblock", 0.5F),
            Map.entry("summon", 0.5F),
            Map.entry("effect", 0.3F),

            Map.entry("ride", 0.1F),
            Map.entry("advancement", 0.1F),
            Map.entry("recipe", 0.1F),
            Map.entry("clear", 0.1F),
            Map.entry("item", 0.1F),

            Map.entry("spectate", 0.0F),
            Map.entry("trigger", 0.0F),
            Map.entry("ping", 0.0F),
            Map.entry("perf", 0.0F),
            Map.entry("datapack", 0.0F),
            Map.entry("defaultgamemode", 0.0F),
            Map.entry("msg", 0.0F),
            Map.entry("tell", 0.0F),
            Map.entry("w", 0.0F),
            Map.entry("me", 0.0F),
            Map.entry("list", 0.0F),
            Map.entry("team", 0.0F),
            Map.entry("teammsg", 0.0F),
            Map.entry("help", 0.0F),
            Map.entry("say", 0.0F),
            Map.entry("particle", 0.0F),
            Map.entry("playsound", 0.0F),
            Map.entry("stopsound", 0.0F),
            Map.entry("title", 0.0F),
            Map.entry("tellraw", 0.0F),
            Map.entry("bossbar", 0.0F),
            Map.entry("camera", 0.0F),
            Map.entry("seed", 0.0F)
    );

    public static float cost(Player player, String base, String command) {
        float multiplier = getCommandMultiplier(base, command);
        return player.getMaxHealth() * multiplier;
    }

    private static float getCommandMultiplier(String base, String fullCommand) {
        if (base.equals("execute")) {
            int runIndex = fullCommand.indexOf(" run ");
            if (runIndex != -1) {
                String subCommand = fullCommand.substring(runIndex + 5).trim();
                if (!subCommand.isEmpty()) {
                    String subBase = subCommand.split(" ")[0];
                    float calculatedCost = getCommandMultiplier(subBase, subCommand) + 0.1F;
                    return Math.min(1.0F, calculatedCost);
                }
            }
            return 1.0F;
        }
        float baseCost = COMMAND_COSTS.getOrDefault(base, 0.5F);
        float generationTax = calculateGenerationTax(base, fullCommand);
        return baseCost + generationTax;
    }

    private static float calculateGenerationTax(String base, String fullCommand) {
        String[] parts = fullCommand.trim().split("\\s+");

        if (base.equals("give")) {

            int count = 1;

            if (parts.length >= 4)
                try {
                    count = Integer.parseInt(parts[3]);
                } catch (NumberFormatException _) {}

            int stacks = (int) Math.floor(count / 64.0);
            return 0.1F * stacks;
        }
        return 0.0F;
    }
}
