package com.stroam.apotropaic.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IThreadListener;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import javax.annotation.Nullable;

import com.stroam.apotropaic.client.renderer.entity.ModRenderers;

public class ClientProxy implements IProxy{
	
	private final Minecraft MINECRAFT = Minecraft.getMinecraft();

	@Override
	public void preInit() {
		ModRenderers.register();
	}

	@Override
	public void init() {
		
	}

	@Override
	public void postInit() {

	}

	@Override
	public void doClientRightClick() {
		
	}

	@Nullable
	@Override
	public EntityPlayer getClientPlayer() {
		return MINECRAFT.player;
	}

	@Nullable
	@Override
	public World getClientWorld() {
		return MINECRAFT.world;
	}

	@Override
	public IThreadListener getThreadListener(final MessageContext context) {
		if (context.side.isClient()) {
			return MINECRAFT;
		} else {
			return context.getServerHandler().player.mcServer;
		}
	}

	@Override
	public EntityPlayer getPlayer(final MessageContext context) {
		if (context.side.isClient()) {
			return MINECRAFT.player;
		} else {
			return context.getServerHandler().player;
		}
	}

	@Override
	public void displayLockGUI(final BlockPos pos, final EnumFacing facing) {

	}

}
