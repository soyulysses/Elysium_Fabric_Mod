package com.github.soyulysses.elysium_fabric.event.entity;

import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ArmorStandEntityEvent implements UseEntityCallback {
    @Override
    public ActionResult interact(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (entity instanceof ArmorStandEntity) {
            if (player.getStackInHand(hand).isOf(Items.STICK)) {
                if (world instanceof ServerWorld) {
                    NbtCompound nbt = entity.writeNbt(new NbtCompound());
                    nbt.putBoolean("ShowArms", true);
                    entity.readNbt(nbt);

                    return ActionResult.success(world.isClient());
                }
            }
        }
        return ActionResult.PASS;
    }
}
