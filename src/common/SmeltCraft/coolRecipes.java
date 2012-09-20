package SmeltCraft;

import net.minecraft.src.*;

import java.util.HashMap;
import java.util.Map;

// Referenced classes of package net.minecraft.src:
//            Block, ItemStack, Item

public class coolRecipes {

	public static final coolRecipes smelting() {
		return smeltingBase;
	}

	private coolRecipes() {
		smeltingList = new HashMap();
		addFrost(Item.bucketWater.shiftedIndex, new ItemStack(Block.ice));
		addFrost(SmeltCraft.mmgoldAnditite.shiftedIndex, new ItemStack(
				SmeltCraft.goldAnditite));
		addFrost(SmeltCraft.mmgoldiron.shiftedIndex, new ItemStack(
				SmeltCraft.ironGold));
		addFrost(SmeltCraft.mmironAnditite.shiftedIndex, new ItemStack(
				SmeltCraft.ironAnditite));
		addFrost(SmeltCraft.moltAnd.shiftedIndex, new ItemStack(
				SmeltCraft.andIngot));
		addFrost(SmeltCraft.moltGold.shiftedIndex,
				new ItemStack(Item.ingotGold));
		addFrost(SmeltCraft.moltIron.shiftedIndex,
				new ItemStack(Item.ingotIron));
		addFrost(SmeltCraft.moltSteel.shiftedIndex, new ItemStack(
				SmeltCraft.ingotSteel));
		addFrost(SmeltCraft.moltStone.shiftedIndex, new ItemStack(Block.stone));
		addFrost(SmeltCraft.hmIron.shiftedIndex, new ItemStack(Item.ingotIron,
				2));
		addFrost(SmeltCraft.rhIron.shiftedIndex, new ItemStack(Item.ingotIron,
				4));
		addFrost(SmeltCraft.mmixer.shiftedIndex,
				new ItemStack(SmeltCraft.mixer));

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
