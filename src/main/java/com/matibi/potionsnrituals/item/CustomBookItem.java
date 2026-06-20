package com.matibi.potionsnrituals.item;

import com.matibi.potionsnrituals.potion.ModPotions;
import com.matibi.potionsnrituals.potion.PotionIconHelper;
import com.matibi.potionsnrituals.screen.CustomBookScreen;
import com.matibi.potionsnrituals.util.ModUtils;
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
        List<CustomBookScreen.BookPage> pages = List.of(
                new CustomBookScreen.BookPage(
                        name(ModPotions.ADHESION),
                        List.of(CustomBookScreen.BookPage.PageImage.fromItem(fromPotion(ModPotions.ADHESION), null),
                                CustomBookScreen.BookPage.PageImage.fromTexture(
                                effectTexture(ModPotions.ADHESION), 64, 64, null)
                        ),
                        "The Great Work of Alchemy begins with Nigredo, the blackening."
                ),
                new CustomBookScreen.BookPage(
                        "Albedo",
                        List.of(),
                        "Reduce matter to its primordial form — Materia Prima — through chaos and decay."
                ),
                new CustomBookScreen.BookPage(
                        "Citrinitas",
                        List.of(),
                        "Channel Nether energies to forge talismans and artifacts."
                )
                // ^ 3 pages = impair → dernier spread aura page droite vide, automatique
        );

        Minecraft.getInstance().setScreenAndShow(
                new CustomBookScreen(
                        Component.translatable("item.potions-n-rituals.alchemical_tome"),
                        pages
                )
        );
    }

    private ItemStack fromPotion(Holder<Potion> potion) {
        ItemStack itemStack = new ItemStack(Items.POTION);
        itemStack.set(DataComponents.POTION_CONTENTS, new PotionContents(potion));
        return itemStack;
    }

    private String name(Holder<Potion> potion) {
        String res = fromPotion(potion).getDisplayName().getString();
        int len = res.length();
        return res.substring(1, len - 1);
    }

    private Identifier effectTexture(Holder<Potion> potion) {
        Identifier id = PotionIconHelper.getEffectSpriteId(fromPotion(potion));
        if  (id == null) return null;
        return id.withPrefix("textures/").withSuffix(".png");
    }
}