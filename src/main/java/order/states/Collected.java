package order.states;

import order.Order;

public class Collected extends OrderState {

	public Collected(Order order) {
		super(order);
	}

	public boolean nextState() {
		return false;
	}

	public void cancelState() { }

}