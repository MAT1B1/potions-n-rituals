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
                                            "\n\n§5Up, up and away§r.",
                                            "You gently float upward, defying gravity. Great for reaching high places or escaping danger.",
                                            "§7Feather + Slow Falling")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.GLOWING), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.GLOWING,
                                            "\n\n§eBe the light§r.",
                                            "You emit a bright glow visible through walls, making you easy to spot.",
                                            "§7Glow Berries + Night Vision")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.ALCOHOL), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.ALCOHOL,
                                            "\n\n§2*hic*§r.",
                                            "The world spins around you. Not the most useful effect, but entertaining.",
                                            "§7Sweet Berries + Awkward")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.DARKNESS), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.DARKNESS,
                                            "\n\n§8Darkness consumes§r.",
                                            "Your vision narrows to a small circle. Hostile mobs can spawn despite the light.",
                                            "§7Ink Sac + Awkward")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.HASTE), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.HASTE,
                                            "\n\n§eMine faster!§r.",
                                            "Your mining speed increases significantly. Pairs well with efficiency tools.",
                                            "§7Claw + Awkward")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.MINING_FATIGUE), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.MINING_FATIGUE,
                                            "\n\n§7So heavy...§r.",
                                            "Your arms feel like lead. Mining becomes painfully slow.",
                                            "§7Fermented Eye + Haste")
                            )

                            // ── Permanent ──
                            .subChapter(BookUtils.getEffectName(ModPotions.PERM_HEALTH), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.PERM_HEALTH,
                                            "\n\n§cHealth, permanently§r.",
                                            "Increases your maximum health permanently. A true body enhancement.",
                                            "Obtained through alchemical rituals.")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.PERM_SPEED), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.PERM_SPEED,
                                            "\n\n§bFaster forever§r.",
                                            "Permanently increases your movement speed. You'll never walk the same again.",
                                            "Obtained through alchemical rituals.")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.PERM_STRENGTH), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.PERM_STRENGTH,
                                            "\n\n§cUnstoppable power§r.",
                                            "Permanently increases your attack damage. A true warrior's blessing.",
                                            "Obtained through alchemical rituals.")
                            )

                            // ── Terrain ──
                            .subChapter(BookUtils.getEffectName(ModPotions.ACID), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.ACID,
                                            "\n\n§aCorrosive touch§r.",
                                            "Melts through armor and flesh. Deals damage over time to anything nearby.",
                                            "§7Rotten Flesh + Poison")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.ALCHEMIST), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.ALCHEMIST,
                                            "\n\n§6Transmutation master§r.",
                                            "Transforms copper into more valuable resources. A must-have for any alchemist.",
                                            "§7Copper Ingot + Awkward")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.GIANT), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.GIANT,
                                            "\n\n§3Fee fi fo fum§r.",
                                            "You grow to twice your size, gaining reach and strength but becoming an easier target.",
                                            "§7Bone Meal + Awkward")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.IGNITION), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.IGNITION,
                                            "\n\n§6Burn, baby, burn§r.",
                                            "Sets entities on fire. Excellent for crowd control.",
                                            "§7Fermented Eye + Fire Resistance")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.PETRIFICATION), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.PETRIFICATION,
                                            "\n\n§8Stone cold§r.",
                                            "Turns living creatures to stone, immobilizing them completely.",
                                            "§7Obsidian + Turtle Master")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.RESURRECTION), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.RESURRECTION,
                                            "\n\n§dLife after death§r.",
                                            "Prevents death. Upon taking fatal damage, you are revived instead.",
                                            "§7Totem of Undying + Awkward")
                            )

                            // ── Normal effects ──
                            .subChapter(BookUtils.getEffectName(ModPotions.ADHESION), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.ADHESION,
                                            "\n\nMmh that's a bit...\n\n§2sticky§r.",
                                            "You stick to surfaces, allowing you to walk on walls and ceilings.",
                                            "Something §2sticky§r, like resin, plus a base potion.")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.BERSERK), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.BERSERK,
                                            "\n\n§cRage mode§r.",
                                            "Your attack power increases dramatically, but you feel every hit more intensely.",
                                            "§7Torchflower + Awkward")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.BRAINWASHING), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.BRAINWASHING,
                                            "\n\n§dMind control§r.",
                                            "Zombies and other undead fight on your side, bending to your will.",
                                            "§7Zombie Brain + Awkward")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.DEATH), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.DEATH,
                                            "\n\n§8The end§r.",
                                            "Instant death. Handle with extreme caution — this is the ultimate weapon.",
                                            "§7Wither Rose + Resurrection")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.DOUBLE_HEALTH), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.DOUBLE_HEALTH,
                                            "\n\n§cDouble the hearts§r.",
                                            "Doubles your current health pool temporarily. Life-saving in a pinch.",
                                            "§7Golden Apple + Strong Healing")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.DWARF), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.DWARF,
                                            "\n\n§3Short but strong§r.",
                                            "You shrink down, becoming harder to hit but losing reach.",
                                            "§7Fermented Eye + Giant")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.FROST), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.FROST,
                                            "\n\n§bFreeze frame§r.",
                                            "Freezes water around you and slows enemies. Walk on water safely.",
                                            "§7Snowball + Cold")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.GHOST_WALK), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.GHOST_WALK,
                                            "\n\n§7Through the veil§r.",
                                            "Phase through solid matter. Walk through walls like a true specter.",
                                            "§7Ghast Tear + Awkward")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.LIQUID_WALKER), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.LIQUID_WALKER,
                                            "\n\n§3Walk on water§r.",
                                            "You can walk on the surface of water and lava as if it were solid ground.",
                                            "§7Lily Pad + Awkward")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.LONG_COOLDOWN), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.LONG_COOLDOWN,
                                            "\n\n§8Time slows§r.",
                                            "Increases the cooldown time between your actions. A hindrance effect.",
                                            "§7Fermented Eye + Short Cooldown")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.LONG_LEG), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.LONG_LEG,
                                            "\n\n§3Step high§r.",
                                            "Your legs grow longer, increasing your step height and jump reach.",
                                            "§7Bamboo + Awkward")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.LOVE), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.LOVE,
                                            "\n\n§dLove is in the air§r.",
                                            "Creatures become passive and friendly towards you. Even monsters feel the love.",
                                            "§7Poppy + Awkward")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.MASKING), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.MASKING,
                                            "\n\n§8Hidden in plain sight§r.",
                                            "Mobs no longer detect you even when you're close. Superior invisibility.",
                                            "§7Fermented Eye + Invisibility")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.NO_INTERACTION), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.NO_INTERACTION,
                                            "\n\n§8Can't touch this§r.",
                                            "Prevents you from interacting with the world. Use with caution.",
                                            "§7Rabbit Foot + Brainwashing")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.OBLIVION), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.OBLIVION,
                                            "\n\n§5Forgotten§r.",
                                            "Erases memory of recent events. A mysterious and unpredictable effect.",
                                            "§7Pitcher Plant + Awkward")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.ORE_SENSE), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.ORE_SENSE,
                                            "\n\n§eX-ray vision§r.",
                                            "Ores glow through stone, revealing valuable resources around you.",
                                            "§7Iron Ore + Night Vision")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.PHOTOSYNTHESIS), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.PHOTOSYNTHESIS,
                                            "\n\n§aSolar powered§r.",
                                            "Standing in sunlight regenerates your health and feeds you.",
                                            "§7Leaf Litter + Awkward")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.PURIFICATION), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.PURIFICATION,
                                            "\n\n§fCleansed§r.",
                                            "Removes all status effects, both good and bad. A fresh start.",
                                            "§7Milk Bucket + Awkward")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.REACTIVATION), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.REACTIVATION,
                                            "\n\n§eReset§r.",
                                            "Resets your cooldowns, allowing you to use abilities again immediately.",
                                            "§7Sunflower + Short Cooldown")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.RESONANCE), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.RESONANCE,
                                            "\n\n§dVibrations§r.",
                                            "Sonar-like pulses reveal nearby entities. Useful for finding hidden mobs.",
                                            "§7Echo Shard + Awkward")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.INFINITY), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.INFINITY,
                                            "\n\n§5Endless§r.",
                                            "Your items never run out. A truly boundless blessing.",
                                            "Obtained through advanced alchemy.")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.RUST), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.RUST,
                                            "\n\n§6Corroding§r.",
                                            "Metal objects decay around you, weakening armor and tools.",
                                            "§7Oxydation + Awkward")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.SATURATION), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.SATURATION,
                                            "\n\n§cFull belly§r.",
                                            "Instantly fills your hunger bar. Great for long expeditions.",
                                            "§7Beetroot + Awkward")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.SHORT_COOLDOWN), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.SHORT_COOLDOWN,
                                            "\n\n§eFaster actions§r.",
                                            "Reduces cooldown times, letting you act more frequently.",
                                            "§7Sunflower + Awkward")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.STUN), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.STUN,
                                            "\n\n§8Can't move§r.",
                                            "Paralyzes the target, preventing all movement and action.",
                                            "§7Charged Copper + Awkward")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.TELEPORTATION), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.TELEPORTATION,
                                            "\n\n§5Blink§r.",
                                            "Randomly teleports you a short distance. Unpredictable but handy.",
                                            "§7Ender Pearl + Awkward")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.THORNS), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.THORNS,
                                            "\n\n§2Prickly§r.",
                                            "Damages attackers when they hit you. A defensive must-have.",
                                            "§7Cactus + Awkward")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.UNSTABLE), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.UNSTABLE,
                                            "\n\n§6Boom§r.",
                                            "Makes you highly volatile. Explosive surprises await.",
                                            "Obtained through alchemical rituals.")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.VAMPIRISM), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.VAMPIRISM,
                                            "\n\n§cBlood drinker§r.",
                                            "Damaging enemies heals you. Life steal in a bottle.",
                                            "§7Blood Bag + Awkward")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.XP_BOOST), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.XP_BOOST,
                                            "\n\n§bXP boost§r.",
                                            "Increases experience points gained from all sources.",
                                            "§7Lapis Lazuli + Awkward")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.XP_REDUCTION), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.XP_REDUCTION,
                                            "\n\n§8XP penalty§r.",
                                            "Reduces experience gained. A harmful effect to avoid.",
                                            "§7Fermented Eye + XP Boost")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.XP_LIFE), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.XP_LIFE,
                                            "\n\n§aXP for health§r.",
                                            "Converts experience points into health, keeping you alive.",
                                            "§7Glistering Melon + XP Boost")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.ZEUS), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.ZEUS,
                                            "\n\n§6God of thunder§r.",
                                            "Summon lightning strikes around you. Divine power unleashed.",
                                            "§7Materia Prima + Stun")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.COLD), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.COLD,
                                            "\n\n§bChilly§r.",
                                            "Slows down movement and actions. A chilling touch.",
                                            "§7Snowball + Awkward")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.CLUMSINESS), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.CLUMSINESS,
                                            "\n\n§7All thumbs§r.",
                                            "Your movements become clumsy and inaccurate. A hindrance effect.",
                                            "Brewing recipe unknown.")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.ASTHMA), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.ASTHMA,
                                            "\n\n§8Out of breath§r.",
                                            "Your stamina depletes faster. Running becomes exhausting.",
                                            "Brewing recipe unknown.")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.PARANOIA), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.PARANOIA,
                                            "\n\n§5Watching...§r.",
                                            "Your vision distorts with imagined threats. Stay alert.",
                                            "Brewing recipe unknown.")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.MEDUSA), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.MEDUSA,
                                            "\n\n§2Stone gaze§r.",
                                            "Your gaze turns enemies to stone. Look but don't touch.",
                                            "§7Materia Prima + Strong Petrification")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.MIDAS), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.MIDAS,
                                            "\n\n§6Golden touch§r.",
                                            "Everything you touch turns to gold. Wealth beyond measure.",
                                            "§7Materia Prima + Alchemist")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.ACTIVE_TELEPORT), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.ACTIVE_TELEPORT,
                                            "\n\n§5Controlled blink§r.",
                                            "Teleports you to where you're looking. Precision movement.",
                                            "§7Sulfur Ball + Teleportation")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.HYDROPHOBIA), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.HYDROPHOBIA,
                                            "\n\n§8Water fear§r.",
                                            "You take damage from water contact. Stay dry at all costs.",
                                            "§7Fermented Eye + Water Breathing")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.ZOMBIE_CONTAGION), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.ZOMBIE_CONTAGION,
                                            "\n\n§2The infection spreads§r.",
                                            "Transform into a zombie upon death, continuing your journey undead.",
                                            "§7Rotten Flesh + Awkward")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.MAGNETISM), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.MAGNETISM,
                                            "\n\n§bMagnetic§r.",
                                            "Items fly toward you from afar. Loot collection made easy.",
                                            "§7Iron Ingot + Awkward")
                            )
                            .subChapter(BookUtils.getEffectName(ModPotions.REALITY_CHECK), sub ->
                                    BookUtils.createPotionChapter(sub, ModPotions.REALITY_CHECK,
                                            "\n\n§5Is this real?§r.",
                                            "Questions the nature of reality itself. Strange effects follow.",
                                            "Brewing recipe unknown.")
                            )
                            .subChapter(BookUtils.getEffectName(Potions.FIRE_RESISTANCE), sub ->
                                    BookUtils.createPotionChapter(sub, Potions.FIRE_RESISTANCE,
                                            "\n\n§6Not today, fire§r.",
                                            "Grants immunity to fire and lava damage. Explore the Nether safely.",
                                            "§7Magma Block + Awkward")
                            )
                            .subChapter(BookUtils.getEffectName(Potions.REGENERATION), sub ->
                                    BookUtils.createPotionChapter(sub, Potions.REGENERATION,
                                            "\n\n§cHealing factor§r.",
                                            "Rapidly restores health over time. A combat essential.",
                                            "§7Golden Apple + Awkward")
                            )
                            .subChapter(BookUtils.getEffectName(Potions.STRENGTH), sub ->
                                    BookUtils.createPotionChapter(sub, Potions.STRENGTH,
                                            "\n\n§cPower up§r.",
                                            "Increases melee damage dealt to enemies.",
                                            "§7Firework Star + Awkward")
                            )
                            .subChapter(BookUtils.getEffectName(Potions.SWIFTNESS), sub ->
                                    BookUtils.createPotionChapter(sub, Potions.SWIFTNESS,
                                            "\n\n§bGotta go fast§r.",
                                            "Boosts your movement speed significantly.",
                                            "§7Sugar + Awkward")
                            )
                            .subChapter(BookUtils.getEffectName(Potions.POISON), sub ->
                                    BookUtils.createPotionChapter(sub, Potions.POISON,
                                            "\n\n§aToxic§r.",
                                            "Inflicts poison damage over time. Great for weakening foes.",
                                            "§7Poisonous Carrot + Awkward")
                            )
                            .subChapter(BookUtils.getEffectName(Potions.HEALING), sub ->
                                    BookUtils.createPotionChapter(sub, Potions.HEALING,
                                            "\n\n§cInstant health§r.",
                                            "Instantly restores health. A lifesaver in emergencies.",
                                            "§7Glistering Melon + Awkward")
                            )
                            .subChapter(BookUtils.getEffectName(Potions.NIGHT_VISION), sub ->
                                    BookUtils.createPotionChapter(sub, Potions.NIGHT_VISION,
                                            "\n\n§eSee in the dark§r.",
                                            "Brightens the world around you. Explore caves without torches.",
                                            "§7Golden Carrot + Awkward")
                            )
                            .subChapter(BookUtils.getEffectName(Potions.INVISIBILITY), sub ->
                                    BookUtils.createPotionChapter(sub, Potions.INVISIBILITY,
                                            "\n\n§8Now you see me...§r.",
                                            "Makes you completely invisible. Mobs won't attack unless provoked.",
                                            "§7Fermented Eye + Night Vision")
                            )
                            .subChapter(BookUtils.getEffectName(Potions.WATER_BREATHING), sub ->
                                    BookUtils.createPotionChapter(sub, Potions.WATER_BREATHING,
                                            "\n\n§bBreathe underwater§r.",
                                            "Allows you to breathe underwater. Explore oceans freely.",
                                            "§7Pufferfish + Awkward")
                            )
                            .subChapter(BookUtils.getEffectName(Potions.LEAPING), sub ->
                                    BookUtils.createPotionChapter(sub, Potions.LEAPING,
                                            "\n\n§3Jump higher§r.",
                                            "Increases your jump height and reduces fall damage.",
                                            "§7Rabbit Foot + Awkward")
                            )
                            .subChapter(BookUtils.getEffectName(Potions.TURTLE_MASTER), sub ->
                                    BookUtils.createPotionChapter(sub, Potions.TURTLE_MASTER,
                                            "\n\n§2Tank mode§r.",
                                            "Slows you down but grants massive resistance to damage.",
                                            "§7Turtle Shell + Awkward")
                            )
                            .subChapter(BookUtils.getEffectName(Potions.SLOW_FALLING), sub ->
                                    BookUtils.createPotionChapter(sub, Potions.SLOW_FALLING,
                                            "\n\n§fGentle descent§r.",
                                            "Slows your falling speed, preventing fall damage.",
                                            "§7Phantom Membrane + Awkward")
                            )
                            .subChapter(BookUtils.getEffectName(Potions.WEAKNESS), sub ->
                                    BookUtils.createPotionChapter(sub, Potions.WEAKNESS,
                                            "\n\n§7Feeble§r.",
                                            "Reduces melee damage dealt. Weakening your foes.",
                                            "§7Fermented Eye + Strength")
                            )
                            .subChapter(BookUtils.getEffectName(Potions.SLOWNESS), sub ->
                                    BookUtils.createPotionChapter(sub, Potions.SLOWNESS,
                                            "\n\n§7Slow down§r.",
                                            "Reduces movement speed. Useful for escaping or slowing pursuers.",
                                            "§7Fermented Eye + Swiftness")
                            )
                            .subChapter(BookUtils.getEffectName(Potions.HARMING), sub ->
                                    BookUtils.createPotionChapter(sub, Potions.HARMING,
                                            "\n\n§cInstant damage§r.",
                                            "Instantly damages the target. A potent weapon.",
                                            "§7Fermented Eye + Healing")
                            )
                    )
        );
    }
}
