package SmeltCraft;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import SmeltCraft.Library.GuiIds;

public class CommonProxy implements IGuiHandler{
	public static String ITEMS_PNG = "/SmeltCraft/MTSS/items.png";
	public static String BLOCK_PNG = "/SmeltCraft/MTSS/block.png";

	// Client stuff
	public void registerRenderers() {
		// Nothing here as this is the server side proxy
	}


	
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    //   //if (ID == GuiIds.PORTABLE_CRAFTING) {
     //       return new ContainerPortableCrafting(player.inventory, world, x, y, z);
     //   } //else 
    	if (ID == GuiIds.COOLER) {
    		FMLLog.info("GUI Number" + ID + "Server Called");
            TileEntitycool cool = (TileEntitycool)world.getBlockTileEntity(x, y, z);
            return new Containercool(player.inventory, cool);
        }
        
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    	
    //    if (ID == GuiIds.PORTABLE_CRAFTING) {
    //        return new GuiPortableCrafting(player, world, x, y, z);
    //    } else 
    	
    	if (ID == GuiIds.COOLER) {
    		FMLLog.info("GUI Number" + GuiIds.COOLER + "Client Called");
            TileEntitycool cool = (TileEntitycool)world.getBlockTileEntity(x, y, z);
            return new Guicool(player.inventory, cool);
        }
        
        return null;
    } 
}