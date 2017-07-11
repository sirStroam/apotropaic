package com.stroam.apotropaic.blocks;

import com.stroam.apotropaic.Apotropaic;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class BlockApotropaic extends Block {
	
	public BlockApotropaic(final Material material, final MapColor mapColor, final String blockName) {
		super(material, mapColor);
		setBlockName(this, blockName);
		setCreativeTab(Apotropaic.creativeTab);
	}

	public BlockApotropaic(final Material materialIn, final String blockName) {
		this(materialIn, materialIn.getMaterialMapColor(), blockName);
	}

	/**
	 * Set the registry name of {@code block} to {@code blockName} and the unlocalised name to the full registry name.
	 *
	 * @param block     The block
	 * @param blockName The block's name
	 */
	public static void setBlockName(final Block block, final String blockName) {
		block.setRegistryName(Apotropaic.MOD_ID, blockName);
		block.setUnlocalizedName(block.getRegistryName().toString());
	}

}
