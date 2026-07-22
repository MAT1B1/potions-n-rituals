# Potions & Rituals — Agent Guide

## Project Overview

**Potions & Rituals** is a Fabric 26.2 Minecraft mod centered on alchemy, structured around the four classical alchemical stages: Nigredo, Albedo, Citrinitas, and Rubedo. It adds ~40 custom potion effects, a full brewing overhaul, rituals, talismans, custom dimensions, and a configurable alchemical stone system.

**Mod ID**: `potions-n-rituals`  
**Loader**: Fabric (fabric-loom)  
**Minecraft**: 26.2  
**Language**: Java 25  
**Dependencies**: Fabric API, YetAnotherConfigLib (v3), ModMenu

## Quickstart for Adding Content

### Adding an Effect
1. Create `<Name>Effect.java` in `effect/custom/` extending `StatusEffect`
2. Create a Mixin in `mixin/effect/` if custom behavior is needed
3. Register the effect in `ModEffects.java`
4. If a potion is needed:
   - Add potion variants in `ModPotions.java` (base, long, strong)
   - Add translations in `ModFrenchLanguageProvider.java` and `ModUsLanguageProvider.java`
   - If it should appear in the Nigredo book, add it to the book content provider
5. Add config options in `ModConfig.java` + `ModConfigScreen.java` + `ModConfigLanguageHelper.java`

### Adding an Item
1. Create `<Name>Item.java` in `item/custom/` if special behavior is needed
2. Register in `ModItems.java`
3. Add translations in language providers
4. If it has configurable constants: add to `ModConfig.java`, `ModConfigScreen.java`, `ModConfigLanguageHelper.java`
5. If it's a talisman: add to book content, language helper, and `ModRecipeProvider.java`

### Adding a Ritual
Create a JSON file in `data/potions-n-rituals/rituals/` following the ritual schema (pattern grid, catalysts, conditions, results).

## Package Structure

```
com.matibi.potionsnrituals/
├── PotionsNRituals.java                — Main entry (ModInitializer)
├── PotionsNRitualsClient.java          — Client entry (ClientModInitializer)
├── PotionsNRitualsDataGenerator.java   — Data generation entry
├── block/                              — Custom blocks + block entities (pedestal, brewing cauldron, bag walls/exit, blood trail)
├── book/                               — BookStructure, BookPage (TextPage|ImagePage|RecipePage|EmptyPage), book content builders
├── command/                            — Custom commands
├── config/                             — ModConfig (YACL+Gson), ModConfigScreen (YACL UI), ModMenuIntegration
├── datacomponent/                      — Custom data components (BloodType, ImbuedEffect, PersonalBookmark, ReturnLocation, TalismanCharge)
├── datagen/                            — Data generation providers
│   ├── language/                       — EN & FR translations + book/config translation helpers
│   ├── ModAdvancementProvider.java     — 4-stage advancement tree
│   ├── ModBlockLootTableProvider.java
│   ├── ModItemTagProvider.java
│   ├── ModLootTableProvider.java
│   ├── ModModelProvider.java
│   ├── ModRecipeProvider.java
│   ├── ModRitualProvider.java
│   └── ModWorldgenProvider.java
├── effect/                             — All effect classes
│   ├── ActiveEffect.java               — Interface for keybind-triggered effects (Zeus, Medusa, Active TP, Love)
│   ├── ModEffects.java                 — Effect registry (~59 effects)
│   ├── TerrainEffect.java              — Interface for block-applicable effects (Alchemical Stone)
│   ├── helper/                         — ActiveEffectHandler, DisorientInputHandler
│   └── custom/                         — Individual effect implementations organized by type
├── entity/                             — RitualControllerEntity (invisible), PortalBuilderEntity (animated)
├── group/                              — Custom "Alchemy" creative tab
├── item/                               — Item registry + custom items (alchemical stone, books, syringe, talismans)
├── keybind/                            — Keybind registration
├── mixin/                              — Mixins for brewing system, individual effects, ghost walk, xp life, etc.
├── network/                            — Custom network payloads (OreSense, UpdateBookmarks)
├── potion/                             — ModPotions (registry + brewing recipes), PotionIconHelper
├── recipe/                             — Custom recipe types (ImbuedEffect, Combination, AlchemicalStone, FoodWithEffect)
├── renderer/                           — OreSenseOverlayRenderer, CooldownOverlayRenderer
├── ritual/                             — RitualManager (JSON reload listener), RitualActions (hardcoded effects), RitualTriggerManager
├── screen/                             — CustomBookScreen (book GUI)
├── util/                               — Utilities (ActiveEffectUtils, AttributeUtils, BookUtils, CombinationUtils, etc.)
└── world/                              — Dimensions, biomes, world attachments
```

