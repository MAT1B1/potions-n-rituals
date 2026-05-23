package com.matibi.potionsnrituals.keybind;

import com.matibi.potionsnrituals.PotionsNRituals;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;

public class KeyInputHandler {
    public static KeyMapping SHOW_EFFECT_ICONS;

    public static void register() {
        KeyMapping.Category CATEGORY = KeyMapping.Category.register(Identifier.fromNamespaceAndPath(PotionsNRituals.MOD_ID, "main"));

        SHOW_EFFECT_ICONS = KeyMappingHelper.registerKeyMapping(new KeyMapping(
                "key.potions-n-rituals.show_effect_icons",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_M,
                CATEGORY
        ));
    }

    public static boolean isHeld() {
        if (SHOW_EFFECT_ICONS == null) return false;
        long window = Minecraft.getInstance().getWindow().handle();
        InputConstants.Key key = InputConstants.getKey(SHOW_EFFECT_ICONS.saveString());
        return GLFW.glfwGetKey(window, key.getValue()) == GLFW.GLFW_PRESS;
    }
}