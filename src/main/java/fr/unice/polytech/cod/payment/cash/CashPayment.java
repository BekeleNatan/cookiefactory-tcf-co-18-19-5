package fr.unice.polytech.cod.payment.cash;

import fr.unice.polytech.cod.payment.PaymentMethod;

public class CashPayment implements PaymentMethod {

	public boolean pay(Double aAmount) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean refund(Double aAmount) {
		return false; // todo
	}
}