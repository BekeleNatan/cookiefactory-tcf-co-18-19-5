package fr.unice.polytech.cod.store;

import fr.unice.polytech.cod.recipe.ingredients.Ingredient;
import fr.unice.polytech.cod.recipe.ingredients.IngredientType;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class StockTest {

    Stock stock;
    Ingredient ingredient1;

    @Before
    public void initialize() {
        stock = new Stock();
        ingredient1 = new Ingredient("M&Ms", IngredientType.Topping, 2, 2);
        stock.addIngredient(ingredient1, 20);
    }

    @Test
    public void addIngredient() {
        Optional<Ingredient> optionalIngredient1 = stock.getIngredientByName("M&Ms");
        assertTrue(optionalIngredient1.isPresent() && optionalIngredient1.get().equals(ingredient1));
        assertTrue(optionalIngredient1.isPresent() && stock.getQuantity(ingredient1) == 20);
    }

    @Test
    public void removeIngredient() {
        System.out.println(stock.ingredients.values());
        stock.removeIngredient(ingredient1, 10);
        Optional<Ingredient> optionalIngredient = stock.getIngredientByName("M&Ms");
        assertTrue(optionalIngredient.isPresent() && stock.getQuantity(optionalIngredient.get()) == 10);

        stock.removeIngredient(ingredient1, 10);
        assertTrue(optionalIngredient.isPresent() && stock.getQuantity(optionalIngredient.get()) == 0);
    }

    @Test
    public void getNonExistingIngredientByName() {
        Optional<Ingredient> optionalIngredient2 = stock.getIngredientByName("Chocobons");
        assertTrue(!optionalIngredient2.isPresent());
    }

    @Ignore
    public void getIngredientsByType() {
        List<Ingredient> expectedToppings = new ArrayList<>();
        expectedToppings.add(ingredient1);
        Ingredient ingredientTopping2 = new Ingredient("topping2", IngredientType.Topping, 2, 2);
        Ingredient ingredientTopping3 = new Ingredient("topping3", IngredientType.Topping, 2, 2);

        stock.addIngredient(ingredientTopping2, 20);
        expectedToppings.add(ingredientTopping2);

        stock.addIngredient(ingredientTopping3, 30);
        expectedToppings.add(ingredientTopping3);

        assertEquals(expectedToppings, stock.getIngredientsByType(IngredientType.Topping));


        List<Ingredient> expectedFlavours = new ArrayList<>();
        Ingredient ingredientFlavour1 = new Ingredient("flavour1", IngredientType.Flavour, 2, 2);
        Ingredient ingredientFlavour2 = new Ingredient("flavour2", IngredientType.Flavour, 2, 2);
        stock.addIngredient(ingredientFlavour1, 20);
        expectedFlavours.add(ingredientFlavour1);
        stock.addIngredient(ingredientFlavour2, 30);
        expectedToppings.add(ingredientFlavour2);
        assertEquals(expectedFlavours, stock.getIngredientsByType(IngredientType.Flavour));
    }
}