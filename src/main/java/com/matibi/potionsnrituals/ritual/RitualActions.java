package com.matibi.potionsnrituals.ritual;

import com.matibi.potionsnrituals.config.ModConfig;
import com.matibi.potionsnrituals.util.ModUtils;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;

import java.util.HashMap;
import java.util.Map;

public class RitualActions {
    public static final Map<Identifier, RitualAction> REGISTRY = new HashMap<>();

    public static void addAction(String id, RitualAction action) {
        REGISTRY.put(ModUtils.id(id), action);
    }

    static {
        addAction("random_effect", (level, entity, _) -> {
            Player player = level.getNearestPlayer(entity, 10.0);
            if (player != null) {
                var allEffects = BuiltInRegistries.MOB_EFFECT.stream().toList();
                MobEffect chosenHolder = allEffects
                        .get(level.getRandom().nextInt(allEffects.size()));
                player.addEffect(new MobEffectInstance(Holder.direct(chosenHolder), ModConfig.get().dur_short, 0));
                level.playSound(null, player.blockPosition(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS, 0.5f, 1.0f);
            }
        });
    }
}
