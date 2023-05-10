package net.XenceV.pondcraft.entity.fry;

import net.XenceV.pondcraft.PondCraft;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class FryEntityRenderer extends GeoEntityRenderer<FryEntity> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(PondCraft.MOD_ID, "textures/entity/fry/fry.png");

    public FryEntityRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new FryEntityModel());
    }
}
