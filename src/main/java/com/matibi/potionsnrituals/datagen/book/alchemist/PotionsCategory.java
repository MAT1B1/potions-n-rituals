package com.matibi.potionsnrituals.datagen.book.alchemist;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.SingleBookSubProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookCategoryModel;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.book.BookDisplayMode;
import com.matibi.potionsnrituals.datagen.book.alchemist.gettingstarted.*;
import net.minecraft.world.item.Items;

public class PotionsCategory extends CategoryProvider {

    public static final String ID = "potions";

    public PotionsCategory(SingleBookSubProvider parent) {
        super(parent);
    }

    @Override
    protected void generateEntries() {
    }

    @Override
    protected BookCategoryModel additionalSetup(BookCategoryModel category) {
        return category.withDisplayMode(BookDisplayMode.INDEX);
    }

    @Override
    protected String categoryName() {
        return "Potions";
    }

    @Override
    protected String categoryDescription() {
        return "A list of all Potions.";
    }

    @Override
    protected BookIconModel categoryIcon() {
        return BookIconModel.create(Items.POTION);
    }

    @Override
    public String categoryId() {
        return ID;
    }
}
