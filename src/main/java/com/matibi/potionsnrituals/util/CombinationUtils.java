package com.matibi.potionsnrituals.util;

import com.matibi.potionsnrituals.config.ModConfig;
import com.matibi.potionsnrituals.effect.ModEffects;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class CombinationUtils {

    public static List<MobEffectInstance> combineEffect(List<MobEffectInstance> effects) {
        AtomicInteger countBenef = new AtomicInteger();
        AtomicInteger countHarm = new AtomicInteger();

        Map<Holder<MobEffect>, MobEffectInstance> unique = new HashMap<>();

        for (MobEffectInstance e : effects) {
            Holder<MobEffect> type = e.getEffect();

            if (unique.containsKey(type)) {
                MobEffectInstance prev = unique.get(type);
                if (e.getAmplifier() > prev.getAmplifier() ||
                        (e.getAmplifier() == prev.getAmplifier() && e.getDuration() > prev.getDuration()))
                    unique.put(type, e);
            } else {
                unique.put(type, e);
            }
        }

        List<MobEffectInstance> cleaned = new ArrayList<>(unique.values());

        cleaned.forEach(e -> {
            if (e.getEffect().value().isBeneficial()) countBenef.incrementAndGet();
            else countHarm.incrementAndGet();
        });

        return ((countBenef.get() + countHarm.get() <= 2) ||
                (countBenef.get() <= countHarm.get() + ModConfig.max_diff && countHarm.get() <= countBenef.get() + ModConfig.max_diff))
                ? cleaned
                : List.of(new MobEffectInstance(ModEffects.UNSTABLE, 1));
    }
}