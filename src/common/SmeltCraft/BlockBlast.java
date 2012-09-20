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

public class BlockBlast extends BlockContainer {

	public BlockBlast(int i, boolean flag) {
		super(i, Material.rock);
		blastRand = new Random();
		isActive = flag;
		blockIndexInTexture = 45;
	}

	public int idDropped(int i, Random random) {
		return SmeltCraft.bfurBlock.blockID;
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
			return SmeltCraft.blastTop;
		}
		if (l == 0) {
			return SmeltCraft.blastTop;
		}
		int i1 = iblockaccess.getBlockMetadata(i, j, k);
		if (l != i1) {
			return SmeltCraft.blastSides;
		}
		if (isActive) {
			return SmeltCraft.blastOn;
		} else {
			return SmeltCraft.blastOff;
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
			return SmeltCraft.blastTop;
		}
		if (i == 0) {
			return SmeltCraft.blastSides;
		}
		if (i == 3) {
			return SmeltCraft.blastOff;
		} else {
			return SmeltCraft.blastSides;
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

	public static void updateblastBlockState(boolean flag, World world, int i,
			int j, int k) {
		int l = world.getBlockMetadata(i, j, k);
		TileEntity tileentity = world.getBlockTileEntity(i, j, k);
		keepblastInventory = true;
		if (flag) {
			world.setBlockWithNotify(i, j, k, SmeltCraft.bfurBlockOn.blockID);
		} else {
			world.setBlockWithNotify(i, j, k, SmeltCraft.bfurBlock.blockID);
		}
		keepblastInventory = false;
		world.setBlockMetadataWithNotify(i, j, k, l);
		if (tileentity != null) {
			tileentity.validate();
			world.setBlockTileEntity(i, j, k, tileentity);
		}
	}

	public TileEntity getBlockEntity() {
		return new TileEntityBlast();
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
	public void breakBlock(World par1World, int par2, int par3, int par4,
			int par5, int par6) {
		if (!keepblastInventory) {
			TileEntityBlast var7 = (TileEntityBlast) par1World
					.getBlockTileEntity(par2, par3, par4);

			if (var7 != null) {
				for (int var8 = 0; var8 < var7.getSizeInventory(); ++var8) {
					ItemStack var9 = var7.getStackInSlot(var8);

					if (var9 != null) {
						float var10 = this.blastRand.nextFloat() * 0.8F + 0.1F;
						float var11 = this.blastRand.nextFloat() * 0.8F + 0.1F;
						float var12 = this.blastRand.nextFloat() * 0.8F + 0.1F;

						while (var9.stackSize > 0) {
							int var13 = this.blastRand.nextInt(21) + 10;

							if (var13 > var9.stackSize) {
								var13 = var9.stackSize;
							}

							var9.stackSize -= var13;
							EntityItem var14 = new EntityItem(par1World,
									(double) ((float) par2 + var10),
									(double) ((float) par3 + var11),
									(double) ((float) par4 + var12),
									new ItemStack(var9.itemID, var13,
											var9.getItemDamage()));

							if (var9.hasTagCompound()) {
								var14.item.setTagCompound((NBTTagCompound) var9
										.getTagCompound().copy());
							}

							float var15 = 0.05F;
							var14.motionX = (double) ((float) this.blastRand
									.nextGaussian() * var15);
							var14.motionY = (double) ((float) this.blastRand
									.nextGaussian() * var15 + 0.2F);
							var14.motionZ = (double) ((float) this.blastRand
									.nextGaussian() * var15);
							par1World.spawnEntityInWorld(var14);
						}
					}
				}
			}
		}

		super.breakBlock(par1World, par2, par3, par4, par5, par6);
	}

	private Random blastRand;
	private final boolean isActive;
	private static boolean keepblastInventory = false;

	@Override
	public TileEntity createNewTileEntity(World var1) {
		// TODO Auto-generated method stub
		return null;
	}

}
