package com.matibi.potionsnrituals.datagen.book;

import com.klikli_dev.modonomicon.api.datagen.LanguageProviderCache;
import com.klikli_dev.modonomicon.api.datagen.SingleBookSubProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookModel;
import com.klikli_dev.modonomicon.book.BookDisplayMode;
import com.matibi.potionsnrituals.datagen.book.alchemist.GettingStartedCategory;
import net.minecraft.resources.Identifier;

public class AlchemistBookProvider extends SingleBookSubProvider {

    public static final String ID = "alchemist_book";

    public AlchemistBookProvider(String modId, LanguageProviderCache enUs, LanguageProviderCache frFr) {
        super(ID, modId, enUs, java.util.Map.of("fr_fr", frFr));
    }

    @Override
    protected BookModel additionalSetup(BookModel book) {
        return book.withDisplayMode(BookDisplayMode.INDEX)
                .withAutoAddReadConditions(false)
                .withModel(Identifier.parse("modonomicon:modonomicon_purple"))
                .withGenerateBookItem(true)
                .withTheme(theme -> theme.withLayout(layout -> layout
                        .withBookTextOffsetX(5)
                        .withBookTextOffsetY(0)
                        .withBookTextOffsetWidth(-5)));
    }

    @Override
    protected void registerDefaultMacros() {
    }

    @Override
    protected void generateCategories() {
        this.add(new GettingStartedCategory(this).generate());
    }

    @Override
    protected String bookName() {
        return "Alchemist's Guide";
    }

    @Override
    protected String bookTooltip() {
        return "A guide to the arcane arts of alchemy.";
    }
}
