package noppes.npcs;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;

import com.flansmod.common.guns.*;
import com.flansmod.common.teams.ItemTeamArmour;
import noppes.npcs.constants.EnumPotionType;
import noppes.npcs.entity.EntityNPCInterface;

public class DataInventory implements IInventory{
	public HashMap<Integer,ItemStack> items = new HashMap<Integer,ItemStack>();
	public HashMap<Integer,Double> dropchance = new HashMap<Integer,Double>();
	public HashMap<Integer,ItemStack> weapons = new HashMap<Integer, ItemStack>();
	public HashMap<Integer,ItemStack> armor = new HashMap<Integer, ItemStack>();
		
	public int minExp = 0;
	public int maxExp = 0;
	
	public int lootMode = 0;

	private EntityNPCInterface npc;

	public boolean useWeaponMeleeStats;
	public boolean useWeaponRangedStats;
	public boolean useArmorStats;
	
	public DataInventory(EntityNPCInterface npc){
		this.npc = npc;
	}
	public NBTTagCompound writeEntityToNBT(NBTTagCompound nbttagcompound){
		nbttagcompound.setInteger("MinExp", minExp);
		nbttagcompound.setInteger("MaxExp", maxExp);
		nbttagcompound.setTag("NpcInv", NBTTags.nbtItemStackList(items));
		nbttagcompound.setTag("Armor", NBTTags.nbtItemStackList(getArmor()));
		nbttagcompound.setTag("Weapons", NBTTags.nbtItemStackList(getWeapons()));
		nbttagcompound.setTag("DoubleDropChance", NBTTags.nbtIntegerDoubleMap(dropchance));
		nbttagcompound.setInteger("LootMode", lootMode);
		nbttagcompound.setBoolean("UseWeaponMeleeStats", useWeaponMeleeStats);
		nbttagcompound.setBoolean("UseWeaponRangedStats", useWeaponRangedStats);
		nbttagcompound.setBoolean("UseArmorStats", useArmorStats);
		return nbttagcompound;
	}
	public void readEntityFromNBT(NBTTagCompound nbttagcompound){
		minExp = nbttagcompound.getInteger("MinExp");
		maxExp = nbttagcompound.getInteger("MaxExp");
		items = NBTTags.getItemStackList(nbttagcompound.getTagList("NpcInv", 10));
		setArmor(NBTTags.getItemStackList(nbttagcompound.getTagList("Armor", 10)));
		setWeapons(NBTTags.getItemStackList(nbttagcompound.getTagList("Weapons", 10)));

		if(!nbttagcompound.hasKey("DoubleDropChance")) {
			dropchance.clear();
			HashMap<Integer, Integer> oldDropChance = NBTTags.getIntegerIntegerMap(nbttagcompound.getTagList("DropChance", 10));
			for(int i = 0; i < oldDropChance.entrySet().size(); i++){
				dropchance.put(i, Double.valueOf(oldDropChance.get(i)));
			}
		} else {
			dropchance = NBTTags.getIntegerDoubleMap(nbttagcompound.getTagList("DoubleDropChance", 10));
		}

		lootMode = nbttagcompound.getInteger("LootMode");
		useWeaponMeleeStats = nbttagcompound.getBoolean("UseWeaponMeleeStats");
		useWeaponRangedStats = nbttagcompound.getBoolean("UseWeaponRangedStats");
		useArmorStats = nbttagcompound.getBoolean("UseArmorStats");
	}
	public HashMap<Integer, ItemStack> getWeapons() {
		return weapons;
	}
	public void setWeapons(HashMap<Integer, ItemStack> list) {
		weapons = list;
		setWeaponStats(weapons.get(0), weapons.get(1), weapons.get(2));
		setKnockbackResistance(weapons, armor);
	}
	public HashMap<Integer, ItemStack> getArmor() {
		return armor;
	}
	public void setArmor(HashMap<Integer, ItemStack> list) {
		armor = list;
		setArmorStats(armor);
		setKnockbackResistance(weapons, armor);
	}
	public ItemStack getWeapon(){
		return weapons.get(0);
	}
	public void setWeapon(ItemStack item){
		weapons.put(0, item);
		setWeaponStats(weapons.get(0), weapons.get(1), weapons.get(2));
		setKnockbackResistance(weapons, armor);
	}
	public ItemStack getProjectile(){
		return weapons.get(1);
	}
	public void setProjectile(ItemStack item){
		weapons.put(1, item);
		setWeaponStats(weapons.get(0), weapons.get(1), weapons.get(2));
		setKnockbackResistance(weapons, armor);
	}
	public ItemStack getOffHand(){
		return weapons.get(2);
	}
	public void setOffHand(ItemStack item){
		weapons.put(2, item);
		setWeaponStats(weapons.get(0), weapons.get(1), weapons.get(2));
		setKnockbackResistance(weapons, armor);
	}

