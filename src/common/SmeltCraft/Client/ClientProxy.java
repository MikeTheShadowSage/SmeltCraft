package SmeltCraft.Client;

import net.minecraftforge.client.MinecraftForgeClient;
import SmeltCraft.CommonProxy;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void registerRenderers() {
		MinecraftForgeClient.preloadTexture(ITEMS_PNG);
		MinecraftForgeClient.preloadTexture(BLOCK_PNG);
		
		
	}
	
}