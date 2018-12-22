package order;

import store.Stock;
import store.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderRegister {
    List<Order> orders;


    public OrderRegister(){
        orders = new ArrayList<>();
    }

    public Order findOrder(UUID aOrderId) {
        for (Order order : orders){
            if(order.getOrderId()==aOrderId){
                return order;
            }
        }
        return null;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public Order createNewOrder(Store store) {
        Order order = new Order(store);
        orders.add(order);
        return order;
    }
}