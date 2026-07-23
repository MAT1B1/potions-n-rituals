package com.matibi.potionsnrituals.item.custom.book;

import com.matibi.potionsnrituals.book.BookStructure;
import com.matibi.potionsnrituals.util.BookUtils;
import com.matibi.potionsnrituals.util.ModUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

public class CitrinitasBookItem extends CustomBookItem {
    public CitrinitasBookItem() {
        super(new Item.Properties().setId(ResourceKey.create(
                Registries.ITEM,
                ModUtils.id("alchemy_guide_citrinitas"))
        ).stacksTo(1), () ->
                new BookStructure("item.potions-n-rituals.alchemy_guide_citrinitas")
                        .tableOfContents("book.potions-n-rituals.basic.toc")

                        .chapter("book.potions-n-rituals.citrinitas.chapter.rituals", c -> c
                                .subChapter("book.potions-n-rituals.page.citrinitas.skeleton_horse", sub ->
                                        BookUtils.createRitualChapter(sub,
                                            "book.potions-n-rituals.page.citrinitas.skeleton_horse",
                                            "skeleton_horse_summon_ritual",
                                            "book.potions-n-rituals.page.citrinitas.skeleton_horse.desc"
                                        )
                                )
                                .subChapter("book.potions-n-rituals.page.citrinitas.ancient_debris",  sub ->
                                        BookUtils.createRitualChapter(sub,
                                                "book.potions-n-rituals.page.citrinitas.ancient_debris",
                                                "ancient_debris_ritual",
                                                "book.potions-n-rituals.page.citrinitas.ancient_debris.desc"
                                        )
                                )
                                .subChapter("book.potions-n-rituals.page.citrinitas.random_effect",  sub ->
                                        BookUtils.createRitualChapter(sub,
                                                "book.potions-n-rituals.page.citrinitas.random_effect",
                                                "random_effect",
                                                "book.potions-n-rituals.page.citrinitas.random_effect.desc"
                                        )
                                )
                                .subChapter("book.potions-n-rituals.page.citrinitas.summon_thunderstorm",  sub ->
                                        BookUtils.createRitualChapter(sub,
                                                "book.potions-n-rituals.page.citrinitas.summon_thunderstorm",
                                                "summon_thunderstorm",
                                                "book.potions-n-rituals.page.citrinitas.summon_thunderstorm.desc"
                                        )
                                )
                                .subChapter("book.potions-n-rituals.page.citrinitas.echo_shard",  sub ->
                                        BookUtils.createRitualChapter(sub,
                                                "book.potions-n-rituals.page.citrinitas.echo_shard",
                                                "echo_shard_ritual",
                                                "book.potions-n-rituals.page.citrinitas.echo_shard.desc"
                                        )
                                )
                                .subChapter("book.potions-n-rituals.page.citrinitas.summon_dawn",  sub ->
                                        BookUtils.createRitualChapter(sub,
                                                "book.potions-n-rituals.page.citrinitas.summon_dawn",
                                                "summon_dawn",
                                                "book.potions-n-rituals.page.citrinitas.summon_dawn.desc"
                                        )
                                )
                                .subChapter("book.potions-n-rituals.page.citrinitas.nether_gate",  sub ->
                                        BookUtils.createRitualChapter(sub,
                                                "book.potions-n-rituals.page.citrinitas.nether_gate",
                                                "nether_gate_final",
                                                "book.potions-n-rituals.page.citrinitas.nether_gate.desc"
                                        )
                                )
                                .subChapter("book.potions-n-rituals.page.citrinitas.seal_nether",  sub ->
                                        BookUtils.createRitualChapter(sub,
                                                "book.potions-n-rituals.page.citrinitas.seal_nether",
                                                "seal_nether",
                                                "book.potions-n-rituals.page.citrinitas.seal_nether.desc"
                                        )
                                )
                        )
        );
    }
}
