package com.matibi.potionsnrituals.ritual;

import com.matibi.potionsnrituals.block.custom.pedestal.PedestalBlockEntity;
import com.matibi.potionsnrituals.ritual.datagen.definition.Ritual;
import com.matibi.potionsnrituals.ritual.datagen.definition.RitualCatalyst;
import com.matibi.potionsnrituals.ritual.datagen.definition.RitualIngredient;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Map;
import java.util.Optional;

public class RitualActivator {

    public record RitualType(Identifier id, Ritual ritual, BlockPos center) {}

    public static Optional<RitualType> checkRitualMatch(Level level, BlockPos clickedPos) {
        for (Map.Entry<Identifier, Ritual> entry : RitualManager.getAllRituals().entrySet()) {
            Ritual ritual = entry.getValue();

            if (ritual.catalyst().isPresent() && ritual.catalyst().get() == RitualCatalyst.IGNITE) {
                Optional<BlockPos> validCenter = findValidCenter(level, clickedPos, ritual);
                if (validCenter.isPresent())
                    return Optional.of(new RitualType(entry.getKey(), ritual, validCenter.get()));
            }
        }
        return Optional.empty();
    }

    public static boolean isPatternStillValid(Level level, BlockPos centerPos, Ritual ritual) {
        return matchesPattern(level, centerPos, ritual);
    }

    private static Optional<BlockPos> findValidCenter(Level level, BlockPos clickedPos, Ritual ritual) {
        var pattern = ritual.pattern();
        int zSize = pattern.size();
        int xSize = pattern.getFirst().length();
        int xOffset = xSize / 2;
        int zOffset = zSize / 2;

        for (int z = 0; z < zSize; z++) {
            String row = pattern.get(z);
            for (int x = 0; x < xSize; x++) {
                if (row.charAt(x) == ' ') continue;

                BlockPos potentialCenter = clickedPos.offset(-(x - xOffset), 0, -(z - zOffset));

                if (matchesPattern(level, potentialCenter, ritual))
                    return Optional.of(potentialCenter);
            }
        }
        return Optional.empty();
    }

    private static boolean matchesPattern(Level level, BlockPos centerPos, Ritual ritual) {
        var pattern = ritual.pattern();
        int zSize = pattern.size();
        int xSize = pattern.getFirst().length();
        int xOffset = xSize / 2;
        int zOffset = zSize / 2;

        for (int z = 0; z < zSize; z++) {
            String row = pattern.get(z);
            for (int x = 0; x < xSize; x++) {
                char symbol = row.charAt(x);
                if (symbol == ' ') continue;

                BlockPos checkPos = centerPos.offset(x - xOffset, 0, z - zOffset);
                RitualIngredient expectedIngredient = ritual.keys().get(String.valueOf(symbol));
                if (expectedIngredient == null) return false;

                Identifier expectedId = Identifier.parse(expectedIngredient.id());
                boolean matchFound = false;

                Optional<Holder.Reference<Block>> optBlock = BuiltInRegistries.BLOCK.get(expectedId);
                if (optBlock.isPresent()) {
                    Block expectedBlock = optBlock.get().value();
                    BlockState state = level.getBlockState(checkPos);
                    if (state.is(expectedBlock))
                        matchFound = true;
                }

                // 2. Si ce n'est pas un bloc, est-ce un ITEM sur un piédestal ?
                if (!matchFound) {
                    Optional<Holder.Reference<Item>> optItem = BuiltInRegistries.ITEM.get(expectedId);
                    if (optItem.isPresent()) {
                        Item expectedItem = optItem.get().value();
                        BlockEntity blockEntity = level.getBlockEntity(checkPos);

                        if (blockEntity instanceof PedestalBlockEntity pedestal)
                            if (pedestal.getItem().getItem() == expectedItem)
                                matchFound = true;
                    }
                }

                if (!matchFound)
                    return false;
            }
        }
        return true;
    }
}