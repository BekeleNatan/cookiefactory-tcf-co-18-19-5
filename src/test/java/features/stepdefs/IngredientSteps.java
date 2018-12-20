package features.stepdefs;

import cucumber.api.java8.En;
import franchise.Franchise;
import franchise.FranchiseMenu;
import recipe.ingredients.Ingredient;
import recipe.ingredients.IngredientType;
import store.Stock;
import store.Store;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class IngredientSteps implements En {

    private IngredientType type;
    private String name;
    private double price;
    private double marginPrice;
    private int oldQuantiy;
    private Franchise franchise = null;
    private FranchiseMenu franchiseMenu = null;
    private Store store = null;
    private Stock stock = null;

    private IngredientType getIngredientType(String type){

        switch (type.toLowerCase()){

            case "flavour" :
                return IngredientType.Flavour;
            case "topping" :
                return IngredientType.Topping;
            case "dough" :
                return IngredientType.Dough;
            default:
                return null;
        }

    }

    public IngredientSteps() {
        Given("^some ingredients exist with a name \"([^\"]*)\" ,a type \"([^\"]*)\" , a price of \"([^\"]*)\" and a priceMargin of \"([^\"]*)\":$", (String name, String type ,String price ,String MarginPrice) -> {
            Ingredient ingredient = new Ingredient(name,getIngredientType(type),Double.parseDouble(price),Double.parseDouble(MarginPrice));
        });
        Given("^the type \"([^\"]*)\" of ingredient is known$", (String type) -> {
             this.type = getIngredientType(type);
        });
        When("^the store \"([^\"]*)\" of franchise \"([^\"]*)\" has ingredient and he doesn't know the name and price and margin price$", (String franchiseName, String storeName) -> {
            franchise = new Franchise(franchiseName);
            FranchiseMenu franchiseMenu = new FranchiseMenu();
            franchise.addStore(storeName, franchiseMenu);
            this.store = franchise.getStoreByName(storeName).get();
            this.name = "";
             this.price = 0;
             this.marginPrice = 0;
        });
        Then("^the ingredient is had to store like an unknown ingredient$", () -> {

             Ingredient ingredient = new Ingredient(this.name,this.type,this.price,this.marginPrice);

             this.stock = this.store.getStock();
             this.oldQuantiy = this.stock.getQuantity(ingredient);
             this.stock.addIngredient(ingredient,(this.stock.getQuantity(ingredient))+1);
             int newQuantity = this.stock.getQuantity(ingredient);
             assertEquals(oldQuantiy+1,newQuantity);

        });


    }
}
