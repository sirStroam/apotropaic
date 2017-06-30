package com.stroam.apotropaic.init;

import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.common.registry.IForgeRegistry;

import java.util.HashSet;
import java.util.Set;

import com.stroam.apotropaic.Apotropaic;
import com.stroam.apotropaic.entities.EntityVanishingPainting;
import com.stroam.apotropaic.items.*;

@SuppressWarnings("WeakerAccess")
@ObjectHolder(Apotropaic.MOD_ID)
public class ModItems {
	
	public static final ItemAttachableEntity ATTACHABLE = new ItemAttachableEntity(EntityVanishingPainting.class, "painting");


	/**
	 * Initialise this mod's {@link Item}s with any post-registration data.
	 */
	public static void initialiseItems() {
		
	}

	@Mod.EventBusSubscriber
	public static class RegistrationHandler {
		public static final Set<Item> ITEMS = new HashSet<>();

		/**
		 * Register this mod's {@link Item}s.
		 *
		 * @param event The event
		 */
		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event) {
			final Item[] items = {
					ATTACHABLE,
			};

			final IForgeRegistry<Item> registry = event.getRegistry();

			for (final Item item : items) {
				registry.register(item);
				ITEMS.add(item);
			}
		}
	}
}
