package SmeltCraft;

import net.minecraft.src.*;

import java.util.HashMap;
import java.util.Map;

// Referenced classes of package net.minecraft.src:
//            Block, ItemStack, Item

public class BlastRecipes {

	public static final BlastRecipes smelting() {
		return smeltingBase;
	}

	private BlastRecipes() {
		smeltingList = new HashMap();
		addRoast(SmeltCraft.andIngot.shiftedIndex, new ItemStack(
				SmeltCraft.moltAnd));
		addRoast(Item.ingotIron.shiftedIndex,
				new ItemStack(SmeltCraft.moltIron));
		addRoast(Item.ingotGold.shiftedIndex,
				new ItemStack(SmeltCraft.moltGold));
		addRoast(SmeltCraft.ingotSteel.shiftedIndex, new ItemStack(
				SmeltCraft.moltSteel));
		addRoast(Block.stone.blockID, new ItemStack(SmeltCraft.moltStone));

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
