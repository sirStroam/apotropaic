package com.stroam.apotropaic.items;

import com.stroam.apotropaic.Apotropaic;

import net.minecraft.item.Item;

public class ItemApotropaic extends Item {
	public ItemApotropaic(final String itemName) {
		setItemName(this, itemName);
		setCreativeTab(Apotropaic.creativeTab);
	}

	/**
	 * Set the registry name of {@code item} to {@code itemName} and the unlocalised name to the full registry name.
	 *
	 * @param item     The item
	 * @param itemName The item's name
	 */
	public static void setItemName(final Item item, final String itemName) {
		item.setRegistryName(Apotropaic.MOD_ID, itemName);
		item.setUnlocalizedName(item.getRegistryName().toString());
	}
}
