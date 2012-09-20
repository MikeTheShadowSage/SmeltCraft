// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package SmeltCraft;

import net.minecraft.src.*;

// Referenced classes of package net.minecraft.src:
//            TileEntity, IInventory, ItemStack, NBTTagCompound, 
//            NBTTagList, World, Item, Blockblast, 
//            blastRecipes, Block, Material, ModLoader, 
//            EntityPlayer

public class TileEntityBlast extends TileEntity implements IInventory {

	public TileEntityBlast() {
		blastItemStacks = new ItemStack[3];
		blastBurnTime = 0;
		currentItemBurnTime = 0;
		blastCookTime = 0;
	}

	public int getSizeInventory() {
		return blastItemStacks.length;
	}

	public ItemStack getStackInSlot(int i) {
		return blastItemStacks[i];
	}

	public ItemStack decrStackSize(int i, int j) {
		if (blastItemStacks[i] != null) {
			if (blastItemStacks[i].stackSize <= j) {
				ItemStack itemstack = blastItemStacks[i];
				blastItemStacks[i] = null;
				return itemstack;
			}
			ItemStack itemstack1 = blastItemStacks[i].splitStack(j);
			if (blastItemStacks[i].stackSize == 0) {
				blastItemStacks[i] = null;
			}
			return itemstack1;
		} else {
			return null;
		}
	}

	public void setInventorySlotContents(int i, ItemStack itemstack) {
		blastItemStacks[i] = itemstack;
		if (itemstack != null && itemstack.stackSize > getInventoryStackLimit()) {
			itemstack.stackSize = getInventoryStackLimit();
		}
	}

	public String getInvName() {
		return "blast";
	}

	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		NBTTagList var2 = par1NBTTagCompound.getTagList("Items");
		this.blastItemStacks = new ItemStack[this.getSizeInventory()];

		for (int var3 = 0; var3 < var2.tagCount(); ++var3) {
			NBTTagCompound var4 = (NBTTagCompound) var2.tagAt(var3);
			byte var5 = var4.getByte("Slot");

			if (var5 >= 0 && var5 < this.blastItemStacks.length) {
				this.blastItemStacks[var5] = ItemStack
						.loadItemStackFromNBT(var4);
			}
		}

		this.blastBurnTime = par1NBTTagCompound.getShort("BurnTime");
		this.blastCookTime = par1NBTTagCompound.getShort("CookTime");
		this.currentItemBurnTime = getItemBurnTime(this.blastItemStacks[1]);
	}

	/**
	 * Writes a tile entity to NBT.
	 */
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setShort("BurnTime", (short) this.blastBurnTime);
		par1NBTTagCompound.setShort("CookTime", (short) this.blastCookTime);
		NBTTagList var2 = new NBTTagList();

		for (int var3 = 0; var3 < this.blastItemStacks.length; ++var3) {
			if (this.blastItemStacks[var3] != null) {
				NBTTagCompound var4 = new NBTTagCompound();
				var4.setByte("Slot", (byte) var3);
				this.blastItemStacks[var3].writeToNBT(var4);
				var2.appendTag(var4);
			}
		}

		par1NBTTagCompound.setTag("Items", var2);
	}

	public int getInventoryStackLimit() {
		return 64;
	}

	public int getCookProgressScaled(int i) {
		return (blastCookTime * i) / 200;
	}

	public int getBurnTimeRemainingScaled(int i) {
		if (currentItemBurnTime == 0) {
			currentItemBurnTime = 200;
		}
		return (blastBurnTime * i) / currentItemBurnTime;
	}

	public boolean isBurning() {
		return blastBurnTime > 0;
	}

	public void updateEntity() {
		boolean flag = blastBurnTime > 0;
		boolean flag1 = false;
		if (blastBurnTime > 0) {
			blastBurnTime--;
		}
		if (!this.worldObj.isRemote) {
			if (blastBurnTime == 0 && canSmelt()) {
				currentItemBurnTime = blastBurnTime = getItemBurnTime(blastItemStacks[1]);
				if (blastBurnTime > 0) {
					flag1 = true;
					if (blastItemStacks[1] != null) {
						if (blastItemStacks[1].getItem().hasContainerItem()) {
							blastItemStacks[1] = new ItemStack(
									blastItemStacks[1].getItem()
											.getContainerItem());
						} else {
							blastItemStacks[1].stackSize--;
						}
						if (blastItemStacks[1].stackSize == 0) {
							blastItemStacks[1] = null;
						}
					}
				}
			}
			if (isBurning() && canSmelt()) {
				blastCookTime++;
				if (blastCookTime == 200) {
					blastCookTime = 0;
					smeltItem();
					flag1 = true;
				}
			} else {
				blastCookTime = 0;
			}
			if (flag != (blastBurnTime > 0)) {
				flag1 = true;
				BlockBlast.updateblastBlockState(blastBurnTime > 0, worldObj,
						xCoord, yCoord, zCoord);
			}
		}
		if (flag1) {
			onInventoryChanged();
		}
	}

	private boolean canSmelt() {
		if (blastItemStacks[0] == null) {
			return false;
		}
		ItemStack itemstack = BlastRecipes.smelting().getSmeltingResult(
				blastItemStacks[0].getItem().shiftedIndex);
		if (itemstack == null) {
			return false;
		}
		if (blastItemStacks[2] == null) {
			return true;
		}
		if (!blastItemStacks[2].isItemEqual(itemstack)) {
			return false;
		}
		if (blastItemStacks[2].stackSize < getInventoryStackLimit()
				&& blastItemStacks[2].stackSize < blastItemStacks[2]
						.getMaxStackSize()) {
			return true;
		}
		return blastItemStacks[2].stackSize < itemstack.getMaxStackSize();
	}

	public void smeltItem() {
		if (!canSmelt()) {
			return;
		}
		ItemStack itemstack = BlastRecipes.smelting().getSmeltingResult(
				blastItemStacks[0].getItem().shiftedIndex);
		if (blastItemStacks[2] == null) {
			blastItemStacks[2] = itemstack.copy();
		} else if (blastItemStacks[2].itemID == itemstack.itemID) {
			blastItemStacks[2].stackSize++;
		}
		if (blastItemStacks[0].getItem().hasContainerItem()) {
			blastItemStacks[0] = new ItemStack(blastItemStacks[0].getItem()
					.getContainerItem());
		} else {
			blastItemStacks[0].stackSize--;
		}
		if (blastItemStacks[0].stackSize <= 0) {
			blastItemStacks[0] = null;
		}
	}

	private int getItemBurnTime(ItemStack itemstack) {
		if (itemstack == null) {
			return 0;
		}
		int i = itemstack.getItem().shiftedIndex;

		if (i == SmeltCraft.bCoal.shiftedIndex) {
			return 300;
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

	private ItemStack blastItemStacks[];
	public int blastBurnTime;
	public int currentItemBurnTime;
	public int blastCookTime;

	@Override
	public ItemStack getStackInSlotOnClosing(int var1) {
		// TODO Auto-generated method stub
		return null;
	}
}
