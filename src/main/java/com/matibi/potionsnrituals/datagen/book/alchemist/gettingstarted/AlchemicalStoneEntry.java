package com.matibi.potionsnrituals.datagen.book.alchemist.gettingstarted;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.IndexModeEntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookTextPageModel;
import com.klikli_dev.modonomicon.client.gui.book.theme.GuiSprite;
import com.matibi.potionsnrituals.item.ModItems;

public class AlchemicalStoneEntry extends IndexModeEntryProvider {

    public static final String ID = "alchemical_stone";

    public AlchemicalStoneEntry(CategoryProvider parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {
        this.page("info", () -> BookTextPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
        );
        this.pageTitle("Alchemical Stones");
        this.pageText("""
                The {0} is a reusable tool that can be imbued with effects.
                Right-click a block while holding the stone to activate its effect.
                                
                Each stone has a limited number of hits before it needs to be recharged.
                """,
                this.itemLink(ModItems.ALCHEMICAL_STONE));

        this.page("recipe", () -> BookCraftingRecipePageModel.create()
                .withTitle1(this.context().pageTitle())
                .withRecipeId1("potions-n-rituals:alchemical_stone")
                .withText(this.context().pageText())
        );
        this.pageTitle("Crafting");
        this.pageText("""
                Surround an alchemist core with cobblestone to craft eight alchemical stones.
                You can also use nether wart as an alternative.
                """);

        this.page("usage", () -> BookTextPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
        );
        this.pageTitle("Usage");
        this.pageText("""
                To use an alchemical stone, hold it in your hand and right-click on a block.
                The stone will apply its imbued effect to you.
                                
                You can combine multiple effects on a single stone, and the stone will display all active effects.
                """);
    }

    @Override
    protected String entryName() {
        return "Alchemical Stones";
    }

    @Override
    protected String entryDescription() {
        return "Reusable tools of power";
    }

    @Override
    protected GuiSprite entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(ModItems.ALCHEMICAL_STONE);
    }

    @Override
    protected String entryId() {
        return ID;
    }
}
