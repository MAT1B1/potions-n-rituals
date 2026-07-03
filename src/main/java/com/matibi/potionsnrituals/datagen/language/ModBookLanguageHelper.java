package com.matibi.potionsnrituals.datagen.language;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider.TranslationBuilder;

public class ModBookLanguageHelper {

    public static void generateBookFrFrTranslations(TranslationBuilder t) {
        // --- GUIDE DE BASE (BASIC_GUIDE) ---
        t.add("book.potions-n-rituals.basic.toc", "Sommaire");
        t.add("book.potions-n-rituals.basic.toc2", "Cliquez sur une section pour débuter :\n\n");

        t.add("book.potions-n-rituals.basic.opus.chapter", "Le Grand Œuvre");
        t.add("book.potions-n-rituals.basic.opus.title", "La Voie de l'Alchimiste");
        t.add("book.potions-n-rituals.basic.opus.body", "L'accomplissement de l'Art se divise en quatre grandes étapes successives. Ce premier volume pose les bases de votre voyage. Comprendre l'ordre des œuvres est la clé pour ne pas vous perdre dans les méandres de la création.");

        t.add("book.potions-n-rituals.basic.nigredo.chapter", "I. L'Œuvre au Noir");
        t.add("book.potions-n-rituals.basic.nigredo.title", "I. Nigredo");
        t.add("book.potions-n-rituals.basic.nigredo.body", "Le Nigredo est la phase de décomposition et d'extraction. C'est ici que vous séparez les composants de base du monde pour isoler les trois principes fondamentaux : le Soufre, le Mercure et le Sel, afin de façonner la Materia Prima.");
        t.add("book.potions-n-rituals.basic.nigredo.sulfur", "Le Soufre");
        t.add("book.potions-n-rituals.basic.nigredo.mercury", "Le Mercure");
        t.add("book.potions-n-rituals.basic.nigredo.salt", "Le Sel");
        t.add("book.potions-n-rituals.basic.nigredo.materia", "Materia Prima");

        t.add("book.potions-n-rituals.basic.albedo.chapter", "II. L'Œuvre au Blanc");
        t.add("book.potions-n-rituals.basic.albedo.title", "II. Albedo");
        t.add("book.potions-n-rituals.basic.albedo.body", "L'Albedo marque le début de la purification. Une fois la Materia Prima obtenue, vous apprendrez à la dissoudre et à la lier pour infuser des essences volatiles. C'est l'art de concevoir des Potions alchimiques avancées aux vertus insoupçonnées.");

        t.add("book.potions-n-rituals.basic.citrinitas.chapter", "III. L'Œuvre au Jaune");
        t.add("book.potions-n-rituals.basic.citrinitas.title", "III. Citrinitas");
        t.add("book.potions-n-rituals.basic.citrinitas.body", "Le Citrinitas pousse la matière vers sa cristallisation et son éveil. En capturant les effets volatils de vos potions pour les fixer dans la matière, vous deviendrez capable de forger des Talismans et des Artefacts magiques transmettant des pouvoirs permanents.");

        t.add("book.potions-n-rituals.basic.rubedo.chapter", "IV. L'Œuvre au Rouge");
        t.add("book.potions-n-rituals.basic.rubedo.title", "IV. Rubedo");
        t.add("book.potions-n-rituals.basic.rubedo.body", "L'accomplissement ultime et la maîtrise absolue du Grand Œuvre. Le Rubedo est l'aboutissement où toutes vos connaissances fusionnent. En combinant la matière, les fluides et vos artefacts, vous pourrez ériger de puissants Rituels capables de plier la réalité à votre volonté.");

        // --- TRAITÉ NIGREDO (NigredoBookItem) ---
        t.add("book.potions-n-rituals.nigredo.chapter.potions", "Potions");

        // Effets & Potions
        registerPotionLines(t, "levitation", "\nLa gravité est un poids sur les épaules, oubliez-la.", "Vous tombez lentement, si lentement que vous ne subissez aucun dégât de chute.\n\nC'est comme si vous étiez sur la §3Lune§r.\n\n§4Attention, cela ne dure qu'un instant.§r", "Une §3Potion de Chute Lente§r avec une §3plume§r devrait suffire à alléger votre fardeau.");
        registerPotionLines(t, "adhesion", "\nMmh c'est un peu...\n\n§2collant§r.", "Vous adhérez aux surfaces, ce qui vous permet de marcher sur les murs.\n\nTrès utile pour repousser les araignées.", "Trouvez quelque chose de collant, comme de la §2résine§r. Mélangez-la avec une §2potion étrange§r, cela devrait suffire.");
        registerPotionLines(t, "glowing", "\nC'est un peu sombre ici...\n\nMais je peux très bien vous §6voir§r.", "Rend les entités §6lumineuses§r pour que vous puissiez les voir dans le noir et à travers les murs.", "Une §6Potion de Vision Nocturne§r vous permet de voir dans le noir tandis qu'une §6baie lumineuse§r vous fait briller... assez direct.");
        registerPotionLines(t, "alcohol", "\n\n§2*hic*§r... Juste une dernière goutte.", "Donne à l'entité un sentiment de perte.\n\nVous §2perdez le contrôle§r, pourtant tout semble si §2drôle§r !", "Mélangez des §2baies sucrées§r et une §2potion étrange§r pour obtenir de la liqueur.");
        registerPotionLines(t, "darkness", "\nTout disparaît, seul le §7vide§r continue de se montrer.", "Vous perdez la vue, tout devient noir.\n\nIl n'y a qu'une seule chose à retenir...\n\n§4FUYEZ§r\n\nIl est proche...", "Une poche d'encre mélangée à une potion étrange est idéale pour imiter l'effet.");
        registerPotionLines(t, "haste", "\n\nLe rêve d'un mineur...\n\nC'est tellement §6rapide§r !!", "Vous pouvez tout miner plus §6rapidement§r,\n\nvraiment très §6vite§r.", "Les chats creusent beaucoup, donc une §6griffe§r avec une §6potion étrange§r pourrait vous donner la formule.");
        registerPotionLines(t, "mining_fatigue", "\nWow, c'est une §7pierre dure§r ! \n\nJe ne peux pas la casser.", "Vous minez tout plus §4lentement§r,\n\nvraiment très §4lentement§r.", "Vous inversez la §4potion de célérité§r avec un §4œil d'araignée fermenté§r.");
        registerPotionLines(t, "acid", "Substance hautement §2toxique§r,\n\nÀ utiliser avec précaution.\n\nPeut causement des indigestions.", "Vous inflige des dégâts toutes les secondes.", "Mélangez de la §2viande putréfiée§r et une §2potion de poison§r pour obtenir un mélange hautement toxique.");
        registerPotionLines(t, "alchemist", "\nLe rêve de tout alchimiste,\n\ndu §7charbon§r qui devient de l'§6or§r.", "Transforme les objets en §7Charbon§r en leur variante d'§6Or§r.", "Prenez un objet d'alchimiste comme la §6boule de mercure§r et une §6potion étrange§r pour réaliser ce rêve.");

        registerPotionLines(t, "giant", "\n\n§3Regarder le monde de haut.§r", "", "§7Poudre d'os + Potion Étrange");
        registerPotionLines(t, "ignition", "\n\n§6Le feu intérieur.§r", "", "§7Œil Fermenté + Résistance au Feu");
        registerPotionLines(t, "petrification", "\n\n§8Glacer le sang.§r", "", "§7Obsidienne + Maître Tortue");
        registerPotionLines(t, "resurrection", "\n\n§dTromper la faucheuse.§r", "", "§7Totem d'Éternité + Potion Étrange");
        registerPotionLines(t, "berserk", "\n\n§cFureur aveugle.§r", "", "§7Fleur-torche + Potion Étrange");
        registerPotionLines(t, "brainwashing", "\n\n§dMarionnettes putrides.§r", "", "§7Cerveau de Zombie + Potion Étrange");
        registerPotionLines(t, "death", "\n\n§8L'arrêt définitif.§r", "", "§7Rose du Wither + Résurrection");
        registerPotionLines(t, "double_health", "\n\n§cUn second souffle de vie.§r", "", "§7Pomme dorée + Soin Instantané Puissant");
        registerPotionLines(t, "dwarf", "\n\n§3Une perspective de souris.§r", "", "§7Œil Fermenté + Géant");
        registerPotionLines(t, "frost", "\n\n§bLe baiser de l'hiver.§r", "", "§7Boule de neige + Rhume");
        registerPotionLines(t, "ghost_walk", "\n\n§7Traverser le voile.§r", "", "§7Larme de Ghast + Potion Étrange");
        registerPotionLines(t, "liquid_walker", "\n\n§3Densité des fluides.§r", "", "§7Nénuphar + Potion Étrange");
        registerPotionLines(t, "long_cooldown", "\n\n§8Engourdissement temporel.§r", "", "§7Œil Fermenté + Cooldown Réduit");
        registerPotionLines(t, "long_leg", "\n\n§3Des enjambées de géant.§r", "", "§7Bambou + Potion Étrange");
        registerPotionLines(t, "love", "\n\n§dEuphorie paisible.§r", "", "§7Coquelicot + Potion Étrange");
        registerPotionLines(t, "masking", "\n\n§8Effacement total.§r", "", "§7Œil Fermenté + Invisibilité");
        registerPotionLines(t, "no_interaction", "\n\n§8Spectateur du monde.§r", "", "§7Patte de Lapin + Lavage de Cerveau");
        registerPotionLines(t, "oblivion", "\n\n§5Le grand vide.§r", "", "§7Fleur de Broc + Potion Étrange");
        registerPotionLines(t, "ore_sense", "\n\n§eL'appel de la roche.§r", "", "§7Minerai de Fer + Vision Nocturne");
        registerPotionLines(t, "photosynthesis", "\n\n§aNourri par l'astre divin.§r", "", "§7Feuillage + Potion Étrange");
        registerPotionLines(t, "purification", "\n\n§fTable rase.§r", "", "§7Seau de Lait + Potion Étrange");
        registerPotionLines(t, "reactivation", "\n\n§eMoment critique.§r", "", "§7Tournesol + Cooldown Réduit");
        registerPotionLines(t, "resonance", "\n\n§dL'impulsion de la terre.§r", "", "§7Éclat d'écho + Potion Étrange");
        registerPotionLines(t, "infinity", "\n\n§5La source sans limites.§r", "", "Obtenu par l'alchimie avancée.");
        registerPotionLines(t, "rust", "\n\n§6L'âge de la rouille.§r", "", "§7Fragment d'Oxydation + Potion Étrange");
        registerPotionLines(t, "saturation", "\n\n§cLe festin concentré.§r", "", "§7Betterave + Potion Étrange");
        registerPotionLines(t, "short_cooldown", "\n\n§eVivacité surhumaine.§r", "", "§7Tournesol + Potion Étrange");
        registerPotionLines(t, "stun", "\n\n§8Surcharge synaptique.§r", "", "§7Cuivre Chargé + Potion Étrange");
        registerPotionLines(t, "teleportation", "\n\n§5Saut quantique instable.§r", "", "§7Perle de l'Ender + Potion Étrange");
        registerPotionLines(t, "thorns", "\n\n§2Épines de chair.§r", "", "§7Cactus + Potion Étrange");
        registerPotionLines(t, "unstable", "\n\n§6Un baril de poudre sur pattes.§r", "", "Obtenu via des rituels alchimiques.");
        registerPotionLines(t, "vampirism", "\n\n§cDu sang pour du sang.§r", "", "§7Poche de Sang + Potion Étrange");
        registerPotionLines(t, "xp_boost", "\n\n§bEsprit d'apprentissage.§r", "", "§7Lapis-lazuli + Potion Étrange");
        registerPotionLines(t, "xp_reduction", "\n\n§8Amnésie de l'âme.§r", "", "§7Œil Fermenté + Boost d'XP");
        registerPotionLines(t, "xp_life", "\n\n§aSacrifier le savoir pour la chair.§r", "", "§7Tranche de Melon Scintillant + Boost d'XP");
        registerPotionLines(t, "zeus", "\n\n§6Colère des cieux.§r", "", "§7Materia Prima + Étourdissement");
        registerPotionLines(t, "cold", "\n\n§bTorpeur hivernale.§r", "", "§7Boule de neige + Potion Étrange");
        registerPotionLines(t, "clumsiness", "\n\n§7Mains de beurre.§r", "", "Formule de brassage perdue dans le temps.");
        registerPotionLines(t, "asthma", "\n\n§8Poumons de cendres.§r", "", "Formule de brassage perdue dans le temps.");
        registerPotionLines(t, "paranoia", "\n\n§5Des ombres dans les coins.§r", "", "Formule de brassage perdue dans le temps.");
        registerPotionLines(t, "medusa", "\n\n§2Le regard fatal.§r", "", "§7Materia Prima + Pétrification Puissante");
        registerPotionLines(t, "midas", "\n\n§6La malédiction dorée.§r", "", "§7Materia Prima + Alchimiste");
        registerPotionLines(t, "active_teleport", "\n\n§5Déplacement focalisé.§r", "", "§7Boule de Mercure + Téléportation");
        registerPotionLines(t, "hydrophobia", "\n\n§8Rejet des vagues.§r", "", "§7Œil Fermenté + Apnée");
        registerPotionLines(t, "zombie_contagion", "\n\n§2Le fléau des rongeurs de tombes.§r", "", "§7Viande putréfiée + Potion Étrange");
        registerPotionLines(t, "magnetism", "\n\n§bGravité attractive.§r", "", "§7Lingot de Fer + Potion Étrange");
        registerPotionLines(t, "reality_check", "\n\n§5Effondrement cognitif.§r", "", "Formule de brassage perdue dans le temps.");

        // Potions de base Minecraft
        registerPotionLines(t, "fire_resistance", "\n\n§6Peau de salamandre.§r", "", "§7Crème de Magma + Potion Étrange");
        registerPotionLines(t, "regeneration", "\n\n§cÉpanouissement cellulaire.§r", "", "§7Larme de Ghast + Potion Étrange");
        registerPotionLines(t, "strength", "\n\n§cPoussée de puissance brute.§r", "", "§7Poudre de Blaze + Potion Étrange");
        registerPotionLines(t, "swiftness", "\n\n§bDépasser le temps.§r", "", "§7Sucre + Potion Étrange");
        registerPotionLines(t, "poison", "\n\n§aLe fléau de l'alchimiste.§r", "", "§7Œil d'Araignée + Potion Étrange");
        registerPotionLines(t, "healing", "\n\n§cÉclat instantané de vie.§r", "", "§7Tranche de Melon Scintillant + Potion Étrange");
        registerPotionLines(t, "night_vision", "\n\n§ePupilles de félin.§r", "", "§7Carotte Dorée + Potion Étrange");
        registerPotionLines(t, "invisibility", "\n\n§8Réfraction de la lumière.§r", "", "§7Œil Fermenté + Vision Nocturne");
        registerPotionLines(t, "water_breathing", "\n\n§bOuïes éphémères.§r", "", "§7Poisson-globe + Potion Étrange");
        registerPotionLines(t, "leaping", "\n\n§3Défier les hauteurs.§r", "", "§7Patte de Lapin + Potion Étrange");
        registerPotionLines(t, "turtle_master", "\n\n§2La carapace de l'esprit.§r", "", "§7Carapace de Tortue + Potion Étrange");
        registerPotionLines(t, "slow_falling", "\n\n§fDescente de plume.§r", "", "§7Membrane de Phantom + Potion Étrange");
        registerPotionLines(t, "weakness", "\n\n§7Léthargie des muscles.§r", "", "§7Œil d'Araignée Fermenté + Eau");
        registerPotionLines(t, "slowness", "\n\n§7Allure de plomb.§r", "", "§7Œil Fermenté + Vitesse");
        registerPotionLines(t, "harming", "\n\n§cFléau distillé.§r", "", "§7Œil Fermenté + Soin");

        // Élixirs Permanents
        registerPotionLines(t, "perm_health", "\n\n§cLa chair transcendée.§r", "", "Obtenu via des rituels alchimiques.");
        registerPotionLines(t, "perm_speed", "\n\n§bVélocité éternelle.§r", "", "Obtenu via des rituels alchimiques.");
        registerPotionLines(t, "perm_strength", "\n\n§cMarque du Titan.§r", "", "Obtenu via des rituels alchimiques.");
    }

