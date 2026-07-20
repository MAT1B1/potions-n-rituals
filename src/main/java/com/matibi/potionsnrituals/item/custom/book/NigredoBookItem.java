package com.matibi.potionsnrituals.item.custom.book;

import com.matibi.potionsnrituals.book.BookPage;
import com.matibi.potionsnrituals.book.BookStructure;
import com.matibi.potionsnrituals.potion.ModPotions;
import com.matibi.potionsnrituals.util.BookUtils;
import com.matibi.potionsnrituals.util.ModUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potions;

public class NigredoBookItem extends CustomBookItem {
    public NigredoBookItem() {
        super(new Item.Properties().setId(ResourceKey.create(
                        Registries.ITEM,
                        ModUtils.id("alchemy_guide_nigredo"))
                ).stacksTo(1), () ->
                        new BookStructure("item.potions-n-rituals.alchemy_guide_nigredo")
                                .tableOfContents("book.potions-n-rituals.basic.toc")

                                // ── Chapitre : Potions Customs ──────────────────────────────────
                                .chapter("book.potions-n-rituals.nigredo.chapter.potions", alch -> alch
                                        .page(new BookPage.EmptyPage())
                                        .page(BookUtils.createIllustrationPage("potion_page"))
                                        .subChapter(BookUtils.getEffectName(ModPotions.LEVITATION), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.LEVITATION,
                                                        "book.potions-n-rituals.page.levitation.resume",
                                                        "book.potions-n-rituals.page.levitation.explanation",
                                                        "book.potions-n-rituals.page.levitation.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.ADHESION), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.ADHESION,
                                                        "book.potions-n-rituals.page.adhesion.resume",
                                                        "book.potions-n-rituals.page.adhesion.explanation",
                                                        "book.potions-n-rituals.page.adhesion.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.GLOWING), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.GLOWING,
                                                        "book.potions-n-rituals.page.glowing.resume",
                                                        "book.potions-n-rituals.page.glowing.explanation",
                                                        "book.potions-n-rituals.page.glowing.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.ALCOHOL), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.ALCOHOL,
                                                        "book.potions-n-rituals.page.alcohol.resume",
                                                        "book.potions-n-rituals.page.alcohol.explanation",
                                                        "book.potions-n-rituals.page.alcohol.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.DARKNESS), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.DARKNESS,
                                                        "book.potions-n-rituals.page.darkness.resume",
                                                        "book.potions-n-rituals.page.darkness.explanation",
                                                        "book.potions-n-rituals.page.darkness.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.HASTE), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.HASTE,
                                                        "book.potions-n-rituals.page.haste.resume",
                                                        "book.potions-n-rituals.page.haste.explanation",
                                                        "book.potions-n-rituals.page.haste.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.MINING_FATIGUE), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.MINING_FATIGUE,
                                                        "book.potions-n-rituals.page.mining_fatigue.resume",
                                                        "book.potions-n-rituals.page.mining_fatigue.explanation",
                                                        "book.potions-n-rituals.page.mining_fatigue.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.ACID), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.ACID,
                                                        "book.potions-n-rituals.page.acid.resume",
                                                        "book.potions-n-rituals.page.acid.explanation",
                                                        "book.potions-n-rituals.page.acid.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.ALCHEMIST), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.ALCHEMIST,
                                                        "book.potions-n-rituals.page.alchemist.resume",
                                                        "book.potions-n-rituals.page.alchemist.explanation",
                                                        "book.potions-n-rituals.page.alchemist.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.GIANT), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.GIANT,
                                                        "book.potions-n-rituals.page.giant.resume",
                                                        "book.potions-n-rituals.page.giant.explanation",
                                                        "book.potions-n-rituals.page.giant.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.IGNITION), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.IGNITION,
                                                        "book.potions-n-rituals.page.ignition.resume",
                                                        "book.potions-n-rituals.page.ignition.explanation",
                                                        "book.potions-n-rituals.page.ignition.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.PETRIFICATION), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.PETRIFICATION,
                                                        "book.potions-n-rituals.page.petrification.resume",
                                                        "book.potions-n-rituals.page.petrification.explanation",
                                                        "book.potions-n-rituals.page.petrification.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.RESURRECTION), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.RESURRECTION,
                                                        "book.potions-n-rituals.page.resurrection.resume",
                                                        "book.potions-n-rituals.page.resurrection.explanation",
                                                        "book.potions-n-rituals.page.resurrection.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.BERSERK), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.BERSERK,
                                                        "book.potions-n-rituals.page.berserk.resume",
                                                        "book.potions-n-rituals.page.berserk.explanation",
                                                        "book.potions-n-rituals.page.berserk.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.BRAINWASHING), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.BRAINWASHING,
                                                        "book.potions-n-rituals.page.brainwashing.resume",
                                                        "book.potions-n-rituals.page.brainwashing.explanation",
                                                        "book.potions-n-rituals.page.brainwashing.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.DEATH), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.DEATH,
                                                        "book.potions-n-rituals.page.death.resume",
                                                        "book.potions-n-rituals.page.death.explanation",
                                                        "book.potions-n-rituals.page.death.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.DOUBLE_HEALTH), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.DOUBLE_HEALTH,
                                                        "book.potions-n-rituals.page.double_health.resume",
                                                        "book.potions-n-rituals.page.double_health.explanation",
                                                        "book.potions-n-rituals.page.double_health.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.DWARF), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.DWARF,
                                                        "book.potions-n-rituals.page.dwarf.resume",
                                                        "book.potions-n-rituals.page.dwarf.explanation",
                                                        "book.potions-n-rituals.page.dwarf.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.FROST), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.FROST,
                                                        "book.potions-n-rituals.page.frost.resume",
                                                        "book.potions-n-rituals.page.frost.explanation",
                                                        "book.potions-n-rituals.page.frost.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.GHOST_WALK), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.GHOST_WALK,
                                                        "book.potions-n-rituals.page.ghost_walk.resume",
                                                        "book.potions-n-rituals.page.ghost_walk.explanation",
                                                        "book.potions-n-rituals.page.ghost_walk.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.LIQUID_WALKER), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.LIQUID_WALKER,
                                                        "book.potions-n-rituals.page.liquid_walker.resume",
                                                        "book.potions-n-rituals.page.liquid_walker.explanation",
                                                        "book.potions-n-rituals.page.liquid_walker.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.LONG_COOLDOWN), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.LONG_COOLDOWN,
                                                        "book.potions-n-rituals.page.long_cooldown.resume",
                                                        "book.potions-n-rituals.page.long_cooldown.explanation",
                                                        "book.potions-n-rituals.page.long_cooldown.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.LONG_LEG), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.LONG_LEG,
                                                        "book.potions-n-rituals.page.long_leg.resume",
                                                        "book.potions-n-rituals.page.long_leg.explanation",
                                                        "book.potions-n-rituals.page.long_leg.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.LOVE), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.LOVE,
                                                        "book.potions-n-rituals.page.love.resume",
                                                        "book.potions-n-rituals.page.love.explanation",
                                                        "book.potions-n-rituals.page.love.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.MASKING), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.MASKING,
                                                        "book.potions-n-rituals.page.masking.resume",
                                                        "book.potions-n-rituals.page.masking.explanation",
                                                        "book.potions-n-rituals.page.masking.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.NO_INTERACTION), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.NO_INTERACTION,
                                                        "book.potions-n-rituals.page.no_interaction.resume",
                                                        "book.potions-n-rituals.page.no_interaction.explanation",
                                                        "book.potions-n-rituals.page.no_interaction.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.OBLIVION), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.OBLIVION,
                                                        "book.potions-n-rituals.page.oblivion.resume",
                                                        "book.potions-n-rituals.page.oblivion.explanation",
                                                        "book.potions-n-rituals.page.oblivion.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.ORE_SENSE), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.ORE_SENSE,
                                                        "book.potions-n-rituals.page.ore_sense.resume",
                                                        "book.potions-n-rituals.page.ore_sense.explanation",
                                                        "book.potions-n-rituals.page.ore_sense.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.PHOTOSYNTHESIS), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.PHOTOSYNTHESIS,
                                                        "book.potions-n-rituals.page.photosynthesis.resume",
                                                        "book.potions-n-rituals.page.photosynthesis.explanation",
                                                        "book.potions-n-rituals.page.photosynthesis.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.PURIFICATION), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.PURIFICATION,
                                                        "book.potions-n-rituals.page.purification.resume",
                                                        "book.potions-n-rituals.page.purification.explanation",
                                                        "book.potions-n-rituals.page.purification.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.REACTIVATION), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.REACTIVATION,
                                                        "book.potions-n-rituals.page.reactivation.resume",
                                                        "book.potions-n-rituals.page.reactivation.explanation",
                                                        "book.potions-n-rituals.page.reactivation.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.RESONANCE), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.RESONANCE,
                                                        "book.potions-n-rituals.page.resonance.resume",
                                                        "book.potions-n-rituals.page.resonance.explanation",
                                                        "book.potions-n-rituals.page.resonance.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.INFINITY), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.INFINITY,
                                                        "book.potions-n-rituals.page.infinity.resume",
                                                        "book.potions-n-rituals.page.infinity.explanation",
                                                        "book.potions-n-rituals.page.infinity.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.RUST), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.RUST,
                                                        "book.potions-n-rituals.page.rust.resume",
                                                        "book.potions-n-rituals.page.rust.explanation",
                                                        "book.potions-n-rituals.page.rust.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.SATURATION), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.SATURATION,
                                                        "book.potions-n-rituals.page.saturation.resume",
                                                        "book.potions-n-rituals.page.saturation.explanation",
                                                        "book.potions-n-rituals.page.saturation.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.SHORT_COOLDOWN), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.SHORT_COOLDOWN,
                                                        "book.potions-n-rituals.page.short_cooldown.resume",
                                                        "book.potions-n-rituals.page.short_cooldown.explanation",
                                                        "book.potions-n-rituals.page.short_cooldown.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.STUN), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.STUN,
                                                        "book.potions-n-rituals.page.stun.resume",
                                                        "book.potions-n-rituals.page.stun.explanation",
                                                        "book.potions-n-rituals.page.stun.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.TELEPORTATION), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.TELEPORTATION,
                                                        "book.potions-n-rituals.page.teleportation.resume",
                                                        "book.potions-n-rituals.page.teleportation.explanation",
                                                        "book.potions-n-rituals.page.teleportation.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.THORNS), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.THORNS,
                                                        "book.potions-n-rituals.page.thorns.resume",
                                                        "book.potions-n-rituals.page.thorns.explanation",
                                                        "book.potions-n-rituals.page.thorns.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.UNSTABLE), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.UNSTABLE,
                                                        "book.potions-n-rituals.page.unstable.resume",
                                                        "book.potions-n-rituals.page.unstable.explanation",
                                                        "book.potions-n-rituals.page.unstable.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.VAMPIRISM), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.VAMPIRISM,
                                                        "book.potions-n-rituals.page.vampirism.resume",
                                                        "book.potions-n-rituals.page.vampirism.explanation",
                                                        "book.potions-n-rituals.page.vampirism.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.XP_BOOST), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.XP_BOOST,
                                                        "book.potions-n-rituals.page.xp_boost.resume",
                                                        "book.potions-n-rituals.page.xp_boost.explanation",
                                                        "book.potions-n-rituals.page.xp_boost.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.XP_REDUCTION), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.XP_REDUCTION,
                                                        "book.potions-n-rituals.page.xp_reduction.resume",
                                                        "book.potions-n-rituals.page.xp_reduction.explanation",
                                                        "book.potions-n-rituals.page.xp_reduction.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.XP_LIFE), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.XP_LIFE,
                                                        "book.potions-n-rituals.page.xp_life.resume",
                                                        "book.potions-n-rituals.page.xp_life.explanation",
                                                        "book.potions-n-rituals.page.xp_life.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.ZEUS), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.ZEUS,
                                                        "book.potions-n-rituals.page.zeus.resume",
                                                        "book.potions-n-rituals.page.zeus.explanation",
                                                        "book.potions-n-rituals.page.zeus.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.COLD), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.COLD,
                                                        "book.potions-n-rituals.page.cold.resume",
                                                        "book.potions-n-rituals.page.cold.explanation",
                                                        "book.potions-n-rituals.page.cold.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.CLUMSINESS), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.CLUMSINESS,
                                                        "book.potions-n-rituals.page.clumsiness.resume",
                                                        "book.potions-n-rituals.page.clumsiness.explanation",
                                                        "book.potions-n-rituals.page.clumsiness.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.ASTHMA), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.ASTHMA,
                                                        "book.potions-n-rituals.page.asthma.resume",
                                                        "book.potions-n-rituals.page.asthma.explanation",
                                                        "book.potions-n-rituals.page.asthma.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.PARANOIA), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.PARANOIA,
                                                        "book.potions-n-rituals.page.paranoia.resume",
                                                        "book.potions-n-rituals.page.paranoia.explanation",
                                                        "book.potions-n-rituals.page.paranoia.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.MEDUSA), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.MEDUSA,
                                                        "book.potions-n-rituals.page.medusa.resume",
                                                        "book.potions-n-rituals.page.medusa.explanation",
                                                        "book.potions-n-rituals.page.medusa.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.MIDAS), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.MIDAS,
                                                        "book.potions-n-rituals.page.midas.resume",
                                                        "book.potions-n-rituals.page.midas.explanation",
                                                        "book.potions-n-rituals.page.midas.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.ACTIVE_TELEPORT), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.ACTIVE_TELEPORT,
                                                        "book.potions-n-rituals.page.active_teleport.resume",
                                                        "book.potions-n-rituals.page.active_teleport.explanation",
                                                        "book.potions-n-rituals.page.active_teleport.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.HYDROPHOBIA), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.HYDROPHOBIA,
                                                        "book.potions-n-rituals.page.hydrophobia.resume",
                                                        "book.potions-n-rituals.page.hydrophobia.explanation",
                                                        "book.potions-n-rituals.page.hydrophobia.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.ZOMBIE_CONTAGION), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.ZOMBIE_CONTAGION,
                                                        "book.potions-n-rituals.page.zombie_contagion.resume",
                                                        "book.potions-n-rituals.page.zombie_contagion.explanation",
                                                        "book.potions-n-rituals.page.zombie_contagion.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.MAGNETISM), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.MAGNETISM,
                                                        "book.potions-n-rituals.page.magnetism.resume",
                                                        "book.potions-n-rituals.page.magnetism.explanation",
                                                        "book.potions-n-rituals.page.magnetism.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.REALITY_CHECK), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.REALITY_CHECK,
                                                        "book.potions-n-rituals.page.reality_check.resume",
                                                        "book.potions-n-rituals.page.reality_check.explanation",
                                                        "book.potions-n-rituals.page.reality_check.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(Potions.FIRE_RESISTANCE), sub ->
                                                BookUtils.createPotionChapter(sub, Potions.FIRE_RESISTANCE,
                                                        "book.potions-n-rituals.page.fire_resistance.resume",
                                                        "book.potions-n-rituals.page.fire_resistance.explanation",
                                                        "book.potions-n-rituals.page.fire_resistance.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(Potions.REGENERATION), sub ->
                                                BookUtils.createPotionChapter(sub, Potions.REGENERATION,
                                                        "book.potions-n-rituals.page.regeneration.resume",
                                                        "book.potions-n-rituals.page.regeneration.explanation",
                                                        "book.potions-n-rituals.page.regeneration.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(Potions.STRENGTH), sub ->
                                                BookUtils.createPotionChapter(sub, Potions.STRENGTH,
                                                        "book.potions-n-rituals.page.strength.resume",
                                                        "book.potions-n-rituals.page.strength.explanation",
                                                        "book.potions-n-rituals.page.strength.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(Potions.SWIFTNESS), sub ->
                                                BookUtils.createPotionChapter(sub, Potions.SWIFTNESS,
                                                        "book.potions-n-rituals.page.swiftness.resume",
                                                        "book.potions-n-rituals.page.swiftness.explanation",
                                                        "book.potions-n-rituals.page.swiftness.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(Potions.POISON), sub ->
                                                BookUtils.createPotionChapter(sub, Potions.POISON,
                                                        "book.potions-n-rituals.page.poison.resume",
                                                        "book.potions-n-rituals.page.poison.explanation",
                                                        "book.potions-n-rituals.page.poison.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(Potions.HEALING), sub ->
                                                BookUtils.createPotionChapter(sub, Potions.HEALING,
                                                        "book.potions-n-rituals.page.healing.resume",
                                                        "book.potions-n-rituals.page.healing.explanation",
                                                        "book.potions-n-rituals.page.healing.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(Potions.NIGHT_VISION), sub ->
                                                BookUtils.createPotionChapter(sub, Potions.NIGHT_VISION,
                                                        "book.potions-n-rituals.page.night_vision.resume",
                                                        "book.potions-n-rituals.page.night_vision.explanation",
                                                        "book.potions-n-rituals.page.night_vision.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(Potions.INVISIBILITY), sub ->
                                                BookUtils.createPotionChapter(sub, Potions.INVISIBILITY,
                                                        "book.potions-n-rituals.page.invisibility.resume",
                                                        "book.potions-n-rituals.page.invisibility.explanation",
                                                        "book.potions-n-rituals.page.invisibility.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(Potions.WATER_BREATHING), sub ->
                                                BookUtils.createPotionChapter(sub, Potions.WATER_BREATHING,
                                                        "book.potions-n-rituals.page.water_breathing.resume",
                                                        "book.potions-n-rituals.page.water_breathing.explanation",
                                                        "book.potions-n-rituals.page.water_breathing.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(Potions.LEAPING), sub ->
                                                BookUtils.createPotionChapter(sub, Potions.LEAPING,
                                                        "book.potions-n-rituals.page.leaping.resume",
                                                        "book.potions-n-rituals.page.leaping.explanation",
                                                        "book.potions-n-rituals.page.leaping.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(Potions.TURTLE_MASTER), sub ->
                                                BookUtils.createPotionChapter(sub, Potions.TURTLE_MASTER,
                                                        "book.potions-n-rituals.page.turtle_master.resume",
                                                        "book.potions-n-rituals.page.turtle_master.explanation",
                                                        "book.potions-n-rituals.page.turtle_master.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(Potions.SLOW_FALLING), sub ->
                                                BookUtils.createPotionChapter(sub, Potions.SLOW_FALLING,
                                                        "book.potions-n-rituals.page.slow_falling.resume",
                                                        "book.potions-n-rituals.page.slow_falling.explanation",
                                                        "book.potions-n-rituals.page.slow_falling.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(Potions.WEAKNESS), sub ->
                                                BookUtils.createPotionChapter(sub, Potions.WEAKNESS,
                                                        "book.potions-n-rituals.page.weakness.resume",
                                                        "book.potions-n-rituals.page.weakness.explanation",
                                                        "book.potions-n-rituals.page.weakness.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(Potions.SLOWNESS), sub ->
                                                BookUtils.createPotionChapter(sub, Potions.SLOWNESS,
                                                        "book.potions-n-rituals.page.slowness.resume",
                                                        "book.potions-n-rituals.page.slowness.explanation",
                                                        "book.potions-n-rituals.page.slowness.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(Potions.HARMING), sub ->
                                                BookUtils.createPotionChapter(sub, Potions.HARMING,
                                                        "book.potions-n-rituals.page.harming.resume",
                                                        "book.potions-n-rituals.page.harming.explanation",
                                                        "book.potions-n-rituals.page.harming.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.PERM_HEALTH), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.PERM_HEALTH,
                                                        "book.potions-n-rituals.page.perm_health.resume",
                                                        "book.potions-n-rituals.page.perm_health.explanation",
                                                        "book.potions-n-rituals.page.perm_health.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.PERM_SPEED), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.PERM_SPEED,
                                                        "book.potions-n-rituals.page.perm_speed.resume",
                                                        "book.potions-n-rituals.page.perm_speed.explanation",
                                                        "book.potions-n-rituals.page.perm_speed.brew")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.PERM_STRENGTH), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.PERM_STRENGTH,
                                                        "book.potions-n-rituals.page.perm_strength.resume",
                                                        "book.potions-n-rituals.page.perm_strength.explanation",
                                                        "book.potions-n-rituals.page.perm_strength.brew")
                                        )
                                )
        );
    }
}