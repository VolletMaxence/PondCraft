package net.XenceV.pondcraft.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModTab {
    public static final CreativeModeTab POND_CRAFT_TAB = new CreativeModeTab("pondcrafttab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.RAW_KOI.get());
        }
    };
}
