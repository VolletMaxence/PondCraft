package net.XenceV.pondcraft.entity;

import com.google.common.collect.Maps;
import net.XenceV.pondcraft.PondCraft;
import net.XenceV.pondcraft.entity.variant.KoiVariant;
import net.minecraft.Util;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class KoiEntityRenderer extends MobRenderer<KoiEntity, KoiEntityModel> {

    //private static final ResourceLocation TEXTURE = new ResourceLocation(PondCraft.MOD_ID, "textures/entity/tancho_koi/tancho_koi.png");

    public static final Map<KoiVariant, ResourceLocation> TEXTURE =
            Util.make(Maps.newEnumMap(KoiVariant.class), (map) -> {
                map.put(KoiVariant.TANCHO,
                        new ResourceLocation(PondCraft.MOD_ID, "textures/entity/koi/tancho_koi.png"));
                map.put(KoiVariant.KUMONRYU,
                        new ResourceLocation(PondCraft.MOD_ID, "textures/entity/koi/kumonryu_koi.png"));
                map.put(KoiVariant.KOHAKU,
                        new ResourceLocation(PondCraft.MOD_ID, "textures/entity/koi/kohaku_koi.png"));
                map.put(KoiVariant.REDMATSUBA,
                        new ResourceLocation(PondCraft.MOD_ID, "textures/entity/koi/red_matsuba_koi.png"));
                map.put(KoiVariant.YELLOWMATSUBA,
                        new ResourceLocation(PondCraft.MOD_ID, "textures/entity/koi/yellow_matsuba_koi.png"));
                map.put(KoiVariant.SILVERMATSUBA,
                        new ResourceLocation(PondCraft.MOD_ID, "textures/entity/koi/silver_matsuba_koi.png"));
                map.put(KoiVariant.PATCH,
                        new ResourceLocation(PondCraft.MOD_ID, "textures/entity/koi/silver_matsuba_koi.png"));
                map.put(KoiVariant.HIHITSURI,
                        new ResourceLocation(PondCraft.MOD_ID, "textures/entity/koi/hi_hitsuri_koi.png"));
                map.put(KoiVariant.BLUE,
                        new ResourceLocation(PondCraft.MOD_ID, "textures/entity/koi/blue_koi.png"));
                map.put(KoiVariant.AYAWAKABA,
                        new ResourceLocation(PondCraft.MOD_ID, "textures/entity/koi/aya_wakaba_koi.png"));
                map.put(KoiVariant.GOSHIKI,
                        new ResourceLocation(PondCraft.MOD_ID, "textures/entity/koi/goshiki_koi.png"));
            });

    public KoiEntityRenderer(Context context) {
        super(context, new KoiEntityModel(context.bakeLayer(KoiEntityModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(KoiEntity entity) {
        return TEXTURE.get(entity.getVariant());
    }
}