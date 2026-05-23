package com.matibi.potionsnrituals;

import com.matibi.potionsnrituals.config.ModConfig;
import com.matibi.potionsnrituals.datacomponent.ModDataComponents;
import com.matibi.potionsnrituals.effect.ModEffects;
import com.matibi.potionsnrituals.effect.custom.BerserkEffect;
import com.matibi.potionsnrituals.effect.custom.brainwashing.DisorientMobHandler;
import com.matibi.potionsnrituals.effect.custom.brainwashing.DisorientVillagerHandler;
import com.matibi.potionsnrituals.effect.custom.terrain.ResurrectionEffect;
import com.matibi.potionsnrituals.group.ModItemGroups;
import com.matibi.potionsnrituals.item.ModItems;
import com.matibi.potionsnrituals.item.alchemicalStone.ModAlchemicalStone;
import com.matibi.potionsnrituals.network.OreSensePayload;
import com.matibi.potionsnrituals.potion.ModPotions;
import com.matibi.potionsnrituals.recipe.ModRecipeSerializer;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PotionsNRituals implements ModInitializer {
	public static final String MOD_ID = "potions-n-rituals";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
		//ModNetworking.init();
		ModPotions.register();
		ModEffects.register();
		ModItems.register();
		ModAlchemicalStone.register();
		ModRecipeSerializer.register();
		ModItemGroups.register();
		ModDataComponents.register();
		ModConfig.register();
		/*ModLootTable.register();
		ModEvent.register();
		ModBlocks.register();
		ModBlockEntities.register();
		ModScreenHandlers.register();
		ModRituals.register();*/

		ResurrectionEffect.registerDeathHandler();
		BerserkEffect.registerDeathHandler();
		DisorientMobHandler.register();
		DisorientVillagerHandler.register();

		PayloadTypeRegistry.clientboundPlay().register(OreSensePayload.TYPE, OreSensePayload.CODEC);

	}
}