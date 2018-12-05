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
        cookieFactory = new CookieFactory(1, 1, 0,
                1, 1, 3, 2);

        ingredientsSample = new ArrayList<>();
        ingredientsSample.add(new Ingredient("Chocolate", IngredientType.Dough, 1, 1));
        ingredientsSample.add(new Ingredient("Cinnamon", IngredientType.Flavour, 1, 1));
        ingredientsSample.add(new Ingredient("White chocolate", IngredientType.Topping, 1, 1));
        ingredientsSample.add(new Ingredient("Milk chocolate", IngredientType.Topping, 1, 1));

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
        assertEquals(expected, store.getStoreMenu().getMenu(new Stock()));

        store.getStoreMenu().removeRecipe(monthly);
        assertTrue(store.getStoreMenu().getMenu(new Stock()).isEmpty());
    }


    @Test
    public void getMenuWithOnlyFranchiseRecipe() {
        assertTrue(store.getStoreMenu().getMenu(new Stock()).isEmpty());
        franchiseMenu.getMenu().add(recipe1);
        store.getStoreMenu().getMenu(new Stock());
        ArrayList<Recipe> expected = new ArrayList<>();
        expected.add(recipe1);
        assertEquals(expected, store.getStoreMenu().getMenu(new Stock()));
    }

    @Test
    public void getMenuWithFranchiseAndStoresRecipe() {
        StoreMenu storesMenu = store.getStoreMenu();
        storesMenu.addRecipe(recipe2);
        franchiseMenu.addRecipe(recipe1);
        ArrayList<Recipe> expected = new ArrayList<>();
        expected.add(recipe1);
        expected.add(recipe2);
        assertEquals(expected, store.getStoreMenu().getMenu(new Stock()));
    }
}