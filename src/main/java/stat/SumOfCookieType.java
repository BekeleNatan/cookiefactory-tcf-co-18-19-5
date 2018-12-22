package stat;

import order.Item;
import order.Order;
import recipe.CookieType;
import recipe.CookingType;

import java.util.List;
import java.util.stream.Collectors;

public class SumOfCookieType extends Stat {

    @Override
    public int compute(List<Order> orders) {
        int sum =0;
        for (Order order:orders){

            for(Item item: order.items){

                if (item.getRecipe().getCookieType().equals(CookieType.personnalizedRecipe))
                    sum = sum + item.getQuantity();
            }
        }
          return sum;
    }
}
