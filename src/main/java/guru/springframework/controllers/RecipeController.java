package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class RecipeController {
   private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipe/show/?")
    public String getRecipe(Model model) {
        log.debug("In getRecipe");
        model.addAttribute("recipe", recipeService.getRecipe(1L));
        return "recipe/show";
    }
}
