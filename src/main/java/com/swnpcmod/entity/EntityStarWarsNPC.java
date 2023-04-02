package com.swnpcmod.entity;

import org.lwjgl.opengl.GL11;

import noppes.npcs.constants.EnumAnimation;
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.entity.EntityNPCInterface;

import net.minecraft.client.renderer.entity.NPCRendererHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public abstract class EntityStarWarsNPC extends EntityNPCInterface
{
    private final float baseWidth;
    protected EntityStarWarsNPC(World world, float width, float height)
    {
        super(world);
        baseWidth = width;
        baseHeight = height;
        setSize(baseWidth, baseHeight);
    }

    @Override
    public void updateHitbox()
    {
        if(currentAnimation == EnumAnimation.LYING || currentAnimation == EnumAnimation.CRAWLING)
        {
            width = (4F / 3F) * baseWidth;
            height = baseHeight / 4.5f;
        }
        else if (isRiding())
        {
            width = baseWidth;
            height = baseHeight * 0.77f;
        }
        else
        {
            width = baseWidth;
            height = baseHeight;
        }
        width = (width / 5f) * display.modelSize;
        height = (height / 5f) * display.modelSize;

        setPosition(posX, posY, posZ);
    }

    @Override
    public void onUpdate()
    {
        isDead = true;

        if(!worldObj.isRemote)
        {
            NBTTagCompound compound = new NBTTagCompound();
            writeToNBT(compound);
            EntityCustomNpc npc = new EntityCustomNpc(worldObj);
            npc.readFromNBT(compound);
            npc.modelData.setEntityClass(getClass());
            npc.display.texture = NPCRendererHelper.getTexture((RendererLivingEntity) RenderManager.instance.getEntityRenderObject(this), this);
            worldObj.spawnEntityInWorld(npc);
        }
        super.onUpdate();
    }
}
