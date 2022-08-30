package net.XenceV.pondcraft.entity;

import net.XenceV.pondcraft.PondCraft;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class KoiEntityRenderer extends MobRenderer<KoiEntity, KoiEntityModel> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(PondCraft.MOD_ID, "textures/entity/tancho_koi/tancho_koi.png");

    public KoiEntityRenderer(Context context) {
        super(context, new KoiEntityModel(context.bakeLayer(KoiEntityModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(KoiEntity entity) {
        return TEXTURE;
    }
}