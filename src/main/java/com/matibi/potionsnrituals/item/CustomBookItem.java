package com.matibi.potionsnrituals.item;

import com.matibi.potionsnrituals.book.BookPage;
import com.matibi.potionsnrituals.book.BookStructure;
import com.matibi.potionsnrituals.potion.ModPotions;
import com.matibi.potionsnrituals.potion.PotionIconHelper;
import com.matibi.potionsnrituals.screen.CustomBookScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class CustomBookItem extends Item {

    public CustomBookItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NonNull InteractionResult use(@NonNull Level level, @NonNull Player player, @NonNull InteractionHand hand) {
        if (level.isClientSide()) {
            openScreen();
        }
        return InteractionResult.SUCCESS;
    }

    @Environment(EnvType.CLIENT)
    private void openScreen() {
        BookStructure book = new BookStructure(Component.translatable("item.potions-n-rituals.alchemical_tome"))
            .tableofcontents("Sommaire Alchimique")

            .page(BookPage.Empty("blank"))

            .chapter("Alchimie", c -> c
                .page(createPotionPage(ModPotions.ADHESION, "The §4Great Work§r of Alchemy begins with §5Nigredo§r."))

                .subChapter("Avancée", sub -> sub
                    .page(createStandardPage("albedo_page", "§oAlbedo", "Reduce matter to its primordial form — Materia Prima — through chaos and decay."))
                    .page(createStandardPage("citrinitas_page", "§nCitrinitas", "Channel Nether energies to §kforge§r talismans and §martifacts§r."))
                )
            )

            .chapter("Autre Onglet", c -> c
                .page(createStandardPage("autre", "Titre", "Contenu..."))
            )
            .build();

        Minecraft.getInstance().setScreenAndShow(
                new CustomBookScreen(book.getBookTitle(), book.getFlatPages())
        );
    }

    @Environment(EnvType.CLIENT)
    private BookPage createStandardPage(String id, String title, String body) {
        if (body != null)
            return new BookPage(id, Component.literal(title), List.of(), Component.literal(body));
        return new BookPage(id, Component.literal(title), List.of(), null);
    }

    @Environment(EnvType.CLIENT)
    private BookPage createPotionPage(Holder<Potion> potion, String body) {
        return new BookPage(
                id(potion),
                Component.literal("§l" + name(potion)),
                List.of(
                        BookPage.PageImage.fromItem(fromPotion(potion), null),
                        BookPage.PageImage.fromTexture(effectTexture(potion), 64, 64, null)
                ),
                Component.literal(body)
        );
    }

    private String id(Holder<Potion> potion) {
        return potion.unwrapKey()
                .map(key -> key.identifier().getPath())
                .orElse("unknown");
    }

    private ItemStack fromPotion(Holder<Potion> potion) {
        ItemStack itemStack = new ItemStack(Items.POTION);
        itemStack.set(DataComponents.POTION_CONTENTS, new PotionContents(potion));
        return itemStack;
    }

    private String name(Holder<Potion> potion) {
        String res = fromPotion(potion).getDisplayName().getString();
        return res.substring(1, res.length() - 1);
    }

    private Identifier effectTexture(Holder<Potion> potion) {
        Identifier id = PotionIconHelper.getEffectSpriteId(fromPotion(potion));
        return id == null ? null : id.withPrefix("textures/").withSuffix(".png");
    }
}