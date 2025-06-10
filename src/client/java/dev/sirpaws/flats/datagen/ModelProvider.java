package dev.sirpaws.flats.datagen;

import dev.sirpaws.flats.items.interfaces.GenerativeItemInterface;
import dev.sirpaws.flats.items.ItemRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.Item;

import java.util.ArrayList;

public class ModelProvider extends FabricModelProvider {
    public ModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        ArrayList<GenerativeItemInterface> items = ItemRegistry.getItems();
        for (GenerativeItemInterface item : items) {
            switch (item.getModel()) {
                case GENERATED:
                    itemModelGenerator.register((Item) item, Models.GENERATED);
                    break;
                default:
                    break;
            }
        }
    }
}
