package features.stepdefs;

import cucumber.api.java8.En;
import franchise.Franchise;
import franchise.FranchiseMenu;
import io.cucumber.datatable.DataTable;
import recipe.CookieFactory;
import recipe.CookingType;
import recipe.MixType;
import recipe.NormalRecipe;
import recipe.ingredients.Ingredient;
import recipe.ingredients.IngredientType;
import store.Stock;
import store.Store;
import store.StoreMenu;

import java.util.*;

import static java.lang.Integer.parseInt;
import static org.junit.Assert.*;


public class RecipeSteps implements En {
    private Franchise franchise = null;
    private Store store = null;
    private Stock stock = null;
    private  CookieFactory cookieFactory = null;
    private List<Ingredient> topping = new ArrayList<>();
    private int[] oldToppingQuantity = new int[3];
    private Ingredient dough = null;
    private Ingredient flavour = null;
    private List<Ingredient> ingredients = new ArrayList<>();
    private MixType mixType = null;
    private CookingType cookingType = null;
    private String name;
    private double price;
    private List<String> items = new ArrayList<>();
    FranchiseMenu franchiseMenu;
    StoreMenu storeMenu;
    NormalRecipe storeRecipe;



    private IngredientType getIngredientType(String type) {

        switch (type.toLowerCase()) {

            case "flavour":
                return IngredientType.Flavour;
            case "topping":
                return IngredientType.Topping;
            case "dough":
                return IngredientType.Dough;
            default:
                return null;
        }

    }

        private MixType getMixType(String mixType){

            switch (mixType.toLowerCase()){

                case "mixed" :
                    return MixType.Mixed;
                case "topped" :
                    return MixType.Topped;
                default:
                    return null;
            }

    }


    private CookingType getCookingType(String cookingType) {

        switch (cookingType.toLowerCase()) {

            case "crunchy":
                return CookingType.Crunchy;
            case "chewy":
                return CookingType.Chewy;
            default:
                return null;
        }
    }
    public RecipeSteps() {
        Given("^there is the franchise \"([^\"]*)\" that have a \"([^\"]*)\"$", (String franchiseName, String storeName) -> {
            franchise = new Franchise(franchiseName);
            franchiseMenu = new FranchiseMenu();
            franchise.addStore(storeName, franchiseMenu);
            this.store = franchise.getStoreByName(storeName).get();
            this.stock = this.store.getStock();


        });
        And("^this store have some ingredients:$", (DataTable dataTable) -> {
            List<Map<String, String>> dataMaps = dataTable.asMaps(String.class ,String.class);
            dataMaps.forEach(dataMap -> {

                Ingredient ingredient = new Ingredient(dataMap.get("name"),getIngredientType(dataMap.get("type")),Double.parseDouble(dataMap.get("price")),Double.parseDouble(dataMap.get("priceMargin")));
                this.stock.addIngredient(ingredient,4);
            });




        });
        Given("^the rules of creation are defined$", () -> {
           cookieFactory = new CookieFactory(1,1,1,1,1,3,3.5);
        });
        When("^he add \"([^\"]*)\", a \"([^\"]*)\" and \"([^\"]*)\"$", (String toppingNames, String doughName, String flavourName) -> {


            if(toppingNames!=null) {

                items.addAll(Arrays.asList(toppingNames.split(",")));
            }



            for (String item: items) {
                Optional<Ingredient> in = this.stock.getIngredientByName(item);
                in.ifPresent(ingredient -> topping.add(ingredient));
               // ingredient.ifPresent(ingredient1 -> this.topping.add(ingredient1));

            }

            ingredients.addAll(topping);



            Optional<Ingredient> dough = this.stock.getIngredientByName(doughName);
            dough.ifPresent(ingredient -> {

                        ingredients.add(ingredient);
                        this.dough = ingredient;
            });

            Optional<Ingredient> flavour = this.stock.getIngredientByName(flavourName);
            flavour.ifPresent(ingredient -> {

                ingredients.add(ingredient);
                this.flavour = ingredient;
            });






        });
        And("^choose \"([^\"]*)\" like Mix type , \"([^\"]*)\" like cooking type$", (String mixType, String cookingType) -> {

              this.mixType = getMixType(mixType);
              this.cookingType = getCookingType(mixType);
        });
        And("^name the recipe \"([^\"]*)\" and set its price to \"([^\"]*)\"$", (String name, String price) -> {

              this.name = name;
              this.price = Double.parseDouble(price);
        });
        Then("^the cookie is create$", () -> {

              NormalRecipe recipe1 = this.cookieFactory.createNormalRecipe(this.name,this.price,this.cookingType,this.mixType,this.ingredients);

              List<Ingredient> ingredients = new ArrayList<>();
              ingredients.add(new Ingredient("Cinnamon",IngredientType.Flavour,0.5,0.2));
              ingredients.add(new Ingredient("Oatmeal",IngredientType.Dough,0.7,0.5));
              ingredients.add(new Ingredient("Milk chocolate",IngredientType.Topping,1.0,0.2));
              NormalRecipe recipe2  = new NormalRecipe("LeMot",3.5,CookingType.Crunchy,MixType.Mixed,ingredients);
              assertNotEquals(recipe1,recipe2);



        });
        Given("^the store menu as create$", () -> {
             storeMenu = new StoreMenu(franchiseMenu);
        });
        When("^the store create the recipe that was added to the menu$", () -> {
            List<Ingredient> ingredients = new ArrayList<>();
            ingredients.add(new Ingredient("Cinnamon",IngredientType.Flavour,0.5,0.2));
            storeRecipe = new NormalRecipe(this.name,this.price,this.cookingType,this.mixType,ingredients);

        });
        Then("^the month's recipe is define$", () -> {
              storeMenu.addRecipe(storeRecipe);
        });


    }
}
