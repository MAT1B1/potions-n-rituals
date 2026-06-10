package com.matibi.potionsnrituals.util;

import com.matibi.potionsnrituals.PotionsNRituals;
import net.minecraft.resources.Identifier;

public class ModUtils {
    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(PotionsNRituals.MOD_ID, path);
    }
}
