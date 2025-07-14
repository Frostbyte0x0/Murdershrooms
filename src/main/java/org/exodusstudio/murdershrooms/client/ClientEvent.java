package org.exodusstudio.murdershrooms.client;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import org.exodusstudio.murdershrooms.Murdershrooms;
import org.exodusstudio.murdershrooms.client.overlays.*;
import org.exodusstudio.murdershrooms.common.particle.*;
import org.exodusstudio.murdershrooms.common.registry.ParticleRegistry;

@EventBusSubscriber(modid = Murdershrooms.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ClientEvent {
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerOverlayEvent(RegisterGuiLayersEvent event) {
        event.registerAboveAll(ResourceLocation.fromNamespaceAndPath(Murdershrooms.MOD_ID, "decay_overlay"),
                DecayOverlay::render);
        event.registerAboveAll(ResourceLocation.fromNamespaceAndPath(Murdershrooms.MOD_ID, "rage_overlay"),
                RageOverlay::render);
    }

    @SubscribeEvent
    public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ParticleRegistry.SPORE_PARTICLE.get(), SporeParticle.Provider::new);
    }
}
