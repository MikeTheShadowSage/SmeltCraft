package SmeltCraft;

import net.minecraft.src.*;

public class aiPick extends aiTool {

	public aiPick(int i, smelttoolmaterials smelttoolmaterials) {
		super(i, 2, smelttoolmaterials, blocksEffectiveAgainst);
	}

	public String getTextureFile() {
		return CommonProxy.ITEMS_PNG;
	}

	public boolean canHarvestBlock(Block block) {
		if (block == Block.obsidian) {
			return toolMaterial.getHarvestLevel() == 3;
		}
		if (block == Block.blockDiamond || block == Block.oreDiamond) {
			return toolMaterial.getHarvestLevel() >= 2;
		}
		if (block == Block.blockGold || block == Block.oreGold) {
			return toolMaterial.getHarvestLevel() >= 2;
		}
		if (block == Block.blockSteel || block == Block.oreIron) {
			return toolMaterial.getHarvestLevel() >= 1;
		}
		if (block == Block.blockLapis || block == Block.oreLapis) {
			return toolMaterial.getHarvestLevel() >= 1;
		}
		if (block == Block.blockLapis || block == Block.oreLapis) {
			return toolMaterial.getHarvestLevel() >= 1;
		}
		if (block == Block.blockLapis || block == Block.oreLapis) {
			return toolMaterial.getHarvestLevel() >= 1;
		}
		if (block == Block.blockLapis || block == Block.oreLapis) {
			return toolMaterial.getHarvestLevel() >= 1;
		}

		if (block.blockMaterial == Material.rock) {
			return true;
		}
		return block.blockMaterial == Material.iron;
	}

	private static Block blocksEffectiveAgainst[];

	static {
		blocksEffectiveAgainst = (new Block[] { Block.cobblestone, Block.stone,
				Block.sandStone, Block.cobblestoneMossy, Block.oreIron,
				Block.blockSteel, Block.oreCoal, Block.blockGold,
				Block.oreGold, Block.oreDiamond, Block.blockDiamond, Block.ice,
				Block.netherrack, Block.oreLapis, Block.blockLapis

		});
		/*
		 * shovelSteel = (new mod_NightmareSpade(0,
		 * smelttoolmaterials.IRON)).setIconCoord(2,
		 * 5).setItemName("shovelIron"); pickaxeSteel = (new aiPick(1,
		 * smelttoolmaterials.IRON)).setIconCoord(2,
		 * 6).setItemName("pickaxeIron"); axeSteel = (new mod_NightmareAxe(2,
		 * smelttoolmaterials.IRON)).setIconCoord(2,
		 * 7).setItemName("hatchetIron"); swordSteel = (new
		 * mod_NightmareSword(11, smelttoolmaterials.IRON)).setIconCoord(2,
		 * 4).setItemName("swordIron"); swordWood = (new mod_NightmareSword(12,
		 * smelttoolmaterials.WOOD)).setIconCoord(0,
		 * 4).setItemName("swordWood"); shovelWood = (new mod_NightmareSpade(13,
		 * smelttoolmaterials.WOOD)).setIconCoord(0,
		 * 5).setItemName("shovelWood"); pickaxeWood = (new aiPick(14,
		 * smelttoolmaterials.WOOD)).setIconCoord(0,
		 * 6).setItemName("pickaxeWood"); axeWood = (new mod_NightmareAxe(15,
		 * smelttoolmaterials.WOOD)).setIconCoord(0,
		 * 7).setItemName("hatchetWood"); swordStone = (new
		 * mod_NightmareSword(16, smelttoolmaterials.STONE)).setIconCoord(1,
		 * 4).setItemName("swordStone"); shovelStone = (new
		 * mod_NightmareSpade(17, smelttoolmaterials.STONE)).setIconCoord(1,
		 * 5).setItemName("shovelStone"); pickaxeStone = (new aiPick(18,
		 * smelttoolmaterials.STONE)).setIconCoord(1,
		 * 6).setItemName("pickaxeStone"); axeStone = (new mod_NightmareAxe(19,
		 * smelttoolmaterials.STONE)).setIconCoord(1,
		 * 7).setItemName("hatchetStone"); swordDiamond = (new
		 * mod_NightmareSword(20, smelttoolmaterials.EMERALD)).setIconCoord(3,
		 * 4).setItemName("swordDiamond"); shovelDiamond = (new
		 * mod_NightmareSpade(21, smelttoolmaterials.EMERALD)).setIconCoord(3,
		 * 5).setItemName("shovelDiamond"); pickaxeDiamond = (new aiPick(22,
		 * smelttoolmaterials.EMERALD)).setIconCoord(3,
		 * 6).setItemName("pickaxeDiamond"); axeDiamond = (new
		 * mod_NightmareAxe(23, smelttoolmaterials.EMERALD)).setIconCoord(3,
		 * 7).setItemName("hatchetDiamond"); swordGold = (new
		 * mod_NightmareSword(27, smelttoolmaterials.GOLD)).setIconCoord(4,
		 * 4).setItemName("swordGold"); shovelGold = (new mod_NightmareSpade(28,
		 * smelttoolmaterials.GOLD)).setIconCoord(4,
		 * 5).setItemName("shovelGold"); pickaxeGold = (new aiPick(29,
		 * smelttoolmaterials.GOLD)).setIconCoord(4,
		 * 6).setItemName("pickaxeGold"); axeGold = (new mod_NightmareAxe(30,
		 * smelttoolmaterials.GOLD)).setIconCoord(4,
		 * 7).setItemName("hatchetGold"); hoeWood = (new mod_NightmareHoe(34,
		 * smelttoolmaterials.WOOD)).setIconCoord(0, 8).setItemName("hoeWood");
		 * hoeStone = (new mod_NightmareHoe(35,
		 * smelttoolmaterials.STONE)).setIconCoord(1,
		 * 8).setItemName("hoeStone"); hoeSteel = (new mod_NightmareHoe(36,
		 * smelttoolmaterials.IRON)).setIconCoord(2, 8).setItemName("hoeIron");
		 * hoeDiamond = (new mod_NightmareHoe(37,
		 * smelttoolmaterials.EMERALD)).setIconCoord(3,
		 * 8).setItemName("hoeDiamond"); hoeGold = (new mod_NightmareHoe(38,
		 * smelttoolmaterials.GOLD)).setIconCoord(4, 8).setItemName("hoeGold");
		 */
	}
}
