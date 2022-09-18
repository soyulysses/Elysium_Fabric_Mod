package com.github.soyulysses.elysium_fabric.event.block;

import com.google.common.collect.ImmutableBiMap;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import java.util.Objects;

public class ShulkerBoxBlockEvent implements UseBlockCallback, BlockEvent {

    public static ImmutableBiMap<Item, Block> DYE_SHULKER_BOX = ImmutableBiMap.<Item, Block>builder()
            .put(Items.WHITE_DYE, Blocks.WHITE_SHULKER_BOX)
            .put(Items.ORANGE_DYE, Blocks.ORANGE_SHULKER_BOX)
            .put(Items.MAGENTA_DYE, Blocks.MAGENTA_SHULKER_BOX)
            .put(Items.LIGHT_BLUE_DYE, Blocks.LIGHT_BLUE_SHULKER_BOX)
            .put(Items.YELLOW_DYE, Blocks.YELLOW_SHULKER_BOX)
            .put(Items.LIME_DYE, Blocks.LIME_SHULKER_BOX)
            .put(Items.PINK_DYE, Blocks.PINK_SHULKER_BOX)
            .put(Items.GRAY_DYE, Blocks.GRAY_SHULKER_BOX)
            .put(Items.LIGHT_GRAY_DYE, Blocks.LIGHT_GRAY_SHULKER_BOX)
            .put(Items.CYAN_DYE, Blocks.CYAN_SHULKER_BOX)
            .put(Items.PURPLE_DYE, Blocks.PURPLE_SHULKER_BOX)
            .put(Items.BLUE_DYE, Blocks.BLUE_SHULKER_BOX)
            .put(Items.BROWN_DYE, Blocks.BROWN_SHULKER_BOX)
            .put(Items.GREEN_DYE, Blocks.GREEN_SHULKER_BOX)
            .put(Items.RED_DYE, Blocks.RED_SHULKER_BOX)
            .put(Items.BLACK_DYE, Blocks.BLACK_SHULKER_BOX)
            .put(Items.POTION, Blocks.SHULKER_BOX)
            .build();

    public static ImmutableBiMap<Block, Item> DYE_SHULKER_BOX_REV = DYE_SHULKER_BOX.inverse();

    @Override
    public ActionResult interact(PlayerEntity player, World world, Hand hand, BlockHitResult hitResult) {
        final BlockPos pos = hitResult.getBlockPos();
        final BlockState state = world.getBlockState(pos);
        final ItemStack stack = player.getStackInHand(hand);
        final Item item = stack.getItem();

        if (isBlock(state, world, pos) && DYE_SHULKER_BOX.containsKey(item)) {

                if (state.isOf(DYE_SHULKER_BOX.get(item))) return ActionResult.PASS;
                if (stack.isOf(Items.POTION) && PotionUtil.getPotion(stack) != Potions.WATER) return ActionResult.PASS;

                if (world instanceof final ServerWorld serverWorld) {
                    final ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;

                    final NbtCompound nbt = Objects.requireNonNull(serverWorld.getBlockEntity(pos)).createNbt().copy();

                    serverWorld.setBlockState(pos, Objects.requireNonNull(DYE_SHULKER_BOX.get(item)).getStateWithProperties(state), Block.field_31022);

                    if (stack.isOf(Items.POTION)) {
                        serverWorld.playSound( null, pos, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.BLOCKS, 1f, 1f);
                        serverPlayer.setStackInHand(hand, new ItemStack(Items.GLASS_BOTTLE, 1));
                    } else {
                        serverWorld.playSound( null, pos, SoundEvents.ITEM_DYE_USE, SoundCategory.BLOCKS, 1f, 1f);
                        stack.decrement(1);
                    }

                    Objects.requireNonNull(serverWorld.getBlockEntity(pos)).readNbt(nbt);
                }
                return ActionResult.success(world.isClient());
        }
        return ActionResult.PASS;
    }

    @Override
    public boolean isBlock(BlockState state, World world, BlockPos pos) {
        return world.getBlockState(pos).isOf(Blocks.SHULKER_BOX) || DYE_SHULKER_BOX.containsValue(world.getBlockState(pos).getBlock());
    }
}
