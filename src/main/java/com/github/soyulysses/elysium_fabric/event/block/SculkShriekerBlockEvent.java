package com.github.soyulysses.elysium_fabric.event.block;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class SculkShriekerBlockEvent implements UseBlockCallback, BlockEvent {
    @Override
    public ActionResult interact(PlayerEntity player, World world, Hand hand, BlockHitResult hitResult) {
        final BlockPos pos = hitResult.getBlockPos();
        final BlockState state = world.getBlockState(pos);
        final ItemStack stack = player.getStackInHand(hand);

        if (isBlock(state, world, pos, Properties.CAN_SUMMON) && stack.isOf(Items.ECHO_SHARD)) {
            if (player instanceof ServerPlayerEntity) {
                final ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
                final ServerWorld serverWorld = (ServerWorld) world;

                if (serverWorld.random.nextBetween(1,12) == 6) {
                    Criteria.ITEM_USED_ON_BLOCK.trigger(serverPlayer, pos, stack);
                    serverWorld.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(serverPlayer, state));

                    serverWorld.playSound( null, pos, SoundEvents.ENTITY_WARDEN_NEARBY_CLOSEST, SoundCategory.BLOCKS, 1f, 1f);
                    serverWorld.setBlockState(pos, state.with(Properties.CAN_SUMMON, true), Block.field_31022);
                }

                serverPlayer.increaseStat(Stats.USED.getOrCreateStat(Items.ECHO_SHARD), 1);

                serverWorld.spawnParticles(ParticleTypes.SCULK_SOUL, pos.getX() + 0.5, pos.getY() + 1.15, pos.getZ() + 0.5, 2, 0.2, 0.0, 0.2, 0.0);
                serverWorld.playSound(null, pos, SoundEvents.BLOCK_SCULK_CATALYST_BLOOM, SoundCategory.BLOCKS, 2.0F, 0.6F + serverWorld.random.nextFloat() * 0.4F);
                serverWorld.playSound(null, pos, SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME, SoundCategory.BLOCKS, 2.0F, 0.6F + serverWorld.random.nextFloat() * 0.4F);

                if (!serverPlayer.getAbilities().creativeMode) {
                    stack.decrement(1);
                }
            }

            return ActionResult.success(world.isClient);
        } else {
            return ActionResult.PASS;
        }
    }

    @Override
    public boolean isBlock(BlockState state, World world, BlockPos pos) {
        return state.getBlock().equals(Blocks.SCULK_SHRIEKER);
    }

    public boolean isBlock(BlockState state, World world, BlockPos pos, BooleanProperty property) {
        return isBlock(state, world, pos) && !state.get(property);
    }
}
