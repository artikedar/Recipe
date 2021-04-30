package com.recipe.demo.component;

import com.recipe.demo.entity.Recipe;
import com.recipe.demo.exception.InvalidInputException;
import com.recipe.demo.util.Constants;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RecipeValidator {

    public Boolean isValidInput(Recipe recipe) {
        if(Objects.isNull(recipe)) {
            throw new InvalidInputException(Constants.RECIPE_NOT_FOUND_MSG);
        }
        validateRecipeName(recipe.getName());
        validateRecipeIngredients(recipe.getIngredients());
        validateCookingInstructions(recipe.getCookingInstructions());
        validateForPeople(recipe.getForPeople());
        validateIsVegetarian(recipe.isVegetarian());
        return Boolean.TRUE;
    }

    public void validateIsVegetarian(Boolean vegetarian) {
        if(Objects.isNull(vegetarian)) {
            throw new InvalidInputException(Constants.RECIPE_VEGETARIAN_FLAG_NOT_NULL_MSG);
        }
    }

    public void validateForPeople(Integer forPeople) {
        if(Objects.isNull(forPeople)) {
            throw new InvalidInputException(Constants.RECIPE_NUMBER_OF_PEOPLE_NOT_NULL_MSG);
        }
    }

    public void validateCookingInstructions(String cookingInstructions) {
        if(Objects.isNull(cookingInstructions)) {
            throw new InvalidInputException(Constants.RECIPE_INSTRUCTIONS_NOT_NULL_MSG);
        }
    }

    public void validateRecipeIngredients(String ingredients) {
        if(Objects.isNull(ingredients)) {
            throw new InvalidInputException(Constants.RECIPE_INGREDIENTS_NOT_NULL_MSG);
        }
    }

    public void validateRecipeName(String name) {
        if(Objects.isNull(name)) {
            throw new InvalidInputException(Constants.RECIPE_NAME_NOT_NULL_MSG);
        }
    }
}