## Key Patterns

### Registry Pattern
All registries follow the same pattern:
```java
public static final RegistryKey<EntityType<?>> RITUAL_CONTROLLER_KEY = RegistryKey.of(Registry.ENTITY_TYPE, id("ritual_controller"));
public static final EntityType<RitualControllerEntity> RITUAL_CONTROLLER = Registry.register(
    Registries.ENTITY_TYPE, id("ritual_controller"),
    EntityType.Builder.<RitualControllerEntity>of(RitualControllerEntity::new, SpawnGroup.MISC)
        .dimensions(1f, 1f).build();
);
```

### Mixin Strategy
- `mixin/effect/` — one mixin per effect for specific game event hooks
- `mixin/brewingNpotion/` — 7 mixins overriding the vanilla brewing system
- `mixin/effect/ghost_walker/` — 3 mixins for ghost phase-through-blocks
- `mixin/effect/XpLife/` — 2 mixins for XP-as-health display

### Active Effects
Effects implementing `ActiveEffect` interface use a keybind (default: `R`) to trigger. Managed by `ActiveEffectHandler` with cooldown arrays.

### Terrain Effects
Effects implementing `TerrainEffect` interface can be applied to blocks via the Alchemical Stone. Checked via `instanceof`.

### Ritual System
Data-driven via `SimpleJsonResourceReloadListener`. Ritual JSON schema:
- `pattern`: string array grid
- `keys`: character → block/item mapping
- `catalyst`: `KILL` (entity type) or `THUNDERSTRIKE`
- `conditions`: biome, XP, time, weather, moon, health, dimension, brightness, height, effects, offhand
- `during`: particle/effect tick actions
- `result`: items, blocks, entities, or custom actions (see `RitualActions.java`)

### Book System
Books use `BookStructure` → `BookPage` sealed interface hierarchy (TextPage, ImagePage, RecipePage, EmptyPage). Render via `CustomBookScreen`. All content translatable through `ModBookLanguageHelper`.

### Config
`ConfigClassHandler` with `@SerialEntry` annotations. File: `config/potionsnrituals.json`. YACL screen organized in 3 tabs (Potions & Items, Mob Effects, Active Effects). Runtime blacklists (non-serialized).

## Data Components
- `BloodType`: UNKNOWN / HUMAN / MONSTER — tracked per entity
- `ImbuedEffect`: effect + remaining hit count — for weapon imbuing
- `TalismanCharge`: soul charge counter
- `PersonalBookmark`: dimension + position — for teleportation
- `ReturnLocation`: saved position — for teleport potion
- `BagID`: integer — per-pocket-dimension instance

## Translation Files
- `ModFrenchLanguageProvider.java` — all in-game translations (FR)
- `ModUsLanguageProvider.java` — all in-game translations (EN)
- `ModBookLanguageHelper.java` — book page content translations
- `ModConfigLanguageHelper.java` — config screen UI translations

## REGLE.txt Quick Reference
See `src/main/java/com/matibi/potionsnrituals/REGLE.txt` for the project's rule checklist when adding effects, items, or config constants.
