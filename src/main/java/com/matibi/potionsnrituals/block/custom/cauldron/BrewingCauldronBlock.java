package com.matibi.potionsnrituals.block.custom.cauldron;

import com.matibi.potionsnrituals.block.ModBlocks;
import com.matibi.potionsnrituals.effect.ModEffects;
import com.matibi.potionsnrituals.item.ModItems;
import com.matibi.potionsnrituals.item.custom.alchemicalStone.AlchemicalStone;
import com.matibi.potionsnrituals.item.custom.alchemicalStone.ModAlchemicalStone;
import com.matibi.potionsnrituals.util.CombinationUtils;
import com.mojang.serialization.MapCodec;
import net.fabricmc.fabric.api.client.rendering.v1.BlockColorRegistry;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SpellParticleOption;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Util;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BrewingCauldronBlock extends BaseEntityBlock {

    public static final IntegerProperty LEVEL = IntegerProperty.create("level", 0, 3);
    public static final int MAX_LEVEL = 3;
    public static final MapCodec<BrewingCauldronBlock> CODEC = Block.simpleCodec(BrewingCauldronBlock::new);

    private static final VoxelShape SHAPE_INSIDE = Block.column(12.0F, 4.0F, 16.0F);
    protected static final VoxelShape SHAPE = Util.make(() -> Shapes.join(Shapes.block(),
            Shapes.or(Block.column(16.0F, 8.0F, 0.0F, 3.0F),
                    Block.column(8.0F, 16.0F, 0.0F, 3.0F),
                    Block.column(12.0F, 0.0F, 3.0F), SHAPE_INSIDE), BooleanOp.ONLY_FIRST));

    public BrewingCauldronBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(LEVEL, 0));
    }

    @Override
    protected @NonNull MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LEVEL);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NonNull BlockPos pos, @NonNull BlockState state) {
        return new BrewingCauldronBlockEntity(pos, state);
    }

    @Override
    protected @NonNull RenderShape getRenderShape(@NonNull BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected @NonNull VoxelShape getShape(@NonNull BlockState state, @NonNull BlockGetter level, @NonNull BlockPos pos, @NonNull CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected @NonNull VoxelShape getInteractionShape(@NonNull BlockState state, @NonNull BlockGetter level, @NonNull BlockPos pos) {
        return SHAPE_INSIDE;
    }

    @Override
    protected @NonNull InteractionResult useItemOn(@NonNull ItemStack stack, @NonNull BlockState state, @NonNull Level level, @NonNull BlockPos pos, @NonNull Player player, @NonNull InteractionHand hand, @NonNull BlockHitResult hitResult) {
        BrewingCauldronBlockEntity be = getBlockEntity(level, pos);
        if (be == null) return InteractionResult.PASS;

        if (stack.is(ModItems.ALCHEMICAL_STONE)) {
            PotionContents stoneContents = stack.get(DataComponents.POTION_CONTENTS);
            if (stoneContents == null || !stoneContents.hasEffects())
                return tryChargeStone(stack, state, level, pos, player, hand, be);
        }
        if (isPotionItem(stack))
            return tryAddPotion(stack, state, level, pos, player, be);
        if (stack.is(Items.GLASS_BOTTLE))
            return tryExtractPotion(stack, state, level, pos, player, be);
        if (stack.is(Items.ARROW))
            return tryDipArrows(stack, state, level, pos, player, be);

        return InteractionResult.PASS;
    }

    private InteractionResult tryAddPotion(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, BrewingCauldronBlockEntity be) {
        PotionContents itemContents = stack.get(DataComponents.POTION_CONTENTS);
        if (itemContents == null || !itemContents.hasEffects())
            return InteractionResult.PASS;

        if (be.isFull())
            return InteractionResult.PASS;

        if (!level.isClientSide()) {
            List<MobEffectInstance> allEffects = new ArrayList<>();
            if (!be.isEmpty())
                be.getPotionContents().getAllEffects().forEach(allEffects::add);
            itemContents.getAllEffects().forEach(allEffects::add);

            List<MobEffectInstance> combined = CombinationUtils.combineEffect(allEffects);
            int newLevel = be.getLiquidLevel() + 1;

            PotionContents newContents = combined.size() > 1 ||
                    combined.getFirst().getEffect() == ModEffects.UNSTABLE ?
                    new PotionContents(Optional.empty(), Optional.empty(), combined, Optional.of("mixed")) :
                    itemContents;

            level.setBlock(pos, state.setValue(LEVEL, newLevel), 3);

            if (combined.getFirst().getEffect() == ModEffects.UNSTABLE) {
                level.explode(null, pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5, 4.0F, Level.ExplosionInteraction.TNT);
                return InteractionResult.SUCCESS;
            }

            BlockEntity currentBe = level.getBlockEntity(pos);
            if (currentBe instanceof BrewingCauldronBlockEntity updatedBe)
                updatedBe.setPotionContents(newContents);

            level.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);

            if (!player.isCreative())
                stack.shrink(1);
        }

        return InteractionResult.SUCCESS;
    }

    private InteractionResult tryExtractPotion(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, BrewingCauldronBlockEntity be) {
        if (be.isEmpty())
            return InteractionResult.PASS;

        if (!level.isClientSide()) {
            PotionContents contents = be.getPotionContents();
            int newLevel = be.getLiquidLevel() - 1;

            ItemStack potionStack = new ItemStack(Items.POTION);
            potionStack.set(DataComponents.POTION_CONTENTS, contents);

            if (newLevel == 0)
                level.setBlock(pos, Blocks.CAULDRON.defaultBlockState(), 3);
            else {
                level.setBlock(pos, state.setValue(LEVEL, newLevel), 3);

                BlockEntity currentBe = level.getBlockEntity(pos);
                if (currentBe instanceof BrewingCauldronBlockEntity updatedBe)
                    updatedBe.setPotionContents(contents);
            }

            level.playSound(null, pos, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);

            if (!player.isCreative())
                stack.shrink(1);

            if (!player.getInventory().add(potionStack))
                player.drop(potionStack, false);
        }

        return InteractionResult.SUCCESS;
    }

    private boolean isPotionItem(ItemStack stack) {
        return stack.is(Items.POTION)
                || stack.is(Items.SPLASH_POTION)
                || stack.is(Items.LINGERING_POTION);
    }

    private InteractionResult tryDipArrows(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, BrewingCauldronBlockEntity be) {
        if (be.isEmpty())
            return InteractionResult.PASS;

        if (!level.isClientSide()) {
            PotionContents contents = be.getPotionContents();
            int newLevel = be.getLiquidLevel() - 1;

            ItemStack tippedStack = new ItemStack(Items.TIPPED_ARROW, stack.getCount());
            tippedStack.set(DataComponents.POTION_CONTENTS, contents);

            if (newLevel == 0)
                level.setBlock(pos, Blocks.CAULDRON.defaultBlockState(), 3);
            else {
                level.setBlock(pos, state.setValue(LEVEL, newLevel), 3);
                BlockEntity currentBe = level.getBlockEntity(pos);
                if (currentBe instanceof BrewingCauldronBlockEntity updatedBe)
                    updatedBe.setPotionContents(contents);
            }

            level.playSound(null, pos, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);

            if (!player.isCreative())
                stack.shrink(stack.count());

            if (!player.getInventory().add(tippedStack))
                player.drop(tippedStack, false);
        }

        return InteractionResult.SUCCESS;
    }

    private InteractionResult tryChargeStone(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BrewingCauldronBlockEntity be) {
        if (be.isEmpty())
            return InteractionResult.PASS;

        if (!level.isClientSide()) {
            PotionContents contents = be.getPotionContents();
            if (contents == null || !contents.hasEffects())
                return InteractionResult.PASS;

            MobEffectInstance firstEffect = contents.getAllEffects().iterator().next();

            AlchemicalStone matchingStone = null;
            for (AlchemicalStone stone : ModAlchemicalStone.ALCHEMICAL_STONE_REGISTRY) {
                if (stone.effect().value() == firstEffect.getEffect().value() && stone.amplifier() == firstEffect.getAmplifier()) {
                    matchingStone = stone;
                    break;
                }
            }

            if (matchingStone == null)
                return InteractionResult.PASS;

            int newLevel = be.getLiquidLevel() - 1;

            ItemStack stoneStack = AlchemicalStone.getItemStack(Holder.direct(matchingStone));
            stoneStack.setCount(stack.getCount());

            if (newLevel == 0)
                level.setBlock(pos, Blocks.CAULDRON.defaultBlockState(), 3);
            else {
                level.setBlock(pos, state.setValue(LEVEL, newLevel), 3);
                BlockEntity currentBe = level.getBlockEntity(pos);
                if (currentBe instanceof BrewingCauldronBlockEntity updatedBe)
                    updatedBe.setPotionContents(contents);
            }

            level.playSound(null, pos, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);

            player.setItemInHand(hand, stoneStack);
        }

        return InteractionResult.SUCCESS;
    }

    @Nullable
    private BrewingCauldronBlockEntity getBlockEntity(Level level, BlockPos pos) {
        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof BrewingCauldronBlockEntity brewingBe)
            return brewingBe;
        return null;
    }

    public static void registerTint() {
        BlockColorRegistry.register((_, level, pos, tintValues) -> {
            if (level.getBlockEntity(pos) instanceof BrewingCauldronBlockEntity cauldronBe) {
                PotionContents contents = cauldronBe.getPotionContents();

                if (contents != null && !contents.equals(PotionContents.EMPTY)) {
                    tintValues.add(contents.getColor());
                    return;
                }
            }
            tintValues.add(BiomeColors.getAverageWaterColor(level, pos));
        }, ModBlocks.BREWING_CAULDRON);
    }

    @Override
    public void animateTick(@NonNull BlockState state, @NonNull Level level, @NonNull BlockPos pos, @NonNull RandomSource random) {
        super.animateTick(state, level, pos, random);
        int liquidLevel = state.getValue(LEVEL);

        if (liquidLevel > 0) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof BrewingCauldronBlockEntity cauldronBe) {
                PotionContents contents = cauldronBe.getPotionContents();

                if (contents != null && !contents.equals(PotionContents.EMPTY)) {
                    int color = contents.getColor();

                    double surfaceHeight = 0.3 + (liquidLevel * 0.2);

                    if (random.nextFloat() < 0.3F) {
                        double x = pos.getX() + 0.3 + (random.nextFloat() * 0.4);
                        double y = pos.getY() + surfaceHeight;
                        double z = pos.getZ() + 0.3 + (random.nextFloat() * 0.4);

                        level.addParticle(
                                SpellParticleOption.create(ParticleTypes.EFFECT, color, 0.5f),
                                x, y, z,
                                0.0, 0.03, 0.0
                        );
                    }
                }
            }
        }
    }
}