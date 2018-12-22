package stat;

import order.Item;
import order.Order;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CookiesByDay extends Stat {
    @Override
    public int compute(List<Order> orders) {
        Set<Integer> days = new HashSet<>();
        int numOfCookies=0;
        for(Order order: orders){
            days.add(order.getCollectTime().getDate());
            for(Item item: order.items){
                numOfCookies+= item.getQuantity();
            }
        }
        return numOfCookies/days.size();
    }
}
