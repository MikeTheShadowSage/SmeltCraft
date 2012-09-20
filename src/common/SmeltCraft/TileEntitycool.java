package SmeltCraft;

import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.Item;
import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemHoe;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ItemSword;
import net.minecraft.src.ItemTool;
import net.minecraft.src.Material;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NBTTagList;
import net.minecraft.src.TileEntity;
import net.minecraftforge.common.ISidedInventory;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.common.registry.GameRegistry;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

public class TileEntitycool extends TileEntity implements IInventory, ISidedInventory
{
    /**
     * The ItemStacks that hold the items currently being used in the cool
     */
    private ItemStack[] coolItemStacks = new ItemStack[3];

    /** The number of ticks that the cool will keep burning */
    public int coolBurnTime = 0;

    /**
     * The number of ticks that a fresh copy of the currently-burning item would keep the cool burning for
     */
    public int currentItemBurnTime = 0;

    /** The number of ticks that the current item has been cooking for */
    public int coolCookTime = 0;

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return this.coolItemStacks.length;
    }

    /**
     * Returns the stack in slot i
     */
    public ItemStack getStackInSlot(int par1)
    {
        return this.coolItemStacks[par1];
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
    public ItemStack decrStackSize(int par1, int par2)
    {
        if (this.coolItemStacks[par1] != null)
        {
            ItemStack var3;

            if (this.coolItemStacks[par1].stackSize <= par2)
            {
                var3 = this.coolItemStacks[par1];
                this.coolItemStacks[par1] = null;
                return var3;
            }
            else
            {
                var3 = this.coolItemStacks[par1].splitStack(par2);

                if (this.coolItemStacks[par1].stackSize == 0)
                {
                    this.coolItemStacks[par1] = null;
                }

                return var3;
            }
        }
        else
        {
            return null;
        }
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    public ItemStack getStackInSlotOnClosing(int par1)
    {
        if (this.coolItemStacks[par1] != null)
        {
            ItemStack var2 = this.coolItemStacks[par1];
            this.coolItemStacks[par1] = null;
            return var2;
        }
        else
        {
            return null;
        }
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
    {
        this.coolItemStacks[par1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
        {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    /**
     * Returns the name of the inventory.
     */
    public String getInvName()
    {
        return "container.cool";
    }

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList var2 = par1NBTTagCompound.getTagList("Items");
        this.coolItemStacks = new ItemStack[this.getSizeInventory()];

        for (int var3 = 0; var3 < var2.tagCount(); ++var3)
        {
            NBTTagCompound var4 = (NBTTagCompound)var2.tagAt(var3);
            byte var5 = var4.getByte("Slot");

            if (var5 >= 0 && var5 < this.coolItemStacks.length)
            {
                this.coolItemStacks[var5] = ItemStack.loadItemStackFromNBT(var4);
            }
        }

        this.coolBurnTime = par1NBTTagCompound.getShort("BurnTime");
        this.coolCookTime = par1NBTTagCompound.getShort("CookTime");
        this.currentItemBurnTime = getItemBurnTime(this.coolItemStacks[1]);
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setShort("BurnTime", (short)this.coolBurnTime);
        par1NBTTagCompound.setShort("CookTime", (short)this.coolCookTime);
        NBTTagList var2 = new NBTTagList();

        for (int var3 = 0; var3 < this.coolItemStacks.length; ++var3)
        {
            if (this.coolItemStacks[var3] != null)
            {
                NBTTagCompound var4 = new NBTTagCompound();
                var4.setByte("Slot", (byte)var3);
                this.coolItemStacks[var3].writeToNBT(var4);
                var2.appendTag(var4);
            }
        }

        par1NBTTagCompound.setTag("Items", var2);
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended. *Isn't
     * this more of a set than a get?*
     */
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns an integer between 0 and the passed value representing how close the current item is to being completely
     * cooked
     */
    public int getCookProgressScaled(int par1)
    {
        return this.coolCookTime * par1 / 200;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns an integer between 0 and the passed value representing how much burn time is left on the current fuel
     * item, where 0 means that the item is exhausted and the passed value means that the item is fresh
     */
    public int getBurnTimeRemainingScaled(int par1)
    {
        if (this.currentItemBurnTime == 0)
        {
            this.currentItemBurnTime = 200;
        }

        return this.coolBurnTime * par1 / this.currentItemBurnTime;
    }

    /**
     * Returns true if the cool is currently burning
     */
    public boolean isBurning()
    {
        return this.coolBurnTime > 0;
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
        boolean var1 = this.coolBurnTime > 0;
        boolean var2 = false;

        if (this.coolBurnTime > 0)
        {
            --this.coolBurnTime;
        }

        if (!this.worldObj.isRemote)
        {
            if (this.coolBurnTime == 0 && this.canSmelt())
            {
                this.currentItemBurnTime = this.coolBurnTime = getItemBurnTime(this.coolItemStacks[1]);

                if (this.coolBurnTime > 0)
                {
                    var2 = true;

                    if (this.coolItemStacks[1] != null)
                    {
                        --this.coolItemStacks[1].stackSize;

                        if (this.coolItemStacks[1].stackSize == 0)
                        {
                            this.coolItemStacks[1] = this.coolItemStacks[1].getItem().getContainerItemStack(coolItemStacks[1]);
                        }
                    }
                }
            }

            if (this.isBurning() && this.canSmelt())
            {
                ++this.coolCookTime;

                if (this.coolCookTime == 200)
                {
                    this.coolCookTime = 0;
                    this.smeltItem();
                    var2 = true;
                }
            }
            else
            {
                this.coolCookTime = 0;
            }

            if (var1 != this.coolBurnTime > 0)
            {
                var2 = true;
                Blockcool.updatecoolBlockState(this.coolBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }

        if (var2)
        {
            this.onInventoryChanged();
        }
    }

    /**
     * Returns true if the cool can smelt an item, i.e. has a source item, destination stack isn't full, etc.
     */
    private boolean canSmelt()
    {
        if (this.coolItemStacks[0] == null)
        {
            return false;
        }
        else
        {
            ItemStack var1 = coolRecipes.smelting().getSmeltingResult(this.coolItemStacks[0]);
            if (var1 == null) return false;
            if (this.coolItemStacks[2] == null) return true;
            if (!this.coolItemStacks[2].isItemEqual(var1)) return false;
            int result = coolItemStacks[2].stackSize + var1.stackSize;
            return (result <= getInventoryStackLimit() && result <= var1.getMaxStackSize());
        }
    }

    /**
     * Turn one item from the cool source stack into the appropriate smelted item in the cool result stack
     */
    public void smeltItem()
    {
        if (this.canSmelt())
        {
            ItemStack var1 = coolRecipes.smelting().getSmeltingResult(this.coolItemStacks[0]);

            if (this.coolItemStacks[2] == null)
            {
                this.coolItemStacks[2] = var1.copy();
            }
            else if (this.coolItemStacks[2].isItemEqual(var1))
            {
                coolItemStacks[2].stackSize += var1.stackSize;
            }

            --this.coolItemStacks[0].stackSize;

            if (this.coolItemStacks[0].stackSize <= 0)
            {
                this.coolItemStacks[0] = null;
            }
        }
    }

    /**
     * Returns the number of ticks that the supplied fuel item will keep the cool burning, or 0 if the item isn't
     * fuel
     */
    public static int getItemBurnTime(ItemStack par0ItemStack)
    {
        if (par0ItemStack == null)
        {
            return 0;
        }
        else
        {
            int var1 = par0ItemStack.getItem().shiftedIndex;
            Item var2 = par0ItemStack.getItem();

            if (par0ItemStack.getItem() instanceof ItemBlock && Block.blocksList[var1] != null)
            {
                Block var3 = Block.blocksList[var1];

                if (var3 == Block.woodSingleSlab)
                {
                    return 150;
                }

                if (var3.blockMaterial == Material.wood)
                {
                    return 300;
                }
            }
            if (var2 instanceof ItemTool && ((ItemTool) var2).func_77861_e().equals("WOOD")) return 200;
            if (var2 instanceof ItemSword && ((ItemSword) var2).func_77825_f().equals("WOOD")) return 200;
            if (var2 instanceof ItemHoe && ((ItemHoe) var2).func_77842_f().equals("WOOD")) return 200;
            if (var1 == Item.stick.shiftedIndex) return 100;
            if (var1 == Item.coal.shiftedIndex) return 1600;
            if (var1 == Item.bucketLava.shiftedIndex) return 20000;
            if (var1 == Block.sapling.blockID) return 100;
            if (var1 == Item.blazeRod.shiftedIndex) return 2400;
            return GameRegistry.getFuelValue(par0ItemStack);
        }
    }

    /**
     * Return true if item is a fuel source (getItemBurnTime() > 0).
     */
    public static boolean isItemFuel(ItemStack par0ItemStack)
    {
        return getItemBurnTime(par0ItemStack) > 0;
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
    {
        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

    public void openChest() {}

    public void closeChest() {}

    @Override
    public int getStartInventorySide(ForgeDirection side)
    {
        if (side == ForgeDirection.DOWN) return 1;
        if (side == ForgeDirection.UP) return 0; 
        return 2;
    }

    @Override
    public int getSizeInventorySide(ForgeDirection side)
    {
        return 1;
    }
}
