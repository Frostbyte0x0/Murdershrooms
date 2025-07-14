package org.exodusstudio.murdershrooms.common.event;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.DirectionalPayloadHandler;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import org.exodusstudio.murdershrooms.common.entity.client.layers.ModModelLayers;
import org.exodusstudio.murdershrooms.Murdershrooms;
import org.exodusstudio.murdershrooms.common.entity.client.models.*;
import org.exodusstudio.murdershrooms.common.entity.custom.*;
import org.exodusstudio.murdershrooms.common.entity.custom.illusory.IllusoryEndermanEntity;
import org.exodusstudio.murdershrooms.common.entity.custom.illusory.IllusoryZombieEntity;
import org.exodusstudio.murdershrooms.common.network.PlayerHeartDataHandler;
import org.exodusstudio.murdershrooms.common.registry.EntityRegistry;

import static org.exodusstudio.murdershrooms.common.registry.RegistryRegistry.JAR_REGISTRY;

@EventBusSubscriber(modid = Murdershrooms.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.AGARIC_MURDERSHROOM, AgaricMurdershroomModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.CRYSTAL_MURDERSHROOM, CrystalMurdershroomModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.DECAYING_MURDERSHROOM, DecayingMurdershroomModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.FLORAL_MURDERSHROOM, FloralMurdershroomModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.LIGHT_MURDERSHROOM, LightMurdershroomModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.MOSSY_MURDERSHROOM, MossyMurdershroomModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.PINE_MURDERSHROOM, PineMurdershroomModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(EntityRegistry.AGARIC_MURDERSHROOM.get(), AgaricMurdershroomEntity.createAttributes().build());
        event.put(EntityRegistry.CRYSTAL_MURDERSHROOM.get(), CrystalMurdershroomEntity.createAttributes().build());
        event.put(EntityRegistry.DECAYING_MURDERSHROOM.get(), DecayingMurdershroomEntity.createAttributes().build());
        event.put(EntityRegistry.FLORAL_MURDERSHROOM.get(), FloralMurdershroomEntity.createAttributes().build());
        event.put(EntityRegistry.LIGHT_MURDERSHROOM.get(), LightMurdershroomEntity.createAttributes().build());
        event.put(EntityRegistry.MOSSY_MURDERSHROOM.get(), MossyMurdershroomEntity.createAttributes().build());
        event.put(EntityRegistry.PINE_MURDERSHROOM.get(), PineMurdershroomEntity.createAttributes().build());
        event.put(EntityRegistry.ILLUSORY_ZOMBIE.get(), IllusoryZombieEntity.createAttributes().build());
        event.put(EntityRegistry.ILLUSORY_ENDERMAN.get(), IllusoryEndermanEntity.createAttributes().build());
    }

    @SubscribeEvent
    static void registerRegistries(NewRegistryEvent event) {
        event.register(JAR_REGISTRY);
    }

    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1");
        registrar.playToClient(
                PlayerHeartDataHandler.TYPE,
                PlayerHeartDataHandler.STREAM_CODEC,
                new DirectionalPayloadHandler<>(
                        ModEventBusEvents::handleHeartData,
                        ModEventBusEvents::handleHeartData
                )
        );
    }

    public static void handleHeartData(final PlayerHeartDataHandler data, final IPayloadContext context) {
        context.enqueueWork(() -> PlayerHeartDataHandler.handle(data, context.player()));
    }
}
