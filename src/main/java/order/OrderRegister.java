package order;

import fr.unice.polytech.cod.stats.Stats;
import store.Stock;

import java.util.List;
import java.util.UUID;

public class OrderRegister {
    List<Order> orders;
    List<Stats> stats;

    public OrderRegister(){

    }

    public Order findOrder(UUID aOrderId) {
        for (Order order : orders){
            if(order.getOrderId()==aOrderId){
                return order;
            }
        }
        return null;
    }

    public Order createNewOrder() {
        Order order = new Order();
        orders.add(order);
        return order;
    }
}