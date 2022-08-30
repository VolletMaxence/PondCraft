package net.XenceV.pondcraft.entity;// Made with Blockbench 4.3.1
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.XenceV.pondcraft.PondCraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class KoiEntityModel extends EntityModel<KoiEntity> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(PondCraft.MOD_ID, "koi"), "main");
	private final ModelPart body;

	public KoiEntityModel(ModelPart root) {
		this.body = root.getChild("body");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(20, 10).addBox(0.0F, 0.5F, 0.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(20, 10).addBox(0.0F, -3.5F, 0.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(20, 10).addBox(0.0F, -2.3F, -1.0F, 0.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, 12.0F));

		PartDefinition fin_back_2 = body.addOrReplaceChild("fin_back_2", CubeListBuilder.create().texOffs(0, 2).addBox(0.0F, 0.0F, 0.0F, 0.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -10.5F, 3.0F));

		PartDefinition bone = body.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body_back = bone.addOrReplaceChild("body_back", CubeListBuilder.create().texOffs(0, 13).addBox(-1.25F, -2.3F, 1.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, 4.0F));

		PartDefinition ventral_left_fin = bone.addOrReplaceChild("ventral_left_fin", CubeListBuilder.create().texOffs(4, 0).addBox(0.0F, -8.0F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -4.5F, -3.0F, -1.5708F, 0.0F, 0.7854F));

		PartDefinition left_fin = bone.addOrReplaceChild("left_fin", CubeListBuilder.create().texOffs(4, 0).addBox(0.0F, -1.0F, 0.0F, 3.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -4.5F, -3.0F, -1.5708F, 0.0F, 0.7854F));

		PartDefinition ventral_right_fin = bone.addOrReplaceChild("ventral_right_fin", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -8.0F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, -4.5F, -3.0F, -1.5708F, 0.0F, -0.7854F));

		PartDefinition right_fin = bone.addOrReplaceChild("right_fin", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -1.0F, 0.0F, 3.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, -4.5F, -3.0F, -1.5708F, 0.0F, -0.7854F));

		PartDefinition body_front = bone.addOrReplaceChild("body_front", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -2.5F, 0.0F, 3.0F, 5.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, -4.0F));

		PartDefinition head = bone.addOrReplaceChild("head", CubeListBuilder.create().texOffs(22, 0).addBox(-1.0F, -2.0F, -3.0F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, -4.0F));

		PartDefinition fin_back_1 = bone.addOrReplaceChild("fin_back_1", CubeListBuilder.create().texOffs(4, 2).addBox(0.0F, -1.0F, 1.0F, 0.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -10.5F, 1.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(KoiEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}