package net.XenceV.pondcraft.item;

import net.XenceV.pondcraft.PondCraft;
import net.XenceV.pondcraft.block.DragonStatueBlock;
import net.XenceV.pondcraft.entity.ModEntityTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.XenceV.pondcraft.item.ModCreativeModTab.POND_CRAFT_TAB;

public class ModItems {
    public static final Item.Properties ITEM_PROPERTIES = new Item.Properties().tab(POND_CRAFT_TAB);

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, PondCraft.MOD_ID);

    public static final RegistryObject<Item> KOI_PALLET = ITEMS.register("koi_pallet",
            () -> new Item(new Item.Properties().tab(POND_CRAFT_TAB)));

    public static final RegistryObject<MobBucketItem> KOI_FISH_BUCKET = ITEMS.register("koi_fish_bucket",
            () -> new MobBucketItem(ModEntityTypes.KOI, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH,
                    new Item.Properties().stacksTo(1).tab(POND_CRAFT_TAB)));

    public static final RegistryObject<ForgeSpawnEggItem> KOI_SPAWN_EGG = ITEMS.register("koi_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.KOI, 0xffffff,  0xb40a1a,
                    new Item.Properties().tab(POND_CRAFT_TAB)));

    public static final RegistryObject<ForgeSpawnEggItem> ASIAN_DRAGON_SPAWN_EGG = ITEMS.register("asian_dragon_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.ASIAN_DRAGON, 0x6f11b7,  0xc4d154,
                    new Item.Properties().tab(POND_CRAFT_TAB)));

    public static final RegistryObject<AdvancedItem> DRAGON_PEARL_STRENGHT = ITEMS.register("dragon_pearl_strenght",
            () -> new AdvancedItem(new Item.Properties().stacksTo(1).tab(POND_CRAFT_TAB)));

    public static final RegistryObject<AdvancedItem> DRAGON_PEARL_RESISTANCE = ITEMS.register("dragon_pearl_resistance",
            () -> new AdvancedItem(new Item.Properties().stacksTo(1).tab(POND_CRAFT_TAB)));

    public static final RegistryObject<AdvancedItem> DRAGON_PEARL_EXPLORATION = ITEMS.register("dragon_pearl_exploration",
            () -> new AdvancedItem(new Item.Properties().stacksTo(1).tab(POND_CRAFT_TAB)));

    public static final RegistryObject<Item> RAW_KOI = ITEMS.register("raw_koi",
            () -> new Item(new Item.Properties().tab(POND_CRAFT_TAB).food(Foods.RAW_KOI)));

    public static final RegistryObject<Item> COOKED_KOI = ITEMS.register("cooked_koi",
            () -> new Item(new Item.Properties().tab(POND_CRAFT_TAB).food(Foods.COOKED_KOI)));

    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, PondCraft.MOD_ID);

    public static final RegistryObject<Block> DRAGON_STATUE_BLOCK = BLOCKS.register("dragon_statue",
            () -> new DragonStatueBlock(BlockBehaviour.Properties.of(Material.STONE).strength(999f)
                    .noOcclusion()
                    .lightLevel(state -> state.getValue(DragonStatueBlock.ACTIVATED) ? 1 : 15)));

    public static final RegistryObject<Item> DRAGON_STATUE_ITEM = fromBlock(DRAGON_STATUE_BLOCK);

    public static final RegistryObject<Emerald_Dragon_Eye> EMERALD_DRAGON_EYE = ITEMS.register("emerald_dragon_eye",
            () -> new Emerald_Dragon_Eye(new Emerald_Dragon_Eye.Properties().tab(POND_CRAFT_TAB)));

    public static class Foods {
        public static final FoodProperties RAW_KOI = new FoodProperties.Builder().nutrition(2).saturationMod(2.4f).build();
        public static final FoodProperties COOKED_KOI = new FoodProperties.Builder().nutrition(5).saturationMod(11f).build();
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
        BLOCKS.register(eventBus);
    }

    public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), ITEM_PROPERTIES));
    }
}
