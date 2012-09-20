package SmeltCraft;

import net.minecraft.src.*;

public class agAxe extends agTool {

	public agAxe(int i, smelttoolmaterials smelttoolmaterials) {
		super(i, 3, smelttoolmaterials, blocksEffectiveAgainst);
	}

	public String getTextureFile() {
		return CommonProxy.ITEMS_PNG;
	}

	private static Block blocksEffectiveAgainst[];

	static {
		blocksEffectiveAgainst = (new Block[] { Block.planks, Block.bookShelf,
				Block.wood, Block.chest, });
	}
}