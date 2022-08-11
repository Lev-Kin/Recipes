package recipes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class RecipeService {
    private RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public long saveRecipe(Recipe toSave) {
        Recipe saveRecipe = recipeRepository.save(toSave);
        return saveRecipe.getId();
    }

    public Optional<Recipe> getRecipeById(long id) {
        if (recipeRepository.findById(id).isPresent()) {
            return Optional.of(recipeRepository.findById(id).get());
        } else {
            return Optional.empty();
        }
    }

    public void deleteRecipeById(long id) {
        recipeRepository.deleteById(id);
    }

    public List<Recipe> getAllRecipes() {
        List<Recipe> recipeList = new ArrayList<>();
        recipeRepository.findAll().forEach(recipeList::add);
        return recipeList;
    }
}

