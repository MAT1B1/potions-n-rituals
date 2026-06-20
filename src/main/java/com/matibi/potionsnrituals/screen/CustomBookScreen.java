package com.matibi.potionsnrituals.screen;

import com.matibi.potionsnrituals.book.BookPage;
import com.matibi.potionsnrituals.util.ModUtils;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.Identifier;
import net.minecraft.util.FormattedCharSequence;
import org.apache.commons.lang3.mutable.MutableFloat;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.List;

public class CustomBookScreen extends Screen {

    private static final Identifier BOOK_TEXTURE = ModUtils.id("textures/gui/book.png");

    private static final int TEXTURE_WIDTH = 281;
    private static final int TEXTURE_HEIGHT = 180;

    private static final int BG_WIDTH = TEXTURE_WIDTH;
    private static final int BG_HEIGHT = TEXTURE_HEIGHT;

    private static final int HALF_WIDTH = TEXTURE_WIDTH / 2;

    private static final int LINE_HEIGHT = 10;
    private static final int OUTER_MARGIN = 20;
    private static final int INNER_MARGIN = 18;
    private static final int TEXT_MARGIN_Y = 14;

    private static final int PAGE_CONTENT_WIDTH = HALF_WIDTH - OUTER_MARGIN - INNER_MARGIN;

    private static final int TITLE_COLOR = 0xFF1A1A1A;
    private static final int TEXT_COLOR = 0xFF000000;
    private static final int CAPTION_COLOR = 0xFF3A3A3A;

    private static final int TITLE_GAP = 6;
    private static final int IMAGE_GAP = 4;
    private static final int CAPTION_GAP = 2;
    private static final int IMAGE_BLOCK_GAP = 6;
    private static final int MAX_IMAGE_HEIGHT = 50;

    private static final Identifier ARROW_BACK_TEXTURE = Identifier.withDefaultNamespace("textures/gui/sprites/widget/page_backward.png");
    private static final Identifier ARROW_BACK_HIGHLIGHTED = Identifier.withDefaultNamespace("textures/gui/sprites/widget/page_backward_highlighted.png");
    private static final Identifier ARROW_FORWARD_TEXTURE = Identifier.withDefaultNamespace("textures/gui/sprites/widget/page_forward.png");
    private static final Identifier ARROW_FORWARD_HIGHLIGHTED = Identifier.withDefaultNamespace("textures/gui/sprites/widget/page_forward_highlighted.png");
    private static final int ARROW_TEX_WIDTH = 23;
    private static final int ARROW_TEX_HEIGHT = 13;
    private static final int ARROW_BOTTOM_OFFSET = 25;

    private final List<BookPage> pages;
    private int spreadIndex;

    private BookPage currentLeft;
    private BookPage currentRight;

    private List<FormattedCharSequence> leftBodyLines;
    private List<FormattedCharSequence> rightBodyLines;

    // Calcul dynamique des positions Y pour la détection précise de la souris
    private int leftTextStartY;
    private int rightTextStartY;

    public CustomBookScreen(Component title, List<BookPage> pages) {
        super(title);
        if (pages == null || pages.isEmpty()) {
            throw new IllegalArgumentException("pages ne peut pas être vide");
        }
        this.pages = List.copyOf(pages);
        this.spreadIndex = 0;
    }

    @Override
    protected void init() {
        super.init();
        updateCurrentSpread();
    }

    private void updateCurrentSpread() {
        this.currentLeft = pages.get(spreadIndex);
        this.currentRight = (spreadIndex + 1 < pages.size()) ? pages.get(spreadIndex + 1) : null;

        this.leftBodyLines = wrapBody(currentLeft);
        this.rightBodyLines = wrapBody(currentRight);
    }

    private List<FormattedCharSequence> wrapBody(@Nullable BookPage page) {
        if (page == null || page.bodyText() == null) {
            return List.of();
        }
        return this.font.split(page.bodyText(), PAGE_CONTENT_WIDTH);
    }

    private FormattedCharSequence toLine(Component component) {
        return this.font.split(component, Integer.MAX_VALUE).getFirst();
    }

    private boolean hasPrevSpread() {
        return spreadIndex - 2 >= 0;
    }

    private boolean hasNextSpread() {
        return spreadIndex + 2 < pages.size();
    }

