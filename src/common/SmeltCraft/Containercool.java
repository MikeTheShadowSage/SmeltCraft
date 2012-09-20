package SmeltCraft;

import net.minecraft.src.*;

import java.util.List;

// Referenced classes of package net.minecraft.src:
//            Container, Slot, SlotFurnace, InventoryPlayer, 
//            ICrafting, TileEntityFurnace, ItemStack, EntityPlayer

public class Containercool extends ContainerSmelt {

	public Containercool(InventoryPlayer inventoryplayer,
			TileEntitycool tileentitycool) {
		super(tileentitycool.getSizeInventory());
		CookTime = 0;
		BurnTime = 0;
		ItemBurnTime = 0;
		cool = tileentitycool;
		addSlotToContainer(new Slot(tileentitycool, 0, 56, 17));
		addSlotToContainer(new Slot(tileentitycool, 1, 56, 53));
		addSlotToContainer(new Slotcool(inventoryplayer.player, tileentitycool,
				2, 116, 35));
		for (int i = 0; i < 3; i++) {
			for (int k = 0; k < 9; k++) {
				addSlotToContainer(new Slot(inventoryplayer, k + i * 9 + 9,
						8 + k * 18, 84 + i * 18));
			}

		}

		for (int j = 0; j < 9; j++) {
			addSlotToContainer(new Slot(inventoryplayer, j, 8 + j * 18, 142));
		}

	}

	public void updateCraftingResults() {
		super.updateCraftingResults();
		for (int i = 0; i < crafters.size(); i++) {
			ICrafting icrafting = (ICrafting) crafters.get(i);
			if (CookTime != cool.coolCookTime) {
				icrafting.updateCraftingInventoryInfo(this, 0,
						cool.coolCookTime);
			}
			if (BurnTime != cool.coolBurnTime) {
				icrafting.updateCraftingInventoryInfo(this, 1,
						cool.coolBurnTime);
			}
			if (ItemBurnTime != cool.currentItemBurnTime) {
				icrafting.updateCraftingInventoryInfo(this, 2,
						cool.currentItemBurnTime);
			}
		}

		CookTime = cool.coolCookTime;
		BurnTime = cool.coolBurnTime;
		ItemBurnTime = cool.currentItemBurnTime;
	}

	public void func_20112_a(int i, int j) {
		if (i == 0) {
			cool.coolCookTime = j;
		}
		if (i == 1) {
			cool.coolBurnTime = j;
		}
		if (i == 2) {
			cool.currentItemBurnTime = j;
		}
	}

	public boolean canInteractWith(EntityPlayer entityplayer) {
		return cool.isUseableByPlayer(entityplayer);
	}

	public ItemStack getStackInSlot(int i) {
		ItemStack itemstack = null;
		Slot slot = (Slot) inventorySlots.get(i);
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if (i == 2) {
				if (!mergeItemStack(itemstack1, 3, 39, true)) {
					return null;
				}
			} else if (i >= 3 && i < 30) {
				if (!mergeItemStack(itemstack1, 30, 39, false)) {
					return null;
				}
			} else if (i >= 30 && i < 39) {
				if (!mergeItemStack(itemstack1, 3, 30, false)) {
					return null;
				}
			} else if (!mergeItemStack(itemstack1, 3, 39, false)) {
				return null;
			}
			if (itemstack1.stackSize == 0) {
				slot.putStack(null);
			} else {
				slot.onSlotChanged();
			}
			if (itemstack1.stackSize != itemstack.stackSize) {
				slot.onPickupFromSlot(itemstack1);
			} else {
				return null;
			}
		}
		return itemstack;
	}

	private TileEntitycool cool;
	private int CookTime;
	private int BurnTime;
	private int ItemBurnTime;
}