	private void setKnockbackResistance(Map<Integer, ItemStack> weapons, Map<Integer, ItemStack> armor)
	{
		if (useWeaponMeleeStats || useWeaponRangedStats || useArmorStats)
		{
			AtomicReference<Float> knockbackResistance = new AtomicReference<>(1F);
			List<ItemStack> itemList = new ArrayList<>();

			if (useWeaponMeleeStats || useWeaponRangedStats)
				itemList.addAll(weapons.values());
			if (useArmorStats)
				itemList.addAll(armor.values());

			for (ItemStack item : itemList)
			{
				getAttributeModifier(item, SharedMonsterAttributes.knockbackResistance).ifPresent(value -> knockbackResistance.set(knockbackResistance.get() + (float) value));
			}
			npc.stats.resistances.knockback = knockbackResistance.get();
		}
	}

	private void setArmorStats(HashMap<Integer, ItemStack> armor)
	{
		if (!useArmorStats)
			return;

		float meleeResistance = 1F;
		float projectileResistance = 1F;
		float explosionResistance = 1F;
		int protectionLevel = 0;
		int projectileProtectionLevel = 0;
		int blastProtectionLevel = 0;

		for (int i = 0; i < 4; i++)
		{
			if (armor.get(i) == null)
				continue;

			if (armor.get(i).getItem() instanceof ItemArmor)
			{
				if (armor.get(i).isItemEnchanted())
				{
					protectionLevel += EnchantmentHelper.getEnchantmentLevel(Enchantment.protection.effectId, armor.get(i));
					projectileProtectionLevel += EnchantmentHelper.getEnchantmentLevel(Enchantment.projectileProtection.effectId, armor.get(i));
					blastProtectionLevel += EnchantmentHelper.getEnchantmentLevel(Enchantment.blastProtection.effectId, armor.get(i));
				}

				ItemArmor item = (ItemArmor) armor.get(i).getItem();
				if (item instanceof ItemTeamArmour)
				{
					meleeResistance += ((ItemTeamArmour)item).type.defence;
					projectileResistance += ((ItemTeamArmour)item).type.bulletDefence;
					explosionResistance += ((ItemTeamArmour)item).type.defence;

					if (((ItemTeamArmour)item).type.negateFallDamage)
						npc.stats.noFallDamage = true;
					if (((ItemTeamArmour)item).type.fireResistance)
						npc.stats.immuneToFire = true;
					if (((ItemTeamArmour)item).type.waterBreathing)
						npc.stats.drowningType = 0;
				}
				else
				{
					meleeResistance += 0.04F * item.damageReduceAmount;
					projectileResistance += 0.04F * item.damageReduceAmount;
					explosionResistance += 0.04F * item.damageReduceAmount;
				}
			}
		}

		npc.stats.resistances.playermelee = meleeResistance + protectionLevel * 0.04F * (2F - meleeResistance);
		npc.stats.resistances.arrow = projectileResistance + projectileProtectionLevel * 0.04F * (2F - meleeResistance);
		npc.stats.resistances.explosion = explosionResistance + blastProtectionLevel * 0.04F * (2F - meleeResistance);
	}

