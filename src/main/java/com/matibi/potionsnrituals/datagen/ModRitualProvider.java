package com.matibi.potionsnrituals.datagen;

import com.matibi.potionsnrituals.block.ModBlocks;
import com.matibi.potionsnrituals.ritual.datagen.Ritual;
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

        // --- Spawn cheval squelettique ---
        addRitual(EntityTypes.SKELETON_HORSE, 1)
                .duration(20 * 60)
                .pattern("  I  ")
                .pattern("  S  ")
                .pattern("ISSSI")
                .pattern("  S  ")
                .pattern("  I  ")
                .define('S', ModBlocks.BLOOD_TRAIL)
                .define('I', Items.BONE)
                .catalyst(Ritual.Catalysts.KILL)
                .save();

        // --- Creation Ancient debris ---
        addRitual(Blocks.ANCIENT_DEBRIS, 1)
                .duration(20 * 60 * 5)
                .pattern("D   D")
                .pattern(" SSS ")
                .pattern(" S S ")
                .pattern(" SSS ")
                .pattern("D   D")
                .define('D', Items.DIAMOND_BLOCK)
                .define('S', ModBlocks.BLOOD_TRAIL)
                .catalyst(Ritual.Catalysts.IGNITE)
                .save();

        // --- Random effet ---
        addRitual("random_effect")
                .pattern("P")
                .define('P', Items.POTION)
                .catalyst(Ritual.Catalysts.IGNITE)
                .save();
    }
}