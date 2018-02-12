package com.mrcrayfish.obfuscate.client;

import com.mrcrayfish.obfuscate.client.model.layer.LayerCustomHeldItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;
import java.util.Map;

/**
 * Author: MrCrayfish
 */
public class ClientEvents
{
    private boolean setupThirdPerson = false;

    @SubscribeEvent
    public void onRenderPlayer(RenderPlayerEvent.Pre event)
    {
        if(!setupThirdPerson)
        {
            RenderPlayer renderer = event.getRenderer();
            List<LayerRenderer<EntityLivingBase>> layers = ObfuscationReflectionHelper.getPrivateValue(RenderLivingBase.class, renderer, "field_177097_h");
            if(layers != null)
            {
                layers.removeIf(layerRenderer -> layerRenderer instanceof LayerHeldItem);
                layers.add(new LayerCustomHeldItem(event.getRenderer()));
            }
            setupThirdPerson = true;
        }
    }
}
