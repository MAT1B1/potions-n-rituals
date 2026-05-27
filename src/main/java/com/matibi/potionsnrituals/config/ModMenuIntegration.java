package com.matibi.potionsnrituals.config;

import com.matibi.potionsnrituals.PotionsNRituals;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.loader.api.FabricLoader;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        if (FabricLoader.getInstance().isModLoaded(PotionsNRituals.YACL_ID))
            return ModConfigScreen::create;
        return _ -> null;
    }
}