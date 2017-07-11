package com.stroam.apotropaic.brokenexample;

import javax.annotation.Nullable;

import com.stroam.apotropaic.entities.EntityVanishingPainting;

import net.minecraft.client.renderer.entity.RenderEntityItem;
import net.minecraft.client.renderer.entity.RenderPainting;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ModelManager;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
//import net.minecraft.entity.item.EntityPainting;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.storage.MapData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderVanishingPainting extends Render<EntityVanishingPainting>
{
	 //private static final ResourceLocation KRISTOFFER_TempAntiMobSpawn_TEXTURE = new ResourceLocation("textures/entity/papertalisman.png");
	private final ResourceLocation entityTexture;
	
	    public RenderVanishingPainting(final RenderManager renderManagerIn, final ResourceLocation entityTexture)
	    {
	        super(renderManagerIn);
	        this.entityTexture = entityTexture;
	    }
	    
	    protected ResourceLocation getEntityTexture(final EntityVanishingPainting entity)
	    {
	    	return entityTexture;
	    }

	    /**
	     * Renders the desired {@code T} type Entity.
	     */
	    public void doRender(EntityVanishingPainting entity, double x, double y, double z, float entityYaw, float partialTicks)
	    {
	        GlStateManager.pushMatrix();
	        GlStateManager.translate(x, y, z);
	        GlStateManager.rotate(180.0F - entityYaw, 0.0F, 1.0F, 0.0F);
	        GlStateManager.enableRescaleNormal();
	        this.bindEntityTexture(entity);
	        float f = 0.0625F;
	        GlStateManager.scale(0.0625F, 0.0625F, 0.0625F);

	        if (this.renderOutlines)
	        {
	            GlStateManager.enableColorMaterial();
	            GlStateManager.enableOutlineMode(this.getTeamColor(entity));
	        }

	        this.renderVanishingPainting(entity, 6, 12, 6, 3); //width height starting x and y positions

	        if (this.renderOutlines)
	        {
	            GlStateManager.disableOutlineMode();
	            GlStateManager.disableColorMaterial();
	        }

	        GlStateManager.disableRescaleNormal();
	        GlStateManager.popMatrix();
	        super.doRender(entity, x, y, z, entityYaw, partialTicks);
	    }

	    private void renderVanishingPainting(EntityVanishingPainting paiting, int width, int height, int textureU, int textureV)
	    {
	        float f = (float)(-width) / 2.0F;
	        float f1 = (float)(-height) / 2.0F;
	        float f2 = 0.5F;
	        float f3 = 0.75F;
	        float f4 = 0.8125F;
	        float f5 = 0.0F;
	        float f6 = 0.0625F;
	        float f7 = 0.75F;
	        float f8 = 0.8125F;
	        float f9 = 0.001953125F;
	        float f10 = 0.001953125F;
	        float f11 = 0.7519531F;
	        float f12 = 0.7519531F;
	        float f13 = 0.0F;
	        float f14 = 0.0625F;

	        for (int i = 0; i < width / 16; ++i)
	        {
	            for (int j = 0; j < height / 16; ++j)
	            {
	                float f15 = f + (float)((i + 1) * 16);
	                float f16 = f + (float)(i * 16);
	                float f17 = f1 + (float)((j + 1) * 16);
	                float f18 = f1 + (float)(j * 16);
	                this.setLightmap(paiting, (f15 + f16) / 2.0F, (f17 + f18) / 2.0F);
	                float f19 = (float)(textureU + width - i * 16) / 256.0F;
	                float f20 = (float)(textureU + width - (i + 1) * 16) / 256.0F;
	                float f21 = (float)(textureV + height - j * 16) / 256.0F;
	                float f22 = (float)(textureV + height - (j + 1) * 16) / 256.0F;
	                Tessellator tessellator = Tessellator.getInstance();
	                BufferBuilder bufferbuilder = tessellator.getBuffer();
	                bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);
	                bufferbuilder.pos((double)f15, (double)f18, -0.5D).tex((double)f20, (double)f21).normal(0.0F, 0.0F, -1.0F).endVertex();
	                bufferbuilder.pos((double)f16, (double)f18, -0.5D).tex((double)f19, (double)f21).normal(0.0F, 0.0F, -1.0F).endVertex();
	                bufferbuilder.pos((double)f16, (double)f17, -0.5D).tex((double)f19, (double)f22).normal(0.0F, 0.0F, -1.0F).endVertex();
	                bufferbuilder.pos((double)f15, (double)f17, -0.5D).tex((double)f20, (double)f22).normal(0.0F, 0.0F, -1.0F).endVertex();
	                bufferbuilder.pos((double)f15, (double)f17, 0.5D).tex(0.75D, 0.0D).normal(0.0F, 0.0F, 1.0F).endVertex();
	                bufferbuilder.pos((double)f16, (double)f17, 0.5D).tex(0.8125D, 0.0D).normal(0.0F, 0.0F, 1.0F).endVertex();
	                bufferbuilder.pos((double)f16, (double)f18, 0.5D).tex(0.8125D, 0.0625D).normal(0.0F, 0.0F, 1.0F).endVertex();
	                bufferbuilder.pos((double)f15, (double)f18, 0.5D).tex(0.75D, 0.0625D).normal(0.0F, 0.0F, 1.0F).endVertex();
	                bufferbuilder.pos((double)f15, (double)f17, -0.5D).tex(0.75D, 0.001953125D).normal(0.0F, 1.0F, 0.0F).endVertex();
	                bufferbuilder.pos((double)f16, (double)f17, -0.5D).tex(0.8125D, 0.001953125D).normal(0.0F, 1.0F, 0.0F).endVertex();
	                bufferbuilder.pos((double)f16, (double)f17, 0.5D).tex(0.8125D, 0.001953125D).normal(0.0F, 1.0F, 0.0F).endVertex();
	                bufferbuilder.pos((double)f15, (double)f17, 0.5D).tex(0.75D, 0.001953125D).normal(0.0F, 1.0F, 0.0F).endVertex();
	                bufferbuilder.pos((double)f15, (double)f18, 0.5D).tex(0.75D, 0.001953125D).normal(0.0F, -1.0F, 0.0F).endVertex();
	                bufferbuilder.pos((double)f16, (double)f18, 0.5D).tex(0.8125D, 0.001953125D).normal(0.0F, -1.0F, 0.0F).endVertex();
	                bufferbuilder.pos((double)f16, (double)f18, -0.5D).tex(0.8125D, 0.001953125D).normal(0.0F, -1.0F, 0.0F).endVertex();
	                bufferbuilder.pos((double)f15, (double)f18, -0.5D).tex(0.75D, 0.001953125D).normal(0.0F, -1.0F, 0.0F).endVertex();
	                bufferbuilder.pos((double)f15, (double)f17, 0.5D).tex(0.751953125D, 0.0D).normal(-1.0F, 0.0F, 0.0F).endVertex();
	                bufferbuilder.pos((double)f15, (double)f18, 0.5D).tex(0.751953125D, 0.0625D).normal(-1.0F, 0.0F, 0.0F).endVertex();
	                bufferbuilder.pos((double)f15, (double)f18, -0.5D).tex(0.751953125D, 0.0625D).normal(-1.0F, 0.0F, 0.0F).endVertex();
	                bufferbuilder.pos((double)f15, (double)f17, -0.5D).tex(0.751953125D, 0.0D).normal(-1.0F, 0.0F, 0.0F).endVertex();
	                bufferbuilder.pos((double)f16, (double)f17, -0.5D).tex(0.751953125D, 0.0D).normal(1.0F, 0.0F, 0.0F).endVertex();
	                bufferbuilder.pos((double)f16, (double)f18, -0.5D).tex(0.751953125D, 0.0625D).normal(1.0F, 0.0F, 0.0F).endVertex();
	                bufferbuilder.pos((double)f16, (double)f18, 0.5D).tex(0.751953125D, 0.0625D).normal(1.0F, 0.0F, 0.0F).endVertex();
	                bufferbuilder.pos((double)f16, (double)f17, 0.5D).tex(0.751953125D, 0.0D).normal(1.0F, 0.0F, 0.0F).endVertex();
	                tessellator.draw();
	            }
	        }
	    }

	    private void setLightmap(EntityVanishingPainting painting, float p_77008_2_, float p_77008_3_)
	    {
	        int i = MathHelper.floor(painting.posX);
	        int j = MathHelper.floor(painting.posY + (double)(p_77008_3_ / 16.0F));
	        int k = MathHelper.floor(painting.posZ);
	        EnumFacing enumfacing = painting.facingDirection;

	        if (enumfacing == EnumFacing.NORTH)
	        {
	            i = MathHelper.floor(painting.posX + (double)(p_77008_2_ / 16.0F));
	        }

	        if (enumfacing == EnumFacing.WEST)
	        {
	            k = MathHelper.floor(painting.posZ - (double)(p_77008_2_ / 16.0F));
	        }

	        if (enumfacing == EnumFacing.SOUTH)
	        {
	            i = MathHelper.floor(painting.posX - (double)(p_77008_2_ / 16.0F));
	        }

	        if (enumfacing == EnumFacing.EAST)
	        {
	            k = MathHelper.floor(painting.posZ + (double)(p_77008_2_ / 16.0F));
	        }

	        int l = this.renderManager.world.getCombinedLight(new BlockPos(i, j, k), 0);
	        int i1 = l % 65536;
	        int j1 = l / 65536;
	        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)i1, (float)j1);
	        GlStateManager.color(1.0F, 1.0F, 1.0F);
	    }
}
