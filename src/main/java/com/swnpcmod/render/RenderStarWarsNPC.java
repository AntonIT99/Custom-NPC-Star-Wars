package com.swnpcmod.render;

import org.lwjgl.opengl.GL11;

import com.swnpcmod.Strings;
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
    private float modelScaleX = 1F;
    private float modelScaleY = 1F;
    private float modelScaleZ = 1F;

    public RenderStarWarsNPC(ModelStarWarsNPC model, String texturePath)
    {
        super(model, new ModelNPCMale(1F), new ModelNPCMale(0.5F));
        this.texturePath = texturePath;
    }

    public RenderStarWarsNPC(ModelStarWarsNPC model, float scale, String texturePath)
    {
        this(model, texturePath);
        modelScaleX = modelScaleY = modelScaleZ = scale;
    }

    public RenderStarWarsNPC(ModelStarWarsNPC model, float scaleX, float scaleY, float scaleZ, String texturePath)
    {
        this(model, texturePath);
        modelScaleX = scaleX;
        modelScaleY = scaleY;
        modelScaleZ = scaleZ;
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entity, float f)
    {
        if (modelScaleX != 1F || modelScaleY != 1F || modelScaleZ != 1F )
        {
            GL11.glScalef(modelScaleX, modelScaleY, modelScaleZ);
        }
    }

    @Override
    public ResourceLocation getEntityTexture(Entity entity)
    {
        return new ResourceLocation(Strings.MOD_ID + ":textures/entity/" + texturePath);
    }
}
