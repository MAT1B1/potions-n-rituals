package com.matibi.potionsnrituals.renderer;

import com.matibi.potionsnrituals.effect.helper.CooldownSyncPayload;
import com.matibi.potionsnrituals.util.ModUtils;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.resources.Identifier;

import java.util.HashMap;
import java.util.Map;

public class CooldownOverlayRenderer {
    private record CooldownInfo(long endTick, int totalTicks, Identifier effectId) {}

    private static final Map<Identifier, CooldownInfo> COOLDOWNS = new HashMap<>();

    public static void register() {
        ClientPlayNetworking.registerGlobalReceiver(CooldownSyncPayload.TYPE, (payload, _) -> {
            Identifier effectId = payload.effectId();
            int ticks = payload.cooldownTicks();
            Minecraft mc = Minecraft.getInstance();
            if (mc.level != null) {
                long endTick = mc.level.getGameTime() + ticks;
                COOLDOWNS.put(effectId, new CooldownInfo(endTick, ticks, effectId));
            }
        });

        HudElementRegistry.attachElementBefore(
                VanillaHudElements.MISC_OVERLAYS,
                ModUtils.id("cooldown_overlay"),
                CooldownOverlayRenderer::render
        );
    }

    private static void render(GuiGraphicsExtractor graphics, DeltaTracker delta) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null || mc.player == null) return;

        long currentTick = mc.level.getGameTime();
        COOLDOWNS.values().removeIf(info -> currentTick >= info.endTick());
        if (COOLDOWNS.isEmpty()) return;

        CooldownInfo info = COOLDOWNS.values().iterator().next();
        long remainingTicks = info.endTick() - currentTick;
        if (remainingTicks <= 0) return;

        float remainingSeconds = remainingTicks / 20.0f;
        float progress = (float) remainingTicks / info.totalTicks();

        int w = mc.getWindow().getGuiScaledWidth();
        int h = mc.getWindow().getGuiScaledHeight();

        int centerX = w / 2;
        int barY = h / 2 + 12;
        int barWidth = 30;
        int barHeight = 2;

        graphics.fill(centerX - barWidth / 2, barY, centerX + barWidth / 2, barY + barHeight, 0x60FFFFFF);
        graphics.fill(centerX - barWidth / 2, barY, centerX - barWidth / 2 + (int) (barWidth * progress), barY + barHeight, 0xFFFFFFFF);

        String timeText = String.format("%.1fs", remainingSeconds);
        int textX = centerX + barWidth / 2 + 3;
        int textY = barY - 1;
        graphics.text(mc.font, timeText, textX, textY, 0xFFFFFF);
    }
}
