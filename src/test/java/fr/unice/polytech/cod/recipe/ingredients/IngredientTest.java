package fr.unice.polytech.cod.recipe.ingredients;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IngredientTest {

    private static final double DELTA = 1e-15;
    private Ingredient ingredient;

    @Before
    public void initialize(){
        ingredient = new Ingredient("M&Ms", IngredientType.Topping, 2, 1);
    }
    @Test
    public void getName() {
        assertEquals("M&Ms", ingredient.getName());
    }

    @Test
    public void getType() {
        assertEquals(IngredientType.Topping, ingredient.getType());
    }

    @Test
    public void getPricePerUnit() {
        assertEquals(3, ingredient.getPricePerUnit(), DELTA);
    }
}