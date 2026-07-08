package com.matibi.potionsnrituals.block.custom.pedestal;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

@Environment(EnvType.CLIENT)
public class PedestalBlockEntityRenderer implements BlockEntityRenderer<PedestalBlockEntity, PedestalRenderState> {

    private final ItemModelResolver itemModelResolver;

    public PedestalBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.itemModelResolver = context.itemModelResolver();
    }

    @Override
    public @NonNull PedestalRenderState createRenderState() {
        return new PedestalRenderState();
    }

    @Override
    public void extractRenderState(@NonNull PedestalBlockEntity blockEntity, @NonNull PedestalRenderState state, float partialTicks, @NonNull Vec3 cameraPosition, @Nullable ModelFeatureRenderer.CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);

        ItemStack item = blockEntity.getItem();
        state.hasItem = !item.isEmpty();

        if (state.hasItem)
            this.itemModelResolver.updateForTopItem(
                    state.itemRenderState,
                    item,
                    ItemDisplayContext.GROUND,
                    blockEntity.getLevel(),
                    null,
                    blockEntity.getBlockPos().hashCode()
            );

        long time = blockEntity.getLevel() != null ? blockEntity.getLevel().getGameTime() : 0;
        state.ageInTicks = time + partialTicks;
    }

    @Override
    public void submit(PedestalRenderState state, @NonNull PoseStack poseStack, @NonNull SubmitNodeCollector submitNodeCollector, @NonNull CameraRenderState camera) {
        if (state.hasItem) {
            poseStack.pushPose();

            float bob = (float) Math.sin(state.ageInTicks / 10.0F) * 0.1F;

            poseStack.translate(0.5F, 1.0F + bob, 0.5F);

            float angle = state.ageInTicks * 3.0F;
            poseStack.mulPose(Axis.YP.rotationDegrees(angle));

            poseStack.scale(0.9F, 0.9F, 0.9F);

            state.itemRenderState.submit(
                    poseStack,
                    submitNodeCollector,
                    15728880,
                    OverlayTexture.NO_OVERLAY,
                    0x000000
            );

            poseStack.popPose();
        }
    }
}
