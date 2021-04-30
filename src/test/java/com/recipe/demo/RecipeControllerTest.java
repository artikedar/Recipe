package com.recipe.demo;

import com.recipe.demo.component.RecipeValidator;
import com.recipe.demo.controller.RecipeController;
import com.recipe.demo.entity.Recipe;
import com.recipe.demo.service.RecipeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class RecipeControllerTest {

    @InjectMocks
    private RecipeController recipeController;

    @Mock
    private RecipeService recipeService;

    @Mock
    private RecipeValidator recipeValidator;

    @Mock
    private Recipe recipe;

    @Test
    public void testCreateRecipe() {
        Mockito.when(recipeValidator.isValidInput(recipe)).thenReturn(Boolean.TRUE);
        Mockito.when(recipeService.createRecipe(recipe)).thenReturn("Successfully saved recipe");
        ResponseEntity<String> expectedResponse = recipeController.createRecipe(recipe);
        Assert.assertEquals(201, expectedResponse.getStatusCodeValue());
    }

    @Test
    public void testDeleteRecipe() {
        Mockito.doNothing().when(recipeValidator).validateRecipeName("recipeName");
        Mockito.when(recipeService.deleteRecipe("recipeName")).thenReturn("Successfully saved recipe");
        ResponseEntity<String> expectedResponse = recipeController.deleteRecipe("recipeName");
        Assert.assertEquals(202, expectedResponse.getStatusCodeValue());
    }
}
