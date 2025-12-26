package com.sjkz1.frostwalkerplus.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.PowderSnowBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PowderSnowBlock.class)
public class PowderSnowBlockMixin
{
    @Inject(method = "canEntityWalkOnPowderSnow", at = @At(value = "RETURN", ordinal = 1), cancellable = true)
    private static void frost_walker_plus$canWalkOnPowderSnow(Entity entity, CallbackInfoReturnable<Boolean> cir)
    {
        boolean bl = ((LivingEntity) entity).getItemBySlot(EquipmentSlot.FEET).getEnchantments().keySet()
                .stream()
                .anyMatch(enchantmentRegistryEntry -> enchantmentRegistryEntry.is(Enchantments.FROST_WALKER));
        cir.setReturnValue(cir.getReturnValue() || bl);
    }
}
