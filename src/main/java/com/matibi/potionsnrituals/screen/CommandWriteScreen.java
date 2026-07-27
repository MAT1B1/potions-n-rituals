package com.matibi.potionsnrituals.screen;

import com.matibi.potionsnrituals.network.ExecuteCommandPayload;
import com.matibi.potionsnrituals.util.CommandPricing;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jspecify.annotations.NonNull;

public class CommandWriteScreen extends Screen {

    private EditBox commandBox;
    private float currentCost = 0.0F;

    private static final Identifier FULL_HEART_TEXTURE = Identifier.withDefaultNamespace("hud/heart/full");
    private static final Identifier HALF_HEART_TEXTURE = Identifier.withDefaultNamespace("hud/heart/half");
    private static final Identifier CONTAINER_TEXTURE = Identifier.withDefaultNamespace("hud/heart/container");

    public CommandWriteScreen() {
        super(Component.translatable("screen.potions-n-rituals.command_write.title").withStyle(ChatFormatting.GOLD));
    }

    @Override
    protected void init() {
        super.init();

        int centerX = this.width / 2;
        int centerY = this.height / 2;

        int titleWidth = this.font.width(this.title);
        int titleX = (this.width - titleWidth) / 2;
        int titleY = centerY - 70;
        this.addRenderableWidget(new StringWidget(titleX, titleY, titleWidth, 9, this.title, this.font));

        this.commandBox = new EditBox(this.font, centerX - 125, centerY - 35, 250, 20, Component.translatable("screen.potions-n-rituals.command_write.placeholder"));
        this.commandBox.setMaxLength(256);
        this.commandBox.setResponder(this::updateCost);
        this.addRenderableWidget(this.commandBox);
        this.setInitialFocus(this.commandBox);

        Button executeButton = Button.builder(Component.translatable("screen.potions-n-rituals.command_write.execute"), _ -> this.executeCommand())
                .bounds(centerX - 75, centerY + 45, 150, 20)
                .build();
        this.addRenderableWidget(executeButton);

        Button cancelButton = Button.builder(Component.literal("✕").withStyle(ChatFormatting.RED), _ -> this.onClose())
                .bounds(centerX + 125, titleY - 5, 20, 20)
                .build();
        this.addRenderableWidget(cancelButton);
    }

    private void updateCost(String fullCommand) {
        if (Minecraft.getInstance().player == null) return;

        String cleanCommand = fullCommand.startsWith("/") ? fullCommand.substring(1) : fullCommand;
        String base = cleanCommand.split(" ")[0].toLowerCase();

        this.currentCost = 0.0F;
        if (!base.isEmpty())
            this.currentCost = CommandPricing.cost(Minecraft.getInstance().player, base, cleanCommand);
    }

    private void executeCommand() {
        String command = this.commandBox.getValue();

        if (!command.isEmpty()) {
            if (command.startsWith("/"))
                command = command.substring(1);

            ClientPlayNetworking.send(new ExecuteCommandPayload(command));
            this.onClose();
        }
    }

    @Override
    public void extractRenderState(@NonNull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractRenderState(graphics, mouseX, mouseY, a);

        int centerX = this.width / 2;
        int centerY = this.height / 2;

        if (this.currentCost > 0) {
            Component costTitle = Component.literal("COST").withStyle(ChatFormatting.GOLD);
            int costTitleWidth = this.font.width(costTitle);
            graphics.text(this.font, costTitle, centerX - (costTitleWidth / 2), centerY - 10, 0xFFAA00FF, true);

            int fullHearts = (int) (this.currentCost / 2);
            boolean hasHalfHeart = (this.currentCost % 2) != 0;
            int totalHearts = fullHearts + (hasHalfHeart ? 1 : 0);

            int startX = centerX - ((totalHearts * 9) / 2);
            int heartsY = centerY + 2;

            for (int i = 0; i < fullHearts; i++) {
                graphics.blitSprite(RenderPipelines.GUI_TEXTURED, CONTAINER_TEXTURE, startX + (i * 9), heartsY, 9, 9);
                graphics.blitSprite(RenderPipelines.GUI_TEXTURED, FULL_HEART_TEXTURE, startX + (i * 9), heartsY, 9, 9);
            }

            if (hasHalfHeart) {
                graphics.blitSprite(RenderPipelines.GUI_TEXTURED, CONTAINER_TEXTURE, startX + (fullHearts * 9), heartsY, 9, 9);
                graphics.blitSprite(RenderPipelines.GUI_TEXTURED, HALF_HEART_TEXTURE, startX + (fullHearts * 9), heartsY, 9, 9);
            }

            String xpText = "100 x ";
            int xpTextWidth = this.font.width(xpText);
            int xpY = centerY + 14;
            int totalWidth = xpTextWidth + 16;
            int xpStartX = centerX - (totalWidth / 2);

            graphics.text(this.font, xpText, xpStartX, xpY + 4, 0xFF0BE637, true);
            graphics.item(new ItemStack(Items.EXPERIENCE_BOTTLE), xpStartX + xpTextWidth, xpY);
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}