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

public class CustomBookItem extends Item {
    private final BookStructure bookStructure;

    public CustomBookItem(Properties properties, BookStructure structure) {
        super(properties);
        bookStructure = structure;
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
        BookStructure compiledBook = this.bookStructure.build();

        Minecraft.getInstance().setScreenAndShow(
                new CustomBookScreen(compiledBook.getBookTitle(), compiledBook.getFlatPages())
        );
    }
}