package com.matibi.potionsnrituals.effect;

import com.matibi.potionsnrituals.effect.ModEffects;
import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Options;

public class DisorientInputHandler {

    private static InputConstants.Key origUp, origDown, origLeft, origRight;
    private static boolean scrambled = false;

    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;
            boolean hasEffect = client.player.hasEffect(ModEffects.BRAINWASHING);

            if (hasEffect && !scrambled) {
                saveAndScramble(client.options);
            } else if (!hasEffect && scrambled) {
                restore(client.options);
            }
        });
    }

    private static void saveAndScramble(Options opts) {
        origUp    = opts.keyUp.getDefaultKey();
        origDown  = opts.keyDown.getDefaultKey();
        origLeft  = opts.keyLeft.getDefaultKey();
        origRight = opts.keyRight.getDefaultKey();

        opts.keyUp.setKey(origDown);
        opts.keyDown.setKey(origUp);
        opts.keyLeft.setKey(origRight);
        opts.keyRight.setKey(origLeft);

        scrambled = true;
    }

    private static void restore(Options opts) {
        opts.keyUp.setKey(origUp);
        opts.keyDown.setKey(origDown);
        opts.keyLeft.setKey(origLeft);
        opts.keyRight.setKey(origRight);
        scrambled = false;
    }
}