    private void goPrev() {
        if (hasPrevSpread()) {
            spreadIndex -= 2;
            updateCurrentSpread();
        }
    }

    private void goNext() {
        if (hasNextSpread()) {
            spreadIndex += 2;
            updateCurrentSpread();
        }
    }

    @Override
    public void extractRenderState(@NonNull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractRenderState(graphics, mouseX, mouseY, a);

        int x = (this.width - BG_WIDTH) / 2;
        int y = (this.height - BG_HEIGHT) / 2;

        graphics.blit(RenderPipelines.GUI_TEXTURED, BOOK_TEXTURE, x, y, 0, 0, TEXTURE_WIDTH, TEXTURE_HEIGHT, TEXTURE_WIDTH, TEXTURE_HEIGHT);

        // Page Gauche
        int leftX = x + OUTER_MARGIN;
        this.leftTextStartY = calculateTextStartY(currentLeft, y + TEXT_MARGIN_Y);
        Style hoveredStyleLeft = getStyleAt(mouseX, mouseY, leftX, leftTextStartY, leftBodyLines);
        drawPage(graphics, currentLeft, leftBodyLines, leftX, y + TEXT_MARGIN_Y, hoveredStyleLeft);

        // Page Droite
        int rightX = x + HALF_WIDTH + INNER_MARGIN;
        this.rightTextStartY = calculateTextStartY(currentRight, y + TEXT_MARGIN_Y);
        Style hoveredStyleRight = getStyleAt(mouseX, mouseY, rightX, rightTextStartY, rightBodyLines);
        drawPage(graphics, currentRight, rightBodyLines, rightX, y + TEXT_MARGIN_Y, hoveredStyleRight);

        drawArrows(graphics, x, y, mouseX, mouseY);
    }

    private int calculateTextStartY(@Nullable BookPage page, int startY) {
        if (page == null) return startY;
        int cursorY = startY;
        if (page.title() != null) {
            cursorY += LINE_HEIGHT + TITLE_GAP;
        }
        if (!page.images().isEmpty()) {
            cursorY = simulateDrawImages(page.images()) + IMAGE_BLOCK_GAP;
        }
        return cursorY;
    }

    private int simulateDrawImages(List<BookPage.PageImage> images) {
        int count = images.size();
        int slotWidth = count == 2 ? (PAGE_CONTENT_WIDTH - IMAGE_GAP) / 2 : PAGE_CONTENT_WIDTH;
        int maxDrawHeight = 0;
        int maxCaptionLineCount = 0;

        for (BookPage.PageImage img : images) {
            float ratio = (float) img.height() / img.width();
            int w = Math.min(slotWidth, img.width());
            int h = Math.min(MAX_IMAGE_HEIGHT, Math.round(w * ratio));
            if (h == MAX_IMAGE_HEIGHT) {
                w = Math.round(h / ratio);
            }
            maxDrawHeight = Math.max(maxDrawHeight, h);

            String caption = img.caption();
            if (caption != null && !caption.isEmpty()) {
                maxCaptionLineCount = Math.max(maxCaptionLineCount, this.font.split(Component.literal(caption), slotWidth).size());
            }
        }
        int captionBlockHeight = maxCaptionLineCount > 0 ? CAPTION_GAP + maxCaptionLineCount * LINE_HEIGHT : 0;
        return maxDrawHeight + captionBlockHeight;
    }

    private void drawArrows(GuiGraphicsExtractor graphics, int x, int y, int mouseX, int mouseY) {
        int arrowY = y + BG_HEIGHT - ARROW_BOTTOM_OFFSET;
        int arrow_margin = OUTER_MARGIN + 10;
        int leftArrowX = x + arrow_margin;
        int rightArrowX = x + BG_WIDTH - arrow_margin - ARROW_TEX_WIDTH;

        if (hasPrevSpread()) {
            boolean hover = isInside(mouseX, mouseY, leftArrowX, arrowY, ARROW_TEX_WIDTH, ARROW_TEX_HEIGHT);
            Identifier tex = hover ? ARROW_BACK_HIGHLIGHTED : ARROW_BACK_TEXTURE;
            graphics.blit(RenderPipelines.GUI_TEXTURED, tex, leftArrowX, arrowY, 0, 0, ARROW_TEX_WIDTH, ARROW_TEX_HEIGHT, ARROW_TEX_WIDTH, ARROW_TEX_HEIGHT);
        }
        if (hasNextSpread()) {
            boolean hover = isInside(mouseX, mouseY, rightArrowX, arrowY, ARROW_TEX_WIDTH, ARROW_TEX_HEIGHT);
            Identifier tex = hover ? ARROW_FORWARD_HIGHLIGHTED : ARROW_FORWARD_TEXTURE;
            graphics.blit(RenderPipelines.GUI_TEXTURED, tex, rightArrowX, arrowY, 0, 0, ARROW_TEX_WIDTH, ARROW_TEX_HEIGHT, ARROW_TEX_WIDTH, ARROW_TEX_HEIGHT);
        }
    }

