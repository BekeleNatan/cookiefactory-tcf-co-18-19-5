package fr.unice.polytech.cod.order.states;

import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.store.CashRegister;

public class Done extends OrderState {

	public Done(Order order) {
		super(order);
	}

	public boolean nextState() {
		if (context.payed){
			context.setCurrentState(new Collected(context));
			return true;
		}
		return false;
	}

	public void cancelState() {
		context.setCurrentState(new NotCollected(context));
	}
}