package com.matibi.potionsnrituals.item.custom.book;

import com.matibi.potionsnrituals.book.BookPage;
import com.matibi.potionsnrituals.book.BookStructure;
import com.matibi.potionsnrituals.item.ModItems;
import com.matibi.potionsnrituals.item.custom.alchemicalStone.ModAlchemicalStone;
import com.matibi.potionsnrituals.util.BookUtils;
import com.matibi.potionsnrituals.util.ModUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

public class AlbedoBookItem extends CustomBookItem {
    public AlbedoBookItem() {
        super(new Item.Properties().setId(ResourceKey.create(
                        Registries.ITEM,
                        ModUtils.id("alchemy_guide_albedo"))
                ).stacksTo(1), () ->
                        new BookStructure("item.potions-n-rituals.alchemy_guide_albedo")
                                .tableOfContents("book.potions-n-rituals.basic.toc")

                                // ── Chapitre : Chemin vers le Citrinitas ────────────────────────
                                .chapter("book.potions-n-rituals.page.alchemy_guide_albedo.chapter.path", c -> c
                                        .subChapter("book.potions-n-rituals.page.alchemy_guide_albedo.chapter.rituals", sub -> sub
                                                .page(BookUtils.createIllustrationPage("nether_page"))
                                                .page(new BookPage.TextPage("nether_gate",
                                                        Component.translatable("book.potions-n-rituals.page.alchemy_guide_albedo.chapter.rituals"),
                                                        Component.translatable("book.potions-n-rituals.page.nether_gate.text")))
                                                .page(BookUtils.createRitualPage("Ritual Pattern", "nether_gate_final", ""))
                                        )
                                        .subChapter("book.potions-n-rituals.page.alchemy_guide_albedo.chapter.talisman", sub -> sub
                                                .page(BookUtils.createCraftingPage("talisman", "book.potions-n-rituals.page.talisman_recipe", ModItems.TALISMAN, "book.potions-n-rituals.page.talisman.desc"))
                                                .page(new BookPage.EmptyPage())
                                        )
                                )
                                // ── Chapitre : Les artefacts ────────────────────────────────────
                                .chapter("book.potions-n-rituals.page.alchemy_guide_albedo.chapter.artifacts", c -> c
                                        .page(BookUtils.createIllustrationPage("artefact_page"))
                                        .subChapter(BookUtils.getName(ModItems.ALCHEMICAL_BAG), sub ->
                                                BookUtils.createTalismanChapter(sub, ModItems.ALCHEMICAL_BAG,
                                                        "book.potions-n-rituals.page.artifact.alchemical_bag.desc",
                                                        "book.potions-n-rituals.page.artifact.alchemical_bag.craft"))
                                        .subChapter(BookUtils.getName(ModItems.SPIRIT_MIRROR), sub ->
                                                BookUtils.createTalismanChapter(sub, ModItems.SPIRIT_MIRROR,
                                                        "book.potions-n-rituals.page.artifact.spirit_mirror.desc",
                                                        ""))
                                        .subChapter(BookUtils.getName(ModItems.NETHER_SEAL_BREAKER), sub ->
                                                BookUtils.createTalismanChapter(sub, ModItems.NETHER_SEAL_BREAKER,
                                                        "book.potions-n-rituals.page.artifact.nether_seal_breaker.desc",
                                                        "book.potions-n-rituals.page.artifact.nether_seal_breaker.craft"))
                                        .subChapter(BookUtils.getName(ModItems.DECOY), sub ->
                                                BookUtils.createTalismanChapter(sub, ModItems.DECOY,
                                                        "book.potions-n-rituals.page.artifact.decoy.desc",
                                                        ""))
                                        .subChapter(BookUtils.getName(ModItems.ALCHEMICAL_STONE), sub ->
                                                BookUtils.createTalismanChapter(sub, ModItems.ALCHEMICAL_STONE,
                                                        "book.potions-n-rituals.page.artifact.alchemical_stone.desc",
                                                        ""))
                                )
                                // ── Chapitre : Les alchemical stones ────────────────────────────
                                .chapter("item.potions-n-rituals.alchemical_stone", c -> c
                                        .subChapter(BookUtils.getIdString(ModAlchemicalStone.ACID), s -> s.page(BookUtils.createStonePage(ModAlchemicalStone.ACID, "")))
                                        .subChapter(BookUtils.getIdString(ModAlchemicalStone.PETRIFICATION), s -> s.page(BookUtils.createStonePage(ModAlchemicalStone.PETRIFICATION, "")))
                                        .subChapter(BookUtils.getIdString(ModAlchemicalStone.ALCHEMIST), s -> s.page(BookUtils.createStonePage(ModAlchemicalStone.ALCHEMIST, "")))
                                        .subChapter(BookUtils.getIdString(ModAlchemicalStone.IGNITION), s -> s.page(BookUtils.createStonePage(ModAlchemicalStone.IGNITION, "")))
                                        .subChapter(BookUtils.getIdString(ModAlchemicalStone.GIANT), s -> s.page(BookUtils.createStonePage(ModAlchemicalStone.GIANT, "")))
                                        .subChapter(BookUtils.getIdString(ModAlchemicalStone.RESURRECTION), s -> s.page(BookUtils.createStonePage(ModAlchemicalStone.RESURRECTION, "")))
                                        .subChapter(BookUtils.getIdString(ModAlchemicalStone.FROST), s -> s.page(BookUtils.createStonePage(ModAlchemicalStone.FROST, "")))
                                )
        );
    }
}