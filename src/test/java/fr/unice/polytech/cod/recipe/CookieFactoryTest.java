package fr.unice.polytech.cod.recipe;

import fr.unice.polytech.cod.recipe.ingredients.Ingredient;
import fr.unice.polytech.cod.recipe.ingredients.IngredientType;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CookieFactoryTest {
    CookieFactory cookieFactory = new CookieFactory(1, 1, 0,
            1, 1, 3, 2);

    private static final double DELTA = 1e-15;
    private List<Ingredient> ingredientsSample;

    @Before
    public void initialize() {
        ingredientsSample = new ArrayList<>();
        ingredientsSample.add(new Ingredient("Plain", IngredientType.Dough, 1));
        ingredientsSample.add(new Ingredient("Vanilla", IngredientType.Flavour, 1));
        ingredientsSample.add(new Ingredient("M&M's", IngredientType.Topping, 1));
        ingredientsSample.add(new Ingredient("Milk chocolate", IngredientType.Topping, 1));
    }

    @Test
    public void createNormalRecipeSuccessfully() {
        NormalRecipe myCookie = cookieFactory.createNormalRecipe("Chocolala", 4, CookingType.Crunchy,
                MixType.Mixed, ingredientsSample);
        assertEquals("Chocolala", myCookie.getName());
        assertEquals(4, myCookie.getPrice(), DELTA);
        assertEquals(ingredientsSample, myCookie.getIngredients());
        assertEquals(CookingType.Crunchy, myCookie.getCookingType());
        assertEquals(MixType.Mixed, myCookie.getMixType());
        assertEquals(CookieType.NormalRecipe, myCookie.getCookieType());

    }

    @Test
    public void createPersonnalizedRecipeSuccessfully() {
        PersonnalizedRecipe myCookie = cookieFactory.createPersonnalizedRecipe(3, CookingType.Crunchy, MixType.Mixed, ingredientsSample);
        assertEquals(6, myCookie.getPrice(), DELTA); // sum of ingredient prices = 4 + specialMargin = 2 donc total = 6
        assertEquals(CookingType.Crunchy, myCookie.getCookingType());
        assertEquals(MixType.Mixed, myCookie.getMixType());
        assertEquals(CookieType.personnalizedRecipe, myCookie.getCookieType());
        assertEquals(ingredientsSample, myCookie.getIngredients());
    }

    @Test
    public void createNormalRecipeFailedMoreDoughs() {
        ArrayList<String> illegalArgExceptionList = new ArrayList<>();

        ingredientsSample.add(new Ingredient("Plain", IngredientType.Dough, 1));
        try {
            PersonnalizedRecipe myCookie = cookieFactory.createPersonnalizedRecipe(3, CookingType.Crunchy, MixType.Mixed, ingredientsSample);
        } catch (IllegalArgumentException e) {
            illegalArgExceptionList.add(e.getMessage());
        }

        assertEquals(1, illegalArgExceptionList.size());
        assertEquals("You surpass the maximum of possible Doughs", illegalArgExceptionList.get(0));
    }

    @Test
    public void createNormalRecipeFailedLessDoughs() {
        ArrayList<String> illegalArgExceptionList = new ArrayList<>();
        ArrayList<Ingredient> ingredientToRemove = new ArrayList<>();
        for (Ingredient ingredient : ingredientsSample) {
            if (ingredient.getType().equals(IngredientType.Dough)) {
                ingredientToRemove.add(ingredient);
            }
        }

        for (Ingredient ingredient : ingredientToRemove) {
            ingredientsSample.remove(ingredient);
        }

        try {
            PersonnalizedRecipe myCookie = cookieFactory.createPersonnalizedRecipe(3, CookingType.Crunchy, MixType.Mixed, ingredientsSample);
        } catch (IllegalArgumentException e) {
            illegalArgExceptionList.add(e.getMessage());
        }
        assertEquals(1, illegalArgExceptionList.size());
        assertEquals("You have lower quantity than possible Doughs", illegalArgExceptionList.get(0));
    }

    @Test
    public void createNormalRecipeFailedMoreFlavours() {
        ArrayList<String> illegalArgExceptionList = new ArrayList<>();

        ingredientsSample.add(new Ingredient("Vanilla", IngredientType.Flavour, 1));
        try {
            PersonnalizedRecipe myCookie = cookieFactory.createPersonnalizedRecipe(3, CookingType.Crunchy, MixType.Mixed, ingredientsSample);
        } catch (IllegalArgumentException e) {
            illegalArgExceptionList.add(e.getMessage());
        }

        assertEquals(1, illegalArgExceptionList.size());
        assertEquals("You surpass the maximum of possible Flavours", illegalArgExceptionList.get(0));
    }

    @Test
    public void createNormalRecipeFailedMoreToppings() {
        ArrayList<String> illegalArgExceptionList = new ArrayList<>();

        ingredientsSample.add(new Ingredient("MilkChocolate", IngredientType.Topping, 1));
        ingredientsSample.add(new Ingredient("Buttercup", IngredientType.Topping, 1));
        ingredientsSample.add(new Ingredient("Peanuts", IngredientType.Topping, 1));

        try {
            PersonnalizedRecipe myCookie = cookieFactory.createPersonnalizedRecipe(3, CookingType.Crunchy, MixType.Mixed, ingredientsSample);
        } catch (IllegalArgumentException e) {
            illegalArgExceptionList.add(e.getMessage());
        }

        assertEquals(1, illegalArgExceptionList.size());
        assertEquals("You surpass the maximum of possible Toppings", illegalArgExceptionList.get(0));
    }


    @Test
    public void createNormalRecipeFailedLessToppings() {
        ArrayList<String> illegalArgExceptionList = new ArrayList<>();
        ArrayList<Ingredient> ingredientToRemove = new ArrayList<>();
        for (Ingredient ingredient : ingredientsSample) {
            if (ingredient.getType().equals(IngredientType.Topping)) {
                ingredientToRemove.add(ingredient);
            }
        }

        for (Ingredient ingredient : ingredientToRemove) {
            ingredientsSample.remove(ingredient);
        }

        try {
            PersonnalizedRecipe myCookie = cookieFactory.createPersonnalizedRecipe(3, CookingType.Crunchy, MixType.Mixed, ingredientsSample);
        } catch (IllegalArgumentException e) {
            illegalArgExceptionList.add(e.getMessage());
        }
        assertEquals(1, illegalArgExceptionList.size());
        assertEquals("You have lower quantity than possible Toppings", illegalArgExceptionList.get(0));
    }


}