package order.states;

import order.Order;
import store.CashRegister;

public class Refused extends OrderState {

	public Refused(Order order) {
		super(order);
		if(context.getPrice() != context.getRemainToPay()){
			context.getCustomer().sendMessage("Votre commande à été annulé");
			CashRegister cashRegister = new CashRegister();
			cashRegister.refund(order);
		}
	}

	public boolean nextState() {
		return false;
	}

	public void cancelState() { }

}