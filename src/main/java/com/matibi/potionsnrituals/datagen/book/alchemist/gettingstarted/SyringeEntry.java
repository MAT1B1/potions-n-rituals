package com.matibi.potionsnrituals.datagen.book.alchemist.gettingstarted;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.IndexModeEntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookTextPageModel;
import com.klikli_dev.modonomicon.client.gui.book.theme.GuiSprite;
import com.matibi.potionsnrituals.item.ModItems;

public class SyringeEntry extends IndexModeEntryProvider {

    public static final String ID = "syringe";

    public SyringeEntry(CategoryProvider parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {
        this.page("info", () -> BookTextPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
        );
        this.pageTitle("Syringe");
        this.pageText("""
                The {0} allows you to extract and transfer potion effects between entities.
                Use it on a mob to extract its active effects, then apply them to yourself or another target.
                """,
                this.itemLink(ModItems.SYRINGE));

        this.page("recipe", () -> BookCraftingRecipePageModel.create()
                .withTitle1(this.context().pageTitle())
                .withRecipeId1("potions-n-rituals:syringe")
                .withText(this.context().pageText())
        );
        this.pageTitle("Crafting");
        this.pageText("""
                Combine a glass bottle with an iron nugget to craft a syringe.
                """);
    }

    @Override
    protected String entryName() {
        return "Syringe";
    }

    @Override
    protected String entryDescription() {
        return "Extract and transfer effects";
    }

    @Override
    protected GuiSprite entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(ModItems.SYRINGE);
    }

    @Override
    protected String entryId() {
        return ID;
    }
}
