package com.github.soyulysses.elysium_fabric.event.block;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface BlockEvent {

    boolean isBlock(BlockState state, World world, BlockPos pos);
}
