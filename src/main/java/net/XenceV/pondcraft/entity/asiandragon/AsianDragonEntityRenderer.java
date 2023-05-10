package net.XenceV.pondcraft.entity.asiandragon;

import net.XenceV.pondcraft.PondCraft;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class AsianDragonEntityRenderer extends GeoEntityRenderer<AsianDragonEntity> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(PondCraft.MOD_ID, "textures/entity/asian_dragon/asian_dragon.png");

    public AsianDragonEntityRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new AsianDragonEntityModel());
    }
}
