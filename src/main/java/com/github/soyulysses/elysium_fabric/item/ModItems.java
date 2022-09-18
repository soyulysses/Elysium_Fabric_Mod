package com.github.soyulysses.elysium_fabric.item;

import com.github.soyulysses.elysium_fabric.Elysium_fabric;
import com.github.soyulysses.elysium_fabric.block.ModBlocks;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;

public class ModItems {

    public static final Item POLISHED_GRANITE_BRICKS;
    public static final Item POLISHED_DIORITE_BRICKS;
    public static final Item POLISHED_ANDESITE_BRICKS;
    public static final Item TUFF_BRICKS;

    public ModItems() {
    }

    public static Item register(Block block, ItemGroup group) {
        return Registry.register(Registry.ITEM, Registry.BLOCK.getId(block), new BlockItem(block, new FabricItemSettings().group(group)));
    }

    static {
        POLISHED_GRANITE_BRICKS = register(ModBlocks.POLISHED_GRANITE_BRICKS, ItemGroup.BUILDING_BLOCKS);
        POLISHED_DIORITE_BRICKS = register(ModBlocks.POLISHED_DIORITE_BRICKS, ItemGroup.BUILDING_BLOCKS);
        POLISHED_ANDESITE_BRICKS = register(ModBlocks.POLISHED_ANDESITE_BRICKS, ItemGroup.BUILDING_BLOCKS);
        TUFF_BRICKS = register(ModBlocks.TUFF_BRICKS, ItemGroup.BUILDING_BLOCKS);

        Elysium_fabric.LOGGER.info("Registering Mod Items for " + Elysium_fabric.MOD_ID);
    }
}
