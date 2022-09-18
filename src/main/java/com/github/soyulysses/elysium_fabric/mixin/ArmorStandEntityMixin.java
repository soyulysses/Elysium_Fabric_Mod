package com.github.soyulysses.elysium_fabric.mixin;

import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ArmorStandEntity.class)
public class ArmorStandEntityMixin {
    @Inject(at = @At("HEAD"), method = "shouldShowArms", cancellable = true)
    private void getHardness(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }
}
