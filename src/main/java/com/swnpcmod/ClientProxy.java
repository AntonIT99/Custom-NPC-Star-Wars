package com.swnpcmod;

import com.swnpcmod.entity.EntityTwilekFemale;
import com.swnpcmod.entity.EntityTwilekMale;
import com.swnpcmod.model.ModelTwilek;
import com.swnpcmod.render.RenderStarWarsNPC;
import cpw.mods.fml.client.registry.RenderingRegistry;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.Map;

public class ClientProxy extends CommonProxy
{
    public static final Map<Item, ModelBiped> armorModels = new HashMap<>();

    @Override
    public void registerRenders()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityTwilekMale.class, new RenderStarWarsNPC(new ModelTwilek(0F), 0.95F, "twilekmale/twilek.png"));
        RenderingRegistry.registerEntityRenderingHandler(EntityTwilekFemale.class, new RenderStarWarsNPC(new ModelTwilek(0F), 0.88F, "twilekfemale/twilek.png"));

        armorModels.put(SWNPCMod.twilekMaleOutfit1, new ModelTwilek(0.1F));
    }
}
