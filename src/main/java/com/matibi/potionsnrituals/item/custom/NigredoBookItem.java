package com.matibi.potionsnrituals.item.custom;

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
                                .tableOfContents("Sommaire")

                                // ── Chapitre : Potions Customs ──────────────────────────────────
                                .chapter("Potions", alch -> alch

                                        .subChapter(BookUtils.getEffectName(ModPotions.LEVITATION), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.LEVITATION,
                                                        "\nGravity is a weight on the shoulders, forget it",
                                                        "You fall slowly, so slowly that you don't take fall damage.\n\nIt's like you are on the §3Moon§r.\n\n§4Be careful, it only last a moment.§r",
                                                        "A §3Slow Falling potion§r with a §3feather§r should be good enough to lighten your burden.")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.ADHESION), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.ADHESION,
                                                        "\nMmh that's a bit...\n\n§2sticky§r.",
                                                        "You stick to surfaces, allowing you to walk on walls.\n\nVery useful for repelling away spiders.",
                                                        "Find something sticky, maybe like §2resin§r. Mix it with an §2awkward potion§r, it should be sufficient.")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.GLOWING), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.GLOWING,
                                                        "\nIt's a bit dark in here...\n\nBut I can §6see§r you very well",
                                                        "Make entity §6glow§r so that you can see them in the dark and behind walls.",
                                                        "A §6Night Vision Potion§r allow you to see in the dark while a §6glow berry§r make you glow... pretty straight forward.")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.ALCOHOL), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.ALCOHOL,
                                                        "\n\n§2*hic*§r... Just one last drop.",
                                                        "Gives entity a feeling of loss.\n\nYou §2lose control§r, yet everything seems so §2funny§r !",
                                                        "Mix §2sweet berries§r and an §2awkward potion§r, to get some liquor.")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.DARKNESS), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.DARKNESS,
                                                        "\nEverything disappear, only the §7void§r keep showing itself.",
                                                        "You lose your sight, everything turn black.\n\nThere is just one thing to remember...\n\n§4RUN§r\n\nHe's close...",
                                                        "An ink sac mixed with an awkward potion is great to mimic the effect.")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.HASTE), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.HASTE,
                                                        "\n\nA miner's dream...\n\nIt's so §6fast§r !!",
                                                        "You can mine everything §6faster§r,\n\nreally really §6fast§r.",
                                                        "Cat dig a lot, so a §6claw§r with an §6awkward potion§r might give you the potion")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.MINING_FATIGUE), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.MINING_FATIGUE,
                                                        "\nWow, it's a §7hard stone§r ! \n\nI can't break it.",
                                                        "You mine everything §4slower§r,\n\nreally really §4slow§r.",
                                                        "You reverse the §4potion of haste§r with a §4fermented spider eye§r")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.ACID), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.ACID,
                                                        "Substance highly §2toxic§r,\n\nUse with precaution.\n\nCan cause indigestion.",
                                                        "Damages you every seconds",
                                                        "Mix a §2rotten flesh§r and a §2poison potion§r to get a highly toxic mixture.")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.ALCHEMIST), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.ALCHEMIST,
                                                        "\nThe dream of every alchemist,\n\n§7coal§r that become §6gold§r.",
                                                        "Turn §7Coal§r items into their §6Gold§r variant.",
                                                        "Take an alchemist item like the §6mercury ball§r and an §6awkward potion§r to achieve that dream.")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.GIANT), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.GIANT,
                                                        "\n\n§3Looking down upon the world.§r",
                                                        "",
                                                        "§7Bone Meal + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.IGNITION), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.IGNITION,
                                                        "\n\n§6The inner fire.§r",
                                                        "",
                                                        "§7Fermented Eye + Fire Resistance")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.PETRIFICATION), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.PETRIFICATION,
                                                        "\n\n§8Freezing the blood.§r",
                                                        "",
                                                        "§7Obsidian + Turtle Master")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.RESURRECTION), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.RESURRECTION,
                                                        "\n\n§dTricking the reaper.§r",
                                                        "",
                                                        "§7Totem of Undying + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.BERSERK), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.BERSERK,
                                                        "\n\n§cBlind fury.§r",
                                                        "",
                                                        "§7Torchflower + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.BRAINWASHING), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.BRAINWASHING,
                                                        "\n\n§dPutrid marionettes.§r",
                                                        "",
                                                        "§7Zombie Brain + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.DEATH), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.DEATH,
                                                        "\n\n§8The definitive stop.§r",
                                                        "",
                                                        "§7Wither Rose + Resurrection")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.DOUBLE_HEALTH), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.DOUBLE_HEALTH,
                                                        "\n\n§cA second surge of life.§r",
                                                        "",
                                                        "§7Golden Apple + Strong Healing")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.DWARF), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.DWARF,
                                                        "\n\n§3A mouse's perspective.§r",
                                                        "",
                                                        "§7Fermented Eye + Giant")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.FROST), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.FROST,
                                                        "\n\n§bThe kiss of winter.§r",
                                                        "",
                                                        "§7Snowball + Cold")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.GHOST_WALK), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.GHOST_WALK,
                                                        "\n\n§7Passing through the veil.§r",
                                                        "",
                                                        "§7Ghast Tear + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.LIQUID_WALKER), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.LIQUID_WALKER,
                                                        "\n\n§3Fluid density.§r",
                                                        "",
                                                        "§7Lily Pad + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.LONG_COOLDOWN), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.LONG_COOLDOWN,
                                                        "\n\n§8Temporal numbness.§r",
                                                        "",
                                                        "§7Fermented Eye + Short Cooldown")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.LONG_LEG), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.LONG_LEG,
                                                        "\n\n§3Des enjambées de géant.§r",
                                                        "",
                                                        "§7Bamboo + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.LOVE), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.LOVE,
                                                        "\n\n§dPeaceful euphoria.§r",
                                                        "",
                                                        "§7Poppy + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.MASKING), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.MASKING,
                                                        "\n\n§8Total erasure.§r",
                                                        "",
                                                        "§7Fermented Eye + Invisibility")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.NO_INTERACTION), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.NO_INTERACTION,
                                                        "\n\n§8Spectator of the world.§r",
                                                        "",
                                                        "§7Rabbit Foot + Brainwashing")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.OBLIVION), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.OBLIVION,
                                                        "\n\n§5The great void.§r",
                                                        "",
                                                        "§7Pitcher Plant + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.ORE_SENSE), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.ORE_SENSE,
                                                        "\n\n§eThe call of the rock.§r",
                                                        "",
                                                        "§7Iron Ore + Night Vision")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.PHOTOSYNTHESIS), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.PHOTOSYNTHESIS,
                                                        "\n\n§aFed by the divine star.§r",
                                                        "",
                                                        "§7Leaf Litter + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.PURIFICATION), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.PURIFICATION,
                                                        "\n\n§fClean slate.§r",
                                                        "",
                                                        "§7Milk Bucket + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.REACTIVATION), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.REACTIVATION,
                                                        "\n\n§eCritical moment.§r",
                                                        "",
                                                        "§7Sunflower + Short Cooldown")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.RESONANCE), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.RESONANCE,
                                                        "\n\n§dThe pulse of the earth.§r",
                                                        "",
                                                        "§7Echo Shard + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.INFINITY), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.INFINITY,
                                                        "\n\n§5The boundless source.§r",
                                                        "",
                                                        "Obtained through advanced alchemy.")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.RUST), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.RUST,
                                                        "\n\n§6The age of rust.§r",
                                                        "",
                                                        "§7Oxydation + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.SATURATION), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.SATURATION,
                                                        "\n\n§cThe concentrated feast.§r",
                                                        "",
                                                        "§7Beetroot + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.SHORT_COOLDOWN), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.SHORT_COOLDOWN,
                                                        "\n\n§eSurhuman vivacity.§r",
                                                        "",
                                                        "§7Sunflower + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.STUN), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.STUN,
                                                        "\n\n§8Synaptic overload.§r",
                                                        "",
                                                        "§7Charged Copper + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.TELEPORTATION), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.TELEPORTATION,
                                                        "\n\n§5Unstable quantum leap.§r",
                                                        "",
                                                        "§7Ender Pearl + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.THORNS), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.THORNS,
                                                        "\n\n§2Thorns of flesh.§r",
                                                        "",
                                                        "§7Cactus + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.UNSTABLE), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.UNSTABLE,
                                                        "\n\n§6A powder keg on legs.§r",
                                                        "",
                                                        "Obtained through alchemical rituals.")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.VAMPIRISM), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.VAMPIRISM,
                                                        "\n\n§cBlood for blood.§r",
                                                        "",
                                                        "§7Blood Bag + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.XP_BOOST), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.XP_BOOST,
                                                        "\n\n§bSpirit of learning.§r",
                                                        "",
                                                        "§7Lapis Lazuli + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.XP_REDUCTION), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.XP_REDUCTION,
                                                        "\n\n§8Amnesia of the soul.§r",
                                                        "",
                                                        "§7Fermented Eye + XP Boost")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.XP_LIFE), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.XP_LIFE,
                                                        "\n\n§aSacrificing lore for flesh.§r",
                                                        "",
                                                        "§7Glistering Melon + XP Boost")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.ZEUS), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.ZEUS,
                                                        "\n\n§6Wrath of the heavens.§r",
                                                        "",
                                                        "§7Materia Prima + Stun")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.COLD), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.COLD,
                                                        "\n\n§bWinter torpor.§r",
                                                        "",
                                                        "§7Snowball + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.CLUMSINESS), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.CLUMSINESS,
                                                        "\n\n§7Butterfingers.§r",
                                                        "",
                                                        "Brewing formula lost to time.")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.ASTHMA), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.ASTHMA,
                                                        "\n\n§8Lungs of ash.§r",
                                                        "",
                                                        "Brewing formula lost to time.")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.PARANOIA), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.PARANOIA,
                                                        "\n\n§5Shadows in the corners.§r",
                                                        "",
                                                        "Brewing formula lost to time.")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.MEDUSA), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.MEDUSA,
                                                        "\n\n§2The fatal gaze.§r",
                                                        "",
                                                        "§7Materia Prima + Strong Petrification")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.MIDAS), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.MIDAS,
                                                        "\n\n§6The gilded curse.§r",
                                                        "",
                                                        "§7Materia Prima + Alchemist")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.ACTIVE_TELEPORT), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.ACTIVE_TELEPORT,
                                                        "\n\n§5Focused displacement.§r",
                                                        "",
                                                        "§7Sulfur Ball + Teleportation")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.HYDROPHOBIA), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.HYDROPHOBIA,
                                                        "\n\n§8Rejection of the waves.§r",
                                                        "",
                                                        "§7Fermented Eye + Water Breathing")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.ZOMBIE_CONTAGION), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.ZOMBIE_CONTAGION,
                                                        "\n\n§2The plague of grave-gnawers.§r",
                                                        "",
                                                        "§7Rotten Flesh + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.MAGNETISM), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.MAGNETISM,
                                                        "\n\n§bAttractive gravity.§r",
                                                        "",
                                                        "§7Iron Ingot + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.REALITY_CHECK), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.REALITY_CHECK,
                                                        "\n\n§5Cognitive collapse.§r",
                                                        "",
                                                        "Brewing formula lost to time.")
                                        )
                                        .subChapter(BookUtils.getEffectName(Potions.FIRE_RESISTANCE), sub ->
                                                BookUtils.createPotionChapter(sub, Potions.FIRE_RESISTANCE,
                                                        "\n\n§6Skin of the salamander.§r",
                                                        "",
                                                        "§7Magma Block + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(Potions.REGENERATION), sub ->
                                                BookUtils.createPotionChapter(sub, Potions.REGENERATION,
                                                        "\n\n§cCellular blossoming.§r",
                                                        "",
                                                        "§7Golden Apple + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(Potions.STRENGTH), sub ->
                                                BookUtils.createPotionChapter(sub, Potions.STRENGTH,
                                                        "\n\n§cSurge of raw power.§r",
                                                        "",
                                                        "§7Firework Star + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(Potions.SWIFTNESS), sub ->
                                                BookUtils.createPotionChapter(sub, Potions.SWIFTNESS,
                                                        "\n\n§bOutrunning time.§r",
                                                        "",
                                                        "§7Sugar + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(Potions.POISON), sub ->
                                                BookUtils.createPotionChapter(sub, Potions.POISON,
                                                        "\n\n§aThe alchemist's blight.§r",
                                                        "",
                                                        "§7Poisonous Carrot + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(Potions.HEALING), sub ->
                                                BookUtils.createPotionChapter(sub, Potions.HEALING,
                                                        "\n\n§cInstant flash of life.§r",
                                                        "",
                                                        "§7Glistering Melon + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(Potions.NIGHT_VISION), sub ->
                                                BookUtils.createPotionChapter(sub, Potions.NIGHT_VISION,
                                                        "\n\n§ePupils of the feline.§r",
                                                        "",
                                                        "§7Golden Carrot + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(Potions.INVISIBILITY), sub ->
                                                BookUtils.createPotionChapter(sub, Potions.INVISIBILITY,
                                                        "\n\n§8Refraction of light.§r",
                                                        "",
                                                        "§7Fermented Eye + Night Vision")
                                        )
                                        .subChapter(BookUtils.getEffectName(Potions.WATER_BREATHING), sub ->
                                                BookUtils.createPotionChapter(sub, Potions.WATER_BREATHING,
                                                        "\n\n§bEphemeral gills.§r",
                                                        "",
                                                        "§7Pufferfish + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(Potions.LEAPING), sub ->
                                                BookUtils.createPotionChapter(sub, Potions.LEAPING,
                                                        "\n\n§3Defying heights.§r",
                                                        "",
                                                        "§7Rabbit Foot + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(Potions.TURTLE_MASTER), sub ->
                                                BookUtils.createPotionChapter(sub, Potions.TURTLE_MASTER,
                                                        "\n\n§2The shell of the mind.§r",
                                                        "",
                                                        "§7Turtle Shell + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(Potions.SLOW_FALLING), sub ->
                                                BookUtils.createPotionChapter(sub, Potions.SLOW_FALLING,
                                                        "\n\n§fPlume descent.§r",
                                                        "",
                                                        "§7Phantom Membrane + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(Potions.WEAKNESS), sub ->
                                                BookUtils.createPotionChapter(sub, Potions.WEAKNESS,
                                                        "\n\n§7Lethargy of the muscles.§r",
                                                        "",
                                                        "§7Fermented Eye + Strength")
                                        )
                                        .subChapter(BookUtils.getEffectName(Potions.SLOWNESS), sub ->
                                                BookUtils.createPotionChapter(sub, Potions.SLOWNESS,
                                                        "\n\n§7Leaden pace.§r",
                                                        "",
                                                        "§7Fermented Eye + Swiftness")
                                        )
                                        .subChapter(BookUtils.getEffectName(Potions.HARMING), sub ->
                                                BookUtils.createPotionChapter(sub, Potions.HARMING,
                                                        "\n\n§cDistilled bane.§r",
                                                        "",
                                                        "§7Fermented Eye + Healing")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.PERM_HEALTH), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.PERM_HEALTH,
                                                        "\n\n§cThe flesh transcended.§r",
                                                        "",
                                                        "Obtained through alchemical rituals.")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.PERM_SPEED), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.PERM_SPEED,
                                                        "\n\n§bEternal velocity.§r",
                                                        "",
                                                        "Obtained through alchemical rituals.")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.PERM_STRENGTH), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.PERM_STRENGTH,
                                                        "\n\n§cMark of the Titan.§r",
                                                        "",
                                                        "Obtained through alchemical rituals.")
                                        )
                                )
        );
    }
}