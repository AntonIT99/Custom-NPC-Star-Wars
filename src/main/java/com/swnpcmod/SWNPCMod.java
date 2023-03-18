package com.swnpcmod;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Strings.MOD_ID, name = Strings.MOD_NAME, version = Strings.MOD_VERSION, dependencies="required-after:customnpcs")

public class SWNPCMod
{
    @SidedProxy(clientSide = "com.swnpcmod.ClientProxy", serverSide = "com.swnpcmod.ServerProxy")
    public static CommonProxy proxy;

    @Instance(Strings.MOD_ID)
    public static SWNPCMod modInstance;


    @EventHandler
    public static void preInit(FMLPreInitializationEvent PreEvent)
    {
        ModEntityRegistry.registerEntities();
        proxy.registerRenderThings();
    }
    @EventHandler
    public static void init(FMLInitializationEvent event) {}


    @EventHandler
    public static void postInit(FMLPostInitializationEvent PostEvent) {}

}
