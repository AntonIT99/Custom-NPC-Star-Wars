package com.swnpcmod;

import com.swnpcmod.entity.*;
import com.swnpcmod.model.*;
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
    public void registerRenderers()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityTwilekMale.class, new RenderStarWarsNPC(new ModelTwilek(0F), 0.95F, "twilekmale/twilek.png"));
        RenderingRegistry.registerEntityRenderingHandler(EntityTwilekFemale.class, new RenderStarWarsNPC(new ModelTwilek(0F), 0.88F, "twilekfemale/twilek.png"));
        RenderingRegistry.registerEntityRenderingHandler(EntityWookie.class, new RenderStarWarsNPC(new ModelWookie(0F), 1.1F, "wookie/brown.png"));
        RenderingRegistry.registerEntityRenderingHandler(EntityJawa.class, new RenderStarWarsNPC(new ModelJawa(0F), 0.8F, 0.65F, 0.8F, "jawa/brown.png"));

        armorModels.put(SWNPCMod.twilekMaleOutfit1, new ModelTwilek(0.2F));
        armorModels.put(SWNPCMod.twilekFemaleOutfit1, new ModelTwilek(0.2F));
        armorModels.put(SWNPCMod.twilekFemaleHelmet, new ModelTwilek(0.2F));
        armorModels.put(SWNPCMod.twilekMaleOutfit2, new ModelTwilek(0.2F));
        armorModels.put(SWNPCMod.twilekFemaleOutfit2, new ModelTwilek(0.2F));
    }
}
