package SmeltCraft;

import net.minecraft.src.*;

public class agSpade extends agTool {
	public String getTextureFile() {
		return CommonProxy.ITEMS_PNG;
	}

	public agSpade(int i, smelttoolmaterials smelttoolmaterials) {
		super(i, 1, smelttoolmaterials, blocksEffectiveAgainst);
	}

	public boolean canHarvestBlock(Block block) {
		if (block == Block.snow) {
			return true;
		}
		return block == Block.blockSnow;
	}

	private static Block blocksEffectiveAgainst[];

	static {
		blocksEffectiveAgainst = (new Block[] { Block.grass, Block.dirt,
				Block.sand, Block.gravel, Block.snow, Block.blockSnow,
				Block.blockClay, Block.tilledField });
	}
}
