package fr.unice.polytech.cod.order.states;

public interface OrderState {

	public void nextState();

	public void cancelState();

	public void getCurrentState();

	public boolean pay(CashRegister aCaissePayed);
}