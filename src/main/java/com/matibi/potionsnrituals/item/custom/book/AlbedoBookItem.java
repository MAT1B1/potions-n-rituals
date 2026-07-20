package com.matibi.potionsnrituals.item.custom.book;

import com.matibi.potionsnrituals.book.BookPage;
import com.matibi.potionsnrituals.book.BookStructure;
import com.matibi.potionsnrituals.item.ModItems;
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
                                .chapter("Chemin vers le Citrinitas", c -> c
                                        .subChapter("Rituel", sub -> sub
                                                .page(BookUtils.createIllustrationPage("nether_page"))
                                                .page(new BookPage.TextPage("nether_gate", Component.literal("Ritual"),
                                                        Component.literal("Vous aurez besoin de partir en enfer avec un rituel. Pour l'activer, il faut sacrifier une entité à l'intérieur du rituel.\n\nAttention nul ne sait comment revenir de l'enfer...")))
                                                .page(BookUtils.createRitualPage("Ritual Pattern", "nether_gate_final", ""))
                                        )
                                        .subChapter("Talisman", sub -> sub
                                                .page(BookUtils.createCraftingPage("talisman", "Talisman Recipe", ModItems.TALISMAN, "Une fois dans le §4Nether§r, vous devrez fabriquer un talisman"))
                                                .page(new BookPage.EmptyPage())
                                        )
                                )
                                // ── Chapitre : Les artefacts ────────────────────────────────────
                                .chapter("Les Artefacts", c -> c
                                        .subChapter(BookUtils.getName(ModItems.ALCHEMICAL_BAG), sub -> BookUtils.createTalismanChapter(sub, ModItems.ALCHEMICAL_BAG, ""))
                                        .subChapter(BookUtils.getName(ModItems.SPIRIT_MIRROR), sub -> BookUtils.createTalismanChapter(sub, ModItems.SPIRIT_MIRROR, ""))
                                        .subChapter(BookUtils.getName(ModItems.NETHER_SEAL_BREAKER), sub -> BookUtils.createTalismanChapter(sub, ModItems.NETHER_SEAL_BREAKER, ""))
                                        .subChapter(BookUtils.getName(ModItems.ALCHEMICAL_STONE), sub -> BookUtils.createTalismanChapter(sub, ModItems.ALCHEMICAL_STONE, ""))
                                )
        );
    }
}
