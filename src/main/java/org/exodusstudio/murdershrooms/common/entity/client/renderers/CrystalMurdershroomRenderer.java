package org.exodusstudio.murdershrooms.common.entity.client.renderers;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import org.exodusstudio.murdershrooms.common.entity.client.layers.ModModelLayers;
import org.exodusstudio.murdershrooms.Murdershrooms;
import org.exodusstudio.murdershrooms.common.entity.client.models.CrystalMurdershroomModel;
import org.exodusstudio.murdershrooms.common.entity.custom.CrystalMurdershroomEntity;

public class CrystalMurdershroomRenderer extends MobRenderer<CrystalMurdershroomEntity, LivingEntityRenderState, CrystalMurdershroomModel> {
    public CrystalMurdershroomRenderer(EntityRendererProvider.Context context) {
        super(context, new CrystalMurdershroomModel(context.bakeLayer(ModModelLayers.CRYSTAL_MURDERSHROOM)), 0.45f);
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }

    @Override
    public ResourceLocation getTextureLocation(LivingEntityRenderState crystalMurdershroomRenderState) {
        return ResourceLocation.fromNamespaceAndPath(Murdershrooms.MOD_ID, "textures/entity/crystal_murdershroom/crystal_murdershroom.png");
    }
}