package SmeltCraft;

import net.minecraft.src.*;

public class andSpade extends andTool {

	public andSpade(int i, smelttoolmaterials smelttoolmaterials) {
		super(i, 1, smelttoolmaterials, blocksEffectiveAgainst);
	}

	public String getTextureFile() {
		return CommonProxy.ITEMS_PNG;
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