    @Override
    public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
        double mouseX = event.x();
        double mouseY = event.y();
        int x = (this.width - BG_WIDTH) / 2;
        int y = (this.height - BG_HEIGHT) / 2;
        int arrowY = y + BG_HEIGHT - ARROW_BOTTOM_OFFSET;
        int arrow_margin = OUTER_MARGIN + 10;
        int leftArrowX = x + arrow_margin;
        int rightArrowX = x + BG_WIDTH - arrow_margin - ARROW_TEX_WIDTH;

        if (hasPrevSpread() && isInside(mouseX, mouseY, leftArrowX, arrowY, ARROW_TEX_WIDTH, ARROW_TEX_HEIGHT)) {
            goPrev();
            return true;
        }
        if (hasNextSpread() && isInside(mouseX, mouseY, rightArrowX, arrowY, ARROW_TEX_WIDTH, ARROW_TEX_HEIGHT)) {
            goNext();
            return true;
        }

        // Gestion des liens au clic sur la page gauche
        Style styleLeft = getStyleAt(mouseX, mouseY, x + OUTER_MARGIN, leftTextStartY, leftBodyLines);
        if (handleStyleClick(styleLeft)) return true;

        // Gestion des liens au clic sur la page droite
        if (currentRight != null) {
            Style styleRight = getStyleAt(mouseX, mouseY, x + HALF_WIDTH + INNER_MARGIN, rightTextStartY, rightBodyLines);
            if (handleStyleClick(styleRight)) return true;
        }

