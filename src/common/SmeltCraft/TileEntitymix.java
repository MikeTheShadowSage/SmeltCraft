package SmeltCraft;

import net.minecraft.src.*;

public class TileEntitymix extends TileEntity implements IInventory {

	public TileEntitymix() {
		mixItemStacks = new ItemStack[4];
		mixBurnTime = 0;
		currentItemBurnTime = 0;
		mixCookTime = 0;
	}

	public int getSizeInventory() {
		return mixItemStacks.length;
	}

	public ItemStack getStackInSlot(int i) {
		return mixItemStacks[i];
	}

	public ItemStack decrStackSize(int i, int j) {
		if (mixItemStacks[i] != null) {
			if (mixItemStacks[i].stackSize <= j) {
				ItemStack itemstack = mixItemStacks[i];
				mixItemStacks[i] = null;
				return itemstack;
			}
			ItemStack itemstack1 = mixItemStacks[i].splitStack(j);
			if (mixItemStacks[i].stackSize == 0) {
				mixItemStacks[i] = null;
			}
			return itemstack1;
		} else {
			return null;
		}
	}

	public void setInventorySlotContents(int i, ItemStack itemstack) {
		mixItemStacks[i] = itemstack;
		if (itemstack != null && itemstack.stackSize > getInventoryStackLimit()) {
			itemstack.stackSize = getInventoryStackLimit();
		}
	}

	public String getInvName() {
		return "mix";
	}

	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		NBTTagList var2 = par1NBTTagCompound.getTagList("Items");
		this.mixItemStacks = new ItemStack[this.getSizeInventory()];

		for (int var3 = 0; var3 < var2.tagCount(); ++var3) {
			NBTTagCompound var4 = (NBTTagCompound) var2.tagAt(var3);
			byte var5 = var4.getByte("Slot");

			if (var5 >= 0 && var5 < this.mixItemStacks.length) {
				this.mixItemStacks[var5] = ItemStack.loadItemStackFromNBT(var4);
			}
		}

		this.mixBurnTime = par1NBTTagCompound.getShort("BurnTime");
		this.mixCookTime = par1NBTTagCompound.getShort("CookTime");
		this.currentItemBurnTime = getItemBurnTime(this.mixItemStacks[1]);
	}

	/**
	 * Writes a tile entity to NBT.
	 */
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setShort("BurnTime", (short) this.mixBurnTime);
		par1NBTTagCompound.setShort("CookTime", (short) this.mixCookTime);
		NBTTagList var2 = new NBTTagList();

		for (int var3 = 0; var3 < this.mixItemStacks.length; ++var3) {
			if (this.mixItemStacks[var3] != null) {
				NBTTagCompound var4 = new NBTTagCompound();
				var4.setByte("Slot", (byte) var3);
				this.mixItemStacks[var3].writeToNBT(var4);
				var2.appendTag(var4);
			}
		}

		par1NBTTagCompound.setTag("Items", var2);
	}

	public int getInventoryStackLimit() {
		return 64;
	}

	public int getCookProgressScaled(int i) {
		return (mixCookTime * i) / 200;
	}

	public int getBurnTimeRemainingScaled(int i) {
		if (currentItemBurnTime == 0) {
			currentItemBurnTime = 200;
		}
		return (mixBurnTime * i) / currentItemBurnTime;
	}

	public boolean isBurning() {
		return mixBurnTime > 0;
	}

	public void updateEntity() {
		boolean flag = mixBurnTime > 0;
		boolean flag1 = false;
		if (mixBurnTime > 0) {
			mixBurnTime--;
		}
		if (!this.worldObj.isRemote) {
			if (mixBurnTime == 0 && canSmelt()) {
				currentItemBurnTime = mixBurnTime = getItemBurnTime(mixItemStacks[2]);
				if (mixBurnTime > 0) {
					flag1 = true;
					if (mixItemStacks[2] != null) {
						if (mixItemStacks[2].getItem().hasContainerItem()) {
							mixItemStacks[2] = new ItemStack(mixItemStacks[2]
									.getItem().getContainerItem());
						} else {
							mixItemStacks[2].stackSize--;
						}
						if (mixItemStacks[2].stackSize == 0) {
							mixItemStacks[2] = null;
						}
					}
				}
			}
			if (isBurning() && canSmelt()) {
				mixCookTime++;
				if (mixCookTime == 200) {
					mixCookTime = 0;
					smeltItem();
					flag1 = true;
				}
			} else {
				mixCookTime = 0;
			}
			if (flag != (mixBurnTime > 0)) {
				flag1 = true;
				Blockmix.updatemixBlockState(mixBurnTime > 0, worldObj, xCoord,
						yCoord, zCoord);
			}
		}
		if (flag1) {
			onInventoryChanged();
		}
	}

	private boolean canSmelt() {
		if (mixItemStacks[0] == null || mixItemStacks[1] == null) {
			// System.out.println("NO INPUT");
			return false;
		}
		ItemStack itemstack = mixRecipes.getSmeltingResult(
				mixItemStacks[0].getItem().shiftedIndex,
				mixItemStacks[1].getItem().shiftedIndex);
		if (itemstack == null) {
			// System.out.println("NO RECIPE");
			return false;
		}
		if (mixItemStacks[3] == null) {
			return true;
		}
		// System.out.println("NO SPACE");
		if (!mixItemStacks[3].isItemEqual(itemstack)) {
			// System.out.println("WRONG PRODUCT");
			return false;
		}
		if (mixItemStacks[3].stackSize < getInventoryStackLimit()
				&& mixItemStacks[3].stackSize < mixItemStacks[3]
						.getMaxStackSize()) {
			return true;
		}
		return mixItemStacks[3].stackSize < itemstack.getMaxStackSize();
	}

	public void smeltItem() {
		if (!canSmelt()) {
			return;
		}
		ItemStack itemstack = mixRecipes.getSmeltingResult(
				mixItemStacks[0].getItem().shiftedIndex,
				mixItemStacks[1].getItem().shiftedIndex);
		if (mixItemStacks[3] == null) {
			mixItemStacks[3] = itemstack.copy();
		} else if (mixItemStacks[3].itemID == itemstack.itemID) {
			mixItemStacks[3].stackSize++;
		}
		for (int i = 0; i < 2; i++) {
			if (mixItemStacks[i].getItem().hasContainerItem()) {
				mixItemStacks[i] = new ItemStack(mixItemStacks[i].getItem()
						.getContainerItem());
			} else {
				mixItemStacks[i].stackSize--;
			}
			if (mixItemStacks[i].stackSize <= 0) {
				mixItemStacks[i] = null;
			}
		}
	}

	private int getItemBurnTime(ItemStack itemstack) {
		if (itemstack == null) {
			return 0;
		}
		int i = itemstack.getItem().shiftedIndex;

		if (i == Item.redstone.shiftedIndex) {
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

	private ItemStack mixItemStacks[];
	public int mixBurnTime;
	public int currentItemBurnTime;
	public int mixCookTime;

	@Override
	public ItemStack getStackInSlotOnClosing(int var1) {
		// TODO Auto-generated method stub
		return null;
	}

}
