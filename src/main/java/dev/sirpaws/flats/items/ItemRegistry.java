package dev.sirpaws.flats.items;

import dev.sirpaws.flats.Flats;
import dev.sirpaws.flats.items.interfaces.GenerativeItem;
import dev.sirpaws.flats.items.interfaces.GenerativeItemInterface;
import dev.sirpaws.flats.util.function.QuadFunction;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;


public class ItemRegistry {
    private static final ItemRegistrar registrar = new ItemRegistrar();
    public static final Item PILE_OF_SAND = registrar.register("pile_of_sand", PileOfSand::PileOfNormalSand,
            new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(0)
                    .saturationModifier(0)
                    .alwaysEdible().build()),
            ItemGroups.FOOD_AND_DRINK);

    public static final Item PILE_OF_RED_SAND = registrar.register("pile_of_red_sand", PileOfSand::PileOfRedSand,
            new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(0)
                    .saturationModifier(0)
                    .alwaysEdible().build()),
            ItemGroups.FOOD_AND_DRINK);

    public static ArrayList<GenerativeItemInterface> getItems() {
        return registrar.items;
    }

    public static void initialize() {}
}