	private void setWeaponStats(ItemStack mainWeapon, ItemStack projectile, ItemStack offHandWeapon)
	{
		if (useWeaponMeleeStats)
		{
			setMeleeStats(mainWeapon, offHandWeapon);
		}

		if (useWeaponRangedStats)
		{
			setRangedStats(mainWeapon, projectile, offHandWeapon);
		}
	}

	private void setMeleeStats(ItemStack mainWeapon, ItemStack offHandWeapon)
	{
		float damageMainWeapon = 0F;
		float damageOffHandWeapon = 0F;
		int fireAspectLevel = 0;

		if (mainWeapon != null)
		{
			damageMainWeapon = getMeleeDamage(mainWeapon);
		}
		if (offHandWeapon != null)
		{
			damageOffHandWeapon = getMeleeDamage(offHandWeapon);
		}

		fireAspectLevel = Math.max(EnchantmentHelper.getEnchantmentLevel(Enchantment.fireAspect.effectId, mainWeapon), EnchantmentHelper.getEnchantmentLevel(Enchantment.knockback.effectId, offHandWeapon));

		npc.stats.setAttackStrength(Math.max(damageMainWeapon, damageOffHandWeapon) + 1F);
		npc.stats.knockback = Math.max(EnchantmentHelper.getEnchantmentLevel(Enchantment.knockback.effectId, mainWeapon), EnchantmentHelper.getEnchantmentLevel(Enchantment.knockback.effectId, offHandWeapon));

		if (fireAspectLevel > 0)
		{
			npc.stats.potionType = EnumPotionType.Fire;
			npc.stats.potionDuration = 4 * fireAspectLevel;
		}
	}

	private float getMeleeDamage(ItemStack item)
	{
		if (item == null)
			return 0F;

		AtomicReference<Float> damage = new AtomicReference<>(0F);
		getAttributeModifier(item, SharedMonsterAttributes.attackDamage).ifPresent(value -> damage.set((float)value));

		if (item.isItemEnchanted())
		{
			Map enchantments = EnchantmentHelper.getEnchantments(item);
			enchantments.forEach((id, level) -> damage.set(damage.get() + Enchantment.enchantmentsList[(Integer)id].func_152376_a((Integer) level, EnumCreatureAttribute.UNDEFINED)));
		}

		return damage.get();
	}

	private OptionalDouble getAttributeModifier(ItemStack item, IAttribute attribute)
	{
		if (item != null)
		{
			AttributeModifier[] attributeModifier = (AttributeModifier[]) item.getItem().getAttributeModifiers(item).get(attribute.getAttributeUnlocalizedName()).toArray(new AttributeModifier[0]);
			if (attributeModifier.length > 0)
			{
				return OptionalDouble.of(attributeModifier[0].getAmount());
			}
		}
		return OptionalDouble.empty();
	}

	private void setRangedStats(ItemStack mainWeapon, ItemStack projectile, ItemStack offHandWeapon)
	{
		//TODO: Take melee Flan's weapons into account (animation -> to test + range + attackSpeed)
		//TODO: Handle gun in offHand
		//TODO: Reload Sound? Distort Sound? LastBulletSound?
		//TODO: Implement Non Flan projectiles / Ranged Weapons / Enchantments / Potions

		if (mainWeapon != null)
		{
			Item mainWeapomItem = mainWeapon.getItem();
			if (mainWeapomItem instanceof ItemGun)
			{
				GunType gun = ((ItemGun) mainWeapomItem).type;
				float reloadTime = gun.getReloadTime(mainWeapon);
				float fireRate = gun.getShootDelay(mainWeapon);
				float bulletSpeed;

				if (projectile != null)
				{
					bulletSpeed = gun.getBulletSpeed(mainWeapon, projectile);
				}
				else
				{
					bulletSpeed = gun.getBulletSpeed(mainWeapon);
				}

				npc.stats.pDamage = gun.getDamage(mainWeapon);
				npc.stats.pSpeed = Math.round(Math.max(bulletSpeed, 1F));
				npc.stats.accuracy = EntityNPCInterface.bulletSpreadToAccuracy(gun.getSpread(mainWeapon, false, false));
				npc.stats.minDelay = (int)Math.floor(reloadTime);
				npc.stats.maxDelay = (int)Math.ceil(reloadTime);
				npc.stats.fireRate = Math.round(fireRate);
				npc.stats.shotCount = gun.getNumBullets(mainWeapon);
				npc.stats.fireSound = getGunSound(mainWeapon, gun);
			}
		}

		if (projectile != null)
		{
			Item projectileItem = projectile.getItem();
			if (projectileItem instanceof ItemShootable)
			{
				ShootableType shootable = ((ItemShootable) projectileItem).type;
				npc.stats.burstCount = shootable.roundsPerItem;
			}
		}
	}

