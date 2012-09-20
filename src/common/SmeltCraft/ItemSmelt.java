package SmeltCraft;

import net.minecraft.src.*;

import java.util.Random;

public class ItemSmelt extends Item {
	public ItemSmelt(int i) {
		super(i);
		maxStackSize = 64;
	}

	public String getTextureFile() {
		return CommonProxy.ITEMS_PNG;
	}
}