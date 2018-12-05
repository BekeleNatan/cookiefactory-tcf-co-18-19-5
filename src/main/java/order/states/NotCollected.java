package order.states;

import order.Order;

public class NotCollected extends OrderState {

	public NotCollected(Order order) {
		super(order);
	}

	public boolean nextState() {
		return false;
	}

	public void cancelState() { }
}