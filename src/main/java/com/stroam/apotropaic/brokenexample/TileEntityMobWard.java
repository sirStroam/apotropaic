package com.stroam.apotropaic.brokenexample;

import com.stroam.apotropaic.blocks.BlockMobWardTalisman;
import com.stroam.apotropaic.utilities.EventHandlerServer;

import net.minecraft.tileentity.TileEntity;

public class TileEntityMobWard extends TileEntity implements IMobWard
{
    @Override
	public double getMobWardRangeSquared() {
    	if (this.getBlockType() instanceof BlockMobWardTalisman) {
            return 4096;
        }
		return 0;
	}
    
    public void invalidate() {
        EventHandlerServer.wardPositions.remove(this);
        super.invalidate();
    }
    
    public void onChunkUnload() {
        super.onChunkUnload();
        EventHandlerServer.wardPositions.remove(this);
    }
    
    public void validate() {
        final TileEntity myCoord = this;
        for (int i = 0; i < EventHandlerServer.wardPositions.size(); ++i) {
            final TileEntity coord = EventHandlerServer.wardPositions.get(i);
            if (myCoord == coord) {
                return;
            }
        }
        EventHandlerServer.wardPositions.add(myCoord);
    }

}
