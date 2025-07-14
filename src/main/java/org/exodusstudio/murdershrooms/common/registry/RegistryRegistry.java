package org.exodusstudio.murdershrooms.common.registry;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.RegistryBuilder;
import org.exodusstudio.murdershrooms.common.item.custom.alchemy.Jar;
import org.exodusstudio.murdershrooms.Murdershrooms;
import org.exodusstudio.murdershrooms.common.item.custom.alchemy.Jar;

public class RegistryRegistry {
    public static final ResourceKey<Registry<Jar>> JAR_REGISTRY_KEY =
            ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(Murdershrooms.MOD_ID, "jars"));
    public static final Registry<org.exodusstudio.murdershrooms.common.item.custom.alchemy.Jar> JAR_REGISTRY = new RegistryBuilder<>(JAR_REGISTRY_KEY)
            // If you want to enable integer id syncing, for networking.
            // These should only be used in networking contexts, for example in packets or purely networking-related NBT data.
            .sync(true)
            .defaultKey(ResourceLocation.fromNamespaceAndPath(Murdershrooms.MOD_ID, "empty"))
            .maxId(256)
            .create();
}
