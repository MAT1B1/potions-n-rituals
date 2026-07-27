package com.matibi.potionsnrituals.entity;

import com.matibi.potionsnrituals.datacomponent.CapturedMob;
import com.matibi.potionsnrituals.datacomponent.ModDataComponents;
import com.matibi.potionsnrituals.item.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;

import java.util.Optional;

public class CaptureSphereEntity extends ThrowableItemProjectile {

    public CaptureSphereEntity(EntityType<? extends ThrowableItemProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public CaptureSphereEntity(Level level, LivingEntity owner, ItemStack stack) {
        super(ModEntities.CAPTURE_SPHERE, owner, level, stack);
    }

    @Override
    public @NonNull Item getDefaultItem() {
        return ModItems.CAPTURE_SPHERE;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NonNull Builder builder) {
        super.defineSynchedData(builder);
    }

    @Override
    protected void onHitEntity(@NonNull EntityHitResult result) {
        if (this.level() instanceof ServerLevel serverLevel) {
            Entity hit = result.getEntity();
            if (hit == this.getOwner()) return;

            CapturedMob captured = getCapturedMob();

            if (captured.hasMob())
                releaseMob(serverLevel, hit.position());
            else {
                captureMob(serverLevel, hit);
            }

        }

        this.discard();
    }

    @Override
    protected void onHitBlock(@NonNull BlockHitResult result) {
        if (this.level() instanceof ServerLevel serverLevel) {
            CapturedMob captured = getCapturedMob();

            if (captured.hasMob())
                releaseMob(serverLevel, result.getLocation());
            else {
                ItemStack newStack = new ItemStack(ModItems.CAPTURE_SPHERE);
                spawnAtLocation(serverLevel, newStack, 0.5f);
            }
        }

        this.discard();
    }

    private CapturedMob getCapturedMob() {
        return this.getItem().getOrDefault(ModDataComponents.CAPTURED_MOB, CapturedMob.EMPTY);
    }

    private void captureMob(@NonNull ServerLevel level, @NonNull Entity target) {
        if (!(target instanceof LivingEntity)) return;

        Identifier typeId = BuiltInRegistries.ENTITY_TYPE.getKey(target.getType());

        target.discard();

        ItemStack newStack = new ItemStack(ModItems.CAPTURE_SPHERE);
        newStack.set(ModDataComponents.CAPTURED_MOB, new CapturedMob(Optional.of(typeId)));
        spawnAtLocation(level, newStack, 0.5f);
    }

    private void releaseMob(@NonNull ServerLevel level, @NonNull Vec3 pos) {
        CapturedMob captured = getCapturedMob();
        if (!captured.hasMob()) return;

        Identifier typeId = captured.entityType().orElse(null);
        if (typeId == null) return;

        BuiltInRegistries.ENTITY_TYPE.get(typeId).ifPresent(holder -> {
            EntityType<?> type = holder.value();
            Entity entity = type.create(level, EntitySpawnReason.SPAWNER);
            if (entity != null) {
                entity.setPos(pos.x, pos.y, pos.z);
                level.addFreshEntity(entity);
            }
        });
    }
}