        return super.mouseClicked(event, doubleClick);
    }

    private boolean handleStyleClick(@Nullable Style style) {
        if (style != null && style.getClickEvent() != null) {
            ClickEvent clickEvent = style.getClickEvent();
            if (clickEvent instanceof ClickEvent.ChangePage(int targetPage)) {
                if (targetPage >= 0 && targetPage < pages.size()) {
                    this.spreadIndex = (targetPage % 2 == 0) ? targetPage : targetPage - 1;
                    updateCurrentSpread();
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isInside(double mouseX, double mouseY, int rx, int ry, int rw, int rh) {
        return mouseX >= rx && mouseX < rx + rw && mouseY >= ry && mouseY < ry + rh;
    }

    private void drawPage(GuiGraphicsExtractor graphics, @Nullable BookPage page, List<FormattedCharSequence> bodyLines, int pageX, int startY, @Nullable Style hoveredStyle) {
        if (page == null) return;

        int cursorY = startY;

        if (page.title() != null) {
            graphics.text(this.font, toLine(page.title()), pageX, cursorY, TITLE_COLOR, false);
            cursorY += LINE_HEIGHT + TITLE_GAP;
        }

        if (!page.images().isEmpty()) {
            cursorY = drawImages(graphics, page.images(), pageX, cursorY) + IMAGE_BLOCK_GAP;
        }

        for (FormattedCharSequence line : bodyLines) {
            FormattedCharSequence finalLine = line;

            // Si un style de lien vers une page est survolé, on force dynamiquement son soulignement
            if (hoveredStyle != null && hoveredStyle.getClickEvent() instanceof ClickEvent.ChangePage(int page2)) {
                finalLine = (sink) -> line.accept((index, style, codePoint) -> {
                    if (style.getClickEvent() instanceof ClickEvent.ChangePage(int page1) && page1 == page2) {
                        return sink.accept(index, style.withUnderlined(true), codePoint);
                    }
                    return sink.accept(index, style, codePoint);
                });
            }

            graphics.text(this.font, finalLine, pageX, cursorY, TEXT_COLOR, false);
            cursorY += LINE_HEIGHT;
        }
    }

    private int drawImages(GuiGraphicsExtractor graphics, List<BookPage.PageImage> images, int pageX, int startY) {
        int count = images.size();
        int slotWidth = count == 2 ? (PAGE_CONTENT_WIDTH - IMAGE_GAP) / 2 : PAGE_CONTENT_WIDTH;

        int maxDrawHeight = 0;
        int[] drawW = new int[count];
        int[] drawH = new int[count];

        for (int i = 0; i < count; i++) {
            BookPage.PageImage img = images.get(i);
            float ratio = (float) img.height() / img.width();
            int w = Math.min(slotWidth, img.width());
            int h = Math.min(MAX_IMAGE_HEIGHT, Math.round(w * ratio));
            if (h == MAX_IMAGE_HEIGHT) {
                w = Math.round(h / ratio);
            }
            drawW[i] = w;
            drawH[i] = h;
            maxDrawHeight = Math.max(maxDrawHeight, h);
        }

        @SuppressWarnings("unchecked")
        List<FormattedCharSequence>[] captionLines = new List[count];
        int maxCaptionLineCount = 0;
        for (int i = 0; i < count; i++) {
            String caption = images.get(i).caption();
            if (caption != null && !caption.isEmpty()) {
                captionLines[i] = this.font.split(Component.literal(caption), slotWidth);
                maxCaptionLineCount = Math.max(maxCaptionLineCount, captionLines[i].size());
            } else {
                captionLines[i] = List.of();
            }
        }

        int cursorX = pageX;
        int captionStartY = startY + maxDrawHeight + CAPTION_GAP;

        for (int i = 0; i < count; i++) {
            BookPage.PageImage img = images.get(i);
            int w = drawW[i];
            int h = drawH[i];
            int imgX = cursorX + (slotWidth - w) / 2;
            int imgY = startY + (maxDrawHeight - h);

            if (img.itemStack() != null) {
                float scaleX = w / 16f;
                float scaleY = h / 16f;
                graphics.pose().pushMatrix();
                graphics.pose().translate(imgX, imgY);
                graphics.pose().scale(scaleX, scaleY);
                graphics.item(img.itemStack(), 0, 0);
                graphics.pose().popMatrix();
            } else {
                float scaleX = (float) w / img.width();
                float scaleY = (float) h / img.height();
                graphics.pose().pushMatrix();
                graphics.pose().translate(imgX, imgY);
                graphics.pose().scale(scaleX, scaleY);
                if (img.texture() != null)
                    graphics.blit(RenderPipelines.GUI_TEXTURED, img.texture(), 0, 0, 0, 0, img.width(), img.height(), img.width(), img.height());
                graphics.pose().popMatrix();
            }

            int lineY = captionStartY;
            for (FormattedCharSequence line : captionLines[i]) {
                int lineWidth = this.font.width(line);
                int lineX = cursorX + (slotWidth - lineWidth) / 2;
                graphics.text(this.font, line, lineX, lineY, CAPTION_COLOR, false);
                lineY += LINE_HEIGHT;
            }

            cursorX += slotWidth + IMAGE_GAP;
        }

        int captionBlockHeight = maxCaptionLineCount > 0 ? CAPTION_GAP + maxCaptionLineCount * LINE_HEIGHT : 0;
        return startY + maxDrawHeight + captionBlockHeight;
    }

    @Nullable
    private Style getStyleAt(double mouseX, double mouseY, int pageX, int startY, List<FormattedCharSequence> bodyLines) {
        int cursorY = startY;
        for (FormattedCharSequence line : bodyLines) {
            if (mouseY >= cursorY && mouseY < cursorY + LINE_HEIGHT) {
                if (mouseX >= pageX && mouseX < pageX + PAGE_CONTENT_WIDTH) {
                    int relativeX = (int) (mouseX - pageX);

                    Style[] foundStyle = new Style[]{null};
                    MutableFloat currentWidth = new MutableFloat();

                    line.accept((_, style, codePoint) -> {
                        String character = new String(Character.toChars(codePoint));
                        float charWidth = this.font.width(character);

                        if (currentWidth.addAndGet(charWidth) > relativeX) {
                            foundStyle[0] = style;
                            return false;
                        }
                        return true;
                    });
                    return foundStyle[0];
                }
            }
            cursorY += LINE_HEIGHT;
        }
        return null;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}