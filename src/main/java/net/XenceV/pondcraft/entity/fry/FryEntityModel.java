package net.XenceV.pondcraft.entity.fry;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.XenceV.pondcraft.PondCraft;
import net.XenceV.pondcraft.entity.asiandragon.AsianDragonEntity;
import net.XenceV.pondcraft.entity.koi.KoiEntity;
import net.XenceV.pondcraft.entity.koi.KoiEntityRenderer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class FryEntityModel extends AnimatedGeoModel<FryEntity> {
    @Override
    public ResourceLocation getModelResource(FryEntity object) {
        return new ResourceLocation(PondCraft.MOD_ID, "geo/fry.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(FryEntity object) {
        return FryEntityRenderer.TEXTURE;
    }

    @Override
    public ResourceLocation getAnimationResource(FryEntity animatable) {
        return new ResourceLocation(PondCraft.MOD_ID, "animations/fry.animation.json");
    }
}
