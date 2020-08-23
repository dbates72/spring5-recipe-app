package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {
    private RecipeServiceImpl recipeService;

    private Long id=1L;

    @Mock
    private RecipeRepository recipeRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService= new RecipeServiceImpl(recipeRepository);
    }

    @Test
    public void getRecipe() {
        //Mock return data setup
        Recipe recipe = new Recipe();
        recipe.setId(id);

        //Mock object setup: when recipeService calls recipeRepository.findAll(), return the recipesData that we setup back to recipeService
        when(recipeRepository.findById(id)).thenReturn(Optional.of(recipe));

        //Test of code
        Recipe foundRecipe = recipeService.getRecipe(id);

        //Assertions
        assertNotNull(foundRecipe);
        assertEquals(1, foundRecipe.getId());
        verify(recipeRepository, times(1)).findById(id);
    }

    @Test
    public void getRecipes() {
        //Mock return data setup
        Recipe recipe = new Recipe();
        HashSet<Recipe> recipesData = new HashSet<>();
        recipesData.add(recipe);

        //Mock object setup: when recipeService calls recipeRepository.findAll(), return the recipesData that we setup back to recipeService
        when(recipeRepository.findAll()).thenReturn(recipesData);

        //Test of code
        Set<Recipe> recipes = recipeService.getRecipes();

        //Assertions
        assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll();
    }
}