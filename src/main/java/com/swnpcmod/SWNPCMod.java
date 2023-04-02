package com.swnpcmod;

import com.swnpcmod.item.CustomArmorItem;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.util.EnumHelper;

import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;

@Mod(modid = Strings.MOD_ID, name = Strings.MOD_NAME, version = Strings.MOD_VERSION, dependencies="required-after:customnpcs")

public class SWNPCMod
{
    @SidedProxy(clientSide = "com.swnpcmod.ClientProxy", serverSide = "com.swnpcmod.CommonProxy")
    public static CommonProxy PROXY;

    @Instance(Strings.MOD_ID)
    public static SWNPCMod INSTANCE;

    public static Item twilekMaleOutfit1, twilekFemaleOutfit1, twilekFemaleHelmet, twilekMaleOutfit2, twilekFemaleOutfit2;


    @EventHandler
    public static void preInit(FMLPreInitializationEvent preEvent)
    {
        ModEntityRegistry.registerEntities();
    }

    @EventHandler
    public static void init(FMLInitializationEvent event)
    {
        ItemArmor.ArmorMaterial swArmorMaterial = EnumHelper.addArmorMaterial("swArmorMaterial", 30, new int[]{1, 6, 0, 0}, 15);

        twilekMaleOutfit1 = new CustomArmorItem(swArmorMaterial, "twilek_male_outfit1", "outfit", 1);
        twilekFemaleOutfit1 = new CustomArmorItem(swArmorMaterial, "twilek_female_outfit1", "outfit", 1);
        twilekFemaleHelmet = new CustomArmorItem(swArmorMaterial, "twilek_female_helmet", "helmet", 0);
        twilekMaleOutfit2 = new CustomArmorItem(swArmorMaterial, "twilek_male_outfit2", "outfit", 1);
        twilekFemaleOutfit2 = new CustomArmorItem(swArmorMaterial, "twilek_female_outfit2", "outfit", 1);

        PROXY.registerRenderers();
    }


    @EventHandler
    public static void postInit(FMLPostInitializationEvent postEvent) {}

}
