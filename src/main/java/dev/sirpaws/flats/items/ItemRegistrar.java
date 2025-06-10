package dev.sirpaws.flats.items;

import dev.sirpaws.flats.Flats;
import dev.sirpaws.flats.items.interfaces.GenerativeItem;
import dev.sirpaws.flats.items.interfaces.GenerativeItemInterface;
import dev.sirpaws.flats.util.function.QuadFunction;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

// this little fella's job is to hide all the nasty bits that the registry needs
public class ItemRegistrar {
    public final ArrayList<GenerativeItemInterface> items = new ArrayList<>();
    ItemRegistrar() {

    }

    public Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings, RegistryKey<ItemGroup> itemGroup) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Flats.MOD_ID, name));

        Item item = itemFactory.apply(settings);
        if (item instanceof GenerativeItemInterface generativeItem) {
            items.add(generativeItem);
        }

        Registry.register(Registries.ITEM, itemKey, item);
        ItemGroupEvents.modifyEntriesEvent(itemGroup)
                .register((group) -> group.add(item));

        return item;
    }

    public Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Flats.MOD_ID, name));

        Item item = itemFactory.apply(settings);
        if (item instanceof GenerativeItemInterface generativeItem) {
            items.add(generativeItem);
        }

        Registry.register(Registries.ITEM, itemKey, item);
        return item;
    }

    public Item register(String name,
                                QuadFunction<
                                        Item.Settings, Map<String, String>,
                                        GenerativeItemInterface.ModelKind,
                                        Function<RecipeExporter, Void>,
                                        GenerativeItem> itemFactory,
                                Item.Settings settings,
                                Map<String, String> translations,
                                GenerativeItemInterface.ModelKind modelKind,
                                Function<RecipeExporter, Void> recipeGenerator,
                                RegistryKey<ItemGroup> itemGroup)
    {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Flats.MOD_ID, name));

        GenerativeItem item = itemFactory.apply(settings, translations, modelKind, recipeGenerator);
        items.add(item);

        Registry.register(Registries.ITEM, itemKey, item);
        ItemGroupEvents.modifyEntriesEvent(itemGroup)
                .register((group) -> group.add(item));

        return item;
    }

    public Item register(String name,
                                QuadFunction<
                                        Item.Settings, Map<String, String>,
                                        GenerativeItemInterface.ModelKind,
                                        Function<RecipeExporter, Void>,
                                        GenerativeItem> itemFactory,
                                Item.Settings settings,
                                Map<String, String> translations,
                                GenerativeItemInterface.ModelKind modelKind,
                                Function<RecipeExporter, Void> recipeGenerator)
    {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Flats.MOD_ID, name));

        GenerativeItem item = itemFactory.apply(settings, translations, modelKind, recipeGenerator);
        items.add(item);

        Registry.register(Registries.ITEM, itemKey, item);
        return item;
    }
}
