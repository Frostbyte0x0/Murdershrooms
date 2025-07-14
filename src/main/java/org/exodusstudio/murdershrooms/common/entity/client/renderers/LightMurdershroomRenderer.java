package org.exodusstudio.murdershrooms.common.entity.client.renderers;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import org.exodusstudio.murdershrooms.common.entity.client.layers.ModModelLayers;
import org.exodusstudio.murdershrooms.Murdershrooms;
import org.exodusstudio.murdershrooms.common.entity.client.models.LightMurdershroomModel;
import org.exodusstudio.murdershrooms.common.entity.custom.LightMurdershroomEntity;

public class LightMurdershroomRenderer extends MobRenderer<LightMurdershroomEntity, LivingEntityRenderState, LightMurdershroomModel> {
    public LightMurdershroomRenderer(EntityRendererProvider.Context context) {
        super(context, new LightMurdershroomModel(context.bakeLayer(ModModelLayers.LIGHT_MURDERSHROOM)), 0.45f);
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }

    @Override
    public ResourceLocation getTextureLocation(LivingEntityRenderState lightMurdershroomRenderState) {
        return ResourceLocation.fromNamespaceAndPath(Murdershrooms.MOD_ID, "textures/entity/light_murdershroom/light_murdershroom.png");
    }
}