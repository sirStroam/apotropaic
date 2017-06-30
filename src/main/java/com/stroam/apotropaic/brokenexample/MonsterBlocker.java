package com.stroam.apotropaic.brokenexample;

import java.util.ArrayList;
import java.util.List;

import com.stroam.apotropaic.proxy.CommonProxy;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = MonsterBlocker.MODID, name = MonsterBlocker.NAME, version = MonsterBlocker.VERSION)
@NetworkMod( channels = {"MB"}, clientSideRequired = true, serverSideRequired = true )

public class MonsterBlocker 
{
    public static final String MODID = "MonsterBlocker";
    public final static String AID = MODID.toLowerCase();
    public final static String NAME = "Monster Blocker";
    public static final String VERSION = "0.1";
    public static final String DATA_NAME = MonsterBlocker.MODID + "_BlockList";
    
    public static final String mbBlock_unlocalizedName = "monsterBlocker";
    public static final String mbBlock_name = "Monster Blocker";
    
    @SidedProxy( clientSide = "com.monsterBlocker.ClientProxy", serverSide = "com.monsterBlocker.CommonProxy" )
    public static CommonProxy proxy;
    
    public static Block monsterBlocker;

    // The instance of your mod that Forge uses.
    @Instance(value = MonsterBlocker.MODID)
    public static MonsterBlocker instance;
    
    @EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
    	proxy.initRenderers();
    	proxy.initSounds();
    	
    	monsterBlocker = new MBBlock(3009);
    	GameRegistry.registerBlock(monsterBlocker, MonsterBlocker.mbBlock_name);
    	LanguageRegistry.addName(monsterBlocker, MonsterBlocker.mbBlock_name);
    	
    	GameRegistry.addRecipe(new ItemStack(MonsterBlocker.monsterBlocker, 1),
    			new Object[] {
    			"BIB",
    			"IGI",
    			"BIB",
    			'G', Block.glowStone, 'I', Item.ingotGold, 'B', Item.blazePowder
    			});
    	}
    
    @EventHandler
    public static void init ( FMLInitializationEvent event ) {
    	MinecraftForge.EVENT_BUS.register(new MBEventHandler());
    }
}
