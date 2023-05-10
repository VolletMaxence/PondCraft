package net.XenceV.pondcraft.entity.koi;

import net.XenceV.pondcraft.PondCraft;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class KoiEntityModel extends AnimatedGeoModel<KoiEntity> {
	@Override
	public ResourceLocation getModelResource(KoiEntity object) {
		return new ResourceLocation(PondCraft.MOD_ID, "geo/koi.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(KoiEntity object) {
		return KoiEntityRenderer.TEXTURE.get(object.getVariant());
	}

	@Override
	public ResourceLocation getAnimationResource(KoiEntity animatable) {
		return new ResourceLocation(PondCraft.MOD_ID, "animations/tancho_koi.animation.json");
	}
}