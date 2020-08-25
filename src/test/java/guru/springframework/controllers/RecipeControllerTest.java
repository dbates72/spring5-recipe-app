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
    private Long id1=1L;
    private String desc1="desc 1";
    private Long id2=2L;
    private String desc2="desc 2";
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
        mockMvc.perform(get("/recipe/" + id1 + "/show/"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"));
        mockMvc.perform(get("/recipe/" + id2 + "/show/"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"));
    }

    @Test
    public void testGetRecipe() {
        getRecipe(id1,desc1);
        //Something with verifying number of times breaks when I run this method twice
        //Trying to check to see if the output changes for different IDs
        //getRecipe(id2,desc2);
    }

    public void getRecipe(Long id, String desc) {
        //Data setup
        Recipe recipe = new Recipe();
        recipe.setId(id);
        recipe.setDescription(desc);

        //Mockito setup
        when(recipeService.getRecipe(id)).thenReturn(recipe);
        ArgumentCaptor<Recipe> argumentCaptor = ArgumentCaptor.forClass(Recipe.class);

        //Test method
        String recipeView=recipeController.getRecipe(String.valueOf(id),model);

        //JUnit Assertions
        assertEquals("recipe/show", recipeView);
        verify(recipeService, times(1)).getRecipe(id);
        verify(model, times(1)).addAttribute(eq("recipe"), argumentCaptor.capture());
        Recipe returnedRecipe = argumentCaptor.getValue();
        assertNotNull(returnedRecipe);
        assertEquals(id, returnedRecipe.getId());
        assertEquals(desc, returnedRecipe.getDescription());
    }
}
