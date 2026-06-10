package com.matibi.potionsnrituals.datagen.book.alchemist.gettingstarted;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.klikli_dev.modonomicon.client.gui.book.theme.GuiSprite;
import com.matibi.potionsnrituals.item.ModItems;

public class AlchemistCoreEntry extends EntryProvider {

    public static final String ID = "alchemist_core";

    public AlchemistCoreEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {
        this.page("spotlight", () -> BookSpotlightPageModel.create()
                .withTitle("Alchemist Core")
                .withItem(ModItems.ALCHEMIST_CORE)
                .withText("The Alchemist Core is the foundation of all alchemy. It is crafted from rare and poisonous ingredients, and serves as the catalyst for brewing potent potions and performing arcane rituals.")
        );

        this.page("recipe", () -> BookCraftingRecipePageModel.create()
                .withRecipeId1("potions-n-rituals:alchemist_core")
                .withTitle1("Crafting")
                .withText("Combine poisonous ingredients with the Witch's Finger to forge the Alchemist Core.")
        );
    }

    @Override
    protected String entryName() {
        return "Alchemist Core";
    }

    @Override
    protected String entryDescription() {
        return "The heart of alchemy.";
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(ModItems.ALCHEMIST_CORE);
    }

    @Override
    protected GuiSprite entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected String entryId() {
        return ID;
    }
}
