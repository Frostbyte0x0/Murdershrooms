package org.exodusstudio.murdershrooms.common.registry;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.item.component.Consumables;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.exodusstudio.murdershrooms.common.component.JarContentsData;
import org.exodusstudio.murdershrooms.common.item.custom.alchemy.EmptyJarItem;
import org.exodusstudio.murdershrooms.common.item.custom.alchemy.JarItem;
import org.exodusstudio.murdershrooms.common.item.custom.alchemy.SprayerItem;
import org.exodusstudio.murdershrooms.Murdershrooms;

public class ItemRegistry {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Murdershrooms.MOD_ID);

    public static final DeferredItem<Item> EMPTY_JAR =
            ITEMS.register("empty_jar", (id) -> new EmptyJarItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, id))));
    public static final DeferredItem<Item> JAR =
            ITEMS.register("jar", (id) -> new JarItem(new Item.Properties()
                    .stacksTo(1)
                    .component(DataComponentTypeRegistry.JAR_CONTENTS, JarContentsData.EMPTY)
                    .component(DataComponents.CONSUMABLE, Consumables.DEFAULT_DRINK)
                    .setId(ResourceKey.create(Registries.ITEM, id))));
    public static final DeferredItem<Item> VIAL =
            ITEMS.register("vial", (id) -> new Item(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, id))));

    public static final DeferredItem<Item> SPRAYER =
            ITEMS.register("sprayer", (id) -> new SprayerItem(new Item.Properties()
                    .stacksTo(1)
                    .component(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY)
                    .setId(ResourceKey.create(Registries.ITEM, id))));
}