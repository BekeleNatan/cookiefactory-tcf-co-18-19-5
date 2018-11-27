package fr.unice.polytech.cod.order.states;

public class Done implements OrderState {

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