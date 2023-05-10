package net.XenceV.pondcraft.event;

import net.XenceV.pondcraft.PondCraft;
import net.XenceV.pondcraft.entity.asiandragon.AsianDragonEntity;
import net.XenceV.pondcraft.entity.fry.FryEntity;
import net.XenceV.pondcraft.entity.koi.KoiEntity;
import net.XenceV.pondcraft.entity.ModEntityTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = PondCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCommonEvents {
    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            SpawnPlacements.register(ModEntityTypes.KOI.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, KoiEntity::canSpawn);
        });
    }

    @SubscribeEvent
    public static void entityAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.KOI.get(), KoiEntity.getKoiAttributes().build());
        event.put(ModEntityTypes.FRY.get(), FryEntity.getFryAttributes().build());
        event.put(ModEntityTypes.ASIAN_DRAGON.get(), AsianDragonEntity.getAsianDragonAttributes().build());
    }
}