	private String getGunSound(ItemStack stack, GunType gunType)
	{
		AttachmentType barrel = gunType.getBarrel(stack);
		AttachmentType grip = gunType.getGrip(stack);

		boolean silenced = barrel != null && barrel.silencer && !gunType.getSecondaryFire(stack);
		String soundToPlay = null;

		if (gunType.getSecondaryFire(stack) && grip != null && grip.secondaryShootSound != null)
			soundToPlay = grip.secondaryShootSound;
		else if (silenced && gunType.suppressedShootSound != null)
			soundToPlay = gunType.suppressedShootSound;
		else if (gunType.shootSound != null)
			soundToPlay = gunType.shootSound;

		return "flansmod:" + soundToPlay;
	}

	public ArrayList<ItemStack> getDroppedItems(DamageSource damagesource) {
		ArrayList<ItemStack> drops = new ArrayList<>();

		ArrayList<EntityItem> list = new ArrayList<EntityItem>();
		for (int i : items.keySet()) {
			ItemStack item = items.get(i);
			if(item == null)
				continue;
			double dchance = 100;
			if(dropchance.containsKey(i))
				dchance = dropchance.get(i);
			double chance = Math.random()*100 + dchance;
			if(chance >= 100){
				EntityItem e = getEntityItem(item.copy());
				if(e != null)
					list.add(e);
			}
		}
		for (EntityItem e : list) {
			drops.add(e.getEntityItem());
		}

		int enchant = 0;
		if (damagesource.getEntity() instanceof EntityPlayer){
			enchant = EnchantmentHelper.getLootingModifier((EntityLivingBase)damagesource.getEntity());
		}

		if (!net.minecraftforge.common.ForgeHooks.onLivingDrops(npc, damagesource, list, enchant, true, 0)){
			return drops;
		} else {
			return new ArrayList<>();
		}
	}

	public int getDroppedXp() {
		int droppedXp = minExp;
		if (maxExp - minExp > 0)
			droppedXp += npc.worldObj.rand.nextInt(maxExp - minExp);

		return droppedXp;
	}

	public void dropXp(Entity entity, int droppedXp) {
		while (droppedXp > 0){
			int var2 = EntityXPOrb.getXPSplit(droppedXp);
			droppedXp -= var2;
			if(lootMode == 1 && entity instanceof EntityPlayer){
				npc.worldObj.spawnEntityInWorld(new EntityXPOrb(entity.worldObj, entity.posX, entity.posY, entity.posZ, var2));
			}
			else{
				npc.worldObj.spawnEntityInWorld(new EntityXPOrb(npc.worldObj, npc.posX, npc.posY, npc.posZ, var2));
			}
		}
	}

	public void dropItems(Entity entity, ArrayList<ItemStack> itemList) {
		ArrayList<EntityItem> list = new ArrayList<EntityItem>();
		for (ItemStack item : itemList) {
			if(item == null)
				continue;
			EntityItem e = getEntityItem(item.copy());
			if(e != null)
				list.add(e);
		}

		for (EntityItem item : list){
			if(lootMode == 1 && entity instanceof EntityPlayer){
				EntityPlayer player = (EntityPlayer)entity;
				item.delayBeforeCanPickup = 2;
				npc.worldObj.spawnEntityInWorld(item);
				ItemStack stack = item.getEntityItem();
				int i = stack.stackSize;

				if (player.inventory.addItemStackToInventory(stack)) {
					npc.worldObj.playSoundAtEntity(item,
							"random.pop",
							0.2F,
							((npc.getRNG().nextFloat() - npc.getRNG().nextFloat()) * 0.7F + 1.0F) * 2.0F);
					player.onItemPickup(item, i);

					if (stack.stackSize <= 0) {
						item.setDead();
					}
				}
			}
			else
				npc.worldObj.spawnEntityInWorld(item);
		}
	}
	
