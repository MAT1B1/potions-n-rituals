package com.matibi.potionsnrituals.item;

import com.matibi.potionsnrituals.screen.CustomBookScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

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
        Minecraft.getInstance().setScreenAndShow(
                new CustomBookScreen(
                        Component.translatable("item.potions-n-rituals.alchemical_tome"),
                        """
                                The Great Work of Alchemy begins with Nigredo, the blackening.
                                
                                 \
                                Reduce matter to its primordial form — Materia Prima — \
                                through chaos and decay. Then purify it through Albedo to brew \
                                your first potions. Channel Nether energies in Citrinitas to forge \
                                talismans and artifacts. Finally, achieve Rubedo through the \
                                performance of great worldly rituals."""
                )
        );
    }
}
