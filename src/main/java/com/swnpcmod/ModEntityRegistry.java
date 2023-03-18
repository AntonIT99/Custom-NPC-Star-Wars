package com.swnpcmod;

import com.swnpcmod.entity.EntityTwilekFemale;
import com.swnpcmod.entity.EntityTwilekMale;
import cpw.mods.fml.common.registry.EntityRegistry;

public class ModEntityRegistry
{
    private ModEntityRegistry() {}

    public static void registerEntities()
    {
        createEntity(EntityTwilekMale.class, "twilekmale");
        createEntity(EntityTwilekFemale.class, "twilekfemale");
    }
    public static void createEntity(Class entityClass, String entityName){
        int randomId = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerModEntity(entityClass, entityName, randomId, SWNPCMod.modInstance, 64, 1, true);
    }
}
