package store;

import franchise.FranchiseMenu;
import recipe.*;
import recipe.ingredients.Ingredient;
import recipe.ingredients.IngredientType;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class StoreMenuTest {

    CookieFactory cookieFactory;

    ArrayList<Ingredient> ingredientsSample;

    FranchiseMenu franchiseMenu;

    NormalRecipe recipe1;
    NormalRecipe recipe2;
    Store store;

    @Before
    public void initialize() {
        cookieFactory = new CookieFactory(0, 1, 0,
                1, 0, 3, 2);

        ingredientsSample = new ArrayList<>();

        franchiseMenu = new FranchiseMenu();

        recipe1 = cookieFactory.createNormalRecipe("M&Ms", 5, CookingType.Crunchy, MixType.Mixed, ingredientsSample);
        recipe2 = cookieFactory.createNormalRecipe("Chocolala", 5, CookingType.Crunchy, MixType.Mixed, ingredientsSample);

        // franchiseMenu.addRecipe(recipe1);
        store = new Store("COD Antibes", franchiseMenu);
    }

    @Test
    public void setAndDeleteMonthlyRecipe() {
        NormalRecipe monthly = cookieFactory.createNormalRecipe("theMONTHLY", 5, CookingType.Crunchy, MixType.Mixed, ingredientsSample);
        store.getStoreMenu().addRecipe(monthly);
        List<Recipe> expected = new ArrayList<>();
        expected.add(monthly);
        assertEquals(expected, store.getStoreMenu().getMenu());

        store.getStoreMenu().removeRecipe(monthly);
        assertTrue(store.getStoreMenu().getMenu().isEmpty());
    }


    @Test
    public void getMenuWithOnlyFranchiseRecipe() {
        assertTrue(store.getStoreMenu().getMenu().isEmpty());
        franchiseMenu.getMenu().add(recipe1);
        store.getStoreMenu().getMenu();
        ArrayList<Recipe> expected = new ArrayList<>();
        expected.add(recipe1);
        assertEquals(expected, store.getStoreMenu().getMenu());
    }

    @Test
    public void getMenuWithFranchiseAndStoresRecipe() {
        StoreMenu storesMenu = store.getStoreMenu();
        storesMenu.addRecipe(recipe2);
        franchiseMenu.addRecipe(recipe1);
        ArrayList<Recipe> expected = new ArrayList<>();
        expected.add(recipe1);
        expected.add(recipe2);
        assertEquals(expected, store.getStoreMenu().getMenu());
    }

    @Test
    public void getMenuThatDoNotContainsRecipesWithIngredientsOutOfStock(){

        // creating a recipe that contains ingredients out of stock
        List<Ingredient> listIngredientsOutOfStock  = new ArrayList<>();
        listIngredientsOutOfStock.add(new Ingredient("ChocoBlanco", IngredientType.Topping, 2, 2));
        Recipe recipe = cookieFactory.createNormalRecipe("BlancoChocoladaCookie", 2, CookingType.Crunchy, MixType.Mixed, listIngredientsOutOfStock);

        store.getStoreMenu().addRecipe(recipe);
        // franchiseMenu.addRecipe(recipe1);
        ArrayList<Recipe> expected = new ArrayList<>();

        // trying to display a recipe that have ingredients, but using a empty stock
        List<Recipe> listOfDisplayedIngredients = store.getStoreMenu().displayMenu(new Stock());
        assertTrue(!listOfDisplayedIngredients.contains(recipe));
    }
}