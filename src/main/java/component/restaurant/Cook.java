package component.restaurant;

import burger.Burger;
import burger.Ingredients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class Cook {
    private static final Logger LOGGER = LoggerFactory.getLogger(Cook.class);
    private final String name;
    private final Map<List<Ingredients>, String> recipes;

    public Cook(String name, Map<List<Ingredients>, String> recipes) {
        this.name = name;
        this.recipes = recipes;
    }

    @Override
    public String toString() {
        return "Cook: " + name;
    }

    public Burger makeFood(List<Ingredients> ingredients) {
        final String type = recipes.get(ingredients);
        if (type == null) {
            LOGGER.warn(this + " can't process ingredients: " + ingredients);
            return null;
        }
        final Burger food = new Burger(type, ingredients);
        LOGGER.info(this + " created " + food);
        return food;
    }

    public Map<List<Ingredients>, String> getRecipes() {
        return recipes;
    }
}
