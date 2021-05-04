package com.recipe.demo.repository;

import com.recipe.demo.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    /**
     * Query to update the recipe
     * @param ingredients
     * @param cookingInstructions
     * @param isVegetarian
     * @param forPeople
     * @param id
     */
    @Modifying
    @Query("update Recipe recipe set recipe.ingredients = ?1, recipe.cookingInstructions = ?2, recipe.isVegetarian = ?3, recipe.forPeople = ?4 where recipe.id = ?5")
    void updateRecipe(String ingredients, String cookingInstructions, Boolean isVegetarian, Integer forPeople, Long id);

    /**
     * Query to find the recipe by name
     * @param name
     * @return
     */
    @Query(value="select  recipe from Recipe recipe where recipe.name like %?1")
    Recipe findByName(String name);
}
