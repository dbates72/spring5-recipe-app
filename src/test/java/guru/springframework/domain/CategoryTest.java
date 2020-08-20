package guru.springframework.domain;

import org.junit.Before;

import java.util.HashSet;

import static org.junit.Assert.*;

public class CategoryTest {
    Category category;

    @Before
    public void setUp() {
        category=new Category();
    }

    @org.junit.Test
    public void getId() {
        Long idValue= 4L;
        category.setId(idValue);
        assertEquals(idValue, category.getId());
    }

    @org.junit.Test
    public void getDescription() {
        String description = "description";
        category.setDescription(description);
        assertEquals(description, category.getDescription());
    }

    @org.junit.Test
    public void getRecipes() {
        HashSet<Recipe> recipes=new HashSet<>();
        Recipe recipe1=new Recipe();
        recipe1.setDescription("one");
        recipes.add(recipe1);
        Recipe recipe2=new Recipe();
        recipe2.setDescription("two");
        recipes.add(recipe2);

        category.setRecipes(recipes);
        assertEquals(recipes, category.getRecipes());
        assertEquals(2, category.getRecipes().size());
    }
}