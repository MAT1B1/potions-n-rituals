package com.matibi.potionsnrituals.datagen.book.alchemist.gettingstarted;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.IndexModeEntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookTextPageModel;
import com.klikli_dev.modonomicon.client.gui.book.theme.GuiSprite;
import com.matibi.potionsnrituals.item.ModItems;

public class AlchemistCoreEntry extends IndexModeEntryProvider {

    public static final String ID = "alchemist_core";

    public AlchemistCoreEntry(CategoryProvider parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {
        this.page("info", () -> BookTextPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
        );
        this.pageTitle("Alchemist Core");
        this.pageText("""
                The {0} is the fundamental building block of all advanced alchemy.
                It is crafted from various ingredients found in the world.
                                
                Once you have one, you can use it to craft {1} and other alchemical devices.
                """,
                this.itemLink(ModItems.ALCHEMIST_CORE),
                this.itemLink(ModItems.ALCHEMICAL_STONE));

        this.page("recipe", () -> BookCraftingRecipePageModel.create()
                .withTitle1(this.context().pageTitle())
                .withRecipeId1("potions-n-rituals:alchemist_core")
                .withText(this.context().pageText())
        );
        this.pageTitle("Crafting");
        this.pageText("""
                Gather poisonous potatoes, brown and red mushrooms, fermented spider eyes, bone meal, rotten flesh, and a witch's finger to create your first core.
                """);
    }

    @Override
    protected String entryName() {
        return "Alchemist Core";
    }

    @Override
    protected String entryDescription() {
        return "The foundation of all alchemy";
    }

    @Override
    protected GuiSprite entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(ModItems.ALCHEMIST_CORE);
    }

    @Override
    protected String entryId() {
        return ID;
    }
}
