package SmeltCraft;
import net.minecraft.src.*;

public class Slotmix extends Slot
{

    public Slotmix(EntityPlayer entityplayer, IInventory iinventory, int i, int j, int k)
    {
        super(iinventory, i, j, k);
        thePlayer = entityplayer;
    }

    public boolean isItemValid(ItemStack itemstack)
    {
        return false;
    }

    /*public void onPickupFromSlot(ItemStack itemstack)
    {
        itemstack.onCrafting(thePlayer.worldObj, thePlayer);
        if(itemstack.itemID == Item.ingotIron.shiftedIndex)
        {
            thePlayer.addStat(AchievementList.acquireIron, 1);
        }
        if(itemstack.itemID == Item.fishCooked.shiftedIndex)
        {
            thePlayer.addStat(AchievementList.cookFish, 1);
        }
        ModLoader.takenFromFurnace(thePlayer, itemstack);
        super.onPickupFromSlot(itemstack);
    }*/

    private EntityPlayer thePlayer;
}
