package com.matibi.potionsnrituals.keybind;

import com.matibi.potionsnrituals.effect.ModEffects;
import com.matibi.potionsnrituals.effect.helper.ActiveEffectPayload;
import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.phys.EntityHitResult;
import org.lwjgl.glfw.GLFW;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;

import static com.matibi.potionsnrituals.PotionsNRituals.MOD_ID;

public class KeyInputHandler {
    public static KeyMapping SHOW_EFFECT_ICONS;
    public static KeyMapping EFFECT_BUTTON;

    public static void register() {
        KeyMapping.Category CATEGORY = KeyMapping.Category.register(Identifier.fromNamespaceAndPath(MOD_ID, "main"));

        SHOW_EFFECT_ICONS = KeyMappingHelper.registerKeyMapping(new KeyMapping(
                "key.potions-n-rituals.show_effect_icons",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_M,
                CATEGORY
        ));

        EFFECT_BUTTON = KeyMappingHelper.registerKeyMapping(new KeyMapping(
                "key.potions-n-rituals.effect_button",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_LEFT_ALT,
                CATEGORY
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (EFFECT_BUTTON.consumeClick()) {
                if (client.hitResult instanceof EntityHitResult hitResult
                        && ClientPlayNetworking.canSend(ActiveEffectPayload.TYPE)) {

                    LocalPlayer player = client.player;
                    String effectType = null;

                    if (player == null) return;
                    if (player.hasEffect(ModEffects.LOVE)) effectType = "love";

                    if (effectType != null) {
                        ClientPlayNetworking.send(new ActiveEffectPayload(
                                hitResult.getEntity().getId(), effectType
                        ));
                    }
                }
            }
        });
    }

    public static boolean isHeld() {
        if (SHOW_EFFECT_ICONS == null) return false;
        long window = Minecraft.getInstance().getWindow().handle();
        InputConstants.Key key = InputConstants.getKey(SHOW_EFFECT_ICONS.saveString());
        return GLFW.glfwGetKey(window, key.getValue()) == GLFW.GLFW_PRESS;
    }
}
