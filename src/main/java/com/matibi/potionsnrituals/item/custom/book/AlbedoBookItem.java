package com.matibi.potionsnrituals.item.custom.book;

import com.matibi.potionsnrituals.book.BookPage;
import com.matibi.potionsnrituals.book.BookStructure;
import com.matibi.potionsnrituals.item.ModItems;
import com.matibi.potionsnrituals.item.custom.alchemicalStone.AlchemicalStone;
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
                                .page(BookUtils.createIllustrationPage("nether_page"))
                                .page(new BookPage.TextPage("nether_gate",
                                        Component.translatable("book.potions-n-rituals.page.alchemy_guide_albedo.chapter.rituals"),
                                        Component.translatable("book.potions-n-rituals.page.nether_gate.text")))
                                .page(BookUtils.createRitualPage("Ritual Pattern", "nether_gate_final", ""))
                                .page(new BookPage.EmptyPage())
                        )
                        // ── Chapitre : Les artefacts ────────────────────────────────────
                        .chapter("book.potions-n-rituals.page.alchemy_guide_albedo.chapter.artifacts", c -> c
                                .page(BookUtils.createIllustrationPage("artefact_page"))
                                .subChapter("book.potions-n-rituals.page.alchemy_guide_albedo.chapter.talisman", sub -> sub
                                        .page(BookUtils.createCraftingPage("talisman", "book.potions-n-rituals.page.talisman_recipe", ModItems.TALISMAN, "book.potions-n-rituals.page.talisman.desc"))
                                        .page(new BookPage.TextPage(null, null, Component.translatable("book.potions-n-rituals.page.talisman.more")))
                                )
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
                                .subChapter(BookUtils.getName(ModItems.GAUNTLET), sub ->
                                        BookUtils.createTalismanChapter(sub, ModItems.GAUNTLET,
                                                "book.potions-n-rituals.page.artifact.gauntlet.desc",
                                                "book.potions-n-rituals.page.artifact.gauntlet.craft"))
                                .subChapter(BookUtils.getName(ModItems.RING), sub ->
                                        BookUtils.createTalismanChapter(sub, ModItems.RING,
                                                "book.potions-n-rituals.page.artifact.invisibility_ring.desc",
                                                ""))
                                .subChapter(BookUtils.getName(ModItems.CLOAK), sub ->
                                        BookUtils.createTalismanChapter(sub, ModItems.CLOAK,
                                                "book.potions-n-rituals.page.artifact.invisibility_cloak.desc",
                                                ""))
                                .subChapter(BookUtils.getName(ModItems.PHOENIX_QUILL), sub ->
                                        BookUtils.createTalismanChapter(sub, ModItems.PHOENIX_QUILL,
                                                "book.potions-n-rituals.page.artifact.phoenix_quill.desc",
                                                ""))
                                .subChapter(BookUtils.getName(ModItems.LOCK), sub ->
                                        BookUtils.createTalismanChapter(sub, ModItems.LOCK,
                                                "book.potions-n-rituals.page.artifact.lock.desc",
                                                ""))
                                .subChapter(BookUtils.getName(ModItems.KEY), sub ->
                                        BookUtils.createTalismanChapter(sub, ModItems.KEY,
                                                "book.potions-n-rituals.page.artifact.key.desc",
                                                ""))
                        )
                        // ── Chapitre : Les alchemical stones ────────────────────────────
                        .chapter("item.potions-n-rituals.alchemical_stone", c -> c
                                .subChapter(BookUtils.getName(AlchemicalStone.getItemStack(ModAlchemicalStone.ACID)),
                                        s -> s.page(BookUtils.createStonePage(ModAlchemicalStone.ACID, "")))
                                .subChapter(BookUtils.getName(AlchemicalStone.getItemStack(ModAlchemicalStone.PETRIFICATION)),
                                        s -> s.page(BookUtils.createStonePage(ModAlchemicalStone.PETRIFICATION, "")))
                                .subChapter(BookUtils.getName(AlchemicalStone.getItemStack(ModAlchemicalStone.ALCHEMIST)),
                                        s -> s.page(BookUtils.createStonePage(ModAlchemicalStone.ALCHEMIST, "")))
                                .subChapter(BookUtils.getName(AlchemicalStone.getItemStack(ModAlchemicalStone.IGNITION)),
                                        s -> s.page(BookUtils.createStonePage(ModAlchemicalStone.IGNITION, "")))
                                .subChapter(BookUtils.getName(AlchemicalStone.getItemStack(ModAlchemicalStone.GIANT)),
                                        s -> s.page(BookUtils.createStonePage(ModAlchemicalStone.GIANT, "")))
                                .subChapter(BookUtils.getName(AlchemicalStone.getItemStack(ModAlchemicalStone.RESURRECTION)),
                                        s -> s.page(BookUtils.createStonePage(ModAlchemicalStone.RESURRECTION, "")))
                                .subChapter(BookUtils.getName(AlchemicalStone.getItemStack(ModAlchemicalStone.FROST)),
                                        s -> s.page(BookUtils.createStonePage(ModAlchemicalStone.FROST, "")))
                        )
        );
    }
}