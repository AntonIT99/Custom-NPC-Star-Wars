package com.swnpcmod;

import com.swnpcmod.entity.EntityTwilekFemale;
import com.swnpcmod.entity.EntityTwilekMale;
import com.swnpcmod.model.ModelTwilek;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
    @Override
    public void registerRenderThings()
    {
        try
        {
            RenderingRegistry.registerEntityRenderingHandler(EntityTwilekMale.class, new RenderStarWarsNPC(ModelTwilek.class, 0.95F, "twilekmale/twilek.png"));
            RenderingRegistry.registerEntityRenderingHandler(EntityTwilekFemale.class, new RenderStarWarsNPC(ModelTwilek.class, 0.88F, "twilekfemale/twilek.png"));
        }
        catch (InstantiationException | IllegalAccessException exception)
        {
            exception.printStackTrace();
        }
    }
}
