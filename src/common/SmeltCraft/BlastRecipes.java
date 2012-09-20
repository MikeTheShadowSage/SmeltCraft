package SmeltCraft;

import net.minecraft.src.*;

import java.util.HashMap;
import java.util.Map;

import SmeltCraft.Load.items;

// Referenced classes of package net.minecraft.src:
//            Block, ItemStack, Item

public class BlastRecipes {

	public static final BlastRecipes smelting() {
		return smeltingBase;
	}

	private BlastRecipes() {
		smeltingList = new HashMap();
		addRoast(items.andIngot.shiftedIndex, new ItemStack(
				items.moltAnd));
		addRoast(Item.ingotIron.shiftedIndex,
				new ItemStack(items.moltIron));
		addRoast(Item.ingotGold.shiftedIndex,
				new ItemStack(items.moltGold));
		addRoast(items.ingotSteel.shiftedIndex, new ItemStack(
				items.moltSteel));
		addRoast(Block.stone.blockID, new ItemStack(items.moltStone));

	}

	public void addRoast(int i, ItemStack itemstack) {
		smeltingList.put(Integer.valueOf(i), itemstack);
	}

	public ItemStack getSmeltingResult(int i) {
		return (ItemStack) smeltingList.get(Integer.valueOf(i));
	}

	public Map getSmeltingList() {
		return smeltingList;
	}

	private static final BlastRecipes smeltingBase = new BlastRecipes();
	private Map smeltingList;

}
