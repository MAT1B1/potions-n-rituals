package com.matibi.potionsnrituals.screen;

import com.matibi.potionsnrituals.PotionsNRituals;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.FormattedCharSequence;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class CustomBookScreen extends Screen {

    private static final Identifier BOOK_TEXTURE = Identifier.fromNamespaceAndPath(PotionsNRituals.MOD_ID, "textures/gui/book.png");

    private static final int TEXTURE_WIDTH = 281;
    private static final int TEXTURE_HEIGHT = 180;

    private static final int BG_WIDTH = TEXTURE_WIDTH;
    private static final int BG_HEIGHT = TEXTURE_HEIGHT;

    private static final int HALF_WIDTH = TEXTURE_WIDTH / 2; // ~140, centre = reliure

    private static final int LINE_HEIGHT = 10;
    private static final int OUTER_MARGIN = 20;
    private static final int INNER_MARGIN = 18;
    private static final int TEXT_MARGIN_Y = 14;

    // ⚠️ largeur max = DEMI page, pas livre entier — c'est ÇA qui forçait le wrap large avant
    private static final int MAX_TEXT_WIDTH = HALF_WIDTH - OUTER_MARGIN - INNER_MARGIN; // ≈ 102px, pas 209px

    private static final int TEXT_COLOR = 0xFF000000;

    private final String text;
    private List<FormattedCharSequence> leftLines;
    private List<FormattedCharSequence> rightLines;

    public CustomBookScreen(Component title, String text) {
        super(title);
        this.text = text;
    }

    @Override
    protected void init() {
        super.init();

        List<FormattedCharSequence> allLines = this.font.split(Component.literal(text), MAX_TEXT_WIDTH);
        int maxLinesPerPage = (TEXTURE_HEIGHT - TEXT_MARGIN_Y * 2) / LINE_HEIGHT;

        int splitIndex = Math.min(maxLinesPerPage, allLines.size());
        this.leftLines = allLines.subList(0, splitIndex);
        this.rightLines = splitIndex < allLines.size()
                ? allLines.subList(splitIndex, Math.min(allLines.size(), splitIndex + maxLinesPerPage))
                : List.of();
    }

    @Override
    public void extractRenderState(@NonNull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractRenderState(graphics, mouseX, mouseY, a);

        int x = (this.width - BG_WIDTH) / 2;
        int y = (this.height - BG_HEIGHT) / 2;

        graphics.blit(RenderPipelines.GUI_TEXTURED, BOOK_TEXTURE, x, y, 0, 0, TEXTURE_WIDTH, TEXTURE_HEIGHT, TEXTURE_WIDTH, TEXTURE_HEIGHT);

        drawPage(graphics, leftLines, x + OUTER_MARGIN, y + TEXT_MARGIN_Y);
        drawPage(graphics, rightLines, x + HALF_WIDTH + INNER_MARGIN, y + TEXT_MARGIN_Y);
    }

    private void drawPage(GuiGraphicsExtractor graphics, List<FormattedCharSequence> lines, int textX, int startY) {
        int textY = startY;
        for (FormattedCharSequence line : lines) {
            graphics.text(this.font, line, textX, textY, TEXT_COLOR, false);
            textY += LINE_HEIGHT;
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}