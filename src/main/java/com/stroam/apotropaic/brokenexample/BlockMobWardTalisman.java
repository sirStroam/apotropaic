package com.stroam.apotropaic.brokenexample;


import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class MBBlock extends Block
{

        public MBBlock (int id)
        {
                super(id, Material.iron);
                this.setUnlocalizedName(MonsterBlocker.mbBlock_unlocalizedName);
                this.setCreativeTab(CreativeTabs.tabBlock);
                
                this.setHardness(5F);
                this.setResistance(15F);
                this.setStepSound(Block.soundMetalFootstep);
                this.setLightValue(1F);
        }
        
        @Override
        @SideOnly(Side.CLIENT)
        public void registerIcons(IconRegister icon) {
        blockIcon = icon.registerIcon(MonsterBlocker.AID + ":" + "monsterBlocker");
        }

        @Override
        public void onBlockAdded(World world, int x, int y, int z)
        {
        	Position pos = new Position(x, y, z);
        	List<Position> mbBlockList = new ArrayList();
    		MBSave mbdata = MBEventHandler.get(world);
    		
    		mbBlockList = mbdata.getList();
    		mbBlockList.add(pos);
    		mbdata.onMBBlockChange(mbBlockList);

        	//System.out.println("I Added");      	
        }
        
        @Override
        public void breakBlock(World world, int x, int y, int z, int oldBlockID, int oldMetadata)
        {
        	Position pos = new Position(x, y, z);
        	List<Position> mbBlockList = new ArrayList();
    		MBSave mbdata = MBEventHandler.get(world);
    		
    		mbBlockList = mbdata.getList();
    		mbBlockList.remove(pos);
    		mbdata.onMBBlockChange(mbBlockList);
        }
}
