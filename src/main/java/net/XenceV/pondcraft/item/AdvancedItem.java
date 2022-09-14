package net.XenceV.pondcraft.item;

import net.XenceV.pondcraft.PondCraft;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class AdvancedItem extends Item {
    public AdvancedItem(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected){
        if(!world.isClientSide) {
            if(entity instanceof LivingEntity) {
                LivingEntity player = (Player)entity;

                if(hasItemOnHand(player)) {
                    evaluatePearl(player);
                }
            }
        }
    }

    private void evaluatePearl(LivingEntity player) {
        int ValPearl = heldCorrectPearl(player);

        if(ValPearl == 0) {

        } else if (ValPearl == 1) {
            //Exploration
            player.addEffect(new MobEffectInstance(MobEffects.LUCK, 20));
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20));
            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 20));
            player.addEffect(new MobEffectInstance(MobEffects.CONDUIT_POWER, 20));

        } else if (ValPearl == 2) {
            //Resistance
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 20));
            player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 20));

        } else if (ValPearl == 3) {
            //Strenght
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 20));
            player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 20));
        }
    }

    private int heldCorrectPearl(LivingEntity player) {
        ItemStack mainhand = player.getItemBySlot(EquipmentSlot.MAINHAND);
        ItemStack offhand = player.getItemBySlot(EquipmentSlot.OFFHAND);

        if(mainhand.getItem() == ModItems.DRAGON_PEARL_EXPLORATION.get()
            || offhand.getItem() == ModItems.DRAGON_PEARL_EXPLORATION.get())
        {
            return 1;
        }if(mainhand.getItem() == ModItems.DRAGON_PEARL_RESISTANCE.get()
                || offhand.getItem() == ModItems.DRAGON_PEARL_RESISTANCE.get())
        {
            return 2;
        }if(mainhand.getItem() == ModItems.DRAGON_PEARL_STRENGHT.get()
                || offhand.getItem() == ModItems.DRAGON_PEARL_STRENGHT.get())
        {
            return 3;
        } else
        {
            return 0;
        }
    }

    //Check if player have the item in hand
    private boolean hasItemOnHand(LivingEntity player) {

        Boolean mainhand = player.hasItemInSlot(EquipmentSlot.MAINHAND);
        Boolean offhand = player.hasItemInSlot(EquipmentSlot.OFFHAND);

        if (mainhand || offhand) {
            return true;
        } else {
            return false;
        }
    }
}
