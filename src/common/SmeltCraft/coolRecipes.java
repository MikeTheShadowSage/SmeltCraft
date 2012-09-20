package SmeltCraft;

import net.minecraft.src.*;

import java.util.HashMap;
import java.util.Map;

import SmeltCraft.Load.items;

// Referenced classes of package net.minecraft.src:
//            Block, ItemStack, Item

public class coolRecipes {

	public static final coolRecipes smelting() {
		return smeltingBase;
	}

	private coolRecipes() {
		smeltingList = new HashMap();
		addFrost(Item.bucketWater.shiftedIndex, new ItemStack(Block.ice));
		addFrost(items.mmgoldAnditite.shiftedIndex, new ItemStack(
				items.goldAnditite));
		addFrost(items.mmgoldiron.shiftedIndex, new ItemStack(
				items.ironGold));
		addFrost(items.mmironAnditite.shiftedIndex, new ItemStack(
				items.ironAnditite));
		addFrost(items.moltAnd.shiftedIndex, new ItemStack(
				items.andIngot));
		addFrost(items.moltGold.shiftedIndex,
				new ItemStack(Item.ingotGold));
		addFrost(items.moltIron.shiftedIndex,
				new ItemStack(Item.ingotIron));
		addFrost(items.moltSteel.shiftedIndex, new ItemStack(
				items.ingotSteel));
		addFrost(items.moltStone.shiftedIndex, new ItemStack(Block.stone));
		addFrost(items.hmIron.shiftedIndex, new ItemStack(Item.ingotIron,
				2));
		addFrost(items.rhIron.shiftedIndex, new ItemStack(Item.ingotIron,
				4));
		addFrost(items.mmixer.shiftedIndex,
				new ItemStack(items.mixer));

	}

	public void addFrost(int i, ItemStack itemstack) {
		smeltingList.put(Integer.valueOf(i), itemstack);
	}

	public ItemStack getSmeltingResult(int i) {
		return (ItemStack) smeltingList.get(Integer.valueOf(i));
	}

	public Map getSmeltingList() {
		return smeltingList;
	}

	private static final coolRecipes smeltingBase = new coolRecipes();
	private Map smeltingList;

}
