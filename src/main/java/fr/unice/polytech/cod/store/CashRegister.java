package fr.unice.polytech.cod.store;

import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.payment.PaymentMethod;
import fr.unice.polytech.cod.payment.PaymentMethodFactory;

public class CashRegister {

	public boolean pay(Order aOrder, PaymentMethod aPaymentMethod, Double aAmount) {
		throw new UnsupportedOperationException();
	}

	public Double remainToPay(Order aOrder) {
		throw new UnsupportedOperationException();
	}

	public boolean applyDiscount(Order aOrder, Double aDiscountRate) {
		throw new UnsupportedOperationException();
	}
}