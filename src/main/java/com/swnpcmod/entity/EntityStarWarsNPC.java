package com.swnpcmod.entity;

import noppes.npcs.ModelData;
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.entity.EntityNPCInterface;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityStarWarsNPC extends EntityNPCInterface
{
    public EntityStarWarsNPC(World w, float width, float height)
    {
        super(w);
        this.setSize(width, height);
    }

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
