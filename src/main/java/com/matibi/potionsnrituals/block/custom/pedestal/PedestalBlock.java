package com.matibi.potionsnrituals.block.custom.pedestal;

import com.matibi.potionsnrituals.ritual.RitualTriggerManager;
import com.matibi.potionsnrituals.ritual.datagen.Ritual;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class PedestalBlock extends Block implements EntityBlock {
    public static final BooleanProperty LIT = BooleanProperty.create("lit");
    public static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 13, 16);

    public PedestalBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(LIT, false));
    }

    @Override
    protected @NonNull VoxelShape getShape(@NonNull BlockState state, @NonNull BlockGetter level, @NonNull BlockPos pos, @NonNull CollisionContext context) {
        return SHAPE;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NonNull BlockPos pos, @NonNull BlockState state) {
        return new PedestalBlockEntity(pos, state);
    }

    @Override
    protected @NonNull InteractionResult useItemOn(@NonNull ItemStack stack, @NonNull BlockState state, @NonNull Level level, @NonNull BlockPos pos, @NonNull Player player, @NonNull InteractionHand hand, @NonNull BlockHitResult hitResult) {
        if (stack.isEmpty())
            return InteractionResult.TRY_WITH_EMPTY_HAND;

        if (stack.getItem() == Items.FLINT_AND_STEEL)
            return this.ignite(state, level, pos, player, hand, stack);

        if (player.isShiftKeyDown() && stack.getItem() instanceof BlockItem)
            return InteractionResult.PASS;

        if (level.getBlockEntity(pos) instanceof PedestalBlockEntity blockEntity) {

            if (!level.isClientSide()) {
                ItemStack previousStack = blockEntity.getItem().copy();
                ItemStack pedestalStack = stack.copyWithCount(1);
                blockEntity.setItem(pedestalStack);

                if (!player.isCreative())
                    stack.shrink(1);

                if (!previousStack.isEmpty())
                    player.getInventory().placeItemBackInInventory(previousStack);

                return InteractionResult.SUCCESS;
            }

            return InteractionResult.SUCCESS;
        }
        return InteractionResult.TRY_WITH_EMPTY_HAND;
    }

    @Override
    protected @NonNull InteractionResult useWithoutItem(@NonNull BlockState state, @NonNull Level level, @NonNull BlockPos pos, @NonNull Player player, @NonNull BlockHitResult hitResult) {
        if (level.getBlockEntity(pos) instanceof PedestalBlockEntity blockEntity) {
            if (state.getValue(LIT)) {
                level.setBlock(pos, state.setValue(LIT, false), Block.UPDATE_ALL);
                return InteractionResult.SUCCESS;
            }

            ItemStack previousStack = blockEntity.getItem().copy();
            if (!previousStack.isEmpty()) {
                if (!level.isClientSide()) {
                    player.getInventory().placeItemBackInInventory(previousStack);
                    blockEntity.clearItem();
                }
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }

    private InteractionResult ignite(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, ItemStack stack) {
        if (!state.getValue(LIT)) {
            if (!level.isClientSide()) {
                level.setBlock(pos, state.setValue(LIT, true), Block.UPDATE_ALL);
                level.playSound(null, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);

                if (!player.isCreative())
                    stack.hurtAndBreak(1, player, hand);

                RitualTriggerManager.tryTriggerRitual(level, pos, Ritual.Catalysts.IGNITE);
            }

            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    @Override
    public void animateTick(@NonNull BlockState state, @NonNull Level level, @NonNull BlockPos pos, @NonNull RandomSource random) {
        if (!state.getValue(LIT))
            return;

        double x = pos.getX() + 0.5D;
        double y = pos.getY() + 0.95D;
        double z = pos.getZ() + 0.5D;

        level.addParticle(ParticleTypes.SMALL_FLAME, x, y, z, 0.0D, 0.02D, 0.0D);

        if (random.nextInt(3) == 0)
            level.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.02D, 0.0D);
    }

    @Override
    protected void tick(@NonNull BlockState state, @NonNull ServerLevel level, @NonNull BlockPos pos, @NonNull RandomSource random) {
        if (level.getGameTime() % 20L != 0L) return;
        if (level.isDarkOutside()) RitualTriggerManager.tryTriggerRitual(level, pos, Ritual.Catalysts.MOONLIGHT);
        super.tick(state, level, pos, random);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NonNull Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }

    private void drop(@NonNull Level level, @NonNull BlockPos pos, @Nullable BlockEntity blockEntity) {
        if (!level.isClientSide() && blockEntity instanceof PedestalBlockEntity pedestalBlockEntity) {
            Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), pedestalBlockEntity.getItem());
            pedestalBlockEntity.clearItem();
        }
    }

    @Override
    protected void spawnAfterBreak(@NonNull BlockState state, @NonNull ServerLevel level, @NonNull BlockPos pos, @NonNull ItemStack tool, boolean dropExperience) {
        drop(level, pos, level.getBlockEntity(pos));
        super.spawnAfterBreak(state, level, pos, tool, dropExperience);
    }

    @Override
    public void playerDestroy(@NonNull Level level, @NonNull Player player, @NonNull BlockPos pos, @NonNull BlockState state, @Nullable BlockEntity blockEntity, @NonNull ItemStack tool) {
        drop(level, pos, blockEntity);
        super.playerDestroy(level, player, pos, state, blockEntity, tool);
    }
}
