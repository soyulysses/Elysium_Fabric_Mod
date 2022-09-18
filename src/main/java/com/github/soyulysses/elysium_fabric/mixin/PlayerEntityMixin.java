package com.github.soyulysses.elysium_fabric.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * Main mixin modifier of the {@link net.minecraft.entity.player.PlayerEntity PlayerEntity.class}
 * @see net.minecraft.entity.player.PlayerEntity
 *
 * @author SoyUlysses
 * @author ParadoxSubject
 * */
@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

    /**
     * Modifies the value that is fetched from
     * {@link net.minecraft.enchantment.EnchantmentHelper#getEfficiency(LivingEntity) EnchantmentHelper.getEfficiency()}
     * in {@link net.minecraft.entity.player.PlayerEntity#getBlockBreakingSpeed(BlockState) getBlockBreakingSpeed()}
     * method when it's called, by increasing the efficiency value if it is a Netherite tool.
     * @see net.minecraft.entity.player.PlayerEntity#getBlockBreakingSpeed(BlockState)
     * @see net.minecraft.enchantment.EnchantmentHelper#getEfficiency(LivingEntity)
     *
     * @param entity Players' Entity
     *
     * @return A modified integer value of the efficiency enchantment level
     * @author SoyUlysses
     * @author ParadoxSubject
     * */
    @Redirect(method = "getBlockBreakingSpeed(Lnet/minecraft/block/BlockState;)F",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/EnchantmentHelper;getEfficiency(Lnet/minecraft/entity/LivingEntity;)I"))
    public int getEfficiency(@NotNull LivingEntity entity) {
        // Gets the enchantment level
        int efficiencyLevel = EnchantmentHelper.getEfficiency(entity);

        // Gets the item that the player has in his main had
        Item mainHandItem = entity.getMainHandStack().getItem();

        // Checks if the item is an instance of the class "ToolItem" and if it's of type netherite
        if (mainHandItem instanceof ToolItem && ((ToolItem) mainHandItem).getMaterial().equals(ToolMaterials.NETHERITE))
            efficiencyLevel = MathHelper.clamp(efficiencyLevel * 8 / 5, 0, 255);

        // return the enchantment level value
        return efficiencyLevel;
    }
}