    public static void generateBookEnUsTranslations(TranslationBuilder t) {
        // --- GUIDE DE BASE (BASIC_GUIDE) ---
        t.add("book.potions-n-rituals.basic.toc", "Summary");
        t.add("book.potions-n-rituals.basic.toc2", "Click on a section to begin:\n\n");

        t.add("book.potions-n-rituals.basic.opus.chapter", "The Great Work");
        t.add("book.potions-n-rituals.basic.opus.title", "The Way of the Alchemist");
        t.add("book.potions-n-rituals.basic.opus.body", "The fulfillment of the Art is divided into four major successive stages. This first volume lays the foundations of your journey. Understanding the order of the works is the key to not getting lost in the twists and turns of creation.");

        t.add("book.potions-n-rituals.basic.nigredo.chapter", "I. The Work in Black");
        t.add("book.potions-n-rituals.basic.nigredo.title", "I. Nigredo");
        t.add("book.potions-n-rituals.basic.nigredo.body", "Nigredo is the phase of decomposition and extraction. It is here that you separate the basic components of the world to isolate the three fundamental principles: Sulfur, Mercury, and Salt, in order to shape the Materia Prima.");
        t.add("book.potions-n-rituals.basic.nigredo.sulfur", "Sulfur");
        t.add("book.potions-n-rituals.basic.nigredo.mercury", "Mercury");
        t.add("book.potions-n-rituals.basic.nigredo.salt", "Salt");
        t.add("book.potions-n-rituals.basic.nigredo.materia", "Materia Prima");

        t.add("book.potions-n-rituals.basic.albedo.chapter", "II. The Work in White");
        t.add("book.potions-n-rituals.basic.albedo.title", "II. Albedo");
        t.add("book.potions-n-rituals.basic.albedo.body", "Albedo marks the beginning of purification. Once the Materia Prima is obtained, you will learn to dissolve and bind it to infuse volatile essences. This is the art of designing advanced alchemical Potions with unsuspected virtues.");

        t.add("book.potions-n-rituals.basic.citrinitas.chapter", "III. The Work in Yellow");
        t.add("book.potions-n-rituals.basic.citrinitas.title", "III. Citrinitas");
        t.add("book.potions-n-rituals.basic.citrinitas.body", "Citrinitas pushes matter towards its crystallization and awakening. By capturing the volatile effects of your potions to fix them into matter, you will become able to forge magical Talismans and Artifacts transmitting permanent powers.");

        t.add("book.potions-n-rituals.basic.rubedo.chapter", "IV. The Work in Red");
        t.add("book.potions-n-rituals.basic.rubedo.title", "IV. Rubedo");
        t.add("book.potions-n-rituals.basic.rubedo.body", "The ultimate achievement and absolute mastery of the Great Work. Rubedo is the culmination where all your knowledge merges. By combining matter, fluids, and your artifacts, you can erect powerful Rituals capable of bending reality to your will.");

        // --- TRAITÉ NIGREDO (NigredoBookItem) ---
        t.add("book.potions-n-rituals.nigredo.chapter.potions", "Potions");

        // Effets & Potions (Textes d'origine conservés)
        registerPotionLines(t, "levitation", "\nGravity is a weight on the shoulders, forget it", "You fall slowly, so slowly that you don't take fall damage.\n\nIt's like you are on the §3Moon§r.\n\n§4Be careful, it only last a moment.§r", "A §3Slow Falling potion§r with a §3feather§r should be good enough to lighten your burden.");
        registerPotionLines(t, "adhesion", "\nMmh that's a bit...\n\n§2sticky§r.", "You stick to surfaces, allowing you to walk on walls.\n\nVery useful for repelling away spiders.", "Find something sticky, maybe like §2resin§r. Mix it with an §2awkward potion§r, it should be sufficient.");
        registerPotionLines(t, "glowing", "\nIt's a bit dark in here...\n\nBut I can §6see§r you very well", "Make entity §6glow§r so that you can see them in the dark and behind walls.", "A §6Night Vision Potion§r allow you to see in the dark while a §6glow berry§r make you glow... pretty straight forward.");
        registerPotionLines(t, "alcohol", "\n\n§2*hic*§r... Just one last drop.", "Gives entity a feeling of loss.\n\nYou §2lose control§r, yet everything seems so §2funny§r !", "Mix §2sweet berries§r and an §2awkward potion§r, to get some liquor.");
        registerPotionLines(t, "darkness", "\nEverything disappear, only the §7void§r keep showing itself.", "You lose your sight, everything turn black.\n\nThere is just one thing to remember...\n\n§4RUN§r\n\nHe's close...", "An ink sac mixed with an awkward potion is great to mimic the effect.");
        registerPotionLines(t, "haste", "\n\nA miner's dream...\n\nIt's so §6fast§r !!", "You can mine everything §6faster§r,\n\nreally really §6fast§r.", "Cat dig a lot, so a §6claw§r with an §6awkward potion§r might give you the potion");
        registerPotionLines(t, "mining_fatigue", "\nWow, it's a §7hard stone§r ! \n\nI can't break it.", "You mine everything §4slower§r,\n\nreally really §4slow§r.", "You reverse the §4potion of haste§r with a §4fermented spider eye§r");
        registerPotionLines(t, "acid", "Substance highly §2toxic§r,\n\nUse with precaution.\n\nCan cause indigestion.", "Damages you every seconds", "Mix a §2rotten flesh§r and a §2poison potion§r to get a highly toxic mixture.");
        registerPotionLines(t, "alchemist", "\nThe dream of every alchemist,\n\n§7coal§r that become §6gold§r.", "Turn §7Coal§r items into their §6Gold§r variant.", "Take an alchemist item like the §6mercury ball§r and an §6awkward potion§r to achieve that dream.");

        registerPotionLines(t, "giant", "\n\n§3Looking down upon the world.§r", "", "§7Bone Meal + Awkward");
        registerPotionLines(t, "ignition", "\n\n§6The inner fire.§r", "", "§7Fermented Eye + Fire Resistance");
        registerPotionLines(t, "petrification", "\n\n§8Freezing the blood.§r", "", "§7Obsidian + Turtle Master");
        registerPotionLines(t, "resurrection", "\n\n§dTricking the reaper.§r", "", "§7Totem of Undying + Awkward");
        registerPotionLines(t, "berserk", "\n\n§cBlind fury.§r", "", "§7Torchflower + Awkward");
        registerPotionLines(t, "brainwashing", "\n\n§dPutrid marionettes.§r", "", "§7Zombie Brain + Awkward");
        registerPotionLines(t, "death", "\n\n§8The definitive stop.§r", "", "§7Wither Rose + Resurrection");
        registerPotionLines(t, "double_health", "\n\n§cA second surge of life.§r", "", "§7Golden Apple + Strong Healing");
        registerPotionLines(t, "dwarf", "\n\n§3A mouse's perspective.§r", "", "§7Fermented Eye + Giant");
        registerPotionLines(t, "frost", "\n\n§bThe kiss of winter.§r", "", "§7Snowball + Cold");
        registerPotionLines(t, "ghost_walk", "\n\n§7Passing through the veil.§r", "", "§7Ghast Tear + Awkward");
        registerPotionLines(t, "liquid_walker", "\n\n§3Fluid density.§r", "", "§7Lily Pad + Awkward");
        registerPotionLines(t, "long_cooldown", "\n\n§8Temporal numbness.§r", "", "§7Fermented Eye + Short Cooldown");
        registerPotionLines(t, "long_leg", "\n\n§3Des enjambées de géant.§r", "", "§7Bamboo + Awkward");
        registerPotionLines(t, "love", "\n\n§dPeaceful euphoria.§r", "", "§7Poppy + Awkward");
        registerPotionLines(t, "masking", "\n\n§8Total erasure.§r", "", "§7Fermented Eye + Invisibility");
        registerPotionLines(t, "no_interaction", "\n\n§8Spectator of the world.§r", "", "§7Rabbit Foot + Brainwashing");
        registerPotionLines(t, "oblivion", "\n\n§5The great void.§r", "", "§7Pitcher Plant + Awkward");
        registerPotionLines(t, "ore_sense", "\n\n§eThe call of the rock.§r", "", "§7Iron Ore + Night Vision");
        registerPotionLines(t, "photosynthesis", "\n\n§aFed by the divine star.§r", "", "§7Leaf Litter + Awkward");
        registerPotionLines(t, "purification", "\n\n§fClean slate.§r", "", "§7Milk Bucket + Awkward");
        registerPotionLines(t, "reactivation", "\n\n§eCritical moment.§r", "", "§7Sunflower + Short Cooldown");
        registerPotionLines(t, "resonance", "\n\n§dThe pulse of the earth.§r", "", "§7Echo Shard + Awkward");
        registerPotionLines(t, "infinity", "\n\n§5The boundless source.§r", "", "Obtained through advanced alchemy.");
        registerPotionLines(t, "rust", "\n\n§6The age of rust.§r", "", "§7Oxydation + Awkward");
        registerPotionLines(t, "saturation", "\n\n§cThe concentrated feast.§r", "", "§7Beetroot + Awkward");
        registerPotionLines(t, "short_cooldown", "\n\n§eSurhuman vivacity.§r", "", "§7Sunflower + Awkward");
        registerPotionLines(t, "stun", "\n\n§8Synaptic overload.§r", "", "§7Charged Copper + Awkward");
        registerPotionLines(t, "teleportation", "\n\n§5Unstable quantum leap.§r", "", "§7Ender Pearl + Awkward");
        registerPotionLines(t, "thorns", "\n\n§2Thorns of flesh.§r", "", "§7Cactus + Awkward");
        registerPotionLines(t, "unstable", "\n\n§6A powder keg on legs.§r", "", "Obtained through alchemical rituals.");
        registerPotionLines(t, "vampirism", "\n\n§cBlood for blood.§r", "", "§7Blood Bag + Awkward");
        registerPotionLines(t, "xp_boost", "\n\n§bSpirit of learning.§r", "", "§7Lapis Lazuli + Awkward");
        registerPotionLines(t, "xp_reduction", "\n\n§8Amnesia of the soul.§r", "", "§7Fermented Eye + XP Boost");
        registerPotionLines(t, "xp_life", "\n\n§aSacrificing lore for flesh.§r", "", "§7Glistering Melon + XP Boost");
        registerPotionLines(t, "zeus", "\n\n§6Wrath of the heavens.§r", "", "§7Materia Prima + Stun");
        registerPotionLines(t, "cold", "\n\n§bWinter torpor.§r", "", "§7Snowball + Awkward");
        registerPotionLines(t, "clumsiness", "\n\n§7Butterfingers.§r", "", "Brewing formula lost to time.");
        registerPotionLines(t, "asthma", "\n\n§8Lungs of ash.§r", "", "Brewing formula lost to time.");
        registerPotionLines(t, "paranoia", "\n\n§5Shadows in the corners.§r", "", "Brewing formula lost to time.");
        registerPotionLines(t, "medusa", "\n\n§2The fatal gaze.§r", "", "§7Materia Prima + Strong Petrification");
        registerPotionLines(t, "midas", "\n\n§6The gilded curse.§r", "", "§7Materia Prima + Alchemist");
        registerPotionLines(t, "active_teleport", "\n\n§5Focused displacement.§r", "", "§7Sulfur Ball + Teleportation");
        registerPotionLines(t, "hydrophobia", "\n\n§8Rejection of the waves.§r", "", "§7Fermented Eye + Water Breathing");
        registerPotionLines(t, "zombie_contagion", "\n\n§2The plague of grave-gnawers.§r", "", "§7Rotten Flesh + Awkward");
        registerPotionLines(t, "magnetism", "\n\n§bAttractive gravity.§r", "", "§7Iron Ingot + Awkward");
        registerPotionLines(t, "reality_check", "\n\n§5Cognitive collapse.§r", "", "Brewing formula lost to time.");

        // Minecraft Base Potions
        registerPotionLines(t, "fire_resistance", "\n\n§6Skin of the salamander.§r", "", "§7Magma Block + Awkward");
        registerPotionLines(t, "regeneration", "\n\n§cCellular blossoming.§r", "", "§7Golden Apple + Awkward");
        registerPotionLines(t, "strength", "\n\n§cSurge of raw power.§r", "", "§7Firework Star + Awkward");
        registerPotionLines(t, "swiftness", "\n\n§bOutrunning time.§r", "", "§7Sugar + Awkward");
        registerPotionLines(t, "poison", "\n\n§aThe alchemist's blight.§r", "", "§7Poisonous Carrot + Awkward");
        registerPotionLines(t, "healing", "\n\n§cInstant flash of life.§r", "", "§7Glistering Melon + Awkward");
        registerPotionLines(t, "night_vision", "\n\n§ePupilles of the feline.§r", "", "§7Golden Carrot + Awkward");
        registerPotionLines(t, "invisibility", "\n\n§8Refraction of light.§r", "", "§7Fermented Eye + Night Vision");
        registerPotionLines(t, "water_breathing", "\n\n§bEphemeral gills.§r", "", "§7Pufferfish + Awkward");
        registerPotionLines(t, "leaping", "\n\n§3Defying heights.§r", "", "§7Rabbit Foot + Awkward");
        registerPotionLines(t, "turtle_master", "\n\n§2The shell of the mind.§r", "", "§7Turtle Shell + Awkward");
        registerPotionLines(t, "slow_falling", "\n\n§fPlume descent.§r", "", "§7Phantom Membrane + Awkward");
        registerPotionLines(t, "weakness", "\n\n§7Lethargy of the muscles.§r", "", "§7Fermented Eye + Strength");
        registerPotionLines(t, "slowness", "\n\n§7Leaden pace.§r", "", "§7Fermented Eye + Swiftness");
        registerPotionLines(t, "harming", "\n\n§cDistilled bane.§r", "", "§7Fermented Eye + Healing");

        // Elixirs
        registerPotionLines(t, "perm_health", "\n\n§cThe flesh transcended.§r", "", "Obtained through alchemical rituals.");
        registerPotionLines(t, "perm_speed", "\n\n§bEternal velocity.§r", "", "Obtained through alchemical rituals.");
        registerPotionLines(t, "perm_strength", "\n\n§cMark of the Titan.§r", "", "Obtained through alchemical rituals.");
    }

    private static void registerPotionLines(TranslationBuilder t, String id, String resume, String explanation, String brew) {
        t.add("book.potions-n-rituals.page." + id + ".resume", resume);
        t.add("book.potions-n-rituals.page." + id + ".explanation", explanation);
        t.add("book.potions-n-rituals.page." + id + ".brew", brew);
    }
}