package net.XenceV.pondcraft.event;

import net.XenceV.pondcraft.PondCraft;
import net.XenceV.pondcraft.entity.*;
import net.XenceV.pondcraft.entity.asiandragon.AsianDragonEntityModel;
import net.XenceV.pondcraft.entity.asiandragon.AsianDragonEntityRenderer;
import net.XenceV.pondcraft.entity.fry.FryEntityModel;
import net.XenceV.pondcraft.entity.fry.FryEntityRenderer;
import net.XenceV.pondcraft.entity.koi.KoiEntityRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = PondCraft.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ModClientEvents {

    @SubscribeEvent
    public static void entityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntityTypes.KOI.get(), KoiEntityRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.ASIAN_DRAGON.get(), AsianDragonEntityRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.FRY.get(), FryEntityRenderer::new);
    }
}
