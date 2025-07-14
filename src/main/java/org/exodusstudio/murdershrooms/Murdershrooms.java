package org.exodusstudio.murdershrooms;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.EndermanRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.exodusstudio.murdershrooms.common.entity.client.renderers.*;
import org.exodusstudio.murdershrooms.common.item.custom.alchemy.Jars;
import org.exodusstudio.murdershrooms.common.registry.*;
import org.slf4j.Logger;

@Mod(Murdershrooms.MOD_ID)
public class Murdershrooms {
    public static final String MOD_ID = "murdershrooms";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Murdershrooms(IEventBus modEventBus, ModContainer modContainer) {
        ItemRegistry.ITEMS.register(modEventBus);
        CreativeModeTabRegistry.CREATIVE_MODE_TABS.register(modEventBus);
        EntityRegistry.ENTITY_TYPES.register(modEventBus);
        EffectRegistry.MOB_EFFECTS.register(modEventBus);
        ParticleRegistry.PARTICLE_TYPES.register(modEventBus);
        Jars.JARS.register(modEventBus);

        //NeoForge.EVENT_BUS.register(this);

    }

    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(EntityRegistry.AGARIC_MURDERSHROOM.get(), AgaricMurdershroomRenderer::new);
            EntityRenderers.register(EntityRegistry.CRYSTAL_MURDERSHROOM.get(), CrystalMurdershroomRenderer::new);
            EntityRenderers.register(EntityRegistry.DECAYING_MURDERSHROOM.get(), DecayingMurdershroomRenderer::new);
            EntityRenderers.register(EntityRegistry.FLORAL_MURDERSHROOM.get(), FloralMurdershroomRenderer::new);
            EntityRenderers.register(EntityRegistry.LIGHT_MURDERSHROOM.get(), LightMurdershroomRenderer::new);
            EntityRenderers.register(EntityRegistry.MOSSY_MURDERSHROOM.get(), MossyMurdershroomRenderer::new);
            EntityRenderers.register(EntityRegistry.PINE_MURDERSHROOM.get(), PineMurdershroomRenderer::new);
            EntityRenderers.register(EntityRegistry.ILLUSORY_ZOMBIE.get(), ZombieRenderer::new);
            EntityRenderers.register(EntityRegistry.ILLUSORY_ENDERMAN.get(), EndermanRenderer::new);
            EntityRenderers.register(EntityRegistry.SPORE_CLOUD.get(), GenericEntityRenderer::new);

        }
    }
}
