package org.exodusstudio.murdershrooms.common.entity.client.renderers;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import org.exodusstudio.murdershrooms.common.entity.client.layers.ModModelLayers;
import org.exodusstudio.murdershrooms.Murdershrooms;
import org.exodusstudio.murdershrooms.common.entity.client.models.MossyMurdershroomModel;
import org.exodusstudio.murdershrooms.common.entity.custom.MossyMurdershroomEntity;

public class MossyMurdershroomRenderer extends MobRenderer<MossyMurdershroomEntity, LivingEntityRenderState, MossyMurdershroomModel> {
    public MossyMurdershroomRenderer(EntityRendererProvider.Context context) {
        super(context, new MossyMurdershroomModel(context.bakeLayer(ModModelLayers.MOSSY_MURDERSHROOM)), 0.45f);
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }

    @Override
    public ResourceLocation getTextureLocation(LivingEntityRenderState mossyMurdershroomRenderState) {
        return ResourceLocation.fromNamespaceAndPath(Murdershrooms.MOD_ID, "textures/entity/mossy_murdershroom/mossy_murdershroom.png");
    }
}