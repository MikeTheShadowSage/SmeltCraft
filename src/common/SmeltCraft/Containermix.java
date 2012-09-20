package SmeltCraft;

import net.minecraft.src.*;

import java.util.List;

public class Containermix extends ContainerSmelt {

	public Containermix(InventoryPlayer inventoryplayer,
			TileEntitymix tileentitymix) {
		super(tileentitymix.getSizeInventory());
		lastCookTime = 0;
		lastBurnTime = 0;
		lastItemBurnTime = 0;
		mix = tileentitymix;
		addSlotToContainer(new Slot(tileentitymix, 0, 38, 17));
		addSlotToContainer(new Slot(tileentitymix, 1, 74, 17));
		addSlotToContainer(new Slot(tileentitymix, 2, 56, 53));
		addSlotToContainer(new Slotmix(inventoryplayer.player, tileentitymix,
				3, 116, 35));
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
			if (lastCookTime != mix.mixCookTime) {
				icrafting.updateCraftingInventoryInfo(this, 0, mix.mixCookTime);
			}
			if (lastBurnTime != mix.mixBurnTime) {
				icrafting.updateCraftingInventoryInfo(this, 1, mix.mixBurnTime);
			}
			if (lastItemBurnTime != mix.currentItemBurnTime) {
				icrafting.updateCraftingInventoryInfo(this, 2,
						mix.currentItemBurnTime);
			}
		}

		lastCookTime = mix.mixCookTime;
		lastBurnTime = mix.mixBurnTime;
		lastItemBurnTime = mix.currentItemBurnTime;
	}

	public void func_20112_a(int i, int j) {
		if (i == 0) {
			mix.mixCookTime = j;
		}
		if (i == 1) {
			mix.mixBurnTime = j;
		}
		if (i == 2) {
			mix.currentItemBurnTime = j;
		}
	}

	public boolean canInteractWith(EntityPlayer entityplayer) {
		return mix.isUseableByPlayer(entityplayer);
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

	private TileEntitymix mix;
	private int lastCookTime;
	private int lastBurnTime;
	private int lastItemBurnTime;
}
