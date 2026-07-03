package com.matibi.potionsnrituals.book;

import net.minecraft.ChatFormatting;
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

    private final List<Consumer<List<Object>>> rootBuilders = new ArrayList<>();

    private boolean hasAutoSummary = false;
    private String summaryTitle = "Sommaire";

    public BookStructure(String bookTitle) {
        this.bookTitle = Component.translatable(bookTitle);
    }

    public BookStructure tableOfContents(String title) {
        this.hasAutoSummary = true;
        this.summaryTitle = title;
        return this;
    }

    public BookStructure page(BookPage page) {
        this.rootBuilders.add(elements -> elements.add(page));
        return this;
    }

    public BookStructure chapter(String title, Consumer<Chapter> configuration) {
        this.rootBuilders.add(elements -> {
            Chapter chapter = new Chapter(title);
            configuration.accept(chapter);
            elements.add(chapter);
        });
        return this;
    }

    public BookStructure build() {
        this.flatPages.clear();
        this.anchorToPageIndex.clear();

        List<Object> rootElements = new ArrayList<>();
        if (hasAutoSummary) {
            flatPages.add(new BookPage.EmptyPage());
        }

        for (Consumer<List<Object>> builder : rootBuilders) {
            builder.accept(rootElements);
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
            Component summaryBody = generateSummaryText(rootElements);
            flatPages.set(0, new BookPage.TextPage("summary", Component.translatable(summaryTitle), summaryBody));
        }

        return this;
    }

    private void flattenChapter(Chapter chapter, boolean isRoot) {
        String prefix = isRoot ? "chapter_" : "subchapter_";
        anchorToPageIndex.put(prefix + chapter.title, flatPages.size());

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

    private Component generateSummaryText(List<Object> rootElements) {
        var text = Component.translatable("book.potions-n-rituals.basic.toc2");

        for (Object element : rootElements) {
            if (element instanceof Chapter chat) {
                Integer chatIndex = anchorToPageIndex.get("chapter_" + chat.title);
                if (chatIndex != null) {
                    text.append(Component.translatable(chat.title)
                            .withStyle(ChatFormatting.DARK_RED)
                            .append("\n")
                            .withStyle(s -> s.withClickEvent(new ClickEvent.ChangePage(chatIndex))));
                }

                for (Chapter subChat : chat.subChapters) {
                    Integer subIndex = anchorToPageIndex.get("subchapter_" + subChat.title);
                    if (subIndex != null) {
                        text.append(Component.literal("- ")
                                .append(Component.translatable(subChat.title))
                                .withStyle(ChatFormatting.DARK_GRAY)
                                .append("\n")
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

        public Chapter subChapter(String title, Consumer<Chapter> configuration) {
            Chapter sub = new Chapter(title);
            configuration.accept(sub);
            this.subChapters.add(sub);
            return this;
        }
    }
}