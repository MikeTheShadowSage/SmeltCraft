package SmeltCraft;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

public class CommonProxy {
	public static String ITEMS_PNG = "/SmeltCraft/MTSS/items.png";
	public static String BLOCK_PNG = "/SmeltCraft/MTSS/block.png";

	// Client stuff
	public void registerRenderers() {
		// Nothing here as this is the server side proxy
	}

}