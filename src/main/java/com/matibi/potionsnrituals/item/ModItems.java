package com.matibi.potionsnrituals.item;

import com.matibi.potionsnrituals.PotionsNRituals;
import com.matibi.potionsnrituals.block.ModBlocks;
import com.matibi.potionsnrituals.book.BookStructure;
import com.matibi.potionsnrituals.effect.ModEffects;
import com.matibi.potionsnrituals.item.custom.alchemicalStone.AlchemicalStoneItem;
import com.matibi.potionsnrituals.item.custom.book.AlbedoBookItem;
import com.matibi.potionsnrituals.item.custom.book.CitrinitasBookItem;
import com.matibi.potionsnrituals.item.custom.book.CustomBookItem;
import com.matibi.potionsnrituals.item.custom.book.NigredoBookItem;
import com.matibi.potionsnrituals.item.custom.syringe.SyringeItem;
import com.matibi.potionsnrituals.item.custom.talisman.*;
import com.matibi.potionsnrituals.util.BookUtils;
import com.matibi.potionsnrituals.util.ModUtils;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
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
            MATERIA_PRIMA = register("materia_prima", new Item(props("materia_prima")
                    .rarity(Rarity.UNCOMMON)) {
                        @Override
                        public boolean isFoil(@NonNull ItemStack stack) { return true; }
                    }
            ),
            MERCURY_BALL = registerSimple("mercury_ball"),
            SULFUR_BALL = registerSimple("sulfur_ball"),
            SALT = registerSimple("salt"),
            CLAW = registerSimple("claw"),
            BAT_WING = registerSimple("bat_wing"),
            BLOOD_BAG = register("blood_bag",
                    new BlockItem(ModBlocks.BLOOD_TRAIL, props("blood_bag"))
            ),
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
            TALISMAN = register("talisman",
                    new TalismanItem(props("talisman"))
            ),
            TALISMAN_CHARGED = register("talisman_charged", new Item(props("talisman_charged")
                    .rarity(Rarity.UNCOMMON)) {
                        @Override
                        public boolean isFoil(@NonNull ItemStack stack) { return true; }
                    }
            ),
            ALCHEMICAL_STONE = register("alchemical_stone",
                new AlchemicalStoneItem(props("alchemical_stone")
                    .stacksTo(16)
                    .component(DataComponents.POTION_CONTENTS, PotionContents.EMPTY)
                )
            ),
            ALCHEMICAL_BAG = register("alchemical_bag", new AlchemicalBagItem(props("alchemical_bag")
                    .stacksTo(1)
                    .rarity(Rarity.RARE)) {
                        @Override
                        public boolean isFoil(@NonNull ItemStack stack) { return true; }
                    }
            ),
            NETHER_SEAL_BREAKER = register("nether_seal_breaker", new NetherSealBreakerItem(props("nether_seal_breaker")
                    .stacksTo(1)
                    .rarity(Rarity.RARE)) {
                        @Override
                        public boolean isFoil(@NonNull ItemStack stack) { return true; }
                    }
            ),
            SPIRIT_MIRROR = register("spirit_mirror", new SpiritMirrorItem(props("spirit_mirror")
                    .durability(6)
                    .stacksTo(1)
                    .rarity(Rarity.RARE)) {
                        @Override
                        public boolean isFoil(@NonNull ItemStack stack) { return true; }
                    }
            ),
            DECOY = register("decoy", new DecoyItem(props("decoy")
                    .stacksTo(1)
                    .rarity(Rarity.RARE)) {
                        @Override
                        public boolean isFoil(@NonNull ItemStack stack) { return true; }
                    }
            ),
            BASIC_GUIDE = register("alchemy_guide_basic",
                    new CustomBookItem(props("alchemy_guide_basic").stacksTo(1), () ->
                            new BookStructure("item.potions-n-rituals.alchemy_guide_basic")
                                    .tableOfContents("book.potions-n-rituals.basic.toc")

                                    .chapter("book.potions-n-rituals.basic.opus.chapter", c -> c
                                            .page(BookUtils.createStandardPage(
                                                    "opus_intro",
                                                    "book.potions-n-rituals.basic.opus.title",
                                                    "book.potions-n-rituals.basic.opus.body"
                                            ))
                                    )
                                    .chapter("book.potions-n-rituals.basic.nigredo.chapter", c -> c
                                            .page(BookUtils.createStandardPage(
                                                    "nigredo_intro",
                                                    "book.potions-n-rituals.basic.nigredo.title",
                                                    "book.potions-n-rituals.basic.nigredo.body"
                                            ))
                                            .page(BookUtils.createFurnacePage("sulfur", "book.potions-n-rituals.basic.nigredo.sulfur", ModItems.SULFUR_BALL, ""))
                                            .page(BookUtils.createFurnacePage("mercury", "book.potions-n-rituals.basic.nigredo.mercury", ModItems.MERCURY_BALL, ""))
                                            .page(BookUtils.createFurnacePage("salt", "book.potions-n-rituals.basic.nigredo.salt", ModItems.SALT, ""))
                                            .page(BookUtils.createCraftingPage("materia_prima", "book.potions-n-rituals.basic.nigredo.materia", ModItems.MATERIA_PRIMA, ""))
                                    )
                                    .chapter("book.potions-n-rituals.basic.albedo.chapter", c -> c
                                            .page(BookUtils.createStandardPage(
                                                    "teasing_albedo",
                                                    "book.potions-n-rituals.basic.albedo.title",
                                                    "book.potions-n-rituals.basic.albedo.body"
                                            ))
                                    )
                                    .chapter("book.potions-n-rituals.basic.citrinitas.chapter", c -> c
                                            .page(BookUtils.createStandardPage(
                                                    "teasing_citrinitas",
                                                    "book.potions-n-rituals.basic.citrinitas.title",
                                                    "book.potions-n-rituals.basic.citrinitas.body"
                                            ))
                                    )
                                    .chapter("book.potions-n-rituals.basic.rubedo.chapter", c -> c
                                            .page(BookUtils.createStandardPage(
                                                    "teasing_rubedo",
                                                    "book.potions-n-rituals.basic.rubedo.title",
                                                    "book.potions-n-rituals.basic.rubedo.body"
                                            ))
                                    )
                    )
            ),
            NIGREDO_GUIDE = register("alchemy_guide_nigredo", new NigredoBookItem()),
            ALBEDO_GUIDE = register("alchemy_guide_albedo", new AlbedoBookItem()),
            CITRINITAS_GUIDE = register("alchemy_guide_citrinitas", new CitrinitasBookItem()),
            RUBEDO_GUIDE = register("alchemy_guide_rubedo",
                    new CustomBookItem(props("alchemy_guide_rubedo").stacksTo(1), () ->
                            new BookStructure("item.potions-n-rituals.alchemy_guide_rubedo")
                                    .page(BookUtils.createRitualPage("Ok", "summon_thunderstorm", ""))));

    private static Item registerSimple(String id) {
        return register(id, new Item(props(id)));
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
                        output.insertAfter(Items.BOOK, ModItems.BASIC_GUIDE));

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS)
                .register(output -> {
                    output.insertAfter(Items.NETHER_WART, ModItems.MATERIA_PRIMA);
                    output.insertAfter(Items.PHANTOM_MEMBRANE,
                            ModItems.CLAW,
                            ModItems.BAT_WING,
                            ModItems.WITCH_S_FINGER,
                            ModItems.OXYDATION,
                            ModItems.CHARGED_COPPER
                    );
                });
    }
}