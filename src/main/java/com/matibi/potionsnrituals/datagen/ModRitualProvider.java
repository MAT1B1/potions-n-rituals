package com.matibi.potionsnrituals.datagen;

import com.matibi.potionsnrituals.block.ModBlocks;
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

        // --- 2. Création de Materia Prima ---
        addRitual(EntityTypes.SKELETON_HORSE, 1)
                .duration(500)
                .pattern("  I  ")
                .pattern("  S  ")
                .pattern("ISSSI")
                .pattern("  S  ")
                .pattern("  I  ")
                .define('S', ModBlocks.BLOOD_TRAIL)
                .define('I', Items.BONE)
                .catalyst(RitualCatalyst.KILL)
                .save();

        // --- 3. Bloc de Diamant ---
        addRitual(Blocks.ANCIENT_DEBRIS, 1)
                .pattern("SSS")
                .pattern("SDS")
                .pattern("SSS")
                .define('D', Items.DIAMOND_BLOCK)
                .define('S', ModBlocks.BLOOD_TRAIL)
                .catalyst(RitualCatalyst.IGNITE)
                .save();

        // --- 4. Rituel Spécial (Custom ID) ---
        addRitual("random_effect")
                .pattern("P")
                .define('P', Items.POTION)
                .catalyst(RitualCatalyst.IGNITE)
                .save();
    }
}