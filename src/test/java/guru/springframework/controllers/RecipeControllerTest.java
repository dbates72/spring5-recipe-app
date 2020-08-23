package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RecipeControllerTest {
    private RecipeController recipeController;
    private Long id=1L;
    private MockMvc mockMvc;

    @Mock
    private Model model;

    @Mock
    private RecipeService recipeService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        recipeController=new RecipeController(recipeService);
    }

    @Test
    public void testMvc() throws Exception {
        //MockMvc test
        mockMvc= MockMvcBuilders.standaloneSetup(recipeController).build();
        mockMvc.perform(get("/recipe/show/" + id))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"));
    }

    @Test
    public void getRecipe() {
        //Data setup
        Recipe recipe = new Recipe();
        recipe.setId(id);

        //Mockito setup
        when(recipeService.getRecipe(id)).thenReturn(recipe);
        ArgumentCaptor<Recipe> argumentCaptor = ArgumentCaptor.forClass(Recipe.class);

        //Test method
        String recipeView=recipeController.getRecipe(model);

        //JUnit Assertions
        assertEquals("recipe/show", recipeView);
        verify(recipeService, times(1)).getRecipe(id);
        verify(model, times(1)).addAttribute(eq("recipe"), argumentCaptor.capture());
        Recipe returnedRecipe = argumentCaptor.getValue();
        assertNotNull(returnedRecipe);
        assertEquals(id, returnedRecipe.getId());
    }
}
