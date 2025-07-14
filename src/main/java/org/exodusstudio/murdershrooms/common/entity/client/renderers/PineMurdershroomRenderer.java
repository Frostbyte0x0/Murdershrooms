package org.exodusstudio.murdershrooms.common.entity.client.renderers;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import org.exodusstudio.murdershrooms.common.entity.client.layers.ModModelLayers;
import org.exodusstudio.murdershrooms.Murdershrooms;
import org.exodusstudio.murdershrooms.common.entity.client.models.PineMurdershroomModel;
import org.exodusstudio.murdershrooms.common.entity.custom.PineMurdershroomEntity;

public class PineMurdershroomRenderer extends MobRenderer<PineMurdershroomEntity, LivingEntityRenderState, PineMurdershroomModel> {
    public PineMurdershroomRenderer(EntityRendererProvider.Context context) {
        super(context, new PineMurdershroomModel(context.bakeLayer(ModModelLayers.PINE_MURDERSHROOM)), 0.45f);
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }

    @Override
    public ResourceLocation getTextureLocation(LivingEntityRenderState pineMurdershroomRenderState) {
        return ResourceLocation.fromNamespaceAndPath(Murdershrooms.MOD_ID, "textures/entity/pine_murdershroom/pine_murdershroom.png");
    }
}