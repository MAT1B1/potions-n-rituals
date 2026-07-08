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
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.MoonPhase;
import net.minecraft.world.level.biome.Biome;
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
                if (validCenter.isPresent() && checkConditions(level, validCenter.get(), ritual))
                    return Optional.of(new RitualType(entry.getKey(), ritual, validCenter.get()));
            }
        }
        return Optional.empty();
    }

    public static boolean isStillValid(Level level, BlockPos centerPos, Ritual ritual) {
        return matchesPattern(level, centerPos, ritual) && checkConditions(level, centerPos, ritual);
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

    private static boolean checkConditions(Level level, BlockPos pos, Ritual ritual) {
        if (!(level instanceof ServerLevel serverLevel)) return false;

        if (ritual.conditions() == null || ritual.conditions().isEmpty()) return true;

        Player player = serverLevel.getNearestPlayer(pos.getX(), pos.getY(), pos.getZ(), 10.0, false);

        for (var condition : ritual.conditions()) {
            var params = condition.params();

            switch (condition.type()) {
                case BIOME -> {
                    String targetBiomeId = params.get("id").getAsString();
                    Optional<ResourceKey<Biome>> optId = level.getBiome(pos).unwrapKey();
                    boolean match = optId.map(biomeResourceKey ->
                            biomeResourceKey.identifier().toString().equals(targetBiomeId)).orElse(false);
                    if (!match) return false;
                }
                case XP -> {
                    if (player == null) return false;
                    float min = params.get("min").getAsFloat();
                    float max = params.get("max").getAsFloat();
                    if (player.experienceLevel < min || player.experienceLevel > max) return false;
                }
                case TIME -> {
                    long time = level.getOverworldClockTime() % 24000;
                    float min = params.get("min").getAsFloat();
                    float max = params.get("max").getAsFloat();
                    if (time < min || time > max) return false;
                }
                case WEATHER -> {
                    String target = params.get("type").getAsString();
                    boolean match = switch (target) {
                        case "rain" -> level.isRaining();
                        case "thunder" -> level.isThundering();
                        default -> !level.isRaining() && !level.isThundering();
                    };
                    if (!match) return false;
                }
                case MOONPHASE -> {
                    int target = params.get("phase").getAsInt();
                    MoonPhase currentPhase = serverLevel.environmentAttributes()
                            .getValue(EnvironmentAttributes.MOON_PHASE, pos);
                    if (currentPhase.index() != target) return false;
                }
                case HEALTH -> {
                    if (player == null) return false;
                    float min = params.get("min").getAsFloat();
                    float max = params.get("max").getAsFloat();
                    if (player.getHealth() < min || player.getHealth() > max) return false;
                }
                case DIMENSION -> {
                    String targetDim = params.get("id").getAsString();
                    if (!level.dimension().identifier().toString().equals(targetDim)) return false;
                }
                case BRIGHTNESS -> {
                    float val = level.getMaxLocalRawBrightness(pos);
                    float min = params.get("min").getAsFloat();
                    float max = params.get("max").getAsFloat();
                    if (val < min || val > max) return false;
                }
                case HEIGHT -> {
                    float min = params.get("min").getAsFloat();
                    float max = params.get("max").getAsFloat();
                    if (pos.getY() < min || pos.getY() > max) return false;
                }
                case EFFECT -> {
                    if (player == null) return false;
                    String id = params.get("id").getAsString();
                    int minAmp = params.get("amp").getAsInt();

                    boolean hasEffect = player.getActiveEffects().stream().anyMatch(e -> {
                            var key = BuiltInRegistries.MOB_EFFECT.getKey(e.getEffect().value());
                            if (key == null) return false;
                            return key.toString().equals(id) && e.getAmplifier() >= minAmp;
                        });
                    if (!hasEffect) return false;
                }
                case OFFHAND -> {
                    if (player == null) return false;
                    String targetId = params.get("id").getAsString();
                    String currentId = BuiltInRegistries.ITEM.getKey(player.getOffhandItem().getItem()).toString();
                    if (!currentId.equals(targetId)) return false;
                }
            }
        }
        return true;
    }
}