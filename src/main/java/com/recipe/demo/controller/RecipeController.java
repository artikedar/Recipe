package com.recipe.demo.controller;

import com.recipe.demo.component.RecipeValidator;
import com.recipe.demo.entity.Recipe;
import com.recipe.demo.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.recipe.demo.util.Constants.RECIPE_URL;

@RestController
@RequestMapping(RECIPE_URL)
public class RecipeController {

    private final RecipeService recipeService;
    private final RecipeValidator recipeValidator;

    @Autowired
    public RecipeController(RecipeService recipeService, RecipeValidator recipeValidator) {
        this.recipeService = recipeService;
        this.recipeValidator = recipeValidator;
    }

    /**
     *
     * @param recipe : recipe to be created
     * @return
     * Returns 201 for successful recipe creation
     * Returns 400 for invalid recipe input
     */
    @PostMapping
    public ResponseEntity createRecipe(@Valid @RequestBody Recipe recipe) {
        recipeValidator.isValidInput(recipe);
        return new ResponseEntity(recipeService.createRecipe(recipe), HttpStatus.CREATED);
    }

    /**
     *
     * @param name : recipe name to be deleted
     * @return
     * Returns 202 for successful recipe deletion
     * Returns 400 for invalid recipe name input
     */
    @DeleteMapping
    public ResponseEntity deleteRecipe(@Valid @RequestBody String name) {
        recipeValidator.validateRecipeName(name);
        return new ResponseEntity(recipeService.deleteRecipe(name), HttpStatus.ACCEPTED);
    }

    /**
     *
     * @return
     * Returns 200 for successful response
     */
    @GetMapping
    public ResponseEntity viewAllRecipes() {
        return new ResponseEntity(recipeService.getAllRecipes(), HttpStatus.OK);
    }

    /**
     *
     * @param recipe
     * @return
     * Returns 204 for successful recipe update operation
     * Returns 400 for invalid recipe input
     */
    @PutMapping
    public ResponseEntity<String> updateRecipe(@Valid @RequestBody Recipe recipe) {
        recipeValidator.isValidInput(recipe);
        return new ResponseEntity<>(recipeService.updateRecipe(recipe), HttpStatus.NO_CONTENT);
    }

}