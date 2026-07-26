package com.matibi.potionsnrituals.screen;

import com.matibi.potionsnrituals.network.ExecuteCommandPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jspecify.annotations.NonNull;

public class CommandWriteScreen extends Screen {

    private EditBox commandBox;

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
        int titleY = centerY - 40;

        this.addRenderableWidget(new StringWidget(titleX, titleY, titleWidth, 9, this.title, this.font));

        this.commandBox = new EditBox(this.font, centerX - 125, centerY - 20, 250, 20, Component.translatable("screen.potions-n-rituals.command_write.placeholder"));
        this.commandBox.setMaxLength(256);
        this.addRenderableWidget(this.commandBox);

        this.setInitialFocus(this.commandBox);

        Button executeButton = Button.builder(Component.translatable("screen.potions-n-rituals.command_write.execute"), _ -> this.executeCommand())
                .bounds(centerX - 50, centerY + 15, 100, 20)
                .build();

        this.addRenderableWidget(executeButton);
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
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}