package SmeltCraft;

import net.minecraft.src.*;

public class steelSword extends Item {

	public steelSword(int i, smelttoolmaterials smelttoolmaterials) {
		super(i);
		maxStackSize = 1;
		setMaxDamage(smelttoolmaterials.getMaxUses());
		weaponDamage = 4 + smelttoolmaterials.getDamageVsEntity() * 2;
	}

	public String getTextureFile() {
		return CommonProxy.ITEMS_PNG;
	}

	public float getStrVsBlock(ItemStack itemstack, Block block) {
		return block.blockID != Block.web.blockID ? 1.5F : 15F;
	}

	public boolean hitEntity(ItemStack itemstack, EntityLiving entityliving,
			EntityLiving entityliving1) {
		itemstack.damageItem(1, entityliving1);
		return true;
	}

	public boolean onBlockDestroyed(ItemStack itemstack, int i, int j, int k,
			int l, EntityLiving entityliving) {
		itemstack.damageItem(2, entityliving);
		return true;
	}

	public int getDamageVsEntity(Entity entity) {
		return weaponDamage;
	}

	public boolean isFull3D() {
		return true;
	}

	public boolean canHarvestBlock(Block block) {
		return block.blockID == Block.web.blockID;
	}

	private int weaponDamage;
}