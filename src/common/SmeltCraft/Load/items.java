package SmeltCraft.Load;

import SmeltCraft.ItemSmelt;
import SmeltCraft.Smelt;
import SmeltCraft.Steel;
import SmeltCraft.agAxe;
import SmeltCraft.agPick;
import SmeltCraft.agSpade;
import SmeltCraft.agSword;
import SmeltCraft.aiAxe;
import SmeltCraft.aiPick;
import SmeltCraft.aiSpade;
import SmeltCraft.aiSword;
import SmeltCraft.andAxe;
import SmeltCraft.andPick;
import SmeltCraft.andSpade;
import SmeltCraft.andSword;
import SmeltCraft.giAxe;
import SmeltCraft.giPick;
import SmeltCraft.giSpade;
import SmeltCraft.giSword;
import SmeltCraft.smelttoolmaterials;
import SmeltCraft.steelAxe;
import SmeltCraft.steelPick;
import SmeltCraft.steelSpade;
import SmeltCraft.steelSword;
import cpw.mods.fml.common.registry.LanguageRegistry;
//TODO import SmeltCraft.ItemIds;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;

public class items {

	/* Item name constants */
	public static final String MINIUM_SHARD_NAME = "miniumShard";
	public static final String MINIUM_STONE_NAME = "miniumStone";
	public static final String PHILOSOPHER_STONE_NAME = "philStone";

	/* Mod item instances */
	public static Item moltAnd;
	public static Item moltGold;
	public static Item moltIron;
	public static Item moltStone;
	public static Item mmgoldiron;
	public static Item mmgoldAnditite;
	public static Item mmironAnditite;
	public static Item ironAnditite;
	public static Item andIngot;
	public static Item ironGold;
	public static Item goldAnditite;
	public static Item dDust;
	public static Item crusher;
	public static Item hmIron;
	public static Item moltSteel;
	public static Item ingotSteel;
	public static Item rhIron;
	public static Item steelBlock;
	public static Item bCoal;
	public static Item mmixer;
	public static Item mixer;
	
	//tools
	public static Item andPick;
	public static Item andSpade;
	public static Item andAxe;
	public static Item andSword;
	public static Item agSword;
	public static Item agAxe;
	public static Item agSpade;
	public static Item agPick;
	public static Item aiPick;
	public static Item aiSpade;
	public static Item aiAxe;
	public static Item aiSword;
	public static Item giSpade;
	public static Item giSword;
	public static Item giAxe;
	public static Item giPick;
	public static Item steelAxe;
	public static Item steelSword;
	public static Item steelSpade;
	public static Item steelPick;
	
	
	//public static Item ;

