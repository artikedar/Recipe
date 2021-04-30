package com.recipe.demo.service;

import com.recipe.demo.entity.Recipe;

import java.util.List;

public interface RecipeServiceInterface {

    String createRecipe(Recipe recipe);

    String deleteRecipe(String name);

    List<Recipe> getAllRecipes();

    String updateRecipe(Recipe recipe);
}
