DROP TABLE IF EXISTS RECIPE;
CREATE TABLE RECIPE (
                        RECIPE_id INT AUTO_INCREMENT  PRIMARY KEY,
                        RECIPE_name VARCHAR(50) NOT NULL,
                        RECIPE_createdAt VARCHAR(50) NOT NULL,
                        RECIPE_vegetarian INT(8) NOT NULL,
                        RECIPE_forPeople VARCHAR(50) NOT NULL,
                        RECIPE_ingredients VARCHAR(50) NOT NULL,
                        RECIPE_cookingInstructions VARCHAR(50) NOT NULL
);  