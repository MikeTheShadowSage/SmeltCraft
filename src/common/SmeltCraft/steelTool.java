package SmeltCraft;

import net.minecraft.src.*;

public class steelTool extends Item {

	protected steelTool(int i, int j, smelttoolmaterials smelttoolmaterials,
			Block ablock[]) {
		super(i);
		efficiencyOnProperMaterial = 4F;
		toolMaterial = smelttoolmaterials;
		blocksEffectiveAgainst = ablock;
		maxStackSize = 1;
		setMaxDamage(smelttoolmaterials.getMaxUses());
		efficiencyOnProperMaterial = smelttoolmaterials
				.getEfficiencyOnProperMaterial();
		damageVsEntity = j + smelttoolmaterials.getDamageVsEntity();
	}

	public float getStrVsBlock(ItemStack itemstack, Block block) {
		for (int i = 0; i < blocksEffectiveAgainst.length; i++) {
			if (blocksEffectiveAgainst[i] == block) {
				return efficiencyOnProperMaterial;
			}
		}

		return 1.0F;
	}

	public boolean hitEntity(ItemStack itemstack, EntityLiving entityliving,
			EntityLiving entityliving1) {
		itemstack.damageItem(2, entityliving1);
		return true;
	}

	public boolean onBlockDestroyed(ItemStack itemstack, int i, int j, int k,
			int l, EntityLiving entityliving) {
		itemstack.damageItem(1, entityliving);
		return true;
	}

	public int getDamageVsEntity(Entity entity) {
		return damageVsEntity;
	}

	public boolean isFull3D() {
		return true;
	}

	private Block blocksEffectiveAgainst[];
	private float efficiencyOnProperMaterial;
	private int damageVsEntity;
	protected smelttoolmaterials toolMaterial;

}