package net.XenceV.pondcraft.entity;

import net.XenceV.pondcraft.PondCraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITIES
            = DeferredRegister.create(ForgeRegistries.ENTITIES, PondCraft.MOD_ID);

    public static final RegistryObject<EntityType<KoiEntity>> KOI = ENTITIES.register("koi",
            () -> EntityType.Builder.of(KoiEntity::new, MobCategory.WATER_AMBIENT).sized(0.5f, 0.7f).build(new ResourceLocation(PondCraft.MOD_ID, "koi").toString()));
}
