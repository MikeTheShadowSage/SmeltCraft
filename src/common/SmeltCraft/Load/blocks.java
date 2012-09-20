package SmeltCraft.Load;

import SmeltCraft.BlockBlast;
import SmeltCraft.Blockcool;
import SmeltCraft.Blockmix;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
//import SmeltCraft.BlockIds;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.ModLoader;

public class blocks {
    
    /* Block name constants */

	
    /* Mod block instances */
	public static Block bfurBlock;
	public static Block oreAnditite;
	public static Block bfurBlockOn;
	public static Block coolBlock;
	public static Block coolBlockOn;
	public static Block mixBlock;
	public static Block mixBlockOn;

	public static void init() {
		
		bfurBlock = (new BlockBlast(200, false))
				.setHardness(2.5F).setResistance(3.0F)
				.setStepSound(Block.soundStoneFootstep).setBlockName("blockblast");
		bfurBlockOn = (new BlockBlast(201, true))
				.setHardness(2.5F).setResistance(3.0F)
				.setStepSound(Block.soundStoneFootstep).setBlockName("blockyblast");
		oreAnditite = (new Block(202, Material.iron))
				.setHardness(2.5F).setResistance(3.0F)
				.setStepSound(Block.soundStoneFootstep).setBlockName("Anditite");
		coolBlock = (new Blockcool(203, false))
				.setHardness(2.5F).setResistance(3.0F)
				.setStepSound(Block.soundStoneFootstep).setBlockName("CoolBlock");
		coolBlockOn = (new Blockcool(204, true))
				.setHardness(2.5F).setResistance(3.0F)
				.setStepSound(Block.soundStoneFootstep).setBlockName("CoolBlockOn");
		mixBlock = (new Blockmix(205, false))
				.setHardness(2.5F).setResistance(3.0F)
				.setStepSound(Block.soundStoneFootstep).setBlockName("MixBlock");
		mixBlockOn = (new Blockmix(206, true))
				.setHardness(2.5F).setResistance(3.0F)
				.setStepSound(Block.soundStoneFootstep).setBlockName("MixBlockOn");
		
		
		
		
		
		LanguageRegistry.addName(bfurBlock, "Blast Furnace");
		LanguageRegistry.addName(oreAnditite, "Anditite Ore");
		LanguageRegistry.addName(coolBlock, "Cooler");
		
		GameRegistry.registerBlock(bfurBlock);
		GameRegistry.registerBlock(bfurBlockOn);
		GameRegistry.registerBlock(oreAnditite);
		GameRegistry.registerBlock(coolBlock);
		GameRegistry.registerBlock(coolBlockOn);
		GameRegistry.registerBlock(mixBlock);
		GameRegistry.registerBlock(mixBlockOn);
		// TODO: NeedsChanging.addSmelting(oreAnditite.blockID, new ItemStack(items.andIngot));

		/*
		calcinator = new BlockCalcinator(BlockIds.CALCINATOR).setBlockName(CALCINATOR_NAME);
		redWaterStill = new BlockRedWaterStill(BlockIds.RED_WATER_STILL, Material.water);
		redWaterFlowing = new BlockRedWaterFlowing(BlockIds.RED_WATER_STILL - 1, Material.water);
		
		LanguageRegistry.addName(calcinator, "Calcinator");
		LanguageRegistry.addName(redWaterStill, "Red Water (Still)");
		LanguageRegistry.addName(redWaterFlowing, "Red Water (Flowing)");
		
		GameRegistry.registerBlock(calcinator);
		GameRegistry.registerBlock(redWaterStill);
		GameRegistry.registerBlock(redWaterFlowing);*/
		
		initBlockRecipes();
		
	}
	
	private static void initBlockRecipes() {
	    
	    
		GameRegistry.addRecipe(
				new ItemStack(bfurBlock, 1),
				new Object[] { "XXX", "SFS", "EEE", Character.valueOf('X'),
						Item.coal, Character.valueOf('S'), Item.ingotIron,
						Character.valueOf('F'), Block.stoneOvenIdle,
						Character.valueOf('E'), Item.gunpowder });

		GameRegistry.addRecipe(new ItemStack(coolBlock, 1), new Object[] { "XXX",
				"XCX", "XXX", Character.valueOf('C'), Item.bucketWater,
				Character.valueOf('X'), Item.ingotIron });

	/*	GameRegistry.addRecipe(new ItemStack(mixBlock),
				new Object[] { "III", "IMI", "CRC", Character.valueOf('I'),
						Item.ingotIron, Character.valueOf('C'), Item.clay,
						Character.valueOf('M'), items.mixer, Character.valueOf('R'),
						Item.redstone, }); */
	    
	}

}
