package dev.sirpaws.flats.items.interfaces;

import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.item.Item;

import java.util.Map;
import java.util.function.Function;

public class GenerativeItem extends Item implements GenerativeItemInterface {
    private final Map<String, String> translations;
    private final ModelKind modelKind;
    private final Function<RecipeExporter, Void> recipeGenerator;

    public GenerativeItem(Settings settings, Map<String, String> translations, ModelKind modelKind, Function<RecipeExporter, Void> recipeGenerator) {
        super(settings);
        this.translations = translations;
        this.modelKind = modelKind;
        this.recipeGenerator = recipeGenerator;
    }

    @Override
    public String getTranslation(String language) {
        if (translations.containsKey(language)) {
            return translations.get(language);
        }
        return "";
    }

    @Override
    public ModelKind getModel() {
        return modelKind;
    }
    @Override
    public void generateRecipe(RecipeExporter recipeExporter) {
        recipeGenerator.apply(recipeExporter);
    }
}
