package com.swnpcmod.model;

import net.minecraft.client.model.ModelRenderer;

public class ModelTwilek extends ModelStarWarsNPC
{
    public ModelRenderer frontalL;
    public ModelRenderer frontalR;
    public ModelRenderer tailBaseL;
    public ModelRenderer tailBaseR;
    public ModelRenderer headTailL;
    public ModelRenderer headTailR;
    public ModelRenderer tailLowerL;
    public ModelRenderer tailLowerR;
    public ModelRenderer spikeL;
    public ModelRenderer spikeR;

    public ModelTwilek(float scaleFactor)
    {
        super(scaleFactor);
        textureWidth = 96;
        textureHeight = 96;

        jacket = new ModelRenderer(this, 16, 32);
        bipedBody = new ModelRenderer(this, 16, 16); // Torso
        chest = new ModelRenderer(this, 1, 66); // Chest
        leftSleeve = new ModelRenderer(this, 48, 48);
        bipedLeftArm = new ModelRenderer(this, 32, 48); // ArmL
        rightSleeve = new ModelRenderer(this, 40, 32);
        bipedRightArm = new ModelRenderer(this, 40, 16); // ArmR
        rightPants = new ModelRenderer(this, 0, 32);
        bipedRightLeg = new ModelRenderer(this, 0, 16); // LegR
        leftPants = new ModelRenderer(this, 0, 48);
        bipedLeftLeg = new ModelRenderer(this, 16, 48); // LegL
        bipedHead = new ModelRenderer(this, 0, 0); // Head
        spikeL = new ModelRenderer(this, 4, 71); // SpikeL
        spikeR = new ModelRenderer(this, 4, 71); // SpikeR
        tailBaseR = new ModelRenderer(this, 14, 66); // TailBaseR
        headTailR = new ModelRenderer(this, 33, 66); // HeadTailR
        tailLowerR = new ModelRenderer(this, 47, 66); // TailLowerR
        tailBaseL = new ModelRenderer(this, 14, 66); // TailBaseL
        headTailL = new ModelRenderer(this, 33, 66); // HeadTailL
        tailLowerL = new ModelRenderer(this, 47, 66); // TailLowerL
        frontalL = new ModelRenderer(this, 2, 79); // FrontalL
        frontalR = new ModelRenderer(this, 2, 79); // FrontalR
        bipedHeadwear = new ModelRenderer(this, 32, 0);

        jacket.setRotationPoint(0.0F, 0.0F, 0.0F);
        jacket.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, scaleFactor + 0.2F);

        bipedBody.addBox(-4F, 0F, -2F, 8, 12, 4, scaleFactor); // Torso
        bipedBody.setRotationPoint(0F, 0F, 0F);
        bipedBody.addChild(jacket);

        chest.addBox(0F, 0F, 0F, 6, 3, 1, scaleFactor); // Chest
        chest.setRotationPoint(-3F, 2F, -2.9F);

        leftSleeve.setRotationPoint(0F, 0F, 0F);
        leftSleeve.addBox(-1.0F, -2.0F, -2.0F, 3, 12, 4, scaleFactor + 0.2F);

        bipedLeftArm.addBox(-1F, -2F, -2F, 3, 12, 4, scaleFactor); // ArmL
        bipedLeftArm.setRotationPoint(5F, 2F, 0F);
        bipedLeftArm.addChild(leftSleeve);

        rightSleeve.setRotationPoint(0F, 0F, 0F);
        rightSleeve.addBox(-2.0F, -2.0F, -2.0F, 3, 12, 4, scaleFactor + 0.2F);

        bipedRightArm.addBox(-2F, -2F, -2F, 3, 12, 4, scaleFactor); // ArmR
        bipedRightArm.setRotationPoint(-5F, 2F, 0F);
        bipedRightArm.addChild(rightSleeve);

