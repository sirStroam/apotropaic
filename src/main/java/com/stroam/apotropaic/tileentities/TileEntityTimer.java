package com.stroam.apotropaic.tileentities;

import net.minecraft.block.Block;
import net.minecraft.client.particle.Particle;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Random;

/**
 * User: brandon3055
 * Date: 06/01/2015
 *
 * This is a simple tile entity which stores some data
 * When placed, it waits for 10 seconds then replaces itself with a random block
 */
public class TileEntityTimer extends TileEntity implements ITickable {

	private final int INVALID_VALUE = -1;
	private int ticksLeftTillDisappear = INVALID_VALUE;  // the time (in ticks) left until the block disappears

	// set by the block upon creation
	public void setTicksLeftTillDisappear(int ticks)
	{
		ticksLeftTillDisappear = ticks;
	}

	// When the world loads from disk, the server needs to send the TileEntity information to the client
	//  it uses getUpdatePacket(), getUpdateTag(), onDataPacket(), and handleUpdateTag() to do this:
  //  getUpdatePacket() and onDataPacket() are used for one-at-a-time TileEntity updates
  //  getUpdateTag() and handleUpdateTag() are used by vanilla to collate together into a single chunk update packet
	//  Not really required for this example since we only use the timer on the client, but included anyway for illustration
	@Override
  @Nullable
  public SPacketUpdateTileEntity getUpdatePacket()
  {
		NBTTagCompound nbtTagCompound = new NBTTagCompound();
		writeToNBT(nbtTagCompound);
		int metadata = getBlockMetadata();
		return new SPacketUpdateTileEntity(this.pos, metadata, nbtTagCompound);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		readFromNBT(pkt.getNbtCompound());
	}

  /* Creates a tag containing the TileEntity information, used by vanilla to transmit from server to client
 */
  @Override
  public NBTTagCompound getUpdateTag()
  {
    NBTTagCompound nbtTagCompound = new NBTTagCompound();
    writeToNBT(nbtTagCompound);
    return nbtTagCompound;
  }

  /* Populates this TileEntity with information from the tag, used by vanilla to transmit from server to client
 */
  @Override
  public void handleUpdateTag(NBTTagCompound tag)
  {
    this.readFromNBT(tag);
  }

  /**
   * Saves NBT data
   * 
   * NBTexplorer examines and validates saved NBT data:
   * http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-tools/1262665-nbtexplorer-nbt-editor-for-windows-and-mac
   */
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound parentNBTTagCompound)
	{
		super.writeToNBT(parentNBTTagCompound); // saves tiles location

		parentNBTTagCompound.setInteger("ticksLeft", ticksLeftTillDisappear);
		// alternatively - could use parentNBTTagCompound.setTag("ticksLeft", new NBTTagInt(ticksLeftTillDisappear));

    return parentNBTTagCompound;
	}

	/**
	 *  load saved NBT data
	 *  
	 *  Never trust data read from NBT - make sure it can't cause a crash
	 */
	@Override
	public void readFromNBT(NBTTagCompound parentNBTTagCompound)
	{
		super.readFromNBT(parentNBTTagCompound); // The super call is required to load the tiles location

		final int NBT_INT_ID = 3;					// see NBTBase.createNewByType()
		int readTicks = INVALID_VALUE;
		if (parentNBTTagCompound.hasKey("ticksLeft", NBT_INT_ID)) {  // check if key exists and is Int. You can omit this if a default value of 0 is ok.
			readTicks = parentNBTTagCompound.getInteger("ticksLeft");
			if (readTicks < 0) readTicks = INVALID_VALUE;
		}
		ticksLeftTillDisappear = readTicks;
	}


	/**
	 * Updates timer
	 * 
	 * called once per tick
	 * replaces with air
	 */
	@Override
	public void update() {
		if (!this.hasWorld()) return;  // prevent crash
		World world = this.getWorld();
		if (world.isRemote) return;   // don't bother doing anything on the client side.
		if (ticksLeftTillDisappear == INVALID_VALUE) return;  // do nothing until the time is valid
		--ticksLeftTillDisappear;
//		this.markDirty();            // if you update a tileentity variable on the server and this should be communicated to the client,
// 										you need to markDirty() to force a resend.  In this case, the client doesn't need to know
		if (ticksLeftTillDisappear > 0) return;   // not ready yet

		world.setBlockToAir(getPos());
		world.spawnParticle(EnumParticleTypes.FLAME, getPos().getX()+0.5, getPos().getY()+0.5, getPos().getZ()+0.5, 0, 1, 0, new int[0]);
		
	}

}