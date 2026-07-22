package com.matibi.potionsnrituals.item.custom.talisman;

import com.matibi.potionsnrituals.util.TickManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.Mannequin;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class DecoyItem extends Item {

    public DecoyItem(Properties properties) {
        super(properties.stacksTo(1));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        Level level = context.getLevel();
        ItemStack stack = context.getItemInHand();
        BlockPos pos = context.getClickedPos();
        Direction face = context.getClickedFace();
        Vec3 clickLocation = context.getClickLocation();

        if (player == null) return InteractionResult.PASS;

        if (!level.isClientSide()) {
            Mannequin mannequin = new Mannequin(EntityTypes.MANNEQUIN, level);

            double spawnX = clickLocation.x;
            double spawnY = pos.getY();
            double spawnZ = clickLocation.z;
            switch (face) {
                case DOWN: spawnY -= 2; break;
                case UP: spawnY++; break;
                case NORTH: spawnZ -= 0.5; break;
                case SOUTH: spawnZ += 0.5; break;
                case WEST: spawnX -= 0.5; break;
                case EAST: spawnX += 0.5; break;
            }

            mannequin.setPos(spawnX, spawnY, spawnZ);

            float yaw = player.getYRot();
            float pitch = player.getXRot();
            mannequin.setYRot(yaw);
            mannequin.yRotO = yaw;
            mannequin.xRotO = pitch;
            mannequin.setYHeadRot(yaw);
            mannequin.yBodyRot = yaw;

            mannequin.setComponent(DataComponents.PROFILE, player.getProfile());
            level.addFreshEntity(mannequin);

            int[] tickCounter = {0};

            TickManager.registerUntil(
                mannequin::isAlive,
                _ -> {
                    tickCounter[0]++;

                    if (tickCounter[0] % 20 == 0) {
                        double radius = 16.0D;
                        AABB searchBox = mannequin.getBoundingBox().inflate(radius);
                        List<Monster> nearbyMonsters = level.getEntitiesOfClass(Monster.class, searchBox);

                        for (Monster monster : nearbyMonsters) {
                            LivingEntity currentTarget = monster.getTarget();
                            if (currentTarget == player)
                                monster.setTarget(mannequin);
                        }
                    }
                }
            );

            stack.shrink(1);
        }

        return InteractionResult.SUCCESS;
    }
}