package fr.unice.polytech.cod.order.states;

import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.store.CashRegister;

public class OnCreation extends OrderState {

	protected OnCreation(Order order) {
		super(order);
	}

	public void nextState() {
		throw new UnsupportedOperationException();
	}

	public void cancelState() {
		throw new UnsupportedOperationException();
	}

	public void getCurrentState() {
		throw new UnsupportedOperationException();
	}

}