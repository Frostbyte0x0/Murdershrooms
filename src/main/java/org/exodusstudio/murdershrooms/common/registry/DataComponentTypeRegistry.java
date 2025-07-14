package org.exodusstudio.murdershrooms.common.registry;

import net.neoforged.neoforge.registries.DeferredHolder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.exodusstudio.murdershrooms.common.component.*;
import org.exodusstudio.murdershrooms.Murdershrooms;
import org.exodusstudio.murdershrooms.common.component.JarContentsData;

import java.util.function.Supplier;

public class DataComponentTypeRegistry {
    public static final DeferredRegister.DataComponents DATA_COMPONENT_TYPES =
            DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, Murdershrooms.MOD_ID);

    public static final Supplier<DataComponentType<FillLevelData>> FILL_LEVEL = DATA_COMPONENT_TYPES.registerComponentType("fill_level",
            builder -> builder.persistent(FillLevelData.CODEC).networkSynchronized(FillLevelData.STREAM_CODEC));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<JarContentsData>> JAR_CONTENTS = DATA_COMPONENT_TYPES.registerComponentType("jar_contents",
            builder -> builder.persistent(JarContentsData.CODEC).networkSynchronized(JarContentsData.STREAM_CODEC));
}
