package com.matibi.potionsnrituals.item;

import com.matibi.potionsnrituals.book.BookStructure;
import com.matibi.potionsnrituals.potion.ModPotions;
import com.matibi.potionsnrituals.util.BookUtils;
import com.matibi.potionsnrituals.util.ModUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potions;

public class NigredoBook extends CustomBook {
    public NigredoBook() {
        super(new Item.Properties().setId(ResourceKey.create(
                        Registries.ITEM,
                        ModUtils.id("alchemy_guide_nigredo"))
                ).stacksTo(1), () ->
                        new BookStructure(Component.translatable("item.potions-n-rituals.alchemy_guide_nigredo"))
                                .tableOfContents("Sommaire")

                                // ── Chapitre : Potions Customs ──────────────────────────────────
                                .chapter("Potions", alch -> alch

                                        // ── Vanilla extended ──
                                        .subChapter(BookUtils.getEffectName(ModPotions.LEVITATION), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.LEVITATION,
                                                        "\n\n§5Gravity is merely a suggestion.§r",
                                                        "The ground surrenders from beneath your feet. A sensation of absolute weightlessness—perfect for escaping the mire of the world, though a sudden end to the effect guarantees a cruel reunion with the earth.",
                                                        "§7Feather + Slow Falling")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.GLOWING), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.GLOWING,
                                                        "\n\n§eBecome the beacon in the dark.§r",
                                                        "Your very essence radiates through solid matter. Hidden paths are rendered useless as you burn brightly, like a captured star.",
                                                        "§7Glow Berries + Night Vision")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.ALCOHOL), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.ALCOHOL,
                                                        "\n\n§2*hic*... Just one last drop.§r",
                                                        "The world tilts, gravity sways, and your steps become an uncertain gamble. It holds no medical value, yet the mind wanders pleasantly.",
                                                        "§7Sweet Berries + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.DARKNESS), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.DARKNESS,
                                                        "\n\n§8The void settles in.§r",
                                                        "A veil of absolute shadow drapes over your eyes. Within this profound blackness, the whispers of the abyss materialize threats that light can no longer banish.",
                                                        "§7Ink Sac + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.HASTE), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.HASTE,
                                                        "\n\n§eMetallic frenzy.§r",
                                                        "Your limbs move before your mind can even command the gesture. Solid rock yields under your strikes with terrifying swiftness.",
                                                        "§7Claw + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.MINING_FATIGUE), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.MINING_FATIGUE,
                                                        "\n\n§7The weight of the world.§r",
                                                        "Your limbs turn to lead. Every swing of an utensil demands superhuman effort, shifting the smallest pebble into an immovable mountain.",
                                                        "§7Fermented Eye + Haste")
                                        )

                                        // ── Permanent ──
                                        .subChapter(BookUtils.getEffectName(ModPotions.PERM_HEALTH), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.PERM_HEALTH,
                                                        "\n\n§cThe flesh transcended.§r",
                                                        "A definitive alteration of your constitution. Your vital spark is anchored far deeper into this plane of existence.",
                                                        "Obtained through alchemical rituals.")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.PERM_SPEED), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.PERM_SPEED,
                                                        "\n\n§bEternal velocity.§r",
                                                        "Time itself seems to slacken around your stride. Your legs no longer obey the mortal laws of fatigue.",
                                                        "Obtained through alchemical rituals.")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.PERM_STRENGTH), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.PERM_STRENGTH,
                                                        "\n\n§cMark of the Titan.§r",
                                                        "Your muscles become saturated with a raw, irreversible power. Your fists can crack iron without flinching.",
                                                        "Obtained through alchemical rituals.")
                                        )

                                        // ── Terrain ──
                                        .subChapter(BookUtils.getEffectName(ModPotions.ACID), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.ACID,
                                                        "\n\n§aIt hisses, it gnaws, it dissolves.§r",
                                                        "A highly corrosive concoction that liquefies steel armor and devours living tissue within seconds. Do not spill onto one's boots.",
                                                        "§7Rotten Flesh + Poison")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.ALCHEMIST), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.ALCHEMIST,
                                                        "\n\n§6The dream of Midas (stable variation).§r",
                                                        "Infuses a spark of transmutation into your touch. Vulgar copper awakens, reorganizing its atomic lattice into far more precious metals.",
                                                        "§7Copper Ingot + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.GIANT), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.GIANT,
                                                        "\n\n§3Looking down upon the world.§r",
                                                        "Your bones elongate; your mass increases tenfold. You become a destructive force of nature, yet also an unmissable target.",
                                                        "§7Bone Meal + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.IGNITION), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.IGNITION,
                                                        "\n\n§6The inner fire.§r",
                                                        "A vial of pure thermal instability. Whosoever comes into contact with this essence combusts instantly from within.",
                                                        "§7Fermented Eye + Fire Resistance")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.PETRIFICATION), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.PETRIFICATION,
                                                        "\n\n§8Freezing the blood.§r",
                                                        "Joints lock up, skin calcifies, and flesh hardens into barren stone. A total and terrifying paralysis.",
                                                        "§7Obsidian + Turtle Master")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.RESURRECTION), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.RESURRECTION,
                                                        "\n\n§dTricking the reaper.§r",
                                                        "An insurance policy upon the soul. Should the thread of your life be cut short, this elixir violently stitches your mortal vessel back together.",
                                                        "§7Totem of Undying + Awkward")
                                        )

                                        // ── Normal effects ──
                                        .subChapter(BookUtils.getEffectName(ModPotions.ADHESION), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.ADHESION,
                                                        "\n\nMmh that's a bit...\n\n§2sticky§r.",
                                                        "You stick to surfaces, allowing you to walk on walls.",
                                                        "Find something §2sticky§r, maybe like resin. An additional awkward potion should be sufficient.")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.BERSERK), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.BERSERK,
                                                        "\n\n§cBlind fury.§r",
                                                        "Pain becomes your fuel. You strike with the fury of a fiend, but every blow received echoes twice as loudly within your bones.",
                                                        "§7Torchflower + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.BRAINWASHING), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.BRAINWASHING,
                                                        "\n\n§dPutrid marionettes.§r",
                                                        "Shatters what little willpower remains within the undead. They forget their hunger, crawling to your feet to slay your foes.",
                                                        "§7Zombie Brain + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.DEATH), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.DEATH,
                                                        "\n\n§8The definitive stop.§r",
                                                        "No cure, no prolonged agony. The heart stops instantly. Handle with thick gloves... and extreme paranoia.",
                                                        "§7Wither Rose + Resurrection")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.DOUBLE_HEALTH), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.DOUBLE_HEALTH,
                                                        "\n\n§cA second surge of life.§r",
                                                        "An alchemical rush of adrenaline that temporarily doubles your blood reserves. Splendid when death comes knocking at the door.",
                                                        "§7Golden Apple + Strong Healing")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.DWARF), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.DWARF,
                                                        "\n\n§3A mouse's perspective.§r",
                                                        "Shrink down until you can slip into the narrowest of fissures. You forfeit your reach for unmatched discretion.",
                                                        "§7Fermented Eye + Giant")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.FROST), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.FROST,
                                                        "\n\n§bThe kiss of winter.§r",
                                                        "Water turns to ice beneath your steps, and the air crystallizes. Enemies shiver and slow, caught within your frozen aura.",
                                                        "§7Snowball + Cold")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.GHOST_WALK), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.GHOST_WALK,
                                                        "\n\n§7Passing through the veil.§r",
                                                        "Solid matter becomes nothing more than a mirage. Walk through walls like a specter, but do not forget to return to reality before the elixir fades.",
                                                        "§7Ghast Tear + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.LIQUID_WALKER), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.LIQUID_WALKER,
                                                        "\n\n§3Fluid density.§r",
                                                        "The surface tension of liquids alters beneath your feet. Water and lava become as firm as cobblestone pavement.",
                                                        "§7Lily Pad + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.LONG_COOLDOWN), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.LONG_COOLDOWN,
                                                        "\n\n§8Temporal numbness.§r",
                                                        "Your muscles react with a dreadful delay. A curse that stretches the seconds between each of your actions.",
                                                        "§7Fermented Eye + Short Cooldown")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.LONG_LEG), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.LONG_LEG,
                                                        "\n\n§3Des enjambées de géant.§r",
                                                        "Your lower limbs stretch disproportionately. Overcoming high obstacles becomes mere child's play.",
                                                        "§7Bamboo + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.LOVE), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.LOVE,
                                                        "\n\n§dPeaceful euphoria.§r",
                                                        "Diffuses magical pheromones so potent that even the most ferocious monsters forget their hatred to beg for a gentle touch.",
                                                        "§7Poppy + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.MASKING), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.MASKING,
                                                        "\n\n§8Total erasure.§r",
                                                        "Far more than mere invisibility: this solution erases your scent, your footsteps, and your psychical presence from nearby predators.",
                                                        "§7Fermented Eye + Invisibility")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.NO_INTERACTION), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.NO_INTERACTION,
                                                        "\n\n§8Spectator of the world.§r",
                                                        "You temporarily lose the capacity to act upon matter. You pass through things without being able to touch them. Frustrating, yet safe.",
                                                        "§7Rabbit Foot + Brainwashing")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.OBLIVION), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.OBLIVION,
                                                        "\n\n§5The great void.§r",
                                                        "Erases recent memories and fogs the mind. Useful for making a misdeed forgotten, or for losing oneself within the limbo.",
                                                        "§7Pitcher Plant + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.ORE_SENSE), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.ORE_SENSE,
                                                        "\n\n§eThe call of the rock.§r",
                                                        "Stone turns translucent to your eyes, imprinting itself with the energetic signatures of precious ores hidden in the depths.",
                                                        "§7Iron Ore + Night Vision")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.PHOTOSYNTHESIS), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.PHOTOSYNTHESIS,
                                                        "\n\n§aFed by the divine star.§r",
                                                        "Your skin reacts to the sun's rays, converting pure light into nutrients and cellular regeneration.",
                                                        "§7Leaf Litter + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.PURIFICATION), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.PURIFICATION,
                                                        "\n\n§fClean slate.§r",
                                                        "A sweeping cleanse of your metabolism. Casts out the worst of curses along with the most divine blessings.",
                                                        "§7Milk Bucket + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.REACTIVATION), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.REACTIVATION,
                                                        "\n\n§eCritical moment.§r",
                                                        "Overloads your nervous system to instantly reset all ability recovery times. Ready to strike anew.",
                                                        "§7Sunflower + Short Cooldown")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.RESONANCE), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.RESONANCE,
                                                        "\n\n§dThe pulse of the earth.§r",
                                                        "Invisible shockwaves emanate from your body, instantly mapping out the location of every beating heart nearby.",
                                                        "§7Echo Shard + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.INFINITY), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.INFINITY,
                                                        "\n\n§5The boundless source.§r",
                                                        "A spatial distortion applies to your possessions: that which should be consumed or broken regenerates instantly.",
                                                        "Obtained through advanced alchemy.")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.RUST), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.RUST,
                                                        "\n\n§6The age of rust.§r",
                                                        "You emanate an acidic humidity that oxidizes iron and degrades your opponents' metallic tools in the blink of an eye.",
                                                        "§7Oxydation + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.SATURATION), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.SATURATION,
                                                        "\n\n§cThe concentrated feast.§r",
                                                        "A single sip yields the caloric equivalent of ten banquets. No longer will you be burdened by carrying vulgar food.",
                                                        "§7Beetroot + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.SHORT_COOLDOWN), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.SHORT_COOLDOWN,
                                                        "\n\n§eSurhuman vivacity.§r",
                                                        "Accelerates the perception of your own body, diminishing the waiting time between combat maneuvers.",
                                                        "§7Sunflower + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.STUN), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.STUN,
                                                        "\n\n§8Synaptic overload.§r",
                                                        "Freezes the target's nervous system. The body refuses to obey, leaving the victim entirely at your mercy.",
                                                        "§7Charged Copper + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.TELEPORTATION), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.TELEPORTATION,
                                                        "\n\n§5Unstable quantum leap.§r",
                                                        "Dissolves your molecules to reassemble them a few paces away. Practical, yet the destination remains subject to the whims of chance.",
                                                        "§7Ender Pearl + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.THORNS), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.THORNS,
                                                        "\n\n§2Thorns of flesh.§r",
                                                        "Your skin grows an abrasive barrier of kinetic energy. Whosoever dares to strike you suffers the rebound of their own violence.",
                                                        "§7Cactus + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.UNSTABLE), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.UNSTABLE,
                                                        "\n\n§6A powder keg on legs.§r",
                                                        "Your internal energy threatens to detonate at the slightest spark or unusually violent jolt.",
                                                        "Obtained through alchemical rituals.")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.VAMPIRISM), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.VAMPIRISM,
                                                        "\n\n§cBlood for blood.§r",
                                                        "Establishes an occult bridge of vitality: every wound inflicted upon your foes drains their essence to mend your own gashes.",
                                                        "§7Blood Bag + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.XP_BOOST), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.XP_BOOST,
                                                        "\n\n§bSpirit of learning.§r",
                                                        "Opens your chakras to the world's memory, allowing you to glean far deeper knowledge from every experience.",
                                                        "§7Lapis Lazuli + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.XP_REDUCTION), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.XP_REDUCTION,
                                                        "\n\n§8Amnesia of the soul.§r",
                                                        "A haze descends upon your intellect, sapping your capacity to retain the lessons of your battles. A burden to avoid.",
                                                        "§7Fermented Eye + XP Boost")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.XP_LIFE), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.XP_LIFE,
                                                        "\n\n§aSacrificing lore for flesh.§r",
                                                        "Literally burns away your accumulated memories and experience to catalyze an emergency mending of the body.",
                                                        "§7Glistering Melon + XP Boost")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.ZEUS), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.ZEUS,
                                                        "\n\n§6Wrath of the heavens.§r",
                                                        "You become a living lightning rod for the tempest's fury. Lightning strikes wherever your angered gaze falls.",
                                                        "§7Materia Prima + Stun")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.COLD), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.COLD,
                                                        "\n\n§bWinter torpor.§r",
                                                        "Cold seeps beneath armor, numbing muscles and slowing the heartbeat.",
                                                        "§7Snowball + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.CLUMSINESS), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.CLUMSINESS,
                                                        "\n\n§7Butterfingers.§r",
                                                        "A chronic ineptitude takes hold of you. Tools slip from your grasp, and your attacks lose all precision.",
                                                        "Brewing formula lost to time.")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.ASTHMA), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.ASTHMA,
                                                        "\n\n§8Lungs of ash.§r",
                                                        "Air grows scarce within your lungs. Running becomes an ordeal, and exhaustion stalks your every effort.",
                                                        "Brewing formula lost to time.")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.PARANOIA), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.PARANOIA,
                                                        "\n\n§5Shadows in the corners.§r",
                                                        "Your mind invents threats where there is only wind. Stay on your guard, lest you lose your sanity entirely.",
                                                        "Brewing formula lost to time.")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.MEDUSA), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.MEDUSA,
                                                        "\n\n§2The fatal gaze.§r",
                                                        "Your eyes channel an ancient, mythological terror: gaze upon a creature to instantly transfigure its vital fluids into granite.",
                                                        "§7Materia Prima + Strong Petrification")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.MIDAS), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.MIDAS,
                                                        "\n\n§6The gilded curse.§r",
                                                        "Fortune comes at a terrible price. Everything your hands brush against freezes into a brilliant, yet barren sheen of solid gold.",
                                                        "§7Materia Prima + Alchemist")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.ACTIVE_TELEPORT), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.ACTIVE_TELEPORT,
                                                        "\n\n§5Focused displacement.§r",
                                                        "A refined and tethered translocation. Your consciousness projects itself exactly where your eyes point.",
                                                        "§7Sulfur Ball + Teleportation")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.HYDROPHOBIA), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.HYDROPHOBIA,
                                                        "\n\n§8Rejection of the waves.§r",
                                                        "Your skin develops a violent aversion to moisture. The slightest raindrop burns like molten metal.",
                                                        "§7Fermented Eye + Water Breathing")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.ZOMBIE_CONTAGION), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.ZOMBIE_CONTAGION,
                                                        "\n\n§2The plague of grave-gnawers.§r",
                                                        "Death shall not be the end, but the dawn of a putrid torment. Should you fall, you will rise again, starved for raw flesh.",
                                                        "§7Rotten Flesh + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.MAGNETISM), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.MAGNETISM,
                                                        "\n\n§bAttractive gravity.§r",
                                                        "You become the epicenter of a low-intensity magnetic vortex, drawing abandoned objects directly toward your pockets.",
                                                        "§7Iron Ingot + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(ModPotions.REALITY_CHECK), sub ->
                                                BookUtils.createPotionChapter(sub, ModPotions.REALITY_CHECK,
                                                        "\n\n§5Cognitive collapse.§r",
                                                        "The world's code flickers before your eyes. Is this real? Hallucinations begin to warp your immediate surroundings.",
                                                        "Brewing formula lost to time.")
                                        )
                                        .subChapter(BookUtils.getEffectName(Potions.FIRE_RESISTANCE), sub ->
                                                BookUtils.createPotionChapter(sub, Potions.FIRE_RESISTANCE,
                                                        "\n\n§6Skin of the salamander.§r",
                                                        "Flames slide off you without consuming your flesh, and lava envelops you like a lukewarm bath.",
                                                        "§7Magma Block + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(Potions.REGENERATION), sub ->
                                                BookUtils.createPotionChapter(sub, Potions.REGENERATION,
                                                        "\n\n§cCellular blossoming.§r",
                                                        "Your tissues tear and re-stitch themselves with miraculous speed. An indispensable panacea for any duelist.",
                                                        "§7Golden Apple + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(Potions.STRENGTH), sub ->
                                                BookUtils.createPotionChapter(sub, Potions.STRENGTH,
                                                        "\n\n§cSurge of raw power.§r",
                                                        "Saturates your muscular fibers with explosive energy to exponentially increase the impact of your melee weapons.",
                                                        "§7Firework Star + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(Potions.SWIFTNESS), sub ->
                                                BookUtils.createPotionChapter(sub, Potions.SWIFTNESS,
                                                        "\n\n§bOutrunning time.§r",
                                                        "Lightens your stride, allowing you to sprint breathlessly without apparent effort.",
                                                        "§7Sugar + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(Potions.POISON), sub ->
                                                BookUtils.createPotionChapter(sub, Potions.POISON,
                                                        "\n\n§aThe alchemist's blight.§r",
                                                        "An underhanded toxin that attacks vital functions, eroding health bit by bit without ever dealing the final blow.",
                                                        "§7Poisonous Carrot + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(Potions.HEALING), sub ->
                                                BookUtils.createPotionChapter(sub, Potions.HEALING,
                                                        "\n\n§cInstant flash of life.§r",
                                                        "Mends open wounds in a heartbeat. Divine intervention condensed into glass.",
                                                        "§7Glistering Melon + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(Potions.NIGHT_VISION), sub ->
                                                BookUtils.createPotionChapter(sub, Potions.NIGHT_VISION,
                                                        "\n\n§ePupils of the feline.§r",
                                                        "Dilates your senses to pierce the shadows of the deepest caverns without relying on fire.",
                                                        "§7Golden Carrot + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(Potions.INVISIBILITY), sub ->
                                                BookUtils.createPotionChapter(sub, Potions.INVISIBILITY,
                                                        "\n\n§8Refraction of light.§r",
                                                        "Your body ceases to reflect luminous rays. You become an absence, so long as you wear nothing to betray yourself.",
                                                        "§7Fermented Eye + Night Vision")
                                        )
                                        .subChapter(BookUtils.getEffectName(Potions.WATER_BREATHING), sub ->
                                                BookUtils.createPotionChapter(sub, Potions.WATER_BREATHING,
                                                        "\n\n§bEphemeral gills.§r",
                                                        "Allows your lungs to filter oxygen directly from the waves. The ocean becomes your manor.",
                                                        "§7Pufferfish + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(Potions.LEAPING), sub ->
                                                BookUtils.createPotionChapter(sub, Potions.LEAPING,
                                                        "\n\n§3Defying heights.§r",
                                                        "Compresses kinetic energy within your thighs for spectacular leaps while dampening the landing.",
                                                        "§7Rabbit Foot + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(Potions.TURTLE_MASTER), sub ->
                                                BookUtils.createPotionChapter(sub, Potions.TURTLE_MASTER,
                                                        "\n\n§2The shell of the mind.§r",
                                                        "Anchors your body so firmly to the ground that almost nothing can shake you, at the expense of all agility.",
                                                        "§7Turtle Shell + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(Potions.SLOW_FALLING), sub ->
                                                BookUtils.createPotionChapter(sub, Potions.SLOW_FALLING,
                                                        "\n\n§fPlume descent.§r",
                                                        "Gravity temporarily forgets your mass, leaving you to float lazily toward the earth during falls.",
                                                        "§7Phantom Membrane + Awkward")
                                        )
                                        .subChapter(BookUtils.getEffectName(Potions.WEAKNESS), sub ->
                                                BookUtils.createPotionChapter(sub, Potions.WEAKNESS,
                                                        "\n\n§7Lethargy of the muscles.§r",
                                                        "Drains the vigor of the target, turning their fierce blows into vain and futile grazes.",
                                                        "§7Fermented Eye + Strength")
                                        )
                                        .subChapter(BookUtils.getEffectName(Potions.SLOWNESS), sub ->
                                                BookUtils.createPotionChapter(sub, Potions.SLOWNESS,
                                                        "\n\n§7Leaden pace.§r",
                                                        "Hampers the target's run as though they were wading through a thick, invisible mire.",
                                                        "§7Fermented Eye + Swiftness")
                                        )
                                        .subChapter(BookUtils.getEffectName(Potions.HARMING), sub ->
                                                BookUtils.createPotionChapter(sub, Potions.HARMING,
                                                        "\n\n§cDistilled bane.§r",
                                                        "A violent, magical disruption of cellular ties that deals heavy direct damage to the flesh.",
                                                        "§7Fermented Eye + Healing")
                                        )
                                )
        );
    }
}