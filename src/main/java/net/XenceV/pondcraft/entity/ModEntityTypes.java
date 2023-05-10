package net.XenceV.pondcraft.entity;

import com.google.common.collect.ImmutableSet;
import net.XenceV.pondcraft.PondCraft;
import net.XenceV.pondcraft.entity.asiandragon.AsianDragonEntity;
import net.XenceV.pondcraft.entity.fry.FryEntity;
import net.XenceV.pondcraft.entity.koi.KoiEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;

public class ModEntityTypes<T extends Entity> {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, PondCraft.MOD_ID);


    public static final RegistryObject<EntityType<KoiEntity>> KOI = ENTITIES.register("koi",
            () -> EntityType.Builder.of(KoiEntity::new, MobCategory.WATER_AMBIENT).sized(0.7f, 0.5f).build(new ResourceLocation(PondCraft.MOD_ID, "koi").toString()));

    public static final RegistryObject<EntityType<FryEntity>> FRY = ENTITIES.register("fry",
            () -> EntityType.Builder.of(FryEntity::new, MobCategory.WATER_AMBIENT).sized(0.3f, 0.2f).build(new ResourceLocation(PondCraft.MOD_ID, "fry").toString()));


    public static final RegistryObject<EntityType<AsianDragonEntity>> ASIAN_DRAGON = ENTITIES.register("asian_dragon",
            () -> EntityType.Builder.of(AsianDragonEntity::new, MobCategory.CREATURE).sized(3f, 1.5f).build(new ResourceLocation(PondCraft.MOD_ID, "asian_dragon").toString()));

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }

}
