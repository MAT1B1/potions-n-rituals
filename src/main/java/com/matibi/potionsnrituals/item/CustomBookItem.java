package com.matibi.potionsnrituals.item;

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
    // ==========================================
    //   MINECRAFT FORMATTING CODES (COLOR & STYLE)
    // ==========================================
    // Note : Toujours mettre la couleur AVANT le style (ex: §6§l pour Or + Gras)
    //
    // --- STYLES ---
    // §l : Gras (Bold)
    // §o : Italique (Italic)
    // §n : Souligné (Underline)
    // §m : Barré (Strikethrough)
    // §k : Obscurci / Magique (Obfuscated)
    // §r : Réinitialiser tout le formatage (Reset)
    //
    // --- COULEURS ---
    // §0 : Noir (Black)
    // §1 : Bleu foncé (Dark Blue)
    // §2 : Vert foncé (Dark Green)
    // §3 : Turquoise foncé (Dark Aqua)
    // §4 : Rouge foncé (Dark Red)
    // §5 : Violet (Dark Purple)
    // §6 : Or (Gold)
    // §7 : Gris clair (Gray)
    // §8 : Gris foncé (Dark Gray)
    // §9 : Bleu (Blue)
    // §a : Vert clair (Green)
    // §b : Turquoise clair (Aqua)
    // §c : Rouge clair (Red)
    // §d : Rose / Magenta (Light Purple)
    // §e : Jaune (Yellow)
    // §f : Blanc (White)
    // ==========================================

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
                        Component.literal("§l" + name(ModPotions.ADHESION)),
                        List.of(
                                CustomBookScreen.BookPage.PageImage.fromItem(fromPotion(ModPotions.ADHESION), null),
                                CustomBookScreen.BookPage.PageImage.fromTexture(
                                        effectTexture(ModPotions.ADHESION), 64, 64, null)
                        ),
                        Component.literal("The §4Great Work§r of Alchemy begins with §5Nigredo§r.")
                ),
                new CustomBookScreen.BookPage(
                        Component.literal("§oAlbedo"),
                        List.of(),
                                Component.literal("Reduce matter to its primordial form — Materia Prima — through chaos and decay.")
                ),
                new CustomBookScreen.BookPage(
                        Component.literal("§nCitrinitas"),
                        List.of(),
                                Component.literal("Channel Nether energies to §kforge§r talismans and §martifacts§r.")
                )
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