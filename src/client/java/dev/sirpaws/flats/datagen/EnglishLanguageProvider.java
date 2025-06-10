package dev.sirpaws.flats.datagen;

import dev.sirpaws.flats.items.interfaces.GenerativeItemInterface;
import dev.sirpaws.flats.items.ItemRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryWrapper;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class EnglishLanguageProvider extends FabricLanguageProvider {
    public EnglishLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        ArrayList<GenerativeItemInterface> items = ItemRegistry.getItems();
        for (GenerativeItemInterface item : items) {
            translationBuilder.add((Item)item, item.getTranslation("en_us"));
        }
    }
}
