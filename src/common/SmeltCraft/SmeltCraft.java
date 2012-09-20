package SmeltCraft;

import java.util.Random;
import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.ModLoader;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenMinable;
import cpw.mods.fml.common.FMLCommonHandler;
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
import SmeltCraft.Load.blocks;
import SmeltCraft.Load.items;
import SmeltCraft.Guicool;



@Mod(modid = "Smeltcraft2", name = "Smeltcraft 2", version = "0.0.1")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class SmeltCraft {


	// Blocks, Tools, And item Class Declarations!

	public static int coolID;
	public static int blastID;



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

	// Items

	//TODO add Ice Ingots

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
			(new WorldGenMinable(blocks.oreAnditite.blockID, 4)).generate(world, rand,
					randPosX, randPosY, randPosZ);
		}
	}

	// The instance of your mod that Forge uses.
	@Instance//("Smeltcraft2")
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
		//FMLCommonHandler.instance().showGuiScreen(new Guicool);
       // NetworkRegistry.instance().registerGuiHandler(instance, proxy);
        
        NetworkRegistry.instance().registerGuiHandler(this.instance, proxy);
		// Blocks
        blocks.init();

		// Items
        items.init();

		//ModLoader.addSmelting(oreAnditite.blockID, new ItemStack(andIngot));

		// Other (Such as TileEntity's!)

		ModLoader.registerTileEntity(TileEntityBlast.class, "Blast");
		ModLoader.registerTileEntity(TileEntitycool.class, "tileCool");
		ModLoader.registerTileEntity(TileEntitymix.class, "Mix");


		// Config File Section Soon-to-be!
		coolID = (126);
		blastID = (125);

		// Recipes

	}

	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
		// Stub Method
	}


}
