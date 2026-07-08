package com.matibi.potionsnrituals;

import com.matibi.potionsnrituals.block.ModBlockEntities;
import com.matibi.potionsnrituals.block.custom.cauldron.BrewingCauldronBlock;
import com.matibi.potionsnrituals.block.custom.pedestal.PedestalBlockEntityRenderer;
import com.matibi.potionsnrituals.datacomponent.ModDataComponents;
import com.matibi.potionsnrituals.effect.helper.DisorientInputHandler;
import com.matibi.potionsnrituals.entity.ModEntities;
import com.matibi.potionsnrituals.keybind.KeyInputHandler;
import com.matibi.potionsnrituals.renderer.CooldownOverlayRenderer;
import com.matibi.potionsnrituals.renderer.OreSenseOverlayRenderer;
import com.matibi.potionsnrituals.util.ModItemTintSources;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.NoopRenderer;

public class PotionsNRitualsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		OreSenseOverlayRenderer.register();
		CooldownOverlayRenderer.register();
		DisorientInputHandler.register();
		KeyInputHandler.register();
		ModItemTintSources.register();
		ModDataComponents.registerTooltips();

		BrewingCauldronBlock.registerTint();
		EntityRenderers.register(ModEntities.RITUAL_CONTROLLER, NoopRenderer::new);
		BlockEntityRenderers.register(ModBlockEntities.PEDESTAL, PedestalBlockEntityRenderer::new);
	}
}
