package net.XenceV.pondcraft.entity;

import com.google.common.collect.Maps;
import net.XenceV.pondcraft.PondCraft;
import net.XenceV.pondcraft.entity.variant.KoiVariant;
import net.minecraft.Util;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class AsianDragonEntityRenderer extends MobRenderer<AsianDragonEntity, AsianDragonEntityModel> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(PondCraft.MOD_ID, "textures/entity/asian_dragon/asian_dragon.png");

    public AsianDragonEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new AsianDragonEntityModel(context.bakeLayer(AsianDragonEntityModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(AsianDragonEntity entity) {
        return TEXTURE;
    }

}
