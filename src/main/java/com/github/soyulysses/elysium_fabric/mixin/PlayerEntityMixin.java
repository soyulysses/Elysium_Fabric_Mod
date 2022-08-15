package com.github.soyulysses.elysium_fabric.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Redirect(method = "getBlockBreakingSpeed(Lnet/minecraft/block/BlockState;)F",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/EnchantmentHelper;getEfficiency(Lnet/minecraft/entity/LivingEntity;)I"))
    public int getEfficiency(LivingEntity entity) {
        Item mainHandItem = entity.getHandItems().iterator().next().getItem();
        int efficiencyLevel = EnchantmentHelper.getEfficiency(entity);

        if (mainHandItem instanceof ToolItem && ((ToolItem) mainHandItem).getMaterial().equals(ToolMaterials.NETHERITE))
            efficiencyLevel = MathHelper.clamp(efficiencyLevel * 8 / 5, 0, 255);

        return efficiencyLevel;
    }
}