	public static void init() {
        /* Initialize each mod item individually */
    	  andIngot = (new Smelt(9002)).setItemName("Anditite Ingot");
    	  
    	  moltAnd = (new Smelt(9003))
    			.setItemName("Molten Anditite");
    	  moltGold = (new Smelt(9004))
    			.setItemName("Molten Gold");
    	  moltIron = (new Smelt(9005))
    			.setItemName("Molten Iron");
    	  moltStone = (new Smelt(9006))
    			.setItemName("Molten Stone");
    	  mmgoldiron = (new Smelt(9007))
    			.setItemName("Mixed Molten Gold And Iron");
    	  mmgoldAnditite = (new Smelt(9008))
    			.setItemName("Mixed Molten Gold And Anditite");
    	  mmironAnditite = (new Smelt(9009))
    			.setItemName("Mixed Iron And Anditite");
    	    ironAnditite = (new Smelt(9010))
    			.setItemName("Mixed Iron And Anditite Ingot");
    	    ironGold = (new Smelt(9011))
    			.setItemName("Mixed Iron And Gold Ingot");
    	    goldAnditite = (new Smelt(9022))
    			.setItemName("Mixed Gold And Anditite Ingot");
    	    dDust = (new Smelt(9012))
    			.setItemName("Diamond Dust");
    	    crusher = (new Smelt(9013))
    			.setItemName("Crusher");
    	    hmIron = (new Smelt(9014))
    			.setItemName("Heavy Molten Iron");
    	    moltSteel = (new Smelt(9015))
    			.setItemName("Molten Steel");
    	    ingotSteel = (new Steel(9016))
    			.setItemName("Hard Steel Ingot");
    	    rhIron = (new Smelt(9017))
    			.setItemName("Really Heavy Iron");
    	    steelBlock = (new Smelt(9019))
    			.setItemName("Steel Block");
    	    bCoal = (new Smelt(9018))
    			.setItemName("Blast Coal");
    	    mmixer = (new Smelt(9021))
    			.setItemName("Mixer Mold");
    	    mixer = (new Smelt(9020)).setItemName("Mixer");
    	/*  ingotCopper;
    	    ingotTin;
    	    ingotBronze;
			*/
    	    
    	    
    		 andPick = (new andPick(
    				ModLoader.getUniqueEntityId(), smelttoolmaterials.AND))
    				.setIconIndex(1).setItemName("AndPickaxe");
    		 andSpade = (new andSpade(
    				ModLoader.getUniqueEntityId(), smelttoolmaterials.AND))
    				.setItemName("AndSpade").setIconIndex(2);
    		 andAxe = (new andAxe(
    				ModLoader.getUniqueEntityId(), smelttoolmaterials.AND))
    				.setIconIndex(0).setItemName("AndAxe");
    		 andSword = (new andSword(
    				ModLoader.getUniqueEntityId(), smelttoolmaterials.AND))
    				.setIconIndex(3).setItemName("AndSword");

    		//  ingotTitanium = (new
    		// CamelOreItem(127)).setIconIndex(0).setItemName("ingotTitanium");
    		 agPick = (new agPick(
    				ModLoader.getUniqueEntityId(), smelttoolmaterials.ANDGOLD))
    				.setItemName("agPickaxe").setIconIndex(5);
    		 agSpade = (new agSpade(
    				ModLoader.getUniqueEntityId(), smelttoolmaterials.ANDGOLD))
    				.setItemName("agSpade").setIconIndex(6);
    		 agAxe = (new agAxe(ModLoader.getUniqueEntityId(),
    				smelttoolmaterials.ANDGOLD)).setItemName("agAxe").setIconIndex(4);
    		 agSword = (new agSword(
    				ModLoader.getUniqueEntityId(), smelttoolmaterials.ANDGOLD))
    				.setItemName("agSword").setIconIndex(7);

    		 aiPick = (new aiPick(
    				ModLoader.getUniqueEntityId(), smelttoolmaterials.IRONAND))
    				.setItemName("aiPickaxe").setIconIndex(9);
    		 aiSpade = (new aiSpade(
    				ModLoader.getUniqueEntityId(), smelttoolmaterials.IRONAND))
    				.setItemName("aiSpade").setIconIndex(10);
    		 aiAxe = (new aiAxe(ModLoader.getUniqueEntityId(),
    				smelttoolmaterials.IRONAND)).setItemName("aiAxe").setIconIndex(8);
    		 aiSword = (new aiSword(
    				ModLoader.getUniqueEntityId(), smelttoolmaterials.IRONAND))
    				.setItemName("aiSword").setIconIndex(11);

    		 giPick = (new giPick(
    				ModLoader.getUniqueEntityId(), smelttoolmaterials.GOLDIRON))
    				.setItemName("giPickaxe").setIconIndex(13);
    		 giSpade = (new giSpade(
    				ModLoader.getUniqueEntityId(), smelttoolmaterials.GOLDIRON))
    				.setItemName("giSpade").setIconIndex(14);
    		 giAxe = (new giAxe(ModLoader.getUniqueEntityId(),
    				smelttoolmaterials.GOLDIRON)).setItemName("giAxe").setIconIndex(12);
    		 giSword = (new giSword(
    				ModLoader.getUniqueEntityId(), smelttoolmaterials.GOLDIRON))
    				.setItemName("giSword").setIconIndex(15);

    		 steelPick = (new steelPick(
    				ModLoader.getUniqueEntityId(), smelttoolmaterials.STEEL))
    				.setItemName("SteelPickaxe").setIconIndex(17);
    		 steelSpade = (new steelSpade(
    				ModLoader.getUniqueEntityId(), smelttoolmaterials.STEEL))
    				.setItemName("SteelSpade").setIconIndex(18);
    		 steelAxe = (new steelAxe(
    				ModLoader.getUniqueEntityId(), smelttoolmaterials.STEEL))
    				.setItemName("SteelAxe").setIconIndex(16);
    		 steelSword = (new steelSword(
    				ModLoader.getUniqueEntityId(), smelttoolmaterials.STEEL))
    				.setItemName("SteelSword").setIconIndex(19);
    	    
    	    
    	    
    	    
    		LanguageRegistry.addName(andPick, "Anditite Pickaxe");
    		LanguageRegistry.addName(andSpade, "Anditite Shovel");
    		LanguageRegistry.addName(andAxe, "Anditite Axe");
    		LanguageRegistry.addName(andSword, "Anditite Sword");

    		LanguageRegistry.addName(agPick, "Mixed Pickaxe (Gold + Anditite)");
    		LanguageRegistry.addName(agSpade, "Mixed Shovel (Gold + Anditite)");
    		LanguageRegistry.addName(agAxe, "Mixed Axe (Gold + Anditite)");
    		LanguageRegistry.addName(agSword, "Mixed Sword (Gold + Anditite)");

    		LanguageRegistry.addName(aiPick, "Mixed Pickaxe (Iron + Anditite)");
    		LanguageRegistry.addName(aiSpade, "Mixed Shovel (Iron + Anditite)");
    		LanguageRegistry.addName(aiAxe, "Mixed Axe (Iron + Anditite)");
    		LanguageRegistry.addName(aiSword, "Mixed Sword (Iron + Anditite)");

    		LanguageRegistry.addName(giPick, "Mixed Pickaxe (Gold + Iron)");
    		LanguageRegistry.addName(giSpade, "Mixed Shovel (Gold + Iron)");
    		LanguageRegistry.addName(giAxe, "Mixed Axe (Gold + Iron)");
    		LanguageRegistry.addName(giSword, "Mixed Sword (Gold + Iron)");

    		LanguageRegistry.addName(steelPick, "Steel Pick");
    		LanguageRegistry.addName(steelSpade, "Steel Shovel");
    		LanguageRegistry.addName(steelAxe, "Steel Axe");
    		LanguageRegistry.addName(steelSword, "Steel Sword)");
    	    
    		LanguageRegistry.addName(andIngot, "Anditite Ingot");
    		LanguageRegistry.addName(moltAnd, "Molten Anditite");
    		LanguageRegistry.addName(moltGold, "Molten Gold");
    		LanguageRegistry.addName(moltIron, "Molten Iron");
    		LanguageRegistry.addName(moltStone, "Molten Stone");
    		LanguageRegistry.addName(mmgoldiron, "Mixed Molten Gold And Iron");
    		LanguageRegistry.addName(mmgoldAnditite, "Mixed Molten Gold And Anditite");
    		LanguageRegistry.addName(mmironAnditite, "Mixed Iron And Anditite");
    		LanguageRegistry.addName(ironAnditite, "Mixed Iron And Anditite Ingot");
    		LanguageRegistry.addName(ironGold, "Mixed Iron And Gold Ingot");
    		LanguageRegistry.addName(goldAnditite, "Mixed Gold And Anditite Ingot");
    		LanguageRegistry.addName(dDust, "Diamond Dust");
    		LanguageRegistry.addName(ingotSteel, "Steel Ingot");
    		LanguageRegistry.addName(moltSteel, "Molten Steel");
    		LanguageRegistry.addName(bCoal, "Blast Coal");
    		LanguageRegistry.addName(hmIron, "Heavy Molten Iron");
    		LanguageRegistry.addName(rhIron, "Really Heavy Molten Iron");
    		LanguageRegistry.addName(mmixer, "Mixer Mold");
    		LanguageRegistry.addName(mixer, "Mixer Spinner");
    	    
    }
	
