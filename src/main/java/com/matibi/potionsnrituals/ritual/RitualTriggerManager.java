package com.matibi.potionsnrituals.ritual;

import com.matibi.potionsnrituals.block.custom.pedestal.PedestalBlockEntity;
import com.matibi.potionsnrituals.entity.ModEntities;
import com.matibi.potionsnrituals.entity.RitualControllerEntity;
import com.matibi.potionsnrituals.ritual.datagen.Ritual;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Map;
import java.util.Optional;

public class RitualTriggerManager {

    public static void tryTriggerRitual(Level level, BlockPos pos, Ritual.Catalysts catalyst) {
        if (level.isClientSide()) return;

        checkRitualMatch(level, pos, catalyst).ifPresent(match -> {
            RitualControllerEntity controller = new RitualControllerEntity(ModEntities.RITUAL_CONTROLLER, level);
            controller.setPos(match.center().getX() + 0.5, match.center().getY() + 0.5, match.center().getZ() + 0.5);
            controller.startRitual(match.id(), 200, match.center());
            level.addFreshEntity(controller);

            level.playSound(null, match.center(), SoundEvents.CONDUIT_ACTIVATE, SoundSource.BLOCKS, 1.0F, 1.0F);
        });
    }

    public static void register() {
        ServerLivingEntityEvents.AFTER_DEATH.register((entity, _) -> {
            if (entity.level() instanceof ServerLevel level)
                RitualTriggerManager.tryTriggerRitual(level, entity.blockPosition(), Ritual.Catalysts.KILL);
        });

        ServerEntityEvents.ENTITY_LOAD.register((entity, level) -> {
            if (entity instanceof LightningBolt lightning && level instanceof ServerLevel)
                RitualTriggerManager.tryTriggerRitual(level, lightning.blockPosition(), Ritual.Catalysts.THUNDERSTRIKE);
        });
    }

    public record RitualType(Identifier id, Ritual ritual, BlockPos center) {}

    public static Optional<RitualType> checkRitualMatch(Level level, BlockPos clickedPos, Ritual.Catalysts catalyst) {
        for (Map.Entry<Identifier, Ritual> entry : RitualManager.getAllRituals().entrySet()) {
            Ritual ritual = entry.getValue();

            if (ritual.catalyst().isPresent()
                    && ritual.catalyst().get() ==  catalyst) {
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
                Ritual.Ingredient expectedIngredient = ritual.keys().get(String.valueOf(symbol));
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