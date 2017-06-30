package com.stroam.apotropaic;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CreativeTabApotropaic extends CreativeTabs {
	private final ItemStack icon;

	public CreativeTabApotropaic() {
		super(Apotropaic.MOD_ID);
		icon = new ItemStack(Items.ENCHANTED_BOOK);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public ItemStack getTabIconItem() {
		return icon;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void displayAllRelevantItems(final NonNullList<ItemStack> items) {
		//items.add(icon.copy());
		super.displayAllRelevantItems(items);
	}

}
