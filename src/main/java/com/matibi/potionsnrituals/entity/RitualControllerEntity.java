package com.matibi.potionsnrituals.entity;

import com.matibi.potionsnrituals.block.custom.pedestal.PedestalBlock;
import com.matibi.potionsnrituals.block.custom.pedestal.PedestalBlockEntity;
import com.matibi.potionsnrituals.ritual.RitualManager;
import com.matibi.potionsnrituals.ritual.datagen.definition.Ritual;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.NonNull;

import java.util.Optional;

public class RitualControllerEntity extends Entity {

    private static final EntityDataAccessor<Integer> MAX_DURATION = SynchedEntityData.defineId(RitualControllerEntity.class, EntityDataSerializers.INT);

    private Identifier recipeId;
    private int currentTicks = 0;
    private BlockPos centerPos;

    public RitualControllerEntity(EntityType<?> entityType, Level level) {
        super(entityType, level);
        this.noPhysics = true;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(MAX_DURATION, 200);
    }

    public void startRitual(Identifier recipeId, int duration, BlockPos pos) {
        this.recipeId = recipeId;
        this.entityData.set(MAX_DURATION, duration);
        this.currentTicks = 0;
        this.centerPos = pos;
        igniteAllPedestals();
    }

    @Override
    public void tick() {
        super.tick();

        int maxDuration = this.entityData.get(MAX_DURATION);

        if (this.level() instanceof ServerLevel serverLevel)
            serverLevel.sendParticles(
                    ParticleTypes.SMOKE,
                    this.getX(), this.getY() + 0.5, this.getZ(),
                    1, 0.0, 0.0, 0.0, 0.0
            );

        if (this.level().isClientSide())
            return;

        this.currentTicks++;

        if (this.currentTicks >= maxDuration)
            this.finishRitual();
    }

    @Override
    public boolean hurtServer(@NonNull ServerLevel level, @NonNull DamageSource source, float damage) {
        return false;
    }

    private void finishRitual() {
        if (this.level() instanceof ServerLevel serverLevel && this.recipeId != null) {
            Ritual ritual = RitualManager.getAllRituals().get(this.recipeId);

            if (ritual != null) {
                consumeIngredients(serverLevel, ritual);

                var result = ritual.result();
                int count = result.count().orElse(1);

                result.item().ifPresent(id -> {
                    Optional<Holder.Reference<Item>> optItem = BuiltInRegistries.ITEM.get(Identifier.parse(id));
                    if (optItem.isEmpty()) return;
                    Item item = optItem.get().value();
                    Containers.dropItemStack(serverLevel, this.getX(), this.getY(), this.getZ(), new ItemStack(item, count));
                });

                result.block().ifPresent(id -> {
                    Optional<Holder.Reference<Block>> optBlock = BuiltInRegistries.BLOCK.get(Identifier.parse(id));
                    if (optBlock.isEmpty()) return;
                    Item item = optBlock.get().value().asItem();
                    Containers.dropItemStack(serverLevel, this.getX(), this.getY(), this.getZ(), new ItemStack(item, count));
                });

                result.entity().ifPresent(id -> {
                    Optional<Holder.Reference<EntityType<?>>> optEntity = BuiltInRegistries.ENTITY_TYPE.get(Identifier.parse(id));
                    if (optEntity.isEmpty()) return;
                    EntityType<?> type = optEntity.get().value();
                    var entity = type.create(serverLevel, EntitySpawnReason.SPAWNER);
                    if (entity == null) return;
                    entity.setPos(this.getX(), this.getY(), this.getZ());
                    serverLevel.addFreshEntity(entity);
                });

                serverLevel.sendParticles(ParticleTypes.ENCHANT, this.getX(), this.getY(), this.getZ(), 1, 0, 0, 0, 0);
            }
        }
        this.discard();
    }

    private void consumeIngredients(ServerLevel level, Ritual ritual) {
        var pattern = ritual.pattern();
        int zSize = pattern.size();
        int xSize = pattern.getFirst().length();
        int xOffset = xSize / 2;
        int zOffset = zSize / 2;

        if (this.centerPos == null) return;

        for (int z = 0; z < zSize; z++) {
            String row = pattern.get(z);
            for (int x = 0; x < xSize; x++) {
                char symbol = row.charAt(x);
                if (symbol == ' ') continue;

                BlockPos checkPos = this.centerPos.offset(x - xOffset, 0, z - zOffset);

                BlockState state = level.getBlockState(checkPos);
                if (state.getBlock() instanceof PedestalBlock) {
                    level.setBlock(checkPos, state.setValue(PedestalBlock.LIT, false), 3);

                    BlockEntity be = level.getBlockEntity(checkPos);
                    if (be instanceof PedestalBlockEntity pedestal)
                        pedestal.clearItem();
                }
            }
        }
    }

    private void igniteAllPedestals() {
        if (this.level() instanceof ServerLevel serverLevel) {
            Ritual ritual = RitualManager.getAllRituals().get(this.recipeId);
            if (ritual == null || this.centerPos == null) return;

            var pattern = ritual.pattern();
            int zSize = pattern.size();
            int xSize = pattern.getFirst().length();
            int xOffset = xSize / 2;
            int zOffset = zSize / 2;

            for (int z = 0; z < zSize; z++) {
                String row = pattern.get(z);
                for (int x = 0; x < xSize; x++) {
                    if (row.charAt(x) == ' ') continue;

                    BlockPos checkPos = this.centerPos.offset(x - xOffset, 0, z - zOffset);
                    BlockState state = serverLevel.getBlockState(checkPos);

                    if (state.getBlock() instanceof PedestalBlock) {
                        serverLevel.setBlock(checkPos, state.setValue(PedestalBlock.LIT, true), 3);
                        serverLevel.playSound(null, checkPos, SoundEvents.FIRE_AMBIENT, SoundSource.BLOCKS, 0.5F, 1.0F);
                    }
                }
            }
        }
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        this.currentTicks = input.getIntOr("CurrentTicks", 0);
        this.entityData.set(MAX_DURATION, input.getIntOr("MaxDuration", 200));

        input.read("RecipeId", Identifier.CODEC).ifPresent(id -> this.recipeId = id);
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        output.putInt("CurrentTicks", this.currentTicks);
        output.putInt("MaxDuration", this.entityData.get(MAX_DURATION));

        if (this.recipeId != null)
            output.store("RecipeId", Identifier.CODEC, this.recipeId);
    }
}