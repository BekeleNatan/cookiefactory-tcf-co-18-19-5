package fr.unice.polytech.cod.unitaryTesting;

import fr.unice.polytech.cod.Menu;
import fr.unice.polytech.cod.recipe.*;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class MenuTest {
    Menu menu = new Menu();
    Recipe recipeAdded;

    @Test
    public void addRecipe() {
        assertEquals(0, menu.getListOfAvailableRecipes().size());
        this.recipeAdded = menu.addRecipe("RandomRecipe", Dough.Chocolate, Flavour.Chili, Topping.MandMs, Cooking.Chewy, Mix.Mixed, 5.0);
        assertEquals(1, menu.getListOfAvailableRecipes().size());
    }

    @Test
    public void getRecipeByName() {
        this.recipeAdded = menu.addRecipe("RandomRecipe", Dough.Chocolate, Flavour.Chili, Topping.MandMs, Cooking.Chewy, Mix.Mixed, 5.0);
        System.out.println(menu.getListOfAvailableRecipes().size());
        Optional<Recipe> optionalRecipe = menu.getRecipeByName("RandomRecipe");
        if (optionalRecipe.isPresent()) {
            optionalRecipe.get().getName();
            assertEquals(recipeAdded, optionalRecipe.get());
        } else {
            throw new RuntimeException("Recipe not found");
        }
    }
}