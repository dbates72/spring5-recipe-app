package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataLoader implements CommandLineRunner {
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final CategoryRepository categoryRepository;

    public DataLoader(RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository, CategoryRepository categoryRepository) {
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {
        Iterable<UnitOfMeasure> uoms=unitOfMeasureRepository.findAll();
        Iterable<Category> categories=categoryRepository.findAll();

        Recipe perfectGuacamole= new Recipe();
        perfectGuacamole.setDescription("Perfect Guacamole");
        Notes perfectGuacamoleNotes=new Notes();
        perfectGuacamoleNotes.setRecipeNotes("perfect guacamole notes");
        perfectGuacamole.setNotes(perfectGuacamoleNotes);
        perfectGuacamole.setCookTime(10);
        perfectGuacamole.setPrepTime(0);
        perfectGuacamole.setDifficulty(Difficulty.EASY);
        perfectGuacamole.setDirections("The simplest version of guacamole is just mashed avocados with salt. Don’t let the lack of availability of other ingredients stop you from making guacamole.\n");
        addRecipeCategory(categories,perfectGuacamole,"Mexican");

        addIngredientToRecipe(perfectGuacamole,uoms,"Avocado", "Each", new BigDecimal(1.0));
        addIngredientToRecipe(perfectGuacamole,uoms,"Salt", "Teaspoon", new BigDecimal(.25));
        addIngredientToRecipe(perfectGuacamole,uoms,"Lemon Juice", "Tablespoon", new BigDecimal(1.0));
        addIngredientToRecipe(perfectGuacamole,uoms,"Serrano Chiles", "Each", new BigDecimal(1.0));
        addIngredientToRecipe(perfectGuacamole,uoms,"Cilantro", "Tablespoon", new BigDecimal(2.0));
        addIngredientToRecipe(perfectGuacamole,uoms,"Black Pepper", "Pinch", new BigDecimal(1.0));
        addIngredientToRecipe(perfectGuacamole,uoms,"Ripe Tomato", "Each", new BigDecimal(1.0));

        recipeRepository.save(perfectGuacamole);

        Recipe spicyGrilledChickenTacos= new Recipe();
        spicyGrilledChickenTacos.setDescription("Spicy Grilled Chicken Tacos");
        Notes spicyGrilledChickenTacosNotes=new Notes();
        spicyGrilledChickenTacosNotes.setRecipeNotes("spicy grilled chicken tacos notes.");
        spicyGrilledChickenTacos.setNotes(spicyGrilledChickenTacosNotes);
        spicyGrilledChickenTacos.setCookTime(9);
        spicyGrilledChickenTacos.setPrepTime(20);
        spicyGrilledChickenTacos.setDifficulty(Difficulty.MODERATE);
        spicyGrilledChickenTacos.setDirections("In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n");
        addRecipeCategory(categories,spicyGrilledChickenTacos,"Mexican");

        addIngredientToRecipe(spicyGrilledChickenTacos,uoms,"Ancho Chili Powder", "Tablespoon", new BigDecimal(2.0));
        addIngredientToRecipe(spicyGrilledChickenTacos,uoms,"Dired Oregano", "Teaspoon", new BigDecimal(1.0));
        addIngredientToRecipe(spicyGrilledChickenTacos,uoms,"Dried Cumin", "Teaspoon", new BigDecimal(1.0));
        addIngredientToRecipe(spicyGrilledChickenTacos,uoms,"Sugar", "Teaspoon", new BigDecimal(1.0));
        addIngredientToRecipe(spicyGrilledChickenTacos,uoms,"Salt", "Teaspoon", new BigDecimal(.5));
        addIngredientToRecipe(spicyGrilledChickenTacos,uoms,"Chopped Garlic", "Clove", new BigDecimal(1.0));
        addIngredientToRecipe(spicyGrilledChickenTacos,uoms,"Orange Zest", "Tablespoon", new BigDecimal(1.0));
        addIngredientToRecipe(spicyGrilledChickenTacos,uoms,"Orange Juice", "Tablespoon", new BigDecimal(3.0));
        addIngredientToRecipe(spicyGrilledChickenTacos,uoms,"Olive Oil", "Tablespoon", new BigDecimal(2.0));
        addIngredientToRecipe(spicyGrilledChickenTacos,uoms,"Skinless, boneless chicken thigh", "Pound", new BigDecimal(5.0));
        addIngredientToRecipe(spicyGrilledChickenTacos,uoms,"Corn Tortillas", "Each", new BigDecimal(8.0));
        addIngredientToRecipe(spicyGrilledChickenTacos,uoms,"Packed baby arugula", "Cup", new BigDecimal(3.0));
        addIngredientToRecipe(spicyGrilledChickenTacos,uoms,"Sliced Radishes", "Each", new BigDecimal(3.0));
        addIngredientToRecipe(spicyGrilledChickenTacos,uoms,"Cherry tomatoes, halved", "Pint", new BigDecimal(.5));
        addIngredientToRecipe(spicyGrilledChickenTacos,uoms,"Sliced Red Onion", "Each", new BigDecimal(.25));
        addIngredientToRecipe(spicyGrilledChickenTacos,uoms,"Cilantro", "Pinch", new BigDecimal(1.0));
        addIngredientToRecipe(spicyGrilledChickenTacos,uoms,"Avocados", "Each", new BigDecimal(2.0));
        addIngredientToRecipe(spicyGrilledChickenTacos,uoms,"Sour Cream", "Cup", new BigDecimal(.5));
        addIngredientToRecipe(spicyGrilledChickenTacos,uoms,"Lime wedges", "Each", new BigDecimal(1.0));

        recipeRepository.save(spicyGrilledChickenTacos);
    }

    void addIngredientToRecipe(Recipe recipe, Iterable uoms, String ingredientDescription, String ingredientUom, BigDecimal ingredientAmount) {
        Ingredient ingredient = new Ingredient();
        ingredient.setDescription(ingredientDescription);
        setIngredientUom(uoms,ingredient,ingredientUom);
        ingredient.setAmount(ingredientAmount);
        recipe.addIngredient(ingredient);
        ingredient.setRecipe(recipe);
    }

    void setIngredientUom(Iterable<UnitOfMeasure> uoms, Ingredient ingredient, String uomStr) {
        uoms.forEach(uom->{
            if (uom!=null){
                if(uom.getDescription().equals(uomStr)) {
                    System.out.println("UOM match: " + uom.getDescription());
                    ingredient.setUom(uom);
                }
            }
            else {
                System.out.println("UOM is NULL");
            }
        });
    }

    void addRecipeCategory(Iterable<Category> categories, Recipe recipe, String categoryStr) {
        categories.forEach(category-> {
            if(category!=null) {
                if(category.getDescription().equals(categoryStr)) {
                    System.out.println("Category match: " + category.getDescription());
                    if(recipe.getCategories()!=null) {
                        recipe.getCategories().add(category);
                    }
                    else {
                        System.out.println("Recipe categories is NULL");
                    }
                }
            }
            else {
                System.out.println("Category is NULL");
            }
        });
    }
}
