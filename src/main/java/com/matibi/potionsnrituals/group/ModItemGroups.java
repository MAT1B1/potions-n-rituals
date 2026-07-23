package com.matibi.potionsnrituals.group;

import com.matibi.potionsnrituals.PotionsNRituals;
import com.matibi.potionsnrituals.block.ModBlocks;
import com.matibi.potionsnrituals.item.ModItems;
import com.matibi.potionsnrituals.item.custom.alchemicalStone.AlchemicalStone;
import com.matibi.potionsnrituals.item.custom.alchemicalStone.ModAlchemicalStone;
import com.matibi.potionsnrituals.util.ModUtils;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.matibi.potionsnrituals.config.ModConfig;

public class ModItemGroups {
    public static final ResourceKey<CreativeModeTab> ALCHEMY =
            ResourceKey.create(Registries.CREATIVE_MODE_TAB, ModUtils.id("alchemy"));

    public static final CreativeModeTab ALCHEMY_GROUP = Registry.register(
            BuiltInRegistries.CREATIVE_MODE_TAB,
            ModUtils.id("alchemy"),
            FabricCreativeModeTab.builder()
                    .icon(() -> new ItemStack(Items.POTION))
                    .title(Component.translatable("itemGroup.potions-n-rituals.alchemy"))
                    .displayItems((_, entries) -> {
                        var SKIP = Set.of(
                                Potions.WATER.value(), Potions.AWKWARD.value(), Potions.THICK.value(), Potions.MUNDANE.value()
                        );
                        List<Holder<Potion>> all = BuiltInRegistries.POTION.entrySet().stream()
                                .map(e -> BuiltInRegistries.POTION.wrapAsHolder(e.getValue()))
                                .filter(e -> !SKIP.contains(e.value()))
                                .filter(e -> !ModConfig.get().isPotionBlacklisted(e))
                                .sorted(Comparator
                                        .<Holder<Potion>, String>comparing(e -> {
                                            var effects = e.value().getEffects();
                                            if (effects.isEmpty()) return "zzz";
                                            return Objects.requireNonNull(BuiltInRegistries.MOB_EFFECT
                                                            .getKey(effects.getFirst().getEffect().value()))
                                                    .toString();
                                        })
                                        .thenComparingInt(e -> {
                                            var effects = e.value().getEffects();
                                            return effects.isEmpty() ? 0 : effects.getFirst().getAmplifier();
                                        })
                                        .thenComparingInt(e -> {
                                            var effects = e.value().getEffects();
                                            return effects.isEmpty() ? 0 : effects.getFirst().getDuration();
                                        })
                                )
                                .toList();

                        entries.accept(ModItems.BASIC_GUIDE);
                        entries.accept(ModItems.NIGREDO_GUIDE);
                        entries.accept(ModItems.ALBEDO_GUIDE);
                        entries.accept(ModItems.CITRINITAS_GUIDE);
                        entries.accept(ModItems.RUBEDO_GUIDE);
                        entries.accept(ModItems.MATERIA_PRIMA);
                        entries.accept(ModItems.SULFUR_BALL);
                        entries.accept(ModItems.MERCURY_BALL);
                        entries.accept(ModItems.TALISMAN);
                        entries.accept(ModItems.TALISMAN_CHARGED);
                        entries.accept(ModItems.ALCHEMICAL_BAG);
                        entries.accept(ModItems.NETHER_SEAL_BREAKER);
                        entries.accept(ModItems.SPIRIT_MIRROR);
                        entries.accept(ModItems.DECOY);
                        entries.accept(ModBlocks.PEDESTAL);
                        entries.accept(ModItems.SALT);

                        entries.accept(ModItems.SYRINGE);
                        entries.accept(ModItems.BLOOD_BAG);

                        entries.accept(Items.GLASS_BOTTLE);
                        addPotionType(Items.POTION, entries, all);
                        addPotionType(Items.SPLASH_POTION, entries, all);
                        addPotionType(Items.LINGERING_POTION, entries, all);

                        entries.accept(ModItems.ALCHEMICAL_STONE);
                        ModAlchemicalStone.ALCHEMICAL_STONE_REGISTRY.asHolderIdMap().forEach( entry -> {
                            ItemStack stack = AlchemicalStone.getItemStack(entry);
                            if (!stack.isEmpty() && !ModConfig.get().isStoneBlacklisted(entry))
                                entries.accept(stack);
                        });
                    })
                    .build()
    );

    public static void register() {
        PotionsNRituals.LOGGER.info("Registering mod groups for " + PotionsNRituals.MOD_ID);
    }

    public static void addPotionType(Item item, CreativeModeTab.Output entries, List<Holder<Potion>> all) {
        entries.accept(PotionContents.createItemStack(item, Potions.WATER));
        entries.accept(PotionContents.createItemStack(item, Potions.AWKWARD));
        entries.accept(PotionContents.createItemStack(item, Potions.MUNDANE));
        entries.accept(PotionContents.createItemStack(item, Potions.THICK));
        for (Holder<Potion> potion : all)
            entries.accept(PotionContents.createItemStack(item, potion));
    }
}