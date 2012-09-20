package SmeltCraft;

import net.minecraft.src.*;

import java.util.HashMap;
import java.util.Map;

import SmeltCraft.Load.items;

// Referenced classes of package net.minecraft.src:
//            Block, ItemStack, Item


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class coolRecipes
{
    private static final coolRecipes smeltingBase = new coolRecipes();

    /** The list of smelting results. */
    private Map smeltingList = new HashMap();
    private Map experienceList = new HashMap();
    private Map metaSmeltingList = new HashMap();

    /**
     * Used to call methods addSmelting and getSmeltingResult.
     */
    public static final coolRecipes smelting()
    {
        return smeltingBase;
    }

    private coolRecipes()
    {
        this.addSmelting(Block.oreIron.blockID, new ItemStack(Item.ingotIron), 0.7F);
        this.addSmelting(Block.oreGold.blockID, new ItemStack(Item.ingotGold), 1.0F);
        this.addSmelting(Block.oreDiamond.blockID, new ItemStack(Item.diamond), 1.0F);
        this.addSmelting(Block.sand.blockID, new ItemStack(Block.glass), 0.1F);
        this.addSmelting(Item.porkRaw.shiftedIndex, new ItemStack(Item.porkCooked), 0.3F);
        this.addSmelting(Item.beefRaw.shiftedIndex, new ItemStack(Item.beefCooked), 0.3F);
        this.addSmelting(Item.chickenRaw.shiftedIndex, new ItemStack(Item.chickenCooked), 0.3F);
        this.addSmelting(Item.fishRaw.shiftedIndex, new ItemStack(Item.fishCooked), 0.3F);
        this.addSmelting(Block.cobblestone.blockID, new ItemStack(Block.stone), 0.1F);
        this.addSmelting(Item.clay.shiftedIndex, new ItemStack(Item.brick), 0.2F);
        this.addSmelting(Block.cactus.blockID, new ItemStack(Item.dyePowder, 1, 2), 0.2F);
        this.addSmelting(Block.wood.blockID, new ItemStack(Item.coal, 1, 1), 0.1F);
        this.addSmelting(Block.oreEmerald.blockID, new ItemStack(Item.emerald), 1.0F);
        this.addSmelting(Block.oreCoal.blockID, new ItemStack(Item.coal), 0.1F);
        this.addSmelting(Block.oreRedstone.blockID, new ItemStack(Item.redstone), 0.7F);
        this.addSmelting(Block.oreLapis.blockID, new ItemStack(Item.dyePowder, 1, 4), 0.2F);
    }

    /**
     * Adds a smelting recipe.
     */
    public void addSmelting(int par1, ItemStack par2ItemStack, float par3)
    {
        this.smeltingList.put(Integer.valueOf(par1), par2ItemStack);
        this.experienceList.put(Integer.valueOf(par2ItemStack.itemID), Float.valueOf(par3));
    }

    /**
     * Returns the smelting result of an item.
     * Deprecated in favor of a metadata sensitive version
     */
    @Deprecated
    public ItemStack getSmeltingResult(int par1)
    {
        return (ItemStack)this.smeltingList.get(Integer.valueOf(par1));
    }

    public Map getSmeltingList()
    {
        return this.smeltingList;
    }

    public float func_77601_c(int par1)
    {
        return this.experienceList.containsKey(Integer.valueOf(par1)) ? ((Float)this.experienceList.get(Integer.valueOf(par1))).floatValue() : 0.0F;
    }

    /**
     * Add a metadata-sensitive furnace recipe
     * @param itemID The Item ID
     * @param metadata The Item Metadata
     * @param itemstack The ItemStack for the result
     */
    public void addSmelting(int itemID, int metadata, ItemStack itemstack)
    {
        metaSmeltingList.put(Arrays.asList(itemID, metadata), itemstack);
    }
    
    /**
     * Used to get the resulting ItemStack form a source ItemStack
     * @param item The Source ItemStack
     * @return The result ItemStack
     */
    public ItemStack getSmeltingResult(ItemStack item) 
    {
        if (item == null)
        {
            return null;
        }
        ItemStack ret = (ItemStack)metaSmeltingList.get(Arrays.asList(item.itemID, item.getItemDamage()));
        if (ret != null) 
        {
            return ret;
        }
        return (ItemStack)smeltingList.get(Integer.valueOf(item.itemID));
    }
}
/*
public class coolRecipes {

	public static final coolRecipes smelting() {
		return smeltingBase;
	}

	private coolRecipes() {
		smeltingList = new HashMap();
		addFrost(Item.bucketWater.shiftedIndex, new ItemStack(Block.ice));
		addFrost(items.mmgoldAnditite.shiftedIndex, new ItemStack(
				items.goldAnditite));
		addFrost(items.mmgoldiron.shiftedIndex, new ItemStack(
				items.ironGold));
		addFrost(items.mmironAnditite.shiftedIndex, new ItemStack(
				items.ironAnditite));
		addFrost(items.moltAnd.shiftedIndex, new ItemStack(
				items.andIngot));
		addFrost(items.moltGold.shiftedIndex,
				new ItemStack(Item.ingotGold));
		addFrost(items.moltIron.shiftedIndex,
				new ItemStack(Item.ingotIron));
		addFrost(items.moltSteel.shiftedIndex, new ItemStack(
				items.ingotSteel));
		addFrost(items.moltStone.shiftedIndex, new ItemStack(Block.stone));
		addFrost(items.hmIron.shiftedIndex, new ItemStack(Item.ingotIron,
				2));
		addFrost(items.rhIron.shiftedIndex, new ItemStack(Item.ingotIron,
				4));
		addFrost(items.mmixer.shiftedIndex,
				new ItemStack(items.mixer));

	}

    public void addFrost(int par1, ItemStack par2ItemStack)
    {
        this.smeltingList.put(Integer.valueOf(par1), par2ItemStack);
        //this.experienceList.put(Integer.valueOf(par2ItemStack.itemID), Float.valueOf(par3));
    }


    public ItemStack getSmeltingResult(int par1)
    {
        return (ItemStack)this.smeltingList.get(Integer.valueOf(par1));
    }
    
    

	public Map getSmeltingList() {
		return smeltingList;
	}

	private static final coolRecipes smeltingBase = new coolRecipes();
	private Map smeltingList;

}
*/