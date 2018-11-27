package fr.unice.polytech.cod.order.states;

import fr.unice.polytech.cod.store.CashRegister;

public interface OrderState {

	public void nextState();

	public void cancelState();

	public void getCurrentState();
}