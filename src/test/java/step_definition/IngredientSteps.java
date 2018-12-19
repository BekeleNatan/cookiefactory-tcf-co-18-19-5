package step_definition;

import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;
import recipe.ingredients.Ingredient;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class IngredientSteps implements En {
    public IngredientSteps() {
        Given("^all the ingredients are available$", () -> {
        });
        When("^he add \"([^\"]*)\" tooping \"([^\"]*)\" dough \"([^\"]*)\"$", (String arg0, String arg1, String arg2) -> {
        });
        And("^choose \"([^\"]*)\" like Mix type , \"([^\"]*)\" like cooking type$", (String arg0, String arg1) -> {
        });
        Given("^some ingredients exist :$", (DataTable dataTable) -> {
            List<Map<String, String>> dataMaps = dataTable.asMaps(String.class ,String.class);
            dataMaps.forEach(dataMap -> {

                  //  Ingredient ingredient = new Ingredient(dataMap.get(0),(dataMaps.get(1)),((dataMaps.get(2)),((dataMaps.get(3)))));
            });


        });

    }
}
