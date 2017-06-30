package com.stroam.apotropaic.entities;

import javax.annotation.Nullable;

import com.stroam.apotropaic.Apotropaic;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityVanishingPainting extends EntityAttachable{
	
	private static final DataParameter<Integer> DURATION = EntityDataManager.<Integer>createKey(EntityVanishingPainting.class,
			DataSerializers.VARINT);
	
	private int duration;
	private int tickCounter1;

	public EntityVanishingPainting(World worldIn)
    {
        super(worldIn);
        this.duration = 80;
        this.setSize(0.5F, 0.5F);
    }

    public EntityVanishingPainting(World worldIn, BlockPos pos, EnumFacing facing)
    {
        super(worldIn, pos);
        this.updateFacingWithBoundingBox(facing);
    }
    
    @Override
    protected void entityInit()
    {
        this.dataManager.register(DURATION, Integer.valueOf(80));
    }
    
    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void onUpdate()
    {
    	
    	this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.tickCounter1++ == 20 && !this.world.isRemote)
        {
            this.tickCounter1 = 0;

            if (!this.isDead && !this.onValidSurface())
            {
                this.setDead();
                this.onBroken((Entity)null);
            }
        }

        --this.duration;

        if (this.duration <= 0)
        {
            this.setDead();

            if (!this.world.isRemote)
            {
                this.burnUp();
            }
        }
        else
        {
            this.handleWaterMovement();
            //this.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);
        }
    }

	@Override
	public int getWidthPixels() {
		return 6;
	}

	@Override
	public int getHeightPixels() {
		return 12;
	}

	@Override
	public void onBroken(Entity brokenEntity) {
		if (this.world.getGameRules().getBoolean("doEntityDrops"))
        {
			this.playSound(SoundEvents.ENTITY_PAINTING_BREAK, 1.0F, 1.0F);
			
			if (brokenEntity instanceof EntityPlayer)
            {
				EntityPlayer entityplayer = (EntityPlayer)brokenEntity;

                if (entityplayer.capabilities.isCreativeMode)
                {
                    return;
                }
            }
			this.setDead();
			this.burnUp();
            //this.entityDropItem(new ItemStack(this), 0.0F);
        }
	}

	@Override
	public void playPlaceSound() {
		this.playSound(SoundEvents.ENTITY_PAINTING_PLACE, 1.0F, 1.0F);
		
	}
	
	private void burnUp()
	{
		world.spawnParticle(EnumParticleTypes.FLAME, this.posX, this.posY, this.posZ, 0, 0, 0, new int[0]);
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound compound)
    {
        compound.setShort("DURATION", (short)this.getDuration());
    }
	
	@Override
	public void readEntityFromNBT(NBTTagCompound compound)
	{
		this.setDuration(compound.getShort("DURATION"));
	}
	
	public void setDuration(int time) 
	{
		this.dataManager.set(DURATION, Integer.valueOf(time));
	}
	
	public void notifyDataMangerChange(DataParameter<?> key)
	{
		if (DURATION.equals(key))
		{
			this.duration = this.getDurationDataManger();
		}
	}
	
	/**
	 * Gets the duration from the data manager
	 */
	public int getDurationDataManger() 
	{
		return ((Integer)this.dataManager.get(DURATION)).intValue();
	}

	public int getDuration() 
	{
		return this.duration;
	}
	
	

}
