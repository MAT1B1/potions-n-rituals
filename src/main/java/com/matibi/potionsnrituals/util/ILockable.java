package com.matibi.potionsnrituals.util;

import java.util.UUID;

public interface ILockable {
    UUID potions_n_rituals$getPadlockId();
    void potions_n_rituals$setPadlockId(UUID id);

    default boolean isUnlocked() {
        return potions_n_rituals$getPadlockId() == null;
    }
}