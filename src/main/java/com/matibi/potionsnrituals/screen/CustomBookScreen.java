package com.matibi.potionsnrituals.screen;

import com.matibi.potionsnrituals.util.ModUtils;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.item.ItemStack;
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
    private int spreadIndex; // index de la page GAUCHE du spread courant, toujours pair

    private BookPage currentLeft;
    private BookPage currentRight; // peut être null si nombre impair sur dernier spread

    private List<FormattedCharSequence> leftBodyLines;
    private List<FormattedCharSequence> rightBodyLines;

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

        drawPage(graphics, currentLeft, leftBodyLines, x + OUTER_MARGIN, y + TEXT_MARGIN_Y);
        drawPage(graphics, currentRight, rightBodyLines, x + HALF_WIDTH + INNER_MARGIN, y + TEXT_MARGIN_Y);

        drawArrows(graphics, x, y, mouseX, mouseY);
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
        return super.mouseClicked(event, doubleClick);
    }

    private boolean isInside(double mouseX, double mouseY, int rx, int ry, int rw, int rh) {
        return mouseX >= rx && mouseX < rx + rw && mouseY >= ry && mouseY < ry + rh;
    }

    private void drawPage(GuiGraphicsExtractor graphics, @Nullable BookPage page, List<FormattedCharSequence> bodyLines, int pageX, int startY) {
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
            graphics.text(this.font, line, pageX, cursorY, TEXT_COLOR, false);
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

        // wrap légende sur largeur du SLOT (pas de l'image) — autorise plusieurs lignes
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
                // item vanilla = icône native 16x16, scale pour atteindre taille voulue (w·h)
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
                if (img.texture != null)
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

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    public record BookPage(@Nullable Component title, List<PageImage> images, @Nullable Component bodyText) {

        public BookPage {
            if (images == null) images = List.of();
            if (images.size() > 2) {
                throw new IllegalArgumentException("Max 2 images par page, reçu: " + images.size());
            }
            images = List.copyOf(images);
        }

        public record PageImage(@Nullable Identifier texture, @Nullable ItemStack itemStack, int width, int height, @Nullable String caption) {

            public PageImage {
                if ((texture == null) == (itemStack == null)) {
                    throw new IllegalArgumentException("PageImage doit avoir soit texture, soit itemStack — pas les deux, pas aucun");
                }
            }

            public static PageImage fromTexture(Identifier texture, int width, int height, @Nullable String caption) {
                return new PageImage(texture, null, width, height, caption);
            }

            public static PageImage fromItem(ItemStack stack, @Nullable String caption) {
                return new PageImage(null, stack, 64, 64, caption);
            }
        }
    }
}