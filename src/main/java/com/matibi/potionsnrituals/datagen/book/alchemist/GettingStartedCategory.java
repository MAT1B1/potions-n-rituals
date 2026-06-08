package com.matibi.potionsnrituals.datagen.book.alchemist;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.SingleBookSubProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookCategoryModel;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.book.BookDisplayMode;
import com.matibi.potionsnrituals.datagen.book.alchemist.gettingstarted.AlchemicalStoneEntry;
import com.matibi.potionsnrituals.datagen.book.alchemist.gettingstarted.AlchemistCoreEntry;
import com.matibi.potionsnrituals.datagen.book.alchemist.gettingstarted.EffectsEntry;
import com.matibi.potionsnrituals.datagen.book.alchemist.gettingstarted.IntroductionEntry;
import com.matibi.potionsnrituals.datagen.book.alchemist.gettingstarted.SyringeEntry;
import com.matibi.potionsnrituals.item.ModItems;

public class GettingStartedCategory extends CategoryProvider {

    public static final String ID = "getting_started";

    public GettingStartedCategory(SingleBookSubProvider parent) {
        super(parent);
    }

    @Override
    protected void generateEntries() {
        this.add(new IntroductionEntry(this).generate());
        this.add(new AlchemistCoreEntry(this).generate());
        this.add(new AlchemicalStoneEntry(this).generate());
        this.add(new SyringeEntry(this).generate());
        this.add(new EffectsEntry(this).generate());
    }

    @Override
    protected BookCategoryModel additionalSetup(BookCategoryModel category) {
        return category.withDisplayMode(BookDisplayMode.INDEX);
    }

    @Override
    protected String categoryName() {
        return "Getting Started";
    }

    @Override
    protected String categoryDescription() {
        return "The basics of alchemy.";
    }

    @Override
    protected BookIconModel categoryIcon() {
        return BookIconModel.create(ModItems.ALCHEMIST_CORE);
    }

    @Override
    public String categoryId() {
        return ID;
    }
}
