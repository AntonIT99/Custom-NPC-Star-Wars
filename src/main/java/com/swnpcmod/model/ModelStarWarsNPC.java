package com.swnpcmod.model;

import noppes.npcs.client.model.ModelNPCMale;
import noppes.npcs.constants.EnumAnimation;
import noppes.npcs.entity.EntityNPCInterface;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public abstract class ModelStarWarsNPC extends ModelNPCMale
{
    protected float scaleFactor = 0.0F;
    protected ModelStarWarsNPC(boolean alex)
    {
        super(0.0F, alex);
    }

    protected void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    public ModelStarWarsNPC scale(float factor)
    {
        scaleFactor = factor;
        return this;
    }

    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
    {
        EntityNPCInterface npc = (EntityNPCInterface) entity;
        isDancing = npc.currentAnimation == EnumAnimation.DANCING;
        isSleeping = npc.isPlayerSleeping();

        super.setRotationAngles(par1, par2, par3, par4, par5, par6, entity);

        if(npc.currentAnimation == EnumAnimation.CRY)
            bipedHeadwear.rotateAngleX = bipedHead.rotateAngleX = 0.7f;

        else if(npc.currentAnimation == EnumAnimation.HUG){
            setRotationAnglesHug(par1, par2, par3, par4, par5, par6, entity, this);
        }

        else if(npc.currentAnimation == EnumAnimation.CRAWLING)
            setRotationAnglesCrawling(par1, par2, par3, par4, par5, par6, entity, this);

        else if(npc.currentAnimation == EnumAnimation.WAVING){
            bipedRightArm.rotateAngleX = -0.1f;
            bipedRightArm.rotateAngleY = 0;
            bipedRightArm.rotateAngleZ = (float) (Math.PI - 1f  - Math.sin(entity.ticksExisted * 0.27f) * 0.5f );
        }
    }

    private static void setRotationAnglesHug(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity, ModelBiped model)
    {
        float f6 = MathHelper.sin(model.onGround * 3.141593F);
        float f7 = MathHelper.sin((1.0F - (1.0F - model.onGround) * (1.0F - model.onGround)) * 3.141593F);
        model.bipedRightArm.rotateAngleZ = 0.0F;
        model.bipedLeftArm.rotateAngleZ = 0.0F;
        model.bipedRightArm.rotateAngleY = -(0.1F - f6 * 0.6F);
        model.bipedLeftArm.rotateAngleY = 0.1F;
        model.bipedRightArm.rotateAngleX = -1.570796F;
        model.bipedLeftArm.rotateAngleX = -1.570796F;
        model.bipedRightArm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
        model.bipedRightArm.rotateAngleZ += MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
        model.bipedLeftArm.rotateAngleZ -= MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
        model.bipedRightArm.rotateAngleX += MathHelper.sin(par3 * 0.067F) * 0.05F;
        model.bipedLeftArm.rotateAngleX -= MathHelper.sin(par3 * 0.067F) * 0.05F;
    }

    private static void setRotationAnglesCrawling(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity, ModelBiped model)
    {
        model.bipedHead.rotateAngleZ = -par4 / (180F / (float)Math.PI);
        model.bipedHead.rotateAngleY = 0;
        model.bipedHead.rotateAngleX = -55 / (180F / (float)Math.PI);

        model.bipedHeadwear.rotateAngleX = model.bipedHead.rotateAngleX;
        model.bipedHeadwear.rotateAngleY = model.bipedHead.rotateAngleY;
        model.bipedHeadwear.rotateAngleZ = model.bipedHead.rotateAngleZ;

        if(par2 > 0.25)
            par2 = 0.25f;
        float movement = MathHelper.cos(par1 * 0.8f + (float)Math.PI) * par2;

        model.bipedLeftArm.rotateAngleX = 180 / (180F / (float)Math.PI) - movement * 0.25f;
        model.bipedLeftArm.rotateAngleY = movement * -0.46f;
        model.bipedLeftArm.rotateAngleZ = movement * -0.2f;
        model.bipedLeftArm.rotationPointY = 2 - movement * 9.0F;

        model.bipedRightArm.rotateAngleX = 180 / (180F / (float)Math.PI) + movement * 0.25f;
        model.bipedRightArm.rotateAngleY = movement * -0.4f;
        model.bipedRightArm.rotateAngleZ = movement * -0.2f;
        model.bipedRightArm.rotationPointY = 2 + movement * 9.0F;

        model.bipedBody.rotateAngleY = movement * 0.1f;
        model.bipedBody.rotateAngleX = 0;
        model.bipedBody.rotateAngleZ = movement * 0.1f;

        model.bipedLeftLeg.rotateAngleX = movement * 0.1f;
        model.bipedLeftLeg.rotateAngleY = movement * 0.1f;
        model.bipedLeftLeg.rotateAngleZ = -7 / (180F / (float)Math.PI) - movement * 0.25f;
        model.bipedLeftLeg.rotationPointY = 10.4f + movement * 9.0F;
        model.bipedLeftLeg.rotationPointZ = movement * 0.6f - 0.01f;

        model.bipedRightLeg.rotateAngleX = movement * -0.1f;
        model.bipedRightLeg.rotateAngleY = movement * 0.1f;
        model.bipedRightLeg.rotateAngleZ = 7 / (180F / (float)Math.PI) - movement * 0.25f;
        model.bipedRightLeg.rotationPointY = 10.4f - movement * 9.0F;
        model.bipedRightLeg.rotationPointZ = movement * -0.6f - 0.01f;
    }
}
