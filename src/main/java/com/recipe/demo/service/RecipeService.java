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

import static com.recipe.demo.util.Constants.*;

@Service
public class RecipeService implements RecipeServiceInterface {

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    /**
     *
     * @param recipe
     * @return
     * returns RECIPE_SAVED_MESSAGE string on successful recipe save action
     */
    @Override
    public String createRecipe(Recipe recipe) {
        recipeRepository.save(recipe);
        return RECIPE_SAVED_MESSAGE;
    }

    /**
     *
     * @param name
     * @return
     * returns RECIPE_DELETED_MESSAGE string on successful recipe delete action
     * throws RecipeNotFoundException if the recipe is not found in db
     */
    @Override
    public String deleteRecipe(String name) {
        Recipe recipe = recipeRepository.findByName(name);
        if (Objects.nonNull(recipe)) {
            recipeRepository.delete(recipe);
            return RECIPE_DELETED_MESSAGE + name;
        } else {
            throw new RecipeNotFoundException(Constants.RECIPE_NOT_FOUND_MSG);
        }
    }

    /**
     *
     * @return
     * returns list of recipes available in the db
     */
    @Override
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    /**
     *
     * @param recipe
     * @return
     * returns RECIPE_UPDATED_MESSAGE string on successful recipe update action
     * throws RecipeDoesNotExistException if the recipe does not exists in db
     */
    @Override
    @Transactional
    public String updateRecipe(Recipe recipe) {
        Recipe existingRecipe = recipeRepository.findByName(recipe.getName());
        if (!Objects.nonNull(existingRecipe)) {
            throw new RecipeDoesNotExistException(Constants.RECIPE_DOES_NOT_EXIST_MSG);
        }
        recipeRepository.updateRecipe(recipe.getIngredients(), recipe.getCookingInstructions(), recipe.isVegetarian(), recipe.getForPeople(), existingRecipe.getId());
        return RECIPE_UPDATED_MESSAGE;
    }
}