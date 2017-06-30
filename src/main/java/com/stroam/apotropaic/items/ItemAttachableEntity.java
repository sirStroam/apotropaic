package com.stroam.apotropaic.items;

import javax.annotation.Nullable;

import com.stroam.apotropaic.Apotropaic;
import com.stroam.apotropaic.entities.EntityAttachable;
import com.stroam.apotropaic.entities.EntityVanishingPainting;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemAttachableEntity extends Item
{
    private final Class <? extends EntityAttachable > AttachableEntityClass;

    public ItemAttachableEntity(Class <? extends EntityAttachable > entityClass, final String itemName)
    {
        this.AttachableEntityClass = entityClass;
        ItemApotropaic.setItemName(this, itemName);
        this.setCreativeTab(Apotropaic.creativeTab);
    }

    /**
     * Called when a Block is right-clicked with this Item
     */
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemstack = player.getHeldItem(hand);
        BlockPos blockpos = pos.offset(facing);

        if (facing != EnumFacing.DOWN && facing != EnumFacing.UP && player.canPlayerEdit(blockpos, facing, itemstack))
        {
            EntityAttachable EntityAttachable = this.createEntity(worldIn, blockpos, facing);

            if (EntityAttachable != null && EntityAttachable.onValidSurface())
            {
                if (!worldIn.isRemote)
                {
                    EntityAttachable.playPlaceSound();
                    worldIn.spawnEntity(EntityAttachable);
                }

                itemstack.shrink(1);
            }

            return EnumActionResult.SUCCESS;
        }
        else
        {
            return EnumActionResult.FAIL;
        }
    }

    @Nullable
    private EntityAttachable createEntity(World worldIn, BlockPos pos, EnumFacing clickedSide)
    {
        //if (this.AttachableEntityClass == EntityVanishingPainting.class)
        
        return new EntityVanishingPainting(worldIn, pos, clickedSide);
    }
}
