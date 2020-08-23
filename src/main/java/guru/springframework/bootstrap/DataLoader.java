package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
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
        log.debug("Loading data");
        Optional<UnitOfMeasure> tableSpoon=unitOfMeasureRepository.findByDescription("Tablespoon");
        Optional<UnitOfMeasure> teaSpoon=unitOfMeasureRepository.findByDescription("Teaspoon");
        Optional<UnitOfMeasure> cup=unitOfMeasureRepository.findByDescription("Cup");
        Optional<UnitOfMeasure> clove=unitOfMeasureRepository.findByDescription("Clove");
        Optional<UnitOfMeasure> each=unitOfMeasureRepository.findByDescription("Each");
        Optional<UnitOfMeasure> pinch=unitOfMeasureRepository.findByDescription("Pinch");
        Optional<UnitOfMeasure> pint=unitOfMeasureRepository.findByDescription("Pint");
        Optional<UnitOfMeasure> pound=unitOfMeasureRepository.findByDescription("Pound");
        Optional<Category> mexicanCategory=categoryRepository.findByDescription("Mexican");
        Optional<Category> americanCategory=categoryRepository.findByDescription("American");


        Recipe perfectGuacamole= new Recipe();
        perfectGuacamole.setDescription("Perfect Guacamole");
        Notes perfectGuacamoleNotes=new Notes();
        perfectGuacamoleNotes.setRecipeNotes("perfect guacamole notes");
        perfectGuacamole.setNotes(perfectGuacamoleNotes);
        perfectGuacamole.setCookTime(10);
        perfectGuacamole.setPrepTime(0);
        perfectGuacamole.setDifficulty(Difficulty.EASY);
        perfectGuacamole.setServings(4);
        perfectGuacamole.setSource("Reddit");
        perfectGuacamole.setUrl("https://davidbates.net");
        perfectGuacamole.setDirections("The simplest version of guacamole is just mashed avocados with salt. Don’t let the lack of availability of other ingredients stop you from making guacamole.\n");
        perfectGuacamole.setNotes(perfectGuacamoleNotes);

        perfectGuacamole.getCategories().add(mexicanCategory.get());
        perfectGuacamole.getCategories().add(americanCategory.get());
        log.debug("Created recipe :" + perfectGuacamole.getDescription());

        addIngredientToRecipe(perfectGuacamole,each,"Avocado", new BigDecimal(1.0));
        addIngredientToRecipe(perfectGuacamole,teaSpoon,"Salt", new BigDecimal(.25));
        addIngredientToRecipe(perfectGuacamole,tableSpoon,"Lemon Juice", new BigDecimal(1.0));
        addIngredientToRecipe(perfectGuacamole,each,"Serrano Chiles", new BigDecimal(1.0));
        addIngredientToRecipe(perfectGuacamole,tableSpoon,"Cilantro", new BigDecimal(2.0));
        addIngredientToRecipe(perfectGuacamole,pinch,"Black Pepper", new BigDecimal(1.0));
        addIngredientToRecipe(perfectGuacamole,each,"Ripe Tomato", new BigDecimal(1.0));

        recipeRepository.save(perfectGuacamole);

        Recipe spicyGrilledChickenTacos= new Recipe();
        spicyGrilledChickenTacos.setDescription("Spicy Grilled Chicken Tacos");
        Notes spicyGrilledChickenTacosNotes=new Notes();
        spicyGrilledChickenTacosNotes.setRecipeNotes("spicy grilled chicken tacos notes.");
        spicyGrilledChickenTacos.setNotes(spicyGrilledChickenTacosNotes);
        spicyGrilledChickenTacos.setCookTime(9);
        spicyGrilledChickenTacos.setPrepTime(20);
        spicyGrilledChickenTacos.setServings(4);
        spicyGrilledChickenTacos.setSource("Dark Web");
        spicyGrilledChickenTacos.setUrl("https://davidbates.net");
        spicyGrilledChickenTacos.setDifficulty(Difficulty.MODERATE);
        spicyGrilledChickenTacos.setDirections("In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n");
        spicyGrilledChickenTacos.getCategories().add(mexicanCategory.get());
        spicyGrilledChickenTacos.getCategories().add(americanCategory.get());
        log.debug("Created Recipe: " + spicyGrilledChickenTacos.getDescription());

        addIngredientToRecipe(spicyGrilledChickenTacos,tableSpoon,"Ancho Chili Powder", new BigDecimal(2.0));
        addIngredientToRecipe(spicyGrilledChickenTacos,teaSpoon,"Dired Oregano", new BigDecimal(1.0));
        addIngredientToRecipe(spicyGrilledChickenTacos,teaSpoon,"Dried Cumin", new BigDecimal(1.0));
        addIngredientToRecipe(spicyGrilledChickenTacos,teaSpoon,"Sugar", new BigDecimal(1.0));
        addIngredientToRecipe(spicyGrilledChickenTacos,teaSpoon,"Salt", new BigDecimal(.5));
        addIngredientToRecipe(spicyGrilledChickenTacos,clove,"Chopped Garlic", new BigDecimal(1.0));
        addIngredientToRecipe(spicyGrilledChickenTacos,tableSpoon,"Orange Zest", new BigDecimal(1.0));
        addIngredientToRecipe(spicyGrilledChickenTacos,tableSpoon,"Orange Juice", new BigDecimal(3.0));
        addIngredientToRecipe(spicyGrilledChickenTacos,tableSpoon,"Olive Oil", new BigDecimal(2.0));
        addIngredientToRecipe(spicyGrilledChickenTacos,pound,"Skinless, boneless chicken thigh", new BigDecimal(5.0));
        addIngredientToRecipe(spicyGrilledChickenTacos,each,"Corn Tortillas", new BigDecimal(8.0));
        addIngredientToRecipe(spicyGrilledChickenTacos,cup,"Packed baby arugula", new BigDecimal(3.0));
        addIngredientToRecipe(spicyGrilledChickenTacos,each,"Sliced Radishes", new BigDecimal(3.0));
        addIngredientToRecipe(spicyGrilledChickenTacos,pint,"Cherry tomatoes, halved", new BigDecimal(.5));
        addIngredientToRecipe(spicyGrilledChickenTacos,each,"Sliced Red Onion", new BigDecimal(.25));
        addIngredientToRecipe(spicyGrilledChickenTacos,pinch,"Cilantro", new BigDecimal(1.0));
        addIngredientToRecipe(spicyGrilledChickenTacos,each,"Avocados", new BigDecimal(2.0));
        addIngredientToRecipe(spicyGrilledChickenTacos,cup,"Sour Cream", new BigDecimal(.5));
        addIngredientToRecipe(spicyGrilledChickenTacos,each,"Lime wedges", new BigDecimal(1.0));

        recipeRepository.save(spicyGrilledChickenTacos);
    }

    void addIngredientToRecipe(Recipe recipe, Optional<UnitOfMeasure> uom, String ingredientDescription, BigDecimal ingredientAmount) {
        log.debug("Adding " + ingredientDescription + " to " + recipe.getDescription());
        Ingredient ingredient = new Ingredient();
        ingredient.setDescription(ingredientDescription);
        ingredient.setUom(uom.get());
        ingredient.setAmount(ingredientAmount);
        recipe.addIngredient(ingredient);
        ingredient.setRecipe(recipe);
    }

}
