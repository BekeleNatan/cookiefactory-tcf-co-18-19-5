package fr.unice.polytech.cod.order.states;

import fr.unice.polytech.cod.store.CashRegister;

public class notCollected implements OrderState {

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