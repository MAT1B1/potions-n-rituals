package com.matibi.potionsnrituals.entity;

import com.matibi.potionsnrituals.world.data.ModAttachments;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.NonNull;

public class PortalBuilderEntity extends Entity {

    private int ageInTicks = 0;
    private Direction.Axis portalAxis = Direction.Axis.X;

    public PortalBuilderEntity(EntityType<?> entityType, Level level) {
        super(entityType, level);
        this.noPhysics = true;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NonNull Builder entityData) { }

    public void setPortalAxis(Direction.Axis axis) {
        this.portalAxis = (axis == Direction.Axis.Z) ? Direction.Axis.X : Direction.Axis.Z;
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) return;

        this.ageInTicks++;
        ServerLevel serverLevel = (ServerLevel) this.level();

        if (this.ageInTicks % 10 == 0) {
            int step = this.ageInTicks / 10;
            buildStep(serverLevel, step);
        }
    }

    @Override
    public boolean hurtServer(@NonNull ServerLevel level, @NonNull DamageSource source, float damage) {
        return false;
    }

    private void buildStep(ServerLevel level, int step) {
        BlockPos center = this.blockPosition();

        // 10 étapes = 5 secondes d'animation symétrique
        switch (step) {
            case 1: placePair(level, center, 0, 0, 0, 0); break; // Centre Bas
            case 2: placePair(level, center, -1, 0, 1, 0); break; // Côtés Bas
            case 3: placePair(level, center, -2, 0, 2, 0); break; // Coins Bas
            case 4: placePair(level, center, -2, 1, 2, 1); break; // Piliers (Bas)
            case 5: placePair(level, center, -2, 2, 2, 2); break; // Piliers (Milieu)
            case 6: placePair(level, center, -2, 3, 2, 3); break; // Piliers (Haut)
            case 7: placePair(level, center, -2, 4, 2, 4); break; // Coins Haut
            case 8: placePair(level, center, -1, 4, 1, 4); break; // Côtés Haut
            case 9: placePair(level, center, 0, 4, 0, 4); break; // Centre Haut
            case 10:
                ServerLevel overworld = level.getServer().getLevel(Level.OVERWORLD);
                if (overworld != null) {
                    overworld.setAttached(ModAttachments.NETHER_SEALED, false);

                    level.getServer().getPlayerList().getPlayers().forEach(player ->
                        player.sendSystemMessage(Component.literal("§4§lLe sceau de la dimension de feu a été brisé...")));
                }

                BlockPos firePos = center.above();
                level.setBlockAndUpdate(firePos, Blocks.FIRE.defaultBlockState());

                this.discard();
                break;
        }
    }

    private void placePair(ServerLevel level, BlockPos center, int dx1, int dy1, int dx2, int dy2) {
        boolean isX = (this.portalAxis == Direction.Axis.X);

        BlockPos pos1 = center.offset(isX ? dx1 : 0, dy1, isX ? 0 : dx1);
        BlockPos pos2 = center.offset(isX ? dx2 : 0, dy2, isX ? 0 : dx2);

        level.setBlockAndUpdate(pos1, Blocks.OBSIDIAN.defaultBlockState());
        level.setBlockAndUpdate(pos2, Blocks.OBSIDIAN.defaultBlockState());

        level.playSound(null, pos1, SoundEvents.RESPAWN_ANCHOR_CHARGE, SoundSource.BLOCKS, 1.0f, 0.8f);

        level.sendParticles(ParticleTypes.PORTAL, pos1.getX() + 0.5, pos1.getY() + 0.5, pos1.getZ() + 0.5, 10, 0.2, 0.2, 0.2, 0.1);
        level.sendParticles(ParticleTypes.PORTAL, pos2.getX() + 0.5, pos2.getY() + 0.5, pos2.getZ() + 0.5, 10, 0.2, 0.2, 0.2, 0.1);
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        this.ageInTicks = input.getIntOr("AgeInTicks", 0);
        this.portalAxis = Direction.Axis.valueOf(input.getStringOr("PortalAxis", Direction.Axis.X.name()));
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        output.putInt("AgeInTicks", this.ageInTicks);
        output.putString("PortalAxis", this.portalAxis.name());
    }
}