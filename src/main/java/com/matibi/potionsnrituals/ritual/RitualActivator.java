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
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.Map;
import java.util.Optional;

public class RitualActivator {

    public static Optional<Map.Entry<Identifier, Ritual>> checkRitualMatch(Level level, BlockPos clickedPos) {
        for (Map.Entry<Identifier, Ritual> entry : RitualManager.getAllRituals().entrySet()) {
            Ritual ritual = entry.getValue();

            if (ritual.catalyst().isPresent() && ritual.catalyst().get() == RitualCatalyst.IGNITE) {

                Optional<BlockPos> validCenter = findValidCenter(level, clickedPos, ritual);
                if (validCenter.isPresent())
                    return Optional.of(entry);
            }
        }
        return Optional.empty();
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
                BlockPos checkPos = centerPos.offset(x - xOffset, 0, z - zOffset);

                if (symbol == ' ') continue;

                RitualIngredient expectedIngredient = ritual.keys().get(String.valueOf(symbol));
                if (expectedIngredient == null) return false;

                Identifier expectedId = Identifier.parse(expectedIngredient.id());
                Item expectedItem = BuiltInRegistries.ITEM.get(expectedId)
                        .map(Holder.Reference::value)
                        .orElse(null);

                if (expectedItem == null) return false;

                BlockEntity blockEntity = level.getBlockEntity(checkPos);
                if (!(blockEntity instanceof PedestalBlockEntity pedestal))
                    return false;

                if (pedestal.getItem().getItem() != expectedItem)
                    return false;
            }
        }
        return true;
    }
}