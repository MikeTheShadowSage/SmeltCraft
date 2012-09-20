package SmeltCraft;

import net.minecraft.src.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import SmeltCraft.Load.items;

public class mixRecipes {
	public static ItemStack getSmeltingResult(int i, int j) {
		return getOutput(i, j);
	}

	private static ItemStack getOutput(int i, int j) {
		if ((i == items.moltIron.shiftedIndex && j == items.moltGold.shiftedIndex)
				|| (i == items.moltGold.shiftedIndex && j == items.moltIron.shiftedIndex)) {
			return new ItemStack(items.mmgoldiron, 1);
		}
		if ((i == items.moltIron.shiftedIndex && j == items.moltAnd.shiftedIndex)
				|| (i == items.moltAnd.shiftedIndex && j == items.moltIron.shiftedIndex)) {
			return new ItemStack(items.mmironAnditite, 1);
		}
		if ((i == items.moltGold.shiftedIndex && j == items.moltAnd.shiftedIndex)
				|| (i == items.moltAnd.shiftedIndex && j == items.moltGold.shiftedIndex)) {
			return new ItemStack(items.mmgoldAnditite, 1);
		}

		if ((i == items.moltIron.shiftedIndex && j == items.moltIron.shiftedIndex)) {
			return new ItemStack(items.hmIron, 1);
		}

		if ((i == items.hmIron.shiftedIndex && j == items.hmIron.shiftedIndex)) {
			return new ItemStack(items.rhIron, 1);
		}

		if ((i == items.rhIron.shiftedIndex && j == items.rhIron.shiftedIndex)) {
			return new ItemStack(items.moltSteel, 1);
		}
		/*
		 * if(i == Block.oreGold.blockID && j == Block.oreGold.blockID) { return
		 * new ItemStack(Block.obsidian, 1); }
		 */
		else
			return null;
	}
}
