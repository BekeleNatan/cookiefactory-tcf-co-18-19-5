package order.states;

import order.Order;

public class ToDo extends OrderState {

	public ToDo(Order order) {
		super(order);
	}

	public boolean nextState(){
		context.setCurrentState(new Done(context));
		return true;
	}

}