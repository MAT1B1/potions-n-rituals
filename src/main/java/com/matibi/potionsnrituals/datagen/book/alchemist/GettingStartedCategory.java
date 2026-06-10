package com.matibi.potionsnrituals.datagen.book.alchemist;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.SingleBookSubProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookCategoryModel;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.book.BookDisplayMode;
import com.matibi.potionsnrituals.datagen.book.alchemist.gettingstarted.AlchemistCoreEntry;
import com.matibi.potionsnrituals.datagen.book.alchemist.gettingstarted.PotionEntry;
import com.matibi.potionsnrituals.item.ModItems;

public class GettingStartedCategory extends CategoryProvider {

    public static final String ID = "getting_started";

    public GettingStartedCategory(SingleBookSubProvider parent) {
        super(parent);
    }

    @Override
    protected void generateEntries() {
        this.add(new AlchemistCoreEntry(this).generate());

        // ── Vanilla extended ──────────────────────────────────────────────
        p("levitation", "Flings targets helplessly into the air.",
                "Flings its target into the air, rendering them helpless against gravity.",
                "Slow Falling", "Feather",
                "An unnatural force lifts you from the ground. Your feet leave the earth, pulled skyward by an invisible hand.", true);
        p("glowing", "Outlines targets, making them visible through walls.",
                "Causes the target to glow, making them visible through all obstacles.",
                "Night Vision", "Glow Berries",
                "A spectral outline envelops the target, revealing their presence to all who can see.", true);
        p("alcohol", "Causes nausea and disorientation.",
                "Intoxicates the target, causing severe nausea and disorientation.",
                "Awkward Potion", "Sweet Berries",
                "A dizzying brew that twists the world into a spinning blur of confusion.", true);
        p("darkness", "Shrouds enemies in darkness.",
                "Blinds its victim, shrouding their vision in an impenetrable blackness.",
                "Awkward Potion", "Ink Sac",
                "It is not a shadow... It is alive. It isolates you, severing your tie to the world above.", true);
        p("haste", "Increases mining and attack speed.",
                "Increases the target's mining and attack speed.",
                "Awkward Potion", "Claw",
                "Your hands move faster, swings become a blur, and stone shatters like glass.", true);
        p("mining_fatigue", "Slows mining and attack speed.",
                "Drastically reduces the target's mining and attack speed.",
                "Potion of Haste", "Fermented Spider Eye",
                "Every swing feels like moving through molasses. Time slows to a crawl.", true);

        // ── Permanent ─────────────────────────────────────────────────────
        p("perm_health", "Permanently increases maximum health.",
                "Grants additional permanent heart containers to the drinker.",
                "Unknown Ritual", "???",
                "A forbidden elixir that rewrites the very fabric of your being.", false);
        p("perm_speed", "Permanently increases movement speed.",
                "Grants a permanent boost to the drinker's movement speed.",
                "Unknown Ritual", "???",
                "The wind itself seems to carry you forward, a blessing etched into your soul.", false);
        p("perm_strength", "Permanently increases attack damage.",
                "Grants a permanent boost to the drinker's attack strength.",
                "Unknown Ritual", "???",
                "Raw power courses through your veins, never to fade.", false);

        // ── Terrain ───────────────────────────────────────────────────────
        p("acid", "Deals corrosive damage over time.",
                "Burns the target with corrosive acid, dealing damage over time.",
                "Awkward Potion", "Rotten Flesh",
                "A bubbling, hissing concoction that eats through anything it touches.", false);
        p("alchemist", "Grants alchemical insight.",
                "Awakens latent alchemical knowledge within the drinker.",
                "Awkward Potion", "Copper Ingot",
                "The secrets of transmutation whisper at the edge of consciousness.", false);
        p("giant", "Increases the target's size.",
                "Causes the target to grow to enormous proportions.",
                "Awkward Potion", "Bone Meal",
                "The world shrinks beneath you as your form expands to titanic scale.", false);
        p("ignition", "Sets the target ablaze.",
                "Ignites the target in magical flames that cannot be extinguished.",
                "Fire Resistance", "Fermented Spider Eye",
                "Unholy fire erupts from within, a flame that water cannot quench.", false);
        p("petrification", "Turns the target to stone.",
                "Petrifies the target, freezing them in a stone-like state.",
                "Turtle Master", "Obsidian",
                "A creeping rigidity spreads through the body until movement becomes impossible.", false);
        p("resurrection", "Brings the dead back to life.",
                "Returns a fallen ally to the world of the living.",
                "Awkward Potion", "Totem of Undying",
                "A defiance of death itself. Life stirs where only silence reigned.", false);

        // ── Normal ─────────────────────────────────────────────────────────
        p("adhesion", "Prevents the target from jumping.",
                "Causes the target to stick to the ground, unable to jump.",
                "Awkward Potion", "Resin Clump",
                "Your feet are glued to the earth, every leap swallowed by sticky inevitability.", false);
        p("berserk", "Massively increases damage at a cost.",
                "Sends the target into a berserk rage, increasing damage dealt and received.",
                "Awkward Potion", "Torchflower",
                "A crimson haze descends. Pain means nothing. Only destruction remains.", false);
        p("brainwashing", "Turns mobs into allies.",
                "Reprograms the target's mind, turning them into a loyal ally.",
                "Awkward Potion", "Zombie Brain",
                "Free will is an illusion. The mind bends to a new master.", false);
        p("death", "Instantly kills the target.",
                "A lethal concoction that snuffs out life instantly.",
                "Potion of Resurrection", "Wither Rose",
                "A single sip. Then, nothing. The void welcomes all equally.", false);
        p("double_health", "Doubles the target's maximum health.",
                "Temporarily doubles the target's maximum health.",
                "Strong Healing", "Golden Apple",
                "Vitality surges through every fiber, a second heart beating alongside the first.", false);
        p("dwarf", "Decreases the target's size.",
                "Shrinks the target to minuscule proportions.",
                "Potion of Giant", "Fermented Spider Eye",
                "The world grows vast around you. Every blade of grass becomes a tree.", false);
        p("frost", "Freezes the target solid.",
                "Encases the target in magical frost, slowing and damaging them.",
                "Potion of Cold", "Snowball",
                "Winter's heart beats within this vial, ready to consume all warmth.", false);
        p("ghost_walk", "Allows phasing through walls.",
                "Grants the ability to phase through solid blocks.",
                "Awkward Potion", "Ghast Tear",
                "Solid walls become suggestions. The material world is merely a suggestion.", false);
        p("liquid_walker", "Allows walking on liquids.",
                "Grants the ability to walk on water and lava as if solid.",
                "Awkward Potion", "Lily Pad",
                "The surface tension becomes your path. Oceans are now roads.", false);
        p("long_cooldown", "Increases ability cooldowns.",
                "Extends the cooldown time of all abilities.",
                "Potion of Short Cooldown", "Fermented Spider Eye",
                "Time stretches between your actions. Impatience becomes your greatest enemy.", false);
        p("long_leg", "Increases step height.",
                "Allows the target to step over taller obstacles.",
                "Awkward Potion", "Bamboo",
                "Your strides grow longer. Walls and fences become trivial obstacles.", false);
        p("love", "Causes nearby animals to breed.",
                "Affects nearby animals, causing them to enter love mode.",
                "Awkward Potion", "Poppy",
                "A rosy mist fills the air. Hearts bloom where indifference once prevailed.", false);
        p("masking", "Hides the target's identity.",
                "Masks the target, hiding their name and appearance.",
                "Invisibility", "Fermented Spider Eye",
                "Who you are becomes a secret. Your face is a canvas of shadows.", false);
        p("no_interaction", "Prevents the target from interacting.",
                "Blocks the target from interacting with blocks and entities.",
                "Potion of Brainwashing", "Rabbit Foot",
                "Your hands pass through objects. The world becomes untouchable — and you, untouchable.", false);
        p("oblivion", "Erases nearby entities from existence.",
                "A devastating potion that erases entities from the world.",
                "Awkward Potion", "Pitcher Plant",
                "They do not die. They simply cease to have ever been.", false);
        p("ore_sense", "Highlights nearby ores.",
                "Grants the ability to sense nearby ores through walls.",
                "Night Vision", "Iron Ore",
                "The earth whispers secrets of buried treasure. Every vein, revealed.", false);
        p("photosynthesis", "Regenerates health in sunlight.",
                "Allows the target to regenerate health while exposed to sunlight.",
                "Awkward Potion", "Leaf Litter",
                "The sun becomes your healer. Light itself knits your wounds.", false);
        p("purification", "Removes all negative effects.",
                "Cleanses the target, removing all harmful status effects.",
                "Awkward Potion", "Milk Bucket",
                "A tide of purity washes through you, cleansing every corruption.", false);
        p("reactivation", "Resets all cooldowns.",
                "Instantly resets all ability cooldowns.",
                "Potion of Short Cooldown", "Sunflower",
                "Time rewinds. What was spent is returned. Powers surge anew.", false);
        p("resonance", "Creates a damaging vibration aura.",
                "Emits a damaging resonance that harms nearby entities.",
                "Awkward Potion", "Echo Shard",
                "The air itself vibrates with destructive harmonics. Proximity becomes pain.", false);
        p("infinity", "Grants infinite resources.",
                "Bestows infinite uses of certain items to the drinker.",
                "Unknown Ritual", "???",
                "A taste of the infinite. Scarcity becomes a forgotten concept.", false);
        p("rust", "Corrodes equipment and armor.",
                "Accelerates the rusting and degradation of equipment.",
                "Awkward Potion", "Oxydation",
                "Time accelerates around your gear. Iron turns to dust.", false);
        p("saturation", "Fills hunger and saturation.",
                "Restores hunger and saturation to the target.",
                "Awkward Potion", "Beetroot",
                "A feast in a bottle. Hunger becomes a distant memory.", false);
        p("short_cooldown", "Decreases ability cooldowns.",
                "Reduces the cooldown time of all abilities.",
                "Awkward Potion", "Sunflower",
                "Your powers flow more freely. The pause between actions shrinks.", false);
        p("stun", "Paralyzes the target.",
                "Stuns the target, preventing all movement and action.",
                "Awkward Potion", "Lightning Rod",
                "A jolt locks every muscle. The body becomes a prison.", false);
        p("teleportation", "Randomly teleports the target.",
                "Causes the target to teleport to a random nearby location.",
                "Awkward Potion", "Ender Pearl",
                "Reality fractures. Here one moment, there the next. Control is an illusion.", false);
        p("thorns", "Reflects damage back to attackers.",
                "Causes attackers to take damage when hitting the target.",
                "Awkward Potion", "Cactus",
                "Every blow against you draws blood from the attacker. Pain is reciprocal.", false);
        p("unstable", "Causes unpredictable chaotic effects.",
                "Subjects the target to random, unpredictable magical effects.",
                "Unknown Ritual", "???",
                "Chaos in liquid form. Each dose is a gamble with fate.", false);
        p("vampirism", "Steals health from attacked targets.",
                "Grants lifesteal, healing the attacker for a portion of damage dealt.",
                "Awkward Potion", "Blood Bag",
                "Life flows from your enemies into you. Their strength becomes yours.", false);
        p("xp_boost", "Increases experience gained.",
                "Multiplies all experience gained by the target.",
                "Awkward Potion", "Lapis Lazuli",
                "Knowledge flows like a river. Every lesson yields tenfold.", false);
        p("xp_reduction", "Reduces experience gained.",
                "Reduces all experience gained by the target.",
                "Potion of XP Boost", "Fermented Spider Eye",
                "Learning becomes a struggle. Knowledge slips through your fingers like sand.", false);
        p("xp_life", "Converts experience into health.",
                "Uses experience points to heal the target over time.",
                "Potion of XP Boost", "Glistering Melon Slice",
                "Your hard-earned knowledge becomes life itself. Wisdom heals.", false);
        p("zeus", "Strikes nearby entities with lightning.",
                "Calls down lightning bolts on nearby targets.",
                "Awkward Potion", "Charged Copper",
                "The sky answers your call. Thunder becomes your weapon.", false);
        p("cold", "Slows the target with freezing cold.",
                "Chills the target, reducing movement speed.",
                "Awkward Potion", "Snowball",
                "A winter chill seeps into the bones, turning swift strides into labored steps.", false);
        p("clumsiness", "Causes the target to stumble and drop items.",
                "Makes the target clumsy, causing them to drop held items.",
                "Unknown Ritual", "???",
                "Butterfingers in a bottle. Grace is a forgotten art.", false);
        p("asthma", "Restricts breathing and stamina.",
                "Afflicts the target with magical asthma, reducing stamina.",
                "Unknown Ritual", "???",
                "Every breath becomes a battle. The air itself seems to thin.", false);
        p("paranoia", "Causes the target to hear false sounds.",
                "Induces paranoia, making the target hear imaginary threats.",
                "Unknown Ritual", "???",
                "Every sound is a threat. Footsteps where none exist. Whispers in empty rooms.", false);
        p("medusa", "Turns targets to stone when looked at.",
                "Petrifies entities that look directly at the target.",
                "Unknown Ritual", "???",
                "Meet your gaze, and become a monument. Beauty that kills.", false);
        p("active_teleportation", "Allows controlled teleportation.",
                "Grants the ability to teleport at will to a targeted location.",
                "Unknown Ritual", "???",
                "Space bends to your will. Here, there, anywhere — at a thought.", false);
    }

    private void p(String id, String desc, String expl, String base, String ingr, String lore, boolean vanilla) {
        this.add(new PotionEntry(this, id, desc, expl, base, ingr, lore, vanilla).generate());
    }

    @Override
    protected BookCategoryModel additionalSetup(BookCategoryModel category) {
        return category.withDisplayMode(BookDisplayMode.INDEX);
    }

    @Override
    protected String categoryName() {
        return "Getting Started";
    }

    @Override
    protected String categoryDescription() {
        return "The basics of alchemy.";
    }

    @Override
    protected BookIconModel categoryIcon() {
        return BookIconModel.create(ModItems.ALCHEMIST_CORE);
    }

    @Override
    public String categoryId() {
        return ID;
    }
}
