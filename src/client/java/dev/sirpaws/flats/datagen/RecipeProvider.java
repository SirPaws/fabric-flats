package dev.sirpaws.flats.datagen;

import dev.sirpaws.flats.items.ItemRegistry;
import dev.sirpaws.flats.items.interfaces.GenerativeItemInterface;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class RecipeProvider extends FabricRecipeProvider {
    public RecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter recipeExporter) {
        ArrayList<GenerativeItemInterface> items = ItemRegistry.getItems();
        for (GenerativeItemInterface item : items) {
            item.generateRecipe(recipeExporter);
        }
    }
}
