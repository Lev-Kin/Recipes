package recipes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;


@RestController
@Validated
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @PostMapping("/api/recipe/new")
    Map<String, Long> setRecipe(@Valid @RequestBody Recipe recipe) {
        long id = recipeService.saveRecipe(recipe);
        return Map.of("id", id);
    }

    @GetMapping("/api/recipe/{id}")
    Map<String, Object> getRecipe(@PathVariable int id) {
        Optional<Recipe> recipeById = recipeService.getRecipeById(id);
        if (recipeById.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Recipe recipe = recipeById.get();
        Map<String, Object> recipeToReturn = new LinkedHashMap<>();
        recipeToReturn.put("name", recipe.getName());
        recipeToReturn.put("description", recipe.getDescription());
        recipeToReturn.put("ingredients", recipe.getIngredients());
        recipeToReturn.put("directions", recipe.getDirections());
        return recipeToReturn;
    }

    @DeleteMapping("/api/recipe/{id}")
    ResponseEntity<?> deleteRecipe(@Valid @PathVariable long id) {
        Optional<Recipe> recipeById = recipeService.getRecipeById(id);
        if (recipeById.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        recipeService.deleteRecipeById(id);
        return ResponseEntity.noContent().build();
    }
}

