package com.github.soyulysses.elysium_fabric;

import com.github.soyulysses.elysium_fabric.event.block.SculkShriekerBlockEvent;
import com.github.soyulysses.elysium_fabric.event.block.ShulkerBoxBlockEvent;
import com.github.soyulysses.elysium_fabric.event.entity.ArmorStandEntityEvent;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Elysium_fabric implements ModInitializer {

    public static final String MOD_ID = "elysium_fabric";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Initialized with success: " + MOD_ID);

        //EVENTS
        UseBlockCallback.EVENT.register(new SculkShriekerBlockEvent());
        UseBlockCallback.EVENT.register(new ShulkerBoxBlockEvent());
        UseEntityCallback.EVENT.register(new ArmorStandEntityEvent());

        // BLOCKS & ITEMS
        try {
            Class.forName("com.github.soyulysses.elysium_fabric.block.ModBlocks");
            Class.forName("com.github.soyulysses.elysium_fabric.item.ModItems");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
