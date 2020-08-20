package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {
    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService= new RecipeServiceImpl(recipeRepository);
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