        rightPants.setRotationPoint(-0.1F, 0F, 0.0F);
        rightPants.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, scaleFactor + 0.2F);

        bipedRightLeg.addBox(-2F, 0F, -2F, 4, 12, 4, scaleFactor); // LegR
        bipedRightLeg.setRotationPoint(-1.9F, 12F, 0.0F);
        bipedRightLeg.addChild(rightPants);

        leftPants.setRotationPoint(0.1F, 0F, 0.0F);
        leftPants.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, scaleFactor + 0.2F);

        bipedLeftLeg.addBox(-2F, 0F, -2F, 4, 12, 4, scaleFactor); // LegL
        bipedLeftLeg.setRotationPoint(1.9F, 12F, 0.0F);
        bipedLeftLeg.addChild(leftPants);

        bipedHead.addBox(-4F, -8F, -4F, 8, 8, 8, scaleFactor); // Head
        bipedHead.setRotationPoint(0F, 0F, 0F);

        spikeL.addBox(0F, 0F, 0F, 2, 3, 2, scaleFactor); // SpikeL
        spikeL.setRotationPoint(3.0F, -5.8F, -1.8F);
        setRotateAngle(spikeL, 0.0F, 0.0F, 0.4461061568097506F);

        spikeR.addBox(0F, 0F, 0F, 2, 3, 2, scaleFactor); // SpikeR
        spikeR.setRotationPoint(-4.8F, -4.8F, -1.8F);
        setRotateAngle(spikeR, 0.0F, 0.0F, -0.4461061568097506F);

        tailBaseR.addBox(0F, 0F, 0F, 4, 5, 5, scaleFactor); // TailBaseR
        tailBaseR.setRotationPoint(-4.5F, -7.2F, -0.2F);
        setRotateAngle(tailBaseR, 0.18203784098300857F, 0.0F, 0.0F);

        headTailR.addBox(0F, 1.9F, 1.1F, 3, 10, 3, scaleFactor); // HeadTailR
        headTailR.setRotationPoint(0.4F, -0.2F, 0.9F);
        setRotateAngle(headTailR, -0.08325220532012952F, 0.0F, 0.0F);

        tailLowerR.addBox(0F, 0F, 0F, 2, 6, 2, scaleFactor); // TailLowerR
        tailLowerR.setRotationPoint(0.5F, 11.5F, 1.7F);
        setRotateAngle(tailLowerR, -0.08868795731309655F, 0.0F, 0.0F);

        tailBaseL.addBox(0F, 0F, 0F, 4, 5, 5, scaleFactor); // TailBaseL
        tailBaseL.setRotationPoint(0.5F, -7.2F, -0.2F);
        setRotateAngle(tailBaseL, 0.18203784098300857F, 0.0F, 0.0F);

        headTailL.addBox(0F, 1.9F, 1.1F, 3, 10, 3, scaleFactor); // HeadTailL
        headTailL.setRotationPoint(0.6F, -0.2F, 0.9F);
        setRotateAngle(headTailL, -0.08325220532012952F, 0.0F, 0.0F);

        tailLowerL.addBox(0F, 0F, 0F, 2, 6, 2, scaleFactor); // TailLowerL
        tailLowerL.setRotationPoint(0.5F, 11.5F, 1.7F);
        setRotateAngle(tailLowerL, -0.08866272600131193F, 0.0F, 0.0F);

        frontalL.addBox(0F, 0F, 0F, 4, 4, 4, scaleFactor); // FrontalL
        frontalL.setRotationPoint(0.3F, -8.6F, -4.4F);

        frontalR.addBox(0F, 0F, 0F, 4, 4, 4, scaleFactor); // FrontalR
        frontalR.setRotationPoint(-4.3F, -8.6F, -4.4F);

        bipedHeadwear.addBox(-4F, -8F, -4F, 8, 8, 8, scaleFactor + 0.2F);
        bipedHeadwear.setRotationPoint(0.0F, 0.0F, 0.0F);

        spikeR.mirror = true;
        tailLowerR.mirror = true;
        headTailR.mirror = true;
        tailBaseR.mirror = true;
        frontalR.mirror = true;

        headTailR.addChild(tailLowerR);
        headTailL.addChild(tailLowerL);
        tailBaseL.addChild(headTailL);
        tailBaseR.addChild(headTailR);
        bipedHead.addChild(spikeR);
        bipedHead.addChild(spikeL);
        bipedHead.addChild(tailBaseR);
        bipedHead.addChild(tailBaseL);
        bipedHead.addChild(frontalL);
        bipedHead.addChild(frontalR);
        bipedBody.addChild(chest);
    }
}
