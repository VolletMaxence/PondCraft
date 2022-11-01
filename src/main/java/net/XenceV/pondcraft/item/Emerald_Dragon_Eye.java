package net.XenceV.pondcraft.item;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.List;

public class Emerald_Dragon_Eye extends Item {
    public Emerald_Dragon_Eye(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> component, TooltipFlag p_41424_)
    {
        component.add(Component.literal("It must fit somewhere.").withStyle(ChatFormatting.YELLOW));
    }
}