	public EntityItem getEntityItem(ItemStack itemstack) {
		if (itemstack == null) {
			return null;
		}
		EntityItem entityitem = new EntityItem(npc.worldObj, npc.posX,
				(npc.posY - 0.30000001192092896D) + (double) npc.getEyeHeight(), npc.posZ,
				itemstack);
		entityitem.delayBeforeCanPickup = 40;

		float f2 = npc.getRNG().nextFloat() * 0.5F;
		float f4 = npc.getRNG().nextFloat() * 3.141593F * 2.0F;
		entityitem.motionX = -MathHelper.sin(f4) * f2;
		entityitem.motionZ = MathHelper.cos(f4) * f2;
		entityitem.motionY = 0.20000000298023224D;

		return entityitem;
	}
	
	public ItemStack armorItemInSlot(int i) {
		return getArmor().get(i);
	}
	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return 15;
	}
	@Override
	public ItemStack getStackInSlot(int i) {
		if(i < 4)
			return armorItemInSlot(i);
		else if(i < 7)
			return getWeapons().get(i-4);
		else
			return items.get(i-7);
	}
	@Override
	public ItemStack decrStackSize(int par1, int par2) {
		int i =0;
        HashMap<Integer,ItemStack> var3;

        if (par1 >= 7)
        {
        	var3 = items;
            par1 -= 7;
        }
        else if (par1 >= 4)
        {
        	var3 = getWeapons();
            par1 -= 4;
            i = 1;
        }
        else{
        	var3 = getArmor();
            i = 2;
        }
        
        ItemStack var4 = null;
        if (var3.get(par1) != null)
        {

            if (var3.get(par1).stackSize <= par2)
            {
                var4 = var3.get(par1);
                var3.put(par1,null);
            }
            else
            {
                var4 = var3.get(par1).splitStack(par2);

                if (var3.get(par1).stackSize == 0)
                {
                    var3.put(par1,null);
                }
            }
        }
        if(i == 1)
        	setWeapons(var3);
        if(i == 2)
        	setArmor(var3);
        return var4;
	}
	@Override
	public ItemStack getStackInSlotOnClosing(int par1) {
		int i = 0;
        HashMap<Integer,ItemStack> var2;;

        if (par1 >= 7)
        {
        	var2 = items;
            par1 -= 7;
        }
        else if (par1 >= 4)
        {
        	var2 = getWeapons();
            par1 -= 4;
            i = 1;
        }
        else{
        	var2 = getArmor();
            i = 2;
        }

        if (var2.get(par1) != null)
        {
            ItemStack var3 = var2.get(par1);
            var2.put(par1,null);
            if(i == 1)
            	setWeapons(var2);
            if(i == 2)
            	setArmor(var2);
            return var3;
        }
        else
        {
            return null;
        }
	}
	@Override
    public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
    {
		int i = 0;
        HashMap<Integer,ItemStack> var3;

        if (par1 >= 7)
        {
        	var3 = items;
            par1 -= 7;
        }
        else if (par1 >= 4)
        {
        	var3 = getWeapons();
            par1 -= 4;
            i = 1;
        }
        else{
        	var3 = getArmor();
            i = 2;
        }

        var3.put(par1,par2ItemStack);
        if(i == 1)
        	setWeapons(var3);
        if(i == 2)
        	setArmor(var3);
    }
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	@Override
	public boolean isUseableByPlayer(EntityPlayer var1) {
		return true;
	}
	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return true;
	}
	@Override
	public String getInventoryName() {
		return "NPC Inventory";
	}
	@Override
	public boolean hasCustomInventoryName() {
		return true;
	}
	@Override
	public void markDirty() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void openInventory() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void closeInventory() {
		// TODO Auto-generated method stub
		
	}
}
