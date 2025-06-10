package dev.sirpaws.flats.items;

import dev.sirpaws.flats.items.interfaces.GenerativeItemInterface;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.ActionResult;

import static net.minecraft.data.server.recipe.RecipeProvider.conditionsFromItem;
import static net.minecraft.data.server.recipe.RecipeProvider.hasItem;

public class PileOfSand extends Item implements GenerativeItemInterface {
    public enum SandType {
        NORMAL, RED
    }
    public static PileOfSand PileOfNormalSand(Settings settings) { return new PileOfSand(settings, SandType.NORMAL); }
    public static PileOfSand PileOfRedSand(Settings settings) { return new PileOfSand(settings, SandType.RED); }

    private final SandType type;
    public PileOfSand(Settings settings, SandType type) {
        super(settings);
        this.type = type;
    }

    public Block blockFromSandType(SandType type) {
        switch (type) {
            case NORMAL -> {
                return Blocks.SAND;
            }
            case RED -> {
                return Blocks.RED_SAND;
            }
        }
        return null;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockState state = context.getWorld().getBlockState(context.getBlockPos());
        if (state.isOf(blockFromSandType(type))) {
            context.getStack().decrement(1);
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    @Override
    public String getTranslation(String language) {
        if (!language.equals("en_us")) {
            return "";
        }

        switch (type) {
            case NORMAL -> {
                return "Pile Of Sand";
            }
            case RED -> {
                return "Pile Of Red Sand";
            }
        }
        return "";
    }

    @Override
    public ModelKind getModel() {
        return ModelKind.GENERATED;
    }

    @Override
    public void generateRecipe(RecipeExporter recipeExporter) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, this, 9)
                .input(blockFromSandType(type))
                .criterion(hasItem(blockFromSandType(type).asItem()), conditionsFromItem(blockFromSandType(type).asItem()))
                .offerTo(recipeExporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, blockFromSandType(type))
                .input(this)
                .input(this)
                .input(this)
                .input(this)
                .input(this)
                .input(this)
                .input(this)
                .input(this)
                .input(this)
                .criterion(hasItem(this), conditionsFromItem(this))
                .offerTo(recipeExporter);
    }
}
