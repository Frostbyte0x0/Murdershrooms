package org.exodusstudio.murdershrooms.client.overlays;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.player.Player;
import org.exodusstudio.murdershrooms.common.registry.EffectRegistry;
import org.exodusstudio.murdershrooms.Murdershrooms;

public class DecayOverlay {
    private static final ResourceLocation DECAY = ResourceLocation.fromNamespaceAndPath(Murdershrooms.MOD_ID,
            "textures/overlays/decay.png");

    public static void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
        Player player = Minecraft.getInstance().player;

        assert player != null;
        if (player.hasEffect(EffectRegistry.DECAY)) {
            int i = ARGB.color(20, 20, 20);
            guiGraphics.blit(RenderType::guiTexturedOverlay, DECAY, 0, 0, 0.0F, 0.0F,
                    guiGraphics.guiWidth(), guiGraphics.guiHeight(), guiGraphics.guiWidth(), guiGraphics.guiHeight(), i);
        }
    }
}
