package SmeltCraft;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import java.util.Iterator;

import net.minecraft.src.Container;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ICrafting;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Slot;

public class Containercool extends Container
{
    private TileEntitycool cool;
    private int lastCookTime = 0;
    private int lastBurnTime = 0;
    private int lastItemBurnTime = 0;

    public Containercool(InventoryPlayer par1InventoryPlayer, TileEntitycool par2TileEntitycool)
    {
        this.cool = par2TileEntitycool;
        this.addSlotToContainer(new Slot(par2TileEntitycool, 0, 56, 17));
        this.addSlotToContainer(new Slot(par2TileEntitycool, 1, 56, 53));
        this.addSlotToContainer(new Slotcool(par1InventoryPlayer.player, par2TileEntitycool, 2, 116, 35));
        int var3;

        for (var3 = 0; var3 < 3; ++var3)
        {
            for (int var4 = 0; var4 < 9; ++var4)
            {
                this.addSlotToContainer(new Slot(par1InventoryPlayer, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
            }
        }

        for (var3 = 0; var3 < 9; ++var3)
        {
            this.addSlotToContainer(new Slot(par1InventoryPlayer, var3, 8 + var3 * 18, 142));
        }
    }

    public void addCraftingToCrafters(ICrafting par1ICrafting)
    {
        super.addCraftingToCrafters(par1ICrafting);
        par1ICrafting.updateCraftingInventoryInfo(this, 0, this.cool.coolCookTime);
        par1ICrafting.updateCraftingInventoryInfo(this, 1, this.cool.coolBurnTime);
        par1ICrafting.updateCraftingInventoryInfo(this, 2, this.cool.currentItemBurnTime);
    }

    /**
     * Updates crafting matrix; called from onCraftMatrixChanged. Args: none
     */
    public void updateCraftingResults()
    {
        super.updateCraftingResults();
        Iterator var1 = this.crafters.iterator();

        while (var1.hasNext())
        {
            ICrafting var2 = (ICrafting)var1.next();

            if (this.lastCookTime != this.cool.coolCookTime)
            {
                var2.updateCraftingInventoryInfo(this, 0, this.cool.coolCookTime);
            }

            if (this.lastBurnTime != this.cool.coolBurnTime)
            {
                var2.updateCraftingInventoryInfo(this, 1, this.cool.coolBurnTime);
            }

            if (this.lastItemBurnTime != this.cool.currentItemBurnTime)
            {
                var2.updateCraftingInventoryInfo(this, 2, this.cool.currentItemBurnTime);
            }
        }

        this.lastCookTime = this.cool.coolCookTime;
        this.lastBurnTime = this.cool.coolBurnTime;
        this.lastItemBurnTime = this.cool.currentItemBurnTime;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2)
    {
        if (par1 == 0)
        {
            this.cool.coolCookTime = par2;
        }

        if (par1 == 1)
        {
            this.cool.coolBurnTime = par2;
        }

        if (par1 == 2)
        {
            this.cool.currentItemBurnTime = par2;
        }
    }

    public boolean canInteractWith(EntityPlayer par1EntityPlayer)
    {
        return this.cool.isUseableByPlayer(par1EntityPlayer);
    }

    /**
     * Called to transfer a stack from one inventory to the other eg. when shift clicking.
     */
    public ItemStack transferStackInSlot(int par1)
    {
        ItemStack var2 = null;
        Slot var3 = (Slot)this.inventorySlots.get(par1);

        if (var3 != null && var3.getHasStack())
        {
            ItemStack var4 = var3.getStack();
            var2 = var4.copy();

            if (par1 == 2)
            {
                if (!this.mergeItemStack(var4, 3, 39, true))
                {
                    return null;
                }

                var3.onSlotChange(var4, var2);
            }
            else if (par1 != 1 && par1 != 0)
            {
                if (coolRecipes.smelting().getSmeltingResult(var4) != null)
                {
                    if (!this.mergeItemStack(var4, 0, 1, false))
                    {
                        return null;
                    }
                }
                else if (TileEntitycool.isItemFuel(var4))
                {
                    if (!this.mergeItemStack(var4, 1, 2, false))
                    {
                        return null;
                    }
                }
                else if (par1 >= 3 && par1 < 30)
                {
                    if (!this.mergeItemStack(var4, 30, 39, false))
                    {
                        return null;
                    }
                }
                else if (par1 >= 30 && par1 < 39 && !this.mergeItemStack(var4, 3, 30, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(var4, 3, 39, false))
            {
                return null;
            }

            if (var4.stackSize == 0)
            {
                var3.putStack((ItemStack)null);
            }
            else
            {
                var3.onSlotChanged();
            }

            if (var4.stackSize == var2.stackSize)
            {
                return null;
            }

            var3.onPickupFromSlot(var4);
        }

        return var2;
    }
}
