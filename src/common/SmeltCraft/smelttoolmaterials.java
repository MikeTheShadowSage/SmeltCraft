package SmeltCraft;

import net.minecraft.src.*;

public enum smelttoolmaterials {
	AND("AND", 5, 4, 40, 10F, 4), IRONAND("IRONAND", 6, 4, 200, 10F, 4), ANDGOLD(
			"ANDGOLD", 7, 4, 40, 16F, 6), GOLDIRON("GOLDIRON", 8, 4, 140, 12F,
			2), STEEL("STEEL", 9, 2, 1561, 6F, 2), COPPER("COPPER", 10, 1, 250,
			4F, 1), TIN("TIN", 11, 2, 131, 6F, 2), HANDSAW("HANDSAW", 12, 1, 0,
			2.5F, 0), BRONZE("BRONZE", 13, 2, 350, 5F, 2);
	// ADD YOUR TOOL TYPE HERE (ex: LAPIS can go here));
	/*
	 * public static EnumToolMaterial[] values() { return
	 * (EnumToolMaterial[])field_21209_j.clone(); }
	 * 
	 * public static EnumToolMaterial valueOf(String s) { return
	 * (EnumToolMaterial)Enum.valueOf(net.minecraft.src.EnumToolMaterial.class,
	 * s); }
	 */
	private smelttoolmaterials(String s, int i, int j, int k, float f, int l) {
		// super(s, i);
		harvestLevel = j;
		maxUses = k;
		efficiencyOnProperMaterial = f;
		damageVsEntity = l;
	}

	public int getMaxUses() {
		return maxUses;
	}

	public float getEfficiencyOnProperMaterial() {
		return efficiencyOnProperMaterial;
	}

	public int getDamageVsEntity() {
		return damageVsEntity;
	}

	public int getHarvestLevel() {
		return harvestLevel;
	}

	/*
	 * public static final EnumToolMaterial WOOD; public static final
	 * EnumToolMaterial STONE; public static final EnumToolMaterial IRON; public
	 * static final EnumToolMaterial EMERALD; public static final
	 * EnumToolMaterial GOLD;
	 */
	private final int harvestLevel;
	private final int maxUses;
	private final float efficiencyOnProperMaterial;
	private final int damageVsEntity;

	static {
		/*
		 * WOOD = new EnumToolMaterial("WOOD", 0, 0, 59, 2.0F, 0); STONE = new
		 * EnumToolMaterial("STONE", 1, 1, 131, 4F, 1); IRON = new
		 * EnumToolMaterial("IRON", 2, 2, 250, 6F, 2); EMERALD = new
		 * EnumToolMaterial("EMERALD", 3, 3, 1561, 8F, 3); GOLD = new
		 * EnumToolMaterial("GOLD", 4, 0, 32, 12F, 0);
		 */
	}
}