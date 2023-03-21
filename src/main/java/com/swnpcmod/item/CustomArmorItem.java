package com.swnpcmod.item;

import com.swnpcmod.ClientProxy;
import com.swnpcmod.Strings;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class CustomArmorItem extends ItemArmor
{
    protected String name;

    public CustomArmorItem(ArmorMaterial material, String name, String iconName, int renderIndex, int type)
    {
        super(material, renderIndex, type);
        this.name = name;
        setUnlocalizedName(Strings.MOD_ID + "." + name);
        setTextureName(Strings.MOD_ID + ":" + iconName);
        setMaxStackSize(1);
        setCreativeTab(CreativeTabs.tabCombat);
        GameRegistry.registerItem(this, name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel (EntityLivingBase entityLiving, ItemStack itemstack, int armorSlot){

        ModelBiped armorModel = ClientProxy.armorModels.get(this);

        if(armorModel != null)
        {
            armorModel.bipedHead.showModel = true;
            armorModel.bipedHeadwear.showModel = true;
            armorModel.bipedBody.showModel = true;
            armorModel.bipedRightArm.showModel = true;
            armorModel.bipedLeftArm.showModel = true;
            armorModel.bipedRightLeg.showModel = true;
            armorModel.bipedLeftLeg.showModel = true;

            armorModel.isSneak = entityLiving.isSneaking();
            armorModel.isRiding = entityLiving.isRiding();
            armorModel.isChild = entityLiving.isChild();

            armorModel.heldItemRight = 0;
            armorModel.aimedBow = false;

            if (entityLiving instanceof EntityPlayer)
            {
                EntityPlayer player = (EntityPlayer)entityLiving;

                ItemStack heldItem = player.getEquipmentInSlot(0);

                if (heldItem != null)
                {
                    armorModel.heldItemRight = 1;

                    if (player.getItemInUseCount() > 0)
                    {
                        EnumAction enumaction = heldItem.getItemUseAction();

                        if (enumaction == EnumAction.bow)
                        {
                            armorModel.aimedBow = true;
                        }
                        else if (enumaction == EnumAction.block)
                        {
                            armorModel.heldItemRight = 3;
                        }
                    }
                }
            }
        }

        return armorModel;
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
    {
        return String.format("%s:textures/armor/%s.png", Strings.MOD_ID, name);
    }
}
