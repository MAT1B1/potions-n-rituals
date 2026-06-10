package com.matibi.potionsnrituals.datagen.book.alchemist.gettingstarted;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookImagePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookTextPageModel;
import com.klikli_dev.modonomicon.client.gui.book.theme.GuiSprite;
import com.matibi.potionsnrituals.PotionsNRituals;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Items;

public class PotionEntry extends EntryProvider {

    private final String id;
    private final String effectId;
    private final String description;
    private final String explanation;
    private final String base;
    private final String ingredient;
    private final String lore;
    private final boolean vanilla;

    public PotionEntry(CategoryProviderBase parent, String id, String description, String explanation, String base, String ingredient, String lore, boolean vanilla) {
        this(parent, id, id, description, explanation, base, ingredient, lore, vanilla);
    }

    public PotionEntry(CategoryProviderBase parent, String id, String effectId, String description, String explanation, String base, String ingredient, String lore, boolean vanilla) {
        super(parent);
        this.id = id;
        this.effectId = effectId;
        this.description = description;
        this.explanation = explanation;
        this.base = base;
        this.ingredient = ingredient;
        this.lore = lore;
        this.vanilla = vanilla;
    }

    @Override
    protected void generatePages() {

        Component trans = Component.translatable("item.minecraft.potion.effect.%s".formatted(effectId))
                .withStyle(style -> style.withColor(0xFF000000).withBold(true));

        Identifier id_img = vanilla ? Identifier.withDefaultNamespace("textures/mob_effect/" + effectId + ".png") : Identifier.fromNamespaceAndPath(PotionsNRituals.MOD_ID, "textures/mob_effect/" + effectId + ".png");

        this.page(id, () -> BookImagePageModel.create()
                .withTitle(trans)
                .withImages(id_img));

        this.page(id + "_explanation", () -> BookTextPageModel.create()
                .withTitle(trans)
                .withText(explanation)
        );

        this.page(id + "_recipe", () -> BookSpotlightPageModel.create()
                .withTitle("Brewing Recipe")
                .withItem(Items.BREWING_STAND)
                .withText("""
                    **Base**: %s
                    \\
                    \\
                    **Ingredient**: %s
                    """.formatted(base, ingredient))
        );

        this.page(id + "_lore", () -> BookTextPageModel.create()
                .withText(lore)
        );
    }

    @Override
    protected String entryName() {
        return "Potion of " + capitalize(id);
    }

    private String capitalize(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1).replace("_", " ");
    }

    @Override
    protected String entryDescription() {
        return description;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(Items.POTION);
    }

    @Override
    protected GuiSprite entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected String entryId() {
        return id + "_page";
    }
}
