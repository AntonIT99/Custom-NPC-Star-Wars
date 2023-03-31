package com.swnpcmod.entity;

import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.entity.EntityNPCInterface;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public abstract class EntityStarWarsNPC extends EntityNPCInterface
{
    protected EntityStarWarsNPC(World world, float width, float height)
    {
        super(world);
        this.setSize(width, height);
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
            worldObj.spawnEntityInWorld(npc);
        }
        super.onUpdate();
    }
}
