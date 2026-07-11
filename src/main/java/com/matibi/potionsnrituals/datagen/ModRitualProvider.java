package com.matibi.potionsnrituals.datagen;

import com.matibi.potionsnrituals.block.ModBlocks;
import com.matibi.potionsnrituals.item.ModItems;
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

        addRitual("random_effect")
                .pattern("P")
                .define('P', Items.POTION)
                .catalyst(Ritual.Catalysts.IGNITE)
                .save();

        addRitual("summon_thunderstorm")
                .pattern(" W ")
                .pattern("WSW")
                .pattern(" W ")
                .define('W', Items.WATER_BUCKET)
                .define('S', ModBlocks.BLOOD_TRAIL)
                .catalyst(Ritual.Catalysts.IGNITE)
                .height(120, 321)
                .save();

        addRitual(Items.ECHO_SHARD, 1)
                .duration(20 * 20)
                .pattern(" A ")
                .pattern("ASA")
                .pattern(" A ")
                .define('A', Items.AMETHYST_SHARD)
                .define('S', ModBlocks.BLOOD_TRAIL)
                .catalyst(Ritual.Catalysts.KILL)
                .time(13000, 23000)
                .moonphase(RitualBuilder.MOONPHASE.NO_MOON)
                .save();

        addRitual("summon_dawn")
                .pattern(" G ")
                .pattern("GSG")
                .pattern(" G ")
                .define('G', Items.GLOWSTONE_DUST)
                .define('S', Blocks.SUNFLOWER)
                .catalyst(Ritual.Catalysts.IGNITE)
                .save();

        addRitual("nether_gate_final")
                .during("nether_gate_action")
                .pattern("  S  ")
                .pattern(" SSS ")
                .pattern("SS SS")
                .pattern(" SSS ")
                .pattern("  S  ")
                .define('S', ModBlocks.BLOOD_TRAIL)
                .catalyst(Ritual.Catalysts.KILL)
                .dimension(RitualBuilder.DIMENSION.OVERWORLD)
                .save();

        addRitual("seal_nether")
                .pattern("  S  ")
                .pattern(" SSS ")
                .pattern("SSNSS")
                .pattern(" SSS ")
                .pattern("  S  ")
                .define('S', ModBlocks.BLOOD_TRAIL)
                .define('N', ModItems.NETHER_SEAL_BREAKER)
                .catalyst(Ritual.Catalysts.IGNITE)
                .save();
    }
}