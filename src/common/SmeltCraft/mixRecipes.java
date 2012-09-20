package SmeltCraft;

import net.minecraft.src.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class mixRecipes {
	public static ItemStack getSmeltingResult(int i, int j) {
		return getOutput(i, j);
	}

	private static ItemStack getOutput(int i, int j) {
		if ((i == SmeltCraft.moltIron.shiftedIndex && j == SmeltCraft.moltGold.shiftedIndex)
				|| (i == SmeltCraft.moltGold.shiftedIndex && j == SmeltCraft.moltIron.shiftedIndex)) {
			return new ItemStack(SmeltCraft.mmgoldiron, 1);
		}
		if ((i == SmeltCraft.moltIron.shiftedIndex && j == SmeltCraft.moltAnd.shiftedIndex)
				|| (i == SmeltCraft.moltAnd.shiftedIndex && j == SmeltCraft.moltIron.shiftedIndex)) {
			return new ItemStack(SmeltCraft.mmironAnditite, 1);
		}
		if ((i == SmeltCraft.moltGold.shiftedIndex && j == SmeltCraft.moltAnd.shiftedIndex)
				|| (i == SmeltCraft.moltAnd.shiftedIndex && j == SmeltCraft.moltGold.shiftedIndex)) {
			return new ItemStack(SmeltCraft.mmgoldAnditite, 1);
		}

		if ((i == SmeltCraft.moltIron.shiftedIndex && j == SmeltCraft.moltIron.shiftedIndex)) {
			return new ItemStack(SmeltCraft.hmIron, 1);
		}

		if ((i == SmeltCraft.hmIron.shiftedIndex && j == SmeltCraft.hmIron.shiftedIndex)) {
			return new ItemStack(SmeltCraft.rhIron, 1);
		}

		if ((i == SmeltCraft.rhIron.shiftedIndex && j == SmeltCraft.rhIron.shiftedIndex)) {
			return new ItemStack(SmeltCraft.moltSteel, 1);
		}
		/*
		 * if(i == Block.oreGold.blockID && j == Block.oreGold.blockID) { return
		 * new ItemStack(Block.obsidian, 1); }
		 */
		else
			return null;
	}
}
