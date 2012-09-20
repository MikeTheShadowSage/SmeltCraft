package SmeltCraft;

import net.minecraft.src.*;

public class aiAxe extends aiTool {
	public String getTextureFile() {
		return CommonProxy.ITEMS_PNG;
	}

	public aiAxe(int i, smelttoolmaterials smelttoolmaterials) {
		super(i, 3, smelttoolmaterials, blocksEffectiveAgainst);
	}

	private static Block blocksEffectiveAgainst[];

	static {
		blocksEffectiveAgainst = (new Block[] { Block.planks, Block.bookShelf,
				Block.wood, Block.chest, });
	}
}