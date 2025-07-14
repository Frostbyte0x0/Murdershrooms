package org.exodusstudio.murdershrooms.common.registry;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.exodusstudio.murdershrooms.common.component.JarContentsData;
import org.exodusstudio.murdershrooms.common.item.custom.alchemy.Jar;
import org.exodusstudio.murdershrooms.Murdershrooms;

import java.util.function.Supplier;

public class CreativeModeTabRegistry {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Murdershrooms.MOD_ID);

    public static final Supplier<CreativeModeTab> MURDERSHROOMS_TAB =
            CREATIVE_MODE_TABS.register("murdershroom_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.frostbite.murdershroom_tab"))
                    .icon(() -> new ItemStack(ItemRegistry.VIAL.get()))
                    .displayItems(((itemDisplayParameters, output) -> {
                        output.accept(ItemRegistry.VIAL);
                        output.accept(ItemRegistry.EMPTY_JAR);
                        output.accept(ItemRegistry.SPRAYER);

                        itemDisplayParameters.holders()
                                .lookup(RegistryRegistry.JAR_REGISTRY_KEY).ifPresent(
                                        lookup -> generatePotionEffectTypes(
                                                output,
                                                lookup,
                                                ItemRegistry.JAR.asItem(),
                                                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS,
                                                itemDisplayParameters.enabledFeatures()
                                ));
                    })).build());

    private static void generatePotionEffectTypes(
            CreativeModeTab.Output output,
            HolderLookup<Jar> jars,
            Item item,
            CreativeModeTab.TabVisibility tabVisibility,
            FeatureFlagSet requiredFeatures
    ) {
        jars.listElements()
                .filter(p_337926_ -> p_337926_.value().isEnabled(requiredFeatures))
                .map(p_330083_ -> JarContentsData.createItemStack(item, p_330083_))
                .forEach(p_270000_ -> output.accept(p_270000_, tabVisibility));
    }
}
