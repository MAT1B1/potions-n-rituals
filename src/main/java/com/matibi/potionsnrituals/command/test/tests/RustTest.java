package com.matibi.potionsnrituals.command.test.tests;

import com.matibi.potionsnrituals.command.test.TestAssert;
import com.matibi.potionsnrituals.command.test.TestResult;
import com.matibi.potionsnrituals.command.test.TestSuite;
import com.matibi.potionsnrituals.config.ModConfig;
import com.matibi.potionsnrituals.effect.ModEffects;
import com.matibi.potionsnrituals.util.TickManager;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class RustTest implements TestSuite {

    @Override
    public String id() {
        return "rust:armor_damage";
    }

    @Override
    public String description() {
        return "Vérifie que l'effet Rust endommage l'armure équipée";
    }

    @Override
    public String category() {
        return "effects";
    }

    @Override
    public TestResult run(ServerLevel level, ServerPlayer player) {
        ItemStack chestplate = new ItemStack(Items.IRON_CHESTPLATE);
        player.setItemSlot(EquipmentSlot.CHEST, chestplate);
        int initialDamage = chestplate.getDamageValue();

        player.addEffect(new MobEffectInstance(ModEffects.RUST, ModConfig.get().dur_short, 0));
        TestAssert.forceTickEffects(level, player);
        TickManager.flush(level.getServer());

        if (chestplate.getDamageValue() <= initialDamage)
            return TestResult.fail("Rust n'a pas endommagé l'armure");

        return TestResult.pass("Armure endommagée : " + initialDamage + " → " + chestplate.getDamageValue());
    }
}
