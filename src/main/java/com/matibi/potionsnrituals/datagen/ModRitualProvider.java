package com.matibi.potionsnrituals.datagen;

import com.matibi.potionsnrituals.item.ModItems;
import com.matibi.potionsnrituals.ritual.datagen.definition.RitualCatalyst;
import com.matibi.potionsnrituals.ritual.datagen.RitualRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

public class ModRitualProvider extends RitualRecipeProvider {

    public ModRitualProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    protected void configure() {

        // --- 1. Invocation de Cheval Squelette ---
        addRitual(EntityTypes.SKELETON_HORSE, 1)
                .pattern("I")
                .define('I', Items.BONE)
                .catalyst(RitualCatalyst.IGNITE)
                .save();

        // --- 2. Création de Materia Prima ---
        addRitual(ModItems.MATERIA_PRIMA, 3)
                .duration(500)
                .pattern("  I  ")
                .pattern("I S I")
                .pattern("  I  ")
                .define('S', Blocks.CRYING_OBSIDIAN)
                .define('I', Items.GHAST_TEAR)
                .catalyst(RitualCatalyst.KILL)
                .save();

        // --- 3. Bloc de Diamant ---
        addRitual(Blocks.DIAMOND_BLOCK, 1)
                .pattern(" S ")
                .pattern("SXS")
                .pattern(" S ")
                .define('X', Items.NETHER_STAR)
                .define('S', Items.DIAMOND)
                .catalyst(RitualCatalyst.IGNITE)
                .save();

        /*// --- 4. Rituel Spécial (Custom ID) ---
        addRitual("any_id_rituals")
                .pattern(" S ")
                .pattern("S S")
                .pattern(" S ")
                .define('S', Items.DIAMOND)
                .catalyst(RitualCatalyst.KILL)
                .save();*/
    }
}