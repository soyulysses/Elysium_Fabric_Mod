package com.github.soyulysses.elysium_fabric.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

@Mixin(AbstractBlock.AbstractBlockState.class)
public class AbstractBlockState {

    @Inject(at = @At("HEAD"), method = "getHardness", cancellable = true)
    private void getHardness(BlockView world, BlockPos pos, CallbackInfoReturnable<Float> callback) {
        ArrayList<Block> BlocksArray = new ArrayList<Block> (Arrays.asList(
                Blocks.DEEPSLATE,
                Blocks.BASALT,
                Blocks.SMOOTH_BASALT,
                Blocks.BLACKSTONE,
                Blocks.COBBLESTONE,
                Blocks.MOSSY_COBBLESTONE,
                Blocks.END_STONE,
                Blocks.OAK_LOG,
                Blocks.BIRCH_LOG,
                Blocks.SPRUCE_LOG,
                Blocks.JUNGLE_LOG,
                Blocks.DARK_OAK_LOG,
                Blocks.ACACIA_LOG,
                Blocks.MANGROVE_LOG,
                Blocks.CRIMSON_STEM,
                Blocks.WARPED_STEM,
                Blocks.STRIPPED_OAK_LOG,
                Blocks.STRIPPED_BIRCH_LOG,
                Blocks.STRIPPED_SPRUCE_LOG,
                Blocks.STRIPPED_JUNGLE_LOG,
                Blocks.STRIPPED_DARK_OAK_LOG,
                Blocks.STRIPPED_ACACIA_LOG,
                Blocks.STRIPPED_MANGROVE_LOG,
                Blocks.STRIPPED_CRIMSON_STEM,
                Blocks.STRIPPED_WARPED_STEM,
                Blocks.OAK_WOOD,
                Blocks.BIRCH_WOOD,
                Blocks.SPRUCE_WOOD,
                Blocks.JUNGLE_WOOD,
                Blocks.DARK_OAK_WOOD,
                Blocks.ACACIA_WOOD,
                Blocks.MANGROVE_WOOD,
                Blocks.CRIMSON_HYPHAE,
                Blocks.WARPED_HYPHAE,
                Blocks.STRIPPED_OAK_WOOD,
                Blocks.STRIPPED_BIRCH_WOOD,
                Blocks.STRIPPED_SPRUCE_WOOD,
                Blocks.STRIPPED_JUNGLE_WOOD,
                Blocks.STRIPPED_DARK_OAK_WOOD,
                Blocks.STRIPPED_ACACIA_WOOD,
                Blocks.STRIPPED_MANGROVE_WOOD,
                Blocks.STRIPPED_CRIMSON_HYPHAE,
                Blocks.STRIPPED_WARPED_HYPHAE,
                Blocks.OAK_PLANKS,
                Blocks.BIRCH_PLANKS,
                Blocks.SPRUCE_PLANKS,
                Blocks.JUNGLE_PLANKS,
                Blocks.DARK_OAK_PLANKS,
                Blocks.ACACIA_PLANKS,
                Blocks.MANGROVE_PLANKS,
                Blocks.CRIMSON_PLANKS,
                Blocks.WARPED_PLANKS
        ));

        if (BlocksArray.contains(world.getBlockState(pos).getBlock())) {
            callback.setReturnValue(1.6f);
        }
    }
}