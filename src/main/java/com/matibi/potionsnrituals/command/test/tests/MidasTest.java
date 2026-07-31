package com.matibi.potionsnrituals.command.test.tests;

import com.matibi.potionsnrituals.command.test.TestAssert;
import com.matibi.potionsnrituals.command.test.TestResult;
import com.matibi.potionsnrituals.command.test.TestSuite;
import com.matibi.potionsnrituals.effect.ModEffects;
import com.matibi.potionsnrituals.util.TickManager;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class MidasTest implements TestSuite {

    @Override
    public String id() {
        return "midas:gold_conversion";
    }

    @Override
    public String description() {
        return "Vérifie que Midas convertit les outils en or";
    }

    @Override
    public String category() {
        return "effects";
    }

    @Override
    public TestResult run(ServerLevel level, ServerPlayer player) {
        player.getInventory().setItem(0, new ItemStack(Items.IRON_SWORD));
        player.getInventory().setItem(1, new ItemStack(Items.APPLE));

        player.addEffect(new MobEffectInstance(ModEffects.MIDAS_BENEDICTION, 20 * 60, 0));
        TestAssert.forceTickEffects(level, player);
        TickManager.flush(level.getServer());

        ItemStack slot0 = player.getInventory().getItem(0);
        ItemStack slot1 = player.getInventory().getItem(1);

        if (slot0.getItem() != Items.GOLDEN_SWORD)
            return TestResult.fail("L'épée en fer n'est pas devenue en or : " + slot0.getItem());

        if (slot1.getItem() != Items.GOLDEN_APPLE)
            return TestResult.fail("La pomme n'est pas devenue golden apple : " + slot1.getItem());

        return TestResult.pass("Midas convertit fer→or et pomme→golden apple");
    }
}
