package com.stroam.apotropaic.brokenexample;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;

public class MBSave extends WorldSavedData {

private static final String DATA_NAME = MonsterBlocker.MODID + "_BlockList";
public List<Position> mbBlockList = new ArrayList();

public MBSave() {
    super(DATA_NAME);
}

public MBSave(String s) {
	super(s);
}
    
    public List<Position> getList() {
    	return mbBlockList;
    }

    public void onMBBlockChange(List<Position> newMBBlockList) {
    	mbBlockList = newMBBlockList;
    	markDirty();
    }
    
    public void addBlock(Position pos)
    {
    	mbBlockList.add(pos);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
    	mbBlockList.clear();
    	
    	NBTTagList nbttaglist = nbt.getTagList("mbBlockList");
    	for (int i = 0; i < nbttaglist.tagCount(); ++i) {
    		NBTTagCompound posCompound = (NBTTagCompound) nbttaglist.tagAt(i);
    		Position pos = new Position(posCompound.getInteger("x"), posCompound.getInteger("y"), posCompound.getInteger("z"));
    		mbBlockList.add(pos);
    	}
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
    	
    	NBTTagList nbttaglist = new NBTTagList();
    	nbt.setTag("mbBlockList", nbttaglist);
    	for(int i = 0; i < mbBlockList.size(); i++) {
    		NBTTagCompound compound = new NBTTagCompound();
    		Position pos = mbBlockList.get(i);
       	    compound.setInteger("x", pos.x);
       	    compound.setInteger("y", pos.y);
       	    compound.setInteger("z", pos.z);
       	    nbttaglist.appendTag(compound);
    	}    	 
    }
}
