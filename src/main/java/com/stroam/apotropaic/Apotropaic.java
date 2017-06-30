package com.stroam.apotropaic;

import java.util.UUID;

import org.apache.logging.log4j.Logger;

import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

import com.stroam.apotropaic.CreativeTabApotropaic;
import com.stroam.apotropaic.init.*;
import com.stroam.apotropaic.proxy.IProxy;
import com.stroam.*;

@Mod(modid = Apotropaic.MOD_ID, name = Apotropaic.MOD_NAME, acceptedMinecraftVersions = "[1.12]",useMetadata = false, 
dependencies = Reference.DEPENDENCIES)
public class Apotropaic
{
	public static final String MOD_ID = "apotropaic";
	public static final String MOD_NAME = "Ouroboros Apotropaic";

	public static final CreativeTabApotropaic creativeTab = new CreativeTabApotropaic();
	
	@SidedProxy(clientSide = "com.stroam.apotropaic.proxy.ClientProxy", serverSide = "com.stroam.apotropaic.proxy.ServerProxy")
	public static IProxy proxy;
	
	@Instance
	public static Apotropaic instance;
	
	public static SimpleNetworkWrapper network;

	public Apotropaic() {}
	
	static {
		FluidRegistry.enableUniversalBucket(); //must be called before preInit
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		com.stroam.apotropaic.Logger.setLogger(event.getModLog());
	
		network = NetworkRegistry.INSTANCE.newSimpleChannel(Apotropaic.MOD_ID);

		ModItems.initialiseItems();
		ModEntities.registerEntities();
		
		proxy.preInit();
	}

	@EventHandler
	public void init(final FMLInitializationEvent event) {
		//ModRecipes.registerRecipes();
		//ModRecipes.removeCraftingRecipes();
		//ModEntities.addSpawns();

		proxy.init();
	}

	@EventHandler
	public void postInit(final FMLPostInitializationEvent event) {

		proxy.postInit();
		//Tests.runTests();
	}

	@EventHandler
	public void serverStopped(final FMLServerStoppedEvent event) {
		//SpawnerDrops.serverStopped();
	}

	@EventHandler
	public void modsLoaded(FMLPostInitializationEvent event)
	{
		//reaction to other mods
	}

}

//helpful examples
//https://github.com/Choonster-Minecraft-Mods