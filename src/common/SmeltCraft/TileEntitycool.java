// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package SmeltCraft;

import net.minecraft.src.*;

// Referenced classes of package net.minecraft.src:
//            TileEntity, IInventory, ItemStack, NBTTagCompound, 
//            NBTTagList, World, Item, Blockcool, 
//            coolRecipes, Block, Material, ModLoader, 
//            EntityPlayer

public class TileEntitycool extends TileEntity implements IInventory {

	public TileEntitycool() {
		coolItemStacks = new ItemStack[3];
		coolBurnTime = 0;
		currentItemBurnTime = 0;
		coolCookTime = 0;
	}

	public int getSizeInventory() {
		return coolItemStacks.length;
	}

	public ItemStack getStackInSlot(int i) {
		return coolItemStacks[i];
	}

	public ItemStack decrStackSize(int i, int j) {
		if (coolItemStacks[i] != null) {
			if (coolItemStacks[i].stackSize <= j) {
				ItemStack itemstack = coolItemStacks[i];
				coolItemStacks[i] = null;
				return itemstack;
			}
			ItemStack itemstack1 = coolItemStacks[i].splitStack(j);
			if (coolItemStacks[i].stackSize == 0) {
				coolItemStacks[i] = null;
			}
			return itemstack1;
		} else {
			return null;
		}
	}

	public void setInventorySlotContents(int i, ItemStack itemstack) {
		coolItemStacks[i] = itemstack;
		if (itemstack != null && itemstack.stackSize > getInventoryStackLimit()) {
			itemstack.stackSize = getInventoryStackLimit();
		}
	}

	public String getInvName() {
		return "cool";
	}

	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		NBTTagList var2 = par1NBTTagCompound.getTagList("Items");
		this.coolItemStacks = new ItemStack[this.getSizeInventory()];

		for (int var3 = 0; var3 < var2.tagCount(); ++var3) {
			NBTTagCompound var4 = (NBTTagCompound) var2.tagAt(var3);
			byte var5 = var4.getByte("Slot");

			if (var5 >= 0 && var5 < this.coolItemStacks.length) {
				this.coolItemStacks[var5] = ItemStack
						.loadItemStackFromNBT(var4);
			}
		}

		this.coolBurnTime = par1NBTTagCompound.getShort("BurnTime");
		this.coolCookTime = par1NBTTagCompound.getShort("CookTime");
		this.currentItemBurnTime = getItemBurnTime(this.coolItemStacks[1]);
	}

	/**
	 * Writes a tile entity to NBT.
	 */
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setShort("BurnTime", (short) this.coolBurnTime);
		par1NBTTagCompound.setShort("CookTime", (short) this.coolCookTime);
		NBTTagList var2 = new NBTTagList();

		for (int var3 = 0; var3 < this.coolItemStacks.length; ++var3) {
			if (this.coolItemStacks[var3] != null) {
				NBTTagCompound var4 = new NBTTagCompound();
				var4.setByte("Slot", (byte) var3);
				this.coolItemStacks[var3].writeToNBT(var4);
				var2.appendTag(var4);
			}
		}

		par1NBTTagCompound.setTag("Items", var2);
	}

	public int getInventoryStackLimit() {
		return 64;
	}

	public int getCookProgressScaled(int i) {
		return (coolCookTime * i) / 200;
	}

	public int getBurnTimeRemainingScaled(int i) {
		if (currentItemBurnTime == 0) {
			currentItemBurnTime = 200;
		}
		return (coolBurnTime * i) / currentItemBurnTime;
	}

	public boolean isBurning() {
		return coolBurnTime > 0;
	}

	public void updateEntity() {
		boolean flag = coolBurnTime > 0;
		boolean flag1 = false;
		if (coolBurnTime > 0) {
			coolBurnTime--;
		}
		if (!this.worldObj.isRemote) {
			if (coolBurnTime == 0 && canSmelt()) {
				currentItemBurnTime = coolBurnTime = getItemBurnTime(coolItemStacks[1]);
				if (coolBurnTime > 0) {
					flag1 = true;
					if (coolItemStacks[1] != null) {
						if (coolItemStacks[1].getItem().hasContainerItem()) {
							coolItemStacks[1] = new ItemStack(coolItemStacks[1]
									.getItem().getContainerItem());
						} else {
							coolItemStacks[1].stackSize--;
						}
						if (coolItemStacks[1].stackSize == 0) {
							coolItemStacks[1] = null;
						}
					}
				}
			}
			if (isBurning() && canSmelt()) {
				coolCookTime++;
				if (coolCookTime == 200) {
					coolCookTime = 0;
					smeltItem();
					flag1 = true;
				}
			} else {
				coolCookTime = 0;
			}
			if (flag != (coolBurnTime > 0)) {
				flag1 = true;
				Blockcool.updatecoolBlockState(coolBurnTime > 0, worldObj,
						xCoord, yCoord, zCoord);
			}
		}
		if (flag1) {
			onInventoryChanged();
		}
	}

	private boolean canSmelt() {
		if (coolItemStacks[0] == null) {
			return false;
		}
		ItemStack itemstack = coolRecipes.smelting().getSmeltingResult(
				coolItemStacks[0].getItem().shiftedIndex);
		if (itemstack == null) {
			return false;
		}
		if (coolItemStacks[2] == null) {
			return true;
		}
		if (!coolItemStacks[2].isItemEqual(itemstack)) {
			return false;
		}
		if (coolItemStacks[2].stackSize < getInventoryStackLimit()
				&& coolItemStacks[2].stackSize < coolItemStacks[2]
						.getMaxStackSize()) {
			return true;
		}
		return coolItemStacks[2].stackSize < itemstack.getMaxStackSize();
	}

	public void smeltItem() {
		if (!canSmelt()) {
			return;
		}
		ItemStack itemstack = coolRecipes.smelting().getSmeltingResult(
				coolItemStacks[0].getItem().shiftedIndex);
		if (coolItemStacks[2] == null) {
			coolItemStacks[2] = itemstack.copy();
		} else if (coolItemStacks[2].itemID == itemstack.itemID) {
			coolItemStacks[2].stackSize++;
		}
		if (coolItemStacks[0].getItem().hasContainerItem()) {
			coolItemStacks[0] = new ItemStack(coolItemStacks[0].getItem()
					.getContainerItem());
		} else {
			coolItemStacks[0].stackSize--;
		}
		if (coolItemStacks[0].stackSize <= 0) {
			coolItemStacks[0] = null;
		}
	}

	private int getItemBurnTime(ItemStack itemstack) {
		if (itemstack == null) {
			return 0;
		}
		int i = itemstack.getItem().shiftedIndex;

		if (i == Item.bucketWater.shiftedIndex) {
			return 300;
		}
		if (i == Block.ice.blockID) {
			return 100;
		} else {
			return 0;
		}
	}

	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		if (worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) != this) {
			return false;
		}
		return entityplayer.getDistanceSq((double) xCoord + 0.5D,
				(double) yCoord + 0.5D, (double) zCoord + 0.5D) <= 64D;
	}

	public void openChest() {
	}

	public void closeChest() {
	}

	private ItemStack coolItemStacks[];
	public int coolBurnTime;
	public int currentItemBurnTime;
	public int coolCookTime;

	@Override
	public ItemStack getStackInSlotOnClosing(int var1) {
		// TODO Auto-generated method stub
		return null;
	}
}
