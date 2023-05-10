package net.XenceV.pondcraft.entity.asiandragon;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.XenceV.pondcraft.PondCraft;
import net.XenceV.pondcraft.entity.fry.FryEntity;
import net.XenceV.pondcraft.entity.fry.FryEntityRenderer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AsianDragonEntityModel extends AnimatedGeoModel<AsianDragonEntity> {
    @Override
    public ResourceLocation getModelResource(AsianDragonEntity object) {
        return new ResourceLocation(PondCraft.MOD_ID, "geo/asian_dragon.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AsianDragonEntity object) {
        return AsianDragonEntityRenderer.TEXTURE;
    }


    @Override
    public ResourceLocation getAnimationResource(AsianDragonEntity animatable) {
        return new ResourceLocation(PondCraft.MOD_ID, "animations/asian_dragon.animation.json");
    }
}
