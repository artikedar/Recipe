# Recipe
# Recipe
#Start the application
#There are 4 apis to create, update, delete, list all recipes
# The recipe input is as follow :

# Create Recipe: 
API : http://localhost:8081/api/recipe/recipe
POST Mapping: 
Request body : 
{
   "createdAt":null,
   "name" : "second",
   "vegetarian":true,
   "forPeople":8,
   "ingredients": "Salt, Pepper, water",
   "cookingInstructions":"follow these steps"
}

Expected Response: 200 , "Successfully saved recipe"

# Get All Recipe: 
API : http://localhost:8081/api/recipe/recipe
Get Mapping: 
Expected Response: 
[
    {
        "id": 1,
        "name": "second",
        "createdAt": null,
        "forPeople": 8,
        "ingredients": "Salt, Pepper, water",
        "cookingInstructions": "follow these steps",
        "vegetarian": true
    }
]

#Delete Recipe:
API : http://localhost:8081/api/recipe/recipe
Delete Mapping: 
Request Body : name of the recipe
Expected Response : Successfully deleted recipe: second

#Update Recipe
API : http://localhost:8081/api/recipe/recipe
Put Mapping: 
Request Body : 
{
   "createdAt":null,
   "name" : "second",
   "vegetarian":true,
   "forPeople":9,
   "ingredients": "Salt, Pepper, water",
   "cookingInstructions":"follow these steps"
}

Response : 204 No content
