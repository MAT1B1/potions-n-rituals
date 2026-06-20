package com.matibi.potionsnrituals.book;

import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class BookStructure {
    private final Component bookTitle;
    private final List<BookPage> flatPages = new ArrayList<>();
    private final Map<String, Integer> anchorToPageIndex = new HashMap<>();
    private final List<Object> rootElements = new ArrayList<>();

    private boolean hasAutoSummary = false;
    private String summaryTitle = "Sommaire";

    public BookStructure(Component bookTitle) {
        this.bookTitle = bookTitle;
    }

    public BookStructure tableofcontents(String title) {
        this.hasAutoSummary = true;
        this.summaryTitle = title;
        return this;
    }

    public BookStructure page(BookPage page) {
        this.rootElements.add(page);
        return this;
    }

    public BookStructure chapter(String title, Consumer<Chapter> configuration) {
        Chapter chapter = new Chapter(title);
        configuration.accept(chapter);
        this.rootElements.add(chapter);
        return this;
    }

    public BookStructure build() {
        this.flatPages.clear();
        this.anchorToPageIndex.clear();

        if (hasAutoSummary) {
            flatPages.add(BookPage.Empty("summary_placeholder"));
        }

        for (Object element : rootElements) {
            if (element instanceof Chapter chat) {
                flattenChapter(chat, true);
            } else if (element instanceof BookPage page) {
                int index = flatPages.size();
                flatPages.add(page);
                if (page.id() != null) {
                    anchorToPageIndex.put(page.id(), index);
                }
            }
        }

        if (hasAutoSummary) {
            Component summaryBody = generateSummaryText();
            flatPages.set(0, new BookPage("summary", Component.literal(summaryTitle), List.of(), summaryBody));
        }

        return this;
    }

    private void flattenChapter(Chapter chapter, boolean isRoot) {
        if (!chapter.pages.isEmpty()) {
            String prefix = isRoot ? "chapter_" : "subchapter_";
            anchorToPageIndex.put(prefix + chapter.title, flatPages.size());
        }

        for (BookPage page : chapter.pages) {
            int index = flatPages.size();
            flatPages.add(page);
            if (page.id() != null) {
                anchorToPageIndex.put(page.id(), index);
            }
        }
        for (Chapter sub : chapter.subChapters) {
            flattenChapter(sub, false);
        }
    }

    private Component generateSummaryText() {
        var text = Component.literal("Cliquez sur une section pour débuter :\n\n");

        for (Object element : rootElements) {
            if (element instanceof Chapter chat) {
                Integer chatIndex = anchorToPageIndex.get("chapter_" + chat.title);
                if (chatIndex != null) {
                    text.append(Component.literal("§3➤ " + chat.title + "§r\n")
                            .withStyle(s -> s.withClickEvent(new ClickEvent.ChangePage(chatIndex))));
                }

                for (Chapter subChat : chat.subChapters) {
                    Integer subIndex = anchorToPageIndex.get("subchapter_" + subChat.title);
                    if (subIndex != null) {
                        text.append(Component.literal("  §7-- " + subChat.title + "§r\n")
                                .withStyle(s -> s.withClickEvent(new ClickEvent.ChangePage(subIndex))));
                    }
                }
            }
        }
        return text;
    }

    public Component getBookTitle() { return bookTitle; }
    public List<BookPage> getFlatPages() { return flatPages; }

    public static class Chapter {
        private final String title;
        private final List<BookPage> pages = new ArrayList<>();
        private final List<Chapter> subChapters = new ArrayList<>();

        public Chapter(String title) {
            this.title = title;
        }

        public Chapter page(BookPage page) {
            this.pages.add(page);
            return this;
        }

        public void subChapter(String title, Consumer<Chapter> configuration) {
            Chapter sub = new Chapter(title);
            configuration.accept(sub);
            this.subChapters.add(sub);
        }
    }
}