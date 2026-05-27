package com.matibi.potionsnrituals.renderer;

import com.matibi.potionsnrituals.PotionsNRituals;
import com.matibi.potionsnrituals.config.ModConfig;
import com.matibi.potionsnrituals.network.OreSensePayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

public class OreSenseOverlayRenderer {
    // Ore dans le champ de vision → haut/bas
    private static float targetInViewIntensity  = 0.0f;
    private static float currentInViewIntensity = 0.0f;
    private static int   targetInViewColor      = 0xFFFFFF;
    private static int   currentInViewColor     = 0xFFFFFF;

    // Ore hors du champ de vision → gauche/droite
    private static float targetOffViewIntensity  = 0.0f;
    private static float currentOffViewIntensity = 0.0f;
    private static int   targetOffViewColor      = 0xFFFFFF;
    private static int   currentOffViewColor     = 0xFFFFFF;
    private static float targetAngle             = 0.0f;
    private static float currentAngle            = 0.0f;

    public static void register() {
        ClientPlayNetworking.registerGlobalReceiver(OreSensePayload.TYPE, (payload, _) -> {
            targetInViewIntensity = payload.inViewIntensity();
            if (payload.inViewIntensity() > 0.01f)
                targetInViewColor = payload.inViewColor();

            targetOffViewIntensity = payload.offViewIntensity();
            if (payload.offViewIntensity() > 0.01f) {
                targetOffViewColor = payload.offViewColor();
                targetAngle = payload.offViewAngle();
            }
        });

        HudElementRegistry.attachElementBefore(
                VanillaHudElements.MISC_OVERLAYS,
                Identifier.fromNamespaceAndPath(PotionsNRituals.MOD_ID, "ore_sense_overlay"),
                OreSenseOverlayRenderer::render
        );
    }

    private static void render(GuiGraphicsExtractor graphics, DeltaTracker delta) {
        currentInViewIntensity  = Mth.lerp(ModConfig.get().lerp_speed, currentInViewIntensity,  targetInViewIntensity);
        currentOffViewIntensity = Mth.lerp(ModConfig.get().lerp_speed, currentOffViewIntensity, targetOffViewIntensity);
        currentAngle = lerpAngle(currentAngle, targetAngle, 0.1f);

        if (currentInViewIntensity < 0.01f && currentOffViewIntensity < 0.01f) return;

        Minecraft mc = Minecraft.getInstance();
        int w = mc.getWindow().getGuiScaledWidth();
        int h = mc.getWindow().getGuiScaledHeight();
        int thick = (int)(Math.min(w, h) * ModConfig.get().thick);

        // ── Haut / Bas : ore dans le champ de vision ──────────────────────
        if (currentInViewIntensity > 0.01f) {
            currentInViewColor = lerpColor(currentInViewColor, targetInViewColor, 0.05f);
            float alpha = Mth.clamp(currentInViewIntensity * ModConfig.get().alpha_max, 0.0f, ModConfig.get().alpha_max);
            int opaque = (currentInViewColor & 0x00FFFFFF) | ((int)(alpha * 255) << 24);
            int transp = currentInViewColor & 0x00FFFFFF;

            graphics.fillGradient(0, 0, w, thick, opaque, transp);
            graphics.fillGradient(0, h - thick, w, h, transp, opaque);
        }

        // ── Gauche / Droite : ore hors du champ de vision ─────────────────
        if (currentOffViewIntensity > 0.01f) {
            currentOffViewColor = lerpColor(currentOffViewColor, targetOffViewColor, 0.05f);
            float alpha = Mth.clamp(currentOffViewIntensity * ModConfig.get().alpha_max, 0.0f, ModConfig.get().alpha_max);
            int transp = currentOffViewColor & 0x00FFFFFF;

            // sin(angle) > 0 → ore à droite, < 0 → ore à gauche
            // cos(angle) < 0 → ore derrière → les deux côtés s'allument
            float rightStrength = (float) Math.max(0, Math.sin(currentAngle));
            float leftStrength  = (float) Math.max(0, -Math.sin(currentAngle));
            float behind = (float) Math.max(0, -Math.cos(currentAngle)) * 0.5f;
            rightStrength = Math.min(1, rightStrength + behind);
            leftStrength  = Math.min(1, leftStrength  + behind);

            if (leftStrength > 0.01f) {
                int opaqueL = (currentOffViewColor & 0x00FFFFFF) | ((int)(alpha * leftStrength * 255) << 24);
                drawHorizontalGradient(graphics, 0, thick, h, transp, opaqueL);
            }
            if (rightStrength > 0.01f) {
                int opaqueR = (currentOffViewColor & 0x00FFFFFF) | ((int)(alpha * rightStrength * 255) << 24);
                drawHorizontalGradient(graphics, w - thick, thick, h, opaqueR, transp);
            }
        }
    }

    private static int lerpColor(int a, int b, float t) {
        int ar = (a >> 16) & 0xFF, ag = (a >> 8) & 0xFF, ab = a & 0xFF;
        int br = (b >> 16) & 0xFF, bg = (b >> 8) & 0xFF, bb = b & 0xFF;
        int r  = (int)(ar + (br - ar) * t);
        int g  = (int)(ag + (bg - ag) * t);
        int bl = (int)(ab + (bb - ab) * t);
        return (r << 16) | (g << 8) | bl;
    }

    private static float lerpAngle(float current, float target, float t) {
        float diff = target - current;
        while (diff >  Math.PI) diff -= (float) (2 * Math.PI);
        while (diff < -Math.PI) diff += (float) (2 * Math.PI);
        return current + diff * t;
    }

    private static void drawHorizontalGradient(
            GuiGraphicsExtractor graphics, int x, int thick, int h, int left, int right) {
        graphics.pose().pushMatrix();
        graphics.pose().translate(x + thick / 2f, h / 2f);
        graphics.pose().rotate((float)(Math.PI / 2));
        graphics.fillGradient(-h / 2, -thick / 2, h / 2, thick / 2, left, right);
        graphics.pose().popMatrix();
    }
}