package com.recipe.demo.controller;

import com.recipe.demo.component.RecipeValidator;
import com.recipe.demo.entity.Recipe;
import com.recipe.demo.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private RecipeService recipeService;
    private RecipeValidator recipeValidator;

    @Autowired
    public RecipeController(RecipeService recipeService, RecipeValidator recipeValidator) {
        this.recipeService = recipeService;
        this.recipeValidator = recipeValidator;
    }

    @PostMapping
    public ResponseEntity<String> createRecipe(@Valid @RequestBody Recipe recipe) {
        recipeValidator.isValidInput(recipe);
        return new ResponseEntity(recipeService.createRecipe(recipe), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteRecipe(@Valid @RequestBody String name) {
        recipeValidator.validateRecipeName(name);
        return new ResponseEntity(recipeService.deleteRecipe(name), HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<List<Recipe>> viewAllRecipies() {
        return new ResponseEntity(recipeService.getAllRecipes(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> updateRecipe(@Valid @RequestBody Recipe recipe) {
        recipeValidator.isValidInput(recipe);
        return new ResponseEntity<>(recipeService.updateRecipe(recipe), HttpStatus.NO_CONTENT);
    }

}