package com.swnpcmod;

import com.swnpcmod.entity.*;
import cpw.mods.fml.common.registry.EntityRegistry;

import net.minecraft.entity.Entity;

public class ModEntityRegistry
{
    private static int entityID = 0;
    private ModEntityRegistry() {}

    public static void registerEntities()
    {
        createEntity(EntityTwilekMale.class, "twilekmale");
        createEntity(EntityTwilekFemale.class, "twilekfemale");
        createEntity(EntityWookie.class, "wookie");
        createEntity(EntityJawa.class, "jawa");
    }
    public static void createEntity(Class <? extends Entity> entityClass, String entityName)
    {
        EntityRegistry.registerModEntity(entityClass, entityName, entityID++, SWNPCMod.INSTANCE, 64, 1, true);
    }
}
