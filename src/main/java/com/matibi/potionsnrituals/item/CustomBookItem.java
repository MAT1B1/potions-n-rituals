package com.matibi.potionsnrituals.item;

import com.matibi.potionsnrituals.book.BookStructure;
import com.matibi.potionsnrituals.screen.CustomBookScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

import java.util.function.Supplier;

public class CustomBookItem extends Item {
    private final Supplier<BookStructure> bookSupplier;

    public CustomBookItem(Properties properties, Supplier<BookStructure> bookSupplier) {
        super(properties);
        this.bookSupplier = bookSupplier;
    }

    @Override
    public @NonNull InteractionResult use(@NonNull Level level, @NonNull Player player, @NonNull InteractionHand hand) {
        if (level.isClientSide())
            openScreen();
        return InteractionResult.SUCCESS;
    }

    @Environment(EnvType.CLIENT)
    private void openScreen() {
        BookStructure compiledBook = this.bookSupplier.get().build();

        Minecraft.getInstance().setScreenAndShow(
                new CustomBookScreen(compiledBook.getBookTitle(), compiledBook.getFlatPages())
        );
    }
}