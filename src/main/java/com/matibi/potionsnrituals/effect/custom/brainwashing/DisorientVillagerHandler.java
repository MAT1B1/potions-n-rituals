package com.matibi.potionsnrituals.effect.custom.brainwashing;

import com.matibi.potionsnrituals.effect.ModEffects;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.npc.villager.Villager;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DisorientVillagerHandler {
    private static final Map<UUID, MerchantOffers> SNAPSHOTS = new HashMap<>();

    public static void register() {
        ServerTickEvents.END_LEVEL_TICK.register(world ->
                world.getAllEntities().forEach(entity -> {
            if (entity instanceof Villager villager) {
                RandomSource random = villager.getRandom();
                boolean hasEffect = villager.hasEffect(ModEffects.BRAINWASHING);
                UUID villagerId = villager.getUUID();

                if (hasEffect) {
                    if (!SNAPSHOTS.containsKey(villagerId))
                        SNAPSHOTS.put(villagerId, copyOffers(villager.getOffers()));

                    villager.getOffers().forEach(offer -> {
                        int base = offer.getCostA().getCount();

                        int price = random.nextIntBetweenInclusive(1, Math.max(2, base * 4));
                        int currentDiff = offer.getSpecialPriceDiff();
                        offer.addToSpecialPriceDiff((price - base) - currentDiff);
                    });
                } else {
                    MerchantOffers snapshot = SNAPSHOTS.remove(villagerId);
                    if (snapshot != null)
                        restoreOffers(villager, snapshot);
                }
            }
        }));
    }

    private static MerchantOffers copyOffers(MerchantOffers offers) {
        MerchantOffers copy = new MerchantOffers();
        offers.forEach(offer -> copy.add(new MerchantOffer(
                offer.getItemCostA(),
                offer.getItemCostB(),
                offer.getResult().copy(),
                offer.getUses(),
                offer.getMaxUses(),
                offer.getXp(),
                offer.getPriceMultiplier(),
                offer.getDemand()
        )));
        return copy;
    }

    private static void restoreOffers(Villager villager, MerchantOffers snapshot) {
        villager.getOffers().clear();
        snapshot.forEach(offer -> villager.getOffers().add(offer));
    }
}