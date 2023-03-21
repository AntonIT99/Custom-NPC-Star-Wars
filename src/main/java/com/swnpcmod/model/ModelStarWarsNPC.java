package com.swnpcmod.model;

import noppes.npcs.client.model.ModelNPCMale;
import noppes.npcs.constants.EnumAnimation;
import noppes.npcs.constants.EnumJobType;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.roles.JobPuppet;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;

public abstract class ModelStarWarsNPC extends ModelNPCMale
{
    protected ModelStarWarsNPC(float scaleFactor, boolean alex)
    {
        super(scaleFactor, alex);
    }

    protected void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        super.render(par1Entity, par2, par3, par4, par5, par6, par7);
    }

    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
    {
        isDancing = false;
        isSleeping = false;

        setInitialAngles();
        setRotationAnglesBipedEntity(par1, par2, par3, par4, par5, par6, entity);

        if(entity instanceof EntityPlayer)
            isSleeping = ((EntityPlayer)entity).isPlayerSleeping();

        if (entity instanceof EntityNPCInterface)
        {
            EntityNPCInterface npc = (EntityNPCInterface) entity;
            isDancing = npc.currentAnimation == EnumAnimation.DANCING;
            aimedBow = npc.currentAnimation == EnumAnimation.AIMING;
            isSleeping = npc.isPlayerSleeping();

            super.setRotationAngles(par1, par2, par3, par4, par5, par6, entity);

            switch (npc.currentAnimation)
            {
                case CRAWLING:
                    setRotationAnglesCrawling(par1, par2, par3, par4, par5, par6, entity);
                    break;
                case HUG:
                    setRotationAnglesHug(par1, par2, par3, par4, par5, par6, entity);
                    break;
                case WAVING:
                    setRotationAnglesWaving(par1, par2, par3, par4, par5, par6, entity);
                    break;
                case CRY:
                    setRotationAnglesCry(par1, par2, par3, par4, par5, par6, entity);
                    break;
            }

            if (npc.advanced.job == EnumJobType.Puppet)
            {
                setRotationAnglesPuppet(par1, par2, par3, par4, par5, par6, npc);
            }
        }
    }

    protected void setInitialAngles()
    {
        // Head
        this.bipedHead.rotateAngleZ = 0F;
        this.bipedHeadwear.rotateAngleZ = 0F;

        // Body
        this.bipedBody.rotationPointX = 0F;
        this.bipedBody.rotationPointY = 0F;
        this.bipedBody.rotationPointZ = 0F;
        this.bipedBody.rotateAngleX = 0F;
        this.bipedBody.rotateAngleY = 0F;
        this.bipedBody.rotateAngleZ = 0F;

        // Legs
        this.bipedLeftLeg.rotateAngleX = 0F;
        this.bipedLeftLeg.rotateAngleY = 0F;
        this.bipedLeftLeg.rotateAngleZ = 0F;
        this.bipedRightLeg.rotateAngleX = 0F;
        this.bipedRightLeg.rotateAngleY = 0F;
        this.bipedRightLeg.rotateAngleZ = 0F;

        // Arms
        this.bipedLeftArm.rotationPointY = 2F;
        this.bipedLeftArm.rotationPointZ = 0F;
        this.bipedRightArm.rotationPointY = 2F;
        this.bipedRightArm.rotationPointZ = 0F;
    }

    protected void setRotationAnglesBipedEntity(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
    {
        this.bipedHead.rotateAngleY = par4 / (180F / (float)Math.PI);
        this.bipedHead.rotateAngleX = par5 / (180F / (float)Math.PI);
        this.bipedHeadwear.rotateAngleY = this.bipedHead.rotateAngleY;
        this.bipedHeadwear.rotateAngleX = this.bipedHead.rotateAngleX;
        this.bipedRightArm.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F;
        this.bipedLeftArm.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
        this.bipedRightArm.rotateAngleZ = 0.0F;
        this.bipedLeftArm.rotateAngleZ = 0.0F;
        this.bipedRightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
        this.bipedLeftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
        this.bipedRightLeg.rotateAngleY = 0.0F;
        this.bipedLeftLeg.rotateAngleY = 0.0F;

        if (this.isRiding)
        {
            this.bipedRightArm.rotateAngleX += -((float)Math.PI / 5F);
            this.bipedLeftArm.rotateAngleX += -((float)Math.PI / 5F);
            this.bipedRightLeg.rotateAngleX = -((float)Math.PI * 2F / 5F);
            this.bipedLeftLeg.rotateAngleX = -((float)Math.PI * 2F / 5F);
            this.bipedRightLeg.rotateAngleY = ((float)Math.PI / 10F);
            this.bipedLeftLeg.rotateAngleY = -((float)Math.PI / 10F);
        }

        if (this.heldItemLeft != 0)
        {
            this.bipedLeftArm.rotateAngleX = this.bipedLeftArm.rotateAngleX * 0.5F - ((float)Math.PI / 10F) * (float)this.heldItemLeft;
        }

        if (this.heldItemRight != 0)
        {
            this.bipedRightArm.rotateAngleX = this.bipedRightArm.rotateAngleX * 0.5F - ((float)Math.PI / 10F) * (float)this.heldItemRight;
        }

        this.bipedRightArm.rotateAngleY = 0.0F;
        this.bipedLeftArm.rotateAngleY = 0.0F;
        float f6;
        float f7;

        if (this.onGround > -9990.0F)
        {
            f6 = this.onGround;
            this.bipedBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt_float(f6) * (float)Math.PI * 2.0F) * 0.2F;
            this.bipedRightArm.rotationPointZ = MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
            this.bipedRightArm.rotationPointX = -MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
            this.bipedLeftArm.rotationPointZ = -MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
            this.bipedLeftArm.rotationPointX = MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
            this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY;
            this.bipedLeftArm.rotateAngleY += this.bipedBody.rotateAngleY;
            this.bipedLeftArm.rotateAngleX += this.bipedBody.rotateAngleY;
            f6 = 1.0F - this.onGround;
            f6 *= f6;
            f6 *= f6;
            f6 = 1.0F - f6;
            f7 = MathHelper.sin(f6 * (float)Math.PI);
            float f8 = MathHelper.sin(this.onGround * (float)Math.PI) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F;
            this.bipedRightArm.rotateAngleX = (float)((double)this.bipedRightArm.rotateAngleX - ((double)f7 * 1.2D + (double)f8));
            this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY * 2.0F;
            this.bipedRightArm.rotateAngleZ = MathHelper.sin(this.onGround * (float)Math.PI) * -0.4F;
        }

        if (this.isSneak)
        {
            this.bipedBody.rotateAngleX = 0.5F;
            this.bipedRightArm.rotateAngleX += 0.4F;
            this.bipedLeftArm.rotateAngleX += 0.4F;
            this.bipedRightLeg.rotationPointZ = 4.0F;
            this.bipedLeftLeg.rotationPointZ = 4.0F;
            this.bipedRightLeg.rotationPointY = 9.0F;
            this.bipedLeftLeg.rotationPointY = 9.0F;
            this.bipedHead.rotationPointY = 1.0F;
            this.bipedHeadwear.rotationPointY = 1.0F;
        }
        else
        {
            this.bipedBody.rotateAngleX = 0.0F;
            this.bipedRightLeg.rotationPointZ = 0.1F;
            this.bipedLeftLeg.rotationPointZ = 0.1F;
            this.bipedRightLeg.rotationPointY = 12.0F;
            this.bipedLeftLeg.rotationPointY = 12.0F;
            this.bipedHead.rotationPointY = 0.0F;
            this.bipedHeadwear.rotationPointY = 0.0F;
        }

        this.bipedRightArm.rotateAngleZ += MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
        this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
        this.bipedRightArm.rotateAngleX += MathHelper.sin(par3 * 0.067F) * 0.05F;
        this.bipedLeftArm.rotateAngleX -= MathHelper.sin(par3 * 0.067F) * 0.05F;

        if (this.aimedBow)
        {
            f6 = 0.0F;
            f7 = 0.0F;
            this.bipedRightArm.rotateAngleZ = 0.0F;
            this.bipedLeftArm.rotateAngleZ = 0.0F;
            this.bipedRightArm.rotateAngleY = -(0.1F - f6 * 0.6F) + this.bipedHead.rotateAngleY;
            this.bipedLeftArm.rotateAngleY = 0.1F - f6 * 0.6F + this.bipedHead.rotateAngleY + 0.4F;
            this.bipedRightArm.rotateAngleX = -((float)Math.PI / 2F) + this.bipedHead.rotateAngleX;
            this.bipedLeftArm.rotateAngleX = -((float)Math.PI / 2F) + this.bipedHead.rotateAngleX;
            this.bipedRightArm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
            this.bipedLeftArm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
            this.bipedRightArm.rotateAngleZ += MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
            this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
            this.bipedRightArm.rotateAngleX += MathHelper.sin(par3 * 0.067F) * 0.05F;
            this.bipedLeftArm.rotateAngleX -= MathHelper.sin(par3 * 0.067F) * 0.05F;
        }
    }


    protected void setRotationAnglesHug(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
    {
        final float f6 = MathHelper.sin(onGround * 3.141593F);
        final float f7 = MathHelper.sin((1.0F - (1.0F - onGround) * (1.0F - onGround)) * 3.141593F);
        bipedRightArm.rotateAngleZ = 0.0F;
        bipedLeftArm.rotateAngleZ = 0.0F;
        bipedRightArm.rotateAngleY = -(0.1F - f6 * 0.6F);
        bipedLeftArm.rotateAngleY = 0.1F;
        bipedRightArm.rotateAngleX = -1.570796F;
        bipedLeftArm.rotateAngleX = -1.570796F;
        bipedRightArm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
        bipedRightArm.rotateAngleZ += MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
        bipedLeftArm.rotateAngleZ -= MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
        bipedRightArm.rotateAngleX += MathHelper.sin(par3 * 0.067F) * 0.05F;
        bipedLeftArm.rotateAngleX -= MathHelper.sin(par3 * 0.067F) * 0.05F;
    }

    protected void setRotationAnglesCrawling(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
    {
        bipedHead.rotateAngleZ = -par4 / (180F / (float)Math.PI);
        bipedHead.rotateAngleY = 0;
        bipedHead.rotateAngleX = -55 / (180F / (float)Math.PI);

        bipedHeadwear.rotateAngleX = bipedHead.rotateAngleX;
        bipedHeadwear.rotateAngleY = bipedHead.rotateAngleY;
        bipedHeadwear.rotateAngleZ = bipedHead.rotateAngleZ;

        if(par2 > 0.25)
            par2 = 0.25f;
        float movement = MathHelper.cos(par1 * 0.8f + (float)Math.PI) * par2;

        bipedLeftArm.rotateAngleX = 180 / (180F / (float)Math.PI) - movement * 0.25f;
        bipedLeftArm.rotateAngleY = movement * -0.46f;
        bipedLeftArm.rotateAngleZ = movement * -0.2f;
        bipedLeftArm.rotationPointY = 2 - movement * 9.0F;

        bipedRightArm.rotateAngleX = 180 / (180F / (float)Math.PI) + movement * 0.25f;
        bipedRightArm.rotateAngleY = movement * -0.4f;
        bipedRightArm.rotateAngleZ = movement * -0.2f;
        bipedRightArm.rotationPointY = 2 + movement * 9.0F;

        bipedBody.rotateAngleY = movement * 0.1f;
        bipedBody.rotateAngleX = 0;
        bipedBody.rotateAngleZ = movement * 0.1f;

        bipedLeftLeg.rotateAngleX = movement * 0.1f;
        bipedLeftLeg.rotateAngleY = movement * 0.1f;
        bipedLeftLeg.rotateAngleZ = -7 / (180F / (float)Math.PI) - movement * 0.25f;
        bipedLeftLeg.rotationPointY = 10.4f + movement * 9.0F;
        bipedLeftLeg.rotationPointZ = movement * 0.6f - 0.01f;

        bipedRightLeg.rotateAngleX = movement * -0.1f;
        bipedRightLeg.rotateAngleY = movement * 0.1f;
        bipedRightLeg.rotateAngleZ = 7 / (180F / (float)Math.PI) - movement * 0.25f;
        bipedRightLeg.rotationPointY = 10.4f - movement * 9.0F;
        bipedRightLeg.rotationPointZ = movement * -0.6f - 0.01f;
    }

    protected void setRotationAnglesWaving(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
    {
        bipedRightArm.rotateAngleX = -0.1f;
        bipedRightArm.rotateAngleY = 0;
        bipedRightArm.rotateAngleZ = (float) (Math.PI - 1f  - Math.sin(entity.ticksExisted * 0.27f) * 0.5f);
    }

    protected void setRotationAnglesCry(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
    {
        bipedHeadwear.rotateAngleX = bipedHead.rotateAngleX = 0.7f;
    }

    protected void setRotationAnglesPuppet(float par1, float par2, float par3, float par4, float par5, float par6, EntityNPCInterface npc)
    {
        JobPuppet job = (JobPuppet) npc.jobInterface;

        if(job.isActive())
        {
            float pi = (float) Math.PI;

            if(!job.head.disabled)
            {
                bipedHeadwear.rotateAngleX = bipedHead.rotateAngleX = job.head.rotationX * pi;
                bipedHeadwear.rotateAngleY = bipedHead.rotateAngleY = job.head.rotationY * pi;
                bipedHeadwear.rotateAngleZ = bipedHead.rotateAngleZ = job.head.rotationZ * pi;
            }

            if(!job.body.disabled)
            {
                bipedBody.rotateAngleX = job.body.rotationX * pi;
                bipedBody.rotateAngleY = job.body.rotationY * pi;
                bipedBody.rotateAngleZ = job.body.rotationZ * pi;
            }

            if(!job.larm.disabled)
            {
                bipedLeftArm.rotateAngleX = job.larm.rotationX * pi;
                bipedLeftArm.rotateAngleY = job.larm.rotationY * pi;
                bipedLeftArm.rotateAngleZ = job.larm.rotationZ * pi;

                if(!npc.display.disableLivingAnimation)
                {
                    this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
                    this.bipedLeftArm.rotateAngleX -= MathHelper.sin(par3 * 0.067F) * 0.05F;
                }
            }

            if(!job.rarm.disabled)
            {
                bipedRightArm.rotateAngleX = job.rarm.rotationX * pi;
                bipedRightArm.rotateAngleY = job.rarm.rotationY * pi;
                bipedRightArm.rotateAngleZ = job.rarm.rotationZ * pi;

                if(!npc.display.disableLivingAnimation)
                {
                    this.bipedRightArm.rotateAngleZ += MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
                    this.bipedRightArm.rotateAngleX += MathHelper.sin(par3 * 0.067F) * 0.05F;
                }
            }

            if(!job.rleg.disabled)
            {
                bipedRightLeg.rotateAngleX = job.rleg.rotationX * pi;
                bipedRightLeg.rotateAngleY = job.rleg.rotationY * pi;
                bipedRightLeg.rotateAngleZ = job.rleg.rotationZ * pi;
            }

            if(!job.lleg.disabled)
            {
                bipedLeftLeg.rotateAngleX = job.lleg.rotationX * pi;
                bipedLeftLeg.rotateAngleY = job.lleg.rotationY * pi;
                bipedLeftLeg.rotateAngleZ = job.lleg.rotationZ * pi;
            }
        }
    }
}
