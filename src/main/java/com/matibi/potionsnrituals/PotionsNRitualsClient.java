package com.matibi.potionsnrituals;

import com.matibi.potionsnrituals.block.ModBlocks;
import com.matibi.potionsnrituals.block.custom.cauldron.BrewingCauldronBlock;
import com.matibi.potionsnrituals.block.custom.cauldron.BrewingCauldronBlockEntity;
import com.matibi.potionsnrituals.datacomponent.ModDataComponents;
import com.matibi.potionsnrituals.effect.helper.DisorientInputHandler;
import com.matibi.potionsnrituals.keybind.KeyInputHandler;
import com.matibi.potionsnrituals.renderer.CooldownOverlayRenderer;
import com.matibi.potionsnrituals.renderer.OreSenseOverlayRenderer;
import com.matibi.potionsnrituals.util.ModItemTintSources;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockColorRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockTintsFactory;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

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
	}
}