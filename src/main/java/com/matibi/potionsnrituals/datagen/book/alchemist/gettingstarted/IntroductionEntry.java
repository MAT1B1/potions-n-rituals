package com.matibi.potionsnrituals.datagen.book.alchemist.gettingstarted;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.IndexModeEntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookTextPageModel;
import com.klikli_dev.modonomicon.client.gui.book.theme.GuiSprite;
import com.matibi.potionsnrituals.item.ModItems;

public class IntroductionEntry extends IndexModeEntryProvider {

    public static final String ID = "introduction";

    public IntroductionEntry(CategoryProvider parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {
        this.page("welcome", () -> BookTextPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
        );
        this.pageTitle("Welcome, Alchemist");
        this.pageText("""
                Welcome to the world of alchemy!
                                
                This guide will teach you the basics of brewing, alchemical stones, and the many effects you can create.
                                
                Begin by gathering ingredients and crafting your first {0}.
                """, this.itemLink(ModItems.ALCHEMIST_CORE));

        this.page("about", () -> BookTextPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
        );
        this.pageTitle("About Potions & Rituals");
        this.pageText("""
                Potions & Rituals reworks Minecraft's potion system with new effects, early-game ingredients, and advanced alchemical interactions.
                                
                Explore the world to find ingredients, brew powerful potions, and craft alchemical stones to imbue yourself with magic.
                """);
    }

    @Override
    protected String entryName() {
        return "Introduction";
    }

    @Override
    protected String entryDescription() {
        return "Welcome to the Alchemist's Guide";
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
