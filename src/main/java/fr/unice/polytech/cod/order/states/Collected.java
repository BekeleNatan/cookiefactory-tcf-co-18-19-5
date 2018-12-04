package fr.unice.polytech.cod.order.states;

import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.store.CashRegister;

public class Collected extends OrderState {

	public Collected(Order order) {
		super(order);
	}

	public boolean nextState() {
		return false;
	}

	public void cancelState() { }

}