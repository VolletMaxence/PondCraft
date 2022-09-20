package net.XenceV.pondcraft.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.XenceV.pondcraft.PondCraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class AsianDragonEntityModel extends EntityModel<AsianDragonEntity> {

    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(PondCraft.MOD_ID, "asian_dragon"), "main");
    private final ModelPart aretirer;
    private final ModelPart bone;

    public AsianDragonEntityModel(ModelPart root) {
        this.aretirer = root.getChild("aretirer");
        this.bone = root.getChild("bone");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition aretirer = partdefinition.addOrReplaceChild("aretirer", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition griffes2 = aretirer.addOrReplaceChild("griffes2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition griffesmain2 = griffes2.addOrReplaceChild("griffesmain2", CubeListBuilder.create(), PartPose.offsetAndRotation(9.0F, -3.0F, -16.0F, 0.1859F, 0.6031F, -1.1666F));

        PartDefinition griffe3_r1 = griffesmain2.addOrReplaceChild("griffe3_r1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.2852F, -1.1476F, -0.2942F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.3479F, -0.2459F, 2.893F));

        PartDefinition griffe2_r1 = griffesmain2.addOrReplaceChild("griffe2_r1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.2852F, 0.8524F, -0.2942F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4157F, -0.6112F, 3.053F));

        PartDefinition griffespouce2 = griffes2.addOrReplaceChild("griffespouce2", CubeListBuilder.create(), PartPose.offset(7.0F, -3.0F, -16.0F));

        PartDefinition petitegriffe2_r1 = griffespouce2.addOrReplaceChild("petitegriffe2_r1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.7148F, -0.1476F, -0.2942F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 0).mirror().addBox(-1.2852F, -0.1476F, -0.2942F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5662F, -0.8398F, -2.8058F));

        PartDefinition griffes = aretirer.addOrReplaceChild("griffes", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition griffesmain = griffes.addOrReplaceChild("griffesmain", CubeListBuilder.create(), PartPose.offsetAndRotation(-9.0F, -3.0F, -16.0F, 0.1859F, -0.6031F, 1.1666F));

        PartDefinition griffe2_r2 = griffesmain.addOrReplaceChild("griffe2_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-1.7148F, -1.1476F, -0.2942F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.3479F, 0.2459F, -2.893F));

        PartDefinition griffe1_r1 = griffesmain.addOrReplaceChild("griffe1_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.7148F, 0.8524F, -0.2942F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4157F, 0.6112F, -3.053F));

        PartDefinition griffespouce = griffes.addOrReplaceChild("griffespouce", CubeListBuilder.create(), PartPose.offset(-7.0F, -3.0F, -16.0F));

        PartDefinition petitegriffe1_r1 = griffespouce.addOrReplaceChild("petitegriffe1_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.7148F, -0.1476F, -0.2942F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-0.7148F, -0.1476F, -0.2942F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5662F, 0.8398F, 2.8058F));

        PartDefinition testdents = aretirer.addOrReplaceChild("testdents", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -2.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.0F, -57.0F));

        PartDefinition basdent2_r1 = testdents.addOrReplaceChild("basdent2_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-0.3572F, 0.0F, -0.766F, 2.0F, 1.0F, 1.0F, new CubeDeformation(-1.0F)), PartPose.offsetAndRotation(2.0F, -1.0F, 0.0F, -3.1416F, -0.6981F, 3.1416F));

        PartDefinition hautdent2_r1 = testdents.addOrReplaceChild("hautdent2_r1", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -1.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition basdent1_r1 = testdents.addOrReplaceChild("basdent1_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(-1.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 0.0F, 0.0F, -0.8727F, 0.0F));

        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition corps = bone.addOrReplaceChild("corps", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -4.0F, 14.0F, 2.0F, 3.0F, 7.0F, new CubeDeformation(1.0F))
                .texOffs(0, 0).addBox(-2.0F, -6.0767F, 56.2503F, 4.0F, 4.0F, 13.0F, new CubeDeformation(1.0F))
                .texOffs(0, 0).addBox(-1.0F, 10.0F, 106.0F, 2.0F, 1.0F, 5.0F, new CubeDeformation(1.0F)), PartPose.offset(0.0F, -16.0F, -43.0F));

        PartDefinition finqueue_r1 = corps.addOrReplaceChild("finqueue_r1", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -3.1296F, -2.3144F, 0.0F, 0.0F, 4.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, 9.0F, 121.0F, 0.829F, 0.0F, 0.0F));

        PartDefinition boutqueuemilieu_r1 = corps.addOrReplaceChild("boutqueuemilieu_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -2.0F, -3.0F, 2.0F, 0.0F, 4.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, 11.0F, 116.0F, 0.3054F, 0.0F, 0.0F));

        PartDefinition basequeue_r1 = corps.addOrReplaceChild("basequeue_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -2.4803F, -3.3826F, 4.0F, 2.0F, 7.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, 11.0F, 100.0F, -0.3054F, 0.0F, 0.0F));

        PartDefinition jambe_r1 = corps.addOrReplaceChild("jambe_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, 1.0F, -5.0F, 6.0F, 3.0F, 9.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, 94.0F, -0.6109F, 0.0F, 0.0F));

        PartDefinition bascorps_r1 = corps.addOrReplaceChild("bascorps_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -2.0F, -5.0F, 6.0F, 5.0F, 7.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, 86.0F, -0.1309F, 0.0F, 0.0F));

        PartDefinition taille4_r1 = corps.addOrReplaceChild("taille4_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -5.0F, -10.0F, 4.0F, 3.0F, 14.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, 76.0F, -0.5672F, 0.0F, 0.0F));

        PartDefinition taille2_r1 = corps.addOrReplaceChild("taille2_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -2.4954F, -7.4392F, 6.0F, 3.0F, 10.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 53.0F, 0.3491F, 0.0F, 0.0F));

        PartDefinition taille_r1 = corps.addOrReplaceChild("taille_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -3.3713F, -7.0505F, 8.0F, 3.0F, 12.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, 42.0F, 0.5672F, 0.0F, 0.0F));

        PartDefinition buste_r1 = corps.addOrReplaceChild("buste_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -4.0F, -3.0F, 10.0F, 7.0F, 7.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, 32.0F, -0.3054F, 0.0F, 0.0F));

        PartDefinition coubas_r1 = corps.addOrReplaceChild("coubas_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -1.969F, -3.0984F, 4.0F, 3.0F, 8.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 24.0F, -0.6109F, 0.0F, 0.0F));

        PartDefinition coumilieu_r1 = corps.addOrReplaceChild("coumilieu_r1", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.2081F, -3.3523F, 0.0F, 2.0F, 7.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 9.0F, 0.3054F, 0.0F, 0.0F));

        PartDefinition tete = bone.addOrReplaceChild("tete", CubeListBuilder.create(), PartPose.offset(0.0F, -12.0F, -48.0F));

        PartDefinition gueulebas_r1 = tete.addOrReplaceChild("gueulebas_r1", CubeListBuilder.create().texOffs(0, 0).addBox(1.0F, 3.8986F, -3.7287F, -2.0F, -4.0F, 3.0F, new CubeDeformation(3.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

        PartDefinition gueulehaut_r1 = tete.addOrReplaceChild("gueulehaut_r1", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.1166F, -6.9023F, 0.0F, -2.0F, 5.0F, new CubeDeformation(3.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

        PartDefinition machoire_r1 = tete.addOrReplaceChild("machoire_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 0.0F, 3.0F, new CubeDeformation(3.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 5.0F, 0.1745F, 0.0F, 0.0F));

        PartDefinition tete_r1 = tete.addOrReplaceChild("tete_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -5.0F, 2.0F, 0.0F, 5.0F, new CubeDeformation(3.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, 5.0F, -0.0873F, 0.0F, 0.0F));

        PartDefinition ecaillesdos = bone.addOrReplaceChild("ecaillesdos", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition ecailles1 = ecaillesdos.addOrReplaceChild("ecailles1", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.0F, -19.0F, -34.0F, 0.2618F, 0.0F, 0.0F));

        PartDefinition ecaille5_r1 = ecailles1.addOrReplaceChild("ecaille5_r1", CubeListBuilder.create().texOffs(35, 5).addBox(2.0F, -1.0F, -1.0F, -2.0F, 1.0F, 0.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 3.0F, 0.0F, -0.0873F, 0.0F));

        PartDefinition ecaille4_r1 = ecailles1.addOrReplaceChild("ecaille4_r1", CubeListBuilder.create().texOffs(35, 5).addBox(2.0F, -2.0F, 0.0F, -2.0F, 2.0F, 0.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, 0.0F, 0.0873F, 0.0F));

        PartDefinition ecaille3_r1 = ecailles1.addOrReplaceChild("ecaille3_r1", CubeListBuilder.create().texOffs(35, 5).addBox(2.0F, -1.0F, 0.0F, -2.0F, 1.0F, 1.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -1.0F, 0.0F, -0.0873F, 0.0F));

        PartDefinition ecaille2_r1 = ecailles1.addOrReplaceChild("ecaille2_r1", CubeListBuilder.create().texOffs(35, 5).addBox(2.0F, 0.0F, 1.0F, -2.0F, 0.0F, 0.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -3.0F, 0.0F, 0.0873F, 0.0F));

        PartDefinition ecailles2 = ecaillesdos.addOrReplaceChild("ecailles2", CubeListBuilder.create(), PartPose.offset(-1.0F, -21.0F, -25.0F));

        PartDefinition ecaille5_r2 = ecailles2.addOrReplaceChild("ecaille5_r2", CubeListBuilder.create().texOffs(35, 5).addBox(2.0F, 0.0F, 0.0F, -2.0F, 0.0F, 1.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, 0.0F, 0.0873F, 0.0F));

        PartDefinition ecaille4_r2 = ecailles2.addOrReplaceChild("ecaille4_r2", CubeListBuilder.create().texOffs(35, 5).addBox(2.0F, -1.0F, 0.0F, -2.0F, 1.0F, 1.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -1.0F, 0.0F, -0.0873F, 0.0F));

        PartDefinition ecaille3_r2 = ecailles2.addOrReplaceChild("ecaille3_r2", CubeListBuilder.create().texOffs(35, 5).addBox(2.0F, 0.0F, 1.0F, -2.0F, 0.0F, 0.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -3.0F, 0.0F, 0.0873F, 0.0F));

        PartDefinition ecailles3 = ecaillesdos.addOrReplaceChild("ecailles3", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.0F, -18.0F, -17.0F, -0.5672F, 0.0F, 0.0F));

        PartDefinition ecaille5_r3 = ecailles3.addOrReplaceChild("ecaille5_r3", CubeListBuilder.create().texOffs(35, 5).addBox(2.0F, 0.0F, 0.0F, -2.0F, 0.0F, 2.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -1.0F, 0.0F, -0.0873F, 0.0F));

        PartDefinition ecaille4_r3 = ecailles3.addOrReplaceChild("ecaille4_r3", CubeListBuilder.create().texOffs(35, 5).addBox(2.0F, -1.0F, 1.0F, -2.0F, 1.0F, 0.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -3.0F, 0.0F, 0.0873F, 0.0F));

        PartDefinition ecailles4 = ecaillesdos.addOrReplaceChild("ecailles4", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.0F, -16.0F, -9.0F, -0.3054F, 0.0F, 0.0F));

        PartDefinition ecaille4_r4 = ecailles4.addOrReplaceChild("ecaille4_r4", CubeListBuilder.create().texOffs(35, 5).addBox(2.0F, -1.0F, -1.0F, -2.0F, 1.0F, 0.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 3.0F, 0.0F, -0.0873F, 0.0F));

        PartDefinition ecaille3_r3 = ecailles4.addOrReplaceChild("ecaille3_r3", CubeListBuilder.create().texOffs(35, 5).addBox(2.0F, -4.0F, 0.0F, -2.0F, 4.0F, 0.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, 0.0F, 0.0873F, 0.0F));

        PartDefinition ecaille2_r2 = ecailles4.addOrReplaceChild("ecaille2_r2", CubeListBuilder.create().texOffs(35, 5).addBox(2.0F, -3.0F, 0.0F, -2.0F, 3.0F, 1.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -1.0F, 0.0F, -0.0873F, 0.0F));

        PartDefinition ecaille1_r1 = ecailles4.addOrReplaceChild("ecaille1_r1", CubeListBuilder.create().texOffs(35, 5).addBox(2.0F, -2.0F, 0.0F, -2.0F, 2.0F, 1.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -3.0F, 0.0F, 0.0873F, 0.0F));

        PartDefinition ecailles5 = ecaillesdos.addOrReplaceChild("ecailles5", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.0F, -16.0F, -1.0F, 0.6109F, 0.0F, 0.0F));

        PartDefinition ecaille5_r4 = ecailles5.addOrReplaceChild("ecaille5_r4", CubeListBuilder.create().texOffs(35, 5).addBox(2.0F, -1.0F, -1.0F, -2.0F, 1.0F, 0.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 3.0F, 0.0F, -0.0873F, 0.0F));

        PartDefinition ecaille4_r5 = ecailles5.addOrReplaceChild("ecaille4_r5", CubeListBuilder.create().texOffs(35, 5).addBox(2.0F, -3.0F, 0.0F, -2.0F, 3.0F, 0.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, 0.0F, 0.0873F, 0.0F));

        PartDefinition ecaille3_r4 = ecailles5.addOrReplaceChild("ecaille3_r4", CubeListBuilder.create().texOffs(35, 5).addBox(2.0F, -2.0F, -1.0F, -2.0F, 2.0F, 2.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -1.0F, 0.0F, -0.0873F, 0.0F));

        PartDefinition ecailles6 = ecaillesdos.addOrReplaceChild("ecailles6", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.0F, -20.0F, -26.0F, 0.3491F, 0.0F, 0.0F));

        PartDefinition ecaille5_r5 = ecailles6.addOrReplaceChild("ecaille5_r5", CubeListBuilder.create().texOffs(35, 5).addBox(4.7027F, 11.2867F, 29.8919F, -2.0F, 0.0F, 2.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 3.0F, 0.0F, -0.0873F, 0.0F));

        PartDefinition ecaille4_r6 = ecailles6.addOrReplaceChild("ecaille4_r6", CubeListBuilder.create().texOffs(35, 5).addBox(-0.7027F, 10.2867F, 30.8919F, -2.0F, 1.0F, 2.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, 0.0F, 0.0873F, 0.0F));

        PartDefinition ecaille3_r5 = ecailles6.addOrReplaceChild("ecaille3_r5", CubeListBuilder.create().texOffs(35, 5).addBox(4.7027F, 9.2867F, 29.8919F, -2.0F, 2.0F, 3.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -1.0F, 0.0F, -0.0873F, 0.0F));

        PartDefinition ecaille2_r3 = ecailles6.addOrReplaceChild("ecaille2_r3", CubeListBuilder.create().texOffs(35, 5).addBox(-0.7027F, 10.2867F, 30.8919F, -2.0F, 1.0F, 2.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -3.0F, 0.0F, 0.0873F, 0.0F));

        PartDefinition ecailles7 = ecaillesdos.addOrReplaceChild("ecailles7", CubeListBuilder.create(), PartPose.offset(-1.0F, -23.0F, 19.0F));

        PartDefinition ecaille6_r1 = ecailles7.addOrReplaceChild("ecaille6_r1", CubeListBuilder.create().texOffs(35, 5).addBox(4.7027F, 11.2867F, 29.8919F, -2.0F, 0.0F, 4.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, -11.2867F, -28.0099F, 0.0F, -0.0873F, 0.0F));

        PartDefinition ecaille5_r6 = ecailles7.addOrReplaceChild("ecaille5_r6", CubeListBuilder.create().texOffs(35, 5).addBox(-0.7027F, 10.2867F, 29.8919F, -2.0F, 1.0F, 3.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, -11.2867F, -30.0099F, 0.0F, 0.0873F, 0.0F));

        PartDefinition ecaille4_r7 = ecailles7.addOrReplaceChild("ecaille4_r7", CubeListBuilder.create().texOffs(35, 5).addBox(4.7027F, 9.2867F, 28.8919F, -2.0F, 2.0F, 0.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, -11.2867F, -32.0099F, 0.0F, -0.0873F, 0.0F));

        PartDefinition ecaille3_r6 = ecailles7.addOrReplaceChild("ecaille3_r6", CubeListBuilder.create().texOffs(35, 5).addBox(-0.7027F, 11.2867F, 28.8919F, -2.0F, 0.0F, 4.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, -11.2867F, -34.0099F, 0.0F, 0.0873F, 0.0F));

        PartDefinition ecailles8 = ecaillesdos.addOrReplaceChild("ecailles8", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.0F, -19.0F, 33.0F, -0.6109F, 0.0F, 0.0F));

        PartDefinition ecaille6_r2 = ecailles8.addOrReplaceChild("ecaille6_r2", CubeListBuilder.create().texOffs(35, 5).addBox(-0.7027F, 9.2867F, 31.8919F, -2.0F, 2.0F, 0.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, -11.2867F, -30.0099F, 0.0F, 0.0873F, 0.0F));

        PartDefinition ecaille5_r7 = ecailles8.addOrReplaceChild("ecaille5_r7", CubeListBuilder.create().texOffs(35, 5).addBox(4.7027F, 10.2867F, 28.8919F, -2.0F, 1.0F, 2.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, -11.2867F, -32.0099F, 0.0F, -0.0873F, 0.0F));

        PartDefinition ecaille4_r8 = ecailles8.addOrReplaceChild("ecaille4_r8", CubeListBuilder.create().texOffs(35, 5).addBox(-0.7027F, 11.2867F, 28.8919F, -2.0F, 0.0F, 6.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, -11.2867F, -34.0099F, 0.0F, 0.0873F, 0.0F));

        PartDefinition ecailles9 = ecaillesdos.addOrReplaceChild("ecailles9", CubeListBuilder.create(), PartPose.offsetAndRotation(1.0F, -16.0F, 42.0F, -3.0106F, 0.0433F, -3.1359F));

        PartDefinition ecaille7_r1 = ecailles9.addOrReplaceChild("ecaille7_r1", CubeListBuilder.create().texOffs(35, 5).addBox(-0.7027F, 9.2867F, 31.8919F, -2.0F, 2.0F, 0.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, -11.2867F, -30.0099F, 0.0F, 0.0873F, 0.0F));

        PartDefinition ecaille6_r3 = ecailles9.addOrReplaceChild("ecaille6_r3", CubeListBuilder.create().texOffs(35, 5).addBox(4.7027F, 11.2867F, 28.8919F, -2.0F, 0.0F, 0.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, -11.2867F, -32.0099F, 0.0F, -0.0873F, 0.0F));

        PartDefinition ecaille5_r8 = ecailles9.addOrReplaceChild("ecaille5_r8", CubeListBuilder.create().texOffs(35, 5).addBox(-0.7027F, 10.2867F, 31.8919F, -2.0F, 1.0F, 3.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, -11.2867F, -34.0099F, 0.0F, 0.0873F, 0.0F));

        PartDefinition ecailles10 = ecaillesdos.addOrReplaceChild("ecailles10", CubeListBuilder.create(), PartPose.offsetAndRotation(1.0F, -11.0F, 50.0F, -2.5306F, 0.0433F, -3.1359F));

        PartDefinition ecaille8_r1 = ecailles10.addOrReplaceChild("ecaille8_r1", CubeListBuilder.create().texOffs(35, 5).addBox(-0.7027F, 8.2867F, 31.8919F, -2.0F, 3.0F, 0.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, -11.2867F, -30.0099F, 0.0F, 0.0873F, 0.0F));

        PartDefinition ecaille7_r2 = ecailles10.addOrReplaceChild("ecaille7_r2", CubeListBuilder.create().texOffs(35, 5).addBox(4.7027F, 9.2867F, 28.8919F, -2.0F, 2.0F, 1.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, -11.2867F, -32.0099F, 0.0F, -0.0873F, 0.0F));

        PartDefinition ecaille6_r4 = ecailles10.addOrReplaceChild("ecaille6_r4", CubeListBuilder.create().texOffs(35, 5).addBox(-0.7027F, 10.2867F, 33.8919F, -2.0F, 1.0F, 1.0F, new CubeDeformation(1.0F))
                .texOffs(35, 5).addBox(-0.7027F, 9.2867F, 36.8919F, -2.0F, 2.0F, 0.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, -11.2867F, -34.0099F, 0.0F, 0.0873F, 0.0F));

        PartDefinition ecailles11 = ecaillesdos.addOrReplaceChild("ecailles11", CubeListBuilder.create(), PartPose.offsetAndRotation(1.0F, -7.0F, 58.0F, -2.836F, 0.0433F, -3.1359F));

        PartDefinition ecaille9_r1 = ecailles11.addOrReplaceChild("ecaille9_r1", CubeListBuilder.create().texOffs(35, 5).addBox(-0.7027F, 9.2867F, 31.8919F, -2.0F, 2.0F, 0.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, -11.2867F, -30.0099F, 0.0F, 0.0873F, 0.0F));

        PartDefinition ecaille8_r2 = ecailles11.addOrReplaceChild("ecaille8_r2", CubeListBuilder.create().texOffs(35, 5).addBox(4.7027F, 10.2867F, 28.8919F, -2.0F, 1.0F, 1.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, -11.2867F, -32.0099F, 0.0F, -0.0873F, 0.0F));

        PartDefinition ecaille7_r3 = ecailles11.addOrReplaceChild("ecaille7_r3", CubeListBuilder.create().texOffs(35, 5).addBox(-0.7027F, 8.2867F, 33.8919F, -2.0F, 3.0F, 1.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, -11.2867F, -34.0099F, 0.0F, 0.0873F, 0.0F));

        PartDefinition ecailles12 = ecaillesdos.addOrReplaceChild("ecailles12", CubeListBuilder.create(), PartPose.offsetAndRotation(1.0F, -6.0F, 65.0F, -3.1415F, 0.0433F, -3.1359F));

        PartDefinition ecaille10_r1 = ecailles12.addOrReplaceChild("ecaille10_r1", CubeListBuilder.create().texOffs(35, 5).addBox(-0.7027F, 10.2867F, 30.8919F, -2.0F, 0.0F, 0.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, -11.2867F, -30.0099F, 0.0F, 0.0873F, 0.0F));

        PartDefinition ecaille9_r2 = ecailles12.addOrReplaceChild("ecaille9_r2", CubeListBuilder.create().texOffs(35, 5).addBox(4.7027F, 10.2867F, 29.8919F, -2.0F, 0.0F, 0.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, -11.2867F, -32.0099F, 0.0F, -0.0873F, 0.0F));

        PartDefinition ecaille8_r3 = ecailles12.addOrReplaceChild("ecaille8_r3", CubeListBuilder.create().texOffs(35, 5).addBox(-0.7027F, 9.2867F, 33.8919F, -2.0F, 1.0F, 0.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, -11.2867F, -34.0099F, 0.0F, 0.0873F, 0.0F));

        PartDefinition ecailles13 = ecaillesdos.addOrReplaceChild("ecailles13", CubeListBuilder.create(), PartPose.offsetAndRotation(1.0F, -6.0F, 71.0F, 2.7927F, 0.0433F, -3.1359F));

        PartDefinition ecaille10_r2 = ecailles13.addOrReplaceChild("ecaille10_r2", CubeListBuilder.create().texOffs(35, 5).addBox(4.7027F, 9.2867F, 29.8919F, -2.0F, 1.0F, 0.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, -11.2867F, -32.0099F, 0.0F, -0.0873F, 0.0F));

        PartDefinition ecaille9_r3 = ecailles13.addOrReplaceChild("ecaille9_r3", CubeListBuilder.create().texOffs(35, 5).addBox(-0.7027F, 10.2867F, 33.8919F, -2.0F, -1.0F, 0.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, -11.2867F, -34.0099F, 0.0F, 0.0873F, 0.0F));

        PartDefinition ecailles14 = ecaillesdos.addOrReplaceChild("ecailles14", CubeListBuilder.create(), PartPose.offsetAndRotation(1.0F, -8.0F, 76.0F, 2.3563F, 0.0433F, -3.1359F));

        PartDefinition ecaille11_r1 = ecailles14.addOrReplaceChild("ecaille11_r1", CubeListBuilder.create().texOffs(35, 5).addBox(4.7027F, 9.2867F, 30.8919F, -2.0F, 0.0F, 0.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, -11.2867F, -32.0099F, 0.0F, -0.0873F, 0.0F));

        PartDefinition ecaille10_r3 = ecailles14.addOrReplaceChild("ecaille10_r3", CubeListBuilder.create().texOffs(35, 5).addBox(-0.7027F, 8.2867F, 34.8919F, -2.0F, 1.0F, -1.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, -11.2867F, -34.0099F, 0.0F, 0.0873F, 0.0F));

        PartDefinition ecaillescou = bone.addOrReplaceChild("ecaillescou", CubeListBuilder.create(), PartPose.offset(0.0F, -20.0F, -40.0F));

        PartDefinition petiteecaillecouavant3_r1 = ecaillescou.addOrReplaceChild("petiteecaillecouavant3_r1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.0F, 0.1808F, -1.4264F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, -1.0F, -0.7988F, 0.5214F, -0.0503F));

        PartDefinition petiteecaillecouavant2_r1 = ecaillescou.addOrReplaceChild("petiteecaillecouavant2_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.1808F, -1.4264F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, -1.0F, -0.7988F, -0.5214F, 0.0503F));

        PartDefinition petiteecaillecouavant_r1 = ecaillescou.addOrReplaceChild("petiteecaillecouavant_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.9599F, 0.0F, 0.0F));

        PartDefinition grosseecaillecoubas_r1 = ecaillescou.addOrReplaceChild("grosseecaillecoubas_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -3.0F, 2.0F, 6.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0472F, 0.0F, 0.0F));

        PartDefinition grosseecaillecotd2_r1 = ecaillescou.addOrReplaceChild("grosseecaillecotd2_r1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-0.2938F, -0.3002F, -0.2442F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-6.0F, 5.0F, -1.0F, -0.5734F, 0.4114F, -1.5485F));

        PartDefinition grosseecaillecotd_r1 = ecaillescou.addOrReplaceChild("grosseecaillecotd_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-2.8612F, -0.8638F, -0.3865F, 4.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 5.0F, 0.0F, -2.0732F, -0.3907F, 1.5903F));

        PartDefinition grosseecaillecotg2_r1 = ecaillescou.addOrReplaceChild("grosseecaillecotg2_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.7062F, -0.3002F, -0.2442F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 5.0F, -1.0F, -0.5734F, -0.4114F, 1.5485F));

        PartDefinition grosseecaillecotg_r1 = ecaillescou.addOrReplaceChild("grosseecaillecotg_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-3.506F, 0.331F, -0.0807F, 4.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 5.0F, 1.0F, -1.097F, -0.4114F, 1.5485F));

        PartDefinition grosseecaillecou_r1 = ecaillescou.addOrReplaceChild("grosseecaillecou_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.6545F, 0.0F, 0.0F));

        PartDefinition petitescailles = ecaillescou.addOrReplaceChild("petitescailles", CubeListBuilder.create(), PartPose.offset(0.0F, -2.0F, 2.0F));

        PartDefinition petiteecaille2_r1 = petitescailles.addOrReplaceChild("petiteecaille2_r1", CubeListBuilder.create().texOffs(0, 0).addBox(1.0F, -2.1958F, 0.6308F, 1.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0036F, 0.0F, 0.0F));

        PartDefinition petiteecaille1_r1 = petitescailles.addOrReplaceChild("petiteecaille1_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -1.1958F, 0.6308F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.2217F, 0.0F, 0.0F));

        PartDefinition fineecaillecoteg = ecaillescou.addOrReplaceChild("fineecaillecoteg", CubeListBuilder.create(), PartPose.offset(6.0F, 5.0F, 1.0F));

        PartDefinition fineecaillecotg_r1 = fineecaillecoteg.addOrReplaceChild("fineecaillecotg_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-2.506F, -0.669F, 0.9193F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-1.506F, -4.669F, 0.9193F, 1.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-1.506F, -2.669F, 0.9193F, 2.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.097F, -0.4114F, 1.5485F));

        PartDefinition fineecaillecoted = ecaillescou.addOrReplaceChild("fineecaillecoted", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.0F, 5.0F, 2.0F, 0.4193F, -0.8413F, -0.5389F));

        PartDefinition fineecaillecotd_r1 = fineecaillecoted.addOrReplaceChild("fineecaillecotd_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-2.506F, -3.669F, 0.9193F, 1.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-1.506F, -4.669F, 0.9193F, 1.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-1.506F, -2.669F, 0.9193F, 2.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.097F, -0.4114F, 1.5485F));

        PartDefinition moustache = bone.addOrReplaceChild("moustache", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition moustacheg = moustache.addOrReplaceChild("moustacheg", CubeListBuilder.create(), PartPose.offset(12.0F, -11.0F, -49.0F));

        PartDefinition finemoustache3_r1 = moustacheg.addOrReplaceChild("finemoustache3_r1", CubeListBuilder.create().texOffs(83, 29).addBox(1.4025F, 0.5234F, 2.1355F, 3.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -1.0F, 6.0F, 2.6799F, -1.228F, -1.9701F));

        PartDefinition finemoustache2_r1 = moustacheg.addOrReplaceChild("finemoustache2_r1", CubeListBuilder.create().texOffs(83, 29).addBox(-1.5975F, 1.5234F, 1.1355F, 4.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -1.0F, 6.0F, -3.1233F, -1.228F, -1.9701F));

        PartDefinition finemoustache1_r1 = moustacheg.addOrReplaceChild("finemoustache1_r1", CubeListBuilder.create().texOffs(83, 29).addBox(-2.0678F, -1.1646F, -0.1248F, 4.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -1.0F, 4.0F, 0.9866F, -0.7892F, 0.0541F));

        PartDefinition moustache4_r1 = moustacheg.addOrReplaceChild("moustache4_r1", CubeListBuilder.create().texOffs(83, 29).addBox(-4.0247F, -2.3515F, 2.5828F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4081F, -1.1397F, 0.7748F));

        PartDefinition moustache3_r1 = moustacheg.addOrReplaceChild("moustache3_r1", CubeListBuilder.create().texOffs(83, 29).addBox(-1.0364F, -0.8075F, -1.3556F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -4.0F, -1.0F, -0.1671F, -0.0794F, 0.4139F));

        PartDefinition moustache2_r1 = moustacheg.addOrReplaceChild("moustache2_r1", CubeListBuilder.create().texOffs(83, 29).addBox(-1.006F, -1.0822F, -0.4948F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, -4.0F, -4.0F, -0.1682F, -1.3659F, 0.3382F));

        PartDefinition moustache1_r1 = moustacheg.addOrReplaceChild("moustache1_r1", CubeListBuilder.create().texOffs(83, 29).addBox(-1.3515F, -0.4329F, 0.743F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, -4.0F, -7.0F, 0.3492F, -0.886F, -0.2749F));

        PartDefinition debutmoustache_r1 = moustacheg.addOrReplaceChild("debutmoustache_r1", CubeListBuilder.create().texOffs(83, 29).addBox(0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F, -4.0F, -9.0F, 0.2233F, -0.2129F, -0.0479F));

        PartDefinition moustached = moustache.addOrReplaceChild("moustached", CubeListBuilder.create(), PartPose.offset(-12.0F, -11.0F, -49.0F));

        PartDefinition finemoustache4_r1 = moustached.addOrReplaceChild("finemoustache4_r1", CubeListBuilder.create().texOffs(83, 29).mirror().addBox(-4.4025F, 0.5234F, 2.1355F, 3.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, -1.0F, 6.0F, 2.6799F, 1.228F, 1.9701F));

        PartDefinition finemoustache3_r2 = moustached.addOrReplaceChild("finemoustache3_r2", CubeListBuilder.create().texOffs(83, 29).mirror().addBox(-2.4025F, 1.5234F, 1.1355F, 4.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, -1.0F, 6.0F, -3.1233F, 1.228F, 1.9701F));

        PartDefinition finemoustache2_r2 = moustached.addOrReplaceChild("finemoustache2_r2", CubeListBuilder.create().texOffs(83, 29).mirror().addBox(-1.9322F, -1.1646F, -0.1248F, 4.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, -1.0F, 4.0F, 0.9866F, 0.7892F, -0.0541F));

        PartDefinition moustache5_r1 = moustached.addOrReplaceChild("moustache5_r1", CubeListBuilder.create().texOffs(83, 29).mirror().addBox(-0.9753F, -2.3515F, 2.5828F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4081F, 1.1397F, -0.7748F));

        PartDefinition moustache4_r2 = moustached.addOrReplaceChild("moustache4_r2", CubeListBuilder.create().texOffs(83, 29).mirror().addBox(-1.9636F, -0.8075F, -1.3556F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, -4.0F, -1.0F, -0.1671F, 0.0794F, -0.4139F));

        PartDefinition moustache3_r2 = moustached.addOrReplaceChild("moustache3_r2", CubeListBuilder.create().texOffs(83, 29).mirror().addBox(-1.994F, -1.0822F, -0.4948F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(6.0F, -4.0F, -4.0F, -0.1682F, 1.3659F, -0.3382F));

        PartDefinition moustache2_r2 = moustached.addOrReplaceChild("moustache2_r2", CubeListBuilder.create().texOffs(83, 29).mirror().addBox(-1.6485F, -0.4329F, 0.743F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(6.0F, -4.0F, -7.0F, 0.3492F, 0.886F, 0.2749F));

        PartDefinition debutmoustache_r2 = moustached.addOrReplaceChild("debutmoustache_r2", CubeListBuilder.create().texOffs(83, 29).mirror().addBox(-2.0F, 0.0F, 1.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(9.0F, -4.0F, -9.0F, 0.2233F, 0.2129F, 0.0479F));

        PartDefinition pattes = bone.addOrReplaceChild("pattes", CubeListBuilder.create(), PartPose.offset(-6.0F, -7.0F, -13.0F));

        PartDefinition pattesavant = pattes.addOrReplaceChild("pattesavant", CubeListBuilder.create(), PartPose.offset(6.0F, 7.0F, 13.0F));

        PartDefinition patted = pattesavant.addOrReplaceChild("patted", CubeListBuilder.create(), PartPose.offset(-6.0F, -7.0F, -13.0F));

        PartDefinition main_r1 = patted.addOrReplaceChild("main_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5482F, -3.3617F, -1.042F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 2.0F, -6.0F, -2.3622F, 0.3189F, 0.2333F));

        PartDefinition avantbras_r1 = patted.addOrReplaceChild("avantbras_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-4.1943F, 0.8283F, -2.4046F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -7.0F, 1.1721F, 0.3189F, 0.2333F));

        PartDefinition bras_r1 = patted.addOrReplaceChild("bras_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -3.0F, -4.0F, 4.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1249F, 0.3189F, 0.2333F));

        PartDefinition epaule_r1 = patted.addOrReplaceChild("epaule_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -5.0F, 0.0F, 4.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.6485F, 0.3189F, 0.2333F));

        PartDefinition patteg = pattesavant.addOrReplaceChild("patteg", CubeListBuilder.create(), PartPose.offset(6.0F, -7.0F, -13.0F));

        PartDefinition main_r2 = patteg.addOrReplaceChild("main_r2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.4518F, -3.3617F, -1.042F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 2.0F, -6.0F, -2.3622F, -0.3189F, -0.2333F));

        PartDefinition avantbras_r2 = patteg.addOrReplaceChild("avantbras_r2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(2.1943F, 0.8283F, -2.4046F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, -7.0F, 1.1721F, -0.3189F, -0.2333F));

        PartDefinition bras_r2 = patteg.addOrReplaceChild("bras_r2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.0F, -3.0F, -4.0F, 4.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1249F, -0.3189F, -0.2333F));

        PartDefinition epaule_r2 = patteg.addOrReplaceChild("epaule_r2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.0F, -5.0F, 0.0F, 4.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.6485F, -0.3189F, -0.2333F));

        PartDefinition pattesarrieres = pattes.addOrReplaceChild("pattesarrieres", CubeListBuilder.create(), PartPose.offset(6.0F, 7.0F, 13.0F));

        PartDefinition patteg2 = pattesarrieres.addOrReplaceChild("patteg2", CubeListBuilder.create(), PartPose.offsetAndRotation(4.0F, -6.0F, 44.0F, 0.0F, 0.0436F, 0.0F));

        PartDefinition pied_r1 = patteg2.addOrReplaceChild("pied_r1", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.0622F, 4.3038F, 0.0F, 2.0F, -3.0F, new CubeDeformation(2.0F)), PartPose.offsetAndRotation(1.0F, 4.0F, 4.0F, 0.6175F, -0.0869F, -0.1515F));

        PartDefinition mollet_r1 = patteg2.addOrReplaceChild("mollet_r1", CubeListBuilder.create().texOffs(0, 0).addBox(1.0F, 2.0F, 1.0F, -1.0F, -1.0F, 5.0F, new CubeDeformation(2.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -2.0F, -0.3861F, -0.0869F, -0.1515F));

        PartDefinition cuisse_r1 = patteg2.addOrReplaceChild("cuisse_r1", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, -2.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(2.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -2.0F, -1.2327F, 0.0886F, -0.2467F));

        PartDefinition patted2 = pattesarrieres.addOrReplaceChild("patted2", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.0F, -6.0F, 44.0F, 0.0F, -0.0436F, 0.0F));

        PartDefinition pied_r2 = patted2.addOrReplaceChild("pied_r2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, -1.0622F, 4.3038F, 0.0F, 2.0F, -3.0F, new CubeDeformation(2.0F)).mirror(false), PartPose.offsetAndRotation(-1.0F, 4.0F, 4.0F, 0.6175F, 0.0869F, 0.1515F));

        PartDefinition mollet_r2 = patted2.addOrReplaceChild("mollet_r2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, 2.0F, 1.0F, -1.0F, -1.0F, 5.0F, new CubeDeformation(2.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -3.0F, -2.0F, -0.3861F, 0.0869F, 0.1515F));

        PartDefinition cuisse_r2 = patted2.addOrReplaceChild("cuisse_r2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, 0.0F, -2.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(2.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -3.0F, -2.0F, -1.2327F, -0.0886F, 0.2467F));

        PartDefinition corne = bone.addOrReplaceChild("corne", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition corneg = corne.addOrReplaceChild("corneg", CubeListBuilder.create(), PartPose.offsetAndRotation(6.0F, -20.0F, -38.0F, -0.3054F, 0.0F, 0.1309F));

        PartDefinition boutcorne_r1 = corneg.addOrReplaceChild("boutcorne_r1", CubeListBuilder.create().texOffs(0, 0).addBox(0.1271F, 1.4792F, -0.3623F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -4.0F, 2.0F, 0.1018F, 0.6101F, 0.0144F));

        PartDefinition corne_r1 = corneg.addOrReplaceChild("corne_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.2229F, 0.5208F, -1.3831F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 0.5818F, 0.6101F, 0.0144F));

        PartDefinition basecorne_r1 = corneg.addOrReplaceChild("basecorne_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, -5.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, -1.0F, 0.7563F, 0.6101F, 0.0144F));

        PartDefinition corned = corne.addOrReplaceChild("corned", CubeListBuilder.create(), PartPose.offsetAndRotation(-6.0F, -20.0F, -38.0F, -0.3054F, 0.0F, -0.1309F));

        PartDefinition boutcorne_r2 = corned.addOrReplaceChild("boutcorne_r2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.1271F, 1.4792F, -0.3623F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0F, -4.0F, 2.0F, 0.1018F, -0.6101F, -0.0144F));

        PartDefinition corne_r2 = corned.addOrReplaceChild("corne_r2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-0.7771F, 0.5208F, -1.3831F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 0.5818F, -0.6101F, -0.0144F));

        PartDefinition basecorne_r2 = corned.addOrReplaceChild("basecorne_r2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, 0.0F, -5.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(1.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, 0.0F, -1.0F, 0.7563F, -0.6101F, -0.0144F));

        PartDefinition ecaillequeue = bone.addOrReplaceChild("ecaillequeue", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition baseecaillearriere = ecaillequeue.addOrReplaceChild("baseecaillearriere", CubeListBuilder.create(), PartPose.offset(0.0F, -12.0F, 77.0F));

        PartDefinition baseecaillequeuevertibas_r1 = baseecaillearriere.addOrReplaceChild("baseecaillequeuevertibas_r1", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, -4.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.2217F, 0.0F, 0.0F));

        PartDefinition baseecaillequeuevertibas_r2 = baseecaillearriere.addOrReplaceChild("baseecaillequeuevertibas_r2", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 1.0F, -1.0F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.9599F, 0.0F, 0.0F));

        PartDefinition ecaille1pixel = ecaillequeue.addOrReplaceChild("ecaille1pixel", CubeListBuilder.create(), PartPose.offset(0.0F, -19.0F, 85.0F));

        PartDefinition petiteecaillesqueue_r1 = ecaille1pixel.addOrReplaceChild("petiteecaillesqueue_r1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, -1.51F, -0.2228F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -2.0F, 2.0F, -1.7206F, -1.0406F, 1.744F));

        PartDefinition petiteecaillesqueue_r2 = ecaille1pixel.addOrReplaceChild("petiteecaillesqueue_r2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-0.2807F, -0.5308F, -0.8568F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -5.0F, 1.0F, 1.015F, -0.1623F, -0.1523F));

        PartDefinition petiteecaillesqueue_r3 = ecaille1pixel.addOrReplaceChild("petiteecaillesqueue_r3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, 0.0F, 2.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, -3.0F, 0.48F, 0.0F, 0.0F));

        PartDefinition ecaillequeuepetite3 = ecaillequeue.addOrReplaceChild("ecaillequeuepetite3", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -15.0F, 80.0F, -1.2574F, 0.8335F, -0.7761F));

        PartDefinition petiteecaillesqueue_r4 = ecaillequeuepetite3.addOrReplaceChild("petiteecaillesqueue_r4", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.0F, -1.0F, -1.0F, 0.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 0).mirror().addBox(-1.0F, -2.0F, -1.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 0).mirror().addBox(-1.0F, -1.0F, -1.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.5529F, 0.6484F, -0.5593F));

        PartDefinition ecaillequeuepetite2 = ecaillequeue.addOrReplaceChild("ecaillequeuepetite2", CubeListBuilder.create(), PartPose.offset(0.0F, -12.0F, 77.0F));

        PartDefinition petiteecaillesqueue_r5 = ecaillequeuepetite2.addOrReplaceChild("petiteecaillesqueue_r5", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.0F, -1.0F, 1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 0).mirror().addBox(-1.0F, -2.0F, -1.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.5529F, 0.6484F, -0.5593F));

        PartDefinition ecaillequeuepetite = ecaillequeue.addOrReplaceChild("ecaillequeuepetite", CubeListBuilder.create(), PartPose.offset(0.0F, -12.0F, 77.0F));

        PartDefinition petiteecaillesqueue_r6 = ecaillequeuepetite.addOrReplaceChild("petiteecaillesqueue_r6", CubeListBuilder.create().texOffs(0, 0).addBox(1.0F, -1.0F, 1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(1.0F, -2.0F, -1.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.5529F, -0.6484F, 0.5593F));

        PartDefinition baseecaillequeuehori = ecaillequeue.addOrReplaceChild("baseecaillequeuehori", CubeListBuilder.create(), PartPose.offset(0.0F, -12.0F, 77.0F));

        PartDefinition baseecaillequeuehoribase_r1 = baseecaillequeuehori.addOrReplaceChild("baseecaillequeuehoribase_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, 0.2633F, 0.8218F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 3.0F, 0.7897F, -0.0924F, -0.0928F));

        PartDefinition baseecaillequeuehoribase_r2 = baseecaillequeuehori.addOrReplaceChild("baseecaillequeuehoribase_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, 0.2633F, -2.1782F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 3.0F, 1.0951F, -0.0924F, -0.0928F));

        PartDefinition baseecaillequeuehoribase_r3 = baseecaillequeuehori.addOrReplaceChild("baseecaillequeuehoribase_r3", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, 1.0F, -1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.964F, -0.0749F, -0.1074F));

        PartDefinition baseecaillequeueverti = ecaillequeue.addOrReplaceChild("baseecaillequeueverti", CubeListBuilder.create(), PartPose.offset(0.0F, -12.0F, 77.0F));

        PartDefinition baseecaillequeuevertibase_r1 = baseecaillequeueverti.addOrReplaceChild("baseecaillequeuevertibase_r1", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -2.9176F, -2.6046F, 0.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 2.0F, 0.9671F, -0.0998F, -0.1434F));

        PartDefinition baseecaillequeuevertibase_r2 = baseecaillequeueverti.addOrReplaceChild("baseecaillequeuevertibase_r2", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -2.0F, -1.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(0.0F, -1.0F, 4.0F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.9599F, 0.0F, 0.0F));

        PartDefinition baseecaillequeuevertibase_r3 = baseecaillequeueverti.addOrReplaceChild("baseecaillequeuevertibase_r3", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -2.1942F, -4.8989F, 0.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 6.0F, 0.3491F, 0.0F, 0.0F));

        PartDefinition boutqueue = ecaillequeue.addOrReplaceChild("boutqueue", CubeListBuilder.create(), PartPose.offset(0.0F, -22.0F, 87.0F));

        PartDefinition baseecaillequeuehoribout_r1 = boutqueue.addOrReplaceChild("baseecaillequeuehoribout_r1", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -0.9479F, -1.3705F, 0.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 2.9671F, -1.4399F, -1.5708F));

        PartDefinition baseecaillequeuehorimilieu_r1 = boutqueue.addOrReplaceChild("baseecaillequeuehorimilieu_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 1.1001F, -4.1755F, 2.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, -2.0F, 0.534F, 0.1886F, 0.1104F));

        PartDefinition baseecaillequeuehorimilieu_r2 = boutqueue.addOrReplaceChild("baseecaillequeuehorimilieu_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 1.1001F, -0.1755F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, -2.0F, 0.5236F, 0.0F, 0.0F));

        PartDefinition baseecaillequeuevertibout_r1 = boutqueue.addOrReplaceChild("baseecaillequeuevertibout_r1", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -3.9223F, -0.9789F, 0.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 3.0F, 1.7006F, 0.017F, -0.1298F));

        PartDefinition baseecaillequeuevertimilieu_r1 = boutqueue.addOrReplaceChild("baseecaillequeuevertimilieu_r1", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.3457F, -0.7828F, 0.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(0.0F, 0.3457F, -3.7828F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, -3.0F, 0.5273F, 0.1133F, 0.0657F));

        return LayerDefinition.create(meshdefinition, 4096, 4096);
    }

    @Override
    public void setupAnim(AsianDragonEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        aretirer.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
