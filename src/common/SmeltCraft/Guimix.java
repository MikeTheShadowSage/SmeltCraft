package SmeltCraft;

import net.minecraft.src.*;

import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

public class Guimix extends GuiContainer {

	public Guimix(InventoryPlayer inventoryplayer, TileEntitymix tileentitymix) {
		super(new Containermix(inventoryplayer, tileentitymix));
		mixInventory = tileentitymix;
	}

	protected void drawGuiContainerForegroundLayer() {
		fontRenderer.drawString("     Mixer", 60, 6, 0x404040);
		fontRenderer.drawString("Smelter's Inventory", 8, (ySize - 96) + 2,
				0x404040);
	}

	protected void drawGuiContainerBackgroundLayer(float f, int z, int y) {
		int i = mc.renderEngine.getTexture("/SmeltCraft/MTSS/gui/Mixer.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(i);
		int j = (width - xSize) / 2;
		int k = (height - ySize) / 2;
		drawTexturedModalRect(j, k, 0, 0, xSize, ySize);
		if (mixInventory.isBurning()) {
			int l = mixInventory.getBurnTimeRemainingScaled(12);
			drawTexturedModalRect(j + 56, (k + 36 + 12) - l, 176, 12 - l, 14,
					l + 2);
		}
		int i1 = mixInventory.getCookProgressScaled(24);
		drawTexturedModalRect(j + 79, k + 34, 176, 14, i1 + 1, 16);
	}

	private TileEntitymix mixInventory;
}