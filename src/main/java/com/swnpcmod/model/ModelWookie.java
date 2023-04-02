package com.swnpcmod.model;

import net.minecraft.client.model.ModelRenderer;

public class ModelWookie extends ModelStarWarsNPC
{
    private final ModelRenderer leftFoot;
    private final ModelRenderer rightFoot;
    private final ModelRenderer hair;

    public ModelWookie(float scaleFactor)
    {
        super(scaleFactor);
        textureWidth = 96;
        textureHeight = 96;
        
        bipedHeadwear = new ModelRenderer(this, 32, 0);
        leftSleeve = new ModelRenderer(this, 48, 49);
        bipedLeftArm = new ModelRenderer(this, 32, 49);
        rightSleeve = new ModelRenderer(this, 40, 33);
        bipedRightArm = new ModelRenderer(this, 40, 17);
        leftPants = new ModelRenderer(this, 0, 49);
        bipedLeftLeg = new ModelRenderer(this, 16, 49);
        leftFoot = new ModelRenderer(this, 47, 70);
        rightPants = new ModelRenderer(this, 0, 33);
        bipedRightLeg = new ModelRenderer(this, 0, 17);
        rightFoot = new ModelRenderer(this, 47, 70);
        bipedHead = new ModelRenderer(this, 0, 0);
        hair = new ModelRenderer(this, 11, 66);
        jacket = new ModelRenderer(this, 16, 33);
        bipedBody = new ModelRenderer(this, 16, 17);

        bipedHeadwear.setRotationPoint(0.0F, -1.0F, 0.0F);
        bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8, 9, 8, scaleFactor + 0.2F);

        leftSleeve.setRotationPoint(0F, 0F, 0F);
        leftSleeve.addBox(-2.0F, -2.0F, -2.0F, 3, 12, 4, scaleFactor + 0.25F);

        bipedLeftArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        bipedLeftArm.addBox(-2.0F, -2.0F, -2.0F, 3, 12, 4, scaleFactor);
        bipedLeftArm.addChild(leftSleeve);

        rightSleeve.setRotationPoint(0F, 0F, 0F);
        rightSleeve.addBox(-1.0F, -2.0F, -2.0F, 3, 12, 4, scaleFactor + 0.25F);

        bipedRightArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        bipedRightArm.addBox(-1.0F, -2.0F, -2.0F, 3, 12, 4, scaleFactor);
        bipedRightArm.addChild(rightSleeve);

        leftPants.setRotationPoint(0.1F, 0F, 0F);
        leftPants.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, scaleFactor + 0.2F);

        bipedLeftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
        bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, scaleFactor);
        bipedLeftLeg.addChild(leftPants);

        leftFoot.setRotationPoint(0.0F, 8.0F, -1.5F);
        bipedLeftLeg.addChild(leftFoot);
        leftFoot.addBox(-2.0F, 0.0F, -1.5F, 4, 4, 5, scaleFactor + 0.1F);

        rightPants.setRotationPoint(-0.1F, 0F, 0F);
        rightPants.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, scaleFactor + 0.2F);

        bipedRightLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
        bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, scaleFactor);
        bipedRightLeg.addChild(rightPants);

        rightFoot.setRotationPoint(0.0F, 8.0F, -1.5F);
        bipedRightLeg.addChild(rightFoot);
        rightFoot.addBox(-2.0F, 0.0F, -1.5F, 4, 4, 5, scaleFactor + 0.1F);

        bipedHead.setRotationPoint(0.0F, -1.0F, 0.0F);
        bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 9, 8, scaleFactor);

        hair.setRotationPoint(0.0F, 0.0F, 0.0F);
        bipedHead.addChild(hair);
        hair.addBox(-4.0F, -8.0F, -4.0F, 8, 17, 8, scaleFactor + 0.2F);

        jacket.setRotationPoint(0.0F, 0.0F, 0.0F);
        jacket.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, scaleFactor + 0.2F);

        bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
        bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, scaleFactor);
        bipedBody.addChild(jacket);
    }
}