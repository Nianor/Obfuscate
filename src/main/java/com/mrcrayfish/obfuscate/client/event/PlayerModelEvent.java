package com.mrcrayfish.obfuscate.client.event;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Cancelable;

/**
 * Author: MrCrayfish
 */
public abstract class PlayerModelEvent extends PlayerEvent
{
    private PlayerModel modelPlayer;
    private float partialTicks;
    private float limbSwing;
    private float limbSwingAmount;
    private float ageInTicks;
    private float netHeadYaw;
    private float headPitch;

    private PlayerModelEvent(PlayerEntity player, PlayerModel modelPlayer, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float partialTicks)
    {
        super(player);
        this.modelPlayer = modelPlayer;
        this.limbSwing = limbSwing;
        this.limbSwingAmount = limbSwingAmount;
        this.ageInTicks = ageInTicks;
        this.netHeadYaw = netHeadYaw;
        this.headPitch = headPitch;
        this.partialTicks = partialTicks;
    }

    public PlayerModel getModelPlayer()
    {
        return this.modelPlayer;
    }

    public float getLimbSwing()
    {
        return this.limbSwing;
    }

    public float getLimbSwingAmount()
    {
        return this.limbSwingAmount;
    }

    public float getAgeInTicks()
    {
        return this.ageInTicks;
    }

    public float getNetHeadYaw()
    {
        return this.netHeadYaw;
    }

    public float getHeadPitch()
    {
        return this.headPitch;
    }

    public float getPartialTicks()
    {
        return this.partialTicks;
    }

    @Cancelable
    public static class SetupAngles extends PlayerModelEvent
    {
        private SetupAngles(PlayerEntity player, PlayerModel modelPlayer, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float partialTicks)
        {
            super(player, modelPlayer, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks);
        }

        public static class Pre extends SetupAngles
        {
            public Pre(PlayerEntity player, PlayerModel modelPlayer, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float partialTicks)
            {
                super(player, modelPlayer, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks);
            }
        }

        public static class Post extends SetupAngles
        {
            public Post(PlayerEntity player, PlayerModel modelPlayer, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float partialTicks)
            {
                super(player, modelPlayer, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks);
            }

            @Override
            public boolean isCancelable()
            {
                return false;
            }
        }
    }

    @Cancelable
    public static class Render extends PlayerModelEvent
    {
        private MatrixStack matrixStack;
        private IVertexBuilder builder;
        private int light;
        private int overlay;

        private Render(PlayerEntity player, PlayerModel modelPlayer, MatrixStack matrixStack, IVertexBuilder builder, int light, int overlay, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float partialTicks)
        {
            super(player, modelPlayer, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks);
            this.matrixStack = matrixStack;
            this.builder = builder;
            this.light = light;
            this.overlay = overlay;
        }

        public static class Pre extends Render
        {
            public Pre(PlayerEntity player, PlayerModel modelPlayer, MatrixStack matrixStack, IVertexBuilder builder, int light, int overlay, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float partialTicks)
            {
                super(player, modelPlayer, matrixStack, builder, light, overlay, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks);
            }
        }

        public static class Post extends Render
        {
            public Post(PlayerEntity player, PlayerModel modelPlayer, MatrixStack matrixStack, IVertexBuilder builder, int light, int overlay, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float partialTicks)
            {
                super(player, modelPlayer, matrixStack, builder, light, overlay, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks);
            }

            @Override
            public boolean isCancelable()
            {
                return false;
            }
        }

        public MatrixStack getMatrixStack()
        {
            return this.matrixStack;
        }

        public IVertexBuilder getBuilder()
        {
            return this.builder;
        }

        public int getLight()
        {
            return this.light;
        }

        public int getOverlay()
        {
            return this.overlay;
        }
    }
}
