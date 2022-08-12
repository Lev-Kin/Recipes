package recipes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
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
    Recipe getRecipe(@PathVariable int id) {
        Optional<Recipe> recipeById = recipeService.getRecipeById(id);
        if (recipeById.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return recipeById.get();
    }

    @GetMapping(value = "/api/recipe/search")
    List<Recipe> getCategoryListRecipes
            (
                    @RequestParam(value = "category", required = false) String category,
                    @RequestParam(value = "name", required = false) String name
            ) {
        if (category != null && name == null) {
            return recipeService.getCategoryList(category);
        } else if (name != null && category == null) {
            return recipeService.getNameList(name);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/api/recipe/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateRecipe(@Valid @PathVariable long id, @Valid @RequestBody Recipe recipe) {
        Optional<Recipe> recipeById = recipeService.getRecipeById(id);
        if (recipeById.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        recipeService.saveRecipe(recipe, id);
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

