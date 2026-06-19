package com.matibi.potionsnrituals.util;

import com.matibi.potionsnrituals.util.ModUtils;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

public class AttributeUtils {
    private static final Identifier HEALTH_BONUS_ID = ModUtils.id("max_health_bonus");
    private static final Identifier STRENGTH_BONUS_ID = ModUtils.id("strength_bonus");
    private static final Identifier SPEED_BONUS_ID = ModUtils.id("speed_bonus");

    public static void changeHealthBy(Player player, double delta) {
        var attr = player.getAttribute(Attributes.MAX_HEALTH);
        if (attr == null) return;

        var existing = attr.getModifier(HEALTH_BONUS_ID);
        double newAmount = delta;

        if (existing != null) {
            newAmount += existing.amount();
            attr.removeModifier(HEALTH_BONUS_ID);
        }

        attr.addPermanentModifier(new AttributeModifier(
                HEALTH_BONUS_ID,
                newAmount,
                AttributeModifier.Operation.ADD_VALUE
        ));

        if (player.getHealth() > player.getMaxHealth())
            player.setHealth(player.getMaxHealth());
    }

    public static void changeStrengthBy(Player player, double delta) {
        var attr = player.getAttribute(Attributes.ATTACK_DAMAGE);
        if (attr == null) return;

        var existing = attr.getModifier(STRENGTH_BONUS_ID);
        double newAmount = delta;

        if (existing != null) {
            newAmount += existing.amount();
            attr.removeModifier(STRENGTH_BONUS_ID);
        }

        attr.addPermanentModifier(new AttributeModifier(
                STRENGTH_BONUS_ID,
                newAmount,
                AttributeModifier.Operation.ADD_VALUE
        ));
    }

    public static void changeSpeedBy(Player player, double delta) {
        var attr = player.getAttribute(Attributes.MOVEMENT_SPEED);
        if (attr == null) return;

        var existing = attr.getModifier(SPEED_BONUS_ID);
        double newAmount = delta;

        if (existing != null) {
            newAmount += existing.amount();
            attr.removeModifier(SPEED_BONUS_ID);
        }

        attr.addPermanentModifier(new AttributeModifier(
                SPEED_BONUS_ID,
                newAmount,
                AttributeModifier.Operation.ADD_VALUE
        ));
    }
}