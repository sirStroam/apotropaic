package com.stroam.apotropaic.init;

import com.stroam.apotropaic.Apotropaic;
import com.stroam.apotropaic.Reference;
import com.stroam.apotropaic.entities.*;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ModEntities {
	public static void registerEntities() {
		registerEntity(EntityVanishingPainting.class, "paper_talisman", 64, 20, false);
	}

	public static void addSpawns() {
		
	}

	/**
	 * Get an array of {@link Biome}s with the specified {@link BiomeDictionary.Type}.
	 *
	 * @param type The Type
	 * @return An array of Biomes
	 */
	private static Biome[] getBiomes(final BiomeDictionary.Type type) {
		return BiomeDictionary.getBiomes(type).toArray(new Biome[0]);
	}


	/**
	 * Add a spawn list entry for {@code classToAdd} in each {@link Biome} with an entry for {@code classToCopy} using the same weight and group count.
	 *
	 * @param classToAdd         The class to add spawn entries for
	 * @param creatureTypeToAdd  The EnumCreatureType to add spawn entries for
	 * @param classToCopy        The class to copy spawn entries from
	 * @param creatureTypeToCopy The EnumCreatureType to copy spawn entries from
	 */
	private static void copySpawns(final Class<? extends EntityLiving> classToAdd, final EnumCreatureType creatureTypeToAdd, final Class<? extends EntityLiving> classToCopy, final EnumCreatureType creatureTypeToCopy) {
		for (final Biome biome : ForgeRegistries.BIOMES) {
			biome.getSpawnableList(creatureTypeToCopy).stream()
					.filter(entry -> entry.entityClass == classToCopy)
					.findFirst()
					.ifPresent(spawnListEntry ->
							biome.getSpawnableList(creatureTypeToAdd).add(new Biome.SpawnListEntry(classToAdd, spawnListEntry.itemWeight, spawnListEntry.minGroupCount, spawnListEntry.maxGroupCount))
					);
		}
	}

	private static int entityID = 0;

	/**
	 * Register an entity with the specified tracking values.
	 *
	 * @param entityClass          The entity's class
	 * @param entityName           The entity's unique name
	 * @param trackingRange        The range at which MC will send tracking updates
	 * @param updateFrequency      The frequency of tracking updates
	 * @param sendsVelocityUpdates Whether to send velocity information packets as well
	 */
	private static void registerEntity(final Class<? extends Entity> entityClass, final String entityName, final int trackingRange, final int updateFrequency, final boolean sendsVelocityUpdates) {
		final ResourceLocation registryName = new ResourceLocation(Apotropaic.MOD_ID, entityName);
		EntityRegistry.registerModEntity(registryName, entityClass, registryName.toString(), entityID++, Apotropaic.instance, trackingRange, updateFrequency, sendsVelocityUpdates);
	}

	/**
	 * Register an entity with the specified tracking values and spawn egg colours.
	 *
	 * @param entityClass          The entity's class
	 * @param entityName           The entity's unique name
	 * @param trackingRange        The range at which MC will send tracking updates
	 * @param updateFrequency      The frequency of tracking updates
	 * @param sendsVelocityUpdates Whether to send velocity information packets as well
	 * @param eggPrimary           The spawn egg's primary (background) colour
	 * @param eggSecondary         The spawn egg's secondary (foreground) colour
	 */
	private static void registerEntity(final Class<? extends Entity> entityClass, final String entityName, final int trackingRange, final int updateFrequency, final boolean sendsVelocityUpdates, final int eggPrimary, final int eggSecondary) {
		final ResourceLocation registryName = new ResourceLocation(Apotropaic.MOD_ID, entityName);
		EntityRegistry.registerModEntity(registryName, entityClass, registryName.toString(), entityID++, Apotropaic.instance, trackingRange, updateFrequency, sendsVelocityUpdates, eggPrimary, eggSecondary);
	}
}
