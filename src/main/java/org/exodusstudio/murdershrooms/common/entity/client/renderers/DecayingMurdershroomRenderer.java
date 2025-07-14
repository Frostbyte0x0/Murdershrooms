package org.exodusstudio.murdershrooms.common.entity.client.renderers;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import org.exodusstudio.murdershrooms.common.entity.client.layers.ModModelLayers;
import org.exodusstudio.murdershrooms.Murdershrooms;
import org.exodusstudio.murdershrooms.common.entity.client.models.DecayingMurdershroomModel;
import org.exodusstudio.murdershrooms.common.entity.custom.DecayingMurdershroomEntity;

public class DecayingMurdershroomRenderer extends MobRenderer<DecayingMurdershroomEntity, LivingEntityRenderState, DecayingMurdershroomModel> {
    public DecayingMurdershroomRenderer(EntityRendererProvider.Context context) {
        super(context, new DecayingMurdershroomModel(context.bakeLayer(ModModelLayers.DECAYING_MURDERSHROOM)), 0.45f);
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }

    @Override
    public ResourceLocation getTextureLocation(LivingEntityRenderState decayingMurdershroomRenderState) {
        return ResourceLocation.fromNamespaceAndPath(Murdershrooms.MOD_ID, "textures/entity/decaying_murdershroom/decaying_murdershroom.png");
    }
}