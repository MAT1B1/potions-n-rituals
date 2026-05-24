package com.matibi.potionsnrituals;

import com.matibi.potionsnrituals.effect.helper.DisorientInputHandler;
import com.matibi.potionsnrituals.keybind.KeyInputHandler;
import com.matibi.potionsnrituals.renderer.OreSenseOverlayRenderer;
import com.matibi.potionsnrituals.util.ModItemTintSources;
import net.fabricmc.api.ClientModInitializer;

public class PotionsNRitualsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		OreSenseOverlayRenderer.register();
		DisorientInputHandler.register();
		KeyInputHandler.register();
		ModItemTintSources.register();
	}
}