	public static void itemCraft(){
		
		


		ModLoader.addRecipe(new ItemStack(mmixer, 1),
				new Object[] { "CIC", "III", "CCC", Character.valueOf('I'),
						moltIron, Character.valueOf('C'), Item.clay, });

		ModLoader.addRecipe(new ItemStack(andPick, 1),
				new Object[] { "AAA", " S ", " S ", Character.valueOf('S'),
						Item.stick, Character.valueOf('A'), andIngot });

		ModLoader.addRecipe(new ItemStack(andAxe, 1),
				new Object[] { " AA", " SA", " S ", Character.valueOf('S'),
						Item.stick, Character.valueOf('A'), andIngot });

		ModLoader.addRecipe(new ItemStack(andSword, 1),
				new Object[] { "A", "A", "S", Character.valueOf('S'),
						Item.stick, Character.valueOf('A'), andIngot });

		ModLoader.addRecipe(new ItemStack(andSpade, 1),
				new Object[] { "A", "S", "S", Character.valueOf('S'),
						Item.stick, Character.valueOf('A'), andIngot });
		// sdasd
		ModLoader.addRecipe(new ItemStack(bCoal, 4),
				new Object[] { "GGG", "GCG", "GGG", Character.valueOf('G'),
						Item.gunpowder, Character.valueOf('C'), Item.coal });

		ModLoader.addRecipe(new ItemStack(aiSpade, 1), new Object[] { "A", "S",
				"S", Character.valueOf('S'), Item.stick,
				Character.valueOf('A'), ironAnditite });

		ModLoader.addRecipe(new ItemStack(aiPick, 1),
				new Object[] { "AAA", " S ", " S ", Character.valueOf('S'),
						Item.stick, Character.valueOf('A'), ironAnditite });

		ModLoader.addRecipe(new ItemStack(aiAxe, 1),
				new Object[] { " AA", " SA", " S ", Character.valueOf('S'),
						Item.stick, Character.valueOf('A'), ironAnditite });

		ModLoader.addRecipe(new ItemStack(aiSword, 1), new Object[] { "A", "A",
				"S", Character.valueOf('S'), Item.stick,
				Character.valueOf('A'), ironAnditite });

		ModLoader.addRecipe(new ItemStack(agSword, 1), new Object[] { "A", "A",
				"S", Character.valueOf('S'), Item.stick,
				Character.valueOf('A'), goldAnditite });

		ModLoader.addRecipe(new ItemStack(agSpade, 1), new Object[] { "A", "S",
				"S", Character.valueOf('S'), Item.stick,
				Character.valueOf('A'), goldAnditite });

		ModLoader.addRecipe(new ItemStack(agAxe, 1),
				new Object[] { " AA", " SA", " S ", Character.valueOf('S'),
						Item.stick, Character.valueOf('A'), goldAnditite });

		ModLoader.addRecipe(new ItemStack(agPick, 1),
				new Object[] { "AAA", " S ", " S ", Character.valueOf('S'),
						Item.stick, Character.valueOf('A'), goldAnditite });

		ModLoader.addRecipe(new ItemStack(giSword, 1), new Object[] { "A", "A",
				"S", Character.valueOf('S'), Item.stick,
				Character.valueOf('A'), ironGold });

		ModLoader.addRecipe(new ItemStack(giSpade, 1), new Object[] { "A", "S",
				"S", Character.valueOf('S'), Item.stick,
				Character.valueOf('A'), ironGold });

		ModLoader.addRecipe(new ItemStack(giPick, 1),
				new Object[] { "AAA", " S ", " S ", Character.valueOf('S'),
						Item.stick, Character.valueOf('A'), ironGold });

		ModLoader.addRecipe(new ItemStack(giAxe, 1),
				new Object[] { " AA", " SA", " S ", Character.valueOf('S'),
						Item.stick, Character.valueOf('A'), ironGold });

		ModLoader.addRecipe(new ItemStack(steelAxe, 1),
				new Object[] { " AA", " SA", " S ", Character.valueOf('S'),
						Item.stick, Character.valueOf('A'), ironGold });

		ModLoader.addRecipe(new ItemStack(steelPick, 1),
				new Object[] { "AAA", " S ", " S ", Character.valueOf('S'),
						Item.stick, Character.valueOf('A'), ironGold });

		ModLoader.addRecipe(new ItemStack(steelSpade, 1),
				new Object[] { "A", "S", "S", Character.valueOf('S'),
						Item.stick, Character.valueOf('A'), ironGold });

		ModLoader.addRecipe(new ItemStack(steelSword, 1),
				new Object[] { "A", "A", "S", Character.valueOf('S'),
						Item.stick, Character.valueOf('A'), ironGold });

		ModLoader.addRecipe(new ItemStack(Item.bucketLava, 1), new Object[] {
				"MMM", "MMM", "MBM", Character.valueOf('M'), moltStone,
				Character.valueOf('B'), Item.bucketEmpty });

		
		
		
		
	}
}
