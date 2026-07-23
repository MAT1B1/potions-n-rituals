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
        registerPotionLines(t, "levitation",
                "\nLa gravité est un poids sur les épaules, oubliez-la.",
                "Vous tombez lentement, si lentement que vous ne subissez aucun dégât de chute.\n\nC'est comme si vous étiez sur la §3Lune§r.\n\n§4Attention, cela ne dure qu'un instant.§r",
                "Une §3Potion de Chute Lente§r avec une §3plume§r devrait suffire à alléger votre fardeau.");
        registerPotionLines(t, "adhesion",
                "\nMmh c'est un peu...\n\n§2collant§r.",
                "Vous adhérez aux surfaces, ce qui vous permet de marcher sur les murs.\n\nTrès utile pour repousser les araignées.",
                "Trouvez quelque chose de collant, comme de la §2résine§r. Mélangez-la avec une §2potion étrange§r, cela devrait suffire.");
        registerPotionLines(t, "glowing",
                "\nC'est un peu sombre ici...\n\nMais je peux très bien vous §6voir§r.",
                "Rend les entités §6lumineuses§r pour que vous puissiez les voir dans le noir et à travers les murs.",
                "Une §6Potion de Vision Nocturne§r vous permet de voir dans le noir tandis qu'une §6baie lumineuse§r vous fait briller... assez direct.");
        registerPotionLines(t, "alcohol",
                "\n\n§2*hic*§r... Juste une dernière goutte.",
                "Donne à l'entité un sentiment de perte.\n\nVous §2perdez le contrôle§r, pourtant tout semble si §2drôle§r !", "Mélangez des §2baies sucrées§r et une §2potion étrange§r pour obtenir de la liqueur.");
        registerPotionLines(t, "darkness",
                "\nTout disparaît, seul le §7vide§r continue de se montrer.",
                "Vous perdez la vue, tout devient noir.\n\nIl n'y a qu'une seule chose à retenir...\n\n§4FUYEZ§r\n\nIl est proche...", "Une poche d'encre mélangée à une potion étrange est idéale pour imiter l'effet.");
        registerPotionLines(t, "haste",
                "\n\nLe rêve d'un mineur...\n\nC'est tellement §6rapide§r !!",
                "Vous pouvez tout miner plus §6rapidement§r,\n\nvraiment très §6vite§r.",
                "Les chats creusent beaucoup, donc une §6griffe§r avec une §6potion étrange§r pourrait vous donner la formule.");
        registerPotionLines(t, "mining_fatigue",
                "\nWow, c'est une §7pierre dure§r ! \n\nJe ne peux pas la casser.",
                "Vous minez tout plus §4lentement§r,\n\nvraiment très §4lentement§r.",
                "Vous inversez la §4potion de célérité§r avec un §4œil d'araignée fermenté§r.");
        registerPotionLines(t, "acid",
                "Substance hautement §2toxique§r,\n\nÀ utiliser avec précaution.\n\nPeut causer des indigestions.",
                "Inflige des dégâts toutes les secondes.",
                "Mélangez de la §2viande putréfiée§r et une §2potion de poison§r pour obtenir un mélange hautement toxique.");
        registerPotionLines(t, "alchemist",
                "\nLe rêve de tout alchimiste,\n\ndu §7charbon§r qui devient de l'§6or§r.",
                "Transforme les objets en §7Charbon§r en leur variante d'§6Or§r.",
                "Prenez un objet d'alchimiste comme la §6boule de mercure§r et une §6potion étrange§r pour réaliser ce rêve.");
        registerPotionLines(t, "giant",
                "\nFaites attention aux plafonds bas...\n\nNe regarder pas le monde de §3haut§r.",
                "Vous grandissez de manière démesurée, ce qui augmente votre allonge mais vous rend plus facile à cibler.",
                "Une simple pincée de §7poudre d'os§r dans une §7potion étrange§r suffit à stimuler une croissance hors norme.");
        registerPotionLines(t, "ignition",
                "\nIl fait §6chaud§r tout d'un coup, non ?",
                "Immole instantanément l'entité, la faisant brûler sans aucune source de chaleur externe.\n\nMais permet de lancer des §6boule de feu§r sur commande.",
                "Corrompez une potion de §7résistance au feu§r avec un §7œil d'araignée fermenté§r pour obtenir l'effet inverse.");
        registerPotionLines(t, "petrification",
                "\nImpossible de bouger le petit doigt.\n\nMais elle était si belle...",
                "Transforme vos membres en pierre. Vous ne pouvez plus bouger, mais votre défense est grandement augmentée, vous ne ressentez plus rien.",
                "Faites fondre de l'§7obsidienne§r dans une potion du §7Maître Tortue§r pour figer définitivement le mouvement.");
        registerPotionLines(t, "resurrection",
                "\nLa mort n'est qu'un contretemps.\n\nIl s'agit de tromper la §dfaucheuse§r.",
                "Si vous subissez un coup fatal sous cet effet, vous êtes instantanément ramené à la vie avec une partie de votre santé.",
                "L'essence d'un §7Totem d'Éternité§r diluée dans une §7potion étrange§r permet de tromper la mort une fois de plus.");
        registerPotionLines(t, "berserk",
                "\nPlus rien d'autre ne compte que le sang.\n\nLe §cValhalla§r ouvrira peut être ces porte.",
                "Votre force physique et vitesse est décuplée, votre adrénaline vous empêche de mourir, mais faite attention au contre-coups.",
                "La chaleur d'une §7fleur-torche§r infusée dans une §7potion étrange§r réveillera votre rage intérieure.");
        registerPotionLines(t, "brainwashing",
                "\nIls vous écouteront, sans poser de questions.",
                "Permet de rallier les monstres morts-vivants à votre cause. Ils attaqueront vos ennemis.\n\nLes villagois mélangerons les prix.\n\nEt vous, vous perderez pieds.",
                "Un §7cerveau de zombie§r macéré dans une §7potion étrange§r suffit à prendre le contrôle de leurs esprits dociles.");
        registerPotionLines(t, "death",
                "\nIl n'y a pas d'échappatoire.\n\nC'est §8l'arrêt définitif§r...",
                "Inflige des dégâts massifs et inévitables qui consument la force vitale jusqu'à ce que mort s'ensuive.",
                "Inversez le miracle de la potion de §7résurrection§r en y trempant une cruelle §7rose du Wither§r.");
        registerPotionLines(t, "double_health",
                "\nDeux fois plus dur à tuer.\n\nUn second §csouffle de vie§r.",
                "Double instantanément votre jauge de santé maximale, c'est une vie qui §cabsorbe§r les dégâts mais ne se régenere pas.",
                "L'abondance d'une §7pomme dorée§r combinée à un §7soin instantané puissant§r renforce considérablement votre vitalité.");
        registerPotionLines(t, "dwarf",
                "\nAttention où vous mettez les pieds !\n\nVous faites maintenant §33 pommes§r de haut.",
                "Réduit drastiquement votre taille. Vous pouvez vous faufiler partout, mais votre allonge est ridicule.",
                "Il suffit de corrompre une potion de §7géant§r avec un §7œil d'araignée fermenté§r pour inverser la croissance.");
        registerPotionLines(t, "frost",
                "\nVos articulations se figent.\n\nIl fait froid, non ?",
                "Inflige des dégâts de froid continus et ralentit considérablement les mouvements de la cible.",
                "Plongez une §7boule de neige§r dans une potion de §7rhume§r pour obtenir un froid véritablement mordant.");
        registerPotionLines(t, "ghost_walk",
                "\nLes murs ne sont plus que des suggestions.",
                "Vous permet de traverser les blocs solides comme si vous étiez un véritable spectre, mais vous avez une vision considérablement réduite.",
                "La tristesse d'une §7larme de Ghast§r distillée dans une §7potion étrange§r vous dématérialise partiellement.");
        registerPotionLines(t, "liquid_walker",
                "\nPourquoi §3nager§r quand on peut marcher ?",
                "Vous permet de marcher sur l'eau.\n\nPour marcher sur la lave il faudra une version plus forte.",
                "Faites bouillir un §7nénuphar§r dans une §7potion étrange§r pour apprendre à défier la physique des liquides.");
        registerPotionLines(t, "long_cooldown",
                "\nVos bras sont si §8lourds§r...",
                "Augmente drastiquement le temps de recharge pour l'utilisation de potions et l'activation d'effet actif. Ils demandent un effort colossal.",
                "Corrompez une potion de §7cooldown réduit§r avec un §7œil d'araignée fermenté§r pour ralentir vos réflexes.");
        registerPotionLines(t, "long_leg",
                "\nDes pas de géant dans un corps humain.",
                "Vous permet de franchir des blocs sans avoir à sauter, comme si vous étiez monté sur des échasses.",
                "La flexibilité d'un bout de §7bambou§r dans une §7potion étrange§r allongera votre foulée.");
        registerPotionLines(t, "love",
                "\nFaites §dl'amour§r, pas la guerre.",
                "Effet actif qui permet de se §dreproduire§r avec n'importe quelle espèces",
                "Le parfum délicat d'un §7coquelicot§r infusé dans une §7potion étrange§r adoucit les mœurs.");
        registerPotionLines(t, "masking",
                "\n§kqssdqds§r §kq§r §kqssdq§r §kqssd§r",
                "Rend les effets invisibles.",
                "Sublimez une simple potion d'§7invisibilité§r avec un §7œil d'araignée fermenté§r pour tordre votre vision de la réalité.");
        registerPotionLines(t, "no_interaction",
                "\nRegarder sans jamais toucher.",
                "Vous empêche de casser des blocs, d'ouvrir des coffres ou de frapper des entités.",
                "Corrompez une potion de §7lavage de cerveau§r avec une §7patte de lapin§r pour devenir totalement impuissant.");
        registerPotionLines(t, "oblivion",
                "\nOù suis-je ? Qui suis-je ?\n\nC'est le §5grand vide.§r",
                "Vide une partie de votre inventaire.",
                "Plongez une §7Fleur de Broc§r dans une §7potion étrange§r pour créer un puissant amnésique liquide.");
        registerPotionLines(t, "ore_sense",
                "\nLes §6trésors§r de la terre vous appellent.",
                "Vous ressentez les minerais précieux à travers la roche, ça facilite vos sessions de minage.",
                "L'alliance d'un §7minerai de fer§r et d'une potion de §7vision nocturne§r affûtera vos sens souterrains.");
        registerPotionLines(t, "photosynthesis",
                "\nLe soleil est votre meilleur repas.\n\nVous êtes nourri par §2l'astre divin§r.",
                "Restaure lentement votre faim tant que vous vous tenez sous la lumière directe du soleil.",
                "Écrasez du §7feuillage§r dans une §7potion étrange§r pour assimiler la force des plantes.");
        registerPotionLines(t, "purification",
                "\nToute §7souillure§r doit disparaître.",
                "Nettoie instantanément tous les effets négatifs sans toucher à vos bonus actifs.",
                "Un §7seau de lait§r concentré et stabilisé dans une §7potion étrange§r purgera votre organisme.");
        registerPotionLines(t, "reactivation",
                "\nLe §6temps§r est de votre côté.",
                "Ajoute du temps supplémentaire à tous les effets actifs.",
                "Le pouvoir solaire d'un §7tournesol§r ajouté à une potion de §7cooldown réduit§r force le destin.");
        registerPotionLines(t, "resonance",
                "\nVous émettez une certaine onde, les entités l'imitent.",
                "Vous transmettez vos effets actifs aux entités qui se trouvent à une certaine distance.",
                "Réduisez un §7éclat d'écho§r en poudre dans une §7potion étrange§r pour réveiller cette capacité.");
        registerPotionLines(t, "infinity",
                "\nLa matière se renouvelle d'elle-même.\n\n§5La source sans limites.§r",
                "Vos effets actifs prennent une durée infini, mais vous perdez 2 coeurs par effets",
                "Ce pouvoir divin ne s'obtient que par le biais de l'§5alchimie avancée§r.");
        registerPotionLines(t, "rust",
                "\nL'acier se §6détériore§r, le fer se §6brise§r.",
                "Ronge rapidement la durabilité des armures de l'entité affectée.",
                "Faites dissoudre un §7fragment d'oxydation§r dans une §7potion étrange§r pour créer ce fléau du métal.");
        registerPotionLines(t, "saturation",
                "\nUn §cfestin§r digne d'un roi en une gorgée.",
                "Remplit progressivement votre barre de saturation.",
                "Le sucre naturel d'une §7betterave§r concentré dans une §7potion étrange§r vous coupera l'appétit pour de bon.");
        registerPotionLines(t, "short_cooldown",
                "\nVos bras sont si §6lourds§r...",
                "Diminue drastiquement le temps de recharge pour l'utilisation de potions et l'activation d'effet actif. Ils demandent si peu d'effort.",
                "L'énergie d'un §7tournesol§r infusée dans une §7potion étrange§r boostera votre dextérité.");
        registerPotionLines(t, "stun",
                "\nVotre cerveau vient de faire un §8court-circuit§r.",
                "Paralyse partiellement l'entité pendant un court instant, l'empêchant de faire la moindre action.",
                "Le choc électrique d'un bloc de §7cuivre chargé§r libéré dans une §7potion étrange§r assomme à coup sûr.");
        registerPotionLines(t, "teleportation",
                "\nOù allez-vous §5atterrir§r cette fois ?",
                "Vous téléporte de manière aléatoire.\n\nMais si vous avez une version pus forte, vous serez téléporté pour sûr chez vous.",
                "L'instabilité naturelle d'une §7perle de l'Ender§r diluée dans une §7potion étrange§r provoque des bonds dans l'espace.");
        registerPotionLines(t, "thorns",
                "\nQui s'y frotte s'y §2pique§r.",
                "Fait pousser des épines sur votre peau. Les ennemis qui vous frappent au corps-à-corps subissent des dégâts.",
                "Le jus d'un §7cactus§r pressé dans une §7potion étrange§r durcira votre épiderme.");
        registerPotionLines(t, "unstable",
                "\nAttention, ça va §6péter§r !",
                "Effet extrêmement instable, vous avez de forte chances d'exploser, une chose est sur, rien de bon ne vient de cet effet.",
                "Si vous faite n'importe quoi avec vos recettes, vous risquez de créer cette potion.");
        registerPotionLines(t, "vampirism",
                "\nLeur douleur est votre remède.\n\n§cDu sang pour du sang.§r",
                "Une portion des dégâts que vous infligez à vos ennemis vous est rendue sous forme de points de vie, mais vous brûler au soleil.",
                "Une macabre §7poche de sang§r mélangée à une §7potion étrange§r réveille la soif de l'hémoglobine.");
        registerPotionLines(t, "xp_boost",
                "\nChaque leçon est gravée dans le marbre.",
                "Augmente drastiquement la quantité d'expérience que vous récoltez depuis n'importe quelle source.",
                "La sagesse contenue dans le §7Lapis-lazuli§r se libère lorsqu'il est plongé dans une §7potion étrange§r.");
        registerPotionLines(t, "xp_reduction",
                "\nDeux fois plus de travail pour la même expérience.",
                "Diminue drastiquement la quantité d'expérience que vous récoltez depuis n'importe quelle source.",
                "L'amertume d'un §7œil fermenté§r altère les propriétés d'une potion de §7boost d'XP§r.");
        registerPotionLines(t, "xp_life",
                "\nVotre savoir coule dans vos veines.",
                "Votre vie est remplacé par votre bar d'expérience.\n\nLe savoir devient votre chair !",
                "Le croisement d'une §7tranche de melon scintillant§r et d'une potion de §7boost d'XP§r convertit l'esprit en chair.");
        registerPotionLines(t, "zeus",
                "\nLa foudre frappe toujours deux fois.",
                "Vos attaques invoque la colère de §3Zeus§r sur la cible, vous pouvez aussi l'invoquer manuellement.",
                "Associez de la mystique §7Materia Prima§r à une potion d'§7étourdissement§r pour invoquer la foudre.");
        registerPotionLines(t, "cold",
                "\n§3Brrrr§r... Prévoyez un manteau.",
                "Vous toussez de manière aléatoire, transmettant ainsi votre maladie a d'autre entité.",
                "Une banale §7boule de neige§r fondue dans une §7potion étrange§r refroidira les ardeurs de n'importe qui.");
        registerPotionLines(t, "clumsiness",
                "\n§7Oups§r, je l'ai encore fait tomber.",
                "Force la cible à lâcher aléatoirement les objets qu'elle tient en main.",
                "Corrompez une §7potion d'alcool§r avec un §7œil d'araignée fermenté§r pour obtenir cette mixture chaotique.");
        registerPotionLines(t, "asthma",
                "\nL'air semble si §8épais§r...",
                "Réduit votre barre d'oxygène si vous courez, même à l'air libre. Vous infligeant des dégâts de suffocation si vous manquez d'air.",
                "Un §7poumon de zombie§r macéré dans une §7potion étrange§r obstruera vos voies respiratoires.");
        registerPotionLines(t, "paranoia",
                "\nAvez-vous entendu ce bruit ?",
                "Fait entendre à la cible des bruits de monstres effrayants qui n'existent pas.",
                "Une §7tête de Creeper§r infusée dans une §7potion étrange§r éveille vos peurs les plus sombres.");
        registerPotionLines(t, "medusa",
                "\nNe la regardez pas dans les yeux !\n\nElle a le §2regard§r qui tue...",
                "Pétrifie, si vous le souhaitez, toute entité qui croise votre regard.",
                "Catalysez une puissante potion de §7pétrification§r avec de la §7Materia Prima§r pour transmettre la malédiction.");
        registerPotionLines(t, "midas",
                "\nTout ce qui §6brille§r... coûte cher.",
                "Transforme les armures et armes dans leurs version dorée...",
                "L'amplification d'une potion d'§7alchimiste§r avec de la §7Materia Prima§r rend la transmutation incontrôlable.");
        registerPotionLines(t, "active_teleport",
                "\nUn §5clignement d'œil§r et vous y êtes.",
                "Vous téléporte instantanément, lorsque vous en avez envie, à l'endroit exact que vous regardez (dans une limite de portée).",
                "Le soufre volatil d'une §7boule de soufre§r stabilise les effets chaotiques d'une potion de §7téléportation§r.");
        registerPotionLines(t, "hydrophobia",
                "\nL'§8eau§r brûle comme de l'acide.",
                "Inflige de lourds dégâts continus si la cible entre en contact avec de l'eau ou la pluie.",
                "Corrompre une potion d'§7apnée§r avec un §7œil d'araignée fermenté§r rendra toute goutte d'eau mortelle.");
        registerPotionLines(t, "zombie_contagion",
                "\nVous avez été mordus...\n\nIl n'existe pas d'échappatoire.",
                "Vous étés infectés par la contagion des zombies, vous vous transformer bientôt en zombie",
                "La corruption d'une §7viande putréfiée§r laissée à pourrir dans une §7potion étrange§r.");
        registerPotionLines(t, "magnetism",
                "\nTout vient à point à qui sait attirer.",
                "Aspire tous les objets au sol situés dans un large rayon autour de vous.",
                "L'attraction d'un lourd §7lingot de fer§r plongé dans une §7potion étrange§r magnétise votre corps.");
        registerPotionLines(t, "reality_check",
                "\nPourquoi tout est si §5lourd§d ??",
                "Dissipe instantanément toutes les illusions, votre inventaire est plein, donc vous êtes lourd.",
                "L'écrasante présence d'une §7enclume§r infusée dans une §7potion étrange§r vous ramène à la réalité.");

        // Potions de base Minecraft
        registerPotionLines(t, "fire_resistance",
                "\nUn §6bain de lave§r n'a jamais fait de mal à personne.",
                "Vous immunise totalement contre les dégâts de feu, de lave et d'embrasement.\n\nIdéal pour un séjour dans le Nether.",
                "Faites fondre un §7bloc de magma§r dans une §7potion étrange§r pour ignorer la chaleur de ce monde.");
        registerPotionLines(t, "regeneration",
                "\nVos plaies se referment à vue d'œil.",
                "Restaure progressivement vos points de vie au fil du temps sans avoir besoin de manger.",
                "L'essence d'une §7pomme dorée§r infusée dans une §7potion étrange§r stimule votre guérison naturelle.");
        registerPotionLines(t, "strength",
                "\nVous vous sentez capable de soulever des montagnes.",
                "Augmente considérablement les dégâts infligés par vos attaques de mêlée.",
                "L'énergie explosive d'une §7étoile de feu d'artifice§r infusée dans une §7potion étrange§r décuplera votre force musculaire.");
        registerPotionLines(t, "swiftness",
                "\nLe vent souffle dans vos oreilles.",
                "Augmente votre vitesse de déplacement et élargit légèrement votre champ de vision.",
                "L'énergie d'un simple morceau de §7sucre§r dissous dans une §7potion étrange§r accélère votre rythme cardiaque.");
        registerPotionLines(t, "poison",
                "\nUn goût amer qui vous ronge de l'intérieur.",
                "Vous inflige des dégâts réguliers sur la durée, mais ne peut pas vous achever. L'effet s'arrête à un demi-cœur.",
                "Faites macérer un §7légume empoisonnée§r dans une §7potion étrange§r pour en extraire le venin toxique.");
        registerPotionLines(t, "healing",
                "\nUn soulagement pur et immédiat.",
                "Restaure instantanément une partie de vos points de vie.\n\n§4Blesse§r les créatures mort-vivantes.",
                "Le jus radieux d'une §7tranche de melon scintillant§r infusé dans une §7potion étrange§r referme vos blessures sur-le-champ.");
        registerPotionLines(t, "night_vision",
                "\nLes ténèbres n'ont plus aucun secret pour vous.",
                "Illumine la zone autour de vous, vous permettant de voir parfaitement dans l'obscurité totale et sous l'eau.",
                "Croquez dans le pouvoir d'une §7carotte dorée§r distillée dans une §7potion étrange§r pour percer l'obscurité.");
        registerPotionLines(t, "invisibility",
                "\nOù êtes-vous passé ?",
                "Rend votre corps complètement invisible aux yeux des autres.\n\n§4Attention, vos armures et objets tenus restent visibles.§r",
                "Corrompez une potion de §7vision nocturne§r avec un §7œil fermenté§r pour tromper la lumière elle-même.");
        registerPotionLines(t, "water_breathing",
                "\nVos poumons s'adaptent aux profondeurs.\n\n§3Ouïes éphémères.§r",
                "Empêche votre barre d'oxygène de se vider sous l'eau, vous permettant de rester immergé indéfiniment.",
                "Gonflez une §7potion étrange§r avec un §7poisson-globe§r pour respirer comme un habitant des océans.");
        registerPotionLines(t, "leaping",
                "\nLa gravité a moins d'emprise sur vous.",
                "Augmente la hauteur de vos sauts de manière significative et réduit les dégâts de chute que vous subissez.",
                "La chance d'une §7patte de lapin§r infusée dans une §7potion étrange§r vous donnera des ressorts sous les pieds.");
        registerPotionLines(t, "turtle_master",
                "\nLent, mais totalement indestructible.",
                "Réduit drastiquement votre vitesse de déplacement, mais vous accorde une résistance inébranlable aux dégâts physiques.",
                "Faites bouillir une §7carapace de tortue§r dans une §7potion étrange§r pour adopter la sagesse et la robustesse du reptile.");
        registerPotionLines(t, "slow_falling",
                "\nPlus léger qu'un flocon de neige.",
                "Ralentit considérablement votre vitesse de chute, vous immunisant totalement contre les dégâts d'atterrissage.",
                "Les restes d'un chasseur nocturne, une §7membrane de Phantom§r, dilués dans une §7potion étrange§r allégeront votre corps.");
        registerPotionLines(t, "weakness",
                "\nVos bras vous abandonnent.",
                "Réduit considérablement les dégâts que vous infligez au corps-à-corps.\n\nUtile pour soigner les villageois zombies.",
                "La moisissure d'un §7œil d'araignée fermenté§r diluée dans une simple fiole d'§7eau§r affaiblit tout organisme.");
        registerPotionLines(t, "slowness",
                "\nComme marcher dans de la mélasse.",
                "Diminue grandement votre vitesse de déplacement et réduit légèrement votre champ de vision.",
                "La corruption d'une potion de §7vitesse§r par un §7œil fermenté§r vous privera de tout votre élan.");
        registerPotionLines(t, "harming",
                "\nUne douleur fulgurante et instantanée.",
                "Inflige des dégâts magiques instantanés qui ignorent la plupart des armures.\n\n§2Soigne les créatures mort-vivantes.§r",
                "Pervertissez une potion de §7soin§r à l'aide d'un §7œil fermenté§r pour transformer le remède en poison mortel.");

        // Élixirs Permanents
        registerPotionLines(t, "perm_health",
                "\nVotre corps change, pour toujours.",
                "Augmente définitivement votre nombre de cœurs maximum.",
                "Ce puissant élixir ne peut être §cobtenu que via des rituels alchimiques§r longs, obscurs et coûteux.");
        registerPotionLines(t, "perm_speed",
                "\nRapide, à chaque instant de votre vie.",
                "Augmente définitivement votre vitesse de déplacement de base.\n\nVos jambes ne se fatigueront plus jamais.",
                "La maîtrise absolue du mouvement est une magie §3obtenue uniquement via des rituels alchimiques§r complexes.");
        registerPotionLines(t, "perm_strength",
                "\nLes dieux vous ont béni.",
                "Augmente de façon permanente la puissance de tous vos coups au corps-à-corps.\n\nRien ne pourra plus vous résister.",
                "Une telle puissance brute doit être §cobtenue via des rituels alchimiques§r d'une précision et d'une dangerosité absolues.");

        // --- AJOUTS CITRINITAS (CitrinitasBookItem) ---
        t.add("book.potions-n-rituals.citrinitas.chapter.rituals", "Les Rituels");

        t.add("book.potions-n-rituals.page.citrinitas.skeleton_horse", "Cheval Squelette");
        t.add("book.potions-n-rituals.page.citrinitas.skeleton_horse.desc", "Invoque un cheval squelette.");
        t.add("book.potions-n-rituals.page.citrinitas.ancient_debris", "Débris Antiques");
        t.add("book.potions-n-rituals.page.citrinitas.ancient_debris.desc", "Fait apparaître des débris antiques, la clé pour l'équipement en Netherite.");
        t.add("book.potions-n-rituals.page.citrinitas.random_effect", "Effet Aléatoire");
        t.add("book.potions-n-rituals.page.citrinitas.random_effect.desc", "Applique un effet aléatoire à vous-même.");
        t.add("book.potions-n-rituals.page.citrinitas.summon_thunderstorm", "Orage");
        t.add("book.potions-n-rituals.page.citrinitas.summon_thunderstorm.desc", "Déclenche un violent orage.");
        t.add("book.potions-n-rituals.page.citrinitas.echo_shard", "Éclat d'Écho");
        t.add("book.potions-n-rituals.page.citrinitas.echo_shard.desc", "Produit un éclat d'écho, une ressource rare des profondeurs.");
        t.add("book.potions-n-rituals.page.citrinitas.summon_dawn", "Aube");
        t.add("book.potions-n-rituals.page.citrinitas.summon_dawn.desc", "Fait apparaître les premières lueurs de l'aube.");
        t.add("book.potions-n-rituals.page.citrinitas.nether_gate", "Portail du Nether");
        t.add("book.potions-n-rituals.page.citrinitas.nether_gate.desc", "Ouvre un portail vers le Nether et vous y téléporte.");
        t.add("book.potions-n-rituals.page.citrinitas.seal_nether", "Sceau du Nether");
        t.add("book.potions-n-rituals.page.citrinitas.seal_nether.desc", "Scelle le Nether pour empêcher tout accès.");

        // --- AJOUTS ALBEDO (AlbedoBookItem) ---
        t.add("book.potions-n-rituals.page.alchemy_guide_albedo.chapter.path", "Chemin vers le Citrinitas");
        t.add("book.potions-n-rituals.page.alchemy_guide_albedo.chapter.rituals", "Rituel");
        t.add("book.potions-n-rituals.page.alchemy_guide_albedo.chapter.talisman", "Talisman");
        t.add("book.potions-n-rituals.page.alchemy_guide_albedo.chapter.artifacts", "Les Artefacts");

        // Textes des pages
        t.add("book.potions-n-rituals.page.nether_gate.text", "Vous aurez besoin de partir en enfer avec un rituel. Pour l'activer, il faut sacrifier une entité à l'intérieur du rituel.");
        t.add("book.potions-n-rituals.page.ritual_pattern", "Structure du Rituel");
        t.add("book.potions-n-rituals.page.talisman_recipe", "Recette du Talisman");

        // --- AJOUTS ARTEFACTS (Chapitre : Les Artefacts) ---
        t.add("book.potions-n-rituals.page.alchemy_guide_albedo.artifact_page", "Les Artefacts");
        t.add("book.potions-n-rituals.page.artifact.alchemical_bag.desc", "Permet de stocker des objets dans une dimension séparée.");
        t.add("book.potions-n-rituals.page.artifact.alchemical_bag.craft", "Vous donnez du pouvoir à un bundle avec un talisman.");
        t.add("book.potions-n-rituals.page.artifact.spirit_mirror.desc", "Permet de naviguer vers une dimension différente.");
        t.add("book.potions-n-rituals.page.artifact.nether_seal_breaker.desc", "Brise le sceau du Nether et crée un portail.");
        t.add("book.potions-n-rituals.page.artifact.nether_seal_breaker.craft", "Vous devez visiter chaque partie du Nether pour récupérer les blocs nécessaires.");
        t.add("book.potions-n-rituals.page.artifact.decoy.desc", "Crée un Leurre qui distrait les mobs hostiles.");
        t.add("book.potions-n-rituals.page.artifact.alchemical_stone.desc", "La pierre peut stocker des effets de potions pour les appliquer au sol.");
        t.add("book.potions-n-rituals.page.artifact.gauntlet.desc", "Permet de stocker plusieurs pierre alchimique et de cycler parmis eux.");
        t.add("book.potions-n-rituals.page.artifact.gauntlet.craft", "Ajouter des pierres alchimiques avec le Gant de l'Absolu pour les ajouter.");
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
        registerPotionLines(t, "levitation",
                "\nGravity is a weight on the shoulders, forget it",
                "You fall slowly, so slowly that you don't take fall damage.\n\nIt's like you are on the §3Moon§r.\n\n§4Be careful, it only last a moment.§r",
                "A §3Slow Falling potion§r with a §3feather§r should be good enough to lighten your burden.");
        registerPotionLines(t, "adhesion",
                "\nMmh that's a bit...\n\n§2sticky§r.",
                "You stick to surfaces, allowing you to walk on walls.\n\nVery useful for repelling away spiders.",
                "Find something sticky, maybe like §2resin§r. Mix it with an §2awkward potion§r, it should be sufficient.");
        registerPotionLines(t, "glowing",
                "\nIt's a bit dark in here...\n\nBut I can §6see§r you very well",
                "Make entity §6glow§r so that you can see them in the dark and behind walls.",
                "A §6Night Vision Potion§r allow you to see in the dark while a §6glow berry§r make you glow... pretty straight forward.");
        registerPotionLines(t, "alcohol",
                "\n\n§2*hic*§r... Just one last drop.",
                "Gives entity a feeling of loss.\n\nYou §2lose control§r, yet everything seems so §2funny§r !", "Mix §2sweet berries§r and an §2awkward potion§r, to get some liquor.");
        registerPotionLines(t, "darkness",
                "\nEverything disappear, only the §7void§r keep showing itself.",
                "You lose your sight, everything turn black.\n\nThere is just one thing to remember...\n\n§4RUN§r\n\nHe's close...",
                "An ink sac mixed with an awkward potion is great to mimic the effect.");
        registerPotionLines(t, "haste",
                "\n\nA miner's dream...\n\nIt's so §6fast§r !!",
                "You can mine everything §6faster§r,\n\nreally really §6fast§r.",
                "Cat dig a lot, so a §6claw§r with an §6awkward potion§r might give you the potion");
        registerPotionLines(t, "mining_fatigue",
                "\nWow, it's a §7hard stone§r ! \n\nI can't break it.",
                "You mine everything §4slower§r,\n\nreally really §4slow§r.",
                "You reverse the §4potion of haste§r with a §4fermented spider eye§r");
        registerPotionLines(t, "acid",
                "Substance highly §2toxic§r,\n\nUse with precaution.\n\nCan cause indigestion.",
                "Damages you every seconds",
                "Mix a §2rotten flesh§r and a §2poison potion§r to get a highly toxic mixture.");
        registerPotionLines(t, "alchemist",
                "\nThe dream of every alchemist,\n\n§7coal§r that become §6gold§r.",
                "Turn §7Coal§r items into their §6Gold§r variant.",
                "Take an alchemist item like the §6mercury ball§r and an §6awkward potion§r to achieve that dream.");
        registerPotionLines(t, "giant",
                "\nWatch out for low ceilings...\n\nDo not look down upon the world from §3above§r.",
                "You grow disproportionately, which increases your reach but makes you an easier target.",
                "A simple pinch of §7bone meal§r in an §7awkward potion§r is enough to stimulate unnatural growth.");
        registerPotionLines(t, "ignition",
                "\nIt's getting §6hot§r all of a sudden, isn't it?",
                "Instantly immolates the entity, causing it to burn without any external heat source.\n\nBut allows throwing §6fireballs§r on command.",
                "Corrupt a §7fire resistance potion§r with a §7fermented spider eye§r to obtain the opposite effect.");
        registerPotionLines(t, "petrification",
                "\nImpossible to move a single finger.\n\nBut she was so beautiful...",
                "Turns your limbs into stone. You can no longer move, but your defense is greatly increased, you feel nothing anymore.",
                "Melt some §7obsidian§r in a §7Turtle Master potion§r to permanently freeze movement.");
        registerPotionLines(t, "resurrection",
                "\nDeath is but a setback.\n\nIt is all about tricking the §dreaper§r.",
                "If you take a fatal blow under this effect, you are instantly brought back to life with a portion of your health.",
                "The essence of a §7Totem of Undying§r diluted in an §7awkward potion§r allows you to cheat death once more.");
        registerPotionLines(t, "berserk",
                "\nNothing else matters but blood.\n\n§cValhalla§r might open its doors.",
                "Your physical strength and speed are multiplied, your adrenaline prevents you from dying, but beware of the backlash.",
                "The heat of a §7torchflower§r infused in an §7awkward potion§r will awaken your inner rage.");
        registerPotionLines(t, "brainwashing",
                "\nThey will listen to you, no questions asked.",
                "Allows you to rally undead monsters to your cause. They will attack your enemies.\n\nVillagers will mix up their prices.\n\nAnd you, you will lose your footing.",
                "A §7zombie brain§r macerated in an §7awkward potion§r is enough to take control of their docile minds.");
        registerPotionLines(t, "death",
                "\nThere is no escape.\n\nIt is §8the definitive stop§r...",
                "Inflicts massive and unavoidable damage that consumes life force until death ensues.",
                "Reverse the miracle of the §7resurrection potion§r by dipping a cruel §7Wither rose§r into it.");
        registerPotionLines(t, "double_health",
                "\nTwice as hard to kill.\n\nA second §csurge of life§r.",
                "Instantly doubles your maximum health bar; it is a life that §cabsorbs§r damage but does not regenerate.",
                "The abundance of a §7golden apple§r combined with a §7strong healing potion§r considerably strengthens your vitality.");
        registerPotionLines(t, "dwarf",
                "\nWatch where you step!\n\nYou are now §3three apples§r tall.",
                "Drastically reduces your size. You can sneak in anywhere, but your reach is ridiculous.",
                "It is enough to corrupt a §7giant potion§r with a §7fermented spider eye§r to reverse the growth.");
        registerPotionLines(t, "frost",
                "\nYour joints freeze up.\n\nIt's cold, isn't it?",
                "Inflicts continuous cold damage and drastically slows the target's movements.",
                "Plunge a §7snowball§r into a §7cold potion§r to obtain a truly biting cold.");
        registerPotionLines(t, "ghost_walk",
                "\nWalls are merely suggestions now.",
                "Allows you to pass through solid blocks as if you were a true specter, but you have considerably reduced vision.",
                "The sadness of a §7Ghast tear§r distilled in an §7awkward potion§r partially dematerializes you.");
        registerPotionLines(t, "liquid_walker",
                "\nWhy §3swim§r when you can walk?",
                "Allows you to walk on water.\n\nTo walk on lava, you will need a stronger version.",
                "Boil a §7lily pad§r in an §7awkward potion§r to learn how to defy the physics of liquids.");
        registerPotionLines(t, "long_cooldown",
                "\nYour arms are so §8heavy§r...",
                "Drastically increases the cooldown time for using potions and activating active effects. They require a colossal effort.",
                "Corrupt a §7short cooldown potion§r with a §7fermented spider eye§r to slow down your reflexes.");
        registerPotionLines(t, "long_leg",
                "\nGiant steps in a human body.",
                "Allows you to step over blocks without having to jump, as if you were on stilts.",
                "The flexibility of a piece of §7bamboo§r in an §7awkward potion§r will lengthen your stride.");
        registerPotionLines(t, "love",
                "\nMake §dlove§r, not war.",
                "Active effect that allows you to §dbreed§r with any species.",
                "The delicate scent of a §7poppy§r infused in an §7awkward potion§r softens tempers.");
        registerPotionLines(t, "masking",
                "\n§kqssdqds§r §kq§r §kqssdq§r §kqssd§r",
                "Makes effects invisible.",
                "Sublimate a simple §7invisibility potion§r with a §7fermented spider eye§r to twist your vision of reality.");
        registerPotionLines(t, "no_interaction",
                "\nLook but never touch.",
                "Prevents you from breaking blocks, opening chests, or hitting entities.",
                "Corrupt a §7brainwashing potion§r with a §7rabbit foot§r to become completely powerless.");
        registerPotionLines(t, "oblivion",
                "\nWhere am I? Who am I?\n\nIt is the §5great void.§r",
                "Empties a part of your inventory.",
                "Plunge a §7pitcher plant§r into an §7awkward potion§r to create a powerful liquid amnesic.");
        registerPotionLines(t, "ore_sense",
                "\nThe earth's §6treasures§r are calling you.",
                "You can sense precious ores through the rock, which facilitates your mining sessions.",
                "The alliance of an §7iron ore§r and a §7night vision potion§r will sharpen your underground senses.");
        registerPotionLines(t, "photosynthesis",
                "\nThe sun is your best meal.\n\nYou are fed by the §2divine star§r.",
                "Slowly restores your hunger as long as you stand in direct sunlight.",
                "Crush some §7leaves§r in an §7awkward potion§r to assimilate the strength of plants.");
        registerPotionLines(t, "purification",
                "\nAll §7defilement§r must disappear.",
                "Instantly cleanses all negative effects without touching your active bonuses.",
                "A §7milk bucket§r concentrated and stabilized in an §7awkward potion§r will purge your organism.");
        registerPotionLines(t, "reactivation",
                "\n§6Time§r is on your side.",
                "Adds extra time to all active effects.",
                "The solar power of a §7sunflower§r added to a §7short cooldown potion§r forces destiny.");
        registerPotionLines(t, "resonance",
                "\nYou emit a certain wave; entities imitate it.",
                "You transmit your active effects to entities that are within a certain distance.",
                "Grind an §7echo shard§r into powder in an §7awkward potion§r to awaken this ability.");
        registerPotionLines(t, "infinity",
                "\nMatter renews itself.\n\n§5The boundless source.§r",
                "Your active effects take on an infinite duration, but you lose 2 hearts per effect.",
                "This divine power can only be obtained through §5advanced alchemy§r.");
        registerPotionLines(t, "rust",
                "\nSteel §6deteriorates§r, iron §6breaks§r.",
                "Rapidly eats away the durability of the affected entity's armor.",
                "Dissolve an §7oxidation fragment§r in an §7awkward potion§r to create this scourge of metal.");
        registerPotionLines(t, "saturation",
                "\nA §cfeast§r fit for a king in one gulp.",
                "Progressively fills your saturation bar.",
                "The natural sugar of a §7beetroot§r concentrated in an §7awkward potion§r will curb your appetite for good.");
        registerPotionLines(t, "short_cooldown",
                "\nYour arms feel so §6light§r...",
                "Drastically decreases the cooldown time for using potions and activating active effects. They require so little effort.",
                "The energy of a §7sunflower§r infused in an §7awkward potion§r will boost your dexterity.");
        registerPotionLines(t, "stun",
                "\nYour brain just §8short-circuited§r.",
                "Partially paralyzes the entity for a short moment, preventing it from taking any action.",
                "The electric shock of a §7charged copper§r block released into an §7awkward potion§r stuns for sure.");
        registerPotionLines(t, "teleportation",
                "\nWhere will you §5land§r this time?",
                "Teleports you randomly.\n\nBut if you have a stronger version, you will be teleported home for sure.",
                "The natural instability of an §7Ender pearl§r diluted in an §7awkward potion§r causes leaps in space.");
        registerPotionLines(t, "thorns",
                "\nTouch me and you'll get §2pricked§r.",
                "Grows thorns on your skin. Enemies who strike you in melee take damage.",
                "The juice of a §7cactus§r squeezed into an §7awkward potion§r will harden your epidermis.");
        registerPotionLines(t, "unstable",
                "\nWatch out, it's going to §6blow§r!",
                "Extremely unstable effect, you have a high chance of exploding. One thing is certain: nothing good comes from this effect.",
                "If you mess around with your recipes, you risk creating this potion.");
        registerPotionLines(t, "vampirism",
                "\nTheir pain is your cure.\n\n§cBlood for blood.§r",
                "A portion of the damage you inflict on your enemies is returned to you as health points, but you burn in the sun.",
                "A macabre §7blood bag§r mixed with an §7awkward potion§r awakens the thirst for hemoglobin.");
        registerPotionLines(t, "xp_boost",
                "\nEvery lesson is carved in stone.",
                "Drastically increases the amount of experience you harvest from any source.",
                "The wisdom contained in §7Lapis Lazuli§r is released when plunged into an §7awkward potion§r.");
        registerPotionLines(t, "xp_reduction",
                "\nTwice the work for the same experience.",
                "Drastically decreases the amount of experience you harvest from any source.",
                "The bitterness of a §7fermented spider eye§r alters the properties of an §7XP boost potion§r.");
        registerPotionLines(t, "xp_life",
                "\nYour knowledge flows in your veins.",
                "Your life is replaced by your experience bar.\n\nKnowledge becomes your flesh!",
                "The crossing of a §7glistering melon slice§r and an §7XP boost potion§r converts mind into flesh.");
        registerPotionLines(t, "zeus",
                "\nLightning always strikes twice.",
                "Your attacks invoke the wrath of §3Zeus§r upon the target, you can also invoke it manually.",
                "Combine mystical §7Materia Prima§r with a §7stun potion§r to summon lightning.");
        registerPotionLines(t, "cold",
                "\n§3Brrrr§r... Bring a coat.",
                "You cough randomly, transmitting your disease to other entities.",
                "A mundane §7snowball§r melted in an §7awkward potion§r will cool anyone's ardor.");
        registerPotionLines(t, "clumsiness",
                "\n§7Oops§r, I dropped it again.",
                "Forces the target to randomly drop the items they are holding in their hands.",
                "Corrupt an §7alcohol potion§r with a §7fermented spider eye§r to obtain this chaotic mixture.");
        registerPotionLines(t, "asthma",
                "\nThe air feels so §8thick§r...",
                "Reduces your oxygen bar if you run, even in the open air. Inflicting suffocation damage if you run out of air.",
                "A §7zombie lung§r macerated in an §7awkward potion§r will clog your airways.");
        registerPotionLines(t, "paranoia",
                "\nDid you hear that noise?",
                "Makes the target hear terrifying monster sounds that do not exist.",
                "A §7Creeper head§r infused in an §7awkward potion§r awakens your darkest fears.");
        registerPotionLines(t, "medusa",
                "\nDo not look her in the eyes!\n\nShe has looks that §2kill§r...",
                "Petrifies, if you wish, any entity that meets your gaze.",
                "Catalyze a strong §7petrification potion§r with §7Materia Prima§r to transmit the curse.");
        registerPotionLines(t, "midas",
                "\nAll that §6glitters§r... is expensive.",
                "Transforms armors and weapons into their golden versions...",
                "Amplifying an §7alchemist potion§r with §7Materia Prima§r makes transmutation uncontrollable.");
        registerPotionLines(t, "active_teleport",
                "\nA §5blink of an eye§r and you're there.",
                "Instantly teleports you, whenever you want, to the exact spot you are looking at (within range).",
                "The volatile sulfur of a §7sulfur ball§r stabilizes the chaotic effects of a §7teleportation potion§r.");
        registerPotionLines(t, "hydrophobia",
                "\n§8Water§r burns like acid.",
                "Inflicts heavy continuous damage if the target comes into contact with water or rain.",
                "Corrupting a §7water breathing potion§r with a §7fermented spider eye§r will make every drop of water deadly.");
        registerPotionLines(t, "zombie_contagion",
                "\nYou have been bitten...\n\nThere is no escape.",
                "You are infected by the zombie contagion, you will soon turn into a zombie.",
                "The corruption of §7rotten flesh§r left to rot in an §7awkward potion§r.");
        registerPotionLines(t, "magnetism",
                "\nEverything comes to those who know how to attract.",
                "Sucks up all dropped items located in a wide radius around you.",
                "The attraction of a heavy §7iron ingot§r plunged into an §7awkward potion§r magnetizes your body.");
        registerPotionLines(t, "reality_check",
                "\nWhy is everything so §5heavy§d??",
                "Instantly dispels all illusions. Your inventory is full, so you are heavy.",
                "The crushing presence of an §7anvil§r infused in an §7awkward potion§r brings you back to reality.");

        // Minecraft Base Potions
        registerPotionLines(t, "fire_resistance",
                "\nA §6lava bath§r never hurt anyone.",
                "Completely immunizes you against fire, lava, and burning damage.\n\nIdeal for a trip to the Nether.",
                "Melt a §7magma block§r in an §7awkward potion§r to ignore the heat of this world.");
        registerPotionLines(t, "regeneration",
                "\nYour wounds close up before your eyes.",
                "Progressively restores your health points over time without needing to eat.",
                "The essence of a §7golden apple§r infused in an §7awkward potion§r stimulates your natural healing.");
        registerPotionLines(t, "strength",
                "\nYou feel capable of moving mountains.",
                "Considerably increases the damage dealt by your melee attacks.",
                "The explosive energy of a §7firework star§r infused in an §7awkward potion§r will multiply your muscular strength.");
        registerPotionLines(t, "swiftness",
                "\nThe wind blows in your ears.",
                "Increases your movement speed and slightly widens your field of vision.",
                "The energy of a simple piece of §7sugar§r dissolved in an §7awkward potion§r accelerates your heart rate.");
        registerPotionLines(t, "poison",
                "\nA bitter taste that eats away at you from the inside.",
                "Inflicts regular damage over time, but cannot kill you. The effect stops at half a heart.",
                "Macerate any §7poisonous vegetables§r in an §7awkward potion§r to extract the toxic venom.");
        registerPotionLines(t, "healing",
                "\nA pure and immediate relief.",
                "Instantly restores a portion of your health points.\n\n§4Hurts§r undead creatures.",
                "The radiant juice of a §7glistering melon slice§r infused in an §7awkward potion§r closes your wounds on the spot.");
        registerPotionLines(t, "night_vision",
                "\nThe darkness no longer holds any secrets from you.",
                "Illuminates the area around you, allowing you to see perfectly in total darkness and underwater.",
                "Bite into the power of a §7golden carrot§r distilled in an §7awkward potion§r to pierce the darkness.");
        registerPotionLines(t, "invisibility",
                "\nWhere did you go?",
                "Makes your body completely invisible to the eyes of others.\n\n§4Be careful, your armors and held items remain visible.§r",
                "Corrupt a §7night vision potion§r with a §7fermented spider eye§r to trick light itself.");
        registerPotionLines(t, "water_breathing",
                "\nYour lungs adapt to the depths.\n\n§3Ephemeral gills.§r",
                "Prevents your oxygen bar from depleting underwater, allowing you to stay submerged indefinitely.",
                "Inflate an §7awkward potion§r with a §7pufferfish§r to breathe like an inhabitant of the oceans.");
        registerPotionLines(t, "leaping",
                "\nGravity has less grip on you.",
                "Significantly increases your jump height and reduces the fall damage you take.",
                "The luck of a §7rabbit foot§r infused in an §7awkward potion§r will put springs under your feet.");
        registerPotionLines(t, "turtle_master",
                "\nSlow, but completely indestructible.",
                "Drastically reduces your movement speed, but grants you unwavering resistance to physical damage.",
                "Boil a §7turtle shell§r in an §7awkward potion§r to adopt the wisdom and sturdiness of the reptile.");
        registerPotionLines(t, "slow_falling",
                "\nLighter than a snowflake.",
                "Significantly slows your falling speed, making you completely immune to landing damage.",
                "The remains of a nocturnal hunter, a §7Phantom membrane§r, diluted in an §7awkward potion§r will lighten your body.");
        registerPotionLines(t, "weakness",
                "\nYour arms abandon you.",
                "Considerably reduces the damage you inflict in melee.\n\nUseful for curing zombie villagers.",
                "The mold of a §7fermented spider eye§r diluted in a simple vial of §7water§r weakens any organism.");
        registerPotionLines(t, "slowness",
                "\nLike walking through molasses.",
                "Greatly decreases your movement speed and slightly reduces your field of vision.",
                "The corruption of a §7swiftness potion§r by a §7fermented spider eye§r will deprive you of all your momentum.");
        registerPotionLines(t, "harming",
                "\nA sudden and blazing pain.",
                "Inflicts instant magical damage that ignores most armors.\n\n§2Heals undead creatures.§r",
                "Pervert a §7healing potion§r using a §7fermented spider eye§r to turn the cure into a deadly poison.");

        // Elixirs
        registerPotionLines(t, "perm_health",
                "\nYour body changes, forever.",
                "Permanently increases your maximum number of hearts.",
                "This powerful elixir can only be §cobtained through alchemical rituals§r that are long, obscure, and costly.");
        registerPotionLines(t, "perm_speed",
                "\nFast, in every moment of your life.",
                "Permanently increases your base movement speed.\n\nYour legs will never get tired again.",
                "The absolute mastery of movement is a magic §3obtained only through complex alchemical rituals§r.");
        registerPotionLines(t, "perm_strength",
                "\nThe gods have blessed you.",
                "Permanently increases the power of all your melee strikes.\n\nNothing will be able to resist you anymore.",
                "Such raw power must be §cobtained through alchemical rituals§r of absolute precision and danger.");

        // --- AJOUTS CITRINITAS (CitrinitasBookItem) ---
        t.add("book.potions-n-rituals.citrinitas.chapter.rituals", "Rituals");

        t.add("book.potions-n-rituals.page.citrinitas.skeleton_horse", "Skeleton Horse");
        t.add("book.potions-n-rituals.page.citrinitas.skeleton_horse.desc", "Summons a skeleton horse.");
        t.add("book.potions-n-rituals.page.citrinitas.ancient_debris", "Ancient Debris");
        t.add("book.potions-n-rituals.page.citrinitas.ancient_debris.desc", "Conjures ancient debris, the key to Netherite equipment.");
        t.add("book.potions-n-rituals.page.citrinitas.random_effect", "Random Effect");
        t.add("book.potions-n-rituals.page.citrinitas.random_effect.desc", "Applies a random effect to yourself.");
        t.add("book.potions-n-rituals.page.citrinitas.summon_thunderstorm", "Thunderstorm");
        t.add("book.potions-n-rituals.page.citrinitas.summon_thunderstorm.desc", "Triggers a violent thunderstorm.");
        t.add("book.potions-n-rituals.page.citrinitas.echo_shard", "Echo Shard");
        t.add("book.potions-n-rituals.page.citrinitas.echo_shard.desc", "Produces an echo shard, a rare resource from the deep.");
        t.add("book.potions-n-rituals.page.citrinitas.summon_dawn", "Dawn");
        t.add("book.potions-n-rituals.page.citrinitas.summon_dawn.desc", "Brings forth the first light of dawn.");
        t.add("book.potions-n-rituals.page.citrinitas.nether_gate", "Nether Portal");
        t.add("book.potions-n-rituals.page.citrinitas.nether_gate.desc", "Opens a portal to the Nether and teleports you there.");
        t.add("book.potions-n-rituals.page.citrinitas.seal_nether", "Seal the Nether");
        t.add("book.potions-n-rituals.page.citrinitas.seal_nether.desc", "Seals the Nether to prevent any access.");

        // --- AJOUTS ALBEDO (AlbedoBookItem) ---
        t.add("book.potions-n-rituals.page.alchemy_guide_albedo.chapter.path", "Path to Citrinitas");
        t.add("book.potions-n-rituals.page.alchemy_guide_albedo.chapter.rituals", "Ritual");
        t.add("book.potions-n-rituals.page.alchemy_guide_albedo.chapter.talisman", "Talisman");
        t.add("book.potions-n-rituals.page.alchemy_guide_albedo.chapter.artifacts", "Artifacts");

        // Textes des pages
        t.add("book.potions-n-rituals.page.nether_gate.text", "You will need to go to the Nether with a ritual. To activate it, you must sacrifice an entity inside the ritual.");
        t.add("book.potions-n-rituals.page.ritual_pattern", "Ritual Pattern");
        t.add("book.potions-n-rituals.page.talisman_recipe", "Talisman Recipe");

        // --- AJOUTS ARTEFACTS (Chapter: Artifacts) ---
        t.add("book.potions-n-rituals.page.alchemy_guide_albedo.artifact_page", "Artifacts");
        t.add("book.potions-n-rituals.page.artifact.alchemical_bag.desc", "Allows you to store in a separate dimension.");
        t.add("book.potions-n-rituals.page.artifact.alchemical_bag.craft", "You give power to a bundle with a talisman.");
        t.add("book.potions-n-rituals.page.artifact.spirit_mirror.desc", "Allows you to navigate to a different dimension.");
        t.add("book.potions-n-rituals.page.artifact.nether_seal_breaker.desc", "Break the nether seal and create a portal.");
        t.add("book.potions-n-rituals.page.artifact.nether_seal_breaker.craft", "You need to go to every part of the nether to get the blocks.");
        t.add("book.potions-n-rituals.page.artifact.decoy.desc", "Create a decoy that distract hostile mobs.");
        t.add("book.potions-n-rituals.page.artifact.alchemical_stone.desc", "The stone can store some potion effects to apply them on the ground.");
        t.add("book.potions-n-rituals.page.artifact.gauntlet.desc", "Stores multiple alchemical stones and allows you to cycle through them.");
        t.add("book.potions-n-rituals.page.artifact.gauntlet.craft", "Combine alchemical stones with the Gauntlet of the Absolute to add their effects.");
    }

    private static void registerPotionLines(TranslationBuilder t, String id, String resume, String explanation, String brew) {
        t.add("book.potions-n-rituals.page." + id + ".resume", resume);
        t.add("book.potions-n-rituals.page." + id + ".explanation", explanation);
        t.add("book.potions-n-rituals.page." + id + ".brew", brew);
    }
}