package SmeltCraft;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Slot;
import net.minecraft.src.Container;

public class ContainerSmelt extends Container {

	private int inventorySize;

	public ContainerSmelt(int inventorySize) {
		this.inventorySize = inventorySize;
	}

	@Override
	public final ItemStack transferStackInSlot(int i) {
		ItemStack itemstack = null;
		Slot slot = (Slot) inventorySlots.get(i);

		if (slot != null && slot.getHasStack()) {

			ItemStack itemstackMerge = slot.getStack();
			itemstack = itemstackMerge.copy();
			if (i < inventorySize)
				mergeItemStack(itemstackMerge, inventorySize,
						inventorySlots.size(), true);
			else
				mergeItemStack(itemstackMerge, 0, inventorySize, false);

			if (itemstackMerge.stackSize == 0)
				slot.putStack(null);
			else
				slot.onSlotChanged();

		}

		return itemstack;
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}

}
