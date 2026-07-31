package com.matibi.potionsnrituals.command.test.tests;

import com.matibi.potionsnrituals.command.test.TestResult;
import com.matibi.potionsnrituals.command.test.TestSuite;
import com.matibi.potionsnrituals.config.ModConfig;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

import java.util.ArrayList;
import java.util.List;

public class ConfigTest implements TestSuite {

    @Override
    public String id() {
        return "config:defaults";
    }

    @Override
    public String description() {
        return "Vérifie les valeurs par défaut de la configuration";
    }

    @Override
    public String category() {
        return "config";
    }

    @Override
    public TestResult run(ServerLevel level, ServerPlayer player) {
        ModConfig cfg = ModConfig.get();
        List<String> failures = new ArrayList<>();

        if (cfg.syringe_damage != 1.0f)
            failures.add("syringe_damage: attendu 1.0, obtenu " + cfg.syringe_damage);
        if (cfg.syringe_durability != 20)
            failures.add("syringe_durability: attendu 20, obtenu " + cfg.syringe_durability);
        if (cfg.dur_basic != 20 * 60 * 3)
            failures.add("dur_basic: attendu " + (20 * 60 * 3) + ", obtenu " + cfg.dur_basic);
        if (cfg.dur_long != 20 * 60 * 8)
            failures.add("dur_long: attendu " + (20 * 60 * 8) + ", obtenu " + cfg.dur_long);
        if (cfg.dur_instant != 1)
            failures.add("dur_instant: attendu 1, obtenu " + cfg.dur_instant);
        if (cfg.vampirism_heal != 0.20f)
            failures.add("vampirism_heal: attendu 0.20, obtenu " + cfg.vampirism_heal);
        if (cfg.vampirism_heal_per_level != 0.10f)
            failures.add("vampirism_heal_per_level: attendu 0.10, obtenu " + cfg.vampirism_heal_per_level);
        if (cfg.rust_damage != 0.001f)
            failures.add("rust_damage: attendu 0.001, obtenu " + cfg.rust_damage);
        if (cfg.rust_min_damage != 1)
            failures.add("rust_min_damage: attendu 1, obtenu " + cfg.rust_min_damage);
        if (cfg.health_required_after_aftermath != 0.5f)
            failures.add("health_required_after_aftermath: attendu 0.5, obtenu " + cfg.health_required_after_aftermath);
        if (cfg.zeus_range != 64.0)
            failures.add("zeus_range: attendu 64.0, obtenu " + cfg.zeus_range);
        if (cfg.reality_check_max_slowdown != 0.7D)
            failures.add("reality_check_max_slowdown: attendu 0.7, obtenu " + cfg.reality_check_max_slowdown);
        if (cfg.max_potion_stack != 16)
            failures.add("max_potion_stack: attendu 16, obtenu " + cfg.max_potion_stack);
        if (cfg.max_resurrection != 5)
            failures.add("max_resurrection: attendu 5, obtenu " + cfg.max_resurrection);
        if (cfg.empathy_dmg != 0.30f)
            failures.add("empathy_dmg: attendu 0.30, obtenu " + cfg.empathy_dmg);

        if (failures.isEmpty()) {
            return TestResult.pass("Toutes les valeurs par défaut sont correctes");
        }
        return TestResult.fail(String.join(" §c|§7 ", failures));
    }
}
