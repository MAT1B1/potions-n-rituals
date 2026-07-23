package com.matibi.potionsnrituals.item.custom.talisman;

import com.matibi.potionsnrituals.datacomponent.ModDataComponents;
import com.matibi.potionsnrituals.effect.TerrainEffect;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;

public class GauntletItem extends Item {

    public static final int MAX_STONES = 5;

    public GauntletItem(Properties properties) {
        super(properties.stacksTo(1).rarity(Rarity.RARE));
    }

    @Override
    public @NonNull InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        ItemStack stack = context.getItemInHand();
        Player player = context.getPlayer();

        if (!(level instanceof ServerLevel serverLevel)) return InteractionResult.SUCCESS;

        PotionContents contents = stack.get(DataComponents.POTION_CONTENTS);
        if (contents == null || !contents.hasEffects()) {
            if (player != null)
                player.sendOverlayMessage(Component.translatable("item.potions-n-rituals.alchemical_gauntlet.empty"));
            return InteractionResult.FAIL;
        }

        List<MobEffectInstance> effectList = new ArrayList<>();
        contents.getAllEffects().forEach(effectList::add);

        if (effectList.isEmpty()) return InteractionResult.FAIL;

        int activeIndex = stack.getOrDefault(ModDataComponents.INDEX, 0);

        if (activeIndex >= effectList.size()) {
            activeIndex = 0;
            stack.set(ModDataComponents.INDEX, activeIndex);
        }

        MobEffectInstance activeEffect = effectList.get(activeIndex);

        if (activeEffect.getEffect().value() instanceof TerrainEffect terrainEffect) {
            if (terrainEffect.isBlockNonApplicable(serverLevel, pos) && player != null) {
                player.sendOverlayMessage(Component.translatable("item.potions-n-rituals.alchemical_stone.block_not_good"));
                return InteractionResult.FAIL;
            } else
                terrainEffect.useOnBlock(serverLevel, player, pos, activeEffect.getDuration(), activeEffect.getAmplifier());
        }

        return InteractionResult.SUCCESS;
    }

    public static void cycleActiveEffect(ItemStack stack, Player player) {
        PotionContents contents = stack.get(DataComponents.POTION_CONTENTS);
        if (contents == null || !contents.hasEffects()) return;

        List<MobEffectInstance> effectList = new ArrayList<>();
        contents.getAllEffects().forEach(effectList::add);

        if (effectList.isEmpty()) return;

        int currentIndex = stack.getOrDefault(ModDataComponents.INDEX, 0);
        int nextIndex = (currentIndex + 1) % effectList.size();

        stack.set(ModDataComponents.INDEX, nextIndex);

        MobEffectInstance nextEffect = effectList.get(nextIndex);
        player.sendOverlayMessage(Component.translatable("message.potions-n-rituals.gauntlet.effect_changed",
                nextEffect.getEffect().value().getDisplayName()));
    }

    @Override
    public boolean isFoil(@NonNull ItemStack stack) { return true; }
}