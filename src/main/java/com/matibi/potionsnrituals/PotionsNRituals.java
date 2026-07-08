package com.matibi.potionsnrituals;

import com.matibi.potionsnrituals.block.ModBlockEntities;
import com.matibi.potionsnrituals.block.ModBlocks;
import com.matibi.potionsnrituals.config.ModConfig;
import com.matibi.potionsnrituals.datacomponent.ModDataComponents;
import com.matibi.potionsnrituals.effect.ModEffects;
import com.matibi.potionsnrituals.effect.custom.BerserkEffect;
import com.matibi.potionsnrituals.effect.helper.ActiveEffectHandler;
import com.matibi.potionsnrituals.effect.custom.brainwashing.DisorientMobHandler;
import com.matibi.potionsnrituals.effect.custom.brainwashing.DisorientVillagerHandler;
import com.matibi.potionsnrituals.effect.custom.terrain.ResurrectionEffect;
import com.matibi.potionsnrituals.entity.ModEntities;
import com.matibi.potionsnrituals.group.ModItemGroups;
import com.matibi.potionsnrituals.item.ModItems;
import com.matibi.potionsnrituals.item.custom.TalismanItem;
import com.matibi.potionsnrituals.item.custom.alchemicalStone.ModAlchemicalStone;
import com.matibi.potionsnrituals.network.ModNetworking;
import com.matibi.potionsnrituals.potion.ModPotions;
import com.matibi.potionsnrituals.recipe.ModRecipeSerializer;
import com.matibi.potionsnrituals.ritual.RitualManager;
import com.matibi.potionsnrituals.util.ModUtils;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.minecraft.server.packs.PackType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PotionsNRituals implements ModInitializer {
	public static final String MOD_ID = "potions-n-rituals";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final String YACL_ID = "yet_another_config_lib_v3";

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
		ModNetworking.register();
		ModPotions.register();
		ModEffects.register();
		ModItems.register();
		ModAlchemicalStone.register();
		ModRecipeSerializer.register();
		ModItemGroups.register();
		ModDataComponents.register();
		ModConfig.register();
		ModBlocks.register();
		ModBlockEntities.register();
		ModEntities.register();

		ResourceLoader.get(PackType.SERVER_DATA)
				.registerReloadListener(
						ModUtils.id("rituals_manager"),
						new RitualManager()
				);

		TalismanItem.registerEvents();
		ResurrectionEffect.registerDeathHandler();
		BerserkEffect.registerDeathHandler();
		DisorientMobHandler.register();
		DisorientVillagerHandler.register();

		ActiveEffectHandler.register();
	}
}
