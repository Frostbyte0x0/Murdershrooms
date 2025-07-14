package org.exodusstudio.murdershrooms.common.network;

import net.minecraft.resources.ResourceLocation;
import org.exodusstudio.murdershrooms.Murdershrooms;

public class NetworkManager {
    public static ResourceLocation PLAYER_HEART_DATA_PACKET_ID = ResourceLocation.fromNamespaceAndPath(Murdershrooms.MOD_ID, "player_heart_data");
    public static ResourceLocation PARTICLE_HIT_ENTITY_EVENT_PACKET_ID = ResourceLocation.fromNamespaceAndPath(Murdershrooms.MOD_ID, "particle_hit_entity");
}
