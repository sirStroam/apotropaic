package com.stroam.apotropaic.brokenexample;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.World;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.world.WorldEvent;

public class WardEventHandler {

//public List<Position> mbBlockList = new ArrayList();
public static final String DATA_NAME = MonsterBlocker.MODID + "_BlockList";
private static final int RANGE_SIDE = 50;
private static final int RANGE_UP = 50;
private static final int RANGE_DOWN = 10;

public static MBSave get(World world) {
	MBSave handler = (MBSave) world.perWorldStorage.loadData(MBSave.class, DATA_NAME);
	if(handler==null) {
		handler = new MBSave();
		world.perWorldStorage.setData(DATA_NAME, handler);
	}
	return handler;
}

@ForgeSubscribe //1.6.4
public void LSECheckSpawn(LivingSpawnEvent.CheckSpawn event)
{
	List<Position> mbBlockList = new ArrayList();
	MBSave mbdata = get(event.world);
	mbBlockList = mbdata.getList();
	for (int i=0; i < mbBlockList.size(); ++i)
	{
		Position posBlock = mbBlockList.get(i);
		//Position posMonster = new Position((int) event.x, (int) event.y, (int) event.z);
		if ( (posBlock.x - RANGE_SIDE < event.x) && (event.x < posBlock.x + RANGE_SIDE) &&
			 (posBlock.y - RANGE_SIDE < event.y) && (event.y < posBlock.y + RANGE_SIDE) &&
			 (posBlock.z - RANGE_DOWN < event.z) && (event.z < posBlock.z + RANGE_UP) )
		{
			event.setResult(Result.DENY);
			System.out.println("Spawn Denied");
			return;
		}
	}
}
}




/*
import java.util.List;

import com.stroam.apotropaic.tileentities.IMobWard;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventHandlerServer
{
    public static String debug;
    public static List<TileEntity> wardPositions;

    
    public static boolean isInRangeOfWard(final Entity entity) {
        for (final TileEntity coord : EventHandlerServer.wardPositions) {
            if (coord.getWorld().provider.getDimension() == entity.world.provider.getDimension() && entity.world.getTileEntity(coord.getPos()) instanceof IMobWard) {
                final TileEntity tile = entity.world.getTileEntity(coord.getPos());
                final double distanceSq = tile.getDistanceSq(entity.posX, entity.posY,entity.posZ);
                if (distanceSq <= ((IMobWard)tile).getMobWardRangeSquared()) {
                    return true;
                }
                continue;
            }
        }
        return false;
    }
    
    @SubscribeEvent
    public void mobWardDenyTeleport(final EnderTeleportEvent event) {
        if (event.getEntityLiving() instanceof EntityEnderman && !((EntityEnderman)event.getEntityLiving()).isScreaming()) {
        	for (final TileEntity coord : EventHandlerServer.wardPositions) {
                if (coord.getWorld().provider.getDimension() == event.getEntity().world.provider.getDimension() && event.getEntity().world.getTileEntity(coord.getPos()) instanceof IMobWard) {
                    final TileEntity tile = event.getEntity().world.getTileEntity(coord.getPos());
                    final double distanceSq = tile.getDistanceSq(event.getTargetX(), event.getTargetY(),event.getTargetZ());
                    if (distanceSq <= ((IMobWard)tile).getMobWardRangeSquared()) {
                        continue;
                    }
                    final double distanceSq2 = tile.getDistanceSq( event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);

                    if (distanceSq >= distanceSq2 ) {
                        continue;
                    }
                    event.setCanceled(true);
                }
        	}
        }
    }
    
    @SubscribeEvent
    public void mobWardDenySpawn(final LivingSpawnEvent.CheckSpawn event) {
        if (event.getResult() == Event.Result.ALLOW) {
            return;
        }
        if (event.getEntityLiving().isCreatureType(EnumCreatureType.MONSTER, false) && isInRangeOfWard(event.getEntity())) {
            event.setResult(Event.Result.DENY);
        }
    }
}
    */
