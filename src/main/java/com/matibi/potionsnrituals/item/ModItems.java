package com.matibi.potionsnrituals.item;

import com.matibi.potionsnrituals.PotionsNRituals;
import com.matibi.potionsnrituals.book.BookPage;
import com.matibi.potionsnrituals.book.BookStructure;
import com.matibi.potionsnrituals.effect.ModEffects;
import com.matibi.potionsnrituals.item.alchemicalStone.AlchemicalStoneItem;
import com.matibi.potionsnrituals.item.syringe.SyringeItem;
import com.matibi.potionsnrituals.potion.ModPotions;
import com.matibi.potionsnrituals.util.BookUtils;
import com.matibi.potionsnrituals.util.ModUtils;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import org.jspecify.annotations.NonNull;

public class ModItems {

    public static final Item
            MATERIA_PRIMA = registerFoil("materia_prima", Rarity.UNCOMMON),
            MERCURY_BALL = registerSimple("mercury_ball"),
            SULFUR_BALL = registerSimple("sulfur_ball"),
            SALT = registerSimple("salt"),
            CLAW = registerSimple("claw"),
            BLOOD_BAG = registerSimple("blood_bag"),
            CHARGED_COPPER = registerSimple("charged_copper"),
            OXYDATION = registerSimple("oxydation"),
            ZOMBIE_BRAIN = registerFoodWithEffect(
                "zombie_brain", 1, 0.1f, true,
                new MobEffectInstance(ModEffects.BRAINWASHING, 200, 0), 0.5f
            ),
            WITCH_S_FINGER =
                registerFood("witch_finger", 1, 0.1f, false),
            POISONOUS_CARROT = registerFoodWithEffect(
                "poisonous_carrot", 1, 0.2f, true,
                new MobEffectInstance(MobEffects.POISON, 120, 1), 0.6f
            ),
            POISONOUS_BEETROOT = registerFoodWithEffect(
                "poisonous_beetroot", 1, 0.2f, true,
                new MobEffectInstance(MobEffects.POISON, 120, 1), 0.6f
            ),
            SYRINGE = register("syringe",
                new SyringeItem(props("syringe")
                    .stacksTo(16)
                    .component(DataComponents.POTION_CONTENTS, PotionContents.EMPTY)
                )
            ),
            ALCHEMICAL_STONE = register("alchemical_stone",
                new AlchemicalStoneItem(props("alchemical_stone")
                    .stacksTo(16)
                    .component(DataComponents.POTION_CONTENTS, PotionContents.EMPTY)
                )
            ),
            ALCHEMICAL_TOME = register("alchemical_tome",
                new CustomBookItem(props("alchemical_tome").stacksTo(1), () ->
                    new BookStructure(Component.translatable("item.potions-n-rituals.alchemical_tome"))
                        .tableOfContents("Sommaire Alchimique")

                        .page(new BookPage.EmptyPage())
                        .page(BookUtils.createCraftingPage(
                                "materia_prima_craft",
                                "Materia Prima",
                                ModItems.MATERIA_PRIMA,
                                "Obtenue automatiquement depuis les registres de recettes du jeu."
                        ))
                        .page(BookUtils.createFurnacePage(
                                "mercury_ball_smelt",
                                "Materia Prima",
                                ModItems.MERCURY_BALL,
                                "Obtenue automatiquement depuis les registres de recettes du jeu."
                        ))
                        .chapter("Alchimie", c -> c
                            .page(BookUtils.createPotionPage(ModPotions.ADHESION, "The §4Great Work§r of Alchemy begins with §5Nigredo§r."))

                            .subChapter("Avancée", sub -> sub
                                .page(BookUtils.createStandardPage("albedo_page", "§oAlbedo", "Reduce matter to its primordial form — Materia Prima — through chaos and decay."))
                                .page(BookUtils.createStandardPage("citrinitas_page", "§nCitrinitas", "Channel Nether energies to §kforge§r talismans and §martifacts§r."))
                            )
                        )

                        .chapter("Autre Onglet", c -> c
                            .page(BookUtils.createStandardPage("autre", "Titre", "Contenu..."))
                        ))
            );

    private static Item registerSimple(String id) {
        return register(id, new Item(props(id)));
    }

    private static Item registerFoil(String id, Rarity rarity) {
        return register(id, new Item(props(id).rarity(rarity)) {
            @Override
            public boolean isFoil(@NonNull ItemStack stack) { return true; }
        });
    }

    private static Item registerFood(String id, int nutrition, float saturation, boolean alwaysEdible) {
        FoodProperties.Builder builder = new FoodProperties.Builder()
                .nutrition(nutrition).saturationModifier(saturation);
        if (alwaysEdible) builder.alwaysEdible();
        return register(id, new Item(props(id).food(builder.build())));
    }

    private static Item registerFoodWithEffect(String id, int nutrition, float saturation,
                                               boolean alwaysEdible, MobEffectInstance effect, float probability) {
        FoodProperties.Builder builder = new FoodProperties.Builder()
                .nutrition(nutrition).saturationModifier(saturation);
        if (alwaysEdible) builder.alwaysEdible();
        return register(id, new Item(props(id)
                .food(builder.build())
                .component(DataComponents.CONSUMABLE, Consumable.builder()
                        .onConsume(new ApplyStatusEffectsConsumeEffect(effect, probability))
                        .build())
        ));
    }

    private static Item.Properties props(String id) {
        return new Item.Properties().setId(ResourceKey.create(
                Registries.ITEM,
                ModUtils.id(id)
        ));
    }

    private static Item register(String id, Item item) {
        return Registry.register(
                BuiltInRegistries.ITEM,
                ModUtils.id(id),
                item
        );
    }

    public static void register() {
        PotionsNRituals.LOGGER.info("Registering mod items for " + PotionsNRituals.MOD_ID);

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FOOD_AND_DRINKS)
                .register(output -> {
                    output.insertAfter(Items.BEETROOT, ModItems.POISONOUS_BEETROOT);
                    output.insertAfter(Items.CARROT, ModItems.POISONOUS_CARROT);
                    output.insertAfter(Items.ROTTEN_FLESH, ModItems.ZOMBIE_BRAIN);
                });

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES)
                .register(output ->
                        output.insertAfter(Items.BOOK, ModItems.ALCHEMICAL_TOME));

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS)
                .register(output -> {
                    output.insertAfter(Items.NETHER_WART, ModItems.MATERIA_PRIMA);
                    output.insertAfter(Items.PHANTOM_MEMBRANE,
                            ModItems.CLAW,
                            ModItems.WITCH_S_FINGER,
                            ModItems.OXYDATION,
                            ModItems.CHARGED_COPPER
                    );
                });
    }
}