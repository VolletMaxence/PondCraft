package com.example.tutomod.datagen;

import com.example.tutomod.setup.Registration;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class TutRecipes extends RecipeProvider {

    public TutRecipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

            SimpleCookingRecipeBuilder.smelting(Ingredient.of(Registration.PLATINUM_ORES_ITEM),
                        Registration.PLATINUM_INGOT.get(), 1.0f, 100)
                    .unlockedBy("has_ore", has(Registration.PLATINUM_ORES_ITEM))
                    .save(consumer, "platinum_ingot1");
            SimpleCookingRecipeBuilder.smelting(Ingredient.of(Registration.RAW_PLATINUM.get()),
                        Registration.PLATINUM_INGOT.get(), 0.0f, 100)
                .unlockedBy("has_chunk", has(Registration.RAW_PLATINUM.get()))
                .save(consumer, "platinum_ingot2");
    }
}
