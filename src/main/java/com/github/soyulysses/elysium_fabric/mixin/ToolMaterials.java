package com.github.soyulysses.elysium_fabric.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(net.minecraft.item.ToolMaterials.class)
public class ToolMaterials {

    /*

    @Inject(at = @At("RETURN"), method = "getMiningSpeedMultiplier" , cancellable = true)
    public void getMiningSpeedMultiplier(CallbackInfoReturnable<Float> callback) {
        if((Object) this == net.minecraft.item.ToolMaterials.NETHERITE) {
            callback.setReturnValue(14.0f);
        }
    }

    */

}