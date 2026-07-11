package com.matibi.potionsnrituals.world.data;

import com.matibi.potionsnrituals.PotionsNRituals;
import com.matibi.potionsnrituals.util.ModUtils;
import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;

public class ModAttachments {

    public static final AttachmentType<Integer> BAG_ID_COUNTER = AttachmentRegistry.createPersistent(
            ModUtils.id("bag_id_counter"),
            Codec.INT
    );

    public static void register() {
        PotionsNRituals.LOGGER.info("Registering Attachments for " + PotionsNRituals.MOD_ID);
    }
}