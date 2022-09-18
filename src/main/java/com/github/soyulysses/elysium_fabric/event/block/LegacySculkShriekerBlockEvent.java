package com.github.soyulysses.elysium_fabric.event.block;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

/**
 * Class imple
 * @see UseBlockCallback
 *
 * @author SoyUlysses
 * */
@Deprecated
public class LegacySculkShriekerBlockEvent implements UseBlockCallback {

    @Deprecated
    @Override
    public ActionResult interact(PlayerEntity player, World world, Hand hand, BlockHitResult hitResult) {
        final BlockPos pos = hitResult.getBlockPos();
        final BlockState blockState = world.getBlockState(pos);
        final Block block = blockState.getBlock();

        if (block.equals(Blocks.SCULK_SHRIEKER)) {
            final ItemStack stack = player.getStackInHand(hand);
            final Item item = stack.getItem();

            if (!blockState.get(Properties.CAN_SUMMON) && item.equals(Items.ECHO_SHARD) && !player.getItemCooldownManager().isCoolingDown(item)) {
                if (player instanceof ServerPlayerEntity) {
                    final ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
                    final ServerWorld serverWorld = (ServerWorld) world;

                    if (serverWorld.random.nextBetween(1,12) == 6) {
                        Criteria.ITEM_USED_ON_BLOCK.trigger(serverPlayer, pos, stack);
                        serverWorld.playSound( null, pos, SoundEvents.ENTITY_WARDEN_NEARBY_CLOSEST, SoundCategory.BLOCKS, 1f, 1f);
                        serverWorld.setBlockState(pos, blockState.with(Properties.CAN_SUMMON, true), Block.field_31022);
                        serverWorld.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(serverPlayer, blockState));
                    }
                   serverPlayer.getItemCooldownManager().set(item, 20);

                    serverPlayer.increaseStat(Stats.USED.getOrCreateStat(Items.ECHO_SHARD), 1);
                    serverWorld.spawnParticles(ParticleTypes.SCULK_SOUL, pos.getX() + 0.5, pos.getY() + 1.15, pos.getZ() + 0.5, 2, 0.2, 0.0, 0.2, 0.0);
                    serverWorld.playSound(null, pos, SoundEvents.BLOCK_SCULK_CATALYST_BLOOM, SoundCategory.BLOCKS, 2.0F, 0.6F + serverWorld.random.nextFloat() * 0.4F);
                    serverWorld.playSound(null, pos, SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME, SoundCategory.BLOCKS, 2.0F, 0.6F + serverWorld.random.nextFloat() * 0.4F);
                }

                if (!player.getAbilities().creativeMode) {
                    stack.decrement(1);
                }

                return ActionResult.success(world.isClient);
            } else if (!blockState.get(Properties.SHRIEKING) && item != Items.ECHO_SHARD && !player.isSneaky()) {

                if (player instanceof ServerPlayerEntity serverPlayer) {
                    ServerWorld serverWorld = (ServerWorld) world;

                    serverWorld.getBlockEntity(pos, BlockEntityType.SCULK_SHRIEKER).ifPresent((blockEntity) -> {
                        blockEntity.shriek(serverWorld, serverPlayer);
                    });
                }

                return ActionResult.SUCCESS;
            } else {
                return ActionResult.PASS;
            }
        } else {
            return ActionResult.PASS;
        }
    }
}
