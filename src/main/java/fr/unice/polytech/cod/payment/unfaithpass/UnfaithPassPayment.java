package fr.unice.polytech.cod.payment.unfaithpass;

import fr.unice.polytech.cod.payment.PaymentMethod;

public abstract class UnfaithPassPayment implements PaymentMethod {
	private String qrCode;

	public boolean pay(Double aAmount) {
		throw new UnsupportedOperationException();
	}
}