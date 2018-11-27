package fr.unice.polytech.cod.order.states;

public class Refused implements OrderState {

	public void nextState() {
		throw new UnsupportedOperationException();
	}

	public void cancelState() {
		throw new UnsupportedOperationException();
	}

	public void getCurrentState() {
		throw new UnsupportedOperationException();
	}

	public boolean pay(CashRegister aCaissePayed) {
		throw new UnsupportedOperationException();
	}
}