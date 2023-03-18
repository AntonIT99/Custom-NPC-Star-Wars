package com.swnpcmod;

import org.lwjgl.opengl.GL11;

import com.swnpcmod.model.ModelStarWarsNPC;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import noppes.npcs.client.model.ModelMPM;
import noppes.npcs.client.model.ModelNPCMale;
import noppes.npcs.client.renderer.RenderNPCHumanMale;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderStarWarsNPC extends RenderNPCHumanMale
{
    private final String texturePath;
    private float modelScale = 1F;

    public RenderStarWarsNPC(Class <? extends ModelStarWarsNPC> model, String texturePath) throws InstantiationException, IllegalAccessException
    {
        super(model.newInstance().scale(0F), new ModelNPCMale(1F), new ModelNPCMale(0.5F));
        this.texturePath = texturePath;
    }

    public RenderStarWarsNPC(Class <? extends ModelStarWarsNPC> model, float scale, String texturePath) throws InstantiationException, IllegalAccessException
    {
        this(model, texturePath);
        modelScale = scale;
    }

    protected void preRenderCallback(EntityLivingBase entity, float f)
    {
        if (modelScale != 1F)
        {
            GL11.glScalef(modelScale, modelScale, modelScale);
        }
    }

    @Override
    public ResourceLocation getEntityTexture(Entity entity)
    {
        return new ResourceLocation(Strings.MOD_ID + ":textures/entity/" + texturePath);
    }
}
