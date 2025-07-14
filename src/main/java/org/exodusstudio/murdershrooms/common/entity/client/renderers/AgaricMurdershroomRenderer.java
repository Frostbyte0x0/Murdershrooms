package org.exodusstudio.murdershrooms.common.entity.client.renderers;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import org.exodusstudio.murdershrooms.common.entity.client.layers.ModModelLayers;
import org.exodusstudio.murdershrooms.Murdershrooms;
import org.exodusstudio.murdershrooms.common.entity.client.models.AgaricMurdershroomModel;
import org.exodusstudio.murdershrooms.common.entity.custom.AgaricMurdershroomEntity;

public class AgaricMurdershroomRenderer extends MobRenderer<AgaricMurdershroomEntity, LivingEntityRenderState, AgaricMurdershroomModel> {
    public AgaricMurdershroomRenderer(EntityRendererProvider.Context context) {
        super(context, new AgaricMurdershroomModel(context.bakeLayer(ModModelLayers.AGARIC_MURDERSHROOM)), 0.45f);
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }

    @Override
    public ResourceLocation getTextureLocation(LivingEntityRenderState agaricMurdershroomRenderState) {
        return ResourceLocation.fromNamespaceAndPath(Murdershrooms.MOD_ID, "textures/entity/agaric_murdershroom/agaric_murdershroom.png");
    }
}
