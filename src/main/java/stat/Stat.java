package stat;


import order.Order;

import java.util.List;

public abstract class Stat {

    public abstract int compute(List<Order> orders);

}
