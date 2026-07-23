package com.matibi.potionsnrituals.item.custom.book;

import com.matibi.potionsnrituals.book.BookStructure;
import com.matibi.potionsnrituals.item.ModItems;
import com.matibi.potionsnrituals.util.BookUtils;
import com.matibi.potionsnrituals.util.ModUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

public class BasicBookItem extends CustomBookItem {
    public BasicBookItem() {
        super(new Item.Properties().setId(ResourceKey.create(
                Registries.ITEM,
                ModUtils.id("alchemy_guide_basic"))
        ).stacksTo(1), () ->
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
        );
    }
}
