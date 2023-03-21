package com.swnpcmod;

import com.swnpcmod.item.CustomArmorItem;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.common.util.EnumHelper;

import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mod(modid = Strings.MOD_ID, name = Strings.MOD_NAME, version = Strings.MOD_VERSION, dependencies="required-after:customnpcs")

public class SWNPCMod
{
    @SidedProxy(clientSide = "com.swnpcmod.ClientProxy", serverSide = "com.swnpcmod.CommonProxy")
    public static CommonProxy commonProxy;

    @Instance(Strings.MOD_ID)
    public static SWNPCMod modInstance;

    public static Item twilekMaleOutfit1;


    @EventHandler
    public static void preInit(FMLPreInitializationEvent PreEvent)
    {
        ModEntityRegistry.registerEntities();

        ItemArmor.ArmorMaterial swArmorMaterial = EnumHelper.addArmorMaterial("swArmorMaterial", 0, new int[]{1, 3, 2, 1}, 15);

        twilekMaleOutfit1 = new CustomArmorItem(swArmorMaterial, "twilek_male_outfit1", "outfit", 1, 1);
    }
    @EventHandler
    public static void init(FMLInitializationEvent event)
    {
        commonProxy.registerRenders();
    }


    @EventHandler
    public static void postInit(FMLPostInitializationEvent PostEvent) {}

}
