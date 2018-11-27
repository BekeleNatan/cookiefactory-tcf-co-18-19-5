package fr.unice.polytech.cod.payment.creditcard;

import fr.unice.polytech.cod.payment.PaymentMethod;

public class CreditCardPayment implements PaymentMethod {
	private String _nameOnCard;
	private String _number;
	private String _cvv;
	private String _expirationDate;

	public boolean pay(Double aAmount) {
		throw new UnsupportedOperationException();
	}
}