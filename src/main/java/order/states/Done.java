package order.states;

import order.Order;

public class Done extends OrderState {

	public Done(Order order) {
		super(order);
	}

	public boolean nextState() {
		if (context.isPayed()){
			context.setCurrentState(new Collected(context));
			return true;
		}
		return false;
	}

	public void cancelState() {
		context.setCurrentState(new NotCollected(context));
	}
}