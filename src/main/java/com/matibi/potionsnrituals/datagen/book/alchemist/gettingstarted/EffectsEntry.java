package com.matibi.potionsnrituals.datagen.book.alchemist.gettingstarted;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.IndexModeEntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookTextPageModel;
import com.klikli_dev.modonomicon.client.gui.book.theme.GuiSprite;
import net.minecraft.world.item.Items;

public class EffectsEntry extends IndexModeEntryProvider {

    public static final String ID = "effects";

    public EffectsEntry(CategoryProvider parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {
        this.page("effects", () -> BookTextPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
        );
        this.pageTitle("Effects Overview");
        this.pageText("""
                Potions & Rituals adds a wide variety of new effects for you to discover and use.
                                
                Some effects are beneficial, like Saturation, Long Legs, or Ghost Walk.
                Others are dangerous, like Death, Oblivion, or Petrification.
                                
                Experiment with brewing ingredients to discover all the possibilities!
                """);

        this.page("special", () -> BookTextPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
        );
        this.pageTitle("Special Effects");
        this.pageText("""
                Some notable effects include:
                                
                - **Zeus Benediction**: Call down lightning on your foes
                - **Medusa Benediction**: Turn enemies to stone
                - **Midas Benediction**: Turn mobs into gold nuggets
                - **Resurrection**: Cheat death
                - **Photosynthesis**: Gain food from sunlight
                - **Pregnancy**: ...spread the love
                                
                Higher-tier effects require more advanced alchemical stones to brew.
                """);
    }

    @Override
    protected String entryName() {
        return "Effects Overview";
    }

    @Override
    protected String entryDescription() {
        return "A list of available effects";
    }

    @Override
    protected GuiSprite entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(Items.POTION);
    }

    @Override
    protected String entryId() {
        return ID;
    }
}
