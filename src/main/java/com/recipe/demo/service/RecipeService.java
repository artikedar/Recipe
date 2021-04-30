package com.recipe.demo.service;

import com.recipe.demo.entity.Recipe;
import com.recipe.demo.exception.RecipeDoesNotExistException;
import com.recipe.demo.exception.RecipeNotFoundException;
import com.recipe.demo.repository.RecipeRepository;
import com.recipe.demo.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class RecipeService implements RecipeServiceInterface {

    private RecipeRepository recipeRepository;
//    private RecipeValidator recipeValidator;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository/*, RecipeValidator recipeValidator*/) {
        this.recipeRepository = recipeRepository;
//        this.recipeValidator = recipeValidator;
    }

    @Override
    public String createRecipe(Recipe recipe) {
        recipeRepository.save(recipe);
        return "Successfully saved recipe";
    }

    @Override
    public String deleteRecipe(String name) {
//        recipeValidator.validateRecipeName(name);
        Recipe recipe = recipeRepository.findByName(name);
        if (Objects.nonNull(recipe)) {
            recipeRepository.delete(recipe);
            return "Successfully deleted recipe: " + name;
        } else {
            throw new RecipeNotFoundException(Constants.RECIPE_NOT_FOUND_MSG);
        }
    }

    @Override
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @Override
    @Transactional
    public String updateRecipe(Recipe recipe) {
        Recipe existingRecipe = recipeRepository.findByName(recipe.getName());
        if (!Objects.nonNull(existingRecipe)) {
            throw new RecipeDoesNotExistException(Constants.RECIPE_DOES_NOT_EXIST_MSG);
        }
        Long id = existingRecipe.getId();
        recipeRepository.updateRecipe(recipe.getIngredients(), recipe.getCookingInstructions(), recipe.isVegetarian(), recipe.getForPeople(), existingRecipe.getId());
        return "Successfully updated recipe";
    }
}