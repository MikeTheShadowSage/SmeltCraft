// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

//blockIndexInTexture; = Regular/Side in the situation
//if(l == 1) = Top texture
//if(l == 0) = Bottom Texture
//if(i == 3) = Front Texture/Off
//blockIndexInTexture + 16; = Active Texture/On
//blockIndexInTexture - 1; = Off Texture/Off
//l = BlockID

package SmeltCraft;

import net.minecraft.src.*;

import java.util.Random;

import SmeltCraft.Load.blocks;

public class Blockmix extends BlockContainer {

	public Blockmix(int i, boolean flag) {
		super(i, Material.rock);
		mixRand = new Random();
		isActive = flag;
		blockIndexInTexture = 45;
	}

	public int idDropped(int i, Random random) {
		return blocks.mixBlock.blockID;
	}

	public void onBlockAdded(World world, int i, int j, int k) {
		super.onBlockAdded(world, i, j, k);
		setDefaultDirection(world, i, j, k);
	}

	private void setDefaultDirection(World world, int i, int j, int k) {

		int l = world.getBlockId(i, j, k - 1);
		int i1 = world.getBlockId(i, j, k + 1);
		int j1 = world.getBlockId(i - 1, j, k);
		int k1 = world.getBlockId(i + 1, j, k);
		byte byte0 = 3;
		if (Block.opaqueCubeLookup[l] && !Block.opaqueCubeLookup[i1]) {
			byte0 = 3;
		}
		if (Block.opaqueCubeLookup[i1] && !Block.opaqueCubeLookup[l]) {
			byte0 = 2;
		}
		if (Block.opaqueCubeLookup[j1] && !Block.opaqueCubeLookup[k1]) {
			byte0 = 5;
		}
		if (Block.opaqueCubeLookup[k1] && !Block.opaqueCubeLookup[j1]) {
			byte0 = 4;
		}
		world.setBlockMetadataWithNotify(i, j, k, byte0);
	}

	public int getBlockTexture(IBlockAccess iblockaccess, int i, int j, int k,
			int l) {
		if (l == 1) {
			return SmeltCraft.mixTop;
		}
		if (l == 0) {
			return SmeltCraft.mixTop;
		}
		int i1 = iblockaccess.getBlockMetadata(i, j, k);
		if (l != i1) {
			return SmeltCraft.mixSides;
		}
		if (isActive) {
			return SmeltCraft.mixOn;
		} else {
			return SmeltCraft.mixOff;
		}
	}

	/*
	 * public void randomDisplayTick(World world, int i, int j, int k, Random
	 * random) { if(!isActive) { return; } int l = world.getBlockMetadata(i, j,
	 * k); float f = (float)i + 0.5F; float f1 = (float)j + 0.0F +
	 * (random.nextFloat() * 6F) / 16F; float f2 = (float)k + 0.5F; float f3 =
	 * 0.52F; float f4 = random.nextFloat() * 0.6F - 0.3F; if(l == 4) {
	 * world.spawnParticle("smoke", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
	 * world.spawnParticle("flame", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D); }
	 * else if(l == 5) { world.spawnParticle("smoke", f + f3, f1, f2 + f4, 0.0D,
	 * 0.0D, 0.0D); world.spawnParticle("flame", f + f3, f1, f2 + f4, 0.0D,
	 * 0.0D, 0.0D); } else if(l == 2) { world.spawnParticle("smoke", f + f4, f1,
	 * f2 - f3, 0.0D, 0.0D, 0.0D); world.spawnParticle("flame", f + f4, f1, f2 -
	 * f3, 0.0D, 0.0D, 0.0D); } else if(l == 3) { world.spawnParticle("smoke", f
	 * + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D); world.spawnParticle("flame", f +
	 * f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D); } }
	 */

	public int getBlockTextureFromSide(int i) {
		if (i == 1) {
			return SmeltCraft.mixTop;
		}
		if (i == 0) {
			return SmeltCraft.mixSides;
		}
		if (i == 3) {
			return SmeltCraft.mixOff;
		} else {
			return SmeltCraft.mixSides;
		}
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int idk, float what, float these, float are) {
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if (tileEntity == null || player.isSneaking()) {
			return false;
		}
		// opens gui, to be implemented later
		// player.openGui();
		return true;
	}

	public static void updatemixBlockState(boolean flag, World world, int i,
			int j, int k) {
		int l = world.getBlockMetadata(i, j, k);
		TileEntity tileentity = world.getBlockTileEntity(i, j, k);
		keepmixInventory = true;
		if (flag) {
			world.setBlockWithNotify(i, j, k, blocks.mixBlockOn.blockID);
		} else {
			world.setBlockWithNotify(i, j, k, blocks.mixBlock.blockID);
		}
		keepmixInventory = false;
		world.setBlockMetadataWithNotify(i, j, k, l);
		if (tileentity != null) {
			tileentity.validate();
			world.setBlockTileEntity(i, j, k, tileentity);
		}
	}

	public TileEntity getBlockEntity() {
		return new TileEntitymix();
	}

	public void onBlockPlacedBy(World world, int i, int j, int k,
			EntityLiving entityliving) {
		int l = MathHelper
				.floor_double((double) ((entityliving.rotationYaw * 4F) / 360F) + 0.5D) & 3;
		if (l == 0) {
			world.setBlockMetadataWithNotify(i, j, k, 2);
		}
		if (l == 1) {
			world.setBlockMetadataWithNotify(i, j, k, 5);
		}
		if (l == 2) {
			world.setBlockMetadataWithNotify(i, j, k, 3);
		}
		if (l == 3) {
			world.setBlockMetadataWithNotify(i, j, k, 4);
		}
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
		dropItems(world, x, y, z);
		super.breakBlock(world, x, y, z, par5, par6);
	}

	private void dropItems(World world, int x, int y, int z) {
		Random rand = new Random();

		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if (!(tileEntity instanceof IInventory)) {
			return;
		}
		IInventory inventory = (IInventory) tileEntity;

		for (int i = 0; i < inventory.getSizeInventory(); i++) {
			ItemStack item = inventory.getStackInSlot(i);

			if (item != null && item.stackSize > 0) {
				float rx = rand.nextFloat() * 0.8F + 0.1F;
				float ry = rand.nextFloat() * 0.8F + 0.1F;
				float rz = rand.nextFloat() * 0.8F + 0.1F;

				EntityItem entityItem = new EntityItem(world, x + rx, y + ry, z
						+ rz, new ItemStack(item.itemID, item.stackSize,
						item.getItemDamage()));

				if (item.hasTagCompound()) {
					entityItem.item.setTagCompound((NBTTagCompound) item
							.getTagCompound().copy());
				}

				float factor = 0.05F;
				entityItem.motionX = rand.nextGaussian() * factor;
				entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
				entityItem.motionZ = rand.nextGaussian() * factor;
				world.spawnEntityInWorld(entityItem);
				item.stackSize = 0;
			}
		}
	}

	private Random mixRand;
	private final boolean isActive;
	private static boolean keepmixInventory = false;

	@Override
	public TileEntity createNewTileEntity(World var1) {
		// TODO Auto-generated method stub
		return null;
	}

}
