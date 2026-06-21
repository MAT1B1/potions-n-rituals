package com.matibi.potionsnrituals.screen;

import com.matibi.potionsnrituals.book.BookPage;
import com.matibi.potionsnrituals.datacomponent.ModDataComponents;
import com.matibi.potionsnrituals.datacomponent.PersonalBookmark;
import com.matibi.potionsnrituals.network.UpdateBookmarksPayload;
import com.matibi.potionsnrituals.util.ModUtils;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import org.apache.commons.lang3.mutable.MutableFloat;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
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
    private static final int IMAGE_GAP = 8;
    private static final int CAPTION_GAP = 2;
    private static final int IMAGE_BLOCK_GAP = 6;
    private static final int MAX_IMAGE_HEIGHT = 50;

    private static final Identifier SLOT_TEXTURE = Identifier.withDefaultNamespace("textures/gui/sprites/container/slot.png");

    private static final Identifier ARROW_BACK_TEXTURE = Identifier.withDefaultNamespace("textures/gui/sprites/widget/page_backward.png");
    private static final Identifier ARROW_BACK_HIGHLIGHTED = Identifier.withDefaultNamespace("textures/gui/sprites/widget/page_backward_highlighted.png");
    private static final Identifier ARROW_FORWARD_TEXTURE = Identifier.withDefaultNamespace("textures/gui/sprites/widget/page_forward.png");
    private static final Identifier ARROW_FORWARD_HIGHLIGHTED = Identifier.withDefaultNamespace("textures/gui/sprites/widget/page_forward_highlighted.png");
    private static final int ARROW_TEX_WIDTH = 23;
    private static final int ARROW_TEX_HEIGHT = 13;
    private static final int ARROW_BOTTOM_OFFSET = 25;

    // Constantes et textures pour les Marque-pages (Bookmarks)
    private static final Identifier BOOKMARK_TEXTURE = ModUtils.id("textures/gui/bookmark.png");
    private static final int BM_TEX_BORDER_1 = 24;
    private static final int BM_TEX_BORDER_2 = 73;

    private final ItemStack stack;
    private final InteractionHand hand;
    private final List<PersonalBookmark> personalBookmarks;
    private final float[] bookmarkAnimations = new float[6];

    private int pickAvailableColor() {
        for (int color : PersonalBookmark.ALLOWED_COLORS) {
            boolean used = personalBookmarks.stream().anyMatch(bm -> bm.color() == color);
            if (!used) return color;
        }
        return PersonalBookmark.ALLOWED_COLORS[0]; // ne devrait jamais arriver (max = PersonalBookmark.ALLOWED_COLORS.length)
    }

    private List<BookPage> paginatedPages;
    private final List<BookPage> originalPages;
    private final int[] originalToPaginatedIndex;
    private int spreadIndex;

    private BookPage currentLeft;
    private BookPage currentRight;

    private List<FormattedCharSequence> leftBodyLines;
    private List<FormattedCharSequence> rightBodyLines;

    private int leftTextStartY;
    private int rightTextStartY;

    public CustomBookScreen(Component title, List<BookPage> pages, ItemStack stack, InteractionHand hand) {
        super(title);
        if (pages == null || pages.isEmpty()) {
            throw new IllegalArgumentException("pages ne peut pas être vide");
        }
        this.originalPages = List.copyOf(pages);
        this.originalToPaginatedIndex = new int[this.originalPages.size()];
        this.spreadIndex = 0;
        this.stack = stack;
        this.hand = hand;
        this.personalBookmarks = new ArrayList<>(stack.getOrDefault(ModDataComponents.PERSONAL_BOOKMARKS, List.of()));
    }

    private void syncBookmarksToStack() {
        List<PersonalBookmark> snapshot = List.copyOf(personalBookmarks);
        this.stack.set(ModDataComponents.PERSONAL_BOOKMARKS, snapshot); // feedback immédiat côté client
        ClientPlayNetworking.send(new UpdateBookmarksPayload(this.hand, snapshot)); // copie autoritaire -> sauvegardée
    }

    @Override
    protected void init() {
        super.init();
        this.paginatedPages = computePagination();
        updateCurrentSpread();
    }

    private List<BookPage> computePagination() {
        List<BookPage> result = new java.util.ArrayList<>();
        int maxTotalHeight = BG_HEIGHT - (TEXT_MARGIN_Y * 2) - 10;

        for (int i = 0; i < this.originalPages.size(); i++) {
            BookPage originalPage = this.originalPages.get(i);
            this.originalToPaginatedIndex[i] = result.size();

            Component text = getPageBodyText(originalPage);
            List<FormattedCharSequence> allLines = text != null ? this.font.split(text, PAGE_CONTENT_WIDTH) : List.of();

            int startY = TEXT_MARGIN_Y;
            int textStartY = calculateTextStartY(originalPage, startY);
            int occupiedHeight = textStartY - startY;
            int availableHeightForFirstPage = maxTotalHeight - occupiedHeight;

            int linesForFirstPage = Math.max(0, availableHeightForFirstPage / LINE_HEIGHT);

            if (allLines.size() <= linesForFirstPage) {
                result.add(originalPage);
            } else {
                List<FormattedCharSequence> firstPageLines = allLines.subList(0, linesForFirstPage);
                List<FormattedCharSequence> remainingLines = allLines.subList(linesForFirstPage, allLines.size());

                result.add(reconstructPageWithLines(originalPage, firstPageLines));

                int linesPerPageLater = maxTotalHeight / LINE_HEIGHT;

                int index = 0;
                while (index < remainingLines.size()) {
                    int end = Math.min(index + linesPerPageLater, remainingLines.size());
                    List<FormattedCharSequence> subLines = remainingLines.subList(index, end);
                    Component remainingTextComponent = linesToComponent(subLines);
                    result.add(new BookPage.TextPage(null, null, remainingTextComponent));
                    index = end;
                }
            }
        }
        return result;
    }

    private @Nullable Component getPageBodyText(BookPage page) {
        return switch (page) {
            case BookPage.TextPage t -> t.bodyText();
            case BookPage.ImagePage i -> i.bodyText();
            case BookPage.RecipePage r -> r.bodyText();
            case BookPage.EmptyPage _ -> null;
        };
    }

    private BookPage reconstructPageWithLines(BookPage page, List<FormattedCharSequence> lines) {
        Component newText = linesToComponent(lines);
        return switch (page) {
            case BookPage.TextPage t -> new BookPage.TextPage(t.id(), t.title(), newText);
            case BookPage.ImagePage i -> new BookPage.ImagePage(i.id(), i.title(), i.images(), newText);
            case BookPage.RecipePage r -> new BookPage.RecipePage(r.id(), r.title(), r.recipe(), newText);
            case BookPage.EmptyPage e -> e;
        };
    }

    private Component linesToComponent(List<FormattedCharSequence> lines) {
        MutableComponent base = Component.empty();
        for (int i = 0; i < lines.size(); i++) {
            lines.get(i).accept((_, style, codePoint) -> {
                base.append(Component.literal(new String(Character.toChars(codePoint))).withStyle(style));
                return true;
            });
            if (i < lines.size() - 1) {
                base.append("\n");
            }
        }
        return base;
    }

    private void updateCurrentSpread() {
        this.currentLeft = paginatedPages.get(spreadIndex);
        this.currentRight = (spreadIndex + 1 < paginatedPages.size()) ? paginatedPages.get(spreadIndex + 1) : null;

        Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.BOOK_PAGE_TURN, 1.0F));

        this.leftBodyLines = wrapBody(currentLeft);
        this.rightBodyLines = wrapBody(currentRight);
    }

    private List<FormattedCharSequence> wrapBody(@Nullable BookPage page) {
        if (page == null) return List.of();

        Component text = switch (page) {
            case BookPage.TextPage t -> t.bodyText();
            case BookPage.ImagePage i -> i.bodyText();
            case BookPage.RecipePage r -> r.bodyText();
            case BookPage.EmptyPage _ -> null;
        };

        if (text == null) return List.of();
        return this.font.split(text, PAGE_CONTENT_WIDTH);
    }

    private boolean hasPrevSpread() {
        return spreadIndex - 2 >= 0;
    }

    private boolean hasNextSpread() {
        return spreadIndex + 2 < paginatedPages.size();
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

        // --- 1. DESSIN DES MARQUE-PAGES GAUCHES (Derrière le livre) ---
        int leftVisibleRest = 4;
        int leftVisibleHover = 14;

        // ==========================================
        // A. SOMMAIRE (Bord Gauche Haut - Rouge)
        // ==========================================
        int leftBmhY = y + 15;
        int leftRestX = x - leftVisibleRest;

        int leftBoxX = leftRestX + (BM_TEX_BORDER_1 - BM_TEX_BORDER_2) / 2;
        int leftBoxY = leftBmhY + (BM_TEX_BORDER_1 + BM_TEX_BORDER_2) / 2 - BM_TEX_BORDER_1;

        int leftBmHoverX = leftBoxX - (int) bookmarkAnimations[0];
        boolean hoverLeftBm = isInside(mouseX, mouseY, leftBmHoverX, leftBoxY, x - leftBmHoverX, BM_TEX_BORDER_1);

        float targetExtLeft = hoverLeftBm ? (leftVisibleHover - leftVisibleRest) : 0f;
        bookmarkAnimations[0] = Mth.lerp(a * 0.22f, bookmarkAnimations[0], targetExtLeft);
        int finalLeftX = leftRestX - (int) bookmarkAnimations[0];

        graphics.pose().pushMatrix();
        graphics.pose().translate(finalLeftX, leftBmhY);
        graphics.pose().translate(BM_TEX_BORDER_1 / 2f, BM_TEX_BORDER_2 / 2f);
        graphics.pose().rotate((float) Math.toRadians(-90));
        graphics.pose().translate(-BM_TEX_BORDER_1 / 2f, -BM_TEX_BORDER_2 / 2f);
        graphics.blit(RenderPipelines.GUI_TEXTURED, BOOKMARK_TEXTURE, 0, 0, 0, 0, BM_TEX_BORDER_1, BM_TEX_BORDER_2, BM_TEX_BORDER_1, BM_TEX_BORDER_2, 0xFFFF5555);
        graphics.pose().popMatrix();


        // ==========================================
        // B. BOUTON AJOUTER "+" (Bord Gauche Bas - Vert)
        // ==========================================
        int addBmhY = y + 92;
        int addRestX = x - leftVisibleRest;

        int addBoxX = addRestX + (BM_TEX_BORDER_1 - BM_TEX_BORDER_2) / 2;
        int addBoxY = addBmhY + (BM_TEX_BORDER_2 - BM_TEX_BORDER_1) / 2;

        int addBmHoverX = addBoxX - (int) bookmarkAnimations[1];
        boolean hoverAddBm = isInside(mouseX, mouseY, addBmHoverX, addBoxY, x - addBmHoverX, BM_TEX_BORDER_1);

        float targetExtAdd = hoverAddBm ? (leftVisibleHover - leftVisibleRest) : 0f;
        bookmarkAnimations[1] = Mth.lerp(a * 0.22f, bookmarkAnimations[1], targetExtAdd);
        int finalAddX = addRestX - (int) bookmarkAnimations[1];

        graphics.pose().pushMatrix();
        graphics.pose().translate(finalAddX, addBmhY);
        graphics.pose().translate(BM_TEX_BORDER_1 / 2f, BM_TEX_BORDER_2 / 2f);
        graphics.pose().rotate((float) Math.toRadians(-90));
        graphics.pose().translate(-BM_TEX_BORDER_1 / 2f, -BM_TEX_BORDER_2 / 2f);
        graphics.blit(RenderPipelines.GUI_TEXTURED, BOOKMARK_TEXTURE, 0, 0, 0, 0, BM_TEX_BORDER_1, BM_TEX_BORDER_2, BM_TEX_BORDER_1, BM_TEX_BORDER_2, 0xFF55FF55);
        graphics.pose().popMatrix();


        // --- 2. DESSIN DES MARQUE-PAGES DU DESSUS (Derrière le livre) ---
        int topVisibleRest = 24;  // Nombre de pixels visibles au repos
        int topVisibleHover = 36; // Nombre de pixels visibles au survol

        int personalCount = personalBookmarks.size();
        int bmStartX = x + 35;
        int bmGapX = 30;
        for (int i = 0; i < personalCount; i++) {
            PersonalBookmark bookmark = personalBookmarks.get(i);
            int bmX = bmStartX + (i * bmGapX);
            int bmRestY = y - topVisibleRest;
            int bmHoverY = bmRestY - (int) bookmarkAnimations[2 + i];
            boolean hoverPerso = isInside(mouseX, mouseY, bmX, bmHoverY, BM_TEX_BORDER_1, y - bmHoverY);
            boolean onThisPage = bookmark.pageIndex() == this.spreadIndex;
            boolean showDelete = hoverPerso && onThisPage;

            float targetExtPerso = hoverPerso ? (topVisibleHover - topVisibleRest) : 0f;
            bookmarkBookmarksAnimation(i, targetExtPerso, a);
            int finalPersoY = bmRestY - (int)bookmarkAnimations[2 + i];

            int bmColor = showDelete ? 0xFFDD3333 : bookmark.color();
            graphics.blit(RenderPipelines.GUI_TEXTURED, BOOKMARK_TEXTURE, bmX, finalPersoY, 0, 0, BM_TEX_BORDER_1, BM_TEX_BORDER_2, BM_TEX_BORDER_1, BM_TEX_BORDER_2, bmColor);

            if (showDelete) {
                int visibleHeight = topVisibleRest + (int) bookmarkAnimations[2 + i];
                int crossX = bmX + (BM_TEX_BORDER_1 - this.font.width("✕")) / 2 + 1;
                int crossY = y - (visibleHeight / 2) - 4;
                graphics.text(this.font, "✕", crossX, crossY, 0xFFFFFFFF, false);
            }
        }

        // --- 3. DESSIN DE LA TEXTURE DU LIVRE PRINCIPAL (Par-dessus) ---
        graphics.blit(RenderPipelines.GUI_TEXTURED, BOOK_TEXTURE, x, y, 0, 0, TEXTURE_WIDTH, TEXTURE_HEIGHT, TEXTURE_WIDTH, TEXTURE_HEIGHT);

        // --- 4. TEXTE DU BOUTON "+" ---
        int plusX = finalAddX - 10;
        int plusY = addBmhY + (BM_TEX_BORDER_2 / 2) - 3;
        graphics.text(this.font, "+", plusX, plusY, 0xFFFFFFFF, true);

        // Rendu des pages internes
        int leftX = x + OUTER_MARGIN;
        this.leftTextStartY = calculateTextStartY(currentLeft, y + TEXT_MARGIN_Y);
        Style hoveredStyleLeft = getStyleAt(mouseX, mouseY, leftX, leftTextStartY, leftBodyLines);
        drawPage(graphics, currentLeft, leftBodyLines, leftX, y + TEXT_MARGIN_Y, hoveredStyleLeft);

        int rightX = x + HALF_WIDTH + INNER_MARGIN;
        this.rightTextStartY = calculateTextStartY(currentRight, y + TEXT_MARGIN_Y);
        Style hoveredStyleRight = getStyleAt(mouseX, mouseY, rightX, rightTextStartY, rightBodyLines);
        drawPage(graphics, currentRight, rightBodyLines, rightX, y + TEXT_MARGIN_Y, hoveredStyleRight);

        drawArrows(graphics, x, y, mouseX, mouseY);
    }

    private void bookmarkBookmarksAnimation(int i, float target, float a) {
        bookmarkAnimations[2 + i] = Mth.lerp(a * 0.22f, bookmarkAnimations[2 + i], target);
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

        // INTERCEPTION CLIC : Marque-page Sommaire (Gauche Haut)
        int leftVisibleRest = 4;
        int leftBmhY = y + 15;
        int leftRestX = x - leftVisibleRest;
        int leftBoxX = leftRestX + (BM_TEX_BORDER_1 - BM_TEX_BORDER_2) / 2;
        int leftBoxY = leftBmhY + (BM_TEX_BORDER_1 + BM_TEX_BORDER_2) / 2 - BM_TEX_BORDER_1;

        int leftBmClickX = leftBoxX - (int) bookmarkAnimations[0];
        if (isInside(mouseX, mouseY, leftBmClickX, leftBoxY, x - leftBmClickX, BM_TEX_BORDER_1)) {
            jumpToPage(0);
            return true;
        }

        // INTERCEPTION CLIC : Marque-page Création / "+" (Gauche Bas)
        int addBmhY = y + 92;
        int addRestX = x - leftVisibleRest;
        int addBoxX = addRestX + (BM_TEX_BORDER_1 - BM_TEX_BORDER_2) / 2;
        int addBoxY = addBmhY + (BM_TEX_BORDER_2 - BM_TEX_BORDER_1) / 2;
        int addBmClickX = addBoxX - (int) bookmarkAnimations[1];
        if (isInside(mouseX, mouseY, addBmClickX, addBoxY, x - addBmClickX, BM_TEX_BORDER_1)) {
            if (personalBookmarks.size() < PersonalBookmark.ALLOWED_COLORS.length
                    && personalBookmarks.stream().noneMatch(bm -> bm.pageIndex() == this.spreadIndex)) {
                personalBookmarks.add(new PersonalBookmark(this.spreadIndex, pickAvailableColor()));
                syncBookmarksToStack();
            }
            return true;
        }

        // INTERCEPTION CLIC : Marque-pages Personnels (Haut)
        int personalCount = personalBookmarks.size();
        int bmStartX = x + 35;
        int bmGapX = 30;
        int topVisibleRest = 24;
        int bmRestY = y - topVisibleRest;
        for (int i = 0; i < personalCount; i++) {
            int bmX = bmStartX + (i * bmGapX);
            int bmClickY = bmRestY - (int) bookmarkAnimations[2 + i];
            if (isInside(mouseX, mouseY, bmX, bmClickY, BM_TEX_BORDER_1, y - bmClickY)) {
                PersonalBookmark bookmark = personalBookmarks.get(i);
                int targetPage = bookmark.pageIndex();
                if (targetPage == this.spreadIndex)
                    removePersonalBookmark(i);
                else
                    jumpToPage(targetPage);
                return true;
            }
        }

        Style styleLeft = getStyleAt(mouseX, mouseY, x + OUTER_MARGIN, leftTextStartY, leftBodyLines);
        if (handleStyleClick(styleLeft)) return true;

        if (currentRight != null) {
            Style styleRight = getStyleAt(mouseX, mouseY, x + HALF_WIDTH + INNER_MARGIN, rightTextStartY, rightBodyLines);
            if (handleStyleClick(styleRight)) return true;
        }

        return super.mouseClicked(event, doubleClick);
    }

    private void jumpToPage(int targetPage) {
        if (targetPage >= 0 && targetPage < paginatedPages.size()) {
            this.spreadIndex = (targetPage % 2 == 0) ? targetPage : targetPage - 1;
            updateCurrentSpread();
        }
    }

    private void removePersonalBookmark(int index) {
        personalBookmarks.remove(index);
        for (int k = index; k < personalBookmarks.size(); k++) {
            bookmarkAnimations[2 + k] = bookmarkAnimations[2 + k + 1];
        }
        bookmarkAnimations[2 + personalBookmarks.size()] = 0f;
        syncBookmarksToStack();
    }

    private boolean handleStyleClick(@Nullable Style style) {
        if (style != null && style.getClickEvent() != null) {
            ClickEvent clickEvent = style.getClickEvent();
            if (clickEvent instanceof ClickEvent.ChangePage(int targetPage)) {
                if (targetPage >= 0 && targetPage < originalToPaginatedIndex.length) {
                    int realPage = originalToPaginatedIndex[targetPage];
                    this.spreadIndex = (realPage % 2 == 0) ? realPage : realPage - 1;
                    updateCurrentSpread();
                    return true;
                }
            }
        }
        return false;
    }

    private int calculateTextStartY(@Nullable BookPage page, int startY) {
        if (page == null) return startY;
        int cursorY = startY;

        Component title = page.title();
        if (title != null) {
            int titleLines = this.font.split(title, PAGE_CONTENT_WIDTH).size();
            cursorY += (titleLines * LINE_HEIGHT) + TITLE_GAP;
        }

        cursorY = switch (page) {
            case BookPage.ImagePage imgPage -> {
                if (!imgPage.images().isEmpty()) {
                    yield cursorY + simulateDrawImages(imgPage.images()) + IMAGE_BLOCK_GAP;
                }
                yield cursorY;
            }
            case BookPage.RecipePage recPage -> {
                int recipeHeight;
                switch (recPage.recipe().type()) {
                    case CRAFTING -> recipeHeight = 56;
                    case FURNACE ->  recipeHeight = 22;
                    case BREWING -> recipeHeight = 50;
                    default -> recipeHeight = 40;
                }
                yield cursorY + recipeHeight + 12;
            }
            default -> cursorY;
        };

        return cursorY;
    }

    private int simulateDrawImages(List<BookPage.Image> images) {
        int count = images.size();
        int slotWidth = count == 2 ? (PAGE_CONTENT_WIDTH - IMAGE_GAP) / 2 : PAGE_CONTENT_WIDTH;
        int maxDrawHeight = 0;
        int maxCaptionLineCount = 0;

        for (BookPage.Image img : images) {
            float ratio = (float) img.height() / img.width();
            int w = Math.min(slotWidth, img.width());
            int h = Math.min(MAX_IMAGE_HEIGHT, Math.round(w * ratio));
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

    private boolean isInside(double mouseX, double mouseY, int rx, int ry, int rw, int rh) {
        return mouseX >= rx && mouseX < rx + rw && mouseY >= ry && mouseY < ry + rh;
    }

    private void drawPage(GuiGraphicsExtractor graphics, @Nullable BookPage page, List<FormattedCharSequence> bodyLines, int pageX, int startY, @Nullable Style hoveredStyle) {
        if (page == null) return;

        int cursorY = startY;

        Component title = page.title();
        if (title != null) {
            List<FormattedCharSequence> titleLines = this.font.split(title, PAGE_CONTENT_WIDTH);
            for (FormattedCharSequence titleLine : titleLines) {
                graphics.text(this.font, titleLine, pageX, cursorY, TITLE_COLOR, false);
                cursorY += LINE_HEIGHT;
            }
            cursorY += TITLE_GAP;
        }

        switch (page) {
            case BookPage.ImagePage imgPage -> {
                if (!imgPage.images().isEmpty())
                    drawImages(graphics, imgPage.images(), pageX, cursorY);
            }
            case BookPage.RecipePage recPage ->
                    drawRecipeLayout(graphics, recPage.recipe(), pageX, cursorY);
            default -> {}
        }

        cursorY = calculateTextStartY(page, startY);

        for (FormattedCharSequence line : bodyLines) {
            FormattedCharSequence finalLine = getSequence(hoveredStyle, line);

            if (cursorY < startY + BG_HEIGHT - TEXT_MARGIN_Y - 10) {
                graphics.text(this.font, finalLine, pageX, cursorY, TEXT_COLOR, false);
                cursorY += LINE_HEIGHT;
            }
        }
    }

    private void drawRecipeLayout(GuiGraphicsExtractor graphics, BookPage.Recipe recipe, int pageX, int startY) {
        int arrowColor = 0xFF8B8B8B;
        int slotSize = 20;
        float bigScale = 1.3f;
        float scale = slotSize / 18.0f;

        int itemOffset = Math.round(scale);
        int gridWidth = 3 * slotSize;
        int actualBigSize = Math.round(18 * scale * bigScale);
        int bigItemOffset = Math.round(scale * bigScale);
        int arrowWidth = 6;

        switch (recipe.type()) {
            case CRAFTING -> {
                int totalWidth = gridWidth + 8 + arrowWidth + 8 + actualBigSize;
                int startX = pageX + 4 + (PAGE_CONTENT_WIDTH - totalWidth) / 2;

                for (int row = 0; row < 3; row++) {
                    for (int col = 0; col < 3; col++) {
                        int index = row * 3 + col;
                        int slotX = startX + (col * slotSize);
                        int slotY = startY + (row * slotSize);

                        graphics.pose().pushMatrix();
                        graphics.pose().translate(slotX, slotY);
                        graphics.pose().scale(scale, scale);
                        graphics.blit(RenderPipelines.GUI_TEXTURED, SLOT_TEXTURE, 0, 0, 0, 0, 18, 18, 18, 18);
                        graphics.pose().popMatrix();

                        if (index < recipe.inputs().size()) {
                            ItemStack stack = recipe.inputs().get(index);
                            if (stack != null && !stack.isEmpty()) {
                                graphics.pose().pushMatrix();
                                graphics.pose().translate(slotX + itemOffset, slotY + itemOffset);
                                graphics.pose().scale(scale, scale);
                                graphics.item(stack, 0, 0);
                                graphics.pose().popMatrix();
                            }
                        }
                    }
                }

                int arrowX = startX + gridWidth + 8;
                int arrowY = startY + slotSize + (slotSize / 2) - 5;

                graphics.text(this.font, "➔", arrowX, arrowY, arrowColor, false);

                int outputX = arrowX + 14;
                int outputY = startY + slotSize - ((actualBigSize - slotSize) / 2);

                graphics.pose().pushMatrix();
                graphics.pose().translate(outputX, outputY);
                graphics.pose().scale(scale * bigScale, scale * bigScale);
                graphics.blit(RenderPipelines.GUI_TEXTURED, SLOT_TEXTURE, 0, 0, 0, 0, 18, 18, 18, 18);
                graphics.pose().popMatrix();

                graphics.pose().pushMatrix();
                graphics.pose().translate(outputX + bigItemOffset, outputY + bigItemOffset);
                graphics.pose().scale(scale * bigScale, scale * bigScale);
                graphics.item(recipe.output(), 0, 0);
                graphics.pose().popMatrix();
            }
            case FURNACE -> {
                int totalWidth = slotSize + arrowWidth + 8 + actualBigSize + 8 + arrowWidth + slotSize;
                int startX = pageX + (PAGE_CONTENT_WIDTH - totalWidth) / 2 - 7;
                int centerY = startY + 10;
                int slotY = centerY - (slotSize / 2);

                graphics.pose().pushMatrix();
                graphics.pose().translate(startX, slotY);
                graphics.pose().scale(scale * bigScale, scale * bigScale);
                graphics.blit(RenderPipelines.GUI_TEXTURED, SLOT_TEXTURE, 0, 0, 0, 0, 18, 18, 18, 18);
                graphics.pose().popMatrix();

                if (!recipe.inputs().isEmpty() && !recipe.inputs().getFirst().isEmpty()) {
                    graphics.pose().pushMatrix();
                    graphics.pose().translate(startX + itemOffset, slotY + itemOffset);
                    graphics.pose().scale(scale * bigScale, scale * bigScale);
                    graphics.item(recipe.inputs().getFirst(), 0, 0);
                    graphics.pose().popMatrix();
                }

                int leftArrowX = startX + slotSize + 11;
                int arrowTextY = centerY + 1;
                graphics.text(this.font, "➔", leftArrowX, arrowTextY, arrowColor, false);

                int furnaceX = leftArrowX + arrowWidth + 6;
                int furnaceY = centerY - (slotSize / 2);

                graphics.pose().pushMatrix();
                graphics.pose().translate(furnaceX, furnaceY);
                graphics.pose().scale(scale * bigScale, scale * bigScale);
                graphics.item(new ItemStack(Blocks.FURNACE), 0, 0);
                graphics.pose().popMatrix();

                int rightArrowX = furnaceX + actualBigSize + 2;
                graphics.text(this.font, "➔", rightArrowX, arrowTextY, arrowColor, false);

                int outputX = rightArrowX + arrowWidth + 7;
                int outputY = centerY - (slotSize / 2);

                graphics.pose().pushMatrix();
                graphics.pose().translate(outputX, outputY);
                graphics.pose().scale(scale * bigScale, scale * bigScale);
                graphics.blit(RenderPipelines.GUI_TEXTURED, SLOT_TEXTURE, 0, 0, 0, 0, 18, 18, 18, 18);
                graphics.pose().popMatrix();

                graphics.pose().pushMatrix();
                graphics.pose().translate(outputX + itemOffset, outputY + itemOffset);
                graphics.pose().scale(scale * bigScale, scale * bigScale);
                graphics.item(recipe.output(), 0, 0);
                graphics.pose().popMatrix();
            }
            case BREWING -> {
                int totalWidth = slotSize + arrowWidth + 8 + actualBigSize + 8 + arrowWidth + slotSize;
                int startX = pageX + (PAGE_CONTENT_WIDTH - totalWidth) / 2;

                int centerY = startY + 25;
                int arrowTextY = centerY - 2;

                int input1Y = centerY - actualBigSize - 2;
                int input2Y = centerY + 2;

                graphics.pose().pushMatrix();
                graphics.pose().translate(startX, input1Y);
                graphics.pose().scale(scale * bigScale, scale * bigScale);
                graphics.blit(RenderPipelines.GUI_TEXTURED, SLOT_TEXTURE, 0, 0, 0, 0, 18, 18, 18, 18);
                graphics.pose().popMatrix();

                if (!recipe.inputs().isEmpty() && !recipe.inputs().getFirst().isEmpty()) {
                    graphics.pose().pushMatrix();
                    graphics.pose().translate(startX + itemOffset, input1Y + itemOffset);
                    graphics.pose().scale(scale * bigScale, scale * bigScale);
                    graphics.item(recipe.inputs().getFirst(), 0, 0);
                    graphics.pose().popMatrix();
                }

                graphics.pose().pushMatrix();
                graphics.pose().translate(startX, input2Y);
                graphics.pose().scale(scale * bigScale, scale * bigScale);
                graphics.blit(RenderPipelines.GUI_TEXTURED, SLOT_TEXTURE, 0, 0, 0, 0, 18, 18, 18, 18);
                graphics.pose().popMatrix();

                if (recipe.inputs().size() > 1 && !recipe.inputs().get(1).isEmpty()) {
                    graphics.pose().pushMatrix();
                    graphics.pose().translate(startX + itemOffset, input2Y + itemOffset);
                    graphics.pose().scale(scale * bigScale, scale * bigScale);
                    graphics.item(recipe.inputs().get(1), 0, 0);
                    graphics.pose().popMatrix();
                }

                int leftArrowX = startX + slotSize + 12;
                graphics.text(this.font, "➔", leftArrowX, arrowTextY, arrowColor, false);

                int brewingX = leftArrowX + arrowWidth + 2;
                int brewingY = centerY - (actualBigSize / 2);

                graphics.pose().pushMatrix();
                graphics.pose().translate(brewingX, brewingY);
                graphics.pose().scale(scale * bigScale, scale * bigScale);
                graphics.item(new ItemStack(Blocks.BREWING_STAND), 0, 0);
                graphics.pose().popMatrix();

                int rightArrowX = brewingX + actualBigSize - 1;
                graphics.text(this.font, "➔", rightArrowX, arrowTextY, arrowColor, false);

                int outputX = rightArrowX + arrowWidth + 7;
                int outputY = centerY - (actualBigSize / 2);

                graphics.pose().pushMatrix();
                graphics.pose().translate(outputX, outputY);
                graphics.pose().scale(scale * bigScale, scale * bigScale);
                graphics.blit(RenderPipelines.GUI_TEXTURED, SLOT_TEXTURE, 0, 0, 0, 0, 18, 18, 18, 18);
                graphics.pose().popMatrix();

                graphics.pose().pushMatrix();
                graphics.pose().translate(outputX + bigItemOffset, outputY + bigItemOffset);
                graphics.pose().scale(scale * bigScale, scale * bigScale);
                graphics.item(recipe.output(), 0, 0);
                graphics.pose().popMatrix();
            }
        }
    }

    private void drawImages(GuiGraphicsExtractor graphics, List<BookPage.Image> images, int pageX, int startY) {
        int count = images.size();
        int slotWidth = count == 2 ? (PAGE_CONTENT_WIDTH - IMAGE_GAP) / 2 : PAGE_CONTENT_WIDTH;

        int maxDrawHeight = 0;
        int[] drawW = new int[count];
        int[] drawH = new int[count];

        for (int i = 0; i < count; i++) {
            BookPage.Image img = images.get(i);
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

        int cursorX = pageX - (count - 1) * IMAGE_GAP / 2;
        int captionStartY = startY + maxDrawHeight + CAPTION_GAP;

        for (int i = 0; i < count; i++) {
            BookPage.Image img = images.get(i);
            int w = drawW[i];
            int h = drawH[i];
            int imgX = cursorX + (slotWidth - w) / 2;
            int imgY = startY + (maxDrawHeight - h);

            if ((img.bgColor() & 0xFF000000) != 0) {
                graphics.fill(imgX - 1, imgY, imgX + w + 1, imgY + h, img.bgColor());
                graphics.fill(imgX, imgY - 1, imgX + w, imgY, img.bgColor());
                graphics.fill(imgX, imgY + h, imgX + w, imgY + h + 1, img.bgColor());
            }

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

    private static FormattedCharSequence getSequence(@Nullable Style hoveredStyle, FormattedCharSequence line) {
        FormattedCharSequence finalLine = line;

        if (hoveredStyle != null && hoveredStyle.getClickEvent() instanceof ClickEvent.ChangePage(int page2)) {
            finalLine = (sink) -> line.accept((index, style, codePoint) -> {
                if (style.getClickEvent() instanceof ClickEvent.ChangePage(int page1) && page1 == page2)
                    return sink.accept(index, style.withUnderlined(true), codePoint);
                return sink.accept(index, style, codePoint);
            });
        }
        return finalLine;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}