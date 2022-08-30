package net.XenceV.pondcraft.event;

import net.XenceV.pondcraft.PondCraft;
import net.XenceV.pondcraft.entity.KoiEntityModel;
import net.XenceV.pondcraft.entity.KoiEntityRenderer;
import net.XenceV.pondcraft.entity.ModEntityTypes;
import net.minecraft.client.renderer.entity.EntityRenderers;
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
    }

    @SubscribeEvent
    public static void registerLayerDefinition(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(KoiEntityModel.LAYER_LOCATION, KoiEntityModel::createBodyLayer);
    }


}
