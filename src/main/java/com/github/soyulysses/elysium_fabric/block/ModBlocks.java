package com.github.soyulysses.elysium_fabric.block;

import com.github.soyulysses.elysium_fabric.Elysium_fabric;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {
    public static final Block POLISHED_GRANITE_BRICKS;
    public static final Block POLISHED_DIORITE_BRICKS;
    public static final Block POLISHED_ANDESITE_BRICKS;
    public static final Block TUFF_BRICKS;

    public ModBlocks() {
    }

    private static Block register(String id, Block block) {
        return (Block) Registry.register(Registry.BLOCK, new Identifier(Elysium_fabric.MOD_ID, id), block);
    }

    static {
        POLISHED_GRANITE_BRICKS = register("polished_granite_bricks", new Block(AbstractBlock.Settings.of(Material.STONE, MapColor.DIRT_BROWN).requiresTool().strength(1.5F, 6.0F)));
        POLISHED_DIORITE_BRICKS = register("polished_diorite_bricks", new Block(AbstractBlock.Settings.of(Material.STONE, MapColor.OFF_WHITE).requiresTool().strength(1.5F, 6.0F)));
        POLISHED_ANDESITE_BRICKS = register("polished_andesite_bricks", new Block(AbstractBlock.Settings.of(Material.STONE, MapColor.STONE_GRAY).requiresTool().strength(1.5F, 6.0F)));
        TUFF_BRICKS = register("tuff_bricks", new Block(AbstractBlock.Settings.of(Material.STONE, MapColor.TERRACOTTA_GRAY).sounds(BlockSoundGroup.TUFF).requiresTool().strength(1.5F, 6.0F)));

        Elysium_fabric.LOGGER.info("Registering Mod Blocks for " + Elysium_fabric.MOD_ID);
    }
}
