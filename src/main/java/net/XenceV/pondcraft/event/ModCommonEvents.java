package net.XenceV.pondcraft.event;

import net.XenceV.pondcraft.PondCraft;
import net.XenceV.pondcraft.entity.KoiEntity;
import net.XenceV.pondcraft.entity.ModEntityTypes;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PondCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCommonEvents {
    @SubscribeEvent
    public static void entityAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.KOI.get(), KoiEntity.getKoiAttributes().build());
    }
}
