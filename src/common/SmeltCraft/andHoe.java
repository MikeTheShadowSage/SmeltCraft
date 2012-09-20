/* Null and Void, Motherfuckers!

package net.minecraft.SmeltCraft.Tools;

import net.minecraft.src.*;


public class andHoe extends Item
{

    public andHoe(int i, SmeltToolMaterials smelttoolmaterials)
    {
        super(i);
        maxStackSize = 1;
        setMaxDamage(smelttoolmaterials.getMaxUses());
    }

    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l)
    {
        int i1 = world.getBlockId(i, j, k);
        int j1 = world.getBlockId(i, j + 1, k);
        if(l != 0 && j1 == 0 && i1 == Block.grass.blockID || i1 == Block.dirt.blockID)
        {
            Block block = Block.tilledField;
            world.playSoundEffect((float)i + 0.5F, (float)j + 0.5F, (float)k + 0.5F, block.stepSound.func_1145_d(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
            if(world.multiplayerWorld)
            {
                return true;
            } else
            {
                world.setBlockWithNotify(i, j, k, block.blockID);
                itemstack.damageItem(1, entityplayer);
                return true;
            }
        } else
        {
            return false;
        }
    }

    public boolean isFull3D()
    {
        return true;
    }
}*/