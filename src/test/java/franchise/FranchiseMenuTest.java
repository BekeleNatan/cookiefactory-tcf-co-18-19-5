package franchise;

import recipe.*;
import recipe.ingredients.Ingredient;
import recipe.ingredients.IngredientType;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FranchiseMenuTest {

    CookieFactory cookieFactory = new CookieFactory(1, 1, 0,
            1, 1, 3, 2);

    ArrayList<Ingredient> ingredientsSample;

    FranchiseMenu franchiseMenu;

    @Before
    public void initialize() {
        ingredientsSample = new ArrayList<>();
        ingredientsSample.add(new Ingredient("Chocolate", IngredientType.Dough, 1, 1));
        ingredientsSample.add(new Ingredient("Cinnamon", IngredientType.Flavour, 1, 1));
        ingredientsSample.add(new Ingredient("White chocolate", IngredientType.Topping, 1, 1));
        ingredientsSample.add(new Ingredient("Milk chocolate", IngredientType.Topping, 1, 1));

        franchiseMenu = new FranchiseMenu();
    }

    @Test
    public void addingToFranchiseMenu() {
        NormalRecipe recipe1 = cookieFactory.createNormalRecipe("M&Ms", 5, CookingType.Crunchy, MixType.Mixed, ingredientsSample);
        NormalRecipe recipe2 = cookieFactory.createNormalRecipe("Chaps", 4, CookingType.Crunchy, MixType.Mixed, ingredientsSample);
        List<Recipe> expectedRecipes = new ArrayList<>();
        expectedRecipes.add(recipe1);
        expectedRecipes.add(recipe2);

        franchiseMenu.addRecipe(recipe1);
        franchiseMenu.addRecipe(recipe2);

        assertEquals(expectedRecipes, franchiseMenu.getMenu());
    }

    @Test
    public void removingFromFranchiseMenu() {
        NormalRecipe recipe1 = cookieFactory.createNormalRecipe("M&Ms", 5, CookingType.Crunchy, MixType.Mixed, ingredientsSample);
        NormalRecipe recipe2 = cookieFactory.createNormalRecipe("Emily", 2, CookingType.Chewy, MixType.Mixed, ingredientsSample);

        franchiseMenu.addRecipe(recipe1);
        assertFalse(franchiseMenu.getMenu().isEmpty());

        franchiseMenu.removeRecipe(recipe1);
        assertTrue(franchiseMenu.getMenu().isEmpty());
    }

    @Test
    public void getMenuEmpty() {
        assertTrue(franchiseMenu.getMenu().isEmpty());
    }
}