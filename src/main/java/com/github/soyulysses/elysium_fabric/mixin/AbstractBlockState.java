package com.github.soyulysses.elysium_fabric.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.AbstractBlockState.class)
public class AbstractBlockState {
    /*


    @Inject(at = @At("RETURN"), method = "getHardness", cancellable = true)
    private void getHardness(BlockView world, BlockPos pos, CallbackInfoReturnable<Float> callback) {
        if (world.getBlockState(pos).getBlock() == Blocks.DEEPSLATE) {
            callback.setReturnValue(3f);
        }
    }

     */
}