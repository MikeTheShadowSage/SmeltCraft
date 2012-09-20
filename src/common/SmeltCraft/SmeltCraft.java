package SmeltCraft;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.ModLoader;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenMinable;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = "Smeltcraft2", name = "Smeltcraft 2", version = "0.0.1")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class SmeltCraft {

	// Blocks, Tools, And item Class Declarations!

	public static int coolID;
	public static int blastID;

	public static final Block bfurBlock = (new BlockBlast(200, false))
			.setHardness(2.5F).setResistance(3.0F)
			.setStepSound(Block.soundStoneFootstep).setBlockName("blockblast");
	public static final Block bfurBlockOn = (new BlockBlast(201, true))
			.setHardness(2.5F).setResistance(3.0F)
			.setStepSound(Block.soundStoneFootstep).setBlockName("blockyblast");
	public static final Block oreAnditite = (new Block(202, Material.iron))
			.setHardness(2.5F).setResistance(3.0F)
			.setStepSound(Block.soundStoneFootstep).setBlockName("Anditite");
	public static final Block coolBlock = (new Blockcool(203, false))
			.setHardness(2.5F).setResistance(3.0F)
			.setStepSound(Block.soundStoneFootstep).setBlockName("CoolBlock");
	public static final Block coolBlockOn = (new Blockcool(204, true))
			.setHardness(2.5F).setResistance(3.0F)
			.setStepSound(Block.soundStoneFootstep).setBlockName("CoolBlockOn");
	public static final Block mixBlock = (new Blockmix(205, false))
			.setHardness(2.5F).setResistance(3.0F)
			.setStepSound(Block.soundStoneFootstep).setBlockName("MixBlock");
	public static final Block mixBlockOn = (new Blockmix(206, true))
			.setHardness(2.5F).setResistance(3.0F)
			.setStepSound(Block.soundStoneFootstep).setBlockName("MixBlockOn");

	public static int blastSides;
	public static int blastOff;
	public static int blastOn;
	public static int blastTop;
	public static int coolOff;
	public static int coolOn;
	public static int coolSides;
	public static int coolTop;
	public static int mixOn;
	public static int mixOff;
	public static int mixSides;
	public static int mixTop;
	public static int essenceOn;
	public static int essenceOff;
	public static int essenceSides;
	public static int essenceTop;
	// Items

	// public static final Item iceIngot = (new
	// ItemSmelt(9001)).setItemName("Ice Ingot Level 1");
	public static final Item andIngot = (new ItemSmelt(9002))
			.setItemName("Anditite Ingot");
	public static final Item moltAnd = (new Smelt(9003))
			.setItemName("Molten Anditite");
	public static final Item moltGold = (new Smelt(9004))
			.setItemName("Molten Gold");
	public static final Item moltIron = (new Smelt(9005))
			.setItemName("Molten Iron");
	public static final Item moltStone = (new Smelt(9006))
			.setItemName("Molten Stone");
	public static final Item mmgoldiron = (new Smelt(9007))
			.setItemName("Mixed Molten Gold And Iron");
	public static final Item mmgoldAnditite = (new Smelt(9008))
			.setItemName("Mixed Molten Gold And Anditite");
	public static final Item mmironAnditite = (new Smelt(9009))
			.setItemName("Mixed Iron And Anditite");
	public static final Item ironAnditite = (new ItemSmelt(9010))
			.setItemName("Mixed Iron And Anditite Ingot");
	public static final Item ironGold = (new ItemSmelt(9011))
			.setItemName("Mixed Iron And Gold Ingot");
	public static final Item goldAnditite = (new ItemSmelt(9022))
			.setItemName("Mixed Gold And Anditite Ingot");
	public static final Item dDust = (new ItemSmelt(9012))
			.setItemName("Diamond Dust");
	public static final Item crusher = (new ItemSmelt(9013))
			.setItemName("Crusher");
	public static final Item hmIron = (new Smelt(9014))
			.setItemName("Heavy Molten Iron");
	public static final Item moltSteel = (new Smelt(9015))
			.setItemName("Molten Steel");
	public static final Item ingotSteel = (new Steel(9016))
			.setItemName("Steel Ingot");
	public static final Item rhIron = (new Smelt(9017))
			.setItemName("Really Heavy Iron");
	public static final Item steelBlock = (new ItemSmelt(9019))
			.setItemName("Steel Block");
	public static final Item bCoal = (new ItemSmelt(9018))
			.setItemName("Blast Coal");
	public static final Item mmixer = (new ItemSmelt(9021))
			.setItemName("Mixer Mold");
	public static final Item mixer = (new ItemSmelt(9020)).setItemName("Mixer");
	public static Item ingotCopper;
	public static Item ingotTin;
	public static Item ingotBronze;
	// 22+
	// Tools
	public static final Item andPick = (new andPick(
			ModLoader.getUniqueEntityId(), smelttoolmaterials.AND))
			.setIconIndex(1).setItemName("AndPickaxe");
	public static final Item andSpade = (new andSpade(
			ModLoader.getUniqueEntityId(), smelttoolmaterials.AND))
			.setItemName("AndSpade").setIconIndex(2);
	public static final Item andAxe = (new andAxe(
			ModLoader.getUniqueEntityId(), smelttoolmaterials.AND))
			.setIconIndex(0).setItemName("AndAxe");
	public static final Item andSword = (new andSword(
			ModLoader.getUniqueEntityId(), smelttoolmaterials.AND))
			.setIconIndex(3).setItemName("AndSword");

	// public static final Item ingotTitanium = (new
	// CamelOreItem(127)).setIconIndex(0).setItemName("ingotTitanium");
	public static final Item agPick = (new agPick(
			ModLoader.getUniqueEntityId(), smelttoolmaterials.ANDGOLD))
			.setItemName("agPickaxe").setIconIndex(5);
	public static final Item agSpade = (new agSpade(
			ModLoader.getUniqueEntityId(), smelttoolmaterials.ANDGOLD))
			.setItemName("agSpade").setIconIndex(6);
	public static final Item agAxe = (new agAxe(ModLoader.getUniqueEntityId(),
			smelttoolmaterials.ANDGOLD)).setItemName("agAxe").setIconIndex(4);
	public static final Item agSword = (new agSword(
			ModLoader.getUniqueEntityId(), smelttoolmaterials.ANDGOLD))
			.setItemName("agSword").setIconIndex(7);

	public static final Item aiPick = (new aiPick(
			ModLoader.getUniqueEntityId(), smelttoolmaterials.IRONAND))
			.setItemName("aiPickaxe").setIconIndex(9);
	public static final Item aiSpade = (new aiSpade(
			ModLoader.getUniqueEntityId(), smelttoolmaterials.IRONAND))
			.setItemName("aiSpade").setIconIndex(10);
	public static final Item aiAxe = (new aiAxe(ModLoader.getUniqueEntityId(),
			smelttoolmaterials.IRONAND)).setItemName("aiAxe").setIconIndex(8);
	public static final Item aiSword = (new aiSword(
			ModLoader.getUniqueEntityId(), smelttoolmaterials.IRONAND))
			.setItemName("aiSword").setIconIndex(11);

	public static final Item giPick = (new giPick(
			ModLoader.getUniqueEntityId(), smelttoolmaterials.GOLDIRON))
			.setItemName("giPickaxe").setIconIndex(13);
	public static final Item giSpade = (new giSpade(
			ModLoader.getUniqueEntityId(), smelttoolmaterials.GOLDIRON))
			.setItemName("giSpade").setIconIndex(14);
	public static final Item giAxe = (new giAxe(ModLoader.getUniqueEntityId(),
			smelttoolmaterials.GOLDIRON)).setItemName("giAxe").setIconIndex(12);
	public static final Item giSword = (new giSword(
			ModLoader.getUniqueEntityId(), smelttoolmaterials.GOLDIRON))
			.setItemName("giSword").setIconIndex(15);

	public static final Item steelPick = (new steelPick(
			ModLoader.getUniqueEntityId(), smelttoolmaterials.STEEL))
			.setItemName("SteelPickaxe").setIconIndex(17);
	public static final Item steelSpade = (new steelSpade(
			ModLoader.getUniqueEntityId(), smelttoolmaterials.STEEL))
			.setItemName("SteelSpade").setIconIndex(18);
	public static final Item steelAxe = (new steelAxe(
			ModLoader.getUniqueEntityId(), smelttoolmaterials.STEEL))
			.setItemName("SteelAxe").setIconIndex(16);
	public static final Item steelSword = (new steelSword(
			ModLoader.getUniqueEntityId(), smelttoolmaterials.STEEL))
			.setItemName("SteelSword").setIconIndex(19);

	public void mod_SmeltCraft() {

	}

	static // Re Defines mod_SmeltCraft to have more Methods.

	{

	}

	public void GenerateSurface(World world, Random rand, int chunkX, int chunkZ) {
		for (int i = 0; i < 10; i++)

		{
			int randPosX = chunkX + rand.nextInt(2);
			int randPosY = rand.nextInt(5);
			int randPosZ = chunkZ + rand.nextInt(2);
			(new WorldGenMinable(oreAnditite.blockID, 4)).generate(world, rand,
					randPosX, randPosY, randPosZ);
		}
	}

	// The instance of your mod that Forge uses.
	@Instance("Smeltcraft2")
	public static SmeltCraft instance;

	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide = "SmeltCraft.Client.ClientProxy", serverSide = "SmeltCraft.CommonProxy")
	public static CommonProxy proxy;

	@PreInit
	public void preInit(FMLPreInitializationEvent event) {

		// Stub Method
	}

	@Init
	public void load(FMLInitializationEvent event) {
		proxy.registerRenderers();

		// Names!! For Blocks or Item ID's! Touch this Patrick, and ill fucking
		// KILL YOU!! (Just Kidding)
		// LOL wut?

		// Gui Registers all help was done my SirSengir! Much Respect, Man!
		/*
		 * ModLoaderMp.RegisterGUI(this, 125); ModLoaderMp.RegisterGUI(this,
		 * 126); ModLoaderMp.RegisterGUI(this, 127);
		 */

		NetworkRegistry.instance().registerGuiHandler(this, coolGui);
		// Blocks

		ModLoader.addName(bfurBlock, "Blast Furnace");
		ModLoader.addName(oreAnditite, "Anditite Ore");
		ModLoader.addName(coolBlock, "Cooler");

		// Items

		// ModLoader.addName(iceIngot, "Ice Ingot Level 1");
		ModLoader.addName(andIngot, "Anditite Ingot");
		ModLoader.addName(moltAnd, "Molten Anditite");
		ModLoader.addName(moltGold, "Molten Gold");
		ModLoader.addName(moltIron, "Molten Iron");
		ModLoader.addName(moltStone, "Molten Stone");
		ModLoader.addName(mmgoldiron, "Mixed Molten Gold And Iron");
		ModLoader.addName(mmgoldAnditite, "Mixed Molten Gold And Anditite");
		ModLoader.addName(mmironAnditite, "Mixed Iron And Anditite");
		ModLoader.addName(ironAnditite, "Mixed Iron And Anditite Ingot");
		ModLoader.addName(ironGold, "Mixed Iron And Gold Ingot");
		ModLoader.addName(goldAnditite, "Mixed Gold And Anditite Ingot");
		ModLoader.addName(dDust, "Diamond Dust");
		ModLoader.addName(ingotSteel, "Steel Ingot");
		ModLoader.addName(moltSteel, "Molten Steel");
		ModLoader.addName(bCoal, "Blast Coal");
		ModLoader.addName(hmIron, "Heavy Molten Iron");
		ModLoader.addName(rhIron, "Really Heavy Molten Iron");
		ModLoader.addName(mmixer, "Mixer Mold");
		ModLoader.addName(mixer, "Mixer Spinner");

		// Tools

		ModLoader.addName(andPick, "Anditite Pickaxe");
		ModLoader.addName(andSpade, "Anditite Shovel");
		ModLoader.addName(andAxe, "Anditite Axe");
		ModLoader.addName(andSword, "Anditite Sword");

		ModLoader.addName(agPick, "Mixed Pickaxe (Gold + Anditite)");
		ModLoader.addName(agSpade, "Mixed Shovel (Gold + Anditite)");
		ModLoader.addName(agAxe, "Mixed Axe (Gold + Anditite)");
		ModLoader.addName(agSword, "Mixed Sword (Gold + Anditite)");

		ModLoader.addName(aiPick, "Mixed Pickaxe (Iron + Anditite)");
		ModLoader.addName(aiSpade, "Mixed Shovel (Iron + Anditite)");
		ModLoader.addName(aiAxe, "Mixed Axe (Iron + Anditite)");
		ModLoader.addName(aiSword, "Mixed Sword (Iron + Anditite)");

		ModLoader.addName(giPick, "Mixed Pickaxe (Gold + Iron)");
		ModLoader.addName(giSpade, "Mixed Shovel (Gold + Iron)");
		ModLoader.addName(giAxe, "Mixed Axe (Gold + Iron)");
		ModLoader.addName(giSword, "Mixed Sword (Gold + Iron)");

		ModLoader.addName(steelPick, "Steel Pick");
		ModLoader.addName(steelSpade, "Steel Shovel");
		ModLoader.addName(steelAxe, "Steel Axe");
		ModLoader.addName(steelSword, "Steel Sword)");

		// Registers! Lets Minecraft Know It Really Exists!

		ModLoader.registerBlock(bfurBlock);
		ModLoader.registerBlock(bfurBlockOn);
		ModLoader.registerBlock(oreAnditite);
		ModLoader.registerBlock(coolBlock);
		ModLoader.registerBlock(coolBlockOn);
		ModLoader.registerBlock(mixBlock);
		ModLoader.registerBlock(mixBlockOn);
		ModLoader.addSmelting(oreAnditite.blockID, new ItemStack(andIngot));

		// Other (Such as TileEntity's!)

		ModLoader.registerTileEntity(TileEntityBlast.class, "Blast");
		ModLoader.registerTileEntity(TileEntitycool.class, "Cool");
		ModLoader.registerTileEntity(TileEntitymix.class, "Mix");
		// ModLoader.RegisterTileEntity(net.minecraft.SmeltCraft.Machines.TileEntityessence.class,
		// "Essence");

		// Overrides (That means this is NOT The Forge Version, Bakas!)
		// block

		// oreAnditite.blockIndexInTexture =
		// ModLoader.addOverride("/terrain.png",
		// "/SmeltCraft/AndySwi/Anditite.png");

		// bfurBlock.blockIndexInTexture = ModLoader.addOverride("/terrain.png",
		// "/SmeltCraft/MTSS/Blast Furnace/Off.png");
		// coolBlock.blockIndexInTexture = ModLoader.addOverride("/terrain.png",
		// "/SmeltCraft/MTSS/Cooler/Off.png");

		// item

		/*
		 * //iceIngot.iconIndex = ModLoader.addOverride("/gui/items.png",
		 * "/SmeltCraft/MTSS/IceIngot.png"); andIngot.iconIndex =
		 * ModLoader.addOverride("/gui/items.png",
		 * "/SmeltCraft/AndySwi/AndititeIngot.png"); moltAnd.iconIndex =
		 * ModLoader.addOverride("/gui/items.png",
		 * "/SmeltCraft/AndySwi/AndititeMolten.png"); moltGold.iconIndex =
		 * ModLoader.addOverride("/gui/items.png",
		 * "/SmeltCraft/AndySwi/GoldMolten.png"); moltIron.iconIndex =
		 * ModLoader.addOverride("/gui/items.png",
		 * "/SmeltCraft/AndySwi/IronMolten.png"); moltStone.iconIndex =
		 * ModLoader.addOverride("/gui/items.png",
		 * "/SmeltCraft/AndySwi/Magma.png"); mmgoldiron.iconIndex =
		 * ModLoader.addOverride("/gui/items.png",
		 * "/SmeltCraft/AndySwi/MixedMoltenB.png"); mmgoldAnditite.iconIndex =
		 * ModLoader.addOverride("/gui/items.png",
		 * "/SmeltCraft/AndySwi/MixedMoltenA.png"); mmironAnditite.iconIndex =
		 * ModLoader.addOverride("/gui/items.png",
		 * "/SmeltCraft/AndySwi/MixedMolten.png"); ironAnditite.iconIndex =
		 * ModLoader.addOverride("/gui/items.png",
		 * "/SmeltCraft/AndySwi/MixedIngot.png"); ironGold.iconIndex =
		 * ModLoader.addOverride("/gui/items.png",
		 * "/SmeltCraft/AndySwi/MixedIngotB.png"); goldAnditite.iconIndex =
		 * ModLoader.addOverride("/gui/items.png",
		 * "/SmeltCraft/AndySwi/MixedIngotA.png"); dDust.iconIndex =
		 * ModLoader.addOverride("/gui/items.png",
		 * "/SmeltCraft/AndySwi/DiamondDust.png"); moltSteel.iconIndex =
		 * ModLoader.addOverride("/gui/items.png",
		 * "/SmeltCraft/AndySwi/MoltenSteel.png"); ingotSteel.iconIndex =
		 * ModLoader.addOverride("/gui/items.png",
		 * "/SmeltCraft/AndySwi/SteelIngot.png"); bCoal.iconIndex =
		 * ModLoader.addOverride("/gui/items.png",
		 * "/SmeltCraft/AndySwi/BlastCoal.png"); hmIron.iconIndex =
		 * ModLoader.addOverride("/gui/items.png",
		 * "/SmeltCraft/AndySwi/HeavyMoltenIron.png"); rhIron.iconIndex =
		 * ModLoader.addOverride("/gui/items.png",
		 * "/SmeltCraft/AndySwi/ReallyHeavyMoltenIron.png");
		 * steelBlock.iconIndex = ModLoader.addOverride("/gui/items.png",
		 * "/SmeltCraft/AndySwi/SteelBlock.png"); // mmixer.iconIndex =
		 * ModLoader.addOverride("/gui/items.png",
		 * "/SmeltCraft/AndySwi/MixMolt.png"); // mixer.iconIndex =
		 * ModLoader.addOverride("/gui/items.png",
		 * "/SmeltCraft/AndySwi/MixerSpinner.png"); //.iconIndex =
		 * ModLoader.addOverride("/gui/items.png",
		 * "/SmeltCraft/MTSS/Essence/off.png");
		 */

		/*
		 * //GUI blastSides = ModLoader.addOverride("/terrain.png",
		 * "/SmeltCraft/MTSS/Blast Furnace/Side.png"); blastOff =
		 * ModLoader.addOverride("/terrain.png",
		 * "/SmeltCraft/MTSS/Blast Furnace/Off.png"); blastOn =
		 * ModLoader.addOverride("/terrain.png",
		 * "/SmeltCraft/MTSS/Blast Furnace/On.png"); blastTop =
		 * ModLoader.addOverride("/terrain.png",
		 * "/SmeltCraft/MTSS/Blast Furnace/Top.png");
		 * 
		 * coolOn = ModLoader.addOverride("/terrain.png",
		 * "/SmeltCraft/MTSS/Cooler/On.png"); coolOff =
		 * ModLoader.addOverride("/terrain.png",
		 * "/SmeltCraft/MTSS/Cooler/Off.png"); coolSides =
		 * ModLoader.addOverride("/terrain.png",
		 * "/SmeltCraft/MTSS/Cooler/Side.png"); coolTop =
		 * ModLoader.addOverride("/terrain.png",
		 * "/SmeltCraft/MTSS/Cooler/Top.png");
		 * 
		 * mixOn = ModLoader.addOverride("/terrain.png",
		 * "/SmeltCraft/MTSS/Mixer/On.png"); mixOff =
		 * ModLoader.addOverride("/terrain.png",
		 * "/SmeltCraft/MTSS/Mixer/Off.png"); mixSides =
		 * ModLoader.addOverride("/terrain.png",
		 * "/SmeltCraft/MTSS/Mixer/Side.png"); mixTop =
		 * ModLoader.addOverride("/terrain.png",
		 * "/SmeltCraft/MTSS/Mixer/Top.png");
		 */

		// Config File Section Soon-to-be!
		coolID = (126);
		blastID = (125);

		// Recipes
		ModLoader.addRecipe(
				new ItemStack(bfurBlock, 1),
				new Object[] { "XXX", "SFS", "EEE", Character.valueOf('X'),
						Item.coal, Character.valueOf('S'), Item.ingotIron,
						Character.valueOf('F'), Block.stoneOvenIdle,
						Character.valueOf('E'), Item.gunpowder });

		ModLoader.addRecipe(new ItemStack(coolBlock, 1), new Object[] { "XXX",
				"XCX", "XXX", Character.valueOf('C'), Item.bucketWater,
				Character.valueOf('X'), Item.ingotIron });

		ModLoader.addRecipe(new ItemStack(mixBlock, 1),
				new Object[] { "III", "IMI", "CRC", Character.valueOf('I'),
						Item.ingotIron, Character.valueOf('C'), Item.clay,
						Character.valueOf('M'), mixer, Character.valueOf('R'),
						Item.redstone, });

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

	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
		// Stub Method
	}

	private CustomCoolGuiHandler coolGui = new CustomCoolGuiHandler();

}
