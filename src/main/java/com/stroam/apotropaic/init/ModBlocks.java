package com.stroam.apotropaic.init;

import com.stroam.apotropaic.Apotropaic;
import com.google.common.base.Preconditions;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemCloth;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityCommandBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.common.registry.IForgeRegistry;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("WeakerAccess")
@ObjectHolder(Apotropaic.MOD_ID)
public class ModBlocks {

	@Mod.EventBusSubscriber
	public static class RegistrationHandler {
		public static final Set<ItemBlock> ITEM_BLOCKS = new HashSet<>();

		/**
		 * Register this mod's {@link Block}s.
		 *
		 * @param event The event
		 */
		@SubscribeEvent
		public static void registerBlocks(final RegistryEvent.Register<Block> event) {
			final IForgeRegistry<Block> registry = event.getRegistry();

			final Block[] blocks = {
					
			};

			registry.registerAll(blocks);

		}

		/**
		 * Register the {@link Block}s of a {@link BlockSlabTestMod3.SlabGroup}.
		 *
		 * @param registry  The registry
		 * @param slabGroup The slab group
		 */
/*		private static void registerSlabGroup(final IForgeRegistry<Block> registry, final BlockSlabTestMod3.SlabGroup<?, ?, ?> slabGroup) {
			registry.register(slabGroup.singleSlab);
			registry.register(slabGroup.doubleSlab);
		}
*/
		/**
		 * Register this mod's {@link ItemBlock}s.
		 *
		 * @param event The event
		 */
		@SubscribeEvent
		public static void registerItemBlocks(final RegistryEvent.Register<Item> event) {
			final ItemBlock[] items = {
					
			};

			final IForgeRegistry<Item> registry = event.getRegistry();

			for (final ItemBlock item : items) {
				final Block block = item.getBlock();
				final ResourceLocation registryName = Preconditions.checkNotNull(block.getRegistryName(), "Block %s has null registry name", block);
				registry.register(item.setRegistryName(registryName));
				ITEM_BLOCKS.add(item);
			}

			registerTileEntities();
		}
	}

	public static void registerTileEntities() {

	}

	private static void registerTileEntity(final Class<? extends TileEntity> tileEntityClass, final String name) {

	}

	private static void registerTileEntity(final Class<? extends TileEntity> tileEntityClass, final String name, final String legacyName) {
	
	}
}

