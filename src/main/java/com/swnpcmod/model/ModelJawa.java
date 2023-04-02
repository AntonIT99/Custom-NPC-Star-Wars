package com.swnpcmod.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelJawa extends ModelStarWarsNPC
{
    public ModelRenderer hood;
    public ModelRenderer hood2;
    public ModelRenderer hood3;
    public ModelRenderer leftEye;
    public ModelRenderer rightEye;

    public ModelJawa(float scaleFactor)
    {
        super(scaleFactor);
        textureWidth = 128;
        textureHeight = 128;
        
        leftPants = new ModelRenderer(this, 0, 48);
        leftPants.setRotationPoint(0.1F, 0F, 0.0F);
        leftPants.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, scaleFactor + 0.2F);
        bipedLeftLeg = new ModelRenderer(this, 16, 48);
        bipedLeftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
        bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, scaleFactor);
        bipedLeftArm = new ModelRenderer(this, 32, 48);
        bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 3, 12, 4, scaleFactor);
        bipedRightLeg = new ModelRenderer(this, 0, 16);
        bipedRightLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
        bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, scaleFactor);
        leftSleeve = new ModelRenderer(this, 48, 48);
        leftSleeve.setRotationPoint(0F, 0F, 0F);
        leftSleeve.addBox(-1.0F, -2.0F, -2.0F, 3, 12, 4, scaleFactor + 0.2F);
        bipedBody = new ModelRenderer(this, 16, 16);
        bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
        bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, scaleFactor);
        jacket = new ModelRenderer(this, 16, 32);
        jacket.setRotationPoint(0.0F, 0.0F, 0.0F);
        jacket.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, scaleFactor + 0.2F);
        hood2 = new ModelRenderer(this, 50, 80);
        hood2.setRotationPoint(0.0F, -8.8F, -2.1F);
        hood2.addBox(-2.5F, 0.0F, 0.0F, 5, 6, 7, scaleFactor);
        setRotateAngle(hood2, 0.0911061832922575F, 0.0F, 0.0F);
        hood3 = new ModelRenderer(this, 50, 74);
        hood3.setRotationPoint(0.0F, 0.0F, 7.0F);
        hood3.addBox(-1.0F, 0.0F, 0.0F, 2, 2, 1, scaleFactor);
        setRotateAngle(hood3, -0.13665927909957545F, 0.0F, 0.0F);
        bipedHeadwear = new ModelRenderer(this, 32, 0);
        bipedHeadwear.setRotationPoint(0.0F, 0.0F, 0.0F);
        bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, scaleFactor + 0.2F);
        hood = new ModelRenderer(this, 11, 65);
        hood.setRotationPoint(0.0F, 0.0F, 0.0F);
        hood.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, scaleFactor + 0.1F);
        bipedHead = new ModelRenderer(this, 0, 0);
        bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
        bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, scaleFactor);
        rightSleeve = new ModelRenderer(this, 40, 32);
        rightSleeve.setRotationPoint(0F, 0F, 0F);
        rightSleeve.addBox(-2.0F, -2.0F, -2.0F, 3, 12, 4, scaleFactor + 0.2F);
        bipedRightArm = new ModelRenderer(this, 40, 16);
        bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        bipedRightArm.addBox(-2.0F, -2.0F, -2.0F, 3, 12, 4, scaleFactor);
        rightPants = new ModelRenderer(this, 0, 32);
        rightPants.setRotationPoint(-0.1F, 0F, 0.0F);
        rightPants.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, scaleFactor + 0.2F);

        hood.addChild(hood2);
        hood2.addChild(hood3);
        bipedHead.addChild(hood);
        bipedBody.addChild(jacket);
        bipedLeftArm.addChild(leftSleeve);
        bipedRightArm.addChild(rightSleeve);
        bipedLeftLeg.addChild(leftPants);
        bipedRightLeg.addChild(rightPants);

        leftEye = new ModelRenderer(this, 1, 4);
        rightEye = new ModelRenderer(this, 1, 1);
        leftEye.addBox(1F, -4F, -4.1F, 1, 1, 1, 0F);
        leftEye.setRotationPoint(0F, 0F, 0F);
        rightEye.addBox(-2F, -4F, -4.1F, 1, 1, 1, 0F);
        rightEye.setRotationPoint(0F, 0F, 0F);
    }

    @Override
    public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        super.render(par1Entity, par2, par3, par4, par5, par6, par7);
        leftEye.rotateAngleX = rightEye.rotateAngleX = bipedHead.rotateAngleX;
        leftEye.rotateAngleY = rightEye.rotateAngleY = bipedHead.rotateAngleY;
        leftEye.rotateAngleZ = rightEye.rotateAngleZ = bipedHead.rotateAngleZ;
        leftEye.rotationPointX = rightEye.rotationPointX = bipedHead.rotationPointX;
        leftEye.rotationPointY = rightEye.rotationPointY = bipedHead.rotationPointY;
        leftEye.rotationPointZ = rightEye.rotationPointZ = bipedHead.rotationPointZ;
        renderGlowingPart(leftEye, par7);
        renderGlowingPart(rightEye, par7);
    }
}
