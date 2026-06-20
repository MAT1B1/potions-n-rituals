package com.matibi.potionsnrituals.book;

import net.minecraft.network.chat.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookStructure {
    private final Component bookTitle;
    private final List<BookPage> flatPages = new ArrayList<>();
    private final Map<String, Integer> anchorToPageIndex = new HashMap<>();
    private final List<Chapter> rootChapters = new ArrayList<>();

    public BookStructure(Component bookTitle) {
        this.bookTitle = bookTitle;
    }

    public BookStructure(String bookTitle) {
        this.bookTitle = Component.literal(bookTitle);
    }

    // Crée et ajoute un chapitre principal, puis le retourne pour y chaîner des pages
    public Chapter chapter(String title) {
        Chapter chapter = new Chapter(this, null, title);
        this.rootChapters.add(chapter);
        return chapter;
    }

    // Compile toutes les pages une fois le chaînage terminé
    public BookStructure build() {
        this.flatPages.clear();
        this.anchorToPageIndex.clear();
        for (Chapter chat : rootChapters) {
            flattenChapter(chat);
        }
        return this;
    }

    private void flattenChapter(Chapter chapter) {
        for (BookPage page : chapter.pages) {
            int index = flatPages.size();
            flatPages.add(page);
            if (page.id() != null) {
                anchorToPageIndex.put(page.id(), index);
            }
        }
        for (Chapter sub : chapter.subChapters) {
            flattenChapter(sub);
        }
    }

    public Component getBookTitle() { return bookTitle; }
    public List<BookPage> getFlatPages() { return flatPages; }
    public Map<String, Integer> getAnchorMap() { return anchorToPageIndex; }

    // --- CLASSE INNER CHAPTER POUR LE CHAÎNAGE ---
    public static class Chapter {
        private final BookStructure builder;
        private final Chapter parent;
        private final String title;
        private final List<BookPage> pages = new ArrayList<>();
        private final List<Chapter> subChapters = new ArrayList<>();

        public Chapter(BookStructure builder, Chapter parent, String title) {
            this.builder = builder;
            this.parent = parent;
            this.title = title;
        }

        public Chapter addPage(BookPage page) {
            this.pages.add(page);
            return this;
        }

        public Chapter subChapter(String title) {
            Chapter sub = new Chapter(this.builder, this, title);
            this.subChapters.add(sub);
            return sub;
        }

        public Chapter endSubChapter() {
            return this.parent != null ? this.parent : this;
        }

        public Chapter chapter(String title) {
            return this.builder.chapter(title);
        }

       public BookStructure build() {
            return this.builder.build();
        }
    }
}