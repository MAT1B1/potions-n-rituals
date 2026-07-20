package com.matibi.potionsnrituals.item.custom.book;

import com.matibi.potionsnrituals.book.BookStructure;
import com.matibi.potionsnrituals.screen.CustomBookScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

import java.util.function.Supplier;

// ==========================================
// CODES DE COULEUR MINECRAFT (SECTION SIGN §)
// ==========================================
// §0 : Noir
// §1 : Bleu foncé
// §2 : Vert foncé
// §3 : Turquoise (Cyan)
// §4 : Rouge foncé
// §5 : Violet
// §6 : Or (Orange)
// §7 : Gris clair
// §8 : Gris foncé
// §9 : Bleu clair
// §a : Vert clair
// §b : Aqua
// §c : Rouge clair
// §d : Rose
// §e : Jaune
// §f : Blanc

// ==========================================
// CODES DE FORMAT / STYLE MINECRAFT
// ==========================================
// §k : Aléatoire / Obscurci (Texte qui défile)
// §l : Gras
// §m : Barré
// §n : Souligné
// §o : Italique
// §r : Reset (Réinitialise toutes les couleurs et styles précédents)

public class CustomBookItem extends Item {
    private final Supplier<BookStructure> bookSupplier;

    public CustomBookItem(Properties properties, Supplier<BookStructure> bookSupplier) {
        super(properties);
        this.bookSupplier = bookSupplier;
    }

    @Override
    public @NonNull InteractionResult use(@NonNull Level level, @NonNull Player player, @NonNull InteractionHand hand) {
        if (level.isClientSide())
            openScreen(player.getItemInHand(hand), hand);
        return InteractionResult.SUCCESS;
    }

    @Environment(EnvType.CLIENT)
    private void openScreen(ItemStack stack, InteractionHand hand) {
        BookStructure compiledBook = this.bookSupplier.get().build();

        Minecraft.getInstance().setScreenAndShow(
                new CustomBookScreen(compiledBook.getBookTitle(), compiledBook.getFlatPages(), stack, hand)
        );
    }
}