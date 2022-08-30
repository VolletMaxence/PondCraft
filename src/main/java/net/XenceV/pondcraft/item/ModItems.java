package net.XenceV.pondcraft.item;

import net.XenceV.pondcraft.PondCraft;
import net.XenceV.pondcraft.entity.ModEntityTypes;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, PondCraft.MOD_ID);

    public static final RegistryObject<Item> KOI_PALLET = ITEMS.register("koi_pallet",
            () -> new Item(new Item.Properties().tab(ModCreativeModTab.POND_CRAFT_TAB)));

    public static final RegistryObject<ForgeSpawnEggItem> TANCHO_KOI_SPAWN_EGG = ITEMS.register("tancho_koi_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.KOI, 0xb40a1a, 0xffffff,
                    new Item.Properties().tab(ModCreativeModTab.POND_CRAFT_TAB)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
