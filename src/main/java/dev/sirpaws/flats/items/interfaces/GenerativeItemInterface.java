package dev.sirpaws.flats.items.interfaces;


import net.minecraft.data.server.recipe.RecipeExporter;

public interface GenerativeItemInterface {
    enum ModelKind {
        GENERATED,
    }
    String getTranslation(String language);
    ModelKind getModel();

    void generateRecipe(RecipeExporter recipeExporter);
}
