package org.exodusstudio.murdershrooms.common.entity.client.renderers;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import org.exodusstudio.murdershrooms.common.entity.client.layers.ModModelLayers;
import org.exodusstudio.murdershrooms.Murdershrooms;
import org.exodusstudio.murdershrooms.common.entity.client.models.FloralMurdershroomModel;
import org.exodusstudio.murdershrooms.common.entity.custom.FloralMurdershroomEntity;

public class FloralMurdershroomRenderer extends MobRenderer<FloralMurdershroomEntity, LivingEntityRenderState, FloralMurdershroomModel> {
    public FloralMurdershroomRenderer(EntityRendererProvider.Context context) {
        super(context, new FloralMurdershroomModel(context.bakeLayer(ModModelLayers.FLORAL_MURDERSHROOM)), 0.45f);
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }

    @Override
    public ResourceLocation getTextureLocation(LivingEntityRenderState floralMurdershroomRenderState) {
        return ResourceLocation.fromNamespaceAndPath(Murdershrooms.MOD_ID, "textures/entity/floral_murdershroom/floral_murdershroom.png");
    }
}