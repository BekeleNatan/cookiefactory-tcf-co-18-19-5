package fr.unice.polytech.cod.payment.creditcard;

import fr.unice.polytech.cod.payment.PaymentMethod;

public class CreditCardPayment implements PaymentMethod {
	private String nameOnCard;
	private String number;
	private String cvv;
	private String expirationDate;

	public boolean pay(Double aAmount) {
		throw new UnsupportedOperationException();
	}